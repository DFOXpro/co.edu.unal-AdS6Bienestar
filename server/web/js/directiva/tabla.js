/* global app */

app.directive('btabla', function () {
	return {
		restrict: 'E',
		scope: {
			tabla: '=info'
		},
		templateUrl: 'btabla-tmplt'
	};
});

app.filter('ceil', function() {
  return function(input) {
    input = input || 0;
    return Math.ceil(input);
  };
});

app.factory('$tabla', function ($conexion) {
	var r = {};
	r.get = function (url, tabla, pos, tamano, accion){
		var a = new Array(tamano);
		for (i = 0; i < tamano; i++)
			a[i] = {titulo: $conexion.strAleatorio(20), id: i};
		var r1 = {
			titulo: tabla,//"Usuarios"
			verAccion: accion,//"/usuarios",
			//editarAccion:"/editareventos",
			//eliminarAccion:"/eliminareventos",
			lineas: a,
			total: 73
		};
		return r1;
	};
	return r;
});