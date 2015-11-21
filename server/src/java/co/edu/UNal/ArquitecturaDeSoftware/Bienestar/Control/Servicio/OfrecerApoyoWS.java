/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.UNal.ArquitecturaDeSoftware.Bienestar.Control.Servicio;

import co.edu.UNal.ArquitecturaDeSoftware.Bienestar.AccesoDatos.Entity.UsuarioEntity;
import co.edu.UNal.ArquitecturaDeSoftware.Bienestar.Control.Admin.CtrlAdmin;
import java.util.ArrayList;

import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;

/**
 *
 * @author mysqluser
 */
@WebService(serviceName = "OfrecerApoyoWS")
public class OfrecerApoyoWS {

    /**
     * This is a sample web service operation
     * @param id
     * @return 
     */
    @WebMethod(operationName = "ofrecerApoyo")
    public ROB ofrecerApoyo(@WebParam(name = "correo") String correo) {   
        return CtrlAdmin.verificarUsuarioConvocatoriaViajes(correo);
    }
}
