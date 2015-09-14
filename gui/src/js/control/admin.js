/* global app */

app.controller('admin', function ($scope, $conexion) {
	console.log("admin");
	$scope.cerrarSesion = function (){
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
});