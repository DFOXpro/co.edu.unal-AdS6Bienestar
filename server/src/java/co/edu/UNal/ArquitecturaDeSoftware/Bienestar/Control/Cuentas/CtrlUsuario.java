/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.UNal.ArquitecturaDeSoftware.Bienestar.Control.Cuentas;

import co.edu.UNal.ArquitecturaDeSoftware.Bienestar.AccesoDatos.DAO.*;
import co.edu.UNal.ArquitecturaDeSoftware.Bienestar.AccesoDatos.Entity.*;
import java.util.ArrayList;

/**
 *
 * @author awake
 */
public class CtrlUsuario {
    UsuarioDAO usr = new UsuarioDAO();
    ConvocatoriaDAO conv = new ConvocatoriaDAO();
    TallerDAO tall = new TallerDAO();
    
    public ConvocatoriaEntity leerConvocatoria(String id){
        return conv.getByID(id);
    }
    
    public TallerEntity leerTaller(String id){
    return tall.getByID(id);
    }
        
    public UsuarioEntity leerUsuario(String username){
        return usr.getByUsername(username);
    }    
    
    public UsuarioEntity leerUsuarioId(int idUsuario){
        return usr.getById(idUsuario);
    }    
    
    public ArrayList<UsuarioEntity> leerMultiplesUsuarios(int tamano, int posicion){
        return usr.getUsuarios(posicion, tamano);
    }   
    public int obtenerTotalUsuarios(){
        return usr.getTotalUsuarios();
    }   
}
