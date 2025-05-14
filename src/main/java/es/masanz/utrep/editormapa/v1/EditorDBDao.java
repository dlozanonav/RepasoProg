package es.masanz.utrep.editormapa.v1;

import java.sql.*;


public class EditorDBDao {

    public static void main(String[] args) {
        EditorDBDao base = new EditorDBDao();

        base.establecerConexion();
        base.insertarTamaNo("MAPA1",3,5);
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

    public void insertarTamaNo(String nombre, int fila, int columna){
       String sql ="INSERT INTO mapas (nombre,columnas,filas) VALUES (?,?,?)";
        try{
            PreparedStatement pst = conexion.prepareStatement(sql);
            pst.setString(1,nombre);
            pst.setInt(2,columna);
            pst.setInt(3,fila);
            pst.executeUpdate();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public boolean actualizarSprite(String nombre, int fila, int columna, String imagenes){
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
        return false;
    }

    public void consultarMapa(String nombreMapa, String[][] mapa) {

    }
}

