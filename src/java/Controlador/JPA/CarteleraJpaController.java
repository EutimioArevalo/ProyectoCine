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
import java.util.ArrayList;
import java.util.List;
import Modelo.Ticket;
import Modelo.Sala;
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
        if (cartelera.getPeliculaList() == null) {
            cartelera.setPeliculaList(new ArrayList<Pelicula>());
        }
        if (cartelera.getTicketList() == null) {
            cartelera.setTicketList(new ArrayList<Ticket>());
        }
        if (cartelera.getSalaList() == null) {
            cartelera.setSalaList(new ArrayList<Sala>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Pelicula> attachedPeliculaList = new ArrayList<Pelicula>();
            for (Pelicula peliculaListPeliculaToAttach : cartelera.getPeliculaList()) {
                peliculaListPeliculaToAttach = em.getReference(peliculaListPeliculaToAttach.getClass(), peliculaListPeliculaToAttach.getIdPelicula());
                attachedPeliculaList.add(peliculaListPeliculaToAttach);
            }
            cartelera.setPeliculaList(attachedPeliculaList);
            List<Ticket> attachedTicketList = new ArrayList<Ticket>();
            for (Ticket ticketListTicketToAttach : cartelera.getTicketList()) {
                ticketListTicketToAttach = em.getReference(ticketListTicketToAttach.getClass(), ticketListTicketToAttach.getIdTicket());
                attachedTicketList.add(ticketListTicketToAttach);
            }
            cartelera.setTicketList(attachedTicketList);
            List<Sala> attachedSalaList = new ArrayList<Sala>();
            for (Sala salaListSalaToAttach : cartelera.getSalaList()) {
                salaListSalaToAttach = em.getReference(salaListSalaToAttach.getClass(), salaListSalaToAttach.getIdSala());
                attachedSalaList.add(salaListSalaToAttach);
            }
            cartelera.setSalaList(attachedSalaList);
            em.persist(cartelera);
            for (Pelicula peliculaListPelicula : cartelera.getPeliculaList()) {
                Cartelera oldIdCarteleraOfPeliculaListPelicula = peliculaListPelicula.getIdCartelera();
                peliculaListPelicula.setIdCartelera(cartelera);
                peliculaListPelicula = em.merge(peliculaListPelicula);
                if (oldIdCarteleraOfPeliculaListPelicula != null) {
                    oldIdCarteleraOfPeliculaListPelicula.getPeliculaList().remove(peliculaListPelicula);
                    oldIdCarteleraOfPeliculaListPelicula = em.merge(oldIdCarteleraOfPeliculaListPelicula);
                }
            }
            for (Ticket ticketListTicket : cartelera.getTicketList()) {
                Cartelera oldIdcarteleraOfTicketListTicket = ticketListTicket.getIdcartelera();
                ticketListTicket.setIdcartelera(cartelera);
                ticketListTicket = em.merge(ticketListTicket);
                if (oldIdcarteleraOfTicketListTicket != null) {
                    oldIdcarteleraOfTicketListTicket.getTicketList().remove(ticketListTicket);
                    oldIdcarteleraOfTicketListTicket = em.merge(oldIdcarteleraOfTicketListTicket);
                }
            }
            for (Sala salaListSala : cartelera.getSalaList()) {
                Cartelera oldIdCarteleraOfSalaListSala = salaListSala.getIdCartelera();
                salaListSala.setIdCartelera(cartelera);
                salaListSala = em.merge(salaListSala);
                if (oldIdCarteleraOfSalaListSala != null) {
                    oldIdCarteleraOfSalaListSala.getSalaList().remove(salaListSala);
                    oldIdCarteleraOfSalaListSala = em.merge(oldIdCarteleraOfSalaListSala);
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
            List<Pelicula> peliculaListOld = persistentCartelera.getPeliculaList();
            List<Pelicula> peliculaListNew = cartelera.getPeliculaList();
            List<Ticket> ticketListOld = persistentCartelera.getTicketList();
            List<Ticket> ticketListNew = cartelera.getTicketList();
            List<Sala> salaListOld = persistentCartelera.getSalaList();
            List<Sala> salaListNew = cartelera.getSalaList();
            List<String> illegalOrphanMessages = null;
            for (Pelicula peliculaListOldPelicula : peliculaListOld) {
                if (!peliculaListNew.contains(peliculaListOldPelicula)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Pelicula " + peliculaListOldPelicula + " since its idCartelera field is not nullable.");
                }
            }
            for (Ticket ticketListOldTicket : ticketListOld) {
                if (!ticketListNew.contains(ticketListOldTicket)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Ticket " + ticketListOldTicket + " since its idcartelera field is not nullable.");
                }
            }
            for (Sala salaListOldSala : salaListOld) {
                if (!salaListNew.contains(salaListOldSala)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Sala " + salaListOldSala + " since its idCartelera field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            List<Pelicula> attachedPeliculaListNew = new ArrayList<Pelicula>();
            for (Pelicula peliculaListNewPeliculaToAttach : peliculaListNew) {
                peliculaListNewPeliculaToAttach = em.getReference(peliculaListNewPeliculaToAttach.getClass(), peliculaListNewPeliculaToAttach.getIdPelicula());
                attachedPeliculaListNew.add(peliculaListNewPeliculaToAttach);
            }
            peliculaListNew = attachedPeliculaListNew;
            cartelera.setPeliculaList(peliculaListNew);
            List<Ticket> attachedTicketListNew = new ArrayList<Ticket>();
            for (Ticket ticketListNewTicketToAttach : ticketListNew) {
                ticketListNewTicketToAttach = em.getReference(ticketListNewTicketToAttach.getClass(), ticketListNewTicketToAttach.getIdTicket());
                attachedTicketListNew.add(ticketListNewTicketToAttach);
            }
            ticketListNew = attachedTicketListNew;
            cartelera.setTicketList(ticketListNew);
            List<Sala> attachedSalaListNew = new ArrayList<Sala>();
            for (Sala salaListNewSalaToAttach : salaListNew) {
                salaListNewSalaToAttach = em.getReference(salaListNewSalaToAttach.getClass(), salaListNewSalaToAttach.getIdSala());
                attachedSalaListNew.add(salaListNewSalaToAttach);
            }
            salaListNew = attachedSalaListNew;
            cartelera.setSalaList(salaListNew);
            cartelera = em.merge(cartelera);
            for (Pelicula peliculaListNewPelicula : peliculaListNew) {
                if (!peliculaListOld.contains(peliculaListNewPelicula)) {
                    Cartelera oldIdCarteleraOfPeliculaListNewPelicula = peliculaListNewPelicula.getIdCartelera();
                    peliculaListNewPelicula.setIdCartelera(cartelera);
                    peliculaListNewPelicula = em.merge(peliculaListNewPelicula);
                    if (oldIdCarteleraOfPeliculaListNewPelicula != null && !oldIdCarteleraOfPeliculaListNewPelicula.equals(cartelera)) {
                        oldIdCarteleraOfPeliculaListNewPelicula.getPeliculaList().remove(peliculaListNewPelicula);
                        oldIdCarteleraOfPeliculaListNewPelicula = em.merge(oldIdCarteleraOfPeliculaListNewPelicula);
                    }
                }
            }
            for (Ticket ticketListNewTicket : ticketListNew) {
                if (!ticketListOld.contains(ticketListNewTicket)) {
                    Cartelera oldIdcarteleraOfTicketListNewTicket = ticketListNewTicket.getIdcartelera();
                    ticketListNewTicket.setIdcartelera(cartelera);
                    ticketListNewTicket = em.merge(ticketListNewTicket);
                    if (oldIdcarteleraOfTicketListNewTicket != null && !oldIdcarteleraOfTicketListNewTicket.equals(cartelera)) {
                        oldIdcarteleraOfTicketListNewTicket.getTicketList().remove(ticketListNewTicket);
                        oldIdcarteleraOfTicketListNewTicket = em.merge(oldIdcarteleraOfTicketListNewTicket);
                    }
                }
            }
            for (Sala salaListNewSala : salaListNew) {
                if (!salaListOld.contains(salaListNewSala)) {
                    Cartelera oldIdCarteleraOfSalaListNewSala = salaListNewSala.getIdCartelera();
                    salaListNewSala.setIdCartelera(cartelera);
                    salaListNewSala = em.merge(salaListNewSala);
                    if (oldIdCarteleraOfSalaListNewSala != null && !oldIdCarteleraOfSalaListNewSala.equals(cartelera)) {
                        oldIdCarteleraOfSalaListNewSala.getSalaList().remove(salaListNewSala);
                        oldIdCarteleraOfSalaListNewSala = em.merge(oldIdCarteleraOfSalaListNewSala);
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
            List<Pelicula> peliculaListOrphanCheck = cartelera.getPeliculaList();
            for (Pelicula peliculaListOrphanCheckPelicula : peliculaListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Cartelera (" + cartelera + ") cannot be destroyed since the Pelicula " + peliculaListOrphanCheckPelicula + " in its peliculaList field has a non-nullable idCartelera field.");
            }
            List<Ticket> ticketListOrphanCheck = cartelera.getTicketList();
            for (Ticket ticketListOrphanCheckTicket : ticketListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Cartelera (" + cartelera + ") cannot be destroyed since the Ticket " + ticketListOrphanCheckTicket + " in its ticketList field has a non-nullable idcartelera field.");
            }
            List<Sala> salaListOrphanCheck = cartelera.getSalaList();
            for (Sala salaListOrphanCheckSala : salaListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Cartelera (" + cartelera + ") cannot be destroyed since the Sala " + salaListOrphanCheckSala + " in its salaList field has a non-nullable idCartelera field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
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
