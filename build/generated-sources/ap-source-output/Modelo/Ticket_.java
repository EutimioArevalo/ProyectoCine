package Modelo;

import Modelo.Cartelera;
import Modelo.Detallefactura;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.7.v20200504-rNA", date="2021-08-15T23:22:42")
@StaticMetamodel(Ticket.class)
public class Ticket_ { 

    public static volatile SingularAttribute<Ticket, Integer> idTicket;
    public static volatile SingularAttribute<Ticket, Cartelera> idCartelera;
    public static volatile SingularAttribute<Ticket, Float> precio;
    public static volatile SingularAttribute<Ticket, String> tipo;
    public static volatile SingularAttribute<Ticket, Detallefactura> idDetalle;
    public static volatile SingularAttribute<Ticket, String> asientos;
    public static volatile SingularAttribute<Ticket, String> nombrePelicula;
    public static volatile SingularAttribute<Ticket, Integer> nroTicket;

}