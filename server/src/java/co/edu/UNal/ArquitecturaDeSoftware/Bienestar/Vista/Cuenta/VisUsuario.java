/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.UNal.ArquitecturaDeSoftware.Bienestar.Vista.Cuenta;

import co.edu.UNal.ArquitecturaDeSoftware.Bienestar.AccesoDatos.Entity.ConvocatoriaEntity;
import co.edu.UNal.ArquitecturaDeSoftware.Bienestar.Control.Cuentas.CtrlUsuario;
import co.edu.UNal.ArquitecturaDeSoftware.Bienestar.Vista.Util;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.simple.JSONObject;

/**
 *
 * @author awake
 */
public class VisUsuario extends HttpServlet {
    
    protected void leerConvocatoria(HttpServletRequest request, HttpServletResponse response) throws IOException{
        CtrlUsuario cU = new CtrlUsuario();
        ConvocatoriaEntity e = cU.leerConvocatoria(request.getParameter("1")); // id de la convocatoria
        
        response.setContentType("application/json;charset=UTF-8");
        PrintWriter out = response.getWriter();
        if (e.getNombre().isEmpty()) {
                        JSONObject obj = new JSONObject();
                        obj.put("isError", true);
                        obj.put("errorDescrip", "La convocatoria no existe");
                        out.print(obj);
        } else  {
                JSONObject obj = new JSONObject();
                obj.put("id", e.getIdConvocatoria());
                obj.put("nombre", e.getNombre());
                obj.put("descrip", e.getDescripcion());
                obj.put("cupos", e.getCupos());
                obj.put("fin", e.getFechaFinRegistro());
                
                out.print(obj);
        }
    }
    
    
    
    
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        //DO NOTHING
        System.out.print("Warning!: acceso por get: "+request);
        response.sendError(401);
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
                    throws ServletException, IOException {
            if("leerConvocatoria".equals(request.getParameter("tipo"))) leerConvocatoria(request, response);
    }
    
    @Override
    public String getServletInfo() {
            return "Vista de Cuenta.Usuario";
    }
}
