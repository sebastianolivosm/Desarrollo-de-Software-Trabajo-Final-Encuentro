package Mundo;



import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

public class Crear_Tablas_Bolsa {

    public static void main (String[] args) throws Exception {
        //Ubicacion Base de datos
        String url= "jdbc:h2:file:./Bolsa de empleados";
        ConnectionSource conexion =
                new JdbcConnectionSource(url);
        //Crea Tabla
        TableUtils.createTable(conexion , Aspirantes.class);
        System.out.println("Tabla Creada");
        conexion.close();

    }


}