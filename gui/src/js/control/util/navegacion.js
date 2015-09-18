/* global app, angular */
app.controller('nav', function ($rootScope, $scope, $sesion) {
	console.log("nav");
	$sesion.verificarSesion();
	$scope.cerrarSesion = $sesion.cerrarSesion;
	$scope.rutas = [];
	$rootScope.nav = function (rut){
		//console.log("nav()", rut);
		$scope.rutas = rut;
		return true;
	};
});