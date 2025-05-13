package es.masanz.utrep.editormapa.v1;

import org.eclipse.jetty.io.Connection;

import java.sql.DriverManager;
import java.sql.SQLException;


public class EditorDBDao {

    private String jdbcURL = "jdbc:mysql://localhost:3306/ut10ejemplos";
    private String user = "root";
    private String pass = "root";
    private Connection conexion;

    public void establecerConexion() {




        try {


            Class.forName("com.mysql.cj.jdbc.Driver");

            conexion = (Connection) DriverManager.getConnection(jdbcURL, user, pass);
            System.out.println("Conexión establecida");

            conexion.close();


        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }

}

