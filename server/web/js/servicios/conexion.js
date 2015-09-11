/* global cryptico, app */

/**
 Esta es una interfaz a Cryptico y a todos los procesos POST
@param $http
**/
app.factory('$conexion', function ($http) {
//PRIVATE STATIC FINAL
	var ruta = "/network/",
		semilla = "",
		cookieHashCode = "",
		llavePrivada = "",
		llavePublica = "",
		llaveServer = "",
		r = {};

/**
Funcion privada que genera una frase aleatorio de tamaño intSize
@param intSize Number tamaño resultante del arreglo
@return
	String
**/
	var strAleatorio = function (intSize) {
		var str = "",alfabeto = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789 ";
		for (var i=0; i < intSize; i++)
			str += alfabeto.charAt(Math.floor(Math.random() * alfabeto.length));
		console.log("strAleatorio: ", str);
		return str;
	};

//PUBLIC
/**
Funcion inicializa las variables de comunicación
@param
@return
**/
	r.iniciar = function () {
		semilla = localStorage.getItem("1");
		var iniciado = true;
		if (semilla === null | semilla === "") {//undefined para chrome null para FX
			semilla = strAleatorio(10);
			cookieHashCode = strAleatorio(10);
			iniciado = false;
		}
		else cookieHashCode = localStorage.getItem("2");
		//Minimo llaves de 512
		llavePrivada = cryptico.generateRSAKey(semilla, 512);
		llavePublica = cryptico.publicKeyString(llavePrivada);
		console.log("semilla: ", semilla);
		console.log("chc: ", cookieHashCode);
		return iniciado;
	};

/**
 * Persistir Datos de Conexión
 * @param parllaveServer String llave publica rsa del servidor
 **/
	r.pdc = function (parllaveServer){
		llaveServer = parllaveServer;
		console.log("local storage iniciado");
		localStorage.setItem("1", semilla);
		localStorage.setItem("2", cookieHashCode);
		localStorage.setItem("3", llaveServer);
		//Minimo llaves de 512
		llavePrivada = cryptico.generateRSAKey(semilla, 512);
		llavePublica = cryptico.publicKeyString(llavePrivada);
		console.log("llpr: ", llavePrivada);
		console.log("llpb: ", llavePublica);
		console.log("test: ", r.cifrar("perro"));
		//console.log("test1: ", r.decifrar(r.cifrar("perro")));
	};

/**
Funcion que cifra con la llave RSA generada por el cliente
@param strOriginal String
@return	String texto cifrado con rsa
**/
	r.cifrar = function (strOriginal) {
		return cryptico.encrypt(strOriginal, llaveServer).cipher;
	};

/**
Funcion que decifra con la llave RSA del server
@param strCifrado String texto a decifrar
@return String texto original
**/
	r.decifrar = function (strCifrado) {
		return cryptico.decrypt(strCifrado, llavePrivada).plaintext;
	};

/**
Funcion asincrona que hace peticiones rest
@param strRuta String https://dominio/network/<strRuta>
@param objData String parametros del post request
@param callback Function se ejecutará tras cualquier respuesta del server
**/
	r.enviar = function (strRuta, objData, callback) {
		$http({
			method: 'POST',
			headers: {'Content-Type': 'application/x-www-form-urlencoded'},
			url: ruta+strRuta,
			transformRequest: function(obj) {
				var str = [];
				for(var p in obj)
				str.push(encodeURIComponent(p) + '=' + encodeURIComponent(obj[p]));
				return str.join('&');
			},
			data: objData
		}).then(callback);
	};

//GETs
	r.getLlavePublica = function () {
		return llavePublica;
	};
	r.getCookieHashCode = function () {
		return cookieHashCode;
	};

	return r;
});
console.log("conexionFactory cargado");
