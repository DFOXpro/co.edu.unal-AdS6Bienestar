/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.UNal.ArquitecturaDeSoftware.Bienestar.Control.Cuentas;

import co.edu.UNal.ArquitecturaDeSoftware.Bienestar.AccesoDatos.DAO.UsuarioDAO;
import co.edu.UNal.ArquitecturaDeSoftware.Bienestar.AccesoDatos.Entity.UsuarioEntity;
import java.util.ArrayList;

/**
 *
 * @author dfoxpro
 */
public class CtrlRegistro {

	public static ArrayList crearCuenta(
		String nombre,
		String apellidos,
		String tipoDeDocumento,
		int documento,
		String correo,
		String contrasena,
		char rol
	) {
		String correo2=correo,nombre2=nombre,apellidos2=apellidos,error="";
		if(nombre.length()>100)nombre2 = nombre.substring(0, 99);
		if(apellidos.length()>100)apellidos2 = apellidos.substring(0, 99);
		if(tipoDeDocumento.length()!=2)error="tipoD";
		if(documento>2147483646*2 || documento <1)error="documento";
		if(contrasena.length()<5 || contrasena.length()>999)error="contraseña";
		if(nombre.isEmpty() || apellidos.isEmpty())error="nombre";
		if(!(rol !='A') && !(rol!='P') && !(rol!='E'))error="rol";
		if(!isValidEmailAddress(correo))error="correo";
		else correo2=correo.toLowerCase();
		
		if(error.isEmpty()){
			UsuarioEntity u = new UsuarioEntity(documento, documento, tipoDeDocumento, nombre2, apellidos2, correo2, contrasena, rol);
			error = UsuarioDAO.put(u);
		}
		
		ArrayList r = new ArrayList();
		if(error.equals("OK")){
			r.add("isExitoso");
		} else if(error.equals("usuario")){
			r.add("error");
			r.add("usuario");
		} else if(error.equals("contraseña")){
			r.add("error");
			r.add("contrasena");
		} else if(error.equals("documento")){
			r.add("error");
			r.add("documento");
		} else if(error.equals("correo")){
			r.add("error");
			r.add("correo");
		} else if(error.equals("nombre")){
			r.add("error");
			r.add("nombre");
		} else if(error.equals("tipoD")){
			r.add("error");
			r.add("tipoDocumento");
		} else if(error.equals("rol")){
			r.add("error");
			r.add("rol");
		}else r.add(error);
		return r;
	}
	
	public static boolean isValidEmailAddress(String email) {
           String ePattern = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
           java.util.regex.Pattern p = java.util.regex.Pattern.compile(ePattern);
           java.util.regex.Matcher m = p.matcher(email);
           return m.matches();
    }
}
