/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.UNal.ArquitecturaDeSoftware.Bienestar.Vista.Cuenta;

import co.edu.UNal.ArquitecturaDeSoftware.Bienestar.Control.Cuentas.CtrlRegistro;
import co.edu.UNal.ArquitecturaDeSoftware.Bienestar.Vista.Static;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.simple.JSONObject;

/**
 *
 * @author dfoxpro
 */
@WebServlet(name = "Registro", urlPatterns = {"/network/registro"})
public class VisRegistro extends HttpServlet {

	/**
	 * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
	 * methods.
	 *
	 * @param request servlet request
	 * @param response servlet response
	 * @throws ServletException if a servlet-specific error occurs
	 * @throws IOException if an I/O error occurs
	 */
	protected void crearSesion(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		ArrayList r = CtrlRegistro.crearCuenta(
			request.getParameter("1"),//Nombre
			request.getParameter("2"),//Apellidos
			request.getParameter("3"),//Tipo de documento
			Integer.getInteger(request.getParameter("4")),//documento
			request.getParameter("5"),//Correo= usuario
			request.getParameter("6"),//passworld
			request.getParameter("7").charAt(0)//rol
		);

		response.setContentType("application/json;charset=UTF-8");
		PrintWriter out = response.getWriter();
		if(r.get(0)=="error"){
			JSONObject obj=new JSONObject();
			if(r.get(1)=="usuario"){
				obj.put("isError",true);
				obj.put("errorDescrip","El usuario ya existe");
			} else if(r.get(1)=="contrasena"){
				obj.put("isError",true); 
				obj.put("errorDescrip","La contraseña es invalida");
			} else if(r.get(1)=="documento"){
				obj.put("isError",true);
				obj.put("errorDescrip","El documento es invalido");
			} else if(r.get(1)=="tipoDocumento"){
				obj.put("isError",true);
				obj.put("errorDescrip","El tipo de documento es invalido");
			} else if(r.get(1)=="correo"){
				obj.put("isError",true);
				obj.put("errorDescrip","El correo escrito no es valido");
			} else if(r.get(1)=="nombre"){
				obj.put("isError",true);
				obj.put("errorDescrip","Los nombres o apellidos son incorrectos");
			} else if(r.get(1)=="rol"){
				obj.put("isError",true);
				obj.put("errorDescrip","El rol es invalido, los posibles valores son: E, P y A");
			} else Static.errordeRespuesta(r, out);
			out.print(obj);
		} else if(r.get(0)=="isExitoso"){
			JSONObject obj=new JSONObject();
			obj.put("pagina",Static.HOME);
			out.print(obj);
		} else Static.errordeRespuesta(r, out);
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
        response.sendRedirect(Static.PAGINA_403_NO_DISPONIBLE);
        System.out.print("Warning!: acceso por get: "+request);
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
		if("crear".equals(request.getParameter("tipo"))) crearSesion(request, response);
	}

	/**
	 * Returns a short description of the servlet.
	 *
	 * @return a String containing servlet description
	 */
	@Override
	public String getServletInfo() {
		return "Vista de Cuenta.Registro";
	}
}
