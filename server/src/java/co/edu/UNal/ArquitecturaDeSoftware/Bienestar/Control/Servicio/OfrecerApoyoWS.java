/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.UNal.ArquitecturaDeSoftware.Bienestar.Control.Servicio;

import co.edu.UNal.ArquitecturaDeSoftware.Bienestar.AccesoDatos.Entity.ConvocatoriaEntity;
import co.edu.UNal.ArquitecturaDeSoftware.Bienestar.Control.Usuario.CtrlUsuario;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;

/**
 *
 * @author mysqluser
 */
@WebService(serviceName = "OfrecerApoyo")
public class OfrecerApoyoWS {

    /**
     * This is a sample web service operation
     */
//    @WebMethod(operationName = "hello")
//    public String hello(@WebParam(name = "name") String txt) {
//        return "Hello " + txt + " !";
//    }
    @WebMethod(operationName = "ofrecerApoyo")
    public ConvocatoriaEntity ofrecerApoyo(String id) {   
        CtrlUsuario ctrlUsuario = new CtrlUsuario();
        
        return ctrlUsuario.leerConvocatoria(id);
    }
    
//        @WebMethod(operationName = "ofrecerApoyoMultiple")
//    public ConvocatoriaEntity ofrecerApoyoMultiple(String id) {   
//        CtrlUsuario ctrlUsuario = new CtrlUsuario();
//        
//        return ctrlUsuario.
//    }
}
