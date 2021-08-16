package Modelo;

import Modelo.Carrito;
import Modelo.Factura;
import Modelo.Ticket;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.7.v20200504-rNA", date="2021-08-15T23:22:42")
@StaticMetamodel(Detallefactura.class)
public class Detallefactura_ { 

    public static volatile ListAttribute<Detallefactura, Carrito> carritoList;
    public static volatile ListAttribute<Detallefactura, Ticket> ticketList;
    public static volatile SingularAttribute<Detallefactura, Integer> idDetalle;
    public static volatile SingularAttribute<Detallefactura, Integer> cantidadTicket;
    public static volatile SingularAttribute<Detallefactura, Integer> cantidadSnack;
    public static volatile ListAttribute<Detallefactura, Factura> facturaList;
    public static volatile SingularAttribute<Detallefactura, Float> precioTotal;

}