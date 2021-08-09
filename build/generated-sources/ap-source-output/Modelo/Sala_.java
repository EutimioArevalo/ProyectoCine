package Modelo;

import Modelo.Cartelera;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.7.v20200504-rNA", date="2021-08-08T19:04:51")
@StaticMetamodel(Sala.class)
public class Sala_ { 

    public static volatile SingularAttribute<Sala, Cartelera> idCartelera;
    public static volatile SingularAttribute<Sala, String> estado;
    public static volatile SingularAttribute<Sala, Integer> filas;
    public static volatile SingularAttribute<Sala, String> asientos;
    public static volatile SingularAttribute<Sala, Integer> nroSala;
    public static volatile SingularAttribute<Sala, Integer> idSala;
    public static volatile SingularAttribute<Sala, Integer> nroAsientos;
    public static volatile SingularAttribute<Sala, Integer> columnas;

}