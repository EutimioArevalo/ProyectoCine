package Modelo;

import Modelo.Cartelera;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.7.v20200504-rNA", date="2021-08-15T23:22:42")
@StaticMetamodel(Pelicula.class)
public class Pelicula_ { 

    public static volatile SingularAttribute<Pelicula, String> resolucion;
    public static volatile SingularAttribute<Pelicula, String> director;
    public static volatile SingularAttribute<Pelicula, String> elenco;
    public static volatile SingularAttribute<Pelicula, String> formato;
    public static volatile SingularAttribute<Pelicula, String> titulo;
    public static volatile SingularAttribute<Pelicula, String> idioma;
    public static volatile SingularAttribute<Pelicula, String> trailer;
    public static volatile SingularAttribute<Pelicula, String> sipnosis;
    public static volatile SingularAttribute<Pelicula, String> fechaEstreno;
    public static volatile SingularAttribute<Pelicula, String> genero;
    public static volatile SingularAttribute<Pelicula, String> duracion;
    public static volatile ListAttribute<Pelicula, Cartelera> carteleraList;
    public static volatile SingularAttribute<Pelicula, Integer> idPelicula;
    public static volatile SingularAttribute<Pelicula, String> clasificacion;
    public static volatile SingularAttribute<Pelicula, String> portada;

}