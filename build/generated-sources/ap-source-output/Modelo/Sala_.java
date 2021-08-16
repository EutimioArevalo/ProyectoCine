package Modelo;

import Modelo.Cartelera;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.7.v20200504-rNA", date="2021-08-15T23:22:42")
@StaticMetamodel(Sala.class)
public class Sala_ { 

    public static volatile SingularAttribute<Sala, String> estado;
    public static volatile SingularAttribute<Sala, Integer> nroColumnas;
    public static volatile SingularAttribute<Sala, Integer> nroSala;
    public static volatile ListAttribute<Sala, Cartelera> carteleraList;
    public static volatile SingularAttribute<Sala, Integer> idSala;
    public static volatile SingularAttribute<Sala, Integer> nroFilas;
    public static volatile SingularAttribute<Sala, Integer> nroAsientos;

}