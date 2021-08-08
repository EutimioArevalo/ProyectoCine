/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador.JPA;

import Controlador.JPA.exceptions.IllegalOrphanException;
import Controlador.JPA.exceptions.NonexistentEntityException;
import Modelo.Detallefactura;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import Modelo.Factura;
import java.util.ArrayList;
import java.util.List;
import Modelo.Ticket;
import Modelo.Snack;
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
        if (detallefactura.getSnackList() == null) {
            detallefactura.setSnackList(new ArrayList<Snack>());
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
            List<Snack> attachedSnackList = new ArrayList<Snack>();
            for (Snack snackListSnackToAttach : detallefactura.getSnackList()) {
                snackListSnackToAttach = em.getReference(snackListSnackToAttach.getClass(), snackListSnackToAttach.getIdSnack());
                attachedSnackList.add(snackListSnackToAttach);
            }
            detallefactura.setSnackList(attachedSnackList);
            em.persist(detallefactura);
            for (Factura facturaListFactura : detallefactura.getFacturaList()) {
                Detallefactura oldIdDetalleFacturaOfFacturaListFactura = facturaListFactura.getIdDetalleFactura();
                facturaListFactura.setIdDetalleFactura(detallefactura);
                facturaListFactura = em.merge(facturaListFactura);
                if (oldIdDetalleFacturaOfFacturaListFactura != null) {
                    oldIdDetalleFacturaOfFacturaListFactura.getFacturaList().remove(facturaListFactura);
                    oldIdDetalleFacturaOfFacturaListFactura = em.merge(oldIdDetalleFacturaOfFacturaListFactura);
                }
            }
            for (Ticket ticketListTicket : detallefactura.getTicketList()) {
                Detallefactura oldIdDetalleFacturaOfTicketListTicket = ticketListTicket.getIdDetalleFactura();
                ticketListTicket.setIdDetalleFactura(detallefactura);
                ticketListTicket = em.merge(ticketListTicket);
                if (oldIdDetalleFacturaOfTicketListTicket != null) {
                    oldIdDetalleFacturaOfTicketListTicket.getTicketList().remove(ticketListTicket);
                    oldIdDetalleFacturaOfTicketListTicket = em.merge(oldIdDetalleFacturaOfTicketListTicket);
                }
            }
            for (Snack snackListSnack : detallefactura.getSnackList()) {
                Detallefactura oldIdDetalleFacturaOfSnackListSnack = snackListSnack.getIdDetalleFactura();
                snackListSnack.setIdDetalleFactura(detallefactura);
                snackListSnack = em.merge(snackListSnack);
                if (oldIdDetalleFacturaOfSnackListSnack != null) {
                    oldIdDetalleFacturaOfSnackListSnack.getSnackList().remove(snackListSnack);
                    oldIdDetalleFacturaOfSnackListSnack = em.merge(oldIdDetalleFacturaOfSnackListSnack);
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
            Detallefactura persistentDetallefactura = em.find(Detallefactura.class, detallefactura.getIdDetalleFactura());
            List<Factura> facturaListOld = persistentDetallefactura.getFacturaList();
            List<Factura> facturaListNew = detallefactura.getFacturaList();
            List<Ticket> ticketListOld = persistentDetallefactura.getTicketList();
            List<Ticket> ticketListNew = detallefactura.getTicketList();
            List<Snack> snackListOld = persistentDetallefactura.getSnackList();
            List<Snack> snackListNew = detallefactura.getSnackList();
            List<String> illegalOrphanMessages = null;
            for (Factura facturaListOldFactura : facturaListOld) {
                if (!facturaListNew.contains(facturaListOldFactura)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Factura " + facturaListOldFactura + " since its idDetalleFactura field is not nullable.");
                }
            }
            for (Ticket ticketListOldTicket : ticketListOld) {
                if (!ticketListNew.contains(ticketListOldTicket)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Ticket " + ticketListOldTicket + " since its idDetalleFactura field is not nullable.");
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
            List<Snack> attachedSnackListNew = new ArrayList<Snack>();
            for (Snack snackListNewSnackToAttach : snackListNew) {
                snackListNewSnackToAttach = em.getReference(snackListNewSnackToAttach.getClass(), snackListNewSnackToAttach.getIdSnack());
                attachedSnackListNew.add(snackListNewSnackToAttach);
            }
            snackListNew = attachedSnackListNew;
            detallefactura.setSnackList(snackListNew);
            detallefactura = em.merge(detallefactura);
            for (Factura facturaListNewFactura : facturaListNew) {
                if (!facturaListOld.contains(facturaListNewFactura)) {
                    Detallefactura oldIdDetalleFacturaOfFacturaListNewFactura = facturaListNewFactura.getIdDetalleFactura();
                    facturaListNewFactura.setIdDetalleFactura(detallefactura);
                    facturaListNewFactura = em.merge(facturaListNewFactura);
                    if (oldIdDetalleFacturaOfFacturaListNewFactura != null && !oldIdDetalleFacturaOfFacturaListNewFactura.equals(detallefactura)) {
                        oldIdDetalleFacturaOfFacturaListNewFactura.getFacturaList().remove(facturaListNewFactura);
                        oldIdDetalleFacturaOfFacturaListNewFactura = em.merge(oldIdDetalleFacturaOfFacturaListNewFactura);
                    }
                }
            }
            for (Ticket ticketListNewTicket : ticketListNew) {
                if (!ticketListOld.contains(ticketListNewTicket)) {
                    Detallefactura oldIdDetalleFacturaOfTicketListNewTicket = ticketListNewTicket.getIdDetalleFactura();
                    ticketListNewTicket.setIdDetalleFactura(detallefactura);
                    ticketListNewTicket = em.merge(ticketListNewTicket);
                    if (oldIdDetalleFacturaOfTicketListNewTicket != null && !oldIdDetalleFacturaOfTicketListNewTicket.equals(detallefactura)) {
                        oldIdDetalleFacturaOfTicketListNewTicket.getTicketList().remove(ticketListNewTicket);
                        oldIdDetalleFacturaOfTicketListNewTicket = em.merge(oldIdDetalleFacturaOfTicketListNewTicket);
                    }
                }
            }
            for (Snack snackListOldSnack : snackListOld) {
                if (!snackListNew.contains(snackListOldSnack)) {
                    snackListOldSnack.setIdDetalleFactura(null);
                    snackListOldSnack = em.merge(snackListOldSnack);
                }
            }
            for (Snack snackListNewSnack : snackListNew) {
                if (!snackListOld.contains(snackListNewSnack)) {
                    Detallefactura oldIdDetalleFacturaOfSnackListNewSnack = snackListNewSnack.getIdDetalleFactura();
                    snackListNewSnack.setIdDetalleFactura(detallefactura);
                    snackListNewSnack = em.merge(snackListNewSnack);
                    if (oldIdDetalleFacturaOfSnackListNewSnack != null && !oldIdDetalleFacturaOfSnackListNewSnack.equals(detallefactura)) {
                        oldIdDetalleFacturaOfSnackListNewSnack.getSnackList().remove(snackListNewSnack);
                        oldIdDetalleFacturaOfSnackListNewSnack = em.merge(oldIdDetalleFacturaOfSnackListNewSnack);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = detallefactura.getIdDetalleFactura();
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
                detallefactura.getIdDetalleFactura();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The detallefactura with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Factura> facturaListOrphanCheck = detallefactura.getFacturaList();
            for (Factura facturaListOrphanCheckFactura : facturaListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Detallefactura (" + detallefactura + ") cannot be destroyed since the Factura " + facturaListOrphanCheckFactura + " in its facturaList field has a non-nullable idDetalleFactura field.");
            }
            List<Ticket> ticketListOrphanCheck = detallefactura.getTicketList();
            for (Ticket ticketListOrphanCheckTicket : ticketListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Detallefactura (" + detallefactura + ") cannot be destroyed since the Ticket " + ticketListOrphanCheckTicket + " in its ticketList field has a non-nullable idDetalleFactura field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            List<Snack> snackList = detallefactura.getSnackList();
            for (Snack snackListSnack : snackList) {
                snackListSnack.setIdDetalleFactura(null);
                snackListSnack = em.merge(snackListSnack);
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
