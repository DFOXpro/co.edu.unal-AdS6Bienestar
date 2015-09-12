/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.UNal.ArquitecturaDeSoftware.Bienestar.AccesoDatos.DAO;

import javax.persistence.Entity;
import javax.persistence.EntityManager;

/**
 *
 * @author snipercat
 */
public abstract class CrudDAO<E extends Entity> {
    
 
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
