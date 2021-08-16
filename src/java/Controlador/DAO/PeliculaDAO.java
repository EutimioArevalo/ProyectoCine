/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador.DAO;

import Controlador.JPA.PeliculaJpaController;
import Modelo.Pelicula;
import java.util.List;

/**
 *
 * @author Jonathan Javier
 */
public class PeliculaDAO {
    private Pelicula pelicula = new Pelicula();
    private PeliculaJpaController pjc = new PeliculaJpaController();

    public void guardar(String titulo, String sipnosis, String trailer, String portada, String fechaEstreno, String duracion, String genero,
            String idioma, String director, String actorPrincipal, String clasificacion, String resolucion, String fomato) {

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
            pelicula.setActorPrincipal(actorPrincipal);
            pelicula.setClasificacion(clasificacion);
            pelicula.setResolucion(resolucion);
            pelicula.setFomato(fomato);
            pjc.create(pelicula);

        } catch (Exception e) {

        }

    }

    public void editar(int id, String titulo, String sipnosis, String trailer, String portada, String fechaEstreno, String duracion, String genero,
            String idioma, String director, String actorPrincipal, String clasificacion, String resolucion, String fomato) {

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
            pelicula.setActorPrincipal(actorPrincipal);
            pelicula.setClasificacion(clasificacion);
            pelicula.setResolucion(resolucion);
            pelicula.setFomato(fomato);
            pjc.edit(pelicula);

        } catch (Exception e) {

        }

    }

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

    public Pelicula buscar(int id) {
        List<Pelicula> list = pjc.findPeliculaEntities();
        Pelicula p = null;
        for (Pelicula pelicula : list) {
            if (id == pelicula.getIdPelicula()) {
                p = pelicula;
                break;
            }
        }
        return p;
    }
}
