package co.edu.UNal.ArquitecturaDeSoftware.Bienestar.Control.Cuentas;

import co.edu.UNal.ArquitecturaDeSoftware.Bienestar.Modelo.Usuario;
import java.util.ArrayList;

/**
 *
 * @author dfoxpro
 */
public class Autenticacion {

    public static ArrayList autenticar(
        String usuario,
        String contrasena,
        String llavePublica,
        String cookieHashTag
    ) {
        //Usuario u = Persistencia.getUsuario(usuario);
        //@TODO: Persistencia
//TEST
        Usuario u = new Usuario();
        u.setNombre("Nametest");
        u.setUsuario("usertest");
        u.setContrasena("test");
        u.setRol('a');
//TEST
        contrasena = Cifrado.decifrar(contrasena, llavePublica);
        if (u.getUsuario().equals(usuario)) {
            if (u.getContrasena().equals(contrasena)) {
                Sesion s = Activas.agregarSesion(usuario, llavePublica, cookieHashTag);
                ArrayList r = new ArrayList();
                r.add("exitoso");
                r.add(u.getNombre());
                r.add(u.getRol());
                r.add(s.getLlavesServer()[1]);//Devuelve la llave publica generada
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
    }

    public static void cerrarSesion(String usuario, String cookieHashCode) {
        Activas.cerrarSesion(usuario, cookieHashCode);
    }
}
