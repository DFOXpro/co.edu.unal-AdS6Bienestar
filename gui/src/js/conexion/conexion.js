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
		console.log("strAleatorio: ", str);
		return str;
	};

//PUBLIC
/**
Funcion inicializa las variables de comunicación
@parameters
@return
**/
	r.iniciar = function () {
		llaveServer = localStorage.getItem("3");
		if (llaveServer === null) {//undefined para chrome null para FX
			$http.get("/network/1").then(function(response){
				console.log("Serverkey: ",response);
			});
			localStorage.setItem("3", llaveServer);
		}
		var semilla = localStorage.getItem("1");
		if (semilla === null) {//undefined para chrome null para FX
			semilla = strAleatorio(10);
			cookieHashCode = strAleatorio(10);
			console.log("local storage iniciado");
			localStorage.setItem("1", semilla);
			localStorage.setItem("2", cookieHashCode);

		}
		else cookieHashCode = localStorage.getItem("2");
		//Minimo llaves de 512
		llavePrivada = cryptico.generateRSAKey(semilla, 512);
		llavePublica = cryptico.publicKeyString(llavePrivada);
		console.log("semilla: ", semilla);
		console.log("llpr: ", llavePrivada);
		console.log("llpb: ", llavePublica);
		console.log("chc: ", cookieHashCode);
		console.log("test: ", r.cifrar("perro"));
		console.log("test1: ", r.decifrar(r.cifrar("perro")));
	};

/**
Funcion que cifra con la llave RSA generada por el cliente
@parameters
	String texto original
@return
	String texto cifrado
**/
	r.cifrar = function (strOriginal) {
		//console.log("cifrar con: "+llavePublica)
		return cryptico.encrypt(strOriginal, llavePublica).cipher
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
		return cryptico.decrypt(strCifrado, llavePrivada).plaintext;
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
		$http({
			method: 'POST',
			//window.location.host por que no vamos a usar un dominio especifico
			url: "/network/"+strRuta,
			//headers: {
				//'Cookie': cookieHashCode
			//},
			data: objData
		}).then(callback);
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
