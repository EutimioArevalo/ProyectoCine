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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author timoa
 */
@Entity
@Table(name = "sala")
public class sala implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id_sala;
    private int nroAsientos;
    private int nroSala;
    private String estado = "activo";
    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
    @JoinColumn(name = "id_cartelera", nullable = false, referencedColumnName = "id_cartelera")
    private cartelera cartelera;

    public cartelera getCartelera() {
        return cartelera;
    }

    public void setCartelera(cartelera cartelera) {
        this.cartelera = cartelera;
    }
    
    
    public int getNroAsientos() {
        return nroAsientos;
    }

    public void setNroAsientos(int nroAsientos) {
        this.nroAsientos = nroAsientos;
    }

    public int getNroSala() {
        return nroSala;
    }

    public void setNroSala(int nroSala) {
        this.nroSala = nroSala;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
    
    

    public Long getId_sala() {
        return id_sala;
    }

    public void setId_sala(Long id_sala) {
        this.id_sala = id_sala;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id_sala != null ? id_sala.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id_sala fields are not set
        if (!(object instanceof sala)) {
            return false;
        }
        sala other = (sala) object;
        if ((this.id_sala == null && other.id_sala != null) || (this.id_sala != null && !this.id_sala.equals(other.id_sala))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Modelo.sala[ id=" + id_sala + " ]";
    }
    
}
