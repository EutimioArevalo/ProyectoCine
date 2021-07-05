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
@Table(name = "snack")
public class snack implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id_snack;
    private String nombreCombo;
    private String descripcion;
    private double precio;
    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
    @JoinColumn(name = "id_detalle", nullable = false, referencedColumnName = "id_detalle")
    private detalle detalle;

    public String getNombreCombo() {
        return nombreCombo;
    }

    public detalle getDetalle() {
        return detalle;
    }

    public void setDetalle(detalle detalle) {
        this.detalle = detalle;
    }

    public void setNombreCombo(String nombreCombo) {
        this.nombreCombo = nombreCombo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public Long getId_snack() {
        return id_snack;
    }

    public void setId_snack(Long id_snack) {
        this.id_snack = id_snack;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id_snack != null ? id_snack.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id_snack fields are not set
        if (!(object instanceof snack)) {
            return false;
        }
        snack other = (snack) object;
        if ((this.id_snack == null && other.id_snack != null) || (this.id_snack != null && !this.id_snack.equals(other.id_snack))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Modelo.snack[ id=" + id_snack + " ]";
    }

}
