/* global app */

app.factory('$sesion', function ($conexion) {
	var r = {};

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

	return r;
});
console.log("sesiónFactory cargado");
