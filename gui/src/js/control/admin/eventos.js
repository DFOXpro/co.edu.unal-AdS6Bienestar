/* global app */

app.controller('eventos', function ($rootScope, $scope,$route, $routeParams, $conexion, $sesion, $tabla) {
	var ruta = [
			{url:"/inicio",nombre:"Inicio"},
			{url:"/"+$routeParams.evento,nombre:"Gestión de "+$routeParams.evento}
		];
	if($routeParams.eventoId != undefined)
		ruta[3] = {
			url:"/"+$routeParams.evento+"/"+$routeParams.eventoId,
			nombre:""+$routeParams.evento
		};

	console.log(
		"eventos",
		$routeParams,
		$rootScope.nav(ruta)
	);
	var get = function (diff) {
		$scope.pagina.pos += diff;
		$scope.pagina.tabla = $tabla.get(
			"admin",
			$routeParams.evento,
			$scope.pagina.pos,
			10,
			"/evento/"+$routeParams.evento,
			function (r){$scope.pagina.tabla = r;}
		);
		$scope.pagina.total = ($scope.pagina.tabla.total /10);
	};

	$scope.pagina = {
		titulo: "Administrador: ",
		subtitulo: window.atob(localStorage.getItem("6")),
		pos: 1,
		total: 0,
		tabla: {},
		get: get,
		objeto: ($routeParams.evento === "talleres")?"taller":"convocatoria"
	};
	get(0);
});
console.log("Admin usuarios cargado");