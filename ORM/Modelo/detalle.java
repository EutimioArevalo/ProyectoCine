/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author timoa
 */
@Entity
@Table(name = "detalle")
public class detalle implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id_detalle;
    private int cantTicket;
    private int cantSnack;
    private float precioTotal;
    private float precioUnitario;
    @OneToMany(mappedBy = "detalle", cascade = CascadeType.ALL)
    private List<ticket> listaTicket = new ArrayList<>();
    @OneToMany(mappedBy = "detalle", cascade = CascadeType.ALL)
    private List<snack> listaSnack = new ArrayList<>();
    @OneToMany(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
    private factura factura;
    
    public List<snack> getListaSnack() {
        return listaSnack;
    }

    public void setListaSnack(List<snack> listaSnack) {
        this.listaSnack = listaSnack;
    }
    public List<ticket> getListaTicket() {
        return listaTicket;
    }

    public void setListaTicket(List<ticket> listaTicket) {
        this.listaTicket = listaTicket;
    }
    
    
    public int getCantTicket() {
        return cantTicket;
    }

    public void setCantTicket(int cantTicket) {
        this.cantTicket = cantTicket;
    }

    public int getCantSnack() {
        return cantSnack;
    }

    public void setCantSnack(int cantSnack) {
        this.cantSnack = cantSnack;
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
    
    

    public Long getId_detalle() {
        return id_detalle;
    }

    public void setId_detalle(Long id_detalle) {
        this.id_detalle = id_detalle;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id_detalle != null ? id_detalle.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id_detalle fields are not set
        if (!(object instanceof detalle)) {
            return false;
        }
        detalle other = (detalle) object;
        if ((this.id_detalle == null && other.id_detalle != null) || (this.id_detalle != null && !this.id_detalle.equals(other.id_detalle))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Modelo.detalle[ id=" + id_detalle + " ]";
    }
    
}
