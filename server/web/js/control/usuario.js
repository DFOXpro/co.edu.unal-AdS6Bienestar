/* global angular */
var app = angular.module('app', ['ngRoute']);
app.config(['$routeProvider',
	function ($routeProvider) {
		$routeProvider.
			when('/evento/:evento', {
				templateUrl: 'tablas-tmplt',
				controller: 'eventos'
			}).
			when('/evento/:evento/:eventoId', {
				templateUrl: 'lee-evento-tmplt',
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
console.log("Usuario cargado");
