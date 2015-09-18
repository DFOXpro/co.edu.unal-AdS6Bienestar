/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.UNal.ArquitecturaDeSoftware.Bienestar.Control.Cuentas;

import co.edu.UNal.ArquitecturaDeSoftware.Bienestar.AccesoDatos.DAO.UsuarioDAO;
import static co.edu.UNal.ArquitecturaDeSoftware.Bienestar.Control.Cuentas.CtrlRegistro.isValidEmailAddress;
import java.util.ArrayList;

/**
 *
 * @author awake
 */
public class CtrlAdmin extends CtrlUsuario{

    public static ArrayList crearUsuario(
                String nombre,
		String apellidos,
		String tipoDeDocumento,
		int documento,
		String correo,
		String contrasena,
		char rol)
    {
		String error="";
		if(nombre.length()>100)nombre = nombre.substring(0, 99);
		if(apellidos.length()>100)apellidos = apellidos.substring(0, 99);
		if(tipoDeDocumento.length()!=2)error="tipoD";
		if(documento>Integer.MAX_VALUE || documento <1)error="documento";
		if(contrasena.length()<5 || contrasena.length()>999)error="contraseña";
		if(nombre.isEmpty() || apellidos.isEmpty())error="nombre";
		if(!(rol =='A' || rol=='P' || rol=='E'))error="rol";
		if(!isValidEmailAddress(correo))error="correo1";
		else correo=correo.toLowerCase();
		
		if(error.isEmpty())
			error = UsuarioDAO.create(documento, tipoDeDocumento, nombre, apellidos, correo, contrasena, rol);
		
		ArrayList r = new ArrayList();
		if(error.equals("OK")){
			r.add("isExitoso");
		} else {
			r.add("error");
			r.add(error);
		}
		return r;
    }
    
        public static ArrayList editarUsuario(
                int idUsuario,
                String nombre,
		String apellidos,
		String tipoDeDocumento,
		int documento,
		String correo,
		String contrasena,
		char rol)
    {
		String error="";
		if(nombre.length()>100)nombre = nombre.substring(0, 99);
		if(apellidos.length()>100)apellidos = apellidos.substring(0, 99);
		if(tipoDeDocumento.length()!=2)error="tipoD";
		if(documento>Integer.MAX_VALUE || documento <1)error="documento";
		if(contrasena.length()<5 || contrasena.length()>999)error="contraseña";
		if(nombre.isEmpty() || apellidos.isEmpty())error="nombre";
		if(!(rol =='A' || rol=='P' || rol=='E'))error="rol";
		if(!isValidEmailAddress(correo))error="correo1";
		else correo=correo.toLowerCase();
		
		if(error.isEmpty())
			error = UsuarioDAO.update(idUsuario, documento, tipoDeDocumento, nombre, apellidos, correo, contrasena, rol);
		
		ArrayList r = new ArrayList();
		if(error.equals("OK")){
			r.add("isExitoso");
		} else {
			r.add("error");
			r.add(error);
		}
		return r;
    }
    
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
    
        public static ArrayList mostrarUsuario(int ID_USUARIO){
        String error = UsuarioDAO.getById(ID_USUARIO).toString();
        ArrayList r = new ArrayList();
        if(error.equals("OK")){
			r.add("isExitoso");
		} else {
			r.add("error");
			r.add(error);
		}
        return r;
    }
        
    public static ArrayList mostrarUsuariosMultiples(int posicion, int tamano){
        String error = UsuarioDAO.getUsuarios(tamano, posicion).toString();
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
