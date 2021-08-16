/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author timoa
 */
@Entity
@Table(name = "sala")
@NamedQueries({
    @NamedQuery(name = "Sala.findAll", query = "SELECT s FROM Sala s"),
    @NamedQuery(name = "Sala.findByIdSala", query = "SELECT s FROM Sala s WHERE s.idSala = :idSala"),
    @NamedQuery(name = "Sala.findByNroSala", query = "SELECT s FROM Sala s WHERE s.nroSala = :nroSala"),
    @NamedQuery(name = "Sala.findByNroFilas", query = "SELECT s FROM Sala s WHERE s.nroFilas = :nroFilas"),
    @NamedQuery(name = "Sala.findByNroColumnas", query = "SELECT s FROM Sala s WHERE s.nroColumnas = :nroColumnas"),
    @NamedQuery(name = "Sala.findByNroAsientos", query = "SELECT s FROM Sala s WHERE s.nroAsientos = :nroAsientos"),
    @NamedQuery(name = "Sala.findByEstado", query = "SELECT s FROM Sala s WHERE s.estado = :estado")})
public class Sala implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idSala", nullable = false)
    private Integer idSala;
    @Basic(optional = false)
    @Column(name = "nroSala", nullable = false)
    private int nroSala;
    @Basic(optional = false)
    @Column(name = "nroFilas", nullable = false)
    private int nroFilas;
    @Basic(optional = false)
    @Column(name = "nroColumnas", nullable = false)
    private int nroColumnas;
    @Basic(optional = false)
    @Column(name = "nroAsientos", nullable = false)
    private int nroAsientos;
    @Basic(optional = false)
    @Column(name = "estado", nullable = false, length = 45)
    private String estado;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idSala", fetch = FetchType.LAZY)
    private List<Cartelera> carteleraList;

    public Sala() {
    }

    public Sala(Integer idSala) {
        this.idSala = idSala;
    }

    public Sala(Integer idSala, int nroSala, int nroFilas, int nroColumnas, int nroAsientos, String estado) {
        this.idSala = idSala;
        this.nroSala = nroSala;
        this.nroFilas = nroFilas;
        this.nroColumnas = nroColumnas;
        this.nroAsientos = nroAsientos;
        this.estado = estado;
    }

    public Integer getIdSala() {
        return idSala;
    }

    public void setIdSala(Integer idSala) {
        this.idSala = idSala;
    }

    public int getNroSala() {
        return nroSala;
    }

    public void setNroSala(int nroSala) {
        this.nroSala = nroSala;
    }

    public int getNroFilas() {
        return nroFilas;
    }

    public void setNroFilas(int nroFilas) {
        this.nroFilas = nroFilas;
    }

    public int getNroColumnas() {
        return nroColumnas;
    }

    public void setNroColumnas(int nroColumnas) {
        this.nroColumnas = nroColumnas;
    }

    public int getNroAsientos() {
        return nroAsientos;
    }

    public void setNroAsientos(int nroAsientos) {
        this.nroAsientos = nroAsientos;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public List<Cartelera> getCarteleraList() {
        return carteleraList;
    }

    public void setCarteleraList(List<Cartelera> carteleraList) {
        this.carteleraList = carteleraList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idSala != null ? idSala.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Sala)) {
            return false;
        }
        Sala other = (Sala) object;
        if ((this.idSala == null && other.idSala != null) || (this.idSala != null && !this.idSala.equals(other.idSala))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Modelo.Sala[ idSala=" + idSala + " ]";
    }
    
}
