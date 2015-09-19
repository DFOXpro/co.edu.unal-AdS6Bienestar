/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.UNal.ArquitecturaDeSoftware.Bienestar.Vista;

import co.edu.UNal.ArquitecturaDeSoftware.Bienestar.AccesoDatos.Entity.ConvocatoriaEntity;
import co.edu.UNal.ArquitecturaDeSoftware.Bienestar.Control.Cuentas.CtrlUsuario;
import co.edu.UNal.ArquitecturaDeSoftware.Bienestar.Vista.Util;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
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
    CtrlUsuario cU = new CtrlUsuario();
    protected void leerConvocatoria(HttpServletRequest request, HttpServletResponse response) throws IOException{
        
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
    
    protected void registrarUsuarioTaller(HttpServletRequest request, HttpServletResponse response) throws IOException{
        ArrayList r = cU.registrarATallerUsuario(Integer.parseInt(request.getParameter("1")), Integer.parseInt(request.getParameter("2"))); // parameter 1: idUsuario param2: idTaller
         
        response.setContentType("application/json;charset=UTF-8");
        PrintWriter out = response.getWriter();
        if(r.get(0)=="error"){
                JSONObject obj=new JSONObject();
                obj.put("isError",true);
                obj.put("errorDescrip",r.get(1));
                out.print(obj);
        } else if(r.get(0)=="isExitoso"){
                JSONObject obj=new JSONObject();
                obj.put("Exitoso",true);
                out.print(obj);
        } else Util.errordeRespuesta(r, out);  
    }
    
    protected void registrarUsuarioConvocatoria(HttpServletRequest request, HttpServletResponse response) throws IOException{
        ArrayList r = cU.registrarAConvocatoriaUsuario(Integer.parseInt(request.getParameter("1")), Integer.parseInt(request.getParameter("2"))); // parameter 1: idUsuario param2: idConv
         
        response.setContentType("application/json;charset=UTF-8");
        PrintWriter out = response.getWriter();
        if(r.get(0)=="error"){
                JSONObject obj=new JSONObject();
                obj.put("isError",true);
                obj.put("errorDescrip",r.get(1));
                out.print(obj);
        } else if(r.get(0)=="isExitoso"){
                JSONObject obj=new JSONObject();
                obj.put("Exitoso",true);
                out.print(obj);
        } else Util.errordeRespuesta(r, out);  
    }
    
        protected void registrarDocenteTaller(HttpServletRequest request, HttpServletResponse response) throws IOException{
        ArrayList r = cU.registrarATallerDocente(Integer.parseInt(request.getParameter("1")), Integer.parseInt(request.getParameter("2"))); // parameter 1: idUsuarioDocente param2: idTaller
         
        response.setContentType("application/json;charset=UTF-8");
        PrintWriter out = response.getWriter();
        if(r.get(0)=="error"){
                JSONObject obj=new JSONObject();
                obj.put("isError",true);
                obj.put("errorDescrip",r.get(1));
                out.print(obj);
        } else if(r.get(0)=="isExitoso"){
                JSONObject obj=new JSONObject();
                obj.put("Exitoso",true);
                out.print(obj);
        } else Util.errordeRespuesta(r, out);  
    }
        
    protected void quitarDocenteTaller(HttpServletRequest request, HttpServletResponse response) throws IOException{
        ArrayList r = cU.abandonarTallerDocente(Integer.parseInt(request.getParameter("1")), Integer.parseInt(request.getParameter("2"))); //1. idUsuario, 2. idEvento
         
        response.setContentType("application/json;charset=UTF-8");
        PrintWriter out = response.getWriter();
        if(r.get(0)=="error"){
                JSONObject obj=new JSONObject();
                obj.put("isError",true);
                obj.put("errorDescrip",r.get(1));
                out.print(obj);
        } else if(r.get(0)=="isExitoso"){
                JSONObject obj=new JSONObject();
                obj.put("Exitoso",true);
                out.print(obj);
        } else Util.errordeRespuesta(r, out);  
    }
    
    protected void quitarUsuarioTaller(HttpServletRequest request, HttpServletResponse response) throws IOException{
        ArrayList r = cU.abandonarTallerUsuario(Integer.parseInt(request.getParameter("1")), Integer.parseInt(request.getParameter("2"))); //1. idUsuario, 2. idEvento
         
        response.setContentType("application/json;charset=UTF-8");
        PrintWriter out = response.getWriter();
        if(r.get(0)=="error"){
                JSONObject obj=new JSONObject();
                obj.put("isError",true);
                obj.put("errorDescrip",r.get(1));
                out.print(obj);
        } else if(r.get(0)=="isExitoso"){
                JSONObject obj=new JSONObject();
                obj.put("Exitoso",true);
                out.print(obj);
        } else Util.errordeRespuesta(r, out);  
    }
    
        protected void quitarUsuarioConvocatoria(HttpServletRequest request, HttpServletResponse response) throws IOException{
        ArrayList r = cU.abandonarConvocatoria(Integer.parseInt(request.getParameter("1")), Integer.parseInt(request.getParameter("2"))); //1. idUsuario, 2. idEvento
         
        response.setContentType("application/json;charset=UTF-8");
        PrintWriter out = response.getWriter();
        if(r.get(0)=="error"){
                JSONObject obj=new JSONObject();
                obj.put("isError",true);
                obj.put("errorDescrip",r.get(1));
                out.print(obj);
        } else if(r.get(0)=="isExitoso"){
                JSONObject obj=new JSONObject();
                obj.put("Exitoso",true);
                out.print(obj);
        } else Util.errordeRespuesta(r, out);  
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
