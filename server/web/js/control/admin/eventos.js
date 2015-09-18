/* global app */

app.controller('eventos', function ($rootScope, $scope,$route, $routeParams, $conexion, $sesion, $tabla) {
	console.log("eventos", $rootScope.nav([
		{url:"/inicio",nombre:"Inicio"},
		{url:"/"+$routeParams.evento,nombre:"Gestión de "+$routeParams.evento}
	]));
	var get = function (diff) {
		$scope.pagina.pos += diff;
		$scope.pagina.tabla = $tabla.get(
			"admin",
			$routeParams.evento,
			$scope.pagina.upos,
			10,
			"/"+$routeParams.evento
		);
		$scope.pagina.total = ($scope.pagina.tabla.total /10);// - (73%10 === 0)? 1:0;
		//TEST
//		$scope.pagina.usuarios = {
//			titulo: "Usuarios",
//			verAccion: "/usuarios",
//			//editarAccion:"/editareventos",
//			//eliminarAccion:"/eliminareventos",
//			lineas: [
//				{titulo: "Pedro Martines", id: 1},
//				{titulo: "Pepito Perez", id: 2},
//				{titulo: "Daniel Zorro", id: 3},
//				{titulo: "Pedro Martines", id: 1},
//				{titulo: "Pepito Perez", id: 2},
//				{titulo: "Daniel Zorro", id: 3},
//				{titulo: "Pedro Martines", id: 1},
//				{titulo: "Pepito Perez", id: 2},
//				{titulo: "Daniel Zorro", id: 3},
//				{titulo: "adasd adadasd", id: 4}
//			]
//		};
		//END TEST
	};

	$scope.pagina = {
		titulo: "Administrador: ",
		subtitulo: window.atob(localStorage.getItem("6")),
		pos: 1,
		total: 0,
		tabla: {},
		get: get
	};
	get(0);
});
console.log("Admin usuarios cargado");