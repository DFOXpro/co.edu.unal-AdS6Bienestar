/* global app */

app.controller('inicio', function ($rootScope, $scope, $conexion, $sesion, $tabla) {
	console.log("inicio", $rootScope.nav([
		{url:"/inicio",nombre:"Inicio"}
	]));
	var getTalleresRecientes = function () {
		//TEST
		r = {
			titulo: "Talleres recientes",
			verAccion: "/eventos",
			//editarAccion:"/editareventos",
			//eliminarAccion:"/eliminareventos",
			lineas: [
				{titulo: "Quantica para industriales", id: 1},
				{titulo: "Quantica for dummies", id: 2},
				{titulo: "Quantica aplicada para ingenieros", id: 3},
				{titulo: "Quantica avanzada para asiaticos", id: 4}
			]
		};
		//END TEST
		return r;
	};

//@TODO gets del cliente admin
	var getCursosRecientes = getTalleresRecientes;
	var getConvocatoriasRecientes = getTalleresRecientes;


	$scope.pagina = {
		titulo: "Administrador: ",
		subtitulo: window.atob(localStorage.getItem("6")),
		talleresRecientes: getTalleresRecientes(),
		convocatoriasRecientes: $tabla.get("admin", "Convocatorias recientes", 0, 4, "/convocatorias"),
		cursosRecientes: $tabla.get("admin", "Cursos recientes", 0, 4, "/cursos"),
		usuariosRecientes: $tabla.get("admin", "Usuarios recientes", 0, 4, "/usuarios")
	};
});
console.log("Admin inicio cargado");