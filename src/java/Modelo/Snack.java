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
@Table(name = "snack")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Snack.findAll", query = "SELECT s FROM Snack s"),
    @NamedQuery(name = "Snack.findByIdSnack", query = "SELECT s FROM Snack s WHERE s.idSnack = :idSnack"),
    @NamedQuery(name = "Snack.findByNombre", query = "SELECT s FROM Snack s WHERE s.nombre = :nombre"),
    @NamedQuery(name = "Snack.findByDescripcion", query = "SELECT s FROM Snack s WHERE s.descripcion = :descripcion"),
    @NamedQuery(name = "Snack.findByPrecio", query = "SELECT s FROM Snack s WHERE s.precio = :precio"),
    @NamedQuery(name = "Snack.findByImg", query = "SELECT s FROM Snack s WHERE s.img = :img")})
public class Snack implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_Snack")
    private Integer idSnack;
    @Basic(optional = false)
    @Column(name = "nombre")
    private String nombre;
    @Basic(optional = false)
    @Column(name = "descripcion")
    private String descripcion;
    @Basic(optional = false)
    @Column(name = "precio")
    private float precio;
    @Basic(optional = false)
    @Column(name = "img")
    private String img;
    @JoinColumn(name = "id_DetalleFactura", referencedColumnName = "id_DetalleFactura")
    @ManyToOne
    private Detallefactura idDetalleFactura;

    public Snack() {
    }

    public Snack(Integer idSnack) {
        this.idSnack = idSnack;
    }

    public Snack(Integer idSnack, String nombre, String descripcion, float precio, String img) {
        this.idSnack = idSnack;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
        this.img = img;
    }

    public Integer getIdSnack() {
        return idSnack;
    }

    public void setIdSnack(Integer idSnack) {
        this.idSnack = idSnack;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
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
        hash += (idSnack != null ? idSnack.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Snack)) {
            return false;
        }
        Snack other = (Snack) object;
        if ((this.idSnack == null && other.idSnack != null) || (this.idSnack != null && !this.idSnack.equals(other.idSnack))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Modelo.Snack[ idSnack=" + idSnack + " ]";
    }
    
}
