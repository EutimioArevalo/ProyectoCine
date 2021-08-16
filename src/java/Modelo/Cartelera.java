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
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author timoa
 */
@Entity
@Table(name = "cartelera")
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
    @Column(name = "idCartelera", nullable = false)
    private Integer idCartelera;
    @Basic(optional = false)
    @Column(name = "horario", nullable = false, length = 10)
    private String horario;
    @Basic(optional = false)
    @Column(name = "duracionCartelera", nullable = false, length = 10)
    private String duracionCartelera;
    @Basic(optional = false)
    @Column(name = "precio", nullable = false)
    private float precio;
    @Basic(optional = false)
    @Lob
    @Column(name = "asientos", nullable = false, length = 1073741824)
    private String asientos;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idCartelera", fetch = FetchType.LAZY)
    private List<Ticket> ticketList;
    @JoinColumn(name = "idPelicula", referencedColumnName = "idPelicula", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Pelicula idPelicula;
    @JoinColumn(name = "idSala", referencedColumnName = "idSala", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Sala idSala;

    public Cartelera() {
    }

    public Cartelera(Integer idCartelera) {
        this.idCartelera = idCartelera;
    }

    public Cartelera(Integer idCartelera, String horario, String duracionCartelera, float precio, String asientos) {
        this.idCartelera = idCartelera;
        this.horario = horario;
        this.duracionCartelera = duracionCartelera;
        this.precio = precio;
        this.asientos = asientos;
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

    public String getDuracionCartelera() {
        return duracionCartelera;
    }

    public void setDuracionCartelera(String duracionCartelera) {
        this.duracionCartelera = duracionCartelera;
    }

    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }

    public String getAsientos() {
        return asientos;
    }

    public void setAsientos(String asientos) {
        this.asientos = asientos;
    }

    public List<Ticket> getTicketList() {
        return ticketList;
    }

    public void setTicketList(List<Ticket> ticketList) {
        this.ticketList = ticketList;
    }

    public Pelicula getIdPelicula() {
        return idPelicula;
    }

    public void setIdPelicula(Pelicula idPelicula) {
        this.idPelicula = idPelicula;
    }

    public Sala getIdSala() {
        return idSala;
    }

    public void setIdSala(Sala idSala) {
        this.idSala = idSala;
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
