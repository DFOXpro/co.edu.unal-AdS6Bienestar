/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.UNal.ArquitecturaDeSoftware.Bienestar.Vista.App.Usuario;

import co.edu.UNal.ArquitecturaDeSoftware.Bienestar.AccesoDatos.Entity.ConvocatoriaEntity;
import co.edu.UNal.ArquitecturaDeSoftware.Bienestar.AccesoDatos.Entity.TallerEntity;
import co.edu.UNal.ArquitecturaDeSoftware.Bienestar.Control.Usuario.CtrlUsuario;
import co.edu.UNal.ArquitecturaDeSoftware.Bienestar.Vista.Util;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/**
 *
 * @author dfoxpro
 */
public class Read extends HttpServlet {

	protected static void leerTaller(HttpServletRequest request, HttpServletResponse response) throws IOException {

		TallerEntity e = CtrlUsuario.leerTaller(request.getParameter("1")); // id de la taller

		response.setContentType("application/json;charset=UTF-8");
		PrintWriter out = response.getWriter();
		if (e.getNombre().isEmpty()) {
			JSONObject obj = new JSONObject();
			obj.put("isError", true);
			obj.put("errorDescrip", "La convocatoria no existe");
			out.print(obj);
		} else {
			JSONObject obj = new JSONObject();
			obj.put("id", e.getIdTaller());
			obj.put("nombre", e.getNombre());
			obj.put("descripcion", e.getDescripcion());
			obj.put("cupos", e.getCupos());
			obj.put("costo", e.getCosto());
			obj.put("fechaFin", "" + e.getFechaFinRegistro());
			obj.put("fechaInicio", "" + e.getFechaInicio());

			out.print(obj);
		}
	}

	protected static void leerConvocatoria(HttpServletRequest request, HttpServletResponse response) throws IOException {

		ConvocatoriaEntity e = CtrlUsuario.leerConvocatoria(request.getParameter("1")); // id de la convocatoria

		response.setContentType("application/json;charset=UTF-8");
		PrintWriter out = response.getWriter();
		if (e.getNombre().isEmpty()) {
			JSONObject obj = new JSONObject();
			obj.put("isError", true);
			obj.put("errorDescrip", "La convocatoria no existe");
			out.print(obj);
		} else {
			JSONObject obj = new JSONObject();
			obj.put("id", e.getIdConvocatoria());
			obj.put("nombre", e.getNombre());
			obj.put("descripcion", e.getDescripcion());
			obj.put("cupos", e.getCupos());
			obj.put("fechaFin", "" + e.getFechaFinRegistro());
			out.print(obj);
		}
	}

	protected static void leerMultiplesConvocatorias(HttpServletRequest request, HttpServletResponse response) throws IOException {
		ArrayList<ConvocatoriaEntity> convocatorias = new ArrayList<>();
		convocatorias = CtrlUsuario.leerMultiplesConvocatorias(Integer.parseInt(request.getParameter("1")), Integer.parseInt(request.getParameter("2"))); // tamaño y posición

		response.setContentType("application/json;charset=UTF-8");
		PrintWriter out = response.getWriter();

		JSONArray list1 = new JSONArray();
		for (ConvocatoriaEntity convocatoria : convocatorias) {
			JSONObject obj = new JSONObject();
			obj.put("id", convocatoria.getIdConvocatoria());
			obj.put("titulo", convocatoria.getNombre());
			list1.add(obj);
		}
		out.print(list1);
	}

	protected static void leerMultiplesTalleres(HttpServletRequest request, HttpServletResponse response) throws IOException {
		ArrayList<TallerEntity> talleres = new ArrayList<>();
		talleres = CtrlUsuario.leerMultiplesTalleres(
			Integer.parseInt(request.getParameter("1")),
			Integer.parseInt(request.getParameter("2"))
		); // posición y tamaño

		response.setContentType("application/json;charset=UTF-8");
		PrintWriter out = response.getWriter();
		System.out.println("Talleres: " + talleres.size());
		JSONArray list1 = new JSONArray();
		for (TallerEntity taller : talleres) {
			JSONObject obj = new JSONObject();
			obj.put("id", taller.getIdTaller());
			obj.put("titulo", taller.getNombre());
			list1.add(obj);
		}
		out.print(list1);
	}

	protected static void obtenerTotalConvocatorias(HttpServletRequest request, HttpServletResponse response) throws IOException {
		int numC = CtrlUsuario.obtenerTotalConvocatorias();
		response.setContentType("application/json;charset=UTF-8");
		PrintWriter out = response.getWriter();
		JSONObject obj = new JSONObject();
		obj.put("total", numC);
		out.print(obj);
	}

	protected static void obtenerTotalTalleres(HttpServletRequest request, HttpServletResponse response) throws IOException {
		int numC = CtrlUsuario.obtenerTotalTalleres();
		response.setContentType("application/json;charset=UTF-8");
		PrintWriter out = response.getWriter();
		JSONObject obj = new JSONObject();
		obj.put("total", numC);
		out.print(obj);
	}

	protected static void obtenerInscritosConv(HttpServletRequest request, HttpServletResponse response) throws IOException {
		int numC = CtrlUsuario.obtenerInscritosConv(Integer.parseInt(request.getParameter("1")));
		response.setContentType("application/json;charset=UTF-8");
		PrintWriter out = response.getWriter();
		JSONObject obj = new JSONObject();
		obj.put("inscritos", numC);
		out.print(obj);
	}

	protected static void obtenerInscritosTaller(HttpServletRequest request, HttpServletResponse response) throws IOException {
		int numC = CtrlUsuario.obtenerInscritosTaller(Integer.parseInt(request.getParameter("1")));
		response.setContentType("application/json;charset=UTF-8");
		PrintWriter out = response.getWriter();
		JSONObject obj = new JSONObject();
		obj.put("inscritos", numC);
		out.print(obj);
	}

	protected static void registrarUsuarioTaller(HttpServletRequest request, HttpServletResponse response) throws IOException {
		ArrayList r = CtrlUsuario.registrarATallerUsuario(Integer.parseInt(request.getParameter("1")), Integer.parseInt(request.getParameter("2"))); // parameter 1: idUsuario param2: idTaller

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
		} else {
			Util.errordeRespuesta(r, out);
		}
	}

	protected static void registrarUsuarioConvocatoria(HttpServletRequest request, HttpServletResponse response) throws IOException {
		ArrayList r = CtrlUsuario.registrarAConvocatoriaUsuario(Integer.parseInt(request.getParameter("1")), Integer.parseInt(request.getParameter("2"))); // parameter 1: idUsuario param2: idConv

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
		} else {
			Util.errordeRespuesta(r, out);
		}
	}

	protected static void registrarDocenteTaller(HttpServletRequest request, HttpServletResponse response) throws IOException {
		ArrayList r = CtrlUsuario.registrarATallerDocente(Integer.parseInt(request.getParameter("1")), Integer.parseInt(request.getParameter("2"))); // parameter 1: idUsuarioDocente param2: idTaller

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
		} else {
			Util.errordeRespuesta(r, out);
		}
	}

	protected static void registrarATallerDocenteByDoc(HttpServletRequest request, HttpServletResponse response) throws IOException {
		ArrayList r = CtrlUsuario.registrarATallerDocenteByDoc(Integer.parseInt(request.getParameter("1")), Integer.parseInt(request.getParameter("2"))); // parameter 1: documentoDocente param2: idTaller

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
		} else {
			Util.errordeRespuesta(r, out);
		}
	}

	protected static void quitarDocenteTaller(HttpServletRequest request, HttpServletResponse response) throws IOException {
		ArrayList r = CtrlUsuario.abandonarTallerDocente(Integer.parseInt(request.getParameter("1")), Integer.parseInt(request.getParameter("2"))); //1. idUsuario, 2. idEvento

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
		} else {
			Util.errordeRespuesta(r, out);
		}
	}

	protected static void quitarUsuarioConvocatoria(HttpServletRequest request, HttpServletResponse response) throws IOException {
		ArrayList r = CtrlUsuario.abandonarConvocatoria(
				Integer.parseInt(request.getParameter("1")),//1. idUsuario
				Integer.parseInt(request.getParameter("2"))// 2. idEvento
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
		} else {
			Util.errordeRespuesta(r, out);
		}
	}

	protected static void quitarUsuarioTaller(HttpServletRequest request, HttpServletResponse response) throws IOException {
		ArrayList r = CtrlUsuario.abandonarTallerUsuario(Integer.parseInt(request.getParameter("1")), Integer.parseInt(request.getParameter("2"))); //1. idUsuario, 2. idEvento

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
		} else {
			Util.errordeRespuesta(r, out);
		}
	}
}