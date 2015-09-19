/* global app */
app.$inject = ['ngRoute', 'ez.datetime', 'ez.modal', 'ez.dropdown'];

app.controller('eventos', function ($rootScope, $scope, $routeParams, $conexion, $tabla) {
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
		{url:"/"+$routeParams.evento,nombre:"Gestión de "+$routeParams.evento}
	];

	if($routeParams.eventoId !== undefined){
//Crear o editar usuario
		ruta[2] = {
			url:"/"+$routeParams.evento+"/"+$routeParams.eventoId,
			nombre:$routeParams.nombre
		};

		$scope.crear = true;
		var revisarCambios = function (){
			if($scope.crear) return false;
			else if(
				$scope.tal.nombre !== taller.nombre |
				$scope.tal.descripcion !== taller.descripcion |
				$scope.tal.fechaInicio !== taller.fechaInicio |
				$scope.tal.fechaFin !== taller.fechaFin |
				$scope.tal.costo !== taller.costo |
				$scope.tal.cupos !== taller.cupos
			) return false;
			else return true;
		};
		$scope.tal = {
			error : "",
			isError : false,
			submit : function () {
				$scope.tal.isError = true;
				if(!$scope.crear & revisarCambios()){
					$scope.tal.error = "No hay ningún cambio";
				} else {
					$scope.tal.error = "Enviando...";
					$conexion.enviar(
						"admin",
						{
							tipo: $scope.tal.tipo,
							1: $scope.tal.nombre,
							2: $scope.tal.descripcion,
							3: $scope.tal.fechaInicio,
							4: $scope.tal.fechaFin,
							5: $scope.tal.costo,
							6: $scope.tal.cupos
						},
						function(respuesta){
							if(respuesta.data.isError)
								$scope.tal.error=respuesta.data.errorDescrip;
							else {
								$scope.exitoso= true;
							}
						}
					);
				}
			}
		};
		
		if($routeParams.usuarioId > 0){//EDITAR
			var taller = {};

//TEST
taller.nombre="qwer";
taller.descripcion="zxcv";
taller.fechaInicio= new Date(2015, 10, 19, 7, 00);
taller.fechaFin=new Date(2015, 10, 20, 15, 00);
taller.costo="10000";
taller.cupos="30";

$scope.tal.nombre = taller.nombre;
$scope.tal.descripcion = taller.descripcion;
$scope.tal.fechaInicio = taller.fechaInicio;
$scope.tal.fechaFin = taller.fechaFin;
$scope.tal.costo = taller.costo;
$scope.tal.cupos = taller.cupos;
//END TEST

			$scope.crear = false;
			$scope.eliminado = false;
			$scope.tal.tipo = "editarTaller";
			$scope.eliminar = function (){
				var tA = $conexion.strAleatorio(5);
				var tB = prompt("Escriba "+tA+" para confirmar");
				if(tA === tB){
					$conexion.enviar(
						"admin",
						{
							tipo: "eliminarTaller",
							1: $scope.tal.id
						},
						function(respuesta){
							if(respuesta.data.isError)
								$scope.tal.error=respuesta.data.errorDescrip;
							else {
								$scope.exitoso= true;
								$scope.eliminado = true;
							}
						}
					);
				}
			};

			$conexion.enviar(
				"admin",
				{
					tipo: "Taller",
					1: $routeParams.eventoId
				},
				function(respuesta){
					if(respuesta.data.isError)
						$scope.tal.error=respuesta.data.errorDescrip;
					else {
						taller = respuesta.data;
						$scope.tal.nombre = taller.nombre;
						$scope.tal.descripcion = taller.descripcion;
						$scope.tal.fechaInicio = taller.fechaInicio;
						$scope.tal.fechaFin = taller.fechaFin;
						$scope.tal.costo = taller.costo;
						$scope.tal.cupos = taller.cupos;
					}
				}
			);
		} else{//CREAR
			$scope.tal.tipo = "crearTaller";
		};
	} else {
//LISTAR EVENTOS
		$scope.pagina = {
			titulo: "Administrador: ",
			subtitulo: window.atob(localStorage.getItem("6")),
			pos: 0,
			total: 0,
			tabla: {},
			get: get,
			objeto: ($routeParams.evento === "talleres")?"taller":"convocatoria"
		};
		get(0);
	}
	console.log(
		"eventos",
		$routeParams,
		$rootScope.nav(ruta)
	);

//Configuraciones del date picker
	$scope.config1 = {
		format: 'MMMM Do YYYY, h:mma',
		ranges: [
			{
				name: 'Today',
				from: moment().startOf('day'),
				to: moment().endOf('day')
			}, {
				name: 'Yesterday',
				from: moment().subtract(1, 'days').startOf('day'),
				to: moment().subtract(1, 'days').endOf('day')
			}
		]
	};
});
console.log("Admin usuarios cargado");