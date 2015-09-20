/* global app */

app.factory('$sesion', function ($conexion) {
	var r = {};
	rutas = [];
	r.verificarSesion = function ($scope) {
		if(!$conexion.iniciar()){
			location.replace("/");
			return true;
		} else return false;
	};

	r.cerrarSesion = function (){
		console.log("Sesion.cerrarSesion");
		$conexion.enviar(
			"sesion",
			{
				tipo:"cerrar",
				1: localStorage.getItem("5")
			},
			function(){
				$conexion.terminar();
				location.replace("/");
			}
		);
	};
	r.setRutas = function (rut){rutas = rut;};
	r.getRutas = rutas;
	r.getId = window.atob(localStorage.getItem("7"));
	return r;
});
console.log("sesiónFactory cargado");
