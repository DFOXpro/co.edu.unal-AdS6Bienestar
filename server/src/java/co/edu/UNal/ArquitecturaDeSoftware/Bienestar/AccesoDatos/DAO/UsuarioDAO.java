/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.UNal.ArquitecturaDeSoftware.Bienestar.AccesoDatos.DAO;

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
        ResultSet rs = super.query("SELECT * FROM USUARIO WHERE EMAIL =?",new String[]{username});
        try {
            rs.first();
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
        } catch (SQLException e) {
            System.out.println("getByUsername: " + e.getMessage());
            return new UsuarioEntity();
        }
    }

    @Override
    protected Class getEntityClass() {
        return UsuarioEntity.class;
    }
}
