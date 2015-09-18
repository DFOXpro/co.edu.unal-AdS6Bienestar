/* global app */

app.controller('usuarios', function ($rootScope, $routeParams, $scope, $conexion, $sesion, $tabla) {
	var get = function (diff) {
		$scope.pagina.pos += diff;
		$tabla.get(
			"admin",
			"Usuarios",
			0,
			10,
			"usuarios",
			function (r){$scope.pagina.tabla = r;}
		);
		$scope.pagina.total = ($scope.pagina.tabla.total /10);
	};

	var ruta = [
		{url:"/inicio",nombre:"Inicio"},
		{url:"/usuarios",nombre:"Gestión de usuarios."}
	];
	if($routeParams.usuarioId !== undefined) {
		ruta[2] = {
			url:"/"+$routeParams.evento+"/"+$routeParams.eventoId,
			nombre:""+$routeParams.nombre
		};
		$scope.documentos = [
			{ id: "TI", name: 'Tarjeta de identidad' },
			{ id: "CC", name: 'Cédula' },
			{ id: "PP", name: 'Pasaporte' }
		];
		$scope.roles = [
			{ id: "E", name: 'Estudiante' },
			{ id: "P", name: 'Profesor' },
			{ id: "A", name: 'Administrador' }
		];
	} else {
		$scope.pagina = {
			titulo: "Administrador: ",
			subtitulo: window.atob(localStorage.getItem("6")),
			pos: 0,
			total: 0,
			tabla: {},
			get: get,
			objeto: "usuario"
		};
		get(0);
	}
	console.log(
		$routeParams,
		$rootScope.nav(ruta)
	);
});
console.log("Admin usuarios cargado");