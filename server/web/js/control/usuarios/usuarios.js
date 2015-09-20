/* global app */

app.controller('usuarios', function ($rootScope, $routeParams, $scope, $conexion, $sesion, $tabla) {
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
	};

	var ruta = [
		{url:"/inicio",nombre:"Inicio"},
		{url:"/usuarios",nombre:"Gestión de usuarios."}
	];

	if($routeParams.usuarioId !== undefined) {
//Crear o editar usuario
		ruta[2] = {
			url:"/"+$routeParams.evento+"/"+$routeParams.eventoId,
			nombre:""+$routeParams.nombre
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
				$scope.cu.tipoUsuario !== usuario.tipoUsuario
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
	//btoa es un cifrador base64
							1: $scope.cu.nombre,
							2: $scope.cu.apellido,
							3: $scope.cu.tipoDocumento,
							4: $scope.cu.documento,
							5: $scope.cu.email.toLowerCase(),
							6: $scope.cu.contrasena,
							7: $scope.cu.tipoUsuario
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
//TEST
//usuario.nombre="qwer";
//usuario.apellido="zxcv";
//usuario.tipoDocumento="TI";
//usuario.documento=1234243;
//usuario.email="asdf@qwer";
//usuario.contrasena="poiuy";
//usuario.tipoUsuario="A";
//
//$scope.cu.nombre = usuario.nombre;
//$scope.cu.apellido = usuario.apellido;
//$scope.cu.tipoDocumento = usuario.tipoDocumento;
//$scope.cu.documento = usuario.documento;
//$scope.cu.email = usuario.email;
//$scope.cu.contrasena = usuario.contrasena;
//$scope.cu.tipoUsuario = usuario.tipoUsuario;
//$scope.cu.contrasena_2 = usuario.contrasena;
//END TEST

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
	//btoa es un cifrador base64
							1: $scope.cu.documento,
							2: $scope.cu.email.toLowerCase()
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
						$scope.cu.nombre = respuesta.data.nombre;
						$scope.cu.apellido = respuesta.data.apellido;
						$scope.cu.tipoDocumento = respuesta.data.tipoDocumento;
						$scope.cu.documento = respuesta.data.documento;
						$scope.cu.email = respuesta.data.email;
						$scope.cu.contrasena = respuesta.data.contrasena;
						$scope.cu.tipoUsuario = respuesta.data.tipoUsuario;
						$scope.cu.contrasena_2 = respuesta.data.contrasena;
					}
				}
			);
		} else{//CREAR
			$scope.cu.tipo = "crearUsuario";
		};
	} else {
//LISTAR USUARIOS
		$scope.pagina = {
			titulo: "Usuario: ",
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
		$rootScope.nav(ruta)
	);
});
console.log("Admin usuarios cargado");