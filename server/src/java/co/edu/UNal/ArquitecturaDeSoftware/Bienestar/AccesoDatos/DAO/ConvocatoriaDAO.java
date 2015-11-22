/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.UNal.ArquitecturaDeSoftware.Bienestar.AccesoDatos.DAO;

import co.edu.UNal.ArquitecturaDeSoftware.Bienestar.AccesoDatos.Entity.ConvocatoriaEntity;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author snipercat
 */
public class ConvocatoriaDAO extends CrudDAO<ConvocatoriaEntity> {

	public ConvocatoriaDAO() {
	}
	/*
	 ConvocatoriaDAO dao = new ConvocatoriaDAO();
	 ConvocatoriaEntity entity = dao.getByID("1");
	 System.out.println("Entity: "+entity.toString());
	 String fecha = "2015/10/20";
	 String resp;
	 //resp = dao.create("Convocatoria de desparche", "Una convocatoria para la dispoersión", fecha, 15);
	 //resp = dao.update(11, "Convocatoria de desparche", "Una convocatoria para la dispersión", "2015/10/01", 15);
	 resp = dao.delete(11);
	 System.out.println("RESPUESTA:\t"+resp);
	 */

	/**
	 * Returns a value object
	 *
	 * @param ID
	 * @return Value object with required user information
	 */
	public static ConvocatoriaEntity getByID(String ID) {
		ResultSet rs = CrudDAO.query("SELECT * FROM CONVOCATORIA WHERE ID_CONVOCATORIA =?", new String[]{ID});
		try {
			rs.first();
			ConvocatoriaEntity ue = toEntity(rs);

			return ue;
		} catch (SQLException e) {
			System.out.println("ConvocatoriaDAO.getByID: " + e.getMessage());
			return new ConvocatoriaEntity();
		}
	}

	/**
	 * Crea una convocatoria, para las fechas ingresarlas en el formato
	 * AAAA/MM/DD ("2015/09/20")
	 *
	 * @param NOMBRE
	 * @param DESCRIPCION
	 * @param FECHA_FIN_REGISTRO
	 * @param CUPOS
	 * @return OK SI EL REGISTRO EN LA BD ES CORRECTO, SI NO DEVOLVER EL TIPO DE
	 * ERROR.
	 */
	public static String create(
			String NOMBRE,
			String DESCRIPCION,
			String FECHA_FIN_REGISTRO,
			int CUPOS
	) {
		String respuestaSQL = update(
				"insert into CONVOCATORIA values(null,?,?,?,?)",
				new String[]{
					NOMBRE,
					DESCRIPCION,
					FECHA_FIN_REGISTRO,
					Integer.toString(CUPOS)
				}
		);

		System.out.println("ConvocatoriaDAO.create: " + respuestaSQL);
		return respuestaSQL;
	}

	/**
	 * Actualiza una convocatoria, para las fechas ingresarlas en el formato
	 * AAAA/MM/DD ("2015/09/20")
	 *
	 * @param ID_CONVOCATORIA
	 * @param NOMBRE
	 * @param DESCRIPCION
	 * @param FECHA_FIN_REGISTRO
	 * @param CUPOS
	 * @return OK SI EL REGISTRO EN LA BD ES CORRECTO, SI NO DEVOLVER EL TIPO DE
	 * ERROR.
	 */
	public static String update(
			int ID_CONVOCATORIA,
			String NOMBRE,
			String DESCRIPCION,
			String FECHA_FIN_REGISTRO,
			int CUPOS
	) {
		String respuestaSQL = CrudDAO.update(
				"UPDATE CONVOCATORIA\n"
				+ "SET NOMBRE = ?,\n"
				+ "DESCRIPCION = ?,\n"
				+ "FECHA_FIN_REGISTRO = ?,\n"
				+ "CUPOS = ?\n"
				+ "WHERE ID_CONVOCATORIA = ?;",
				new String[]{
					NOMBRE,
					DESCRIPCION,
					FECHA_FIN_REGISTRO,
					Integer.toString(CUPOS),
					Integer.toString(ID_CONVOCATORIA)
				}
		);
		System.out.println("ConvocatoriaDAO.update: " + respuestaSQL);
		return respuestaSQL;
	}

	/**
	 *
	 * @param ID_CONVOCATORIA
	 * @return
	 */
	public static String delete(int ID_CONVOCATORIA) {
		String respuestaSQL = CrudDAO.update(
				"DELETE FROM CONVOCATORIA WHERE ID_CONVOCATORIA = ?;",
				new String[]{Integer.toString(ID_CONVOCATORIA)}
		);
		System.out.println("ConvocatoriaDAO.delete: " + respuestaSQL);
		return respuestaSQL;
	}

	/**
	 * Retorna la lista de registros de la tabla TALLER en un rango
	 *
	 * @param tamano
	 * @param pagina
	 * @return
	 */
	public static ArrayList<ConvocatoriaEntity> getConvocatorias(int tamano, int pagina) {
		int posicion = pagina * tamano;

		ArrayList<Object> param = new ArrayList<>();
		param.add(posicion);
		param.add(tamano);

		ArrayList<ConvocatoriaEntity> convocatorias = new ArrayList<>();
		ResultSet rs = CrudDAO.query("SELECT * FROM CONVOCATORIA LIMIT ?,? ", param);
		try {
			while (rs.next()) {
				ConvocatoriaEntity ue = toEntity(rs);
				convocatorias.add(ue);
			}
		} catch (SQLException e) {
			System.out.println("ConvocatoriasDAO.getConvocatorias: " + e.getMessage());
			return new ArrayList<>();
		}
		return convocatorias;
	}

                /**
	 * Retorna la lista de convocatorias que tengan la palabra 'Apoyo'
	 *
	 * 
	 * @return
	 */
	public static ArrayList<ConvocatoriaEntity> getConvocatoriasApoyo() {
		int posicion = 1;

		ArrayList<Object> param = new ArrayList<>();
		param.add(posicion);
		param.add(0);

		ArrayList<ConvocatoriaEntity> convocatorias = new ArrayList<>();
		ResultSet rs = CrudDAO.query("select * from CONVOCATORIA where upper(NOMBRE) like '%APOYO%' ", param);
		try {
			while (rs.next()) {
				ConvocatoriaEntity ue = toEntity(rs);
				convocatorias.add(ue);
			}
		} catch (SQLException e) {
			System.out.println("ConvocatoriasDAO.getConvocatoriasApoyo: " + e.getMessage());
			return new ArrayList<>();
		}
		return convocatorias;
	}
        
	/**
	 * Retorna la cantidad de convocatorias
	 *
	 * @return
	 */
	public static int getCountConvoctorias() {
		ArrayList<Object> param = new ArrayList<>();

		int count;
		ResultSet rs = CrudDAO.query("SELECT COUNT(*) c FROM CONVOCATORIA", param);

		try {
			rs.first();
			count = rs.getInt("c");
		} catch (SQLException e) {
			System.out.println("ConvocatoriaDAO.getCountConvocatorias: " + e.getMessage());
			return 0;
		}
		return count;
	}

	/**
	 * Retorna la cantidad de USUARIOS inscritos en una convocatoria
	 *
	 * @param ID_CONVOCATORIA
	 * @return
	 */
	public static int getCountUsuarioConvocatoria(int ID_CONVOCATORIA) {
		ArrayList<Object> param = new ArrayList<>();
		param.add(ID_CONVOCATORIA);

		int count;
		ResultSet rs = CrudDAO.query("SELECT COUNT(*) c FROM USUARIO_CONVOCATORIA WHERE ID_CONVOCATORIA = ?", param);

		try {
			rs.first();
			count = rs.getInt("c");
		} catch (SQLException e) {
			System.out.println("TallerDAO.getCountUsuarioConvocatoria: " + e.getMessage());
			return 0;
		}
		return count;
	}

////////////////////////        
	@Override
	protected Class getEntityClass() {
		return ConvocatoriaEntity.class;
	}

	protected static ConvocatoriaEntity toEntity(ResultSet rs) throws SQLException {
		return new ConvocatoriaEntity(
				rs.getInt("ID_CONVOCATORIA"),
				rs.getString("NOMBRE"),
				rs.getString("DESCRIPCION"),
				rs.getDate("FECHA_FIN_REGISTRO"),
				rs.getInt("CUPOS")
		);
	}

}
