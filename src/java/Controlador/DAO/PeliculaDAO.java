/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador.DAO;

import Controlador.JPA.PeliculaJpaController;
import Modelo.Pelicula;
import java.util.Date;
import java.util.List;

/**
 *
 * @author David Rodriguez
 */
public class PeliculaDAO {

    private Pelicula pelicula = new Pelicula();
    private PeliculaJpaController pjc = new PeliculaJpaController();

    /**
     * Metodo para insertar una Pelicula en la Base de Datos.
     * @param titulo Titulo de la pelicula
     * @param sipnosis Sipnosis de la pelicula
     * @param trailer URL de Youtube del trailer de la Pelicula.
     * @param portada URL de la Portada de la pelicula
     * @param fechaEstreno Fecha de estreno de la pelicula
     * @param duracion Duración de la Pelicula
     * @param genero Genero de la Pelicula
     * @param idioma Idioma de la Pelicula
     * @param director Director de la Pelicula
     * @param elenco Elenco de la Pelicula
     * @param clasificacion Clasificacion de la Pelicula
     * @param resolucion Resolución de la Pelicula
     * @param formato Formato de la Pelicula.
     */
    public void guardar(String titulo, String sipnosis, String trailer, String portada, String fechaEstreno, String duracion, String genero,
            String idioma, String director, String elenco, String clasificacion, String resolucion, String formato) {

        try {
            pelicula.setIdPelicula(Integer.BYTES);
            pelicula.setTitulo(titulo);
            pelicula.setSipnosis(sipnosis);
            pelicula.setTrailer(trailer);
            pelicula.setPortada(portada);
            pelicula.setFechaEstreno(fechaEstreno);
            pelicula.setDuracion(duracion);
            pelicula.setGenero(genero);
            pelicula.setIdioma(idioma);
            pelicula.setDirector(director);
            pelicula.setElenco(elenco);
            pelicula.setClasificacion(clasificacion);
            pelicula.setResolucion(resolucion);
            pelicula.setFormato(formato);
            pjc.create(pelicula);

        } catch (Exception e) {

        }

    }

    /**
     * Metodo para editar una pelicula
     * @param id Identificador de la pelicula a editar.
     * @param titulo Nuevo titulo
     * @param sipnosis Nueva Sipnosis
     * @param trailer Nuevo trailer
     * @param portada Nueva portada
     * @param fechaEstreno nueva fecha de estreno
     * @param duracion nueva duracion
     * @param genero nuevo genero
     * @param idioma nuevo idioma
     * @param director nuevo director
     * @param elenco nuevo elenco
     * @param clasificacion nueva clasificación 
     * @param resolucion nueva resolución
     * @param formato nuevo formato
     */
    public void editar(int id, String titulo, String sipnosis, String trailer, String portada, String fechaEstreno, String duracion, String genero,
            String idioma, String director, String elenco, String clasificacion, String resolucion, String formato) {

        try {
            pelicula.setIdPelicula(id);
            pelicula.setTitulo(titulo);
            pelicula.setSipnosis(sipnosis);
            pelicula.setTrailer(trailer);
            pelicula.setPortada(portada);
            pelicula.setFechaEstreno(fechaEstreno);
            pelicula.setDuracion(duracion);
            pelicula.setGenero(genero);
            pelicula.setIdioma(idioma);
            pelicula.setDirector(director);
            pelicula.setElenco(elenco);
            pelicula.setClasificacion(clasificacion);
            pelicula.setResolucion(resolucion);
            pelicula.setFormato(formato);
            pjc.edit(pelicula);

        } catch (Exception e) {

        }

    }
    
    /**
     * Metodo para buscar una pelicula mediante el titulo
     * @param titulo Titulo de la Pelicula a Buscar
     * @return Resultado de la busqueda.
     */

    public Pelicula buscar(String titulo) {
        List<Pelicula> list = pjc.findPeliculaEntities();
        Pelicula p = null;
        for (Pelicula pelicula : list) {
            if (titulo.equals(pelicula.getTitulo())) {
                p = pelicula;
                break;
            }
        }
        return p;
    }

    /**
     * Metodo para buscar una pelicula mediante el Identificador
     * @param id Identificador de la Pelicula
     * @return Retorna la pelicula
     */
    public Pelicula buscar(int id) {
        List<Pelicula> list = pjc.findPeliculaEntities();
        Pelicula p = null;
        for (Pelicula peli : list) {
            if (id == peli.getIdPelicula()) {
                p = peli;
                break;
            }
        }
        return p;
    }
    
}
