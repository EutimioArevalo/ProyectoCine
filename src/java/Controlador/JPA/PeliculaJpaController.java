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
import Modelo.Pelicula;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author timoa
 */
public class PeliculaJpaController implements Serializable {

    public PeliculaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }

    public PeliculaJpaController() {
    }
    
    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("ProyectoCineWEBPU");
    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Pelicula pelicula) {
        if (pelicula.getCarteleraList() == null) {
            pelicula.setCarteleraList(new ArrayList<Cartelera>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Cartelera> attachedCarteleraList = new ArrayList<Cartelera>();
            for (Cartelera carteleraListCarteleraToAttach : pelicula.getCarteleraList()) {
                carteleraListCarteleraToAttach = em.getReference(carteleraListCarteleraToAttach.getClass(), carteleraListCarteleraToAttach.getIdCartelera());
                attachedCarteleraList.add(carteleraListCarteleraToAttach);
            }
            pelicula.setCarteleraList(attachedCarteleraList);
            em.persist(pelicula);
            for (Cartelera carteleraListCartelera : pelicula.getCarteleraList()) {
                Pelicula oldIdPeliculaOfCarteleraListCartelera = carteleraListCartelera.getIdPelicula();
                carteleraListCartelera.setIdPelicula(pelicula);
                carteleraListCartelera = em.merge(carteleraListCartelera);
                if (oldIdPeliculaOfCarteleraListCartelera != null) {
                    oldIdPeliculaOfCarteleraListCartelera.getCarteleraList().remove(carteleraListCartelera);
                    oldIdPeliculaOfCarteleraListCartelera = em.merge(oldIdPeliculaOfCarteleraListCartelera);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Pelicula pelicula) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Pelicula persistentPelicula = em.find(Pelicula.class, pelicula.getIdPelicula());
            List<Cartelera> carteleraListOld = persistentPelicula.getCarteleraList();
            List<Cartelera> carteleraListNew = pelicula.getCarteleraList();
            List<String> illegalOrphanMessages = null;
            for (Cartelera carteleraListOldCartelera : carteleraListOld) {
                if (!carteleraListNew.contains(carteleraListOldCartelera)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Cartelera " + carteleraListOldCartelera + " since its idPelicula field is not nullable.");
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
            pelicula.setCarteleraList(carteleraListNew);
            pelicula = em.merge(pelicula);
            for (Cartelera carteleraListNewCartelera : carteleraListNew) {
                if (!carteleraListOld.contains(carteleraListNewCartelera)) {
                    Pelicula oldIdPeliculaOfCarteleraListNewCartelera = carteleraListNewCartelera.getIdPelicula();
                    carteleraListNewCartelera.setIdPelicula(pelicula);
                    carteleraListNewCartelera = em.merge(carteleraListNewCartelera);
                    if (oldIdPeliculaOfCarteleraListNewCartelera != null && !oldIdPeliculaOfCarteleraListNewCartelera.equals(pelicula)) {
                        oldIdPeliculaOfCarteleraListNewCartelera.getCarteleraList().remove(carteleraListNewCartelera);
                        oldIdPeliculaOfCarteleraListNewCartelera = em.merge(oldIdPeliculaOfCarteleraListNewCartelera);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = pelicula.getIdPelicula();
                if (findPelicula(id) == null) {
                    throw new NonexistentEntityException("The pelicula with id " + id + " no longer exists.");
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
            Pelicula pelicula;
            try {
                pelicula = em.getReference(Pelicula.class, id);
                pelicula.getIdPelicula();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The pelicula with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Cartelera> carteleraListOrphanCheck = pelicula.getCarteleraList();
            for (Cartelera carteleraListOrphanCheckCartelera : carteleraListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Pelicula (" + pelicula + ") cannot be destroyed since the Cartelera " + carteleraListOrphanCheckCartelera + " in its carteleraList field has a non-nullable idPelicula field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(pelicula);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Pelicula> findPeliculaEntities() {
        return findPeliculaEntities(true, -1, -1);
    }

    public List<Pelicula> findPeliculaEntities(int maxResults, int firstResult) {
        return findPeliculaEntities(false, maxResults, firstResult);
    }

    private List<Pelicula> findPeliculaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Pelicula.class));
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

    public Pelicula findPelicula(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Pelicula.class, id);
        } finally {
            em.close();
        }
    }

    public int getPeliculaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Pelicula> rt = cq.from(Pelicula.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
