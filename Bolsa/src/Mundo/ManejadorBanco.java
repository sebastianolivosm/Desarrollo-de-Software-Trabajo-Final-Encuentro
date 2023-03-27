package Mundo;

import Mundo.Aspirantes;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.logger.Level;
import com.j256.ormlite.logger.Logger;
import com.j256.ormlite.support.ConnectionSource;
import java.util.Scanner;

public class ManejadorBanco {
    public static void main(String[] args) throws Exception {
        Logger.setGlobalLogLevel(Level.OFF);
        // Ubicación del archivo de la base de datos
        String url = "jdbc:h2:file:./Bolsa de empleados";
        ConnectionSource conexion =
                new JdbcConnectionSource(url);
        // Obtener acceso a la lista de objetos=>Tabla (DAO)
        // Primero es la clase de la tabla, Segundo tipo de la llave
        Dao<Aspirantes, String> lista_aspirantes =
                DaoManager.createDao(conexion, Aspirantes.class);

        Scanner teclado = new Scanner(System.in);

        System.out.print("Ingrese el nombre de el Aspirante: ");
        String nombre = teclado.nextLine();
        System.out.print("Ingrese el nombre de la Profesion: ");
        String profesion = teclado.nextLine();
        System.out.print("Ingrese el numero de la cedula de el aspirante: ");
        int cedula = teclado.nextInt();
        System.out.print("Ingrese el numero de telefono de el aspirante: ");
        int telefono = teclado.nextInt();
        System.out.print("Ingrese la edad de el aspirante: ");
        int edad = teclado.nextInt();
        System.out.print("Ingrese los años de experiencia de el aspirante: ");
        int experiencia= teclado.nextInt();




        Aspirantes aspirantes = new Aspirantes(cedula, nombre,experiencia,edad ,profesion,telefono);
        lista_aspirantes.create(aspirantes);

        for (Aspirantes c : lista_aspirantes) {
            System.out.println("Numero: " + c.getCedula());
        }

        // Cerrar la conexión
        conexion.close();

    }
}