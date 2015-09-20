/* global app */

app.controller('eventos', function ($rootScope, $scope, $routeParams, $conexion, $sesion, $tabla) {
	$scope.user = true;
	var get = function (diff) {
		$scope.pagina.pos += diff;
		$tabla.get(
			"admin",
			$routeParams.evento,
			$scope.pagina.pos,
			10,
			"/evento/"+$routeParams.evento,
			function (r){$scope.pagina.tabla = r;}
		);
		$scope.pagina.total = ($scope.pagina.tabla.total /10);
	};

	var ruta = [
		{url:"/inicio",nombre:"Inicio"},
		{url:"/"+$routeParams.evento,nombre:"Consulta de "+$routeParams.evento}
	];

	if($routeParams.eventoId !== undefined){
//Crear o editar usuario
		ruta[2] = {
			url:"/"+$routeParams.evento+"/"+$routeParams.eventoId,
			nombre:""+$routeParams.nombre
		};

	}

	console.log(
		"eventos",
		$routeParams,
		$rootScope.nav(ruta)
	);


	$scope.pagina = {
		titulo: "Usuario: ",
		subtitulo: window.atob(localStorage.getItem("6")),
		pos: 0,
		tabla: {},
		get: get,
		objeto: ($routeParams.evento === "talleres")?"taller":"convocatoria"
	};
	get(0);
	$scope.$watch("pagina.tabla.total", function (nuevoValor, viejoValor){
		if(nuevoValor === undefined) $scope.pagina.tabla.total=viejoValor;
		else
		$scope.pagina.total = nuevoValor /10;
	});
	$conexion.enviar(
		"admin",{tipo: ($routeParams.evento === "talleres")?"numTalleres":"numConvocatorias"},
		function(respuesta){
			$scope.pagina.tabla.total = respuesta.data.total;
		}
	);
});
console.log("Usuario cargado");