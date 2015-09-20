/* global app */

app.controller('inicio', function ($rootScope, $scope, $sesion, $tabla) {
	console.log("inicio", $rootScope.nav([
		{url:"/inicio",nombre:"Inicio"}
	]));

	$scope.pagina = {
		titulo: "Usuario: ",
		subtitulo: window.atob(localStorage.getItem("6")),
		convocatoriasRecientes:[],
		talleresRecientes:[]
	};
	$tabla.get("usuario", "Convocatorias recientes", 0, 4, "/evento/convocatorias",function (r){$scope.pagina.convocatoriasRecientes = r;});
	$tabla.get("usuario", "Talleres recientes", 0, 4, "/evento/talleres",function (r){$scope.pagina.talleresRecientes = r;});
});
console.log("Admin inicio cargado");