/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.UNal.ArquitecturaDeSoftware.Bienestar.Control.Cuentas;

/**
 *
 * @author dfoxpro
 */
public class Cifrado {

    public static int[] generarLlaves() {
        int[] llaves = {1,1};//privada,publica
        return llaves; 
    }

    static String decifrar(String contrasena, String llavePublica) {
        //TODO: decifrar
        return contrasena;
    }

    static String cifrar(String cadena, String llavePrivada) {
        //TODO: cifrar
        return cadena;
    }   
}