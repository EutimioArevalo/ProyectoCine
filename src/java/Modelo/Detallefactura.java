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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author timoa
 */
@Entity
@Table(name = "detallefactura")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Detallefactura.findAll", query = "SELECT d FROM Detallefactura d"),
    @NamedQuery(name = "Detallefactura.findByIdDetalleFactura", query = "SELECT d FROM Detallefactura d WHERE d.idDetalleFactura = :idDetalleFactura"),
    @NamedQuery(name = "Detallefactura.findByCantidadTicket", query = "SELECT d FROM Detallefactura d WHERE d.cantidadTicket = :cantidadTicket"),
    @NamedQuery(name = "Detallefactura.findByCantidadSnack", query = "SELECT d FROM Detallefactura d WHERE d.cantidadSnack = :cantidadSnack"),
    @NamedQuery(name = "Detallefactura.findByPrecioTotal", query = "SELECT d FROM Detallefactura d WHERE d.precioTotal = :precioTotal"),
    @NamedQuery(name = "Detallefactura.findByPrecioUnitario", query = "SELECT d FROM Detallefactura d WHERE d.precioUnitario = :precioUnitario")})
public class Detallefactura implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_DetalleFactura")
    private Integer idDetalleFactura;
    @Basic(optional = false)
    @Column(name = "cantidadTicket")
    private int cantidadTicket;
    @Basic(optional = false)
    @Column(name = "cantidadSnack")
    private int cantidadSnack;
    @Basic(optional = false)
    @Column(name = "precioTotal")
    private float precioTotal;
    @Basic(optional = false)
    @Column(name = "precioUnitario")
    private float precioUnitario;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idDetalleFactura")
    private List<Factura> facturaList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idDetalleFactura")
    private List<Ticket> ticketList;
    @OneToMany(mappedBy = "idDetalleFactura")
    private List<Snack> snackList;

    public Detallefactura() {
    }

    public Detallefactura(Integer idDetalleFactura) {
        this.idDetalleFactura = idDetalleFactura;
    }

    public Detallefactura(Integer idDetalleFactura, int cantidadTicket, int cantidadSnack, float precioTotal, float precioUnitario) {
        this.idDetalleFactura = idDetalleFactura;
        this.cantidadTicket = cantidadTicket;
        this.cantidadSnack = cantidadSnack;
        this.precioTotal = precioTotal;
        this.precioUnitario = precioUnitario;
    }

    public Integer getIdDetalleFactura() {
        return idDetalleFactura;
    }

    public void setIdDetalleFactura(Integer idDetalleFactura) {
        this.idDetalleFactura = idDetalleFactura;
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

    public float getPrecioUnitario() {
        return precioUnitario;
    }

    public void setPrecioUnitario(float precioUnitario) {
        this.precioUnitario = precioUnitario;
    }

    @XmlTransient
    public List<Factura> getFacturaList() {
        return facturaList;
    }

    public void setFacturaList(List<Factura> facturaList) {
        this.facturaList = facturaList;
    }

    @XmlTransient
    public List<Ticket> getTicketList() {
        return ticketList;
    }

    public void setTicketList(List<Ticket> ticketList) {
        this.ticketList = ticketList;
    }

    @XmlTransient
    public List<Snack> getSnackList() {
        return snackList;
    }

    public void setSnackList(List<Snack> snackList) {
        this.snackList = snackList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idDetalleFactura != null ? idDetalleFactura.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Detallefactura)) {
            return false;
        }
        Detallefactura other = (Detallefactura) object;
        if ((this.idDetalleFactura == null && other.idDetalleFactura != null) || (this.idDetalleFactura != null && !this.idDetalleFactura.equals(other.idDetalleFactura))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Modelo.Detallefactura[ idDetalleFactura=" + idDetalleFactura + " ]";
    }
    
}
