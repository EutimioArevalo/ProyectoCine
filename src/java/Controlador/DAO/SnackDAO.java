/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador.DAO;

import Controlador.JPA.SnackJpaController;
import Modelo.Detallefactura;
import Modelo.Snack;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Eduardo Paccha
 */
public class SnackDAO {

    Snack snack = new Snack();
    SnackJpaController sjc = new SnackJpaController();

    /**
     * Metodo para insertar un Snack en la BD.
     * @param nombre Nombre del Snack
     * @param descripcion Descripcion del Snack
     * @param precio Precio del Snack
     * @param img URL de la imagen del Snack
     */
    public void agregar(String nombre, String descripcion, float precio, String img) {
        snack.setIdSnack(Integer.BYTES);
        snack.setNombre(nombre);
        snack.setDescripcion(descripcion);
        snack.setPrecio(precio);
        snack.setImg(img);
        sjc.create(snack);
    }

    /**
     * Metodo para editar un Snack
     * @param id Id del Snack a editar
     * @param nombre Nuevo nombre del snack
     * @param descripcion Nueva descripcion del snack
     * @param precio Nuevo precio del snack
     * @param img Nuevo url del snack
     */
    public void editar(int id, String nombre, String descripcion, float precio, String img) {
        try {
            Snack aux = sjc.findSnack(id);
            aux.setIdSnack(id);
            aux.setNombre(nombre);
            aux.setDescripcion(descripcion);
            aux.setPrecio(precio);
            aux.setImg(img);
            sjc.edit(aux);
        }catch(Exception e){
            
        } 

    }

    /**
     * Metodo para buscar un Snack por su id
     * @param id Id del snack a buscar
     * @return Snack con el id ingresado.
     */
    public Snack buscarsnack(int id) {
        List<Snack> snacks = sjc.findSnackEntities();
        Snack s = null;
        for (Snack lista : snacks) {
            if (lista.getIdSnack() == id) {
                s = lista;
                break;
            }
        }
        return s;
    }
}
