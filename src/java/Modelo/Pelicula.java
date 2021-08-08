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
@Table(name = "pelicula")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Pelicula.findAll", query = "SELECT p FROM Pelicula p"),
    @NamedQuery(name = "Pelicula.findByIdPelicula", query = "SELECT p FROM Pelicula p WHERE p.idPelicula = :idPelicula"),
    @NamedQuery(name = "Pelicula.findByTitulo", query = "SELECT p FROM Pelicula p WHERE p.titulo = :titulo"),
    @NamedQuery(name = "Pelicula.findBySipnosis", query = "SELECT p FROM Pelicula p WHERE p.sipnosis = :sipnosis"),
    @NamedQuery(name = "Pelicula.findByTrailer", query = "SELECT p FROM Pelicula p WHERE p.trailer = :trailer"),
    @NamedQuery(name = "Pelicula.findByPortada", query = "SELECT p FROM Pelicula p WHERE p.portada = :portada"),
    @NamedQuery(name = "Pelicula.findByFechaEstreno", query = "SELECT p FROM Pelicula p WHERE p.fechaEstreno = :fechaEstreno"),
    @NamedQuery(name = "Pelicula.findByDuracion", query = "SELECT p FROM Pelicula p WHERE p.duracion = :duracion"),
    @NamedQuery(name = "Pelicula.findByGenero", query = "SELECT p FROM Pelicula p WHERE p.genero = :genero"),
    @NamedQuery(name = "Pelicula.findByIdioma", query = "SELECT p FROM Pelicula p WHERE p.idioma = :idioma"),
    @NamedQuery(name = "Pelicula.findByDirector", query = "SELECT p FROM Pelicula p WHERE p.director = :director"),
    @NamedQuery(name = "Pelicula.findByActorPrincipal", query = "SELECT p FROM Pelicula p WHERE p.actorPrincipal = :actorPrincipal"),
    @NamedQuery(name = "Pelicula.findByClasificacion", query = "SELECT p FROM Pelicula p WHERE p.clasificacion = :clasificacion"),
    @NamedQuery(name = "Pelicula.findByResolucion", query = "SELECT p FROM Pelicula p WHERE p.resolucion = :resolucion"),
    @NamedQuery(name = "Pelicula.findByFomato", query = "SELECT p FROM Pelicula p WHERE p.fomato = :fomato")})
public class Pelicula implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_pelicula")
    private Integer idPelicula;
    @Basic(optional = false)
    @Column(name = "titulo")
    private String titulo;
    @Basic(optional = false)
    @Column(name = "sipnosis")
    private String sipnosis;
    @Basic(optional = false)
    @Column(name = "trailer")
    private String trailer;
    @Basic(optional = false)
    @Column(name = "portada")
    private String portada;
    @Basic(optional = false)
    @Column(name = "fechaEstreno")
    private String fechaEstreno;
    @Basic(optional = false)
    @Column(name = "duracion")
    private String duracion;
    @Basic(optional = false)
    @Column(name = "genero")
    private String genero;
    @Basic(optional = false)
    @Column(name = "idioma")
    private String idioma;
    @Basic(optional = false)
    @Column(name = "director")
    private String director;
    @Basic(optional = false)
    @Column(name = "actorPrincipal")
    private String actorPrincipal;
    @Basic(optional = false)
    @Column(name = "clasificacion")
    private String clasificacion;
    @Basic(optional = false)
    @Column(name = "resolucion")
    private String resolucion;
    @Basic(optional = false)
    @Column(name = "fomato")
    private String fomato;
    @JoinColumn(name = "id_cartelera", referencedColumnName = "id_cartelera")
    @ManyToOne(optional = false)
    private Cartelera idCartelera;

    public Pelicula() {
    }

    public Pelicula(Integer idPelicula) {
        this.idPelicula = idPelicula;
    }

    public Pelicula(Integer idPelicula, String titulo, String sipnosis, String trailer, String portada, String fechaEstreno, String duracion, String genero, String idioma, String director, String actorPrincipal, String clasificacion, String resolucion, String fomato) {
        this.idPelicula = idPelicula;
        this.titulo = titulo;
        this.sipnosis = sipnosis;
        this.trailer = trailer;
        this.portada = portada;
        this.fechaEstreno = fechaEstreno;
        this.duracion = duracion;
        this.genero = genero;
        this.idioma = idioma;
        this.director = director;
        this.actorPrincipal = actorPrincipal;
        this.clasificacion = clasificacion;
        this.resolucion = resolucion;
        this.fomato = fomato;
    }

    public Integer getIdPelicula() {
        return idPelicula;
    }

    public void setIdPelicula(Integer idPelicula) {
        this.idPelicula = idPelicula;
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

    public String getFechaEstreno() {
        return fechaEstreno;
    }

    public void setFechaEstreno(String fechaEstreno) {
        this.fechaEstreno = fechaEstreno;
    }

    public String getDuracion() {
        return duracion;
    }

    public void setDuracion(String duracion) {
        this.duracion = duracion;
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

    public String getActorPrincipal() {
        return actorPrincipal;
    }

    public void setActorPrincipal(String actorPrincipal) {
        this.actorPrincipal = actorPrincipal;
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

    public String getFomato() {
        return fomato;
    }

    public void setFomato(String fomato) {
        this.fomato = fomato;
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
        hash += (idPelicula != null ? idPelicula.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Pelicula)) {
            return false;
        }
        Pelicula other = (Pelicula) object;
        if ((this.idPelicula == null && other.idPelicula != null) || (this.idPelicula != null && !this.idPelicula.equals(other.idPelicula))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Modelo.Pelicula[ idPelicula=" + idPelicula + " ]";
    }
    
}
