/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.UNal.ArquitecturaDeSoftware.Bienestar.Control.Cuentas.Util;

import java.security.PublicKey;
import java.security.PrivateKey;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.Cipher;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;


/**
 *
 * @author dfoxpro
 */
public class Cifrado {
    public static final Llaves generarLlaves() {
        try {
            return new Llaves();
        } catch (Exception ex) {
            Logger.getLogger(Cifrado.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    static String decifrar(String cifrado, PrivateKey llavePrivada) {
        try {
            Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
            cipher.init(Cipher.DECRYPT_MODE, llavePrivada);

            byte[] plaintext = cipher.doFinal(decodeBASE64(cifrado));
            return new String(plaintext, "UTF8");
        } catch (Exception ex) {
            Logger.getLogger(Cifrado.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    static String cifrar(String cadena, PublicKey llavePublica) {
        try {
            //PublicKey key = keyPair.getPublic();
            Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
            cipher.init(Cipher.ENCRYPT_MODE, llavePublica);

            byte[] ciphertext = cipher.doFinal(cadena.getBytes("UTF8"));
            return encodeBASE64(ciphertext);
        } catch (Exception ex) {
            Logger.getLogger(Cifrado.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
//UTILITY
    public static byte[] decodeBASE64(String text) throws Exception{
        return new BASE64Decoder().decodeBuffer(text);
    }
    public static String encodeBASE64(byte[] bytes){
        return new BASE64Encoder().encode(bytes);
    }
}
