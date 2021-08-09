package Modelo;

import Modelo.Pelicula;
import Modelo.Sala;
import Modelo.Ticket;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.7.v20200504-rNA", date="2021-08-08T19:04:51")
@StaticMetamodel(Cartelera.class)
public class Cartelera_ { 

    public static volatile SingularAttribute<Cartelera, Integer> idCartelera;
    public static volatile SingularAttribute<Cartelera, Date> duracionCartelera;
    public static volatile SingularAttribute<Cartelera, Float> precio;
    public static volatile ListAttribute<Cartelera, Ticket> ticketList;
    public static volatile SingularAttribute<Cartelera, String> horario;
    public static volatile ListAttribute<Cartelera, Pelicula> peliculaList;
    public static volatile ListAttribute<Cartelera, Sala> salaList;

}