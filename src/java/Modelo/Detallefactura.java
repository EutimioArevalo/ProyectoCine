/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author timoa
 */
@Entity
@Table(name = "detallefactura")
@NamedQueries({
    @NamedQuery(name = "Detallefactura.findAll", query = "SELECT d FROM Detallefactura d"),
    @NamedQuery(name = "Detallefactura.findByIdDetalle", query = "SELECT d FROM Detallefactura d WHERE d.idDetalle = :idDetalle"),
    @NamedQuery(name = "Detallefactura.findByCantidadTicket", query = "SELECT d FROM Detallefactura d WHERE d.cantidadTicket = :cantidadTicket"),
    @NamedQuery(name = "Detallefactura.findByCantidadSnack", query = "SELECT d FROM Detallefactura d WHERE d.cantidadSnack = :cantidadSnack"),
    @NamedQuery(name = "Detallefactura.findByPrecioTotal", query = "SELECT d FROM Detallefactura d WHERE d.precioTotal = :precioTotal")})
public class Detallefactura implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idDetalle", nullable = false)
    private Integer idDetalle;
    @Basic(optional = false)
    @Column(name = "cantidadTicket", nullable = false)
    private int cantidadTicket;
    @Basic(optional = false)
    @Column(name = "cantidadSnack", nullable = false)
    private int cantidadSnack;
    @Basic(optional = false)
    @Column(name = "precioTotal", nullable = false)
    private float precioTotal;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idDetalle", fetch = FetchType.LAZY)
    private List<Factura> facturaList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idDetalle", fetch = FetchType.LAZY)
    private List<Ticket> ticketList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idDetalle", fetch = FetchType.LAZY)
    private List<Carrito> carritoList;

    public Detallefactura() {
    }

    public Detallefactura(Integer idDetalle) {
        this.idDetalle = idDetalle;
    }

    public Detallefactura(Integer idDetalle, int cantidadTicket, int cantidadSnack, float precioTotal) {
        this.idDetalle = idDetalle;
        this.cantidadTicket = cantidadTicket;
        this.cantidadSnack = cantidadSnack;
        this.precioTotal = precioTotal;
    }

    public Integer getIdDetalle() {
        return idDetalle;
    }

    public void setIdDetalle(Integer idDetalle) {
        this.idDetalle = idDetalle;
    }

    public int getCantidadTicket() {
        return cantidadTicket;
    }

    public void setCantidadTicket(int cantidadTicket) {
        this.cantidadTicket = cantidadTicket;
    }

    public int getCantidadSnack() {
        return cantidadSnack;
    }

    public void setCantidadSnack(int cantidadSnack) {
        this.cantidadSnack = cantidadSnack;
    }

    public float getPrecioTotal() {
        return precioTotal;
    }

    public void setPrecioTotal(float precioTotal) {
        this.precioTotal = precioTotal;
    }

    public List<Factura> getFacturaList() {
        return facturaList;
    }

    public void setFacturaList(List<Factura> facturaList) {
        this.facturaList = facturaList;
    }

    public List<Ticket> getTicketList() {
        return ticketList;
    }

    public void setTicketList(List<Ticket> ticketList) {
        this.ticketList = ticketList;
    }

    public List<Carrito> getCarritoList() {
        return carritoList;
    }

    public void setCarritoList(List<Carrito> carritoList) {
        this.carritoList = carritoList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idDetalle != null ? idDetalle.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Detallefactura)) {
            return false;
        }
        Detallefactura other = (Detallefactura) object;
        if ((this.idDetalle == null && other.idDetalle != null) || (this.idDetalle != null && !this.idDetalle.equals(other.idDetalle))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Modelo.Detallefactura[ idDetalle=" + idDetalle + " ]";
    }
    
}
