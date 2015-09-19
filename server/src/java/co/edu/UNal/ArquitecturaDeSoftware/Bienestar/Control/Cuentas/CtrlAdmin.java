package co.edu.UNal.ArquitecturaDeSoftware.Bienestar.Control.Cuentas;

import co.edu.UNal.ArquitecturaDeSoftware.Bienestar.AccesoDatos.DAO.TallerDAO;
import co.edu.UNal.ArquitecturaDeSoftware.Bienestar.AccesoDatos.DAO.UsuarioDAO;
import co.edu.UNal.ArquitecturaDeSoftware.Bienestar.AccesoDatos.Entity.ConvocatoriaEntity;
import co.edu.UNal.ArquitecturaDeSoftware.Bienestar.AccesoDatos.Entity.TallerEntity;
import co.edu.UNal.ArquitecturaDeSoftware.Bienestar.AccesoDatos.Entity.UsuarioEntity;
import static co.edu.UNal.ArquitecturaDeSoftware.Bienestar.Control.Cuentas.CtrlRegistro.isValidEmailAddress;
import java.util.ArrayList;

/**
 *
 * @author awake
 */
public class CtrlAdmin extends CtrlUsuario{
    UsuarioDAO usr = new UsuarioDAO();
    
    public static ArrayList crearUsuario(String nombre, String apellidos, String tipoDeDocumento, int documento, String correo, String contrasena, char rol){
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
    
    public static ArrayList editarUsuario(int idUsuario, String nombre, String apellidos, String tipoDeDocumento, int documento, String correo, String contrasena, char rol){
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

    public UsuarioEntity leerUsuario(String username){
        return usr.getByUsername(username);
    }

    public UsuarioEntity leerUsuarioId(int idUsuario){
        return usr.getById(idUsuario);
    }

    public ArrayList<UsuarioEntity> leerMultiplesUsuarios(int tamano, int posicion) {
        return usr.getUsuarios(posicion, tamano);
    };

    public int obtenerTotalUsuarios() {
        return usr.getTotalUsuarios();
    }
    
    public static ArrayList eliminarUsuario(int ID_USUARIO){
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
    
    
    public ArrayList crearConvocatoria(String nombre, String descripcion, String fin, int cupos){
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
      
        
    public ArrayList crearTaller(char t, String nombre, String descripcion, String finR, String inicio, String fin, int costo, int cupos){
        String error = TallerDAO.create(t, nombre, descripcion, finR, inicio, fin, costo, cupos);
        ArrayList r = new ArrayList();
        if(error.equals("OK")){
            r.add("isExitoso");
        } else {
            r.add("error");
            r.add(error);
        }
        return r;
    }
    
    public ArrayList actualizarTaller(int ID_TALLER, char TIPO_TALLER, String NOMBRE, String DESCRIPCION, String FECHA_FIN_REGISTRO, String FECHA_INICIO, String FECHA_FIN, int COSTO, int CUPOS){
        String error = TallerDAO.update(ID_TALLER, TIPO_TALLER, NOMBRE, DESCRIPCION, FECHA_FIN_REGISTRO, FECHA_INICIO, FECHA_FIN, COSTO, CUPOS);
        ArrayList r = new ArrayList();
        if(error.equals("OK")){
            r.add("isExitoso");
        } else {
            r.add("error");
            r.add(error);
        }
        return r;
    }
    
    public ArrayList eliminarTaller(int id){
        String error = TallerDAO.delete(id);
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



