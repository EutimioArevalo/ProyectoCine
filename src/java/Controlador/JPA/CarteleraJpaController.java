/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador.JPA;

import Controlador.JPA.exceptions.IllegalOrphanException;
import Controlador.JPA.exceptions.NonexistentEntityException;
import Modelo.Cartelera;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import Modelo.Pelicula;
import Modelo.Sala;
import Modelo.Ticket;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author timoa
 */
public class CarteleraJpaController implements Serializable {

    public CarteleraJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }

    public CarteleraJpaController() {
    }
    
    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("ProyectoCineWEBPU");

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Cartelera cartelera) {
        if (cartelera.getTicketList() == null) {
            cartelera.setTicketList(new ArrayList<Ticket>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Pelicula idPelicula = cartelera.getIdPelicula();
            if (idPelicula != null) {
                idPelicula = em.getReference(idPelicula.getClass(), idPelicula.getIdPelicula());
                cartelera.setIdPelicula(idPelicula);
            }
            Sala idSala = cartelera.getIdSala();
            if (idSala != null) {
                idSala = em.getReference(idSala.getClass(), idSala.getIdSala());
                cartelera.setIdSala(idSala);
            }
            List<Ticket> attachedTicketList = new ArrayList<Ticket>();
            for (Ticket ticketListTicketToAttach : cartelera.getTicketList()) {
                ticketListTicketToAttach = em.getReference(ticketListTicketToAttach.getClass(), ticketListTicketToAttach.getIdTicket());
                attachedTicketList.add(ticketListTicketToAttach);
            }
            cartelera.setTicketList(attachedTicketList);
            em.persist(cartelera);
            if (idPelicula != null) {
                idPelicula.getCarteleraList().add(cartelera);
                idPelicula = em.merge(idPelicula);
            }
            if (idSala != null) {
                idSala.getCarteleraList().add(cartelera);
                idSala = em.merge(idSala);
            }
            for (Ticket ticketListTicket : cartelera.getTicketList()) {
                Cartelera oldIdCarteleraOfTicketListTicket = ticketListTicket.getIdCartelera();
                ticketListTicket.setIdCartelera(cartelera);
                ticketListTicket = em.merge(ticketListTicket);
                if (oldIdCarteleraOfTicketListTicket != null) {
                    oldIdCarteleraOfTicketListTicket.getTicketList().remove(ticketListTicket);
                    oldIdCarteleraOfTicketListTicket = em.merge(oldIdCarteleraOfTicketListTicket);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Cartelera cartelera) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Cartelera persistentCartelera = em.find(Cartelera.class, cartelera.getIdCartelera());
            Pelicula idPeliculaOld = persistentCartelera.getIdPelicula();
            Pelicula idPeliculaNew = cartelera.getIdPelicula();
            Sala idSalaOld = persistentCartelera.getIdSala();
            Sala idSalaNew = cartelera.getIdSala();
            List<Ticket> ticketListOld = persistentCartelera.getTicketList();
            List<Ticket> ticketListNew = cartelera.getTicketList();
            List<String> illegalOrphanMessages = null;
            for (Ticket ticketListOldTicket : ticketListOld) {
                if (!ticketListNew.contains(ticketListOldTicket)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Ticket " + ticketListOldTicket + " since its idCartelera field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (idPeliculaNew != null) {
                idPeliculaNew = em.getReference(idPeliculaNew.getClass(), idPeliculaNew.getIdPelicula());
                cartelera.setIdPelicula(idPeliculaNew);
            }
            if (idSalaNew != null) {
                idSalaNew = em.getReference(idSalaNew.getClass(), idSalaNew.getIdSala());
                cartelera.setIdSala(idSalaNew);
            }
            List<Ticket> attachedTicketListNew = new ArrayList<Ticket>();
            for (Ticket ticketListNewTicketToAttach : ticketListNew) {
                ticketListNewTicketToAttach = em.getReference(ticketListNewTicketToAttach.getClass(), ticketListNewTicketToAttach.getIdTicket());
                attachedTicketListNew.add(ticketListNewTicketToAttach);
            }
            ticketListNew = attachedTicketListNew;
            cartelera.setTicketList(ticketListNew);
            cartelera = em.merge(cartelera);
            if (idPeliculaOld != null && !idPeliculaOld.equals(idPeliculaNew)) {
                idPeliculaOld.getCarteleraList().remove(cartelera);
                idPeliculaOld = em.merge(idPeliculaOld);
            }
            if (idPeliculaNew != null && !idPeliculaNew.equals(idPeliculaOld)) {
                idPeliculaNew.getCarteleraList().add(cartelera);
                idPeliculaNew = em.merge(idPeliculaNew);
            }
            if (idSalaOld != null && !idSalaOld.equals(idSalaNew)) {
                idSalaOld.getCarteleraList().remove(cartelera);
                idSalaOld = em.merge(idSalaOld);
            }
            if (idSalaNew != null && !idSalaNew.equals(idSalaOld)) {
                idSalaNew.getCarteleraList().add(cartelera);
                idSalaNew = em.merge(idSalaNew);
            }
            for (Ticket ticketListNewTicket : ticketListNew) {
                if (!ticketListOld.contains(ticketListNewTicket)) {
                    Cartelera oldIdCarteleraOfTicketListNewTicket = ticketListNewTicket.getIdCartelera();
                    ticketListNewTicket.setIdCartelera(cartelera);
                    ticketListNewTicket = em.merge(ticketListNewTicket);
                    if (oldIdCarteleraOfTicketListNewTicket != null && !oldIdCarteleraOfTicketListNewTicket.equals(cartelera)) {
                        oldIdCarteleraOfTicketListNewTicket.getTicketList().remove(ticketListNewTicket);
                        oldIdCarteleraOfTicketListNewTicket = em.merge(oldIdCarteleraOfTicketListNewTicket);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = cartelera.getIdCartelera();
                if (findCartelera(id) == null) {
                    throw new NonexistentEntityException("The cartelera with id " + id + " no longer exists.");
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
            Cartelera cartelera;
            try {
                cartelera = em.getReference(Cartelera.class, id);
                cartelera.getIdCartelera();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The cartelera with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Ticket> ticketListOrphanCheck = cartelera.getTicketList();
            for (Ticket ticketListOrphanCheckTicket : ticketListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Cartelera (" + cartelera + ") cannot be destroyed since the Ticket " + ticketListOrphanCheckTicket + " in its ticketList field has a non-nullable idCartelera field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Pelicula idPelicula = cartelera.getIdPelicula();
            if (idPelicula != null) {
                idPelicula.getCarteleraList().remove(cartelera);
                idPelicula = em.merge(idPelicula);
            }
            Sala idSala = cartelera.getIdSala();
            if (idSala != null) {
                idSala.getCarteleraList().remove(cartelera);
                idSala = em.merge(idSala);
            }
            em.remove(cartelera);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Cartelera> findCarteleraEntities() {
        return findCarteleraEntities(true, -1, -1);
    }

    public List<Cartelera> findCarteleraEntities(int maxResults, int firstResult) {
        return findCarteleraEntities(false, maxResults, firstResult);
    }

    private List<Cartelera> findCarteleraEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Cartelera.class));
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

    public Cartelera findCartelera(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Cartelera.class, id);
        } finally {
            em.close();
        }
    }

    public int getCarteleraCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Cartelera> rt = cq.from(Cartelera.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
