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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author timoa
 */
@Entity
@Table(name = "ticket")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Ticket.findAll", query = "SELECT t FROM Ticket t"),
    @NamedQuery(name = "Ticket.findByIdTicket", query = "SELECT t FROM Ticket t WHERE t.idTicket = :idTicket"),
    @NamedQuery(name = "Ticket.findByNroTicket", query = "SELECT t FROM Ticket t WHERE t.nroTicket = :nroTicket"),
    @NamedQuery(name = "Ticket.findByCodigoQR", query = "SELECT t FROM Ticket t WHERE t.codigoQR = :codigoQR"),
    @NamedQuery(name = "Ticket.findByTipo", query = "SELECT t FROM Ticket t WHERE t.tipo = :tipo"),
    @NamedQuery(name = "Ticket.findByNombrePelicula", query = "SELECT t FROM Ticket t WHERE t.nombrePelicula = :nombrePelicula"),
    @NamedQuery(name = "Ticket.findByPrecio", query = "SELECT t FROM Ticket t WHERE t.precio = :precio"),
    @NamedQuery(name = "Ticket.findByAsientos", query = "SELECT t FROM Ticket t WHERE t.asientos = :asientos")})
public class Ticket implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_ticket")
    private Integer idTicket;
    @Basic(optional = false)
    @Column(name = "nroTicket")
    private int nroTicket;
    @Basic(optional = false)
    @Column(name = "codigoQR")
    private String codigoQR;
    @Basic(optional = false)
    @Column(name = "tipo")
    private String tipo;
    @Basic(optional = false)
    @Column(name = "nombrePelicula")
    private String nombrePelicula;
    @Basic(optional = false)
    @Column(name = "precio")
    private float precio;
    @Basic(optional = false)
    @Column(name = "asientos")
    private int asientos;
    @JoinColumn(name = "Id_cartelera", referencedColumnName = "id_cartelera")
    @ManyToOne(optional = false)
    private Cartelera idcartelera;
    @JoinColumn(name = "id_DetalleFactura", referencedColumnName = "id_DetalleFactura")
    @ManyToOne(optional = false)
    private Detallefactura idDetalleFactura;

    public Ticket() {
    }

    public Ticket(Integer idTicket) {
        this.idTicket = idTicket;
    }

    public Ticket(Integer idTicket, int nroTicket, String codigoQR, String tipo, String nombrePelicula, float precio, int asientos) {
        this.idTicket = idTicket;
        this.nroTicket = nroTicket;
        this.codigoQR = codigoQR;
        this.tipo = tipo;
        this.nombrePelicula = nombrePelicula;
        this.precio = precio;
        this.asientos = asientos;
    }

    public Integer getIdTicket() {
        return idTicket;
    }

    public void setIdTicket(Integer idTicket) {
        this.idTicket = idTicket;
    }

    public int getNroTicket() {
        return nroTicket;
    }

    public void setNroTicket(int nroTicket) {
        this.nroTicket = nroTicket;
    }

    public String getCodigoQR() {
        return codigoQR;
    }

    public void setCodigoQR(String codigoQR) {
        this.codigoQR = codigoQR;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getNombrePelicula() {
        return nombrePelicula;
    }

    public void setNombrePelicula(String nombrePelicula) {
        this.nombrePelicula = nombrePelicula;
    }

    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }

    public int getAsientos() {
        return asientos;
    }

    public void setAsientos(int asientos) {
        this.asientos = asientos;
    }

    public Cartelera getIdcartelera() {
        return idcartelera;
    }

    public void setIdcartelera(Cartelera idcartelera) {
        this.idcartelera = idcartelera;
    }

    public Detallefactura getIdDetalleFactura() {
        return idDetalleFactura;
    }

    public void setIdDetalleFactura(Detallefactura idDetalleFactura) {
        this.idDetalleFactura = idDetalleFactura;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idTicket != null ? idTicket.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Ticket)) {
            return false;
        }
        Ticket other = (Ticket) object;
        if ((this.idTicket == null && other.idTicket != null) || (this.idTicket != null && !this.idTicket.equals(other.idTicket))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Modelo.Ticket[ idTicket=" + idTicket + " ]";
    }
    
}
