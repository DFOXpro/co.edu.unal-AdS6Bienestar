/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.UNal.ArquitecturaDeSoftware.Bienestar.Vista.Cuenta;

import co.edu.UNal.ArquitecturaDeSoftware.Bienestar.Control.Cuentas.CtrlAdmin;
import co.edu.UNal.ArquitecturaDeSoftware.Bienestar.Vista.Util;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.simple.JSONObject;

/**
 *
 * @author awake
 */
public class VisAdmin extends VisUsuario{
    CtrlAdmin ctrlAdmin = new CtrlAdmin();

    protected void eliminarCuenta(HttpServletRequest request, HttpServletResponse response) throws IOException{
        ArrayList r = CtrlAdmin.eliminarCuenta(Integer.parseInt(request.getParameter("1"))); // id_usuario

        response.setContentType("application/json;charset=UTF-8");
            PrintWriter out = response.getWriter();
            if(r.get(0)=="error"){
                    JSONObject obj=new JSONObject();
                    if(r.get(1)=="usuario"){
                            obj.put("isError",true);
                            obj.put("errorDescrip","El usuario no existe");

                    } else Util.errordeRespuesta(r, out);
                    out.print(obj);
            } else if(r.get(0)=="isExitoso"){
                    JSONObject obj=new JSONObject();
                    obj.put("Exitoso",true);
                    out.print(obj);
            } else Util.errordeRespuesta(r, out);
    }

    protected void crearConvocatoria(HttpServletRequest request, HttpServletResponse response) throws IOException{
        ArrayList r = ctrlAdmin.crearConvocatoria(
            request.getParameter("1"), // nombre
            request.getParameter("2"), // descripción
            request.getParameter("3"), // fin
            Integer.parseInt(request.getParameter("4")) // cupos
        );
         
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
    
    protected void actualizarConvocatoria(HttpServletRequest request, HttpServletResponse response) throws IOException{
        ArrayList r = ctrlAdmin.actualizarConvocatoria(
            Integer.parseInt(request.getParameter("1")), // id
            request.getParameter("2"), // nombre
            request.getParameter("3"), // descripción
            request.getParameter("4"), // fin
            Integer.parseInt(request.getParameter("5")) // cupos
        );
         
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
    
    protected void eliminarConvocatoria(HttpServletRequest request, HttpServletResponse response) throws IOException{
        ArrayList r = ctrlAdmin.eliminarConvocatoria(Integer.parseInt(request.getParameter("1"))); // id
         
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
                case "eliminarCuenta":{
                        eliminarCuenta(request, response);
                        break;
                } case "leerConvocatoria":{
                        leerConvocatoria(request,response);
                        break;
                } case "crearConvocatoria":{
                        crearConvocatoria(request,response);
                        break;
                } case "actualizarConvocatoria":{
                        actualizarConvocatoria(request,response);
                        break;
                } case "eliminarConvocatoria":{
                        eliminarConvocatoria(request,response);
                        break;
                } 
                default:
                        System.err.print("tipo de request invalido: " + request.getParameter("tipo"));
            }
        else System.err.print("VisAdmin.doPost: request inválido");
    }

    /**
     * Returns a short description of the servlet
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
            return "Vista de Cuenta.Admin";
    }
}
