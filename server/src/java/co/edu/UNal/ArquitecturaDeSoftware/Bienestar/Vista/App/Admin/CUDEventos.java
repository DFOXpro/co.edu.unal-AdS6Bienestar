/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.UNal.ArquitecturaDeSoftware.Bienestar.Vista.App.Admin;

import co.edu.UNal.ArquitecturaDeSoftware.Bienestar.AccesoDatos.Entity.UsuarioEntity;
import co.edu.UNal.ArquitecturaDeSoftware.Bienestar.Control.Cuentas.CtrlAdmin;
import co.edu.UNal.ArquitecturaDeSoftware.Bienestar.Vista.Util;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/**
 *
 * @author dfoxpro
 */
public class CUDEventos {

	//Para contar usuarios en convocatoria pasando Id la misma
	protected static void consultarUsuariosEnConvocatoriaId(HttpServletRequest request, HttpServletResponse response) throws IOException {
		ArrayList<UsuarioEntity> usuarios = new ArrayList<>();
		usuarios = CtrlAdmin.obtenerUsuariosEnConvocatoria(
			Integer.parseInt(request.getParameter("3")),//id evento
			Integer.parseInt(request.getParameter("2")),//tamaño tabla
			Integer.parseInt(request.getParameter("1"))//pagina
		);

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

	//Para contar usuarios en taller pasando Id la misma
	protected static void consultarUsuariosEnTallerId(HttpServletRequest request, HttpServletResponse response) throws IOException {
		ArrayList<UsuarioEntity> usuarios = new ArrayList<>();
		usuarios = CtrlAdmin.obtenerUsuariosEnTaller(
			Integer.parseInt(request.getParameter("3")),
			Integer.parseInt(request.getParameter("2")),
			Integer.parseInt(request.getParameter("1"))
		);

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

	//Para contar usuarios en convocatoria pasando Id la misma
	protected static void consultarDocentesEnTallerId(HttpServletRequest request, HttpServletResponse response) throws IOException {
		ArrayList<UsuarioEntity> usuarios = new ArrayList<>();
		usuarios = CtrlAdmin.obtenerUsuariosEnTaller(
			Integer.parseInt(request.getParameter("1")),
			Integer.parseInt(request.getParameter("2")),
			Integer.parseInt(request.getParameter("3"))
		);

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

	protected static void crearConvocatoria(HttpServletRequest request, HttpServletResponse response) throws IOException {
		ArrayList r = CtrlAdmin.crearConvocatoria(
			request.getParameter("1"), // nombre
			request.getParameter("2"), // descripción
			request.getParameter("4"), // fin
			Integer.parseInt(request.getParameter("6")) // cupos
		);

		response.setContentType("application/json;charset=UTF-8");
		PrintWriter out = response.getWriter();
		if (r.get(0) == "error") {
			JSONObject obj = new JSONObject();
			obj.put("isError", true);
			obj.put("errorDescrip", r.get(1));
			out.print(obj);
		} else if (r.get(0) == "isExitoso") {
			JSONObject obj = new JSONObject();
			obj.put("Exitoso", true);
			out.print(obj);
		} else Util.errordeRespuesta(r, out);
	}

	protected static void actualizarConvocatoria(HttpServletRequest request, HttpServletResponse response) throws IOException {
		ArrayList r = CtrlAdmin.actualizarConvocatoria(
			Integer.parseInt(request.getParameter("0")), // id
			request.getParameter("1"), // nombre
			request.getParameter("2"), // descripción
			request.getParameter("4"), // fin
			Integer.parseInt(request.getParameter("6")) // cupos
		);

		response.setContentType("application/json;charset=UTF-8");
		PrintWriter out = response.getWriter();
		if (r.get(0) == "error") {
			JSONObject obj = new JSONObject();
			obj.put("isError", true);
			obj.put("errorDescrip", r.get(1));
			out.print(obj);
		} else if (r.get(0) == "isExitoso") {
			JSONObject obj = new JSONObject();
			obj.put("Exitoso", true);
			out.print(obj);
		} else Util.errordeRespuesta(r, out);
	}

	protected static void eliminarConvocatoria(HttpServletRequest request, HttpServletResponse response) throws IOException {
		ArrayList r = CtrlAdmin.eliminarConvocatoria(Integer.parseInt(request.getParameter("1"))); // id

		response.setContentType("application/json;charset=UTF-8");
		PrintWriter out = response.getWriter();
		if (r.get(0) == "error") {
			JSONObject obj = new JSONObject();
			obj.put("isError", true);
			obj.put("errorDescrip", r.get(1));
			out.print(obj);
		} else if (r.get(0) == "isExitoso") {
			JSONObject obj = new JSONObject();
			obj.put("Exitoso", true);
			out.print(obj);
		} else Util.errordeRespuesta(r, out);
	}

	protected static void crearTaller(HttpServletRequest request, HttpServletResponse response) throws IOException {
		ArrayList r = CtrlAdmin.crearTaller(
			request.getParameter("1"), // nombre
			request.getParameter("2"), // descripción
			request.getParameter("4"), // fin registro (Fecha hasta donde está permitido registrarse)
			request.getParameter("3"), // inicio del taller
			request.getParameter("4"), // fin del taller
			Integer.parseInt(request.getParameter("5")), // costo
			Integer.parseInt(request.getParameter("6")) // cupos
		);

		response.setContentType("application/json;charset=UTF-8");
		PrintWriter out = response.getWriter();
		if (r.get(0) == "error") {
			JSONObject obj = new JSONObject();
			obj.put("isError", true);
			obj.put("errorDescrip", r.get(1));
			out.print(obj);
		} else if (r.get(0) == "isExitoso") {
			JSONObject obj = new JSONObject();
			obj.put("Exitoso", true);
			out.print(obj);
		} else Util.errordeRespuesta(r, out);
	}

	protected static void actualizarTaller(HttpServletRequest request, HttpServletResponse response) throws IOException {
		ArrayList r = CtrlAdmin.actualizarTaller(
			Integer.parseInt(request.getParameter("0")), // id
			request.getParameter("1"), // nombre
			request.getParameter("2"), // descripción
			request.getParameter("3"), // fin registro (Fecha hasta donde está permitido registrarse)
			request.getParameter("4"), // inicio del taller
			Integer.parseInt(request.getParameter("5")), // costo
			Integer.parseInt(request.getParameter("6")) // cupos
		);

		response.setContentType("application/json;charset=UTF-8");
		PrintWriter out = response.getWriter();
		if (r.get(0) == "error") {
			JSONObject obj = new JSONObject();
			obj.put("isError", true);
			obj.put("errorDescrip", r.get(1));
			out.print(obj);
		} else if (r.get(0) == "isExitoso") {
			JSONObject obj = new JSONObject();
			obj.put("Exitoso", true);
			out.print(obj);
		} else Util.errordeRespuesta(r, out);
	}

	protected static void eliminarTaller(HttpServletRequest request, HttpServletResponse response) throws IOException {
		ArrayList r = CtrlAdmin.eliminarTaller(Integer.parseInt(request.getParameter("1"))); // id

		response.setContentType("application/json;charset=UTF-8");
		PrintWriter out = response.getWriter();
		if (r.get(0) == "error") {
			JSONObject obj = new JSONObject();
			obj.put("isError", true);
			obj.put("errorDescrip", r.get(1));
			out.print(obj);
		} else if (r.get(0) == "isExitoso") {
			JSONObject obj = new JSONObject();
			obj.put("Exitoso", true);
			out.print(obj);
		} else Util.errordeRespuesta(r, out);
	}
}
