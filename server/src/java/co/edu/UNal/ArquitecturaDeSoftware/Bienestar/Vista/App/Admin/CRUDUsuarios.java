/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.UNal.ArquitecturaDeSoftware.Bienestar.Vista.App.Admin;

import co.edu.UNal.ArquitecturaDeSoftware.Bienestar.AccesoDatos.Entity.UsuarioEntity;
import co.edu.UNal.ArquitecturaDeSoftware.Bienestar.Control.Admin.CtrlAdmin;
import co.edu.UNal.ArquitecturaDeSoftware.Bienestar.Vista.Util;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/**
 *
 * @author dfoxpro
 */
public class CRUDUsuarios {

	protected static void crearUsuario(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println(request.getParameter("5"));
		ArrayList r = CtrlAdmin.crearUsuario(
			request.getParameter("1"),//Nombre
			request.getParameter("2"),//Apellidos
			request.getParameter("3"),//Tipo de documento
			Integer.parseInt(request.getParameter("4")),//documento
			request.getParameter("5"),//Correo= usuario
			request.getParameter("6"),//passworld
			request.getParameter("7").charAt(0)//rol
		);

		response.setContentType("application/json;charset=UTF-8");
		PrintWriter out = response.getWriter();
		if (r.get(0) == "error") {
			JSONObject obj = new JSONObject();
			if (r.get(1) == "usuario") {
				obj.put("isError", true);
				obj.put("errorDescrip", "El usuario ya existe");
			} else if (r.get(1) == "contrasena") {
				obj.put("isError", true);
				obj.put("errorDescrip", "La contraseña es invalida");
			} else if (r.get(1) == "documento") {
				obj.put("isError", true);
				obj.put("errorDescrip", "El documento ya está registrado");
			} else if (r.get(1) == "tipoDocumento") {
				obj.put("isError", true);
				obj.put("errorDescrip", "El tipo de documento es invalido");
			} else if (r.get(1) == "correo") {
				obj.put("isError", true);
				obj.put("errorDescrip", "El correo ya está registrado");
			} else if (r.get(1) == "correo1") {
				obj.put("isError", true);
				obj.put("errorDescrip", "El correo no es valido");
			} else if (r.get(1) == "nombre") {
				obj.put("isError", true);
				obj.put("errorDescrip", "Los nombres o apellidos son incorrectos");
			} else if (r.get(1) == "rol") {
				obj.put("isError", true);
				obj.put("errorDescrip", "El rol es invalido, los posibles valores son: E, P y A");
			} else Util.errordeRespuesta(r, out);
			out.print(obj);
		} else if (r.get(0) == "isExitoso") {
			JSONObject obj = new JSONObject();
			obj.put("Exitoso", true);
			out.print(obj);
		} else Util.errordeRespuesta(r, out);
	}

	protected static void editarUsuario(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println(request.getParameter("5"));
		ArrayList r = CtrlAdmin.editarUsuario(
			Integer.parseInt(request.getParameter("0")),//IdUsuario
			request.getParameter("1"),//Nombre
			request.getParameter("2"),//Apellidos
			request.getParameter("3"),//Tipo de documento
			Integer.parseInt(request.getParameter("4")),//documento
			request.getParameter("5"),//Correo= usuario
			request.getParameter("6"),//passworld
			request.getParameter("7").charAt(0)//rol
		);

		response.setContentType("application/json;charset=UTF-8");
		PrintWriter out = response.getWriter();
		JSONObject obj = new JSONObject();
		if (r.get(0) == "error") {
			obj.put("isError", true);
			boolean errorSerio = false;
			if (r.get(1) == "usuario") {
				obj.put("errorDescrip", "El usuario ya existe");
			} else if (r.get(1) == "contrasena") {
				obj.put("errorDescrip", "La contraseña es invalida");
			} else if (r.get(1) == "documento") {
				obj.put("errorDescrip", "El documento ya está registrado");
			} else if (r.get(1) == "tipoDocumento") {
				obj.put("errorDescrip", "El tipo de documento es invalido");
			} else if (r.get(1) == "correo") {
				obj.put("errorDescrip", "El correo ya está registrado");
			} else if (r.get(1) == "correo1") {
				obj.put("errorDescrip", "El correo no es valido");
			} else if (r.get(1) == "nombre") {
				obj.put("errorDescrip", "Los nombres o apellidos son incorrectos");
			} else if (r.get(1) == "rol") {
				obj.put("errorDescrip", "El rol es invalido, los posibles valores son: E, P y A");
			} else {
				Util.errordeRespuesta(r, out);
				errorSerio = true;
			}
			if (!errorSerio) {
				out.print(obj);
			}
		} else if (r.get(0) == "isExitoso") {
			obj.put("Exitoso", true);
			out.print(obj);
		} else Util.errordeRespuesta(r, out);
	}

	protected static void eliminarUsuario(HttpServletRequest request, HttpServletResponse response) throws IOException {
		ArrayList r = CtrlAdmin.eliminarUsuario(Integer.parseInt(request.getParameter("1"))); // id_usuario

		response.setContentType("application/json;charset=UTF-8");
		PrintWriter out = response.getWriter();
		if (r.get(0) == "error") {
			JSONObject obj = new JSONObject();
			if (r.get(1) == "usuario") {
				obj.put("isError", true);
				obj.put("errorDescrip", "El usuario no existe");

			} else {
				Util.errordeRespuesta(r, out);
			}
			out.print(obj);
		} else if (r.get(0) == "isExitoso") {
			JSONObject obj = new JSONObject();
			obj.put("Exitoso", true);
			out.print(obj);
		} else Util.errordeRespuesta(r, out);
	}

	protected static void leerUsuarioId(HttpServletRequest request, HttpServletResponse response) throws IOException {
		UsuarioEntity e = CtrlAdmin.leerUsuarioId(Integer.parseInt(request.getParameter("1"))); // id del usuario

		response.setContentType("application/json;charset=UTF-8");
		PrintWriter out = response.getWriter();

		JSONObject obj = new JSONObject();
		obj.put("id", e.getIdUsuario());
		obj.put("nombre", e.getNombres());
		obj.put("apellido", e.getApellidos());
		obj.put("tipoDocumento", e.gettDocumento());
		obj.put("documento", e.getDocumento());
		obj.put("contrasena", e.getPassword());
		obj.put("rol", "" + e.getRol());
		obj.put("email", e.getUsername());
		out.print(obj);
	}

	protected static void leerUsuario(HttpServletRequest request, HttpServletResponse response) throws IOException {
		UsuarioEntity e = CtrlAdmin.leerUsuario(request.getParameter("1")); // String nombre del usuario

		response.setContentType("application/json;charset=UTF-8");
		PrintWriter out = response.getWriter();

		JSONObject obj = new JSONObject();
		obj.put("id", e.getIdUsuario());
		obj.put("nombre", e.getNombres());
		obj.put("apellido", e.getApellidos());
		obj.put("tipoDocumento", e.gettDocumento());
		obj.put("documento", e.getDocumento());
		obj.put("contrasena", e.getPassword());
		obj.put("rol", e.getRol());
		out.print(obj);
	}

	protected static void leerUsuariosMultiplesId(HttpServletRequest request, HttpServletResponse response) throws IOException {
		ArrayList<UsuarioEntity> usuarios = new ArrayList<>();
		usuarios = CtrlAdmin.leerMultiplesUsuarios(Integer.parseInt(request.getParameter("1")), Integer.parseInt(request.getParameter("2"))); // id del usuario

		response.setContentType("application/json;charset=UTF-8");
		PrintWriter out = response.getWriter();

		JSONArray list1 = new JSONArray();
		for (UsuarioEntity usuario : usuarios) {
			JSONObject obj = new JSONObject();
			obj.put("id", usuario.getIdUsuario());
			obj.put("titulo", usuario.getNombres() + " " + usuario.getApellidos());
			list1.add(obj);
		}
		out.print(list1);
	}

	protected static void obtenerTotalUsuarios(HttpServletRequest request, HttpServletResponse response) throws IOException {
		int numC = CtrlAdmin.obtenerTotalUsuarios();
		response.setContentType("application/json;charset=UTF-8");
		PrintWriter out = response.getWriter();
		JSONObject obj = new JSONObject();
		obj.put("total", numC);
		out.print(obj);
	}

}