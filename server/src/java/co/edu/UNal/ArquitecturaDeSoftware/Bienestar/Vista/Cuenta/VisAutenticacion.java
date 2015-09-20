package co.edu.UNal.ArquitecturaDeSoftware.Bienestar.Vista.Cuenta;

import co.edu.UNal.ArquitecturaDeSoftware.Bienestar.Control.Cuentas.CtrlAutenticacion;
import co.edu.UNal.ArquitecturaDeSoftware.Bienestar.Vista.Util;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.simple.JSONObject;

/**
 * url /network/sesion
 *
 * @author dfoxpro
 */
public class VisAutenticacion extends HttpServlet {

	/**
	 * Processes requests for HTTP <code>GET</code> methods.
	 *
	 * @param request
	 * @param response servlet response
	 * @throws ServletException if a servlet-specific error occurs
	 * @throws IOException if an I/O error occurs
	 */
	protected void iniciarSesion(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		ArrayList r = CtrlAutenticacion.autenticar(
				request.getParameter("1"),
				request.getParameter("2"),
				request.getParameter("3")
		);

		response.setContentType("application/json;charset=UTF-8");
		PrintWriter out = response.getWriter();
		if (r.get(0) == "error") {
			if (r.get(1) == "usuario") {
				JSONObject obj = new JSONObject();
				obj.put("isError", true);
				obj.put("errorDescrip", "El usuario no está registrado");
				out.print(obj);
			} else if (r.get(1) == "contrasena") {
				JSONObject obj = new JSONObject();
				obj.put("isError", true);
				obj.put("errorDescrip", "La contraseña no coinside");
				out.print(obj);
			} else {
				Util.errordeRespuesta(r, out);
			}
		} else if (r.get(0) == "exitoso") {
			JSONObject obj = new JSONObject();
			obj.put("a", r.get(1));//nombre
			obj.put("b", "" + r.get(2));//rol
			obj.put("c", "" + "asd");//r.get(3));//llave publica
			obj.put("d", r.get(3));//Id
			out.print(obj);
		} else {
			Util.errordeRespuesta(r, out);
		}
	}

	/**
	 *
	 *
	 * @param request servlet request
	 * @throws ServletException if a servlet-specific error occurs
	 * @throws IOException if an I/O error occurs
	 */
	protected void cerrarSesion(HttpServletRequest request)
	throws ServletException, IOException {
		//Este for por que las cookies no llegan mapeadas
		if(request.getCookies() != null)
			for (Cookie cookie : request.getCookies()) {
				if (cookie.getName().equals("2")) {
					CtrlAutenticacion.cerrarSesion(request.getParameter("1"), cookie.getValue());
					break;
				}
			}
	}

	/**
	 *
	 * @param request
	 * @param response
	 */
	private void confirmarCifrado(HttpServletRequest request) {
		if(request.getCookies() != null)
			for (Cookie cookie : request.getCookies()) {
				if (cookie.getName().equals("2")) {
					CtrlAutenticacion.confirmarCifrado(request.getParameter("1"), cookie.getValue());
					break;
				}
			}
	}

	/**
	 * Handles the HTTP <code>GET</code> method.
	 *
	 * @param request servlet request
	 * @param response servlet response
	 * @throws ServletException if a servlet-specific error occurs
	 * @throws IOException if an I/O error occurs
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		//DO NOTHING
		response.sendError(401);
		System.out.print("Warning!: acceso por get: " + request);
	}

	/**
	 * Handles the HTTP <code>POST</code> method.
	 *
	 * @param request servlet request
	 * @param response servlet response
	 * @throws ServletException if a servlet-specific error occurs
	 * @throws IOException if an I/O error occurs
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		if (null != request.getParameter("tipo"))
			switch (request.getParameter("tipo")) {
				case "iniciar":{
					iniciarSesion(request, response);
					break;
				} case "cerrar":{
					cerrarSesion(request);
					response.setStatus(200);
					break;
				} case "cc":{
					confirmarCifrado(request);
					response.setStatus(200);
					break;
				} default:
					System.err.print("tipo de request invalido: " + request.getParameter("tipo"));
			}
		else System.err.print("VisAutenticación.doPost: request invalido");
	}

	/**
	 * Returns a short description of the servlet.
	 *
	 * @return a String containing servlet description
	 */
	@Override
	public String getServletInfo() {
		return "Interfaz a la aplicacion cliente para iniciar/terminar seccion";
	}
}
