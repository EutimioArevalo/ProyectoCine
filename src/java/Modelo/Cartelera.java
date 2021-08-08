/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author timoa
 */
@Entity
@Table(name = "cartelera")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Cartelera.findAll", query = "SELECT c FROM Cartelera c"),
    @NamedQuery(name = "Cartelera.findByIdCartelera", query = "SELECT c FROM Cartelera c WHERE c.idCartelera = :idCartelera"),
    @NamedQuery(name = "Cartelera.findByHorario", query = "SELECT c FROM Cartelera c WHERE c.horario = :horario"),
    @NamedQuery(name = "Cartelera.findByDuracionCartelera", query = "SELECT c FROM Cartelera c WHERE c.duracionCartelera = :duracionCartelera"),
    @NamedQuery(name = "Cartelera.findByPrecio", query = "SELECT c FROM Cartelera c WHERE c.precio = :precio")})
public class Cartelera implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_cartelera")
    private Integer idCartelera;
    @Basic(optional = false)
    @Column(name = "horario")
    private String horario;
    @Basic(optional = false)
    @Column(name = "duracionCartelera")
    @Temporal(TemporalType.DATE)
    private Date duracionCartelera;
    @Basic(optional = false)
    @Column(name = "precio")
    private float precio;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idCartelera")
    private List<Pelicula> peliculaList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idcartelera")
    private List<Ticket> ticketList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idCartelera")
    private List<Sala> salaList;

    public Cartelera() {
    }

    public Cartelera(Integer idCartelera) {
        this.idCartelera = idCartelera;
    }

    public Cartelera(Integer idCartelera, String horario, Date duracionCartelera, float precio) {
        this.idCartelera = idCartelera;
        this.horario = horario;
        this.duracionCartelera = duracionCartelera;
        this.precio = precio;
    }

    public Integer getIdCartelera() {
        return idCartelera;
    }

    public void setIdCartelera(Integer idCartelera) {
        this.idCartelera = idCartelera;
    }

    public String getHorario() {
        return horario;
    }

    public void setHorario(String horario) {
        this.horario = horario;
    }

    public Date getDuracionCartelera() {
        return duracionCartelera;
    }

    public void setDuracionCartelera(Date duracionCartelera) {
        this.duracionCartelera = duracionCartelera;
    }

    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }

    @XmlTransient
    public List<Pelicula> getPeliculaList() {
        return peliculaList;
    }

    public void setPeliculaList(List<Pelicula> peliculaList) {
        this.peliculaList = peliculaList;
    }

    @XmlTransient
    public List<Ticket> getTicketList() {
        return ticketList;
    }

    public void setTicketList(List<Ticket> ticketList) {
        this.ticketList = ticketList;
    }

    @XmlTransient
    public List<Sala> getSalaList() {
        return salaList;
    }

    public void setSalaList(List<Sala> salaList) {
        this.salaList = salaList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idCartelera != null ? idCartelera.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Cartelera)) {
            return false;
        }
        Cartelera other = (Cartelera) object;
        if ((this.idCartelera == null && other.idCartelera != null) || (this.idCartelera != null && !this.idCartelera.equals(other.idCartelera))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Modelo.Cartelera[ idCartelera=" + idCartelera + " ]";
    }
    
}
