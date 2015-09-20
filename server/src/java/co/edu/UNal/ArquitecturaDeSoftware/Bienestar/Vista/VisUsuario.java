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
                obj.put("descripcion", e.getDescripcion());
                obj.put("cupos", e.getCupos());
                obj.put("costo", e.getCosto());
                obj.put("fechaFin", ""+e.getFechaFinRegistro());
                obj.put("fechaInicio", ""+e.getFechaInicio());
                
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
    
    protected void obtenerTotalConvocatorias(HttpServletRequest request, HttpServletResponse response) throws IOException{
        int numC = cU.obtenerTotalConvocatorias();
        response.setContentType("application/json;charset=UTF-8");
        PrintWriter out = response.getWriter();
        JSONObject obj = new JSONObject();
        obj.put("total", numC);
        out.print(obj);
    }    
    
    protected void obtenerInscritosConv(HttpServletRequest request, HttpServletResponse response) throws IOException{
        int numC = cU.obtenerInscritosConv(Integer.parseInt(request.getParameter("1")));
        response.setContentType("application/json;charset=UTF-8");
        PrintWriter out = response.getWriter();
        JSONObject obj = new JSONObject();
        obj.put("inscritos", numC);
        out.print(obj);
    }
    
    protected void leerMultiplesTalleres(HttpServletRequest request, HttpServletResponse response) throws IOException{
        ArrayList<TallerEntity> talleres = new ArrayList<>();
        talleres = cU.leerMultiplesTalleres(Integer.parseInt(request.getParameter("1")), Integer.parseInt(request.getParameter("2"))); // tamaño y posición

        response.setContentType("application/json;charset=UTF-8");
        PrintWriter out = response.getWriter();
		System.out.println("Talleres: "+talleres.size());
        JSONArray list1 = new JSONArray();
		for (TallerEntity taller : talleres) {
			JSONObject obj = new JSONObject();
			obj.put("id", taller.getIdTaller());
			obj.put("titulo", taller.getNombre());
			list1.add(obj);
		}
        out.print(list1);
    }
    
    protected void obtenerTotalTalleres(HttpServletRequest request, HttpServletResponse response) throws IOException{
        int numC = cU.obtenerTotalTalleres();
        response.setContentType("application/json;charset=UTF-8");
        PrintWriter out = response.getWriter();
        JSONObject obj = new JSONObject();
        obj.put("total", numC);
        out.print(obj);
    }
    
    protected void obtenerInscritosTaller(HttpServletRequest request, HttpServletResponse response) throws IOException{
        int numC = cU.obtenerInscritosTaller(Integer.parseInt(request.getParameter("1")));
        response.setContentType("application/json;charset=UTF-8");
        PrintWriter out = response.getWriter();
        JSONObject obj = new JSONObject();
        obj.put("inscritos", numC);
        out.print(obj);
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
            
    protected void registrarATallerDocenteByDoc(HttpServletRequest request, HttpServletResponse response) throws IOException{
        ArrayList r = CtrlUsuario.registrarATallerDocenteByDoc(Integer.parseInt(request.getParameter("1")), Integer.parseInt(request.getParameter("2"))); // parameter 1: documentoDocente param2: idTaller
         
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
    protected void doGet(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
        //DO NOTHING
        System.out.print("Warning!: acceso por get: "+request);
        response.sendError(401);
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
        if (null != request.getParameter("tipo"))
            switch (request.getParameter("tipo")) {
                case "convocatoria":{
                        leerConvocatoria(request, response);
                        break;
                }case "taller":{
                        leerTaller(request, response);
                        break;
                }case "convocatorias":{
                        leerMultiplesConvocatorias(request, response);
                        break;
                }case "numConvocatorias":{
                        obtenerTotalConvocatorias(request, response);
                        break;
                }case "inscritosConvocatoria":{
                        obtenerInscritosConv(request, response);
                        break;
                }case "talleres":{
                        leerMultiplesTalleres(request, response);
                        break;
                } case "numTalleres":{
                        obtenerTotalTalleres(request,response);
                        break;
                } case "inscritosTaller":{
                        obtenerInscritosTaller(request,response);
                        break;
                } case "regUsuarioTaller":{
                        registrarUsuarioTaller(request,response);
                        break;
                } case "regUsuarioConv":{
                        registrarUsuarioConvocatoria(request,response);
                        break;
                } case "regDocente":{
                        registrarDocenteTaller(request, response);
                        break;
                } case "regDocenteDocumento":{
                        registrarATallerDocenteByDoc(request, response);
                        break;
                } case "eliminarUsuarioTaller":{
                        quitarUsuarioTaller(request, response);
                        break;
                } case "eleminarUsuarioConv":{
                        quitarUsuarioConvocatoria(request, response);
                        break;
                }
                default:
                        System.err.print("tipo de request invalido: " + request.getParameter("tipo"));
            }
        else System.err.print("VisUsuario.doPost: request inválido");
    }
    
    @Override
    public String getServletInfo() {
            return "Vista de Cuenta.Usuario";
    }
}
