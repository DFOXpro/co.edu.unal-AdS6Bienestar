/* global app, angular */
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
				templateUrl: 'crear-taller-tmplt',
				controller: 'eventos'
			}).
//			when('/evento/convocatorias/:eventoId', {
//				templateUrl: 'crear-convocatoria-tmplt',
//				controller: 'eventos'
//			}).
			when('/inicio', {
				templateUrl: 'inicio-tmplt',
				controller: 'inicio'
			}).
			otherwise({
				redirectTo: '/inicio'
			});
	}]);
console.log("Admin cargado");
