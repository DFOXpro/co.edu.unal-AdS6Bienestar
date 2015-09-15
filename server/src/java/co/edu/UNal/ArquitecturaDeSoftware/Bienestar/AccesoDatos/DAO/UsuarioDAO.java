/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.UNal.ArquitecturaDeSoftware.Bienestar.AccesoDatos.DAO;

import co.edu.UNal.ArquitecturaDeSoftware.Bienestar.AccesoDatos.Entity.TallerEntity;
import co.edu.UNal.ArquitecturaDeSoftware.Bienestar.AccesoDatos.Entity.UsuarioEntity;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author snipercat
 */
public class UsuarioDAO extends CrudDAO<UsuarioEntity> {

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
		ResultSet rs = CrudDAO.query("SELECT * FROM USUARIO WHERE LOWER(EMAIL) =LOWER(?)", new String[]{username});
		try {
			rs.first();
			UsuarioEntity ue = toEntity(rs);
                        
                                                        
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
	 *
	 * @param DOCUMENTO
	 * @param T_DOCUMENTO
	 * @param NOMBRES
	 * @param APELLIDOS
	 * @param EMAIL
	 * @param PASSWORD
	 * @param ROL
	 * 
	 * @return  OK SI EL REGISTRO EN LA BD ES CORRECTO, SI NO DEVOLVER EL TIPO DE ERROR.
	 */
	public static String create(
			int DOCUMENTO,
			String T_DOCUMENTO,
			String NOMBRES,
			String APELLIDOS,
			String EMAIL,
			String PASSWORD,
			char ROL
	) {
		String respuestaSQL = update(
			"insert into USUARIO values(null,?,?,?,?,?,?,?)",
			new String[]{
				Integer.toString(DOCUMENTO),
				T_DOCUMENTO,
				NOMBRES,
				APELLIDOS,
				EMAIL,
				PASSWORD,
				ROL + ""
			}
		);
		if (respuestaSQL.contains("Duplicate entry")){
			if(respuestaSQL.contains("DOCUMENTO"))
				respuestaSQL = "documento";
			else if(respuestaSQL.contains("EMAIL"))
				respuestaSQL = "correo";
		}
		System.out.println("UsuarioDAO.create: "+respuestaSQL);
		return respuestaSQL;
	}

	/**
	 * Actualiza un usuario
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
	public String update(
			int ID_USUARIO,
			int DOCUMENTO,
			String T_DOCUMENTO,
			String NOMBRES,
			String APELLIDOS,
			String EMAIL,
			String PASSWORD,
			char ROL
	) {
            String respuestaSQL = super.update(
                            "UPDATE USUARIO\n"
                            + "SET DOCUMENTO = ?,\n"
                            + "T_DOCUMENTO = ?,\n"
                            + "NOMBRES = ?,\n"
                            + "APELLIDOS = ?,\n"
                            + "EMAIL = ?,\n"
                            + "PASSWORD = ?,\n"
                            + "ROL = ?\n"
                            + "WHERE ID_USUARIO = ?;",
                            new String[]{
                                    Integer.toString(DOCUMENTO),
                                    T_DOCUMENTO,
                                    NOMBRES,
                                    APELLIDOS,
                                    EMAIL,
                                    PASSWORD,
                                    ROL + "",
                                    Integer.toString(ID_USUARIO)
                            }
		);
            if (respuestaSQL.contains("Duplicate entry")){
                    if(respuestaSQL.contains("DOCUMENTO"))
                            respuestaSQL = "documento";
                    else if(respuestaSQL.contains("EMAIL"))
                            respuestaSQL = "correo";
            }
            System.out.println("UsuarioDAO.update: "+respuestaSQL);
            return respuestaSQL;
	}

	/**
	 *
	 * @param ID_USUARIO
         * @return 
	 */
	public String delete(int ID_USUARIO) {
		String respuestaSQL = CrudDAO.update(
				"DELETE FROM USUARIO WHERE ID_USUARIO = ?;",
				new String[]{Integer.toString(ID_USUARIO)}
		);
                System.out.println("UsuarioDAO.delete: "+respuestaSQL);
                return respuestaSQL;
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
