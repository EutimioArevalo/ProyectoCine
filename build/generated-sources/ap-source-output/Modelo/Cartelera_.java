package Modelo;

import Modelo.Pelicula;
import Modelo.Sala;
import Modelo.Ticket;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.7.v20200504-rNA", date="2021-08-15T23:22:42")
@StaticMetamodel(Cartelera.class)
public class Cartelera_ { 

    public static volatile SingularAttribute<Cartelera, Integer> idCartelera;
    public static volatile SingularAttribute<Cartelera, String> duracionCartelera;
    public static volatile SingularAttribute<Cartelera, Float> precio;
    public static volatile ListAttribute<Cartelera, Ticket> ticketList;
    public static volatile SingularAttribute<Cartelera, String> horario;
    public static volatile SingularAttribute<Cartelera, String> asientos;
    public static volatile SingularAttribute<Cartelera, Pelicula> idPelicula;
    public static volatile SingularAttribute<Cartelera, Sala> idSala;

}