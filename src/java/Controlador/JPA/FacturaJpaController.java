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
import Modelo.Factura;
import Modelo.Persona;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author timoa
 */
public class FacturaJpaController implements Serializable {

    public FacturaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }

    public FacturaJpaController() {
    }
    
    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("ProyectoCineWEBPU");

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Factura factura) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Detallefactura idDetalle = factura.getIdDetalle();
            if (idDetalle != null) {
                idDetalle = em.getReference(idDetalle.getClass(), idDetalle.getIdDetalle());
                factura.setIdDetalle(idDetalle);
            }
            Persona persona = factura.getPersona();
            if (persona != null) {
                persona = em.getReference(persona.getClass(), persona.getIdPersona());
                factura.setPersona(persona);
            }
            em.persist(factura);
            if (idDetalle != null) {
                idDetalle.getFacturaList().add(factura);
                idDetalle = em.merge(idDetalle);
            }
            if (persona != null) {
                persona.getFacturaList().add(factura);
                persona = em.merge(persona);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Factura factura) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Factura persistentFactura = em.find(Factura.class, factura.getIdFactura());
            Detallefactura idDetalleOld = persistentFactura.getIdDetalle();
            Detallefactura idDetalleNew = factura.getIdDetalle();
            Persona personaOld = persistentFactura.getPersona();
            Persona personaNew = factura.getPersona();
            if (idDetalleNew != null) {
                idDetalleNew = em.getReference(idDetalleNew.getClass(), idDetalleNew.getIdDetalle());
                factura.setIdDetalle(idDetalleNew);
            }
            if (personaNew != null) {
                personaNew = em.getReference(personaNew.getClass(), personaNew.getIdPersona());
                factura.setPersona(personaNew);
            }
            factura = em.merge(factura);
            if (idDetalleOld != null && !idDetalleOld.equals(idDetalleNew)) {
                idDetalleOld.getFacturaList().remove(factura);
                idDetalleOld = em.merge(idDetalleOld);
            }
            if (idDetalleNew != null && !idDetalleNew.equals(idDetalleOld)) {
                idDetalleNew.getFacturaList().add(factura);
                idDetalleNew = em.merge(idDetalleNew);
            }
            if (personaOld != null && !personaOld.equals(personaNew)) {
                personaOld.getFacturaList().remove(factura);
                personaOld = em.merge(personaOld);
            }
            if (personaNew != null && !personaNew.equals(personaOld)) {
                personaNew.getFacturaList().add(factura);
                personaNew = em.merge(personaNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = factura.getIdFactura();
                if (findFactura(id) == null) {
                    throw new NonexistentEntityException("The factura with id " + id + " no longer exists.");
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
            Factura factura;
            try {
                factura = em.getReference(Factura.class, id);
                factura.getIdFactura();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The factura with id " + id + " no longer exists.", enfe);
            }
            Detallefactura idDetalle = factura.getIdDetalle();
            if (idDetalle != null) {
                idDetalle.getFacturaList().remove(factura);
                idDetalle = em.merge(idDetalle);
            }
            Persona persona = factura.getPersona();
            if (persona != null) {
                persona.getFacturaList().remove(factura);
                persona = em.merge(persona);
            }
            em.remove(factura);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Factura> findFacturaEntities() {
        return findFacturaEntities(true, -1, -1);
    }

    public List<Factura> findFacturaEntities(int maxResults, int firstResult) {
        return findFacturaEntities(false, maxResults, firstResult);
    }

    private List<Factura> findFacturaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Factura.class));
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

    public Factura findFactura(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Factura.class, id);
        } finally {
            em.close();
        }
    }

    public int getFacturaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Factura> rt = cq.from(Factura.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
