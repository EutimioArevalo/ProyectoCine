/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador.JPA;

import Controlador.JPA.exceptions.NonexistentEntityException;
import Controlador.JPA.exceptions.PreexistingEntityException;
import Modelo.Carrito;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import Modelo.Detallefactura;
import Modelo.Snack;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author timoa
 */
public class CarritoJpaController implements Serializable {

    public CarritoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }

    public CarritoJpaController() {
    }
    
    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("ProyectoCineWEBPU");

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Carrito carrito) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Detallefactura idDetalle = carrito.getIdDetalle();
            if (idDetalle != null) {
                idDetalle = em.getReference(idDetalle.getClass(), idDetalle.getIdDetalle());
                carrito.setIdDetalle(idDetalle);
            }
            Snack snack = carrito.getSnack();
            if (snack != null) {
                snack = em.getReference(snack.getClass(), snack.getIdSnack());
                carrito.setSnack(snack);
            }
            em.persist(carrito);
            if (idDetalle != null) {
                idDetalle.getCarritoList().add(carrito);
                idDetalle = em.merge(idDetalle);
            }
            if (snack != null) {
                snack.getCarritoList().add(carrito);
                snack = em.merge(snack);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findCarrito(carrito.getIdcarrito()) != null) {
                throw new PreexistingEntityException("Carrito " + carrito + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Carrito carrito) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Carrito persistentCarrito = em.find(Carrito.class, carrito.getIdcarrito());
            Detallefactura idDetalleOld = persistentCarrito.getIdDetalle();
            Detallefactura idDetalleNew = carrito.getIdDetalle();
            Snack snackOld = persistentCarrito.getSnack();
            Snack snackNew = carrito.getSnack();
            if (idDetalleNew != null) {
                idDetalleNew = em.getReference(idDetalleNew.getClass(), idDetalleNew.getIdDetalle());
                carrito.setIdDetalle(idDetalleNew);
            }
            if (snackNew != null) {
                snackNew = em.getReference(snackNew.getClass(), snackNew.getIdSnack());
                carrito.setSnack(snackNew);
            }
            carrito = em.merge(carrito);
            if (idDetalleOld != null && !idDetalleOld.equals(idDetalleNew)) {
                idDetalleOld.getCarritoList().remove(carrito);
                idDetalleOld = em.merge(idDetalleOld);
            }
            if (idDetalleNew != null && !idDetalleNew.equals(idDetalleOld)) {
                idDetalleNew.getCarritoList().add(carrito);
                idDetalleNew = em.merge(idDetalleNew);
            }
            if (snackOld != null && !snackOld.equals(snackNew)) {
                snackOld.getCarritoList().remove(carrito);
                snackOld = em.merge(snackOld);
            }
            if (snackNew != null && !snackNew.equals(snackOld)) {
                snackNew.getCarritoList().add(carrito);
                snackNew = em.merge(snackNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = carrito.getIdcarrito();
                if (findCarrito(id) == null) {
                    throw new NonexistentEntityException("The carrito with id " + id + " no longer exists.");
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
            Carrito carrito;
            try {
                carrito = em.getReference(Carrito.class, id);
                carrito.getIdcarrito();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The carrito with id " + id + " no longer exists.", enfe);
            }
            Detallefactura idDetalle = carrito.getIdDetalle();
            if (idDetalle != null) {
                idDetalle.getCarritoList().remove(carrito);
                idDetalle = em.merge(idDetalle);
            }
            Snack snack = carrito.getSnack();
            if (snack != null) {
                snack.getCarritoList().remove(carrito);
                snack = em.merge(snack);
            }
            em.remove(carrito);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Carrito> findCarritoEntities() {
        return findCarritoEntities(true, -1, -1);
    }

    public List<Carrito> findCarritoEntities(int maxResults, int firstResult) {
        return findCarritoEntities(false, maxResults, firstResult);
    }

    private List<Carrito> findCarritoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Carrito.class));
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

    public Carrito findCarrito(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Carrito.class, id);
        } finally {
            em.close();
        }
    }

    public int getCarritoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Carrito> rt = cq.from(Carrito.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
