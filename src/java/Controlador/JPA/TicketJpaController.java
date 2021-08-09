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
    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("ProyectoCineWEBPU");

    public TicketJpaController() {
    }
    

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Ticket ticket) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Cartelera idcartelera = ticket.getIdcartelera();
            if (idcartelera != null) {
                idcartelera = em.getReference(idcartelera.getClass(), idcartelera.getIdCartelera());
                ticket.setIdcartelera(idcartelera);
            }
            Detallefactura idDetalleFactura = ticket.getIdDetalleFactura();
            if (idDetalleFactura != null) {
                idDetalleFactura = em.getReference(idDetalleFactura.getClass(), idDetalleFactura.getIdDetalleFactura());
                ticket.setIdDetalleFactura(idDetalleFactura);
            }
            em.persist(ticket);
            if (idcartelera != null) {
                idcartelera.getTicketList().add(ticket);
                idcartelera = em.merge(idcartelera);
            }
            if (idDetalleFactura != null) {
                idDetalleFactura.getTicketList().add(ticket);
                idDetalleFactura = em.merge(idDetalleFactura);
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
            Cartelera idcarteleraOld = persistentTicket.getIdcartelera();
            Cartelera idcarteleraNew = ticket.getIdcartelera();
            Detallefactura idDetalleFacturaOld = persistentTicket.getIdDetalleFactura();
            Detallefactura idDetalleFacturaNew = ticket.getIdDetalleFactura();
            if (idcarteleraNew != null) {
                idcarteleraNew = em.getReference(idcarteleraNew.getClass(), idcarteleraNew.getIdCartelera());
                ticket.setIdcartelera(idcarteleraNew);
            }
            if (idDetalleFacturaNew != null) {
                idDetalleFacturaNew = em.getReference(idDetalleFacturaNew.getClass(), idDetalleFacturaNew.getIdDetalleFactura());
                ticket.setIdDetalleFactura(idDetalleFacturaNew);
            }
            ticket = em.merge(ticket);
            if (idcarteleraOld != null && !idcarteleraOld.equals(idcarteleraNew)) {
                idcarteleraOld.getTicketList().remove(ticket);
                idcarteleraOld = em.merge(idcarteleraOld);
            }
            if (idcarteleraNew != null && !idcarteleraNew.equals(idcarteleraOld)) {
                idcarteleraNew.getTicketList().add(ticket);
                idcarteleraNew = em.merge(idcarteleraNew);
            }
            if (idDetalleFacturaOld != null && !idDetalleFacturaOld.equals(idDetalleFacturaNew)) {
                idDetalleFacturaOld.getTicketList().remove(ticket);
                idDetalleFacturaOld = em.merge(idDetalleFacturaOld);
            }
            if (idDetalleFacturaNew != null && !idDetalleFacturaNew.equals(idDetalleFacturaOld)) {
                idDetalleFacturaNew.getTicketList().add(ticket);
                idDetalleFacturaNew = em.merge(idDetalleFacturaNew);
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
            Cartelera idcartelera = ticket.getIdcartelera();
            if (idcartelera != null) {
                idcartelera.getTicketList().remove(ticket);
                idcartelera = em.merge(idcartelera);
            }
            Detallefactura idDetalleFactura = ticket.getIdDetalleFactura();
            if (idDetalleFactura != null) {
                idDetalleFactura.getTicketList().remove(ticket);
                idDetalleFactura = em.merge(idDetalleFactura);
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
