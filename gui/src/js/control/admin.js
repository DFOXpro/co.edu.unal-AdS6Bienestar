/* global app */

app.controller('admin', function ($scope, $conexion, $sesion) {
	console.log("admin");
	$sesion.verificarSesion();
	$scope.cerrarSesion = $sesion.cerrarSesion;
});
console.log("Admin cargado");
