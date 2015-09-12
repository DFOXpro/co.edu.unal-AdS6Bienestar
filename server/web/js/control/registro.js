app.controller('registro', function ($scope, $conexion) {
	console.log("registro");
	$conexion.iniciar();
// Create session
	$scope.cs = {
		error : "",
		isError : false,
		submit : function () {
			console.log("cs.submit");
			$scope.cs.tipo = "Iniciar";
			$scope.cs.error = "Enviando...";
			$scope.cs.isError = true;
			console.log("nom ", $scope.cs.nombre);
			console.log("app ", $scope.cs.apellido);
			console.log("tid ", $scope.cs.tipoDocumento);
			console.log("doc ", $scope.cs.documento);
			console.log("ema ", $scope.cs.email);
			console.log("cnt ", $scope.cs.contrasena);
			console.log("tiu ", $scope.cs.tipoUsuario);
			$conexion.enviar(
				"registro",
				{
					tipo:"crear",
//btoa es un cifrador base64
					1: $scope.cs.nombre,
					2: $scope.cs.apellido,
					3: $scope.cs.tipoDocumento,
					4: $scope.cs.documento,
					5: $scope.cs.email,
					6: $scope.cs.contrasena,
					7: $scope.cs.tipoUsuario
				},
				function(respuesta){
					console.log("rta: ", respuesta)
					if(respuesta.data.isError)
						$scope.cs.error=respuesta.data.errorDescrip
					else {
						location.replace("network/"+respuesta.data.pagina);
					}
				}
			)
		}
	};
});
console.log("registrerController cargado")
