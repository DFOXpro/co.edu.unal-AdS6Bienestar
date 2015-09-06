/**
 Esta es una interfaz a Cryptico y a todos los procesos POST
**/
app.factory('$conexion', function ($http) {
//PRIVATE STATIC FINAL
	var ruta = "network/autenticacion",
		cookieHashCode = "",
		llavePrivada = "",
		llavePublica = "",
		llaveServer = "",
		r = {};

/**
Funcion privada que genera una frase aleatorio de tamaño intSize
@parameters
	Number intSize
@return
	String
**/
	var strAleatorio = function (intSize) {
		var str = "",alfabeto = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789 ";
		for (var i=0; i < intSize; i++)
			str += alfabeto.charAt(Math.floor(Math.random() * alfabeto.length));
		console.log("str: ", str);
		return str;
	};

//PUBLIC
/**
Funcion inicializa las variables de comunicación
@parameters
@return
**/
	r.iniciar = function () {
		llavePrivada = localStorage.getItem("1");
		if (llavePrivada === null) {
			llavePrivada = cryptico.generateRSAKey(strAleatorio(20), 128);
			console.log("llp generada");
			cookieHashCode = strAleatorio(10);
		}
		else cookieHashCode = localStorage.getItem("2");
		llavePublica = cryptico.publicKeyString(llavePrivada);
		localStorage.setItem("1", llavePrivada);
		localStorage.setItem("llpb", llavePublica);
		localStorage.setItem("2", cookieHashCode);
		console.log("llpr: ", llavePrivada);
		console.log("llpb: ", llavePublica);
		console.log("chc: ", cookieHashCode);

	};

/**
Funcion que cifra con la llave RSA generada por el cliente
@parameters
	String texto original
@return
	String texto cifrado
**/
	r.cifrar = function (strOriginal) {
		return cryptico.encrypt(strToCrypt, llavePublica)
		//La documentación pide la llave publica para cifrar ?
	}


/**
Funcion que decifra con la llave RSA del server
@parameters
	String texto a decifrar
@return
	String texto original
**/
	r.decifrar = function (strCifrado) {
		return cryptico.decrypt(strCifrado, llaveServer);
		//0.o la llave privada para decifrar?
	}

/**
Funcion asincrona que hace peticiones rest
@parameters
	String https://dominio/network/<strRuta>
	String parametros del post request
	Function se ejecutará tras cualquier respuesta del server
@return
**/
	r.enviar = function (strRuta, objData, callback) {
		var req = {
		method: 'POST',
		//window.location.host por que no vamos a usar un dominio especifico
		url: window.location.host+"/network/"+strRuta,
		headers: {
			'Cookie': cookieHashCode
		},
		data: objData
}

$http(req).then(function(){...}, function(){...});
	}

//GETs
	r.getLlavePublica = function () {
		return llavePublica
	}
	r.getCookieHashCode = function () {
		return cookieHashCode
	}

	return r
});
console.log("conexionFactory cargado")
