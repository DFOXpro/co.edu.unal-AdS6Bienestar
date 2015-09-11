/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.UNal.ArquitecturaDeSoftware.Bienestar.Vista;

import java.io.PrintWriter;
import java.util.ArrayList;
import org.json.simple.JSONObject;

/**
 *
 * @author dfoxpro
 */
public class Static {
    public static String PAGINA_403_NO_DISPONIBLE ="/401.html";
    public static String PAGINA_404_NO_EXISTE ="/404.html";
    public static String PAGINA_500_ERROR_DEL_SERVIDOR ="/500.html";
    public static String HOME = "/index.html";

    public static void errordeRespuesta(ArrayList r, PrintWriter out){
        System.err.print("Respuesta inesperada del control !! r.size:"+r.size()+"|r0:"+r.get(0)+"|r1:"+r.get(1));
        JSONObject obj=new JSONObject();
        obj.put("isError",true);
        obj.put("errorDescrip","Error inesperado");
        out.print(obj);
    }
}
