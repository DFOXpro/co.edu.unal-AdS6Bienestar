package co.edu.UNal.ArquitecturaDeSoftware.Bienestar.Control.Cuentas;

import co.edu.UNal.ArquitecturaDeSoftware.Bienestar.AccesoDatos.DAO.UsuarioDAO;
import co.edu.UNal.ArquitecturaDeSoftware.Bienestar.Control.Cuentas.Util.Sesion;
import co.edu.UNal.ArquitecturaDeSoftware.Bienestar.Control.Cuentas.Util.Activas;
import co.edu.UNal.ArquitecturaDeSoftware.Bienestar.Control.Cuentas.Util.Cifrado;
import co.edu.UNal.ArquitecturaDeSoftware.Bienestar.AccesoDatos.Entity.UsuarioEntity;
import co.edu.UNal.ArquitecturaDeSoftware.Bienestar.Vista.Cuenta.LoginLDAP;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author dfoxpro
 */
public class CtrlAutenticacion {

	private static Map<String, String> esperandoLlave;

	public static ArrayList autenticar(
			String usuario,
			String contrasena,
			String cookieHashCode
	) 
        {
		try {
			usuario = new String(Cifrado.decodeBASE64(usuario), "UTF-8");
			contrasena = new String(Cifrado.decodeBASE64(contrasena), "UTF-8");
			cookieHashCode = new String(Cifrado.decodeBASE64(cookieHashCode), "UTF-8");
		} catch (Exception ex) {
			Logger.getLogger(CtrlAutenticacion.class.getName()).log(Level.SEVERE, null, ex);
		}

		UsuarioEntity u = UsuarioDAO.getByUsername(usuario);                
		try {
                    
                    //String usuario="Daito Manabe";
                    //String contrasena="12345";
            

                    LoginLDAP ldap = new LoginLDAP();
                    String nombreLDAP = u.getNombres()+" "+u.getApellidos();
                    String message = ldap.login(nombreLDAP, contrasena);
                    System.out.println(message);
                    
			if (message.equals("Login exitoso")) {
				Sesion s = Activas.agregarSesion(usuario, cookieHashCode);
				if (esperandoLlave == null) {
					esperandoLlave = new HashMap<>();
				}
				esperandoLlave.put(cookieHashCode, usuario);

				ArrayList r = new ArrayList();
				r.add("exitoso");
				r.add(u.getNombres());
				r.add(u.getRol());
				r.add(u.getIdUsuario());
				//r.add(s.getLlavesServer().publicaToStr());//Devuelve la llave publica generada
				return r;
			} else {
				ArrayList r = new ArrayList();
				r.add("error");
				r.add("contrasena");
				return r;
			}
		} catch (NullPointerException e) {
			ArrayList r = new ArrayList();
			r.add("error");
			r.add("usuario");
			return r;
		}
	}

	public static void cerrarSesion(String usuario, String cookieHashCode) {
		try {
			Activas.cerrarSesion(
					new String(Cifrado.decodeBASE64(usuario), "UTF-8"),
					cookieHashCode
			);
		} catch (Exception ex) {
			Logger.getLogger(CtrlAutenticacion.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	public static void confirmarCifrado(
			String llaveCliente,
			String cookieHashCode
	) {
		String s = esperandoLlave.get(cookieHashCode);
		if (s != null) {
			Activas.getSesion(s).setLlaveCliente(llaveCliente);
			esperandoLlave.remove(cookieHashCode);
		} else
			System.out.println("Warning!: posible ataque en confirmarCifrado: " + cookieHashCode);
	}
}
