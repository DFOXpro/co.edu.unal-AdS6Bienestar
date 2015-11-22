/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.UNal.ArquitecturaDeSoftware.Bienestar.Control.Cuentas.Util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author dfoxpro
 */
public class Sesion {
    private String llaveCliente;
    private final Llaves llavesServer;
    private final String cokieHashCode;
    private final Date finDeSesion;

    public Sesion(String cokieHashCode) {
        //El ID es administrado por el mapa de sesiones en Cuentas/Activas.java
        this.llavesServer = Cifrado.generarLlaves();
        this.cokieHashCode = cokieHashCode;//Codigo generado por el cliente
        this.finDeSesion = generarFindeSesion();
    };

    private Date generarFindeSesion() {
        Calendar c = Calendar.getInstance();
        c.setTime(new Date());
        c.add(Calendar.DATE, 30);//Fecha actual + 30 días
        return c.getTime();
    }

	public void setLlaveCliente(String llaveCliente) {
		this.llaveCliente = llaveCliente;
	}


//GETs
    public String getLlaveCliente() {
        return llaveCliente;
    }

    public String getCokieHashCode() {
        return cokieHashCode;
    }

    public Date getFinDeSesion() {
        return finDeSesion;
    }

    public Llaves getLlavesServer() {
        return llavesServer;
    }

    @Override
    public String toString() {
        return
            "Sesion{" +
            "llaveCliente=" + llaveCliente +
            ", llavesServer=" + llavesServer +
            ", cokieHashCode=" + cokieHashCode +
            ", finDeSesion=" + new SimpleDateFormat("HH:mm dd/MM/yyyy").format(finDeSesion) + '}';
    }
}
