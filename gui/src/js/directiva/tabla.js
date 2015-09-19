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
		var r1 = {
			titulo: tabla,//"Usuarios"
			verAccion: accion,//"/usuarios",
			//editarAccion:"/editareventos",
			//eliminarAccion:"/eliminareventos",
			lineas: a,
			total: 73
		};
		$conexion.enviar(
			url,
			{
				tipo: accion,
				//btoa es un cifrador base64
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
					lineas: respuesta.data,
					total: 73
				});
			}
		);

		var a = new Array(tamano);
		for (i = 0; i < tamano; i++)
			a[i] = {titulo: $conexion.strAleatorio(20), id: i};
		return r1;
	};
	return r;
});