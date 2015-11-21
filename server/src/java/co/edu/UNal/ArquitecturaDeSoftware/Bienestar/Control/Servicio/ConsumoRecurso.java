/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.UNal.ArquitecturaDeSoftware.Bienestar.Control.Servicio;

import EntidadPrueba.ListStudents;
import EntidadPrueba.Student;
import co.edu.UNal.ArquitecturaDeSoftware.Bienestar.AccesoDatos.DAO.UsuarioDAO;
import co.edu.UNal.ArquitecturaDeSoftware.Bienestar.AccesoDatos.Entity.UsuarioEntity;
import java.util.List;

/**
 *
 * @author mysqluser
 */
public class ConsumoRecurso {

	public static void RegistrarEstudiantesEntidad(int idConvocatoria) {
		ListStudents objectReceived = getBestStudents(1);
		List<Student> lista = objectReceived.getStudents();
		int cuposLibres = lista.size();

		for (int i = 0; i < lista.size() || i < cuposLibres; i++) {
			Student est = lista.get(i);
			//Verificar estuiante en BD
			System.out.println(est.getEstEmail());
			UsuarioEntity newUsr = UsuarioDAO.getByUsername(est.getEstEmail());
			int id;
			try {
				id = newUsr.getIdUsuario();
			} catch (NullPointerException e) {
				String ExtraData = est.getEstAddress() + ',' + est.getEstGender() + ',' + est.getEstAge() + ',' + est.getEstTelephone();
				UsuarioDAO.create(String.valueOf(est.getEstDocument()), "CC", est.getEstName(), est.getEstLastName(), est.getEstEmail(), est.getEstPassword(), 'E', ExtraData);

				newUsr = UsuarioDAO.getByUsername(est.getEstEmail());
				id = newUsr.getIdUsuario();

			}
			UsuarioDAO.registrarConvocatoria(id, idConvocatoria);
		}
	}

    //Si no existe, agregar       
        //return null;
	private static ListStudents getBestStudents(int arg0) {
		EntidadPrueba.BestStudents_Service service = new EntidadPrueba.BestStudents_Service();
		EntidadPrueba.BestStudents port = service.getBestStudentsPort();
		return port.getBestStudents(arg0);
	}

}
