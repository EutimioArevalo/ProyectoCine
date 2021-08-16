/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author timoa
 */
@Entity
@Table(name = "carrito")
@NamedQueries({
    @NamedQuery(name = "Carrito.findAll", query = "SELECT c FROM Carrito c"),
    @NamedQuery(name = "Carrito.findByIdcarrito", query = "SELECT c FROM Carrito c WHERE c.idcarrito = :idcarrito"),
    @NamedQuery(name = "Carrito.findByNombreSnack", query = "SELECT c FROM Carrito c WHERE c.nombreSnack = :nombreSnack"),
    @NamedQuery(name = "Carrito.findByCantidad", query = "SELECT c FROM Carrito c WHERE c.cantidad = :cantidad"),
    @NamedQuery(name = "Carrito.findByPrecioU", query = "SELECT c FROM Carrito c WHERE c.precioU = :precioU"),
    @NamedQuery(name = "Carrito.findByPrecioT", query = "SELECT c FROM Carrito c WHERE c.precioT = :precioT")})
public class Carrito implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idcarrito", nullable = false)
    private Integer idcarrito;
    @Basic(optional = false)
    @Column(name = "nombreSnack", nullable = false, length = 45)
    private String nombreSnack;
    @Basic(optional = false)
    @Column(name = "cantidad", nullable = false)
    private int cantidad;
    @Basic(optional = false)
    @Column(name = "precioU", nullable = false)
    private float precioU;
    @Basic(optional = false)
    @Column(name = "precioT", nullable = false)
    private float precioT;
    @JoinColumn(name = "idDetalle", referencedColumnName = "idDetalle", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Detallefactura idDetalle;
    @JoinColumns({
        @JoinColumn(name = "idSnack", referencedColumnName = "idSnack", nullable = false),
        @JoinColumn(name = "idSnack", referencedColumnName = "idSnack", nullable = false)})
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Snack snack;

    public Carrito() {
    }

    public Carrito(Integer idcarrito) {
        this.idcarrito = idcarrito;
    }

    public Carrito(Integer idcarrito, String nombreSnack, int cantidad, float precioU, float precioT) {
        this.idcarrito = idcarrito;
        this.nombreSnack = nombreSnack;
        this.cantidad = cantidad;
        this.precioU = precioU;
        this.precioT = precioT;
    }

    public Integer getIdcarrito() {
        return idcarrito;
    }

    public void setIdcarrito(Integer idcarrito) {
        this.idcarrito = idcarrito;
    }

    public String getNombreSnack() {
        return nombreSnack;
    }

    public void setNombreSnack(String nombreSnack) {
        this.nombreSnack = nombreSnack;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public float getPrecioU() {
        return precioU;
    }

    public void setPrecioU(float precioU) {
        this.precioU = precioU;
    }

    public float getPrecioT() {
        return precioT;
    }

    public void setPrecioT(float precioT) {
        this.precioT = precioT;
    }

    public Detallefactura getIdDetalle() {
        return idDetalle;
    }

    public void setIdDetalle(Detallefactura idDetalle) {
        this.idDetalle = idDetalle;
    }

    public Snack getSnack() {
        return snack;
    }

    public void setSnack(Snack snack) {
        this.snack = snack;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idcarrito != null ? idcarrito.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Carrito)) {
            return false;
        }
        Carrito other = (Carrito) object;
        if ((this.idcarrito == null && other.idcarrito != null) || (this.idcarrito != null && !this.idcarrito.equals(other.idcarrito))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Modelo.Carrito[ idcarrito=" + idcarrito + " ]";
    }
    
}
