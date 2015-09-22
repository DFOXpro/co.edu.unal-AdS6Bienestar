package co.edu.UNal.ArquitecturaDeSoftware.Bienestar.Control.Admin;

import co.edu.UNal.ArquitecturaDeSoftware.Bienestar.AccesoDatos.DAO.ConvocatoriaDAO;
import co.edu.UNal.ArquitecturaDeSoftware.Bienestar.AccesoDatos.DAO.TallerDAO;
import co.edu.UNal.ArquitecturaDeSoftware.Bienestar.AccesoDatos.DAO.UsuarioDAO;
import co.edu.UNal.ArquitecturaDeSoftware.Bienestar.AccesoDatos.Entity.UsuarioEntity;
import co.edu.UNal.ArquitecturaDeSoftware.Bienestar.Control.Usuario.CtrlUsuario;
import static co.edu.UNal.ArquitecturaDeSoftware.Bienestar.Control.Cuentas.CtrlRegistro.isValidEmailAddress;
import java.util.ArrayList;

/**
 *
 * @author awake
 */
public class CtrlAdmin extends CtrlUsuario {

	public static ArrayList crearUsuario(String nombre, String apellidos, String tipoDeDocumento, String documento, String correo, String contrasena, char rol) {
		String error = "";
		if (nombre.length() > 100) {
			nombre = nombre.substring(0, 99);
		}
		if (apellidos.length() > 100) {
			apellidos = apellidos.substring(0, 99);
		}
		if (tipoDeDocumento.length() != 2) {
			error = "tipoD";
		}
		if (documento.length() < 5 || documento.length() > 30) {
			error = "documento";
		}
		if (contrasena.length() < 5 || contrasena.length() > 999) {
			error = "contraseña";
		}
		if (nombre.isEmpty() || apellidos.isEmpty()) {
			error = "nombre";
		}
		if (!(rol == 'A' || rol == 'P' || rol == 'E')) {
			error = "rol";
		}
		if (!isValidEmailAddress(correo)) {
			error = "correo1";
		} else {
			correo = correo.toLowerCase();
		}

		if (error.isEmpty()) {
			error = UsuarioDAO.create(documento, tipoDeDocumento, nombre, apellidos, correo, contrasena, rol);
		}

		ArrayList r = new ArrayList();
		if (error.equals("OK")) {
			r.add("isExitoso");
		} else {
			r.add("error");
			r.add(error);
		}
		return r;
	}

	public static ArrayList editarUsuario(int idUsuario, String nombre, String apellidos, String tipoDeDocumento, String documento, String correo, String contrasena, char rol) {
		String error = "";
		if (nombre.length() > 100) {
			nombre = nombre.substring(0, 99);
		}
		if (apellidos.length() > 100) {
			apellidos = apellidos.substring(0, 99);
		}
		if (tipoDeDocumento.length() != 2) {
			error = "tipoD";
		}
		if (documento.length() < 5 || documento.length() > 30) {
			error = "documento";
		}
		if (contrasena.length() < 5 || contrasena.length() > 999) {
			error = "contraseña";
		}
		if (nombre.isEmpty() || apellidos.isEmpty()) {
			error = "nombre";
		}
		if (!(rol == 'A' || rol == 'P' || rol == 'E')) {
			error = "rol";
		}
		if (!isValidEmailAddress(correo)) {
			error = "correo1";
		} else {
			correo = correo.toLowerCase();
		}

		if (error.isEmpty()) {
			error = UsuarioDAO.update(idUsuario, documento, tipoDeDocumento, nombre, apellidos, correo, contrasena, rol);
		}

		ArrayList r = new ArrayList();
		if (error.equals("OK")) {
			r.add("isExitoso");
		} else {
			r.add("error");
			r.add(error);
		}
		return r;
	}

	public static UsuarioEntity leerUsuario(String username) {
		return UsuarioDAO.getByUsername(username);
	}

	public static UsuarioEntity leerUsuarioId(int idUsuario) {
		return UsuarioDAO.getById(idUsuario);
	}

	public static ArrayList<UsuarioEntity> leerMultiplesUsuarios(int tamano, int posicion) {
		return UsuarioDAO.getUsuarios(posicion, tamano);
	}

	;

    public static int obtenerTotalUsuarios() {
		return UsuarioDAO.getCountUsuarios();
	}

	public static ArrayList eliminarUsuario(int ID_USUARIO) {
		String error = UsuarioDAO.delete(ID_USUARIO);
		ArrayList r = new ArrayList();
		if (error.equals("OK")) {
			r.add("isExitoso");
		} else {
			r.add("error");
			r.add(error);
		}
		return r;
	}

	// Retorna usuarios en taller
	public static ArrayList<UsuarioEntity> obtenerUsuariosEnTaller(int ID_TALLER, int tamano, int pagina) {
		return UsuarioDAO.getUsuariosByTaller(ID_TALLER, tamano, pagina);
	}

	// Retorna profesores en taller
	public static ArrayList<UsuarioEntity> obtenerProfesoresEnTaller(int ID_TALLER, int tamano, int pagina) {
		return UsuarioDAO.getProfesoresByTaller(ID_TALLER, tamano, pagina);
	}

	;
    
        // Retorna usuarios en convocatoria
    public static ArrayList<UsuarioEntity> obtenerUsuariosEnConvocatoria(int ID_CONVOCATORIA, int tamano, int pagina) {
		return UsuarioDAO.getUsuariosByConvocatoria(ID_CONVOCATORIA, tamano, pagina);
	}

	;
    
    
    public static ArrayList crearConvocatoria(String nombre, String descripcion, String fin, int cupos) {
		String error = ConvocatoriaDAO.create(nombre, descripcion, fin, cupos);
		ArrayList r = new ArrayList();
		if (error.equals("OK")) {
			r.add("isExitoso");
		} else {
			r.add("error");
			r.add(error);
		}
		return r;
	}

	public static ArrayList actualizarConvocatoria(int id, String nombre, String descripcion, String fin, int cupos) {
		String error = ConvocatoriaDAO.update(id, nombre, descripcion, fin, cupos);
		ArrayList r = new ArrayList();
		if (error.equals("OK")) {
			r.add("isExitoso");
		} else {
			r.add("error");
			r.add(error);
		}
		return r;
	}

	public static ArrayList eliminarConvocatoria(int id) {
		String error = ConvocatoriaDAO.delete(id);
		ArrayList r = new ArrayList();
		if (error.equals("OK")) {
			r.add("isExitoso");
		} else {
			r.add("error");
			r.add(error);
		}
		return r;
	}

	public static ArrayList crearTaller(String nombre, String descripcion, String finR, String inicio, String fin, int costo, int cupos) {
		String error = TallerDAO.create('t', nombre, descripcion, finR, inicio, fin, costo, cupos);
		ArrayList r = new ArrayList();
		if (error.equals("OK")) {
			r.add("isExitoso");
		} else {
			r.add("error");
			r.add(error);
		}
		return r;
	}

	public static ArrayList actualizarTaller(
			int ID_TALLER, String NOMBRE,
			String DESCRIPCION,
			String FECHA_INICIO,
			String FECHA_FIN,
			int COSTO,
			int CUPOS
	) {
		String error = TallerDAO.update(ID_TALLER, 't', NOMBRE, DESCRIPCION, FECHA_INICIO, FECHA_INICIO, FECHA_FIN, COSTO, CUPOS);
		ArrayList r = new ArrayList();
		if (error.equals("OK")) {
			r.add("isExitoso");
		} else {
			r.add("error");
			r.add(error);
		}
		return r;
	}

	public static ArrayList eliminarTaller(int id) {
		String error = TallerDAO.delete(id);
		ArrayList r = new ArrayList();
		if (error.equals("OK")) {
			r.add("isExitoso");
		} else {
			r.add("error");
			r.add(error);
		}
		return r;
	}

}
