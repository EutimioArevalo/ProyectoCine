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
import Modelo.Cartelera;
import Modelo.Sala;
import java.util.ArrayList;
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
        if (sala.getCarteleraList() == null) {
            sala.setCarteleraList(new ArrayList<Cartelera>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Cartelera> attachedCarteleraList = new ArrayList<Cartelera>();
            for (Cartelera carteleraListCarteleraToAttach : sala.getCarteleraList()) {
                carteleraListCarteleraToAttach = em.getReference(carteleraListCarteleraToAttach.getClass(), carteleraListCarteleraToAttach.getIdCartelera());
                attachedCarteleraList.add(carteleraListCarteleraToAttach);
            }
            sala.setCarteleraList(attachedCarteleraList);
            em.persist(sala);
            for (Cartelera carteleraListCartelera : sala.getCarteleraList()) {
                Sala oldIdSalaOfCarteleraListCartelera = carteleraListCartelera.getIdSala();
                carteleraListCartelera.setIdSala(sala);
                carteleraListCartelera = em.merge(carteleraListCartelera);
                if (oldIdSalaOfCarteleraListCartelera != null) {
                    oldIdSalaOfCarteleraListCartelera.getCarteleraList().remove(carteleraListCartelera);
                    oldIdSalaOfCarteleraListCartelera = em.merge(oldIdSalaOfCarteleraListCartelera);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Sala sala) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Sala persistentSala = em.find(Sala.class, sala.getIdSala());
            List<Cartelera> carteleraListOld = persistentSala.getCarteleraList();
            List<Cartelera> carteleraListNew = sala.getCarteleraList();
            List<String> illegalOrphanMessages = null;
            for (Cartelera carteleraListOldCartelera : carteleraListOld) {
                if (!carteleraListNew.contains(carteleraListOldCartelera)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Cartelera " + carteleraListOldCartelera + " since its idSala field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            List<Cartelera> attachedCarteleraListNew = new ArrayList<Cartelera>();
            for (Cartelera carteleraListNewCarteleraToAttach : carteleraListNew) {
                carteleraListNewCarteleraToAttach = em.getReference(carteleraListNewCarteleraToAttach.getClass(), carteleraListNewCarteleraToAttach.getIdCartelera());
                attachedCarteleraListNew.add(carteleraListNewCarteleraToAttach);
            }
            carteleraListNew = attachedCarteleraListNew;
            sala.setCarteleraList(carteleraListNew);
            sala = em.merge(sala);
            for (Cartelera carteleraListNewCartelera : carteleraListNew) {
                if (!carteleraListOld.contains(carteleraListNewCartelera)) {
                    Sala oldIdSalaOfCarteleraListNewCartelera = carteleraListNewCartelera.getIdSala();
                    carteleraListNewCartelera.setIdSala(sala);
                    carteleraListNewCartelera = em.merge(carteleraListNewCartelera);
                    if (oldIdSalaOfCarteleraListNewCartelera != null && !oldIdSalaOfCarteleraListNewCartelera.equals(sala)) {
                        oldIdSalaOfCarteleraListNewCartelera.getCarteleraList().remove(carteleraListNewCartelera);
                        oldIdSalaOfCarteleraListNewCartelera = em.merge(oldIdSalaOfCarteleraListNewCartelera);
                    }
                }
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

    public void destroy(Integer id) throws IllegalOrphanException, NonexistentEntityException {
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
            List<String> illegalOrphanMessages = null;
            List<Cartelera> carteleraListOrphanCheck = sala.getCarteleraList();
            for (Cartelera carteleraListOrphanCheckCartelera : carteleraListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Sala (" + sala + ") cannot be destroyed since the Cartelera " + carteleraListOrphanCheckCartelera + " in its carteleraList field has a non-nullable idSala field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
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
