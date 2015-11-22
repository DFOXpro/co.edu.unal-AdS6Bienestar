/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.UNal.ArquitecturaDeSoftware.Bienestar.AccesoDatos.DAO;

import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.ResultSet;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.Entity;
import javax.sql.DataSource;

/**
 *
 * @author snipercat
 * @param <E>
 */
public abstract class CrudDAO<E extends Entity> {
    private static final String usuario = "bienestar";
    private static final String contrasena = "bienestar";
    //private final String url = "jdbc:mysql://localhost:3306/bienestar";
    private static final String url = "jdbc:mysql://localhost:3306/bienestar?zeroDateTimeBehavior=convertToNull";
    private static Connection conn;
    
    /**
     * Retornara una Entity de la clase E,
     * @param rs ResultSet, de alli se extraeran los datos para crear el Entity
     * @return
     * @throws java.lang.Exception
     */
    //protected abstract E toEntity(ResultSet rs )throws Exception;

    private static boolean iniciarConeccion(){
        
        System.out.println("iniciarConeccion");

        ////CONECCIÓN CON BASE DE DATOS A TRAVÉS DE UN JDBC DEFINIDO EN GLASSFISH
        try {
        InitialContext ctx = new InitialContext();
        DataSource ds = (DataSource)ctx.lookup("jdbc/bienestar");

        conn = ds.getConnection();
        if(conn == null )
        System.out.println("CONNECTION NULL");
        return true;
        } catch (NamingException ex) {
        Logger.getLogger(CrudDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(CrudDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
        
        /*
        //CONECCIÓN DIRECTA CON BASE DE DATOS
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
        }*/
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

	/**
	 * Use esta funcion para los gets
	 * @param query
	 * @param values
	 * @return 
	 */
    protected static ResultSet query(String query, String[] values){
        if(conn == null)
            iniciarConeccion();
        try{
            PreparedStatement ps = conn.prepareStatement(query);
            for (int i = 0; i < values.length; i++){
                ps.setString(i+1, values[i]);
            }
            return ps.executeQuery();
        }
        catch (SQLException ex){
            System.err.println(ex.getMessage());
            return null;
        }
    }
    
    
    	/**
	 * Use esta funcion para los gets, recive parámetros de diferentes tipos.
	 * @param query
        * @param param
	 * @return 
	 */
    protected static ResultSet query(String query, ArrayList<Object> param){
        if(conn == null)
            iniciarConeccion();
        try{
            PreparedStatement ps = conn.prepareStatement(query);
            for (int i = 0; i < param.size(); i++){
                String className = param.get(i).getClass().getName();
                String clString = String.class.getName();
                String clInteger = Integer.class.getName();
                
                if(className.matches(clString))
                    ps.setString(i+1, (String)param.get(i));
                if(className.matches(clInteger))
                    ps.setInt(i+1, (int) param.get(i));
            }
            return ps.executeQuery();
        }
        catch (SQLException ex){
            System.err.println(ex.getMessage());
            return null;
        }
    }
    

	/**
	 * Use esta funcion para los sets y deletes
	 * @param query
	 * @param values
	 * @return 
	 */
    protected static String update(String query, String[] values){
        if(conn == null)
            iniciarConeccion();
        try{
            PreparedStatement ps = conn.prepareStatement(query);
            for (int i = 0; i < values.length; i++)
                ps.setString(i+1, values[i]);
            ps.executeUpdate();
			return "OK";
        }
        catch (SQLException ex){
            System.out.println("CrudDAO.update: " + ex.getMessage());
            return ex.getMessage();
        }
    }

    protected abstract Class getEntityClass();
}
