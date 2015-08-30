package co.edu.UNal.ArquitecturaDeSoftware.Bienestar.Control;

import co.edu.UNal.ArquitecturaDeSoftware.Bienestar.Modelo.Usuario;
import java.util.ArrayList;

/**
 *
 * @author dfoxpro
 */
public class Autenticacion {

    public static ArrayList autenticar(String usuario, String contrasena) {
        //Usuario u = Persistencia.getUsuario(usuario);
        //@TODO: Persistencia
//TEST
        Usuario u = new Usuario();
        u.setNombre("Nametest");
        u.setUsuario("usertest");
        u.setContrasena("test");
        u.setRol('a');
//TEST
        //contrasena = Crifrado.de(contrasena);
        //@TODO: Cifrado
        if (u.getUsuario() == usuario) {
            if (u.getContrasena() == contrasena) {
                ArrayList r = new ArrayList();
                r.add("exitoso");
                r.add(u.getNombre());
                r.add(u.getRol());
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
;
}
