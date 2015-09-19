/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.UNal.ArquitecturaDeSoftware.Bienestar.AccesoDatos.DAO;

import co.edu.UNal.ArquitecturaDeSoftware.Bienestar.AccesoDatos.Entity.TallerEntity;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author snipercat
 */
public class TallerDAO extends CrudDAO<TallerEntity>  {
    public TallerDAO() {
	}
    
    
    /*
    TallerDAO dao = new TallerDAO();
    TallerEntity entity = dao.getByID("1");
    System.out.println("Entity: "+entity.toString());
    String fecha = "2015/10/20";
    String resp;
    resp = dao.create('T', "Taller de desparche", "Un taller para la dispoersión", fecha, fecha, fecha, 3000, 15);
    resp = dao.update(11,'T', "Taller de desparche", "Un taller para la dispersión", fecha, fecha, fecha, 3000, 15);
    resp = dao.delete(11);
    System.out.println("RESPUESTA:\t"+resp);
    */
    

	/**
	 * Returns a value object 
	 *
         * @param ID
	 * @return Value object with required user information
	 */
	public TallerEntity getByID(String ID) {
		ResultSet rs = CrudDAO.query("SELECT * FROM TALLER WHERE ID_TALLER =?", new String[]{ID});
		try {
			rs.first();
			TallerEntity ue = toEntity(rs);

			return ue;
		} catch (SQLException e) {
			System.out.println("TallerDAO.getByID: " + e.getMessage());
			return new TallerEntity();
		}
	}

	
        
        /**
         * Crea un taller, para las fechas ingresarlas en el formato AAAA/MM/DD ("2015/09/20")
         * @param TIPO_TALLER
         * @param NOMBRE
         * @param DESCRIPCION
         * @param FECHA_FIN_REGISTRO
         * @param FECHA_INICIO
         * @param FECHA_FIN
         * @param COSTO
         * @param CUPOS
         * @return OK SI EL REGISTRO EN LA BD ES CORRECTO, SI NO DEVOLVER EL TIPO DE ERROR.
         */
	public static String create(
			char TIPO_TALLER,
			String NOMBRE,
			String DESCRIPCION,
			String FECHA_FIN_REGISTRO,
			String FECHA_INICIO,
			String FECHA_FIN,
                        int COSTO,
                        int CUPOS
	) { 
		String respuestaSQL = update(
			"insert into TALLER values(null,?,?,?,?,?,?,?,?)",
			new String[]{
                                TIPO_TALLER+"",
                                NOMBRE,
                                DESCRIPCION,
                                FECHA_FIN_REGISTRO,
                                FECHA_INICIO,
                                FECHA_FIN,
                                Integer.toString(COSTO),
                                Integer.toString(CUPOS)
                            }
		);
		
		System.out.println("TallerDAO.create: "+respuestaSQL);
		return respuestaSQL;
	}

	/**
         * Actualiza un taller, para las fechas ingresarlas en el formato AAAA/MM/DD ("2015/09/20")
         * @param ID_TALLER
         * @param TIPO_TALLER
         * @param NOMBRE
         * @param DESCRIPCION
         * @param FECHA_FIN_REGISTRO
         * @param FECHA_INICIO
         * @param FECHA_FIN
         * @param COSTO
         * @param CUPOS
         * @return OK SI EL REGISTRO EN LA BD ES CORRECTO, SI NO DEVOLVER EL TIPO DE ERROR.
         */
	public static String update(
                        int    ID_TALLER,
			char   TIPO_TALLER,
			String NOMBRE,
			String DESCRIPCION,
			String FECHA_FIN_REGISTRO,
			String FECHA_INICIO,
			String FECHA_FIN,
                        int COSTO,
                        int CUPOS
	) {
            String respuestaSQL = CrudDAO.update(
                            "UPDATE TALLER\n"
                            + "SET TIPO_TALLER = ?,\n"
                            + "NOMBRE = ?,\n"
                            + "DESCRIPCION = ?,\n"
                            + "FECHA_FIN_REGISTRO = ?,\n"
                            + "FECHA_INICIO = ?,\n"
                            + "FECHA_FIN = ?,\n"
                            + "COSTO = ?,\n"
                            + "CUPOS = ?\n"
                            + "WHERE ID_TALLER = ?;",
                            new String[]{
                                TIPO_TALLER+"",
                                NOMBRE,
                                DESCRIPCION,
                                FECHA_FIN_REGISTRO,
                                FECHA_INICIO,
                                FECHA_FIN,
                                Integer.toString(COSTO),
                                Integer.toString(CUPOS),
                                Integer.toString(ID_TALLER)
                            }
		);
            System.out.println("TallerDAO.update: "+respuestaSQL);
            return respuestaSQL;
	}

	/**
	 *
	 * @param ID_TALLER
         * @return 
	 */
	public static String delete(int ID_TALLER) {
		String respuestaSQL = CrudDAO.update(
				"DELETE FROM TALLER WHERE ID_TALLER = ?;",
				new String[]{Integer.toString(ID_TALLER)}
		);
                System.out.println("TallerDAO.delete: "+respuestaSQL);
                return respuestaSQL;
	}
        
        
        /**
         * Ingresa un registro en la tabla PROFESOR_TALLER 
         * @param ID_USUARIO
         * @param ID_TALLER
         * @return 
         */
        public static String registrarProfesor(int ID_USUARIO, int ID_TALLER){
                String respuestaSQL = CrudDAO.update(
				"INSERT INTO PROFESOR_TALLER(ID_USUARIO, ID_TALLER) VALUES (?,?);",
				new String[]{Integer.toString(ID_USUARIO),Integer.toString(ID_TALLER) }
		);
                System.out.println("TallerDAO.registrarProfesor: "+respuestaSQL);
                return respuestaSQL;
        }
        
        /**
        * Elimina un registro en la tabla PROFESOR_TALLER
        *
        * @param ID_PROFESOR
        * @param ID_TALLER
        * @return
        */
        
        public static String desvincularProfesor(int ID_PROFESOR, int ID_TALLER) {

            String respuestaSQL = CrudDAO.update(
                    "DELETE FROM PROFESOR_TALLER WHERE ID_PROFESOR = ? AND ID_TALLER = ?;",
                    new String[]{Integer.toString(ID_PROFESOR), Integer.toString(ID_TALLER)}
            );
            System.out.println("TallerDAO.desvincularProfesor: " + respuestaSQL);
            return respuestaSQL;
        }
        
        
        /**
         * Retorna la lista de registros de la tabla TALLER en un rango
         * @return 
         */
        public static ArrayList<TallerEntity> getTodosTalleres(int tamano, int pagina){
            int posicion = pagina * tamano;
            ArrayList<Object> param = new ArrayList<>();
            param.add(posicion);
            param.add(tamano);
            
            ArrayList<TallerEntity> talleres = new ArrayList<>();
                ResultSet rs = CrudDAO.query("SELECT * FROM TALLER ", param);
		try {
                       while(rs.next()){
			TallerEntity ue = toEntity(rs);
			talleres.add(ue);
                       }
		} catch (SQLException e) {
			System.out.println("TallerDAO.getTodosTalleres: " + e.getMessage());
			return new ArrayList<>();
		}
            return talleres;
        }
        
        
        /**
         * Retorna la lista de talleres en un rango
         * @return 
         */
        public ArrayList<TallerEntity> getTalleres(int tamano, int pagina){
            int posicion = pagina * tamano;
            ArrayList<Object> param = new ArrayList<>();
            param.add(posicion);
            param.add(tamano);
            
            ArrayList<TallerEntity> talleres = new ArrayList<>();
                ResultSet rs = CrudDAO.query("SELECT * FROM TALLER WHERE TIPO_TALLER = 'T' ", param);
		try {
                       while(rs.next()){
			TallerEntity ue = toEntity(rs);
			talleres.add(ue);
                       }
		} catch (SQLException e) {
			System.out.println("TallerDAO.getTalleres: " + e.getMessage());
			return new ArrayList<>();
		}
            return talleres;
        }
        
        
        /**
         * Retorna la lista de cursos en un rango
         * @return 
         */
        public ArrayList<TallerEntity> getCursos(int tamano, int pagina){
            int posicion = pagina * tamano;
            ArrayList<Object> param = new ArrayList<>();
            param.add(posicion);
            param.add(tamano);
            
            ArrayList<TallerEntity> talleres = new ArrayList<>();
                ResultSet rs = CrudDAO.query("SELECT * FROM TALLER WHERE TIPO_TALLER = 'C' ", param);
		try {
                       while(rs.next()){
			TallerEntity ue = toEntity(rs);
			talleres.add(ue);
                       }
		} catch (SQLException e) {
			System.out.println("TallerDAO.getCursos: " + e.getMessage());
			return new ArrayList<>();
		}
            return talleres;
        }
        
        /**
        * Retorna la cantidad de registros en la tabla TALLER
        * @return 
        */
       public static int getCountTodosTalleres(){   
           ArrayList<Object> param = new ArrayList<>();

           int count;
           ResultSet rs = CrudDAO.query("SELECT count(*) c FROM TALLER", param);
           try {
               rs.first();
               count = rs.getInt("c");
           } catch (SQLException e) {
               System.out.println("TallerDAO.getCountTodosTaller: " + e.getMessage());
               return 0;
           }
           return count;    
        }
       
       
       /**
        * Retorna la cantidad de Talleres
        * @return 
        */
        public static int getCountTalleres(){   
           ArrayList<Object> param = new ArrayList<>();

           int count;
           ResultSet rs = CrudDAO.query("SELECT COUNT(*) c FROM TALLER WHERE TIPO_TALLER = 'T'", param);
           
           try {
               rs.first();
               count = rs.getInt("c");
           } catch (SQLException e) {
               System.out.println("TallerDAO.getCountTalleres: " + e.getMessage());
               return 0;
           }
           return count;    
        }
       
        
               /**
        * Retorna la cantidad de Talleres
        * @return 
        */
        public static int getCountCursos(){   
           ArrayList<Object> param = new ArrayList<>();

           int count;
           ResultSet rs = CrudDAO.query("SELECT COUNT(*) c FROM TALLER WHERE TIPO_TALLER = 'C'", param);
           
           try {
               rs.first();
               count = rs.getInt("c");
           } catch (SQLException e) {
               System.out.println("TallerDAO.getCountCursos: " + e.getMessage());
               return 0;
           }
           return count;    
        }
       
    /**
     * Retorna la cantidad de USUARIOS inscritos en un Taller
     * @param ID_TALLER
     * @return 
     */
     public static int getCountUsuarioTalleres( int ID_TALLER ){   
        ArrayList<Object> param = new ArrayList<>();
        param.add(ID_TALLER);

        int count;
        ResultSet rs = CrudDAO.query("SELECT COUNT(*) c FROM USUARIO_TALLER WHERE ID_TALLER = ?", param);

        try {
            rs.first();
            count = rs.getInt("c");
        } catch (SQLException e) {
            System.out.println("TallerDAO.getCountUsuarioTalleres: " + e.getMessage());
            return 0;
        }
        return count;    
     }
        
////////////////////////        
        
	@Override
	protected Class getEntityClass() {
		return TallerEntity.class;
	}

	
	protected static TallerEntity toEntity(ResultSet rs) throws SQLException {
		return new TallerEntity(
                                rs.getInt("ID_TALLER"),
                                rs.getString("TIPO_TALLER"),
                                rs.getString("NOMBRE"),
                                rs.getString("DESCRIPCION"),
                                rs.getDate("FECHA_FIN_REGISTRO"),
                                rs.getDate("FECHA_INICIO"),
                                rs.getDate("FECHA_FIN"),
                                rs.getInt("COSTO"),
                                rs.getInt("CUPOS")
		);
	}
}
