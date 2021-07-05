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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author timoa
 */
@Entity
@Table(name = "ticket")
public class ticket implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id_ticket;
    private int  nroTicket;
    private String codigoQR;
    private String tipo;
    private String nombrePelicula;
    private float precio;
    private int nroAsientos;
    @OneToMany(mappedBy = "ticket",cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<cartelera> listaCartelera = new ArrayList<cartelera>();
    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
    @JoinColumn(name = "id_detalle", nullable = false, referencedColumnName = "id_detalle")
    private detalle detalle;

    public List<cartelera> getListaCartelera() {
        return listaCartelera;
    }

    public void setListaCartelera(List<cartelera> listaCartelera) {
        this.listaCartelera = listaCartelera;
    }

    public detalle getDetalle() {
        return detalle;
    }

    public void setDetalle(detalle detalle) {
        this.detalle = detalle;
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

    public int getNroAsientos() {
        return nroAsientos;
    }

    public void setNroAsientos(int nroAsientos) {
        this.nroAsientos = nroAsientos;
    }
    

    public Long getId_ticket() {
        return id_ticket;
    }

    public void setId_ticket(Long id_ticket) {
        this.id_ticket = id_ticket;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id_ticket != null ? id_ticket.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id_ticket fields are not set
        if (!(object instanceof ticket)) {
            return false;
        }
        ticket other = (ticket) object;
        if ((this.id_ticket == null && other.id_ticket != null) || (this.id_ticket != null && !this.id_ticket.equals(other.id_ticket))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Modelo.ticket[ id=" + id_ticket + " ]";
    }
    
}
