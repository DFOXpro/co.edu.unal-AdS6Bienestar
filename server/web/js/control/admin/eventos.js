/* global app, moment */
app.$inject = ['ngRoute', 'ez.datetime', 'ez.modal', 'ez.dropdown'];

app.controller('eventos', function ($rootScope, $scope, $routeParams, $conexion, $tabla) {
	var ruta = [
		{url:"/inicio",nombre:"Inicio"},
		{url:"/evento/"+$routeParams.evento,nombre:"Gestión de "+$routeParams.evento}
	];
	$scope.taller = $routeParams.evento === "talleres";
	if($routeParams.eventoId !== undefined){
//Crear o editar usuario
		ruta[2] = {
			url:"/"+$routeParams.evento+"/"+$routeParams.eventoId,
			nombre:$routeParams.nombre
		};

		$scope.crear = true;
		var revisarCambios = function (){
			if($scope.crear) return false;
			if(
				$scope.evento.nombre !== evento1.nombre |
				$scope.evento.descripcion !== evento1.descripcion |
				$scope.evento.fechaInicio !== evento1.fechaInicio |
				$scope.evento.fechaFin !== evento1.fechaFin |
				$scope.evento.costo !== evento1.costo |
				$scope.evento.cupos !== evento1.cupos
			) return false;
			else return true;
		};
		$scope.evento = {
			error : "",
			isError : false,
			submit : function () {
				$scope.evento.isError = true;
				if(!$scope.crear & revisarCambios()){
					$scope.evento.error = "No hay ningún cambio";
				} else {
					$scope.evento.error = "Enviando...";
					var castFecha = function (f){
						console.log("CastFecha: ",f);
						return f.getFullYear()+"/"+f.getMonth()+"/"+f.getDay();
					};
					//2015/10/20
					$conexion.enviar(
						"admin",
						{
							tipo: $scope.evento.tipo,
							0: $scope.evento.id,
							1: $scope.evento.nombre,
							2: $scope.evento.descripcion,
							3: $scope.evento.fechaInicio,
							4: $scope.evento.fechaFin,
							5: $scope.evento.costo,
							6: $scope.evento.cupos
						},
						function(respuesta){
							if(respuesta.data.isError)
								$scope.evento.error=respuesta.data.errorDescrip;
							else {
								$scope.exitoso= true;
							}
						}
					);
				}
			}
		};

		if($routeParams.eventoId > 0){//EDITAR
			var evento1 = {};
			$scope.crear = false;
			$scope.eliminado = false;
			$scope.evento.tipo = "editar"+$routeParams.evento;
			$scope.eliminar = function (){
				var tA = $conexion.strAleatorio(5);
				var tB = prompt("Escriba "+tA+" para confirmar");
				if(tA === tB){
					$conexion.enviar(
						"admin",
						{
							tipo: "eliminar"+$routeParams.evento,
							1: $scope.evento.id
						},
						function(respuesta){
							if(respuesta.data.isError)
								$scope.evento.error=respuesta.data.errorDescrip;
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
					tipo: ($scope.taller)? "taller":"convocatoria",
					1: $routeParams.eventoId
				},
				function(respuesta){
					if(respuesta.data.isError)
						$scope.evento.error=respuesta.data.errorDescrip;
					else {
						evento1 = respuesta.data;
						$scope.evento.id = evento1.id;
						$scope.evento.nombre = evento1.nombre;
						$scope.evento.descripcion = evento1.descripcion;
						$scope.evento.fechaInicio = new Date(evento1.fechaInicio);
						$scope.evento.fechaFin = new Date(evento1.fechaFin);
						$scope.evento.costo = evento1.costo;
						$scope.evento.cupos = evento1.cupos;
					}
				}
			);
			$scope.inscritos ={
				url: "admin",
				titulo: "Usuarios inscritos",
				pos: 0,
				tamano: 10,
				tipo: ($scope.taller)? "usuariosEnTaller":"usuariosEnConvocatoria",
				tituloAccion2: "Expulsar",
				id:$routeParams.eventoId,
				callback : function (r){
					$scope.inscritos.tabla = r;
					$scope.inscritos.getTamano();
				},
				eliminar: function(usuarioId, eventoId){
					$conexion.enviar(
						"admin",
						{
							tipo: ($scope.taller)? "eliminarUsuarioTaller":"eliminarUsuarioConv",
							1: usuarioId,
							2: eventoId
						},
						function(respuesta){
							$tabla.get(
								$scope.inscritos.url,
								$scope.inscritos.titulo,
								$scope.inscritos.pos,
								$scope.inscritos.tamano,
								$scope.inscritos.tipo,
								$scope.inscritos.callback,
								$scope.inscritos.eliminar,
								$scope.inscritos.tituloAccion2,
								$scope.inscritos.id
							);
						}
					);
				},
				getTamano: function(){
					$conexion.enviar(
						$scope.inscritos.url,
						{
							tipo: ($scope.taller)? "inscritosTaller":"inscritosConvocatoria",
							1: $scope.inscritos.id
						},
						function(respuesta){
							$scope.inscritos.total=respuesta.data.inscritos;
						}
					);
				}
			};
			$tabla.get(
				$scope.inscritos.url,
				$scope.inscritos.titulo,
				$scope.inscritos.pos,
				$scope.inscritos.tamano,
				$scope.inscritos.tipo,
				$scope.inscritos.callback,
				$scope.inscritos.eliminar,
				$scope.inscritos.tituloAccion2,
				$scope.inscritos.id
			);
		} else{//CREAR
			$scope.evento.tipo = ($scope.taller)? "crearTaller":"crearConvocatoria";
		};
	} else {
//LISTAR EVENTOS
		var get = function (diff) {
			$scope.pagina.pos += diff;
			$tabla.get(
				"admin",
				$routeParams.evento,
				($scope.pagina.pos < 2)?0:$scope.pagina.pos,
				10,
				"/evento/"+$routeParams.evento,
				function (r){$scope.pagina.tabla = r;}
			);
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
		$scope.pagina.tabla.total = 0;
		$conexion.enviar(
			"admin",{tipo: "numConvocatorias"},
			function(respuesta){
				//$scope.pagina.tabla.total = respuesta.data.total//NO SIRVE no digiere el cambio
				$scope.$watch("pagina.tabla.total", function (nuevoValor){
					$scope.pagina.total = $scope.pagina.tabla.total /10;
					console.log("tic");
				});
				console.log($scope.pagina.tabla.total = respuesta.data.total);
				//Valimos barrigas con este scope todos los metodos que se que sirven no sirven aqui T.T
				//$scope.$apply();// JAJAJA puto angular
				//$rootScope.$digest()
			}
		);
	}
	console.log(
		$rootScope.nav(ruta)
	);

//Configuraciones del date picker
	var confGeneral ={
		viewFormat: 'DD MMMM YYYY',
		timepickerEnabled: false,
		modelFormat:'YYYY/MM/DD',
		headingText: 'Escoje una fecha',
		okBtnText: 'Aceptar',
		cancelBtnText: 'Cancelar'
	};
	$scope.hoy = new Date();
	$scope.configConv = confGeneral;
	$scope.configTaller = confGeneral;
});
console.log("Admin usuarios cargado");