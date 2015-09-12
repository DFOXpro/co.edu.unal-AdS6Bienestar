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
import javax.persistence.TypedQuery;

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
     * @param em the entity manager
     * @param username String containing the username
     * @param password String containing the password
     * @return Value object with required user information
     */
    public UsuarioEntity getByUsername(String username) {
        
        EntityManager em;
        EntityManagerFactory emf;
        emf = Persistence.createEntityManagerFactory("co.edu.unal-AdS7BienestarPU");
        em  = emf.createEntityManager();
        em.getTransaction().begin();
        UsuarioEntity u = null;
        try {
            checkEntityManager(em);
            TypedQuery<UsuarioEntity> query = em.createQuery("SELECT * FROM USUARIO WHERE email =:username", UsuarioEntity.class);
            u = query.setParameter("username", username).getSingleResult();
            System.out.println("A"+query);
        } catch (NoResultException e) {
            System.out.println("No hay resultados");
            return null;
        } catch (Exception e) {
            System.out.println("C"+e.getMessage());
        }finally{
            em.close();
            System.out.println("finally");
            return u;
        }
    }

    @Override
    protected Class getEntityClass() {
        return UsuarioEntity.class;
    }
}
