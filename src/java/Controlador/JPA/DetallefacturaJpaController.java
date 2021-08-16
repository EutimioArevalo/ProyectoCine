/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador.JPA;

import Controlador.JPA.exceptions.IllegalOrphanException;
import Controlador.JPA.exceptions.NonexistentEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import Modelo.Factura;
import java.util.ArrayList;
import java.util.List;
import Modelo.Ticket;
import Modelo.Carrito;
import Modelo.Detallefactura;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author timoa
 */
public class DetallefacturaJpaController implements Serializable {

    public DetallefacturaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }

    public DetallefacturaJpaController() {
    }
    
    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("ProyectoCineWEBPU");

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Detallefactura detallefactura) {
        if (detallefactura.getFacturaList() == null) {
            detallefactura.setFacturaList(new ArrayList<Factura>());
        }
        if (detallefactura.getTicketList() == null) {
            detallefactura.setTicketList(new ArrayList<Ticket>());
        }
        if (detallefactura.getCarritoList() == null) {
            detallefactura.setCarritoList(new ArrayList<Carrito>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Factura> attachedFacturaList = new ArrayList<Factura>();
            for (Factura facturaListFacturaToAttach : detallefactura.getFacturaList()) {
                facturaListFacturaToAttach = em.getReference(facturaListFacturaToAttach.getClass(), facturaListFacturaToAttach.getIdFactura());
                attachedFacturaList.add(facturaListFacturaToAttach);
            }
            detallefactura.setFacturaList(attachedFacturaList);
            List<Ticket> attachedTicketList = new ArrayList<Ticket>();
            for (Ticket ticketListTicketToAttach : detallefactura.getTicketList()) {
                ticketListTicketToAttach = em.getReference(ticketListTicketToAttach.getClass(), ticketListTicketToAttach.getIdTicket());
                attachedTicketList.add(ticketListTicketToAttach);
            }
            detallefactura.setTicketList(attachedTicketList);
            List<Carrito> attachedCarritoList = new ArrayList<Carrito>();
            for (Carrito carritoListCarritoToAttach : detallefactura.getCarritoList()) {
                carritoListCarritoToAttach = em.getReference(carritoListCarritoToAttach.getClass(), carritoListCarritoToAttach.getIdcarrito());
                attachedCarritoList.add(carritoListCarritoToAttach);
            }
            detallefactura.setCarritoList(attachedCarritoList);
            em.persist(detallefactura);
            for (Factura facturaListFactura : detallefactura.getFacturaList()) {
                Detallefactura oldIdDetalleOfFacturaListFactura = facturaListFactura.getIdDetalle();
                facturaListFactura.setIdDetalle(detallefactura);
                facturaListFactura = em.merge(facturaListFactura);
                if (oldIdDetalleOfFacturaListFactura != null) {
                    oldIdDetalleOfFacturaListFactura.getFacturaList().remove(facturaListFactura);
                    oldIdDetalleOfFacturaListFactura = em.merge(oldIdDetalleOfFacturaListFactura);
                }
            }
            for (Ticket ticketListTicket : detallefactura.getTicketList()) {
                Detallefactura oldIdDetalleOfTicketListTicket = ticketListTicket.getIdDetalle();
                ticketListTicket.setIdDetalle(detallefactura);
                ticketListTicket = em.merge(ticketListTicket);
                if (oldIdDetalleOfTicketListTicket != null) {
                    oldIdDetalleOfTicketListTicket.getTicketList().remove(ticketListTicket);
                    oldIdDetalleOfTicketListTicket = em.merge(oldIdDetalleOfTicketListTicket);
                }
            }
            for (Carrito carritoListCarrito : detallefactura.getCarritoList()) {
                Detallefactura oldIdDetalleOfCarritoListCarrito = carritoListCarrito.getIdDetalle();
                carritoListCarrito.setIdDetalle(detallefactura);
                carritoListCarrito = em.merge(carritoListCarrito);
                if (oldIdDetalleOfCarritoListCarrito != null) {
                    oldIdDetalleOfCarritoListCarrito.getCarritoList().remove(carritoListCarrito);
                    oldIdDetalleOfCarritoListCarrito = em.merge(oldIdDetalleOfCarritoListCarrito);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Detallefactura detallefactura) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Detallefactura persistentDetallefactura = em.find(Detallefactura.class, detallefactura.getIdDetalle());
            List<Factura> facturaListOld = persistentDetallefactura.getFacturaList();
            List<Factura> facturaListNew = detallefactura.getFacturaList();
            List<Ticket> ticketListOld = persistentDetallefactura.getTicketList();
            List<Ticket> ticketListNew = detallefactura.getTicketList();
            List<Carrito> carritoListOld = persistentDetallefactura.getCarritoList();
            List<Carrito> carritoListNew = detallefactura.getCarritoList();
            List<String> illegalOrphanMessages = null;
            for (Factura facturaListOldFactura : facturaListOld) {
                if (!facturaListNew.contains(facturaListOldFactura)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Factura " + facturaListOldFactura + " since its idDetalle field is not nullable.");
                }
            }
            for (Ticket ticketListOldTicket : ticketListOld) {
                if (!ticketListNew.contains(ticketListOldTicket)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Ticket " + ticketListOldTicket + " since its idDetalle field is not nullable.");
                }
            }
            for (Carrito carritoListOldCarrito : carritoListOld) {
                if (!carritoListNew.contains(carritoListOldCarrito)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Carrito " + carritoListOldCarrito + " since its idDetalle field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            List<Factura> attachedFacturaListNew = new ArrayList<Factura>();
            for (Factura facturaListNewFacturaToAttach : facturaListNew) {
                facturaListNewFacturaToAttach = em.getReference(facturaListNewFacturaToAttach.getClass(), facturaListNewFacturaToAttach.getIdFactura());
                attachedFacturaListNew.add(facturaListNewFacturaToAttach);
            }
            facturaListNew = attachedFacturaListNew;
            detallefactura.setFacturaList(facturaListNew);
            List<Ticket> attachedTicketListNew = new ArrayList<Ticket>();
            for (Ticket ticketListNewTicketToAttach : ticketListNew) {
                ticketListNewTicketToAttach = em.getReference(ticketListNewTicketToAttach.getClass(), ticketListNewTicketToAttach.getIdTicket());
                attachedTicketListNew.add(ticketListNewTicketToAttach);
            }
            ticketListNew = attachedTicketListNew;
            detallefactura.setTicketList(ticketListNew);
            List<Carrito> attachedCarritoListNew = new ArrayList<Carrito>();
            for (Carrito carritoListNewCarritoToAttach : carritoListNew) {
                carritoListNewCarritoToAttach = em.getReference(carritoListNewCarritoToAttach.getClass(), carritoListNewCarritoToAttach.getIdcarrito());
                attachedCarritoListNew.add(carritoListNewCarritoToAttach);
            }
            carritoListNew = attachedCarritoListNew;
            detallefactura.setCarritoList(carritoListNew);
            detallefactura = em.merge(detallefactura);
            for (Factura facturaListNewFactura : facturaListNew) {
                if (!facturaListOld.contains(facturaListNewFactura)) {
                    Detallefactura oldIdDetalleOfFacturaListNewFactura = facturaListNewFactura.getIdDetalle();
                    facturaListNewFactura.setIdDetalle(detallefactura);
                    facturaListNewFactura = em.merge(facturaListNewFactura);
                    if (oldIdDetalleOfFacturaListNewFactura != null && !oldIdDetalleOfFacturaListNewFactura.equals(detallefactura)) {
                        oldIdDetalleOfFacturaListNewFactura.getFacturaList().remove(facturaListNewFactura);
                        oldIdDetalleOfFacturaListNewFactura = em.merge(oldIdDetalleOfFacturaListNewFactura);
                    }
                }
            }
            for (Ticket ticketListNewTicket : ticketListNew) {
                if (!ticketListOld.contains(ticketListNewTicket)) {
                    Detallefactura oldIdDetalleOfTicketListNewTicket = ticketListNewTicket.getIdDetalle();
                    ticketListNewTicket.setIdDetalle(detallefactura);
                    ticketListNewTicket = em.merge(ticketListNewTicket);
                    if (oldIdDetalleOfTicketListNewTicket != null && !oldIdDetalleOfTicketListNewTicket.equals(detallefactura)) {
                        oldIdDetalleOfTicketListNewTicket.getTicketList().remove(ticketListNewTicket);
                        oldIdDetalleOfTicketListNewTicket = em.merge(oldIdDetalleOfTicketListNewTicket);
                    }
                }
            }
            for (Carrito carritoListNewCarrito : carritoListNew) {
                if (!carritoListOld.contains(carritoListNewCarrito)) {
                    Detallefactura oldIdDetalleOfCarritoListNewCarrito = carritoListNewCarrito.getIdDetalle();
                    carritoListNewCarrito.setIdDetalle(detallefactura);
                    carritoListNewCarrito = em.merge(carritoListNewCarrito);
                    if (oldIdDetalleOfCarritoListNewCarrito != null && !oldIdDetalleOfCarritoListNewCarrito.equals(detallefactura)) {
                        oldIdDetalleOfCarritoListNewCarrito.getCarritoList().remove(carritoListNewCarrito);
                        oldIdDetalleOfCarritoListNewCarrito = em.merge(oldIdDetalleOfCarritoListNewCarrito);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = detallefactura.getIdDetalle();
                if (findDetallefactura(id) == null) {
                    throw new NonexistentEntityException("The detallefactura with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws IllegalOrphanException, NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Detallefactura detallefactura;
            try {
                detallefactura = em.getReference(Detallefactura.class, id);
                detallefactura.getIdDetalle();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The detallefactura with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Factura> facturaListOrphanCheck = detallefactura.getFacturaList();
            for (Factura facturaListOrphanCheckFactura : facturaListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Detallefactura (" + detallefactura + ") cannot be destroyed since the Factura " + facturaListOrphanCheckFactura + " in its facturaList field has a non-nullable idDetalle field.");
            }
            List<Ticket> ticketListOrphanCheck = detallefactura.getTicketList();
            for (Ticket ticketListOrphanCheckTicket : ticketListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Detallefactura (" + detallefactura + ") cannot be destroyed since the Ticket " + ticketListOrphanCheckTicket + " in its ticketList field has a non-nullable idDetalle field.");
            }
            List<Carrito> carritoListOrphanCheck = detallefactura.getCarritoList();
            for (Carrito carritoListOrphanCheckCarrito : carritoListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Detallefactura (" + detallefactura + ") cannot be destroyed since the Carrito " + carritoListOrphanCheckCarrito + " in its carritoList field has a non-nullable idDetalle field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(detallefactura);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Detallefactura> findDetallefacturaEntities() {
        return findDetallefacturaEntities(true, -1, -1);
    }

    public List<Detallefactura> findDetallefacturaEntities(int maxResults, int firstResult) {
        return findDetallefacturaEntities(false, maxResults, firstResult);
    }

    private List<Detallefactura> findDetallefacturaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Detallefactura.class));
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

    public Detallefactura findDetallefactura(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Detallefactura.class, id);
        } finally {
            em.close();
        }
    }

    public int getDetallefacturaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Detallefactura> rt = cq.from(Detallefactura.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
