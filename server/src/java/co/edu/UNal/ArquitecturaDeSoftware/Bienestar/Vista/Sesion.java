package co.edu.UNal.ArquitecturaDeSoftware.Bienestar.Vista;

import co.edu.UNal.ArquitecturaDeSoftware.Bienestar.Control.Cuentas.Autenticacion;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.simple.JSONObject;
/**
 * url /network/sesion
 * @author dfoxpro
 */
public class Sesion extends HttpServlet {

    /**
     * Processes requests for HTTP <code>GET</code>
     * methods.
     *
	 * @param request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void iniciarSesion(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
		ArrayList r = Autenticacion.autenticar(
			request.getParameter("1"),
			request.getParameter("2"),
			request.getParameter("3"),
			request.getParameter("4")
		);

		response.setContentType("application/json;charset=UTF-8");
		PrintWriter out = response.getWriter();
		if(r.get(0)=="error"){
			if(r.get(1)=="usuario"){
				JSONObject obj=new JSONObject();
				obj.put("isError",true);
				obj.put("errorDescrip","El usuario no está registrado");
				out.print(obj);
			} else if(r.get(1)=="contrasena"){
				JSONObject obj=new JSONObject();
				obj.put("isError",true);
				obj.put("errorDescrip","La contraseña no coinside");
				out.print(obj);
			} else errordeRespuesta(r, out);
		} else if(r.get(0)=="exitoso"){
			JSONObject obj=new JSONObject();
			obj.put("nombre",r.get(1));
			obj.put("pagina",""+r.get(2));
			obj.put("llpbSer",""+r.get(3));
			out.print(obj);
		} else errordeRespuesta(r, out);
    }

    /**
     *
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void cerrarSesion(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        //@TODO: cerrarSesion en cliente
        Autenticacion.cerrarSesion(""+request.getParameter("usuario"),""+request.getParameter("chc"));
        response.sendRedirect(Static.HOME);
    }
    private void errordeRespuesta(ArrayList r, PrintWriter out){
        JSONObject obj=new JSONObject();
        obj.put("isError",true);
        obj.put("errorDescrip","Error inesperado");
        System.err.print("Respuesta inesperada del control !! r.size:"+r.size()+"|r0:"+r.get(0)+"|r1:"+r.get(1));
        out.print(obj);
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
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
		if("iniciar".equals(request.getParameter("tipo"))) iniciarSesion(request, response);
		else if("cerrar".equals(request.getParameter("tipo"))) cerrarSesion(request, response);
		else System.err.print("tipo de request invalido: "+request.getParameter("tipo"));
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Interfaz a la aplicacion cliente para iniciar/terminar seccion";
    }// </editor-fold>

}
