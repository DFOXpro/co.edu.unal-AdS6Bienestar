package co.edu.UNal.ArquitecturaDeSoftware.Bienestar.Vista;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.simple.JSONObject;

/**
 * url /network/autenticacion
 * @author dfoxpro
 */
public class Autenticacion extends HttpServlet {

    /**
     * Processes requests for HTTP <code>GET</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void iniciarSesion(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        
        ArrayList r = co.edu.UNal.ArquitecturaDeSoftware.Bienestar.Control.Autenticacion.autenticar(
                request.getParameter("usuario"),
                request.getParameter("contrasena")
        );

        response.setContentType("text/json;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            if(r.get(0)=="error"){
                if(r.get(1)=="usuario"){
                    JSONObject obj=new JSONObject();
                    obj.put("isError",new Boolean(true));
                    obj.put("errorDescrip","El usuario no está registrado");
                    System.out.print(obj);
                    out.print(obj);
                } else if(r.get(1)=="contrasena"){
                    JSONObject obj=new JSONObject();
                    obj.put("isError",new Boolean(true));
                    obj.put("errorDescrip","La contraseña no coinside");
                    out.print(obj);
                } else errordeRespuesta(r, out);
            } else if(r.get(0)=="exitoso"){
                JSONObject obj=new JSONObject();
                obj.put("nombre",r.get(1));
                obj.put("rol",r.get(2));
                out.print(obj);
            } else errordeRespuesta(r, out);
        } finally {
            out.close();
        }
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
        //@TODO: cerrarSesion
    }
    private void errordeRespuesta(ArrayList r, PrintWriter out){
        JSONObject obj=new JSONObject();
        obj.put("isError",new Boolean(true));
        obj.put("errorDescrip","Error inesperado");
        org.jboss.logging.Logger.getLogger(Autenticacion.class.getName())
        .error("Respuesta inesperada del control !! r.size:"+r.size()+"|r0:"+r.get(0)+"|r1:"+r.get(1));
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
        org.jboss.logging.Logger.getLogger(Autenticacion.class.getName())
        .warn("acceso por post: "+request);
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
        else org.jboss.logging.Logger.getLogger(Autenticacion.class.getName())
        .error("tipo de request invalido: "+request);
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
