/* global app */

app.controller('usuarios', function ($rootScope, $routeParams, $scope, $conexion, $tabla) {
	var ruta = [
		{url:"/inicio",nombre:"Inicio"},
		{url:"/usuarios",nombre:"Gestión de usuarios"}
	];

	if($routeParams.usuarioId !== undefined) {
//Crear o editar usuario
		ruta[2] = {
			url:"/"+$routeParams.evento+"/"+$routeParams.eventoId,
			nombre:$routeParams.nombre
		};

		$scope.documentos = [
			{ id: "CC", name: 'Cédula' },
			{ id: "TI", name: 'Tarjeta de identidad' },
			{ id: "PP", name: 'Pasaporte' }
		];
		$scope.roles = [
			{ id: "E", name: 'Estudiante' },
			{ id: "P", name: 'Profesor' },
			{ id: "A", name: 'Administrador' }
		];
		$scope.crear = true;
		var revisarCambios = function (){
			if($scope.crear) return false;
			else if(
				$scope.cu.nombre !== usuario.nombre |
				$scope.cu.apellido !== usuario.apellido |
				$scope.cu.tipoDocumento !== usuario.tipoDocumento |
				$scope.cu.documento !== usuario.documento |
				$scope.cu.email.toLowerCase() !== usuario.email |
				$scope.cu.contrasena !== usuario.contrasena |
				$scope.cu.rol !== usuario.rol
			) return false;
			else return true;
		};
		$scope.cu = {
			error : "",
			isError : false,
			submit : function () {
				$scope.cu.isError = true;
				if(!$scope.crear & revisarCambios()){
					$scope.cu.error = "No hay ningún cambio";
				} else {
					$scope.cu.error = "Enviando...";
					$conexion.enviar(
						"admin",
						{
							tipo: $scope.cu.tipo,
							0: $scope.cu.id,
							1: $scope.cu.nombre,
							2: $scope.cu.apellido,
							3: $scope.cu.tipoDocumento,
							4: $scope.cu.documento,
							5: $scope.cu.email.toLowerCase(),
							6: $scope.cu.contrasena,
							7: $scope.cu.rol
						},
						function(respuesta){
							if(respuesta.data.isError)
								$scope.cu.error=respuesta.data.errorDescrip;
							else {
								$scope.exitoso= true;
							}
						}
					);
				}
			}
		};
		if($routeParams.usuarioId > 0){//EDITAR
			var usuario = {};
			$scope.crear = false;
			$scope.eliminado = false;
			$scope.cu.tipo = "editarUsuario";
			$scope.eliminar = function (){
				var tA = $conexion.strAleatorio(5);
				var tB = prompt("Escriba "+tA+" para confirmar");
				if(tA === tB){
					$conexion.enviar(
						"admin",
						{
							tipo: "eliminarUsuario",
							1: $scope.cu.id
						},
						function(respuesta){
							if(respuesta.data.isError)
								$scope.cu.error=respuesta.data.errorDescrip;
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
					tipo: "Usuario",
			//btoa es un cifrador base64
					1: $routeParams.usuarioId
				},
				function(respuesta){
					if(respuesta.data.isError)
						$scope.cu.error=respuesta.data.errorDescrip;
					else {
						usuario = respuesta.data;
						$scope.cu.id = usuario.id;
						$scope.cu.nombre = usuario.nombre;
						$scope.cu.apellido = usuario.apellido;
						$scope.cu.tipoDocumento = usuario.tipoDocumento;
						$scope.cu.documento = usuario.documento;
						$scope.cu.email = usuario.email;
						$scope.cu.contrasena = usuario.contrasena;
						$scope.cu.rol = usuario.rol;
						$scope.cu.contrasena_2 = respuesta.data.contrasena;
						console.log("cu",$scope.cu);
					}
				}
			);
		} else{//CREAR
			$scope.cu.tipo = "crearUsuario";
		};
	} else {
//LISTAR USUARIOS
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
		};

		$scope.pagina = {
			titulo: "Administrador: ",
			subtitulo: window.atob(localStorage.getItem("6")),
			pos: 0,
			tabla: {},
			get: get,
			objeto: "usuario"
		};
		get(0);
		$scope.$watch("pagina.tabla.total", function (nuevoValor, viejoValor){
			if(nuevoValor === undefined) $scope.pagina.tabla.total=viejoValor;
			else
			$scope.pagina.total = nuevoValor /10;
		});
		$conexion.enviar(
			"admin",{tipo: "numUsuarios"},
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