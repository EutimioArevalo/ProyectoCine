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
@Table(name = "pelicula")
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
    @NamedQuery(name = "Pelicula.findByElenco", query = "SELECT p FROM Pelicula p WHERE p.elenco = :elenco"),
    @NamedQuery(name = "Pelicula.findByClasificacion", query = "SELECT p FROM Pelicula p WHERE p.clasificacion = :clasificacion"),
    @NamedQuery(name = "Pelicula.findByResolucion", query = "SELECT p FROM Pelicula p WHERE p.resolucion = :resolucion"),
    @NamedQuery(name = "Pelicula.findByFormato", query = "SELECT p FROM Pelicula p WHERE p.formato = :formato")})
public class Pelicula implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idPelicula", nullable = false)
    private Integer idPelicula;
    @Basic(optional = false)
    @Column(name = "titulo", nullable = false, length = 100)
    private String titulo;
    @Basic(optional = false)
    @Column(name = "sipnosis", nullable = false, length = 2000)
    private String sipnosis;
    @Basic(optional = false)
    @Column(name = "trailer", nullable = false, length = 200)
    private String trailer;
    @Basic(optional = false)
    @Column(name = "portada", nullable = false, length = 500)
    private String portada;
    @Basic(optional = false)
    @Column(name = "fechaEstreno", nullable = false, length = 10)
    private String fechaEstreno;
    @Basic(optional = false)
    @Column(name = "duracion", nullable = false, length = 10)
    private String duracion;
    @Basic(optional = false)
    @Column(name = "genero", nullable = false, length = 100)
    private String genero;
    @Basic(optional = false)
    @Column(name = "idioma", nullable = false, length = 45)
    private String idioma;
    @Basic(optional = false)
    @Column(name = "director", nullable = false, length = 100)
    private String director;
    @Basic(optional = false)
    @Column(name = "elenco", nullable = false, length = 1000)
    private String elenco;
    @Basic(optional = false)
    @Column(name = "clasificacion", nullable = false, length = 5)
    private String clasificacion;
    @Basic(optional = false)
    @Column(name = "resolucion", nullable = false, length = 10)
    private String resolucion;
    @Basic(optional = false)
    @Column(name = "formato", nullable = false, length = 10)
    private String formato;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idPelicula", fetch = FetchType.LAZY)
    private List<Cartelera> carteleraList;

    public Pelicula() {
    }

    public Pelicula(Integer idPelicula) {
        this.idPelicula = idPelicula;
    }

    public Pelicula(Integer idPelicula, String titulo, String sipnosis, String trailer, String portada, String fechaEstreno, String duracion, String genero, String idioma, String director, String elenco, String clasificacion, String resolucion, String formato) {
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
        this.elenco = elenco;
        this.clasificacion = clasificacion;
        this.resolucion = resolucion;
        this.formato = formato;
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

    public String getElenco() {
        return elenco;
    }

    public void setElenco(String elenco) {
        this.elenco = elenco;
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

    public List<Cartelera> getCarteleraList() {
        return carteleraList;
    }

    public void setCarteleraList(List<Cartelera> carteleraList) {
        this.carteleraList = carteleraList;
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
