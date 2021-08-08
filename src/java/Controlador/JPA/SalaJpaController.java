/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador.JPA;

import Controlador.JPA.exceptions.NonexistentEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import Modelo.Cartelera;
import Modelo.Sala;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author timoa
 */
public class SalaJpaController implements Serializable {

    public SalaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }

    public SalaJpaController() {
    }
    
    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("ProyectoCineWEBPU");

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Sala sala) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Cartelera idCartelera = sala.getIdCartelera();
            if (idCartelera != null) {
                idCartelera = em.getReference(idCartelera.getClass(), idCartelera.getIdCartelera());
                sala.setIdCartelera(idCartelera);
            }
            em.persist(sala);
            if (idCartelera != null) {
                idCartelera.getSalaList().add(sala);
                idCartelera = em.merge(idCartelera);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Sala sala) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Sala persistentSala = em.find(Sala.class, sala.getIdSala());
            Cartelera idCarteleraOld = persistentSala.getIdCartelera();
            Cartelera idCarteleraNew = sala.getIdCartelera();
            if (idCarteleraNew != null) {
                idCarteleraNew = em.getReference(idCarteleraNew.getClass(), idCarteleraNew.getIdCartelera());
                sala.setIdCartelera(idCarteleraNew);
            }
            sala = em.merge(sala);
            if (idCarteleraOld != null && !idCarteleraOld.equals(idCarteleraNew)) {
                idCarteleraOld.getSalaList().remove(sala);
                idCarteleraOld = em.merge(idCarteleraOld);
            }
            if (idCarteleraNew != null && !idCarteleraNew.equals(idCarteleraOld)) {
                idCarteleraNew.getSalaList().add(sala);
                idCarteleraNew = em.merge(idCarteleraNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = sala.getIdSala();
                if (findSala(id) == null) {
                    throw new NonexistentEntityException("The sala with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Sala sala;
            try {
                sala = em.getReference(Sala.class, id);
                sala.getIdSala();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The sala with id " + id + " no longer exists.", enfe);
            }
            Cartelera idCartelera = sala.getIdCartelera();
            if (idCartelera != null) {
                idCartelera.getSalaList().remove(sala);
                idCartelera = em.merge(idCartelera);
            }
            em.remove(sala);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Sala> findSalaEntities() {
        return findSalaEntities(true, -1, -1);
    }

    public List<Sala> findSalaEntities(int maxResults, int firstResult) {
        return findSalaEntities(false, maxResults, firstResult);
    }

    private List<Sala> findSalaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Sala.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Sala findSala(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Sala.class, id);
        } finally {
            em.close();
        }
    }

    public int getSalaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Sala> rt = cq.from(Sala.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
