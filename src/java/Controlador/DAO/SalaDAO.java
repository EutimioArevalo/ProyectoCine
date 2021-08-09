/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador.Dao;

import Controlador.Jpa.SalaJpaController;
import Modelo.Cartelera;
import Modelo.Sala;
import java.util.List;

/**
 *
 * @author Paccha
 */
public class SalaDAO {
   
    private Sala sala = new Sala();
    private SalaJpaController salactrl = new SalaJpaController();
    String mensaje = " ";

    public String ingresar(short estado, int nrofila, int nrocolum, int nrosala, Cartelera idcartelera) {
        try {
            // seteo el ID de la sala
            sala.setIdSala(Integer.BYTES);
            // seteo del numero de asientos
            // dentro de la vista se piden las filas y columnas de los asientos por ende se multiplican
            sala.setNroAsientos((nrofila * nrocolum));
            //seteo del numero de la sala
            sala.setNroSala(nrosala);
            //ingresar el estado de la sala dado por opciones de boton
            sala.setEstado(estado);
            //se setee el id de la cartelera 
            sala.setIdCartelera(idcartelera);
            //SE CREA LA SALA 
            salactrl.create(sala);
            mensaje = "tarea realizada con exito";
        } catch (Exception e) {
            mensaje = "error en la tarea";
            System.out.println(e.getMessage());
        }
        return mensaje;
    }

    public String darbaja(int nrosala) {
        try {
            // para saber cual es el id de la sala a dar de baja 
            int salabaja = 0;
            List<Sala> salalist = salactrl.findSalaEntities();
            // se reocrre la sala 
            for (Sala sala1 : salalist) {
                // si son iguales los numeros de la sala 
                if (sala1.getNroSala() == nrosala) {
                    //sala baja se asigna el id de la sala igual
                    salabaja = sala1.getIdSala();
                }
            }
            // se elimina la sala junto al id encontrado
            salactrl.destroy(salabaja);
            mensaje = "Tarea realizada con exito";
        } catch (Exception e) {
            mensaje = "fallo en la tarea";
            System.out.println(e.getMessage());
        }
        return mensaje;
    }
    
// es necesario mandar el id de la sala para modificarla REVISAR    
    public String modificar(short estado, int nrofila, int nrocolum, int nrosala, Cartelera idcartelera) {
        try {
            // recorrido de una lista de sala
            List<Sala> salalist = salactrl.findSalaEntities();
            for (Sala sala1 : salalist) {
                //se modifica la sala segun el nro de sala
                if (sala1.getNroSala() == nrosala) {
                    // se hace la modificacion en el elemento de la lista sala1
                    /* seteo del numero de asientos
                     dentro de la vista se piden las filas y columnas de los asientos por ende se multiplican*/
                    sala1.setNroAsientos((nrofila * nrocolum));
                    //seteo del numero de la sala
                    sala1.setNroSala(nrosala);
                    //ingresar el estado de la sala dado por opciones de boton
                    sala1.setEstado(estado);
                    //se setee el id de la cartelera 
                    sala1.setIdCartelera(idcartelera);
                    //SE CREA LA SALA 
                    salactrl.edit(sala1);
                    mensaje = "tarea realizada con exito";
                }
            }

        } catch (Exception e) {
            mensaje = "error en la tarea";
            System.out.println(e.getMessage());
        }
        return mensaje;
    }
    

}
