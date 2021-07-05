/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author timoa
 */
@Entity
@Table(name = "pelicula")
public class pelicula implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id_pelicula;
    private String titulo;
    private String sipnosis;
    private String trailer;
    private String portada;
    private Calendar fechaEstreno;
    private double duración;
    private String genero;
    private String idioma;
    private String director;
    private String protagonista;
    private String clasificacion;
    private String resolucion;
    private String formato;
    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
    @JoinColumn(name = "id_cartelera", nullable = false, referencedColumnName = "id_cartelera")
    private cartelera cartelera;

    public cartelera getCartelera() {
        return cartelera;
    }

    public void setCartelera(cartelera cartelera) {
        this.cartelera = cartelera;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getSipnosis() {
        return sipnosis;
    }

    public void setSipnosis(String sipnosis) {
        this.sipnosis = sipnosis;
    }

    public String getTrailer() {
        return trailer;
    }

    public void setTrailer(String trailer) {
        this.trailer = trailer;
    }

    public String getPortada() {
        return portada;
    }

    public void setPortada(String portada) {
        this.portada = portada;
    }

    public Calendar getFechaEstreno() {
        return fechaEstreno;
    }

    public void setFechaEstreno(Calendar fechaEstreno) {
        this.fechaEstreno = fechaEstreno;
    }

    public double getDuración() {
        return duración;
    }

    public void setDuración(double duración) {
        this.duración = duración;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getIdioma() {
        return idioma;
    }

    public void setIdioma(String idioma) {
        this.idioma = idioma;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getProtagonista() {
        return protagonista;
    }

    public void setProtagonista(String protagonista) {
        this.protagonista = protagonista;
    }

    public String getClasificacion() {
        return clasificacion;
    }

    public void setClasificacion(String clasificacion) {
        this.clasificacion = clasificacion;
    }

    public String getResolucion() {
        return resolucion;
    }

    public void setResolucion(String resolucion) {
        this.resolucion = resolucion;
    }

    public String getFormato() {
        return formato;
    }

    public void setFormato(String formato) {
        this.formato = formato;
    }

    public Long getId_pelicula() {
        return id_pelicula;
    }

    public void setId_pelicula(Long id_pelicula) {
        this.id_pelicula = id_pelicula;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id_pelicula != null ? id_pelicula.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id_pelicula fields are not set
        if (!(object instanceof pelicula)) {
            return false;
        }
        pelicula other = (pelicula) object;
        if ((this.id_pelicula == null && other.id_pelicula != null) || (this.id_pelicula != null && !this.id_pelicula.equals(other.id_pelicula))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Modelo.Pelicula[ id=" + id_pelicula + " ]";
    }

}
