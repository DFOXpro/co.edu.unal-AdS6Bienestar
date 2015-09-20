/* global app, angular */
app.controller('nav', function ($rootScope, $scope, $sesion) {
	$sesion.verificarSesion();
	$scope.cerrarSesion = $sesion.cerrarSesion;
	var b = localStorage.getItem("4")==="A";
	$scope.link={
		is : b,
		pagina:(b)? atob("L2FkbWluLmh0bWw="):"#",
		texto:(b)?atob("RW50cmFyIGNvbW8gYWRtaW5pc3RyYWRvcg=="):""
	};
	$scope.rutas = [];
	$rootScope.nav = function (rut){
		//console.log("nav()", rut);
		$scope.rutas = rut;
		return true;
	};
});