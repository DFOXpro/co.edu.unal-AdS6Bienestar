/* global cryptico, app, encodeURIComponent, Infinity */

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
		var str = "", alfabeto = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
		for (var i = 0; i < intSize; i++)
			str += alfabeto.charAt(Math.floor(Math.random() * alfabeto.length));
		return str;
	};

	//<editor-fold defaultstate="collapsed" desc="Librería cookies">
	/**
	 *
	 *   :: cookies.js ::
	 *
	 *   A complete cookies reader/writer framework with full unicode support.
	 *
	 *   Revision #1 - September 4, 2014
	 *
	 *   https://developer.mozilla.org/en-US/docs/Web/API/document.cookie
	 *   https://developer.mozilla.org/User:fusionchess
	 *
	 *   This framework is released under the GNU Public License, version 3 or later.
	 *   http://www.gnu.org/licenses/gpl-3.0-standalone.html
	 *
	 *   Syntaxes:
	 *
	 *   * docCookies.setItem(name, value[, end[, path[, domain[, secure]]]])
	 *   * docCookies.getItem(name)
	 *   * docCookies.removeItem(name[, path[, domain]])
	 *   * docCookies.hasItem(name)
	 *   * docCookies.keys()
	 *
	 **/
	var docCookies = {
		getItem: function (sKey) {
			if (!sKey) {
				return null;
			}
			return decodeURIComponent(document.cookie.replace(new RegExp("(?:(?:^|.*;)\\s*" + encodeURIComponent(sKey).replace(/[\-\.\+\*]/g, "\\$&") + "\\s*\\=\\s*([^;]*).*$)|^.*$"), "$1")) || null;
		},
		setItem: function (sKey, sValue, vEnd, sPath, sDomain, bSecure) {
			if (!sKey || /^(?:expires|max\-age|path|domain|secure)$/i.test(sKey)) {
				return false;
			}
			var sExpires = "";
			if (vEnd) {
				switch (vEnd.constructor) {
					case Number:
						sExpires = vEnd === Infinity ? "; expires=Fri, 31 Dec 9999 23:59:59 GMT" : "; max-age=" + vEnd;
						break;
					case String:
						sExpires = "; expires=" + vEnd;
						break;
					case Date:
						sExpires = "; expires=" + vEnd.toUTCString();
						break;
				}
			}
			document.cookie = encodeURIComponent(sKey) + "=" + encodeURIComponent(sValue) + sExpires + (sDomain ? "; domain=" + sDomain : "") + (sPath ? "; path=" + sPath : "") + (bSecure ? "; secure" : "");
			return true;
		},
		removeItem: function (sKey, sPath, sDomain) {
			if (!this.hasItem(sKey)) {
				return false;
			}
			document.cookie = encodeURIComponent(sKey) + "=; expires=Thu, 01 Jan 1970 00:00:00 GMT" + (sDomain ? "; domain=" + sDomain : "") + (sPath ? "; path=" + sPath : "");
			return true;
		},
		hasItem: function (sKey) {
			if (!sKey) {
				return false;
			}
			return (new RegExp("(?:^|;\\s*)" + encodeURIComponent(sKey).replace(/[\-\.\+\*]/g, "\\$&") + "\\s*\\=")).test(document.cookie);
		},
		keys: function () {
			var aKeys = document.cookie.replace(/((?:^|\s*;)[^\=]+)(?=;|$)|^\s*|\s*(?:\=[^;]*)?(?:\1|$)/g, "").split(/\s*(?:\=[^;]*)?;\s*/);
			for (var nLen = aKeys.length, nIdx = 0; nIdx < nLen; nIdx++) {
				aKeys[nIdx] = decodeURIComponent(aKeys[nIdx]);
			}
			return aKeys;
		}
	};
	//</editor-fold>


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
			this.terminar();
			semilla = strAleatorio(10);
			cookieHashCode = strAleatorio(10);
			iniciado = false;
		}
		else
			cookieHashCode = docCookies.getItem("2");
		//Minimo llaves de 512
		llavePrivada = cryptico.generateRSAKey(semilla, 512);
		llavePublica = cryptico.publicKeyString(llavePrivada);
		console.log("semilla: ", semilla);
		console.log("chc: ", cookieHashCode);
		return iniciado;
	};

	r.terminar = function () {
		docCookies.removeItem("2");
		localStorage.clear();
	}
	/**
	 * Persistir Datos de Conexión
	 * @param parllaveServer String llave publica rsa del servidor
	 **/
	r.pdc = function (parllaveServer) {
		llaveServer = parllaveServer;
		console.log("local storage iniciado");
		localStorage.setItem("1", semilla);
		docCookies.setItem("2", cookieHashCode, 2592e3, location.host);//El número significa que vencerá en un mes
		localStorage.setItem("3", llaveServer);
		//Minimo llaves de 512
		llavePrivada = cryptico.generateRSAKey(semilla, 512);
		llavePublica = cryptico.publicKeyString(llavePrivada);
		console.log("llpr: ", llavePrivada);
		console.log("llpb: ", llavePublica);
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
			url: ruta + strRuta,
			transformRequest: function (obj) {
				var str = [];
				for (var p in obj)
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

	//Para test
	r.strAleatorio = strAleatorio;
	return r;
});
console.log("conexionFactory cargado");
