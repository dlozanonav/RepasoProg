<!DOCTYPE html>
<html lang="es">
    <head>
        <meta charset='utf-8'>
        <meta http-equiv='X-UA-Compatible' content='IE=edge'>
        <title>pussytime</title>
        <meta name='viewport' content='width=device-width, initial-scale=1'>
        <link rel='stylesheet' type='text/css' media='screen' href='style.css'>
    </head>
        <body>
            <h1>Titulo web</h1>
            <h2>Este es el h2</h2>
            <div class="array-imagenes">
                <#list imagenes as item>
                    ${item}
                </#list>
            </div>
        <div class="botones">
            <button>Cargar</button>
            <button>Guardar</button>
        </div>
        <div class="cuadricula">
            <#list mapa as fila>
              <#list fila as celda>
                  <div class="celda">
                        <#if celda??>
                           ${celda}
                    <#else>
                     null
                    </#if>
                </div>
         </#list>
        </#list>
        </div>
        </body>
</html>