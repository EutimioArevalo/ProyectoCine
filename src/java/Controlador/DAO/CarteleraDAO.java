/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador.DAO;

import Controlador.JPA.CarteleraJpaController;
import Modelo.Cartelera;
import java.util.List;

/**
 *
 * @author timoa
 */
public class CarteleraDAO {
    public Cartelera cartelera = new Cartelera();
    public CarteleraJpaController cjc = new CarteleraJpaController();
    
    
    
    public void listaPeliculas(){
        cartelera.getPeliculaList();
    }
    
    
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
}
