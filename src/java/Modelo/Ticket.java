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
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author timoa
 */
@Entity
@Table(name = "ticket")
@NamedQueries({
    @NamedQuery(name = "Ticket.findAll", query = "SELECT t FROM Ticket t"),
    @NamedQuery(name = "Ticket.findByIdTicket", query = "SELECT t FROM Ticket t WHERE t.idTicket = :idTicket"),
    @NamedQuery(name = "Ticket.findByNroTicket", query = "SELECT t FROM Ticket t WHERE t.nroTicket = :nroTicket"),
    @NamedQuery(name = "Ticket.findByNombrePelicula", query = "SELECT t FROM Ticket t WHERE t.nombrePelicula = :nombrePelicula"),
    @NamedQuery(name = "Ticket.findByPrecio", query = "SELECT t FROM Ticket t WHERE t.precio = :precio"),
    @NamedQuery(name = "Ticket.findByAsientos", query = "SELECT t FROM Ticket t WHERE t.asientos = :asientos"),
    @NamedQuery(name = "Ticket.findByTipo", query = "SELECT t FROM Ticket t WHERE t.tipo = :tipo")})
public class Ticket implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idTicket", nullable = false)
    private Integer idTicket;
    @Basic(optional = false)
    @Column(name = "nroTicket", nullable = false)
    private int nroTicket;
    @Basic(optional = false)
    @Column(name = "nombrePelicula", nullable = false, length = 200)
    private String nombrePelicula;
    @Basic(optional = false)
    @Column(name = "precio", nullable = false)
    private float precio;
    @Basic(optional = false)
    @Column(name = "asientos", nullable = false, length = 100)
    private String asientos;
    @Basic(optional = false)
    @Column(name = "tipo", nullable = false, length = 45)
    private String tipo;
    @JoinColumn(name = "idCartelera", referencedColumnName = "idCartelera", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Cartelera idCartelera;
    @JoinColumn(name = "idDetalle", referencedColumnName = "idDetalle", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Detallefactura idDetalle;

    public Ticket() {
    }

    public Ticket(Integer idTicket) {
        this.idTicket = idTicket;
    }

    public Ticket(Integer idTicket, int nroTicket, String nombrePelicula, float precio, String asientos, String tipo) {
        this.idTicket = idTicket;
        this.nroTicket = nroTicket;
        this.nombrePelicula = nombrePelicula;
        this.precio = precio;
        this.asientos = asientos;
        this.tipo = tipo;
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

    public String getAsientos() {
        return asientos;
    }

    public void setAsientos(String asientos) {
        this.asientos = asientos;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Cartelera getIdCartelera() {
        return idCartelera;
    }

    public void setIdCartelera(Cartelera idCartelera) {
        this.idCartelera = idCartelera;
    }

    public Detallefactura getIdDetalle() {
        return idDetalle;
    }

    public void setIdDetalle(Detallefactura idDetalle) {
        this.idDetalle = idDetalle;
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
