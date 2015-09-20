package co.edu.UNal.ArquitecturaDeSoftware.Bienestar.Control.Usuario;

import co.edu.UNal.ArquitecturaDeSoftware.Bienestar.AccesoDatos.DAO.*;
import co.edu.UNal.ArquitecturaDeSoftware.Bienestar.AccesoDatos.Entity.*;
import java.util.ArrayList;

/**
 *
 * @author awake
 */
public class CtrlUsuario {

	UsuarioDAO usr = new UsuarioDAO();
	ConvocatoriaDAO conv = new ConvocatoriaDAO();
	TallerDAO tall = new TallerDAO();

	public static ConvocatoriaEntity leerConvocatoria(String id) {
		return ConvocatoriaDAO.getByID(id);
	}

	public static TallerEntity leerTaller(String id) {
		return TallerDAO.getByID(id);
	}

	public static ArrayList<ConvocatoriaEntity> leerMultiplesConvocatorias(int posicion, int tamano) {
		return ConvocatoriaDAO.getConvocatorias(tamano, posicion);
	}

	public static int obtenerTotalConvocatorias() {
		return ConvocatoriaDAO.getCountConvoctorias();
	}

	public static int obtenerInscritosConv(int id) {
		return ConvocatoriaDAO.getCountUsuarioConvocatoria(id);
	}

	public static ArrayList<TallerEntity> leerMultiplesTalleres(int posicion, int tamano) {
		return TallerDAO.getTodosTalleres(tamano, posicion);
	}

	public static int obtenerTotalTalleres() {
		return TallerDAO.getCountTodosTalleres();
	}

	public static int obtenerInscritosTaller(int id_taller) {
		return TallerDAO.getCountUsuarioTalleres(id_taller);
	}

    //	public UsuarioEntity leerUsuario(String username) {
//		return usr.getByUsername(username);
//	}
//
//	public UsuarioEntity leerUsuarioId(int idUsuario) {
//		return usr.getById(idUsuario);
//	}
//
//	public ArrayList<UsuarioEntity> leerMultiplesUsuarios(int tamano, int posicion) {
//		return usr.getUsuarios(posicion, tamano);};
//
//	public int obtenerTotalUsuarios() {
//		return usr.getTotalUsuarios();
//	}
	//Para registrar usuario a talleres
	public static ArrayList registrarATallerUsuario(int ID_USUARIO, int ID_TALLER) {
		String error = UsuarioDAO.registrarTaller(ID_USUARIO, ID_TALLER);
		ArrayList r = new ArrayList();
		if (error.equals("OK")) {
			r.add("isExitoso");
		} else {
			r.add("error");
			r.add(error);
		}
		return r;
	}

	//Para registrar usuario a talleres
	public static ArrayList registrarAConvocatoriaUsuario(int ID_USUARIO, int ID_CONVOCATORIA) {
		String error = UsuarioDAO.registrarConvocatoria(ID_USUARIO, ID_CONVOCATORIA);
		ArrayList r = new ArrayList();
		if (error.equals("OK")) {
			r.add("isExitoso");
		} else if(error.contains("Duplicate entry")){
			r.add("error");
			r.add("Ya estás inscrito");
		} else {
			r.add("error");
			r.add(error);
		}
		return r;
	}

	//Para registrar  docente a talleres
	public static ArrayList registrarATallerDocente(int ID_USUARIO, int ID_TALLER) {
		String error = TallerDAO.registrarProfesor(ID_USUARIO, ID_TALLER);
		ArrayList r = new ArrayList();
		if (error.equals("OK")) {
			r.add("isExitoso");
		} else {
			r.add("error");
			r.add(error);
		}
		return r;
	}

	//Para registrar  docente a talleres por cédula (desde el administrador) 
	public static ArrayList registrarATallerDocenteByDoc(int documento, int ID_TALLER) {
		UsuarioEntity u = UsuarioDAO.getByDocumento(documento);
		return registrarATallerDocente(u.getIdUsuario(), ID_TALLER);
	}

	//Para desvincular a usuario de talleres
	public static ArrayList abandonarTallerUsuario(int ID_USUARIO, int ID_TALLER) {
		String error = UsuarioDAO.desvincularTaller(ID_USUARIO, ID_TALLER);
		ArrayList r = new ArrayList();
		if (error.equals("OK")) {
			r.add("isExitoso");
		} else {
			r.add("error");
			r.add(error);
		}
		return r;
	}

	//Para desvincular a usuario de talleres
	public static ArrayList abandonarTallerDocente(int ID_PROFESOR, int ID_TALLER) {
		String error = TallerDAO.desvincularProfesor(ID_PROFESOR, ID_TALLER);
		ArrayList r = new ArrayList();
		if (error.equals("OK")) {
			r.add("isExitoso");
		} else {
			r.add("error");
			r.add(error);
		}
		return r;
	}

	//Para desvincular a usuario de las convocatorias
	public static ArrayList abandonarConvocatoria(int ID_USUARIO, int ID_CONVOCATORIA) {
		String error = UsuarioDAO.desvincularConvocatoria(ID_USUARIO, ID_CONVOCATORIA);
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
