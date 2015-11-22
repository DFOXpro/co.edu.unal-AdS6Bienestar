/* global angular */

var app = angular.module('app', [
	'ngRoute'
]);

app.controller('registro', function ($scope, $conexion) {
	console.log("registro");
	$conexion.iniciar();
	$scope.documentos = [
		{ id: "TI", name: 'Tarjeta de identidad' },
		{ id: "CC", name: 'Cédula' },
		{ id: "PP", name: 'Pasaporte' }
	];
	$scope.roles = [
		{ id: "E", name: 'Estudiante' },
		{ id: "P", name: 'Profesor' }
	];
	$scope.ascii = /^[\x00-\x7F]*$/;
	$scope.exitoso= false;
	
// Create session
	$scope.cs = {
		error : "",
		isError : false,
		submit : function () {
			$scope.cs.tipo = "Iniciar";
			$scope.cs.error = "Enviando...";
			$scope.cs.isError = true;
			$conexion.enviar(
				"registro",
				{
					tipo:"crear",
//btoa es un cifrador base64
					1: $scope.cs.nombre,
					2: $scope.cs.apellido,
					3: $scope.cs.tipoDocumento,
					4: $scope.cs.documento,
					5: $scope.cs.email.toLowerCase(),
					6: $scope.cs.contrasena,
					7: $scope.cs.tipoUsuario
				},
				function(respuesta){
					if(respuesta.data.isError)
						$scope.cs.error=respuesta.data.errorDescrip;
					else {
						$scope.exitoso= true;
					}
				}
			);
		}
	};
});
console.log("registrerController cargado");
