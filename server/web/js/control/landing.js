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
				"asd",
				{
					"1": $conexion.cifrar($scope.is.usuario),
					"2": $conexion.cifrar($scope.is.contrasena),
					"3": $conexion.cifrar($scope.is.cokieHashCode),
					"4": $scope.is.llavePublica
				},
				function(){
					$scope.is.error="Listo"
				}
			)
		}
	};
});
console.log("landingController cargado")
