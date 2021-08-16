/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador.DAO;

import Controlador.JPA.CarteleraJpaController;
import Controlador.JPA.TicketJpaController;
import Modelo.Cartelera;
import Modelo.Pelicula;
import Modelo.Sala;
import Modelo.Ticket;
import com.sun.org.apache.bcel.internal.generic.AALOAD;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author Jonathan Javier
 */
public class CarteleraDAO {

    private Cartelera cartelera = new Cartelera();
    private CarteleraJpaController cjc = new CarteleraJpaController();
    private TicketJpaController tjc = new TicketJpaController();

    /**
     * Este método se encarga de crear una cartelera nueva
     *
     * @param horario es una cadena de texto con la hora en que se proyectará la pelicula
     * @param duracionCartelera es una cadena de texto con la fecha limite en que se mostrará la cartelera
     * @param precio es una dato de tipo float que contiene el precio a pagar por ver la pelicula
     * @param pelicula objeto de tipo Pelicula que permite asignarla a la cartelera
     * @param sala objeto de tipo Sala que permite asignarla a la cartelera
     */
    public void crearCartelera(String horario, String duracionCartelera, Float precio, Pelicula pelicula, Sala sala) {
        try {
            cartelera.setIdCartelera(Integer.BYTES);
            cartelera.setHorario(horario);
            cartelera.setDuracionCartelera(duracionCartelera);
            cartelera.setPrecio(precio);
            cartelera.setIdPelicula(pelicula);
            cartelera.setIdSala(sala);
            cartelera.setTicketList(new ArrayList<>());
            cjc.create(cartelera);
            cartelera = null;
        } catch (Exception e) {
        }
    }
    
    /**
     * Este método se encarga de crear una lista de tickets temporal
     *
     * @param id es un entero que permite acceder a la cuenta para agregar la lista
     */
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
    
    /**
     * Este método se encarga de editar una cartelera seleccionada
     *
     * @param idCartelera es un entero que representa el id de la cartelera que se desea modificar
     * @param horario es una cadena de texto con la hora en que se proyectará la pelicula
     * @param duracionCartelera es una cadena de texto con la fecha limite en que se mostrará la cartelera
     * @param precio es una dato de tipo float que contiene el precio a pagar por ver la pelicula
     * @param pelicula objeto de tipo Pelicula que permite asignarla a la cartelera
     * @param sala objeto de tipo Sala que permite asignarla a la cartelera
     */
    public void editarCartelera(int idCartelera, String horario, String duracionCartelera, Float precio, Pelicula pelicula, Sala sala) {
        try {
            cartelera.setIdCartelera(idCartelera);
            cartelera.setHorario(horario);
            cartelera.setDuracionCartelera(duracionCartelera);
            cartelera.setPrecio(precio);
            cartelera.setIdPelicula(pelicula);
            cartelera.setIdSala(sala);
            cartelera.setTicketList(new ArrayList<>());
            cjc.edit(cartelera);
        } catch (Exception e) {   
        }
    }
    /**
     * Este método se encarga de notificar en caso de que ya exista una cartelera con un mismo horario
     *
     * @param fecha es una cadena de texto que representa la fecha que se desea agregar a la nueva cartelera
     * @param hora es una cadena de texto con la hora en que se proyectará la pelicula de la nueva cartelera
     * @param sala es un objeto de tipo Sala que se quiere asignar a la nueva cartelera
     */
    public boolean coincidenciaHorarios(String fecha, String hora, Sala sala){
        boolean flag = false;
        List<Cartelera> lista = cjc.findCarteleraEntities();
        for (Cartelera cartelera1 : lista) {
            if (cartelera1.getHorario().equals(hora) && cartelera1.getDuracionCartelera().equals(fecha) && cartelera1.getIdSala().getIdSala() == sala.getIdSala()) {
                flag = true;
            }
        }
        return flag;
    }
}
