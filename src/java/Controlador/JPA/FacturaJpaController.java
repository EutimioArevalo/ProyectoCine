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
            Detallefactura idDetalleFactura = factura.getIdDetalleFactura();
            if (idDetalleFactura != null) {
                idDetalleFactura = em.getReference(idDetalleFactura.getClass(), idDetalleFactura.getIdDetalleFactura());
                factura.setIdDetalleFactura(idDetalleFactura);
            }
            Persona persona = factura.getPersona();
            if (persona != null) {
                persona = em.getReference(persona.getClass(), persona.getIdPersona());
                factura.setPersona(persona);
            }
            em.persist(factura);
            if (idDetalleFactura != null) {
                idDetalleFactura.getFacturaList().add(factura);
                idDetalleFactura = em.merge(idDetalleFactura);
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
            Detallefactura idDetalleFacturaOld = persistentFactura.getIdDetalleFactura();
            Detallefactura idDetalleFacturaNew = factura.getIdDetalleFactura();
            Persona personaOld = persistentFactura.getPersona();
            Persona personaNew = factura.getPersona();
            if (idDetalleFacturaNew != null) {
                idDetalleFacturaNew = em.getReference(idDetalleFacturaNew.getClass(), idDetalleFacturaNew.getIdDetalleFactura());
                factura.setIdDetalleFactura(idDetalleFacturaNew);
            }
            if (personaNew != null) {
                personaNew = em.getReference(personaNew.getClass(), personaNew.getIdPersona());
                factura.setPersona(personaNew);
            }
            factura = em.merge(factura);
            if (idDetalleFacturaOld != null && !idDetalleFacturaOld.equals(idDetalleFacturaNew)) {
                idDetalleFacturaOld.getFacturaList().remove(factura);
                idDetalleFacturaOld = em.merge(idDetalleFacturaOld);
            }
            if (idDetalleFacturaNew != null && !idDetalleFacturaNew.equals(idDetalleFacturaOld)) {
                idDetalleFacturaNew.getFacturaList().add(factura);
                idDetalleFacturaNew = em.merge(idDetalleFacturaNew);
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
            Detallefactura idDetalleFactura = factura.getIdDetalleFactura();
            if (idDetalleFactura != null) {
                idDetalleFactura.getFacturaList().remove(factura);
                idDetalleFactura = em.merge(idDetalleFactura);
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
