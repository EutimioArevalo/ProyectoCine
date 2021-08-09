/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador.Dao;

import Controlador.Jpa.SnackJpaController;
import Modelo.Detallefactura;
import Modelo.Snack;
import java.util.List;

/**
 *
 * @author Paccha
 */
public class SnackDAO {

    private Snack snack = new Snack();
    private SnackJpaController snackctrl = new SnackJpaController();
    String mensaje = " ";

    public String ingresar(String nombre, String descripcion, float precio, Detallefactura idDetalleFactura) {
        try {
            // revision del ID asignado 
            snack.setIdSnack(Integer.SIZE);
            snack.setNombre(nombre);
            snack.setDescripcion(descripcion);
            snack.setPrecio(precio);
            // IDfactura es una clave foranea 
            snack.setIdDetalleFactura(idDetalleFactura);
            // se crea en la base de datos un nuevo elemento con la informacion ingresada
            snackctrl.create(snack);
            mensaje = "la tarea se realizo con exito";
        } catch (Exception e) {
            mensaje = "la tara fallo";
            System.out.println(e.getMessage());
        }
        return mensaje;
    }

    // mandar el id del snack a modificar, REVISAR no se envia el ID
    public String modificar(String nombre, String descripcion, float precio, Detallefactura idDetalleFactura) {
        try {
            // reailizar un recorrido de la lista de snaks para comparar id   
            List<Snack> snacklist = snackctrl.findSnackEntities();
            for (Snack snack1 : snacklist) {
                // recorrer la lista 
                if (snack1.getNombre().equals(nombre)) {
                    //si el elemento snack 1 es igual al ingresado se modifica
                    snack1.setIdSnack(Integer.SIZE);
                    snack1.setNombre(nombre);
                    snack1.setDescripcion(descripcion);
                    snack1.setPrecio(precio);
                    snack1.setIdDetalleFactura(idDetalleFactura);
                    snackctrl.edit(snack1);
                    mensaje = "la tarea se realizo con exito";
                }
            }
            // se setea el ID delsnack a modificar REVISAR 
        } catch (Exception e) {
            mensaje = "la tarea fallo";
            System.out.println(e.getMessage());
        }
        return mensaje;
    }
// ES NECESARIO DETERMINAR QUE ELEMENTO SE DEBE DAR DE BAJA 
    // NOMBRE NO DA REFERENCCIA DE ELEMENTO 

    public String darbaja(int nrosnack) {
        try {
            int bajasnack = 0;
            List<Snack> snacklist = snackctrl.findSnackEntities();
            for (Snack snack1 : snacklist) {
                // se llega al elemento tras recorrerlo y compararlo con el numero que 
                // el snack consta en la tabla de la vista 
                if ((bajasnack + 1) == nrosnack) {
                    //se baja el elemento que posea el numero de snack -1, por las listas
                    snackctrl.destroy((nrosnack - 1));
                    mensaje = "la tarea se realizo con exito";
                    // analizar
                    break;
                }
                bajasnack++;
            }

        } catch (Exception e) {
            mensaje = "la tarea fallo";
            System.out.println(e.getMessage());
        }
        return mensaje;
    }
}
