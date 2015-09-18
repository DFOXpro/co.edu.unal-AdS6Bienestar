/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.UNal.ArquitecturaDeSoftware.Bienestar.Control.Cuentas;

import co.edu.UNal.ArquitecturaDeSoftware.Bienestar.AccesoDatos.DAO.UsuarioDAO;
import java.util.ArrayList;

/**
 *
 * @author awake
 */
public class CtrlAdmin extends CtrlUsuario{
    
    
    public static ArrayList eliminarCuenta(int ID_USUARIO){
        String error = UsuarioDAO.delete(ID_USUARIO);
        ArrayList r = new ArrayList();
        if(error.equals("OK")){
			r.add("isExitoso");
		} else {
			r.add("error");
			r.add(error);
		}
        return r;
    }
    
    public ArrayList crearConvocatoria(String nombre, String descripcion, String fin, int cupos)
    {
        String error = conv.create(nombre, descripcion, fin, cupos);
        ArrayList r = new ArrayList();
        if(error.equals("OK")){
            r.add("isExitoso");
        } else {
            r.add("error");
            r.add(error);
        }
        return r;
    }
    
    public ArrayList actualizarConvocatoria(int id, String nombre, String descripcion, String fin, int cupos){
        String error = conv.update(id, nombre, descripcion, fin, cupos);
        ArrayList r = new ArrayList();
        if(error.equals("OK")){
            r.add("isExitoso");
        } else {
            r.add("error");
            r.add(error);
        }
        return r;
    }
    
    public ArrayList eliminarConvocatoria(int id){
        String error = conv.delete(id);
        ArrayList r = new ArrayList();
        if(error.equals("OK")){
            r.add("isExitoso");
        } else {
            r.add("error");
            r.add(error);
        }
        return r;
    }
    
}
