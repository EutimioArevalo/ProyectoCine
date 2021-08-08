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
import Modelo.Detallefactura;
import Modelo.Snack;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author timoa
 */
public class SnackJpaController implements Serializable {

    public SnackJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }

    public SnackJpaController() {
    }
    
    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("ProyectoCineWEBPU");

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Snack snack) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Detallefactura idDetalleFactura = snack.getIdDetalleFactura();
            if (idDetalleFactura != null) {
                idDetalleFactura = em.getReference(idDetalleFactura.getClass(), idDetalleFactura.getIdDetalleFactura());
                snack.setIdDetalleFactura(idDetalleFactura);
            }
            em.persist(snack);
            if (idDetalleFactura != null) {
                idDetalleFactura.getSnackList().add(snack);
                idDetalleFactura = em.merge(idDetalleFactura);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Snack snack) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Snack persistentSnack = em.find(Snack.class, snack.getIdSnack());
            Detallefactura idDetalleFacturaOld = persistentSnack.getIdDetalleFactura();
            Detallefactura idDetalleFacturaNew = snack.getIdDetalleFactura();
            if (idDetalleFacturaNew != null) {
                idDetalleFacturaNew = em.getReference(idDetalleFacturaNew.getClass(), idDetalleFacturaNew.getIdDetalleFactura());
                snack.setIdDetalleFactura(idDetalleFacturaNew);
            }
            snack = em.merge(snack);
            if (idDetalleFacturaOld != null && !idDetalleFacturaOld.equals(idDetalleFacturaNew)) {
                idDetalleFacturaOld.getSnackList().remove(snack);
                idDetalleFacturaOld = em.merge(idDetalleFacturaOld);
            }
            if (idDetalleFacturaNew != null && !idDetalleFacturaNew.equals(idDetalleFacturaOld)) {
                idDetalleFacturaNew.getSnackList().add(snack);
                idDetalleFacturaNew = em.merge(idDetalleFacturaNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = snack.getIdSnack();
                if (findSnack(id) == null) {
                    throw new NonexistentEntityException("The snack with id " + id + " no longer exists.");
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
            Snack snack;
            try {
                snack = em.getReference(Snack.class, id);
                snack.getIdSnack();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The snack with id " + id + " no longer exists.", enfe);
            }
            Detallefactura idDetalleFactura = snack.getIdDetalleFactura();
            if (idDetalleFactura != null) {
                idDetalleFactura.getSnackList().remove(snack);
                idDetalleFactura = em.merge(idDetalleFactura);
            }
            em.remove(snack);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Snack> findSnackEntities() {
        return findSnackEntities(true, -1, -1);
    }

    public List<Snack> findSnackEntities(int maxResults, int firstResult) {
        return findSnackEntities(false, maxResults, firstResult);
    }

    private List<Snack> findSnackEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Snack.class));
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

    public Snack findSnack(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Snack.class, id);
        } finally {
            em.close();
        }
    }

    public int getSnackCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Snack> rt = cq.from(Snack.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
