/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador.DAO;

import Controlador.JPA.CarteleraJpaController;
import Controlador.JPA.TicketJpaController;
import Controlador.Utiles;
import Modelo.Cartelera;
import Modelo.Pelicula;
import Modelo.Sala;
import Modelo.Ticket;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author Jonathan Andrade
 */
public class CarteleraDAO {

    private Cartelera cartelera = new Cartelera();
    private Utiles u = new Utiles();
    private CarteleraJpaController cjc = new CarteleraJpaController();
    private TicketJpaController tjc = new TicketJpaController();

    /**
     * Metodo para insertar una Cartelera en la Base de Datos.
     *
     * @param horario Hora de la cartelera en la que se proyectará la pelicula
     * @param duracionCartelera Duración de la Pelicula en Cartelera
     * @param precio Precio de la Pelicula que estará en Cartelera
     * @param pelicula Pelicula que estará en Cartelera
     * @param sala Sala en la que se transmitirá la pelicula
     */
    public void agregar(String horario, String duracionCartelera, float precio, Pelicula pelicula, Sala sala) {

        cartelera.setIdCartelera(Integer.BYTES);
        cartelera.setHorario(horario);
        cartelera.setDuracionCartelera(duracionCartelera);
        cartelera.setPrecio(precio);
        cartelera.setIdPelicula(pelicula);
        cartelera.setIdSala(sala);
        cartelera.setAsientos(u.toJson(sala.getNroAsientos()));
        cjc.create(cartelera);
        System.out.println("No se pudo agregar");

    }

    public void editarCartelera(int idCartelera, String horario, String duracionCartelera, Float precio, Pelicula pelicula, Sala sala) {
        try {
            Cartelera aux = cjc.findCartelera(idCartelera);
            aux.setIdCartelera(idCartelera);
            aux.setHorario(horario);
            aux.setDuracionCartelera(duracionCartelera);
            aux.setPrecio(precio);
            aux.setIdPelicula(pelicula);
            aux.setIdSala(sala);
            aux.setAsientos(u.toJson(sala.getNroAsientos()));
            cjc.edit(aux);
        } catch (Exception e) {
        }
    }

    public boolean coincidenciaHorarios(String fecha, String hora, Sala sala) {
        boolean flag = false;
        List<Cartelera> lista = cjc.findCarteleraEntities();
        for (Cartelera cartelera1 : lista) {
            if (cartelera1.getHorario().equals(hora) && cartelera1.getDuracionCartelera().equals(fecha) && cartelera1.getIdSala().getIdSala() == sala.getIdSala()) {
                flag = true;
            }
        }
        return flag;
    }

    /**
     * Metodo para editar los asientos seleccionados
     *
     * @param idCartelera Identificador de la Cartelera
     * @param asientos Asientos modificados
     */
    public void asientos(int idCartelera, String asientos) {
        try {
            Cartelera aux = cjc.findCartelera(idCartelera);
            aux.setAsientos(asientos);
            cjc.edit(aux);
        } catch (Exception e) {
            System.out.println("Error al momento de editar en asientos");
        }
    }

    /**
     * Metodo para buscar una Cartelerá mediante su Identificador
     *
     * @param id Identificador para encontrar la cartelera en la Base de datos.
     * @return Retorno de la cartelera según la busqueda efecutuada.
     */
    public Cartelera buscar(int id) {
        List<Cartelera> list = cjc.findCarteleraEntities();
        Cartelera cue = null;
        for (Cartelera c : list) {
            if (id == c.getIdCartelera()) {
                cue = c;
                break;
            }
        }
        return cue;
    }

    /**
     * Metodo para obtener una lista de carteleras mediente el Identificador de
     * la Pelicula
     *
     * @param idP Identificador de la Pelicula
     * @return Retorna una lista de las carteleras de una pelicula en
     * particular.
     */
    public List<Cartelera> obtenerPorPelicula(int idP) {
        List<Cartelera> lista = new ArrayList<>();
        for (Cartelera c : cjc.findCarteleraEntities()) {
            if (idP == c.getIdPelicula().getIdPelicula()) {
                lista.add(c);
            }
        }
        return lista;
    }

    public List<Ticket> listaTickets(Integer id) {
        List<Ticket> tickets = tjc.findTicketEntities();
        List<Ticket> lista = new ArrayList<Ticket>();
        for (Ticket cue : tickets) {
            if (Objects.equals(id, cue.getIdCartelera().getIdCartelera())) {
                lista.add(cue);
            }
        }
        return lista;
    }

}
