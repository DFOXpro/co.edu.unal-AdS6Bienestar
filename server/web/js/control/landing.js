app.controller('landing', function ($scope, $conexion) {
	console.log("landing");
	$conexion.iniciar();
	$scope.formData = {
		tipo: "",
		llavePublica: $conexion.getLlavePublica(),
		cokieHashCode: $conexion.getCookieHashCode(),
		usuario: "Usuario",
		contrasena: "contraseña"
	};
	$scope.iniciarSesion = function () {
		console.log("iniciarSesion");
		$scope.formData.tipo = "iniciar";
		console.log("typ ", $scope.formData.tipo);
		console.log("llp ", $scope.formData.llavePublica);
		console.log("chc ", $scope.formData.cokieHashCode);
		console.log("usr ", $scope.formData.usuario);
		console.log("ctñ ", $scope.formData.contrasena);
	};
});
