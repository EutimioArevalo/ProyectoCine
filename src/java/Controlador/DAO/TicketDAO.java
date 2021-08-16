/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador.DAO;

import Controlador.JPA.TicketJpaController;
import Modelo.Cartelera;
import Modelo.Detallefactura;
import Modelo.Ticket;
import java.util.List;

/**
 *
 * @author David Rodriguez
 */
public class TicketDAO {

    Ticket ticket = new Ticket();
    TicketJpaController tjc = new TicketJpaController();

    /**
     * Metodo para insertar un ticket en la BD
     * @param nombrePelicula Nombre de la Pelicula
     * @param precio Precio del Ticket
     * @param asientos Asiento asignado
     * @param tipo Tipo de ticket
     * @param idCartelera Id de la cartelera
     * @param idDetalle id del detalle de factura.
     */
    public void agregar(String nombrePelicula, float precio, String asientos, String tipo, Cartelera idCartelera, Detallefactura idDetalle) {
        try {
            ticket = new Ticket();
            ticket.setIdTicket(Integer.BYTES);
            ticket.setNroTicket(tjc.getTicketCount() + 1);
            ticket.setNombrePelicula(nombrePelicula);
            ticket.setPrecio(precio);
            ticket.setAsientos(asientos);
            ticket.setTipo(tipo);
            ticket.setIdCartelera(idCartelera);
            ticket.setIdDetalle(idDetalle);
            tjc.create(ticket);
            System.out.println("Agregado");
        } catch (Exception e) {
        }
    }
    
    public Cartelera obtenerIdCartelera(int idDetalle){
        Cartelera aux = null;
        List<Ticket> list = tjc.findTicketEntities();
        for (Ticket ticket1 : list) {
            if(idDetalle == ticket1.getIdDetalle().getIdDetalle()){
                aux = ticket1.getIdCartelera();
                break;
            } 
        }
        return aux;
    }
}
