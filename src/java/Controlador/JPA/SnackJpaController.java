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
import Modelo.Carrito;
import Modelo.Snack;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author timoa
 */
public class SnackJpaController implements Serializable {

    public SnackJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }

    public SnackJpaController() {
    }
    
    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("ProyectoCineWEBPU");

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Snack snack) {
        if (snack.getCarritoList() == null) {
            snack.setCarritoList(new ArrayList<Carrito>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Carrito> attachedCarritoList = new ArrayList<Carrito>();
            for (Carrito carritoListCarritoToAttach : snack.getCarritoList()) {
                carritoListCarritoToAttach = em.getReference(carritoListCarritoToAttach.getClass(), carritoListCarritoToAttach.getIdcarrito());
                attachedCarritoList.add(carritoListCarritoToAttach);
            }
            snack.setCarritoList(attachedCarritoList);
            em.persist(snack);
            for (Carrito carritoListCarrito : snack.getCarritoList()) {
                Snack oldSnackOfCarritoListCarrito = carritoListCarrito.getSnack();
                carritoListCarrito.setSnack(snack);
                carritoListCarrito = em.merge(carritoListCarrito);
                if (oldSnackOfCarritoListCarrito != null) {
                    oldSnackOfCarritoListCarrito.getCarritoList().remove(carritoListCarrito);
                    oldSnackOfCarritoListCarrito = em.merge(oldSnackOfCarritoListCarrito);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Snack snack) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Snack persistentSnack = em.find(Snack.class, snack.getIdSnack());
            List<Carrito> carritoListOld = persistentSnack.getCarritoList();
            List<Carrito> carritoListNew = snack.getCarritoList();
            List<String> illegalOrphanMessages = null;
            for (Carrito carritoListOldCarrito : carritoListOld) {
                if (!carritoListNew.contains(carritoListOldCarrito)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Carrito " + carritoListOldCarrito + " since its snack field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            List<Carrito> attachedCarritoListNew = new ArrayList<Carrito>();
            for (Carrito carritoListNewCarritoToAttach : carritoListNew) {
                carritoListNewCarritoToAttach = em.getReference(carritoListNewCarritoToAttach.getClass(), carritoListNewCarritoToAttach.getIdcarrito());
                attachedCarritoListNew.add(carritoListNewCarritoToAttach);
            }
            carritoListNew = attachedCarritoListNew;
            snack.setCarritoList(carritoListNew);
            snack = em.merge(snack);
            for (Carrito carritoListNewCarrito : carritoListNew) {
                if (!carritoListOld.contains(carritoListNewCarrito)) {
                    Snack oldSnackOfCarritoListNewCarrito = carritoListNewCarrito.getSnack();
                    carritoListNewCarrito.setSnack(snack);
                    carritoListNewCarrito = em.merge(carritoListNewCarrito);
                    if (oldSnackOfCarritoListNewCarrito != null && !oldSnackOfCarritoListNewCarrito.equals(snack)) {
                        oldSnackOfCarritoListNewCarrito.getCarritoList().remove(carritoListNewCarrito);
                        oldSnackOfCarritoListNewCarrito = em.merge(oldSnackOfCarritoListNewCarrito);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = snack.getIdSnack();
                if (findSnack(id) == null) {
                    throw new NonexistentEntityException("The snack with id " + id + " no longer exists.");
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
            Snack snack;
            try {
                snack = em.getReference(Snack.class, id);
                snack.getIdSnack();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The snack with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Carrito> carritoListOrphanCheck = snack.getCarritoList();
            for (Carrito carritoListOrphanCheckCarrito : carritoListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Snack (" + snack + ") cannot be destroyed since the Carrito " + carritoListOrphanCheckCarrito + " in its carritoList field has a non-nullable snack field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(snack);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Snack> findSnackEntities() {
        return findSnackEntities(true, -1, -1);
    }

    public List<Snack> findSnackEntities(int maxResults, int firstResult) {
        return findSnackEntities(false, maxResults, firstResult);
    }

    private List<Snack> findSnackEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Snack.class));
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

    public Snack findSnack(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Snack.class, id);
        } finally {
            em.close();
        }
    }

    public int getSnackCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Snack> rt = cq.from(Snack.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
