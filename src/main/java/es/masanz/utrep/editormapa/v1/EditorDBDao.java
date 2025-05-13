package es.masanz.utrep.editormapa.v1;

import es.masanz.utrep.editormapa.v1.service.EditorService;

import java.sql.*;


public class EditorDBDao {

    public static void main(String[] args) {
        EditorDBDao base = new EditorDBDao();

        base.establecerConexion();
        base.hacerConsulta("MAPA1");
        base.insertarSprite("MAPA4", 1, 1, "prueba");
        base.hacerConsulta("MAPA4");
    }

    private String jdbcURL = "jdbc:mysql://localhost:3306/prom_junio";
    private String user = "root";
    private String pass = "root";
    private Connection conexion;

    public void establecerConexion() {

        //String sql = "SELECT * FROM personas WHERE nombre LIKE ? ";

        //PreparedStatement pst = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conexion = DriverManager.getConnection(jdbcURL, user, pass);
            System.out.println("Conexión establecida");
            //conexion.close();

        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void hacerConsulta(String nombre){

        String sql = "SELECT * FROM editor_mapa WHERE nombre LIKE ? ";

        try {
            PreparedStatement pst = conexion.prepareStatement(sql);
            pst.setString(1, nombre);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                int fila = rs.getInt("fila");
                int columna = rs.getInt("columna");
                String imagenes = rs.getString("imagenes");
                System.out.println(fila + "\t" + nombre + "\t" + columna + "\t" + imagenes);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


    }
    public void insertarSprite(String nombre, int fila, int columna, String imagenes){
        String sql = "INSERT INTO editor_mapa (nombre, fila, columna, imagenes) VALUES (?,?,?,?)";
        try {
            PreparedStatement pst = conexion.prepareStatement(sql);
            pst.setString(1, nombre);
            pst.setInt(2, fila);
            pst.setInt(3, columna);
            pst.setString(4, imagenes);
            pst.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void actualizarSprite(String nombre, int fila, int columna, String imagenes){
        String sql = "UPDATE editor_mapa SET fila=?, columna=?, imagenes=? WHERE nombre=?";
        try {
            PreparedStatement pst = conexion.prepareStatement(sql);
            pst.setInt(1, fila);
            pst.setInt(2, columna);
            pst.setString(3, imagenes);
            pst.setString(4, nombre);
            pst.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}

