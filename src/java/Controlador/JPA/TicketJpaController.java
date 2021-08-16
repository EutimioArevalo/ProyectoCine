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
import Modelo.Detallefactura;
import Modelo.Ticket;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author timoa
 */
public class TicketJpaController implements Serializable {

    public TicketJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    } 

    public TicketJpaController() {
    }
    
    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("ProyectoCineWEBPU");

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Ticket ticket) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Cartelera idCartelera = ticket.getIdCartelera();
            if (idCartelera != null) {
                idCartelera = em.getReference(idCartelera.getClass(), idCartelera.getIdCartelera());
                ticket.setIdCartelera(idCartelera);
            }
            Detallefactura idDetalle = ticket.getIdDetalle();
            if (idDetalle != null) {
                idDetalle = em.getReference(idDetalle.getClass(), idDetalle.getIdDetalle());
                ticket.setIdDetalle(idDetalle);
            }
            em.persist(ticket);
            if (idCartelera != null) {
                idCartelera.getTicketList().add(ticket);
                idCartelera = em.merge(idCartelera);
            }
            if (idDetalle != null) {
                idDetalle.getTicketList().add(ticket);
                idDetalle = em.merge(idDetalle);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Ticket ticket) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Ticket persistentTicket = em.find(Ticket.class, ticket.getIdTicket());
            Cartelera idCarteleraOld = persistentTicket.getIdCartelera();
            Cartelera idCarteleraNew = ticket.getIdCartelera();
            Detallefactura idDetalleOld = persistentTicket.getIdDetalle();
            Detallefactura idDetalleNew = ticket.getIdDetalle();
            if (idCarteleraNew != null) {
                idCarteleraNew = em.getReference(idCarteleraNew.getClass(), idCarteleraNew.getIdCartelera());
                ticket.setIdCartelera(idCarteleraNew);
            }
            if (idDetalleNew != null) {
                idDetalleNew = em.getReference(idDetalleNew.getClass(), idDetalleNew.getIdDetalle());
                ticket.setIdDetalle(idDetalleNew);
            }
            ticket = em.merge(ticket);
            if (idCarteleraOld != null && !idCarteleraOld.equals(idCarteleraNew)) {
                idCarteleraOld.getTicketList().remove(ticket);
                idCarteleraOld = em.merge(idCarteleraOld);
            }
            if (idCarteleraNew != null && !idCarteleraNew.equals(idCarteleraOld)) {
                idCarteleraNew.getTicketList().add(ticket);
                idCarteleraNew = em.merge(idCarteleraNew);
            }
            if (idDetalleOld != null && !idDetalleOld.equals(idDetalleNew)) {
                idDetalleOld.getTicketList().remove(ticket);
                idDetalleOld = em.merge(idDetalleOld);
            }
            if (idDetalleNew != null && !idDetalleNew.equals(idDetalleOld)) {
                idDetalleNew.getTicketList().add(ticket);
                idDetalleNew = em.merge(idDetalleNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = ticket.getIdTicket();
                if (findTicket(id) == null) {
                    throw new NonexistentEntityException("The ticket with id " + id + " no longer exists.");
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
            Ticket ticket;
            try {
                ticket = em.getReference(Ticket.class, id);
                ticket.getIdTicket();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The ticket with id " + id + " no longer exists.", enfe);
            }
            Cartelera idCartelera = ticket.getIdCartelera();
            if (idCartelera != null) {
                idCartelera.getTicketList().remove(ticket);
                idCartelera = em.merge(idCartelera);
            }
            Detallefactura idDetalle = ticket.getIdDetalle();
            if (idDetalle != null) {
                idDetalle.getTicketList().remove(ticket);
                idDetalle = em.merge(idDetalle);
            }
            em.remove(ticket);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Ticket> findTicketEntities() {
        return findTicketEntities(true, -1, -1);
    }

    public List<Ticket> findTicketEntities(int maxResults, int firstResult) {
        return findTicketEntities(false, maxResults, firstResult);
    }

    private List<Ticket> findTicketEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Ticket.class));
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

    public Ticket findTicket(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Ticket.class, id);
        } finally {
            em.close();
        }
    }

    public int getTicketCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Ticket> rt = cq.from(Ticket.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
