/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador.DAO;

import Controlador.JPA.SalaJpaController;
import Controlador.Utiles;
import Modelo.Sala;
import java.util.List;

/**
 *
 * @author Eduardo Paccha
 */
public class SalaDAO {

    private Sala sala = new Sala();
    private SalaJpaController sjc = new SalaJpaController();
    private Utiles u = new Utiles();

    /**
     * Metodo para agregar una Sala
     * @param nroAsientos Numero de Asientos
     * @param nroSala numero de sala
     * @param filas numero de filas
     * @param columnas numero de columnas
     */
    public void agregar(int nroAsientos, int nroSala, int filas, int columnas) {
        try {
            sala.setIdSala(Integer.BYTES);
            sala.setNroAsientos(nroAsientos);
            sala.setNroSala(nroSala);
            sala.setEstado("activo");
            sala.setNroFilas(filas);
            sala.setNroColumnas(columnas);
            sjc.create(sala);
        } catch (Exception e) {
        }
    }

    /**
     * Metodo para editar sala
     * @param id Identificador  de la sala a editar
     * @param nroAsientos nuevo numero de asientos
     * @param filas nuevo numero de filas
     * @param columnas nuevo numero de columnas
     * @param estado nuevo estado
     */
    public void editar(int id, int nroAsientos, int filas, int columnas, String estado) {
        try {
            Sala aux = sjc.findSala(id);
            aux.setIdSala(id);
            aux.setNroAsientos(nroAsientos);
            aux.setEstado(estado);
            aux.setNroFilas(filas);
            aux.setNroColumnas(columnas);
            sjc.edit(aux);
        } catch (Exception e) {
        }
    }

  /*  public void asientos(int idSala, String asientos) {
        try {
            Sala aux = sjc.findSala(idSala);
            aux.setAsientos(asientos);
            sjc.edit(aux);
        } catch (Exception e) {
            System.out.println("Error al momento de editar en asientos");
        }
    }*/

    /**
     * Metodo para obtener Sala mediante su nombre
     * @param nombre nombre de la sala
     * @return Obtener la sala con la busqueda efectuada.
     */
    public Sala obtenerSala(String nombre) {
        List<Sala> list = sjc.findSalaEntities();
        Sala s = null;
        for (Sala listaSala : list) {
            if (nombre.equals("Sala: "+listaSala.getNroSala())) {
                s = listaSala;
                break;
            }
        }
        return s;
    }

    /**
     * Metodo para obtener una sala mediante su numero
     * @param nroSala numero de sala
     * @return Sala con el numero ingresado
     */
    public Sala obtenerSala(int nroSala) {
        List<Sala> list = sjc.findSalaEntities();
        Sala s = null;
        for (Sala listaSala : list) {
            System.out.println("Sala nro: " + listaSala.getNroSala());
            if (listaSala.getNroSala() == nroSala) {
                s = listaSala;
                break;
            }
        }
        System.out.println(s.getNroSala() + " tiene " + s.getNroAsientos());
        return s;
    }

    /**
     * Metodo para buscar una sala por su id
     * @param id Id de la sala
     * @return Sala con el id ingresado
     */
    public Sala obtenerSalaID(int id) {
        List<Sala> list = sjc.findSalaEntities();
        Sala s = null;
        for (Sala listaSala : list) {
            if (listaSala.getIdSala() == id) {
                s = listaSala;
                break;
            }
        }
        return s;
    }
}
