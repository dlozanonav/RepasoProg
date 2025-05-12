package es.masanz.utrep.editormapa.v1;

import java.io.File;

public class MainController {

    private static String[][] generarMapa(String[] imagenes) {
        String[][] mapa= new  String[9][15];
        for (int i = 0; i < mapa.length; i++) {
            for (int j = 0; j < mapa[0].length; j++) {
                mapa[i][j] = imagenes[(int) (Math.random() * imagenes.length)];
            }
        }
        return mapa;
    }

    private static String[] cargarSprites() {
        int tamanoArray = 0;
        File file = new File("src/main/resources/public/pruebas");
        if (file.exists() && !file.isFile()) {
            File[] archivos = file.listFiles();
            for (File archivo : archivos) {
                if (archivo.isFile() && archivo.getName().endsWith(".png")) {
                    tamanoArray = tamanoArray + 1;
                }
            }
        }
        String[] imagenes = new String[tamanoArray];
        File[] direcroio = file.listFiles();
        for (File archivo : direcroio) {
            if (archivo.isFile() && archivo.getName().endsWith(".png")) {
                System.out.println(archivo.getName());
                imagenes[tamanoArray - 1] = "/pruebas/" + archivo.getName();
                if (tamanoArray > 1) {
                    tamanoArray--;
                }

            }
        }
        return imagenes;
    }
}
