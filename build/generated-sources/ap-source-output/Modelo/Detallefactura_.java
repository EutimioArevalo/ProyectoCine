package Modelo;

import Modelo.Factura;
import Modelo.Snack;
import Modelo.Ticket;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.7.v20200504-rNA", date="2021-08-08T19:04:51")
@StaticMetamodel(Detallefactura.class)
public class Detallefactura_ { 

    public static volatile ListAttribute<Detallefactura, Ticket> ticketList;
    public static volatile SingularAttribute<Detallefactura, Float> precioUnitario;
    public static volatile SingularAttribute<Detallefactura, Integer> cantidadTicket;
    public static volatile SingularAttribute<Detallefactura, Integer> cantidadSnack;
    public static volatile ListAttribute<Detallefactura, Factura> facturaList;
    public static volatile SingularAttribute<Detallefactura, Integer> idDetalleFactura;
    public static volatile SingularAttribute<Detallefactura, Float> precioTotal;
    public static volatile ListAttribute<Detallefactura, Snack> snackList;

}