<!DOCTYPE html>
<html lang="es">
    <head>
        <meta charset='utf-8'>
        <meta http-equiv='X-UA-Compatible' content='IE=edge'>
        <title>Editor de Mapa</title>
        <meta name='viewport' content='width=device-width, initial-scale=1'>
        <link rel='stylesheet' type='text/css' media='screen' href='style.css'>
    </head>
    <body>
        <h1>Creador de mapas</h1>
            <div class="selector-rutas center">
                <form action="/seleccionar-mapa" method="POST">
                    <select name="mapaEditable">
                        <option value="Ruta1">Ruta 1</option>
                        <option value="david">David</option>
                    </select>
                    <button type="submit">Cambiar</button>
                </form>
        </div>

            <div class="array-imagenes">
            <#list imagenes as item>
                <img onclick="javascript:spriteSeleccionado(this);" class="sprite" src="${item}" >
            </#list>
        </div>
        <div class="botones">
            <button>Cargar</button>
            <button>Guardar</button>
        </div>
    
        <div class="cuadricula">
            <#list mapa as fila>
                <#list fila as celda>
                    <div class="celda" data-fila="${fila?index}" data-columna="${celda?index}">
                        <#if celda??>
                            <div class="mapa">  <#-- Estaba enviando en vez del sprite global que dice la imagen que he seleccionado,
                             el sprite que corresponde al inicio a la celda, por eso al refrescar no cambiaba, en celda esta el sprite
                             que se usa al generar el mapa pero claro, para luego GUARDAR el sprite seleccionado, ese esta guardado en spriteglobal-->
                                <img onclick="cambiarFondoMapa(this); enviarDatos(${fila?index}, ${celda?index}, spriteGlobal)" src="${celda}">
                            </div>
                        <#else>
                            null
                        </#if>
                    </div>
                </#list>
            </#list>

        </div>
        <script>
            var spriteGlobal = "";

            function spriteSeleccionado(elemento) {
                var antiguoSeleccionado = document.querySelector(".selected");

                if (antiguoSeleccionado != undefined) {
                    antiguoSeleccionado.classList.remove("selected");
                }
                elemento.classList.add("selected");
                spriteGlobal = elemento.src;
            }

            function cambiarFondoMapa(celda) {
                if (spriteGlobal !== "") {
                    celda.src = spriteGlobal;
                }
            }

            function enviarDatos(fila,columna,sprite){
                fetch("/getSprite", {
                    method: "POST",
                    headers: {
                        "Content-Type": "application/x-www-form-urlencoded"
                    },
                    body: 'fila='+encodeURIComponent(fila)+'&columna='+encodeURIComponent(columna)+'&sprite='+encodeURIComponent(sprite)
                })
            }
        </script>
    </body>
</html>