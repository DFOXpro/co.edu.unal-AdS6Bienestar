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

app.filter('ceil', function () {
	return function (input) {
		input = input || 0;
		return Math.ceil(input);
	};
});

app.factory('$tabla', function ($conexion) {
	var r = {};
	r.get = function (url, tabla, pos, tamano, accion,callback){
		$conexion.enviar(
			url,
			{
				tipo: accion,
				1: pos,
				2: tamano
			},
			function (respuesta) {
				if (respuesta.data.isError)
					console.log("Error:", respuesta.data.errorDescrip) ;
				//else callback(respuesta.data);
				else callback({
					titulo: tabla,//"Usuarios"
					verAccion: accion,//"/usuarios",
					//editarAccion:"/editareventos",
					//eliminarAccion:"/eliminareventos",
					lineas: respuesta.data
				});
			}
		);
	};
	return r;
});