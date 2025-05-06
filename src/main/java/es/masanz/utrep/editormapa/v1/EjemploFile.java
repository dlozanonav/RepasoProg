package es.masanz.utrep.editormapa.v1;

import java.io.File;

public class EjemploFile {
    public static void main(String[] args) {
        File file = new File("C:/Users/alumMA/Documents/EditorMapaV1/src/main/resources/public/pruebas");
        if (file.exists() && !file.isFile()){
            File[] archivos= file.listFiles();
            for (File archivo : archivos) {
                if (archivo.isFile() && archivo.getName().endsWith(".xml")) {
                    System.out.println(archivo.getAbsolutePath());
                }
            }
        }
    }
}
//Obtener archivos que cumplan con esos requisitos es un repaso.