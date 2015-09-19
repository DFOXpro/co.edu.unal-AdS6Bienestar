package co.edu.UNal.ArquitecturaDeSoftware.Bienestar.Vista;

import co.edu.UNal.ArquitecturaDeSoftware.Bienestar.AccesoDatos.Entity.ConvocatoriaEntity;
import co.edu.UNal.ArquitecturaDeSoftware.Bienestar.AccesoDatos.Entity.TallerEntity;
import co.edu.UNal.ArquitecturaDeSoftware.Bienestar.Control.Cuentas.CtrlUsuario;
import co.edu.UNal.ArquitecturaDeSoftware.Bienestar.Vista.Util;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.simple.JSONArray;
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
    
    protected void leerTaller(HttpServletRequest request, HttpServletResponse response) throws IOException{
        
        TallerEntity e = cU.leerTaller(request.getParameter("1")); // id de la taller
        
        response.setContentType("application/json;charset=UTF-8");
        PrintWriter out = response.getWriter();
        if (e.getNombre().isEmpty()) {
                        JSONObject obj = new JSONObject();
                        obj.put("isError", true);
                        obj.put("errorDescrip", "La convocatoria no existe");
                        out.print(obj);
        } else  {
                JSONObject obj = new JSONObject();
                obj.put("id", e.getIdTaller());
                obj.put("nombre", e.getNombre());
                obj.put("descrip", e.getDescripcion());
                obj.put("cupos", e.getCupos());
                obj.put("fin", e.getFechaFinRegistro());
                
                out.print(obj);
        }
    }
        
    protected void leerMultiplesConvocatorias(HttpServletRequest request, HttpServletResponse response) throws IOException{
        ArrayList<ConvocatoriaEntity> convocatorias = new ArrayList<>();
        convocatorias = cU.leerMultiplesConvocatorias(Integer.parseInt(request.getParameter("1")), Integer.parseInt(request.getParameter("2"))); // tamaño y posición
        
        response.setContentType("application/json;charset=UTF-8");
        PrintWriter out = response.getWriter();
        
        JSONArray list1 = new JSONArray();
        for(int i = 0; i < convocatorias.size(); i++)
        {
            JSONObject obj = new JSONObject();
            obj.put("id", convocatorias.get(i).getIdConvocatoria());
            obj.put("titulo", convocatorias.get(i).getNombre());
            list1.add(obj);
        }
        out.print(list1);
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
        ArrayList r = CtrlUsuario.registrarATallerDocente(Integer.parseInt(request.getParameter("1")), Integer.parseInt(request.getParameter("2"))); // parameter 1: idUsuarioDocente param2: idTaller
         
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
