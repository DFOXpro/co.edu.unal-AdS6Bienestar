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
import java.security.spec.X509EncodedKeySpec;
import java.util.logging.Level;
import java.util.logging.Logger;
import sun.misc.BASE64Decoder;

/**
 *
 * @author dfoxpro
 */
public class Llaves{
    private final KeyPair kp;
    /**
     * Crea un par de llaves aleatorias
     * @throws java.security.NoSuchAlgorithmException
     */
    public Llaves() throws Exception {
        //super(publicKey, privateKey);
        KeyPairGenerator keygen = KeyPairGenerator.getInstance("RSA");
        keygen.initialize(512);
        kp = keygen.generateKeyPair();
    };

    static PublicKey strToPublic(String str){
        try {
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");

            //Create the PublicKey object from the String encoded in Base64.
            X509EncodedKeySpec publicKeySpec = new X509EncodedKeySpec(new BASE64Decoder().decodeBuffer(str));
            return keyFactory.generatePublic(publicKeySpec);
        } catch (Exception ex) {
            Logger.getLogger(Llaves.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    };

//GETs
    public PublicKey publica(){
        return kp.getPublic();
    }
    public PrivateKey privada(){
        return kp.getPrivate();
    }

    public String publicaToStr() {
        return kp.getPublic().getAlgorithm();
    }
}
