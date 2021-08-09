package Modelo;

import Modelo.Cartelera;
import Modelo.Detallefactura;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.7.v20200504-rNA", date="2021-08-08T19:04:51")
@StaticMetamodel(Ticket.class)
public class Ticket_ { 

    public static volatile SingularAttribute<Ticket, Integer> idTicket;
    public static volatile SingularAttribute<Ticket, String> tipo;
    public static volatile SingularAttribute<Ticket, Float> precio;
    public static volatile SingularAttribute<Ticket, Cartelera> idcartelera;
    public static volatile SingularAttribute<Ticket, Integer> asientos;
    public static volatile SingularAttribute<Ticket, String> nombrePelicula;
    public static volatile SingularAttribute<Ticket, Integer> nroTicket;
    public static volatile SingularAttribute<Ticket, Detallefactura> idDetalleFactura;
    public static volatile SingularAttribute<Ticket, String> codigoQR;

}