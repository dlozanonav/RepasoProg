package es.masanz.utrep.editormapa.v1;

import io.javalin.Javalin;
import io.javalin.http.Context;
import io.javalin.http.Handler;
import io.javalin.rendering.template.JavalinFreemarker;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Main {
    public static void main(String[] args) {

        Javalin app = Javalin.create(config -> {
            // Para cuando queramos servir archivos estáticos (CSS, JS, imágenes)
            config.staticFiles.add("/public");
            config.fileRenderer(new JavalinFreemarker());
        }).start(4567);

        app.get("/",Main::paginaPrincipal);

    }

    private static void paginaPrincipal(Context context) {

        Map<String, Object> model = new HashMap<>();
        String[] imagenes = new String[3];
        imagenes[0] = "Oier";
        imagenes[1] = "Daniela";
        imagenes[2] = "Devid";
        //En el ftl se lee del reves, el valor de la izuquierda son las filas y el de la derecha las columnas
        String[][] mapa = new String[3][5];


        model.put("mapa",mapa);
        model.put("imagenes", imagenes);
        context.render("templates/index.ftl", model);

    }
}