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
import javax.persistence.JoinColumns;
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
@Table(name = "factura")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Factura.findAll", query = "SELECT f FROM Factura f"),
    @NamedQuery(name = "Factura.findByIdFactura", query = "SELECT f FROM Factura f WHERE f.idFactura = :idFactura"),
    @NamedQuery(name = "Factura.findByNroFactura", query = "SELECT f FROM Factura f WHERE f.nroFactura = :nroFactura"),
    @NamedQuery(name = "Factura.findByTotal", query = "SELECT f FROM Factura f WHERE f.total = :total"),
    @NamedQuery(name = "Factura.findBySubtotal", query = "SELECT f FROM Factura f WHERE f.subtotal = :subtotal"),
    @NamedQuery(name = "Factura.findByPersonaRolidRol", query = "SELECT f FROM Factura f WHERE f.personaRolidRol = :personaRolidRol")})
public class Factura implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_factura")
    private Integer idFactura;
    @Basic(optional = false)
    @Column(name = "nroFactura")
    private int nroFactura;
    @Basic(optional = false)
    @Column(name = "total")
    private float total;
    @Basic(optional = false)
    @Column(name = "subtotal")
    private float subtotal;
    @Basic(optional = false)
    @Column(name = "persona_Rol_id_Rol")
    private int personaRolidRol;
    @JoinColumn(name = "id_DetalleFactura", referencedColumnName = "id_DetalleFactura")
    @ManyToOne(optional = false)
    private Detallefactura idDetalleFactura;
    @JoinColumns({
        @JoinColumn(name = "id_Persona", referencedColumnName = "id_Persona"),
        @JoinColumn(name = "id_Persona", referencedColumnName = "id_Persona"),
        @JoinColumn(name = "id_Persona", referencedColumnName = "id_Persona"),
        @JoinColumn(name = "id_Persona", referencedColumnName = "id_Persona")})
    @ManyToOne(optional = false)
    private Persona persona;

    public Factura() {
    }

    public Factura(Integer idFactura) {
        this.idFactura = idFactura;
    }

    public Factura(Integer idFactura, int nroFactura, float total, float subtotal, int personaRolidRol) {
        this.idFactura = idFactura;
        this.nroFactura = nroFactura;
        this.total = total;
        this.subtotal = subtotal;
        this.personaRolidRol = personaRolidRol;
    }

    public Integer getIdFactura() {
        return idFactura;
    }

    public void setIdFactura(Integer idFactura) {
        this.idFactura = idFactura;
    }

    public int getNroFactura() {
        return nroFactura;
    }

    public void setNroFactura(int nroFactura) {
        this.nroFactura = nroFactura;
    }

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }

    public float getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(float subtotal) {
        this.subtotal = subtotal;
    }

    public int getPersonaRolidRol() {
        return personaRolidRol;
    }

    public void setPersonaRolidRol(int personaRolidRol) {
        this.personaRolidRol = personaRolidRol;
    }

    public Detallefactura getIdDetalleFactura() {
        return idDetalleFactura;
    }

    public void setIdDetalleFactura(Detallefactura idDetalleFactura) {
        this.idDetalleFactura = idDetalleFactura;
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
