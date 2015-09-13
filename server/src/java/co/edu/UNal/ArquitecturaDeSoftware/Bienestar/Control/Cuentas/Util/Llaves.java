/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.UNal.ArquitecturaDeSoftware.Bienestar.Control.Cuentas.Util;

import java.io.IOException;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;
import java.util.logging.Level;
import java.util.logging.Logger;
import sun.misc.BASE64Decoder;

/**
 *
 * @author dfoxpro
 */
public class Llaves {

	private final KeyPair kp;

	/**
	 * Crea un par de llaves aleatorias
	 *
	 * @throws java.security.NoSuchAlgorithmException
	 */
	public Llaves() throws Exception {
		//super(publicKey, privateKey);
		KeyPairGenerator keygen = KeyPairGenerator.getInstance("RSA");
		keygen.initialize(512);
		kp = keygen.generateKeyPair();
	}

	;
	/**
	 * Create the PublicKey object from the String encoded in Base64.
	 * @param str base 64 encode public key
	 * @return Client public key
	 */
	static PublicKey strToPublic(String str) {
		try {
			X509EncodedKeySpec publicKeySpec = new X509EncodedKeySpec(new BASE64Decoder().decodeBuffer(str));
			return KeyFactory.getInstance("RSA").generatePublic(publicKeySpec);
		} catch (NoSuchAlgorithmException | IOException | InvalidKeySpecException ex) {
			Logger.getLogger(Llaves.class.getName()).log(Level.SEVERE, null, ex);
			return null;
		}
	}

	;

//GETs
	public PublicKey publica() {
		return kp.getPublic();
	}

	public PrivateKey privada() {
		return kp.getPrivate();
	}

	public String publicaToStr() {
		System.out.println("llavePuvlia: "+kp.getPublic().toString());
		return kp.getPublic().toString();
	}
}
