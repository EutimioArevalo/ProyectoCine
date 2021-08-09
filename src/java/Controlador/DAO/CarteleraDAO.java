/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador.DAO;

import Controlador.JPA.CarteleraJpaController;
import Modelo.Cartelera;

/**
 *
 * @author Jonathan Javier
 */
public class CarteleraDAO {
    private Cartelera cartelera = new Cartelera();
    private CarteleraJpaController cjc = new CarteleraJpaController();
    
    public void crearCartelera(String horario, String duracionCartelera, Float precio){
        try {
            cartelera.setIdCartelera(Integer.BYTES);
            cartelera.setHorario(horario);
            cartelera.setDuracionCartelera(duracionCartelera);
            cartelera.setPrecio(precio);
        } catch (Exception e) {
        }
    }
}
