package Mundo;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "Aspirantes")

//Atributos
public class Aspirantes {
    @DatabaseField(id = true)
    private int Cedula;
    @DatabaseField
    private String nombre;
    @DatabaseField
    private int experienciaA;
    @DatabaseField
    private int edad;
    @DatabaseField
    private String profesion;
    @DatabaseField
    private int telefono;



    //Contructor
    public Aspirantes () { }
    public Aspirantes(int cedula, String nombre, int experienciaA, int edad, String profesion, int telefono) {
        Cedula = cedula;
        this.nombre = nombre;
        this.experienciaA = experienciaA;
        this.edad = edad;
        this.profesion = profesion;
        this.telefono = telefono ;
    }


    public int getCedula() {
        return Cedula;
    }

    public void setCedula(int cedula) {
        Cedula = cedula;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getExperienciaA() {
        return experienciaA;
    }

    public void setExperienciaA(int experienciaA) {
        this.experienciaA = experienciaA;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String getProfesion() {
        return profesion;
    }

    public void setProfecion(String profesion) {
        this.profesion = profesion;
    }

    public int getTelefono() {return telefono;}

    public void setTelefono(int telefono) {this.telefono = telefono;}


}
