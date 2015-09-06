package co.edu.UNal.ArquitecturaDeSoftware.Bienestar.Control.Cuentas;

import co.edu.UNal.ArquitecturaDeSoftware.Bienestar.Modelo.Usuario;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author dfoxpro
 */
public class Autenticacion {

    public static ArrayList autenticar(
        String usuario,
        String contrasena,
        String cookieHashTag,
        String llavePublica
    ) {
        //Usuario u = Persistencia.getUsuario(usuario);
        //@TODO: Persistencia
//TEST
        Usuario u = new Usuario();
        u.setNombre("Nametest");
        u.setUsuario("usertest@unal.edu.co");
        u.setContrasena("test1");
        u.setRol('a');//a dministrador
//END TEST
		System.out.println("asd: "+usuario);
        try {
            usuario = new String(Cifrado.decodeBASE64(usuario), "UTF-8");
            contrasena = new String(Cifrado.decodeBASE64(contrasena), "UTF-8");
            cookieHashTag = new String(Cifrado.decodeBASE64(cookieHashTag), "UTF-8");
			System.out.println(usuario+"+"+contrasena+"+"+cookieHashTag+"+"+llavePublica);
            if (u.getUsuario().equals(usuario)) {
                if (u.getContrasena().equals(contrasena)) {
                    Sesion s = Activas.agregarSesion(usuario, llavePublica, cookieHashTag);
                    ArrayList r = new ArrayList();
                    r.add("exitoso");
                    r.add(u.getNombre());
                    r.add(u.getRol());
                    r.add(s.getLlavesServer().publicaToStr());//Devuelve la llave publica generada
                    return r;
                } else {
                    ArrayList r = new ArrayList();
                    r.add("error");
                    r.add("contrasena");
                    return r;
                }
            } else {
                ArrayList r = new ArrayList();
                r.add("error");
                r.add("usuario");
                return r;
            }
        } catch (Exception ex) {
            Logger.getLogger(Autenticacion.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public static void cerrarSesion(String usuario, String cookieHashCode) {
        Activas.cerrarSesion(usuario, cookieHashCode);
    }
}
