/* global angular */

var app = angular.module('app', [
	'ngRoute'
]);

app.controller('usuario', function ($scope, $conexion) {
	console.log("usuario");
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