/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.UNal.ArquitecturaDeSoftware.Bienestar.AccesoDatos.DAO;

import co.edu.UNal.ArquitecturaDeSoftware.Bienestar.AccesoDatos.Entity.UsuarioEntity;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;

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
     * @param em the entity manager
     * @param username String containing the username
     * @param password String containing the password
     * @return Value object with required user information
     */
    public UsuarioEntity getByUsername(String username) throws Exception {
        
        EntityManager em;
        EntityManagerFactory emf;
        emf = Persistence.createEntityManagerFactory("co.edu.unal-AdS7BienestarPU");
        em  = emf.createEntityManager();
        em.getTransaction().begin();
        
        checkEntityManager(em);
        try {
            return em.createNamedQuery("Usuario.findByUsername", UsuarioEntity.class).
                    setParameter("username", username).getSingleResult();
        } catch (NoResultException e) {
            return null;
        } catch (Exception e) {
            throw new Exception(e.getMessage(), e.getCause());
        }finally{
            em.close();
            return null;
        }
    }
	
	

    @Override
    protected Class getEntityClass() {
        return UsuarioEntity.class;
    }
}
