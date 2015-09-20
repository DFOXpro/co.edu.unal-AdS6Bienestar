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

var app = angular.module('app', ['ngRoute','ez.datetime', 'ez.modal', 'ez.dropdown']);
app.config(['$routeProvider',
	function ($routeProvider) {
		$routeProvider.
			when('/usuarios', {
				templateUrl: 'tablas-tmplt',
				controller: 'usuarios'
			}).
			when('/usuarios/:usuarioId', {
				templateUrl: 'crear-usuario-tmplt',
				controller: 'usuarios'
			}).
			when('/evento/:evento', {
				templateUrl: 'tablas-tmplt',
				controller: 'eventos'
			}).
			when('/evento/:evento/:eventoId', {
				templateUrl: 'crear-evento-tmplt',
				controller: 'eventos'
			}).
			when('/inicio', {
				templateUrl: 'inicio-tmplt',
				controller: 'inicio'
			}).
			otherwise({
				redirectTo: '/inicio'
			});
	}]);