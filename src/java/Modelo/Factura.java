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
@Table(name = "factura")
@NamedQueries({
    @NamedQuery(name = "Factura.findAll", query = "SELECT f FROM Factura f"),
    @NamedQuery(name = "Factura.findByIdFactura", query = "SELECT f FROM Factura f WHERE f.idFactura = :idFactura"),
    @NamedQuery(name = "Factura.findByNroFactura", query = "SELECT f FROM Factura f WHERE f.nroFactura = :nroFactura"),
    @NamedQuery(name = "Factura.findByTotal", query = "SELECT f FROM Factura f WHERE f.total = :total"),
    @NamedQuery(name = "Factura.findByCliente", query = "SELECT f FROM Factura f WHERE f.cliente = :cliente")})
public class Factura implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idFactura", nullable = false)
    private Integer idFactura;
    @Basic(optional = false)
    @Column(name = "nroFactura", nullable = false, length = 50)
    private String nroFactura;
    @Basic(optional = false)
    @Column(name = "total", nullable = false)
    private float total;
    @Basic(optional = false)
    @Column(name = "cliente", nullable = false, length = 100)
    private String cliente;
    @JoinColumn(name = "idDetalle", referencedColumnName = "idDetalle", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Detallefactura idDetalle;
    @JoinColumns({
        @JoinColumn(name = "idPersona", referencedColumnName = "idPersona", nullable = false),
        @JoinColumn(name = "idPersona", referencedColumnName = "idPersona", nullable = false),
        @JoinColumn(name = "idPersona", referencedColumnName = "idPersona", nullable = false),
        @JoinColumn(name = "idPersona", referencedColumnName = "idPersona", nullable = false)})
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Persona persona;

    public Factura() {
    }

    public Factura(Integer idFactura) {
        this.idFactura = idFactura;
    }

    public Factura(Integer idFactura, String nroFactura, float total, String cliente) {
        this.idFactura = idFactura;
        this.nroFactura = nroFactura;
        this.total = total;
        this.cliente = cliente;
    }

    public Integer getIdFactura() {
        return idFactura;
    }

    public void setIdFactura(Integer idFactura) {
        this.idFactura = idFactura;
    }

    public String getNroFactura() {
        return nroFactura;
    }

    public void setNroFactura(String nroFactura) {
        this.nroFactura = nroFactura;
    }

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public Detallefactura getIdDetalle() {
        return idDetalle;
    }

    public void setIdDetalle(Detallefactura idDetalle) {
        this.idDetalle = idDetalle;
    }

    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idFactura != null ? idFactura.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Factura)) {
            return false;
        }
        Factura other = (Factura) object;
        if ((this.idFactura == null && other.idFactura != null) || (this.idFactura != null && !this.idFactura.equals(other.idFactura))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Modelo.Factura[ idFactura=" + idFactura + " ]";
    }
    
}
