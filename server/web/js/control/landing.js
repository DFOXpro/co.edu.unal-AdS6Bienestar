app.controller('landing', function ($scope, $conexion) {
	console.log("landing");

	$scope.is = {escojeRol : false};
	var irARol = function ($scope){
		var rol = localStorage.getItem("4");
		$scope.is.chc = window.btoa(localStorage.getItem("2"));
		if(rol < 4)
			location.replace("usuario?1="+$scope.is.chc);
		if(rol == 4)
			location.replace("admin?1="+$scope.is.chc);
		else $scope.is.escojeRol = true;
	};

	if($conexion.iniciar()) irARol($scope);
	else{
		//is Iniciar Sesion
		console.log($scope.is.escojeRol);
		$scope.is = {
			escojeRol: false,
			llavePublica: $conexion.getLlavePublica(),
			cokieHashCode: $conexion.getCookieHashCode(),
			usuario: "",
			contrasena: "",
			error : "",
			isError : false,
			submit : function () {
				console.log("is.submit");
				$scope.is.error = "Enviando...";
				$scope.is.isError = true;
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
							irARol($scope);
						}
					}
				)
			}
		}
	}
});
console.log("landingController cargado")
