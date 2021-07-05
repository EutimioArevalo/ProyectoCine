/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
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
@Table(name = "cartelera")
public class cartelera implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id_cartelera;
    private Date horario;
    private Calendar duracionEnCartelera;
    private float precio;
    @OneToMany(mappedBy = "cartelera", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<pelicula> listaPelicula = new ArrayList<pelicula>();
    @OneToMany(mappedBy = "sala", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<sala> listaSala = new ArrayList<sala>();
    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
    @JoinColumn(name = "id_ticket", nullable = false, referencedColumnName = "id_ticket")
    private ticket ticket;

    public ticket getTicket() {
        return ticket;
    }

    public void setTicket(ticket ticket) {
        this.ticket = ticket;
    }

    public List<sala> getListaSala() {
        return listaSala;
    }

    public void setListaSala(List<sala> listaSala) {
        this.listaSala = listaSala;
    }

    public List<pelicula> getListaPelicula() {
        return listaPelicula;
    }

    public void setListaPelicula(List<pelicula> listaPelicula) {
        this.listaPelicula = listaPelicula;
    }

    public Date getHorario() {
        return horario;
    }

    public void setHorario(Date horario) {
        this.horario = horario;
    }

    public Calendar getDuracionEnCartelera() {
        return duracionEnCartelera;
    }

    public void setDuracionEnCartelera(Calendar duracionEnCartelera) {
        this.duracionEnCartelera = duracionEnCartelera;
    }

    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }

    public Long getId_cartelera() {
        return id_cartelera;
    }

    public void setId_cartelera(Long id_cartelera) {
        this.id_cartelera = id_cartelera;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id_cartelera != null ? id_cartelera.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id_cartelera fields are not set
        if (!(object instanceof cartelera)) {
            return false;
        }
        cartelera other = (cartelera) object;
        if ((this.id_cartelera == null && other.id_cartelera != null) || (this.id_cartelera != null && !this.id_cartelera.equals(other.id_cartelera))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Modelo.cartelera[ id=" + id_cartelera + " ]";
    }

}
