/* global app */

app.controller('eventos', function ($rootScope, $scope, $routeParams, $conexion, $sesion, $tabla) {
	$scope.user = true;

	var ruta = [
		{url:"/inicio",nombre:"Inicio"},
		{url:"/"+$routeParams.evento,nombre:"Consulta de "+$routeParams.evento}
	];
	var d = (localStorage.getItem("4")==="A" | localStorage.getItem("4")==="P");

	if($routeParams.eventoId !== undefined){//Consultar evento
		ruta[2] = {
			url:"/"+$routeParams.evento+"/"+$routeParams.eventoId,
			nombre:""+$routeParams.nombre
		};
		$scope.taller = false;
		if($routeParams.evento==="talleres"){
			$scope.taller = true;
		}
		$scope.evento = {
			error : "",
			isError : false,
			inscribirse : function (boolInscribeODesiste) {
				$scope.evento.isError = true;
				$scope.evento.error = "Enviando...";
				var request ="";
				var dd= false;
				if(boolInscribeODesiste)
					if($routeParams.evento==="talleres"){
						if(d) dd= confirm("¿Desea inscribirse como profesor?");
						if(dd) request = "regDocenteTaller";
						else request = "regUsuarioTaller";
					} else request = "regUsuarioConv";
				else
					if($routeParams.evento==="talleres") request = "eliminarUsuarioTaller";
					//else if(dd) request = "regUsuarioConv";//NO por que que falta de respeto señores
					else request = "eleminarUsuarioConv";
				$conexion.enviar(
					"usuario",
					{
						tipo: request,
						2: $scope.evento.id,
						1: $sesion.getId
					},
					function(respuesta){
						if(respuesta.data.isError){
							if(respuesta.data.errorDescrip==="Ya estás inscrito")
								$scope.inscrito= true;
							$scope.evento.error=respuesta.data.errorDescrip;
						} else {
							$scope.evento.error = "Listo, estás inscrito!";
							$scope.inscrito= true;
						}
					}
				);
			}
		};
		$conexion.enviar(
			"usuario",
			{
				tipo: ($scope.taller)? "taller":"convocatoria",
				1: $routeParams.eventoId
			},
			function(respuesta){
				if(respuesta.data.isError)
					$scope.evento.error=respuesta.data.errorDescrip;
				else {
					$scope.evento.id = respuesta.data.id;
					$scope.evento.nombre = respuesta.data.nombre;
					$scope.evento.descripcion = respuesta.data.descripcion;
					$scope.evento.fechaInicio = new Date(respuesta.data.fechaInicio);
					$scope.evento.fechaFin = new Date(respuesta.data.fechaFin);
					$scope.evento.costo = respuesta.data.costo;
					$scope.evento.cupos = respuesta.data.cupos;
				}
			}
		);
	} else {//LISTAR EVENTOS
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
		};

		$scope.pagina = {
			titulo: (d)?"Profesor: ":"Estudiante: ",
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
	}
	console.log(
		$rootScope.nav(ruta)
	);
});
console.log("Admin usuarios cargado");