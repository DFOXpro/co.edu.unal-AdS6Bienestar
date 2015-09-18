/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.UNal.ArquitecturaDeSoftware.Bienestar.Control.Cuentas;

import co.edu.UNal.ArquitecturaDeSoftware.Bienestar.AccesoDatos.DAO.ConvocatoriaDAO;
import co.edu.UNal.ArquitecturaDeSoftware.Bienestar.AccesoDatos.Entity.ConvocatoriaEntity;

/**
 *
 * @author awake
 */
public class CtrlUsuario {
    ConvocatoriaDAO conv = new ConvocatoriaDAO();
    
    public ConvocatoriaEntity leerConvocatoria(String id){
        return conv.getByID(id);
    }
}
