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
	r.get = function (
		url,
		tabla,
		pos,
		tamano,
		accion,
		callback,
		accion2Fun,
		accion2Texto,
		accion2id
	){
		$conexion.enviar(
			url,
			{
				tipo: accion,
				1: pos,
				2: tamano,
				3: accion2id
			},
			function (respuesta) {
				if (respuesta.data.isError)
					console.log("Error:", respuesta.data.errorDescrip) ;
				else callback({
					titulo: tabla,//"Usuarios"
					verAccion: accion,//"/usuarios",
					//editarAccion:"/editareventos",
					//eliminarAccion:"/eliminareventos",
					lineas: respuesta.data,
					accion2: (accion2Fun)?{
						exist : true,
						fun : accion2Fun,
						texto : accion2Texto,
						id : accion2id
					}:{exist : false}
				});
			}
		);
	};
	return r;
});