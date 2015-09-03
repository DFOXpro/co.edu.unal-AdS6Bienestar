/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.UNal.ArquitecturaDeSoftware.Bienestar.Control.Cuentas;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author dfoxpro
 */
public class Sesion {
    private final String llaveCliente;
    private final int[] llavesServer;
    private final String cokieHashCode;
    private final Date finDeSesion;

    public Sesion(String llaveCliente, String cokieHashCode) {
        //El ID es administrado por el mapa de sesiones en Cuentas/Activas.java
        this.llaveCliente = llaveCliente;
        this.llavesServer = Cifrado.generarLlaves();
        this.cokieHashCode = cokieHashCode;//Codigo generado por el cliente
        this.finDeSesion = generarFindeSesion();
    }

    public String getLlaveCliente() {
        return llaveCliente;
    }

    public int[] getLlavesServer() {
        return llavesServer;
    }

    public String getCokieHashCode() {
        return cokieHashCode;
    }

    public Date getFinDeSesion() {
        return finDeSesion;
    }

    @Override
    public String toString() {
        return 
            "Sesion{" +
            "llaveCliente=" + llaveCliente +
            ", llavesServer=[" + llavesServer[0]+","+llavesServer[1] +
            "], cokieHashCode=" + cokieHashCode +
            ", finDeSesion=" + new SimpleDateFormat("HH:mm dd/MM/yyyy").format(finDeSesion) + '}';
    }

    private Date generarFindeSesion() {
        Calendar c = Calendar.getInstance();
        c.setTime(new Date());
        c.add(Calendar.DATE, 30);//Fecha actual + 30 días
        return c.getTime();
    }
}
