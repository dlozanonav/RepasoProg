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
        <h1>Titulo web</h1>
        <h2>Este es el h2</h2>
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
                            <div class="mapa">
                                <img onclick="cambiarFondoMapa(this); enviarDatos(${fila?index}, ${celda?index}, '${celda}')" src="${celda}">
                            </div>
                        <#else>
                            null
                        </#if>
                    </div>
                </#list>
            </#list>

        </div>

        <form action="/getSprite" method="post">
            <input type="hidden" name="sprite">
            <input type="hidden" name="columna">
            <input type="hidden" name="fila">
            <button type="submit" style="display: none">Enviar</button>
        </form>
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