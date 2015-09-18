/* global app */

app.controller('usuarios', function ($rootScope, $scope, $conexion, $sesion, $tabla) {
	console.log("usuarios", $rootScope.nav([
		{url:"/inicio",nombre:"Inicio"},
		{url:"/usuarios",nombre:"Gestión de Usuarios"}
	]));
	var get = function (diff) {
		$scope.pagina.pos += diff;
		$tabla.get(
			"admin",
			"Usuarios",
			$scope.pagina.pos,
			10,
			"usuarios",
			function (r){$scope.pagina.tabla = r;}
		);
		$scope.pagina.total = ($scope.pagina.tabla.total /10);

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
		get: get,
		objeto: "usuario"
	};
	get(0);
});
console.log("Admin usuarios cargado");