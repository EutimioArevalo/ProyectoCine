/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

/**
 *
 * @author timoa
 */
@Entity
public class factura implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id_factura;
    private int nroFactura;
    private float total;
    private float subtotal;
    @OneToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
    private persona persona;
    @OneToOne(mappedBy = "detalle", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private detalle detalle;

    public detalle getDetalle() {
        return detalle;
    }

    public void setDetalle(detalle detalle) {
        this.detalle = detalle;
    }

    public persona getPersona() {
        return persona;
    }

    public void setPersona(persona persona) {
        this.persona = persona;
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
    
    

    public Long getId_factura() {
        return id_factura;
    }

    public void setId_factura(Long id_factura) {
        this.id_factura = id_factura;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id_factura != null ? id_factura.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id_factura fields are not set
        if (!(object instanceof factura)) {
            return false;
        }
        factura other = (factura) object;
        if ((this.id_factura == null && other.id_factura != null) || (this.id_factura != null && !this.id_factura.equals(other.id_factura))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Modelo.factura[ id=" + id_factura + " ]";
    }
    
}
