/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.UNal.ArquitecturaDeSoftware.Bienestar.AccesoDatos.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.Entity;
import javax.persistence.EntityManager;

/**
 *
 * @author snipercat
 * @param <E>
 */
public abstract class CrudDAO<E extends Entity> {
    private final String usuario = "bienestar";
    private final String contrasena = "bienestar";
    private final String url = "jdbc:mysql://localhost:3306/bienestar";
    private static Connection conn;
    
    private boolean iniciarConeccion(){
        System.out.println("iniciarConeccion");
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            conn = DriverManager.getConnection(url, usuario, contrasena);
            return true;
        } catch (
            ClassNotFoundException |
            IllegalAccessException |
            InstantiationException |
            SQLException ex
        ) {
            Logger.getLogger(CrudDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    private boolean cerrarConeccion(){
        System.out.println("cerrarConeccion");
        try {
            conn.close();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(CrudDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    protected ResultSet query(String query, String[] values){
        if(conn == null) iniciarConeccion();
        try{
            PreparedStatement ps = conn.prepareStatement(query);
            for (int i = 0; i < values.length; i++)
                ps.setString(i+1, values[i]);
            System.out.println(ps);
            return ps.executeQuery();
        }
        catch (SQLException ex){
            System.err.println(ex.getMessage());
            return null;
        }
    }

    public void create(EntityManager entityManager, E entity)
            throws Exception {
        checkEntityManager(entityManager);
        try {
            entityManager.persist(entity);
        } catch (Exception ex) {
            throw new Exception(ex.getMessage(), ex.getCause());
        }
    }

    
    public E update(EntityManager entityManager, E entity)
            throws Exception {
        checkEntityManager(entityManager);
        try {
            return entityManager.merge(entity);
        } catch (Exception ex) {
            throw new Exception(ex.getMessage(), ex.getCause());
        }
    }

    
    public void delete(EntityManager entityManager, Entity entity) 
            throws Exception {
        try {
            entityManager.remove(entity);
        } catch (Exception e) {
            throw new Exception(e.getMessage(), e.getCause());
        }
    }

    

    
    public E read(EntityManager entityManager, long entityId)
            throws Exception {
        checkEntityManager(entityManager);
        try {
            return (E) entityManager.find(getEntityClass(), entityId);
        } catch (Exception e) {
            throw new Exception(e.getMessage(), e.getCause());
        }


    }

    protected void checkEntityManager(EntityManager entityManager) throws Exception {
        if (entityManager == null) {
            throw new Exception("entityManager.null");
        }
    }

    protected abstract Class getEntityClass();
}
