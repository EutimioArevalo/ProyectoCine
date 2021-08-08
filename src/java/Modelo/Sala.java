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
@Table(name = "sala")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Sala.findAll", query = "SELECT s FROM Sala s"),
    @NamedQuery(name = "Sala.findByIdSala", query = "SELECT s FROM Sala s WHERE s.idSala = :idSala"),
    @NamedQuery(name = "Sala.findByNroAsientos", query = "SELECT s FROM Sala s WHERE s.nroAsientos = :nroAsientos"),
    @NamedQuery(name = "Sala.findByNroSala", query = "SELECT s FROM Sala s WHERE s.nroSala = :nroSala"),
    @NamedQuery(name = "Sala.findByEstado", query = "SELECT s FROM Sala s WHERE s.estado = :estado")})
public class Sala implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_sala")
    private Integer idSala;
    @Basic(optional = false)
    @Column(name = "nroAsientos")
    private int nroAsientos;
    @Basic(optional = false)
    @Column(name = "nroSala")
    private int nroSala;
    @Basic(optional = false)
    @Column(name = "estado")
    private short estado;
    @JoinColumn(name = "id_cartelera", referencedColumnName = "id_cartelera")
    @ManyToOne(optional = false)
    private Cartelera idCartelera;

    public Sala() {
    }

    public Sala(Integer idSala) {
        this.idSala = idSala;
    }

    public Sala(Integer idSala, int nroAsientos, int nroSala, short estado) {
        this.idSala = idSala;
        this.nroAsientos = nroAsientos;
        this.nroSala = nroSala;
        this.estado = estado;
    }

    public Integer getIdSala() {
        return idSala;
    }

    public void setIdSala(Integer idSala) {
        this.idSala = idSala;
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

    public short getEstado() {
        return estado;
    }

    public void setEstado(short estado) {
        this.estado = estado;
    }

    public Cartelera getIdCartelera() {
        return idCartelera;
    }

    public void setIdCartelera(Cartelera idCartelera) {
        this.idCartelera = idCartelera;
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
