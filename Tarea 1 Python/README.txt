Ethiel Carmona Contalba, 201773533-3
Axel Reyes Orellana, 201773502-3

En primer lugar, el archivo que recibe el programa para la lectura del código de LOLCODE debe ingresarse de la forma "archivo.txt", es decir, no sólo ingresar el nombre "archivo",
sino que incluir el ".txt", cabe destacar que el nombre da igual.
Por otra parte, se aconseja probar varios casos de prueba, pues al especificar solo uno se puede asumir que un error será un error siempre, pudiendo este trabajar de buena forma en otros casos.

Dada la estructura de LOLCODE entregada, se realizaron los siguientes SUPUESTOS:
->Los puntos suspensivos indican un etc dentro de los ejemplos, así nos ahorramos especificar una expresion completamente, obviando lo que viene a continuación
- Para la *asignacion de variables* (<nombre_variable> R <expresion>), se consideró correcta cuando esta está al comienzo de una linea y no dentro de una estructura, dada su sintaxis. Por ejemplo:
    aux R SUM OF... CORRECTA
    SUM OF SUM OF aux R 1 AN... INCORRECTA

- Para la entrada la orientación es la misma que para la asignacion de variables. Por ejemplo:
    GIMMEH <nombre_variable> CORRECTO
    SUM OF GIMMEH aux AN 1 INCORRECTO

- Por otra parte, se pide no colocar ciclos con el mismo nombre, pues el programa realiza mal las operaciones en algunos casos
(es decir, hay ocasiones donde el colocar dos o mas ciclos iguales los trabajara bien, pero otras veces no, dependiendo de como este escrito el codigo LOLCODE),
esto debido a que no se puede saber con exactitud cual es el ciclo que efectivamente sí se cierra y cual no. Por ejemplo:
    IM IN YR ciclo UPPIN YR cont
    IM IN YR ciclo UPPIN YR cont
    IM IN YR ciclo2 UPPIN YR cont
    IM OUTTA YR ciclo2
    IM OUTTA YR ciclo
