/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.UNal.ArquitecturaDeSoftware.Bienestar.Control.Cuentas;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author dfoxpro
 */
public class Activas {
    static private Map<String,Sesion> sesionesActivas;

    static public Sesion agregarSesion (String usuario, String llavePublica, String cookieHashTag){
        if (sesionesActivas == null)
            sesionesActivas = new HashMap<>();
        //Cierra la sesion anterior
        //@Deprecated: Map.put remplaza al anterior 
        //if(sesionesActivas.get(usuario) != null) sesionesActivas.remove(usuario);

        Sesion s = new Sesion(
            llavePublica,
            cookieHashTag
        );
        //System.out.println(usuario+"||"+s);
        sesionesActivas.put(usuario, s);
        return s;
    }
    static public Sesion getSesion(String usuario){
        return sesionesActivas.get(usuario);
    }

    static void cerrarSesion(String usuario, String cookieHashCode) {
        Sesion s = sesionesActivas.get(usuario);
        if(s != null & s.getCokieHashCode().equals(cookieHashCode))
            sesionesActivas.remove(usuario);
        else System.out.println("Warning!: posible ataque en cerrar sesión: "+usuario+", "+cookieHashCode);
    }
}
