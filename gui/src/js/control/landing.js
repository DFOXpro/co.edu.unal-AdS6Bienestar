app.controller('landing', function ($scope, $conexion) {
	console.log("landing");

	var irAROL = function (is){
		location.replace(localStorage.getItem("4")+"?1="+window.btoa(localStorage.getItem("2")));
	};

	if($conexion.iniciar()) irAROL($scope);
	else{
		//is Iniciar Sesion
		$scope.is = {
			tipo: "",
			llavePublica: $conexion.getLlavePublica(),
			cokieHashCode: $conexion.getCookieHashCode(),
			usuario: "",
			contrasena: "",
			error : "",
			isError : false,
			submit : function () {
				console.log("is.submit");
				$scope.is.tipo = "iniciar";
				$scope.is.error = "Enviando...";
				$scope.is.isError = true;
				console.log("typ ", $scope.is.tipo);
				console.log("llp ", $scope.is.llavePublica);
				console.log("chc ", $scope.is.cokieHashCode);
				console.log("usr ", $scope.is.usuario);
				console.log("ctñ ", $scope.is.contrasena);
				$conexion.enviar(
					"sesion",
					{
						tipo:"iniciar",
						//btoa es un cifrador base64
						1: window.btoa($scope.is.usuario),
						2: window.btoa($scope.is.contrasena),
						3: window.btoa($scope.is.cokieHashCode),
					},
					function(respuesta){
						console.log("rta: ", respuesta);
						if(respuesta.data.isError)
							$scope.is.error=respuesta.data.errorDescrip;
						else {
							$conexion.enviar(
								"sesion",{
									tipo:"cc",//ConfirmarCifrado
									1: $scope.is.llavePublica,
									2: $scope.is.cokieHashCode
								}
							);
							$conexion.pdc(respuesta.data.llpbSer);
							localStorage.setItem("4",respuesta.data.pagina);
							irAROL($scope);
						}
					}
				)
			}
		}
	}
});
console.log("landingController cargado")
