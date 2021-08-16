/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador.DAO;

import Controlador.JPA.DetallefacturaJpaController;
import Modelo.Detallefactura;
import Modelo.Ticket;
import java.util.List;

/**
 *
 * @author David Rodriguez
 */
public class DetalleFacturaDAO {

    private Detallefactura detalle = new Detallefactura();
    private DetallefacturaJpaController djc = new DetallefacturaJpaController();

    /**
     * Metodo para agregar un Detalle de Factura a la BD.
     * @param cantidadTicket cantidad de tickets
     * @param cantidadSnack cantidad de snacks
     * @param precioTotal precio total
     */
    public void agregar(int cantidadTicket, int cantidadSnack, float precioTotal) {
        try {
            
            detalle.setIdDetalle(Integer.MIN_VALUE);
            detalle.setCantidadTicket(cantidadTicket);
            detalle.setCantidadSnack(cantidadSnack);
            detalle.setPrecioTotal(precioTotal);
            djc.create(detalle);
            System.out.println("Agregado Correctamente");
        } catch (Exception e) {
        }
    }
    
    /**
     * Metodo para buscar el ultimo detalle de factura añadido
     * @return Retorna el ultimo detalle de factura añadido
     */
    public Detallefactura buscarUltimo(){
        List<Detallefactura> lista = djc.findDetallefacturaEntities();
        return lista.get(lista.size()-1);
    }
}
