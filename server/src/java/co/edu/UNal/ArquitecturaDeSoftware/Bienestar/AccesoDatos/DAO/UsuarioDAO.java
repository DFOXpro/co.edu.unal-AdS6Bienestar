/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.UNal.ArquitecturaDeSoftware.Bienestar.AccesoDatos.DAO;

import co.edu.UNal.ArquitecturaDeSoftware.Bienestar.AccesoDatos.Entity.UsuarioEntity;
import com.sun.jmx.snmp.daemon.SnmpInformRequest;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author snipercat
 */
public class UsuarioDAO extends CrudDAO<UsuarioEntity> {

	public static String put(UsuarioEntity u) { // RETORNAR OK SI EL REGISTRO EN LA BD ES CORRECTO, SI NO DEVOLVER EL TIPO DE ERROR.
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
	}

    public UsuarioDAO() {
    }

    /**
     * Returns a value object that corresponds to the user whose username and
     * password are like the specified ones
     *
     * @param username String containing the username
     * @return Value object with required user information
     */
    public UsuarioEntity getByUsername(String username) {
        ResultSet rs = super.query("SELECT * FROM USUARIO WHERE LOWER(EMAIL) =LOWER(?)",new String[]{username});
        try {
            rs.first();
            UsuarioEntity ue= toEntity(rs);
            
            //this.create(1234, "CC", "probar", "crear", "crear@unal.edu.co", "clave1", 'A');
            //this.update(12, 1234, "CC", "GATO", "FELIX", "crear@unal.edu.co", "clave1", 'A');
            //this.delete(12);
            
            return ue;
            /*return new UsuarioEntity(
                rs.getInt("ID_USUARIO"),
                rs.getInt("DOCUMENTO"),
                rs.getString("T_DOCUMENTO"),
                rs.getString("NOMBRES"),
                rs.getString("APELLIDOS"),
                rs.getString("EMAIL"),
                rs.getString("PASSWORD"),
                rs.getString("ROL").charAt(0)
            );*/
        } catch (SQLException e) {
            System.out.println("getByUsername: " + e.getMessage());
            return new UsuarioEntity();
        }
    }
    
    /**
     * Crea un usuario
     * @param DOCUMENTO
     * @param T_DOCUMENTO
     * @param NOMBRES
     * @param APELLIDOS
     * @param EMAIL
     * @param PASSWORD
     * @param ROL 
     */
    public void create(int DOCUMENTO, String T_DOCUMENTO, String NOMBRES, String APELLIDOS, String EMAIL, String PASSWORD, char ROL){
        
                super.CUD("insert into USUARIO values(null,?,?,?,?,?,?,?)",
                    new String[]{ Integer.toString(DOCUMENTO) ,  T_DOCUMENTO,  NOMBRES,  APELLIDOS,  EMAIL,  PASSWORD, ROL+""});
    }
    
    
    /**
     * Actualiza un usuario
     * @param ID_USUARIO
     * @param DOCUMENTO
     * @param T_DOCUMENTO
     * @param NOMBRES
     * @param APELLIDOS
     * @param EMAIL
     * @param PASSWORD
     * @param ROL 
     */
    public void update(int ID_USUARIO, int DOCUMENTO, String T_DOCUMENTO, String NOMBRES, String APELLIDOS, String EMAIL, String PASSWORD, char ROL){
        
                super.CUD(  "UPDATE USUARIO\n" +
                            "SET DOCUMENTO = ?,\n" +
                            "    T_DOCUMENTO = ?,\n" +
                            "    NOMBRES = ?,\n" +
                            "    APELLIDOS = ?,\n" +
                            "    EMAIL = ?,\n" +
                            "    PASSWORD = ?,\n" +
                            "    ROL = ?\n" +
                            "WHERE ID_USUARIO = ?;",
                    new String[]{ Integer.toString(DOCUMENTO) ,  T_DOCUMENTO,  NOMBRES,  APELLIDOS,  EMAIL,  PASSWORD, ROL+"", Integer.toString(ID_USUARIO)});
    }
    
    /**
     * 
     * @param ID_USUARIO
     * @param DOCUMENTO
     * @param T_DOCUMENTO
     * @param NOMBRES
     * @param APELLIDOS
     * @param EMAIL
     * @param PASSWORD
     * @param ROL 
     */
    public void delete(int ID_USUARIO){
        
                super.CUD(  "DELETE FROM USUARIO WHERE ID_USUARIO = ?;",
                    new String[]{ Integer.toString(ID_USUARIO)});
    }
            
            


    
    
    
    
    
	
	
    
    
            
    @Override
    protected Class getEntityClass() {
        return UsuarioEntity.class;
    }

    
    
    @Override
    protected UsuarioEntity toEntity(ResultSet rs) throws SQLException {
        return new UsuarioEntity(
                rs.getInt("ID_USUARIO"),
                rs.getInt("DOCUMENTO"),
                rs.getString("T_DOCUMENTO"),
                rs.getString("NOMBRES"),
                rs.getString("APELLIDOS"),
                rs.getString("EMAIL"),
                rs.getString("PASSWORD"),
                rs.getString("ROL").charAt(0)
            );
    }
}
