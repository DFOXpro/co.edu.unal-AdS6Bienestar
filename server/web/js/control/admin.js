/* global app */
app.config(['$routeProvider',
	function ($routeProvider) {
		$routeProvider.
			when('/usuarios', {
				templateUrl: 'tablas-tmplt',
				controller: 'usuarios'
			}).
			when('/usuarios/:usarioId', {
				templateUrl: 'tablas-tmplt',
				controller: 'usuarios'
			}).
			when('/evento/:evento', {
				templateUrl: 'tablas-tmplt',
				controller: 'eventos'
			}).
			when('/evento/:evento/:eventoId', {
				templateUrl: 'tablas-tmplt',
				controller: 'eventos'
			}).
			when('/informe', {
				templateUrl: 'informe-tmplt',
				controller: 'informe'
			}).
			when('/inicio', {
				templateUrl: 'inicio-tmplt',
				controller: 'inicio'
			}).
			otherwise({
				redirectTo: '/inicio'
			});
	}]);
console.log("Admin cargado");
