package es.masanz.utrep.editormapa.v1;

import io.javalin.Javalin;
import io.javalin.http.Context;
import io.javalin.http.Handler;
import io.javalin.rendering.template.JavalinFreemarker;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class Main {


    public static void main(String[] args) {

        Javalin app = Javalin.create(config -> {
            // Para cuando queramos servir archivos estáticos (CSS, JS, imágenes)
            config.staticFiles.add("/public");
            config.fileRenderer(new JavalinFreemarker());
        }).start(4567);

        app.get("/", Main::paginaPrincipal);
        app.post("/getSprite", Main::obtenerSprite);

    }

    private static void paginaPrincipal(Context context) {

        Map<String, Object> model = new HashMap<>();


        String[] imagenes = cargarSprites();


        //En el ftl se lee del reves, el valor de la izuquierda son las filas y el de la derecha las columnas
        String[][] mapa = generarMapa(imagenes);


        model.put("mapa", mapa);
        model.put("imagenes", imagenes);
        context.render("templates/index.ftl", model);

    }


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

    private static String obtenerSprite(Context context){
        int contCom=0;
        String[] cadenaTexto = new String[3];
        cadenaTexto[0]=context.formParam("fila");
        cadenaTexto[1]= context.formParam("columna");
        cadenaTexto[2]= context.formParam("sprite");
        String spriteSelect="";

        for (String s : cadenaTexto) {
            if (contCom==0){
                spriteSelect=spriteSelect + s;
                contCom++;
            }
            else {
                spriteSelect=spriteSelect+","+s;
            }
        }
        System.out.println(spriteSelect);
        return spriteSelect;
    }
}





