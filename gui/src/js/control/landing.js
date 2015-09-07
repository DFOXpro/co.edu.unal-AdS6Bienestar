app.controller('landing', function ($scope, $conexion) {
	console.log("landing");
	$conexion.iniciar();
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
					4: $scope.is.llavePublica
				},
				function(respuesta){
					console.log("rta: ", respuesta)
					if(respuesta.data.isError)
						$scope.is.error=respuesta.data.errorDescrip
					else {
						localStorage.setItem("4",respuesta.data.llpbSer);
						location.replace("/app/"+respuesta.data.pagina+"?1="+window.btoa($scope.is.cokieHashCode));
					}
				}
			)
		}
	};
});
console.log("landingController cargado")
