/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador.DAO;

import Controlador.JPA.CarritoJpaController;
import Modelo.Carrito;
import Modelo.Detallefactura;
import Modelo.Snack;

/**
 *
 * @author Eutimio Arevalo
 */
public class CarritoDAO {
    Carrito carrito = new Carrito();
    CarritoJpaController cjc = new CarritoJpaController();
    
    /**
     * Metodo para insertar un Carrito en la Base de Datos.
     * @param nombreSnack Nombre del Snack
     * @param cantidad Cantidad del Snack
     * @param precioU Precio unitario del Snack
     * @param precioT Precio Total de los Snacks
     * @param snack Snack seleccionado
     * @param idDetalle Detalle de Factura asignado
     */
    public void agregar(String nombreSnack, int cantidad, float precioU, float precioT, Snack snack, Detallefactura idDetalle){
        try {
            carrito = new Carrito();
            carrito.setIdcarrito(Integer.BYTES);
            carrito.setNombreSnack(nombreSnack);
            carrito.setCantidad(cantidad);
            carrito.setPrecioU(precioU);
            carrito.setPrecioT(precioT);
            carrito.setSnack(snack);
            carrito.setIdDetalle(idDetalle);
            cjc.create(carrito);
            System.out.println("Agregado");
        } catch (Exception e) {
        }
    }
}
