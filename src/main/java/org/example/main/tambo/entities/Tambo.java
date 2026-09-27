package org.example.main.tambo.entities;
import lombok.Data;
import java.util.ArrayList;

@Data
public class Tambo {
    private int id;
    private String nombre;
    private String codUnidadOperativa;
    private int bajadasOrdene;      //Capacidad de ordene simultaneo

    //Relacion establecimiento Muchos tambos -> Un establecimiento
    private Establecimiento establecimiento;

    //Relacion con tanque de leche Un tambo -> Muchos tanques de almacenamiento
    private ArrayList<TanqueLeche> tanquesLeche;

    //Relacion RegistroOrdenie. Un tambo -> Historico de ordenies
    private ArrayList<RegistroOrdenie> registroOrdenie;

    //Relacion con las vacas en ordenie en este tambo
    private ArrayList<Vaca> vacasEnOrdenie;

    //Para indicar si el tambo esta activo
    private Boolean enOperacion;

    public Tambo(int id, String nombre, String codUnidadOperativa, int bajadasOrdene, Establecimiento establecimiento, ArrayList<TanqueLeche> tanquesLeche, ArrayList<RegistroOrdenie> registroOrdenie, ArrayList<Vaca> vacasEnOrdenie, Boolean enOperacion) {
        setId(id);
        setNombre(nombre);
        setCodUnidadOperativa(codUnidadOperativa);
        setBajadasOrdene(bajadasOrdene);
        setEstablecimiento(establecimiento);
        setTanquesLeche(tanquesLeche);
        setRegistroOrdenie(registroOrdenie);
        setVacasEnOrdenie(vacasEnOrdenie);
        setEnOperacion(enOperacion);
    }


}

