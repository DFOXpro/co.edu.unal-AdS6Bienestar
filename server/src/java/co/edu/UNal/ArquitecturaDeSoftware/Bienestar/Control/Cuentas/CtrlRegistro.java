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
 * @author dfoxpro
 */
public class CtrlRegistro {

	public static ArrayList crearCuenta(
		String nombre,
		String apellidos,
		String tipoDeDocumento,
		String documento,
		String correo,
		String contrasena,
		char rol
	) {
		String error="";
		if(nombre.length()>100)nombre = nombre.substring(0, 99);
		if(apellidos.length()>100)apellidos = apellidos.substring(0, 99);
		if(tipoDeDocumento.length()!=2)error="tipoD";
		if(documento.length() < 5 || documento.length() > 30)error="documento";
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
	
	public static boolean isValidEmailAddress(String email) {
		String ePattern = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
		java.util.regex.Pattern p = java.util.regex.Pattern.compile(ePattern);
		java.util.regex.Matcher m = p.matcher(email);
		return m.matches();
	}
}
