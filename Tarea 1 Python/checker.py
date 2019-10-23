import re

#Funcion: rojo
#Input:
#   linea: Recibe una linea de string que no cumple con las expresiones regulares, la cual es una linea completa del codigo de LOLCODE leído
#Resumen:
#   Esta funcion se encarga de pintar en rojo la linea completa, para luego printearla
#Retorno:
#   No retorna nada, solo modifica

def rojo(linea):
    print('\033[41m'+linea.strip()+'\033[49m')

#Funcion: E_F
#Input:
#   linea: Recibe una linea completa del codigo de LOLCODE leído, cont: Recibe un contador que corresponde al numero de linea que se está leyedo.
#Resumen:
#   La funcion ve si la linea corresponde a un KTHXBYE o un HAI, en caso de que sea un HAI y la pila esté vacia, agrega el contador a la pila. En caso de que no esté vacia
# es porque ya hay un HAI guardado, lo saca y agrega el contador actual (Así se busca el HAI de mas adentro). Finalmente retorna True
#   En caso de que sea un KTHXBYE, si la pila tiene 1 elemento (un HAI), entonces simplemente agrega el contador a la pila y retorna False (para que la funcion no se vuelva a llamar),
# de esta manera se habra encontrado un match de HAI-KTHXBYE más interno, ignorando por completo lo que esté fuera de este rango.
#Retorno:
#   Retorna True si recibe un HAI, retorna False si recibe un KTHXBYE

def E_F(linea,cont):
    if patron.match(linea).group(0) == "KTHXBYE":
        if len(pilaI_F) == 1:
            pilaI_F.append(cont)
            return False
    elif patron.match(linea).group(0) == "HAI":
        if len(pilaI_F) == 0:
            pilaI_F.append(cont)
        else:
            pilaI_F.pop()
            pilaI_F.append(cont)
    return True

#Funcion: ciclos
#Input:
#   lista: Lista donde está cada linea del archivo, una en cada posicion de la lista
#   patron1: Patron para match cuando se crea un ciclo (IN)
#   patron2: Patron para match cuando se cierra un ciclo ()
#   pilaCR: Lista que almacenará los nombres de los ciclos que estan correctos
#Resumen:
#   Esta funcion se encarga de revisar que ciclos dentro del codigo estan correctos y los añade a la lista pilaCR
#Retorno:
#   No retorna nada, solo modifica

def ciclos(lista, patron1, patron2, pilaCR):
    pilaI = []
    pilaO = []
    pilaAUX = []
    for linea in lista:
        if patron1.match(linea):
            pilaI.append(patron1.match(linea).group(2))
        if patron2.match(linea):
            pilaO.append(patron2.match(linea).group(2))
    for linea in pilaI:
        if linea not in pilaO:
            pilaI.remove(linea)
    for linea in pilaO:
        if linea not in pilaI:
            pilaO.remove(linea)
    for linea in lista:
        if patron1.match(linea) and patron1.match(linea).group(2) in pilaI:
            pilaAUX.append(patron1.match(linea).group(2))
        if patron2.match(linea) and patron2.match(linea).group(2) in pilaO:
            if patron2.match(linea).group(2) != pilaAUX[-1]:
                del pilaAUX[-1]
            else:
                pilaCR.append(patron2.match(linea).group(2))
                del pilaAUX[-1]

#Funcion: Agregar
#Input:
#   lista: Recibe una lista la cual tendrá a lo más 4 tuplas (una para cada condicional), las cuales están estructuradas de la siguiente manera:
#
#          ORLY                 YARLY               NOWAI               OIC
#           |                     |                   |                  |
#           V                     V                   V                  V
#   [(string,posicion),(string,posicion,[]),(string,posicion,[]),(string,posicion)]
#
# Donde string será el identificador de si es un "ORLY", "YARLY", "NOWAI" o un "OIC", posicion será el numero de la linea en que se encuentra (en el texto transformado en lista)
# Notese que YARLY y NOWAI tienen dentro de sus tuplas un campo para una lista. Esta lista tendra la misma estructura que la lista que estamos analizando ahora,
# de manera que así permite el anidamiento de mas condicionales (los cuales solo se pueden hacer luego de un YARLY o un NOWAI debido a la estructura dada).
#
#   string: Recibe un string el cual sirve de identificador de condicional.
#   posicion: Recibe un entero que indica la posicion donde se encuentra la linea en el texto transformado en lista.
#Resumen:
#   Esta funcion se encarga de ir colocando correctamente los condicionales que están estructuradamente correctos, de manera que se identifiquen los condicionales desordenados.
#Retorno:
#   La funcion tiene dos tipos retornos:
#
# "ERROR" si existe un error en el orden en que se quiere poner el condicional. Este retorno juega dos papeles, uno es para que simplemente la funcion retorne "ERROR" y se
# agregue como error el condicional, y otro es para cuando un condicional que puede colocarse en la lista más exterior igualmente revisa si puede meterse a una lista más interior
# (anidamiento), en caso de que no pueda lograr esto último, recursivamente se retornará "ERROR" hasta que llegue a la lista exterior donde sí podía ubicarse y finalmente realizarlo.
#
# "LISTO": si no existe un error al ubicar un condicional, retornara "LISTO"

def Agregar(lista, string, posicion):
    if string != "ORLY" and len(lista) == 0:
        return "ERROR"

    if string == "ORLY":
        if len(lista) == 0:
            lista.append((string, posicion))
            return "LISTO"
        elif len(lista) == 1:
            return "ERROR"
        elif len(lista) == 2:
            return Agregar(lista[1][2], string, posicion)
        elif len(lista) == 3:
            return Agregar(lista[2][2], string, posicion)
        else:
            return "ERROR"

    elif string == "YARLY":
        if len(lista) == 1:
            lista.append((string, posicion, []))
            return "LISTO"
        elif len(lista) == 2:
            return Agregar(lista[1][2], string, posicion)
        elif len(lista) == 3:
            return Agregar(lista[2][2], string, posicion)
        else:
            return "ERROR"

    elif string == "NOWAI":
        if len(lista) == 1:
            return "ERROR"
        elif len(lista) == 2:
            comando = Agregar(lista[1][2], string, posicion)
            if comando == "ERROR":
                lista.append((string, posicion, []))
            return "LISTO"
        elif len(lista) == 3:
            return Agregar(lista[2][2], string, posicion)
        else:
            return "ERROR"

    elif string == "OIC":
        if len(lista) == 1:
            return "ERROR"
        elif len(lista) == 2:
            return Agregar(lista[1][2], string, posicion)
        elif len(lista) == 3:
            comando = Agregar(lista[2][2], string, posicion)
            if comando == "ERROR":
                lista.append((string, posicion, []))
            return "LISTO"
        else:
            return "ERROR"

#Funcion: recorrer
#Input:
#   lista: Recibe una lista (del tipo de lista que se utilizo en la funcion anterior) la cuál va a recorrer de manera que llegue hasta la lista de más adentro.
#Resumen:
#   Esta funcion se va metiendo a las listas de los anidamientos recursivamente hasta llegar a las más interiores, donde desde ahí va revisando que el tamaño de
# las listas deban ser 4, en caso contrario se deben agregar los restos de elementos a una lista donde guardamos los errores y elimina los condicionales que fueron guardados
# en una lista de estos, de manera que solo queden los que podrian estar buenos.
#Retorno:
#   No retorna nada, solo modifica

def recorrer(lista):
    if len(lista) == 4:
        recorrer(lista[1][2])
        recorrer(lista[2][2])
    elif len(lista) == 3:
        recorrer(lista[1][2])
        recorrer(lista[2][2])
        RLY_errores.append(lista[0][1])
        RLY_errores.append(lista[1][1])
        RLY_errores.append(lista[2][1])
        condicionales.remove((lista[0][0], lista[0][1]))
        condicionales.remove((lista[1][0], lista[1][1]))
        condicionales.remove((lista[2][0], lista[2][1]))
    elif len(lista) == 2:
        recorrer(lista[1][2])
        RLY_errores.append(lista[0][1])
        RLY_errores.append(lista[1][1])
        condicionales.remove((lista[0][0],lista[0][1]))
        condicionales.remove((lista[1][0],lista[1][1]))
    elif len(lista) == 1:
        RLY_errores.append(lista[0][1])
        condicionales.remove((lista[0][0],lista[0][1]))

#Funcion: ORLY
#Input:
#   string: Identificador para condicionales.
#   posicion: Posicion del condicionale en el texto transformado en lista.
#Resumen:
#   Esta funcion se encarga de revisar que se cumpla la sintaxis de que el ORLY deba tener una expresion antes, el YARLY no debe tener una antes pero sí una después,
# el NOWAI debe tener expresiones antes, y el OIC también debe tener una expresion antes.
#Retorno:
#   No retorna nada, solo modifica.

def ORLY(string,posicion):

    if string == "ORLY":
        if not patron_.match(lista[posicion-1]):
            RLY_errores.append(posicion)
        pilaO_RLY.append(posicion)

    elif string == "YARLY":
        diferencia = posicion - pilaO_RLY[-1]
        if diferencia != 1:
            if diferencia > 2:
                for i in range(pilaO_RLY[-1]+1,posicion):
                    RLY_errores.append(i)
            elif diferencia == 2:
                RLY_errores.append(posicion-1)
        pilaYA_RLY.append(posicion)

    elif string == "NOWAI":
        diferencia = posicion - pilaYA_RLY[-1]
        if diferencia == 1:
            RLY_errores.append(posicion-1)
        pilaNO_WAI.append(posicion)

    elif string == "OIC":
        if len(pilaNO_WAI) != 0:
            diferencia = posicion - pilaNO_WAI[-1]
            if diferencia == 1:
                RLY_errores.append(posicion-1)

#Funcion: pintar
#Input:
#   linea: Recibe una linea de string que corresponde a una linea de LOLCODE
#Resumen:
#   Esta funcion reemplaza las palabras clave que se necesitan pintar, para luego printear la linea con los colores ya cambiados
#Retorno:
#   No retorna nada, solo modifica

def pintar(linea):
    linea1 = linea
    linea1 = re.sub(' AN ', '\033[34m AN \033[0m', linea1)
    linea1 = re.sub(' R ', '\033[91m R \033[0m', linea1)
    linea1 = re.sub('NOT ', '\033[34mNOT \033[0m', linea1)
    linea1 = re.sub('SUM\s+OF', '\033[34mSUM OF\033[0m', linea1)
    linea1 = re.sub('DIFF\s+OF', '\033[34mDIFF OF\033[0m', linea1)
    linea1 = re.sub('PRODUKT\s+OF', '\033[34mPRODUKT OF\033[0m', linea1)
    linea1 = re.sub('QUOSHUNT\s+OF', '\033[34mQUOSHUNT OF\033[0m', linea1)
    linea1 = re.sub('MOD\s+OF', '\033[34mMOD OF\033[0m', linea1)
    linea1 = re.sub('BIGGR\s+OF', '\033[34mBIGGR OF\033[0m', linea1)
    linea1 = re.sub('SMALLR\s+OF', '\033[34mSMALLR OF\033[0m', linea1)
    linea1 = re.sub('BOTH\s+OF', '\033[34mBOTH OF\033[0m', linea1)
    linea1 = re.sub('EITHER\s+OF', '\033[34mEITHER OF\033[0m', linea1)
    linea1 = re.sub('BOTH\s+SAEM', '\033[34mBOTH SAEM\033[0m', linea1)
    linea1 = re.sub('DIFFRINT', '\033[34mDIFFRINT\033[0m', linea1)
    linea1 = re.sub('I\s+HAS\s+A\s', '\033[33mI HAS A \033[0m', linea1)
    linea1 = re.sub('ITZ ', '\033[33mITZ \033[0m', linea1)
    linea1 = re.sub('HAI', '\033[32mHAI\033[0m', linea1)
    linea1 = re.sub('KTHXBYE', '\033[32mKTHXBYE\033[0m', linea1)
    linea1 = re.sub('O\s+RLY\?', '\033[36mO RLY?\033[0m', linea1)
    linea1 = re.sub('YA\s+RLY', '\033[36mYA RLY\033[0m', linea1)
    linea1 = re.sub('NO\s+WAI', '\033[36mNO WAI\033[0m', linea1)
    linea1 = re.sub('OIC', '\033[36mOIC\033[0m', linea1)
    linea1 = re.sub('GIMMEH', '\033[91mGIMMEH\033[0m', linea1)
    linea1 = re.sub('VISIBLE', '\033[91mVISIBLE\033[0m', linea1)
    linea1 = re.sub('TIL', '\033[35mTIL\033[0m', linea1)
    linea1 = re.sub('WILE', '\033[35mWILE\033[0m', linea1)
    linea1 = re.sub('IM\s+IN\s+YR', '\033[35mIM IN YR\033[0m', linea1)
    linea1 = re.sub('IM\s+OUTTA\s+YR', '\033[35mIM OUTTA YR\033[0m', linea1)
    linea1 = re.sub('NERFIN\s+YR', '\033[35mNERFIN YR\033[0m', linea1)
    linea1 = re.sub('UPPIN\s+YR', '\033[35mUPPIN YR\033[0m', linea1)
    print(linea1.strip())

#CREACION DE EXPRESIONES REGULARES A UTILIZAR, ALGUNAS FUERON CREADAS PARA COMPOSICIÓN DE OTRAS MÁS GENERALES

operador = r'\s*(SUM\s+OF\s+|DIFF\s+OF\s+|PRODUKT\s+OF\s+|QUOSHUNT\s+OF\s+|MOD\sOF\s+|BIGGR\s+OF\s+|SMALLR\s+OF\s+|BOTH\s+OF\s+|EITHER\s+OF\s+|BOTH\s+SAEM\s+|DIFFRINT\s+)'
expression = r'('+operador+')+|[a-zA-Z]\w*|([0-9])+(\.[0-9]+)?|\".+\"|\'.+\''
estructura1 = r'('+operador+')+(('+expression+')((\s+AN\s+)('+expression+')+))+|\s+NOT\s+(('+operador+')+(('+expression+')(\s+AN\s+)('+expression+'))+)+|[a-zA-Z]\w*|([0-9])+(\.[0-9]+)?|\".+\"|\'.+\''
estructura2 = r'('+operador+')+(('+estructura1+')+((\s+AN\s+)('+estructura1+'))+)+|\s+NOT\s+('+operador+')+(('+estructura1+')((\s+AN\s+)('+estructura1+'))+)+|\s*NOT\s+('+estructura1+')+|('+estructura1+')+|[a-zA-Z]\w*|([0-9])+(\.[0-9]+)?|\".+\"|\'.+\''
estructura_ = r'(('+expression+')*(\s*I\s+HAS\s+A\s+)([a-zA-Z]\w*)\s+(ITZ\s+('+estructura2+')+))|(ITZ\s+('+estructura2+'))|('+estructura2+')+|[a-zA-Z]\w*'
estructura3 = r'(('+expression+')*(\s*I\s+HAS\s+A\s+)([a-zA-Z]\w*)\s+(ITZ\s+(('+estructura_+')+|('+estructura2+')+)+)+)(\s+AN\s+('+estructura_+'))*|((\s*I\s+HAS\s+A\s+)([a-zA-Z]\w*))|(ITZ\s+('+estructura_+'))'
estructuraCicloI = r'(\s*IM\s+IN\s+YR)\s+([a-zA-Z]\w*)\s+(UPPIN|NERFIN)\s+YR\s+([a-zA-Z]\w*)(\s+(TIL|WILE)\s+('+estructura1+'))*'
estructuraCicloO = r'(\s*IM\s+OUTTA\s+YR)\s+([a-zA-Z]\w*)'
estructuraCondiciones = r'\s*(O\s+RLY\?|YA\s+RLY|NO\s+WAI|OIC)'
estructura = r'HAI|KTHXBYE'
estructura4 = r'[a-zA-Z]\w*\s+R\s+('+estructura_+')+'
estructuraVisible = r'\s*VISIBLE\s+('+estructura_+')+'
estructuraGIMMMEH = r'\s*GIMMEH\s+[a-zA-Z]\w*'
estructura_ver = r'(\s*IM\s+IN\s+YR)'
estructura_ORLY = r'(O\s+RLY\?)'
estructura_YARLY = r'(YA\s+RLY)'
estructura_NOWAI = r'(NO\s+WAI)'

#COMPILACION DE EXPRESIONES REGULARES UTILIZADAS

patron = re.compile(estructura)
patron0 = re.compile(estructuraCondiciones)
patron1 = re.compile(estructuraVisible)
patron2 = re.compile(estructura3)
patron3 = re.compile(estructuraCicloI)
patron3b = re.compile(estructuraCicloO)
patron4 = re.compile(estructura4)
patron5 = re.compile(estructura2)
patron6 = re.compile(estructuraGIMMMEH)
patron_ver = re.compile(estructura_ver)
patron_ORLY = re.compile(estructura_ORLY)
patron_YARLY = re.compile(estructura_YARLY)
patron_NOWAI = re.compile(estructura_NOWAI)
patron_ = re.compile(estructura_)

#Contadores y listas a utilizar a lo largo del programa

HAI = 0
KTHX = 0
pilaI_F = []
C_errores = []
pilaCiclos = []
pilaO_RLY = []
pilaYA_RLY = []
pilaNO_WAI = []
RLY_errores = []
condicionales = []

#Comienzo del programa como tal
archivo = open(input("Ingrese nombre del archivo: "),'r')
contador = 0
lista = []
#Se pasa el texto a una lista para una mayor facilidad de acceso.
for linea in archivo:
    lista.append(linea)
archivo.close()
#Se llama la funcion ciclos.
ciclos(lista, patron3, patron3b, pilaCiclos)

flag = True

#Se recorre la lista (texto), de manera que si hace match con HAI o KTHXBYE, llamará a
# E_F, en cambio si hace match con un condicional, lo agregará a la lista de condicionales con
# el string respectivo y la posicion en el archivo.
for linea in lista:
    if patron.match(linea):
        if patron.match(linea).group(0) == linea.strip():
            if flag:
                flag = E_F(linea,contador)
    elif patron0.match(linea):
        if patron0.match(linea).group(0) == linea.strip():
            if patron_ORLY.search(linea):
                condicionales.append(("ORLY", contador))
            elif patron_YARLY.search(linea):
                condicionales.append(("YARLY", contador))
            elif patron_NOWAI.search(linea):
                condicionales.append(("NOWAI", contador))
            else:
                condicionales.append(("OIC", contador))
    contador += 1
# Se crea una lista para agregar los condicionales que seran removidos de la lista de condicionales.
# Se crea una lista de listas (orden), de manera que las listas tendran la estructura de 4 tuplas
# mencionada anteriormente, se crea una lista de listas ya que luego de cerrar un condicional
# se puede abrir otro, dando sentido así a tener una segunda lista y así sucesivamente.
condicionales_err = []
orden = []
orden.append([])
pos_orden = 0
if len(condicionales) != 0:
    for tupla in condicionales:
        # Si la lista en que se está trabajando está llena y se quiere agregar un condicional,
        # se agregará una lista vacia a la lista de listas (orden) y se llama a la funcion.
        if len(orden[pos_orden]) == 4:
            pos_orden += 1
            orden.append([])
            comando = Agregar(orden[pos_orden], tupla[0], tupla[1])
            if comando == "ERROR":
                RLY_errores.append(tupla[1])
                condicionales_err.append(tupla)
        # En caso contrario, simplemente se llama a la funcion, y en ambos casos si el retorno
        # resulta ser "ERROR", agregara a la lista de errores la posicion (para saber que linea pintar),
        # y agregara tupla del condicional a una lista de condicionales erroneos
        # (Se hizo asi ya que al intentar removerla directamente producia errores con el for).
        elif len(orden[pos_orden]) != 4:
            comando = Agregar(orden[pos_orden], tupla[0], tupla[1])
            if comando == "ERROR":
                RLY_errores.append(tupla[1])
                condicionales_err.append(tupla)
# Se remueven las tuplas de los condicionales errones de la lista de condicionales.
for tupla in condicionales_err:
    condicionales.remove(tupla)
# Se recorre la lista de listas para verificar los tamaños de las listas.
if len(orden) != 0:
    for i in range(0,len(orden)):
        recorrer(orden[i])
# Se recorre la lista de condicionales y se verifica cada condicional con la funcion ORLY
if len(condicionales) != 0:
    for tupla in condicionales:
        ORLY(tupla[0],tupla[1])

contador = 0

#A partir de este momento se ven los match realizados para cada
#linea según el patron con el que hizo match y se procede a la vez a pintar la linea

for linea in lista:
    # Se recorre el texto y linea por linea se van analizando los distintos casos.
    # Primero se verifica de que haya una pareja de HAI y KTHXBYE, en caso contrario
    # se pinta en rojo todo el archivo
    if len(pilaI_F) != 2:
        for linea3 in lista:
            rojo(linea3)
        break
    # Otro caso es que la linea esté fuera del rango de la pareja de HAI y KTHXBYE, lo cual
    # tambien se pinta rojo.
    elif contador < pilaI_F[0]:
        rojo(linea)
    elif contador > pilaI_F[1]:
        rojo(linea)
    # Luego se ve si la linea en que estamos corresponde a un condicional erroneo.
    elif contador in RLY_errores:
        rojo(linea)
    # Finalmente, se ve los casos en que se haga match con cualquier otra expresion regular
    # de manera de que si está correcta se pinte con los colores respectivos la linea (llama a la funcion pintar)
    # y en caso contrario se pinta en rojo
    elif patron.match(linea):
        if patron.match(linea).group(0) == linea.strip():
            pintar(linea)
        else:
            rojo(linea)
    elif patron0.match(linea):
        if patron0.match(linea).group(0) == linea.strip():
            pintar(linea)
        else:
            rojo(linea)
    elif patron1.match(linea):
        if patron1.match(linea).group(0) == linea.strip():
            pintar(linea)
        else:
            rojo(linea)
    elif patron2.match(linea):
        #En este match se busca que la cantidad de AN que haya sea igual a la cantidad de operadores (ej SUM OF) que hay,
        #esto para que el match entienda que si faltan la linea presenta error.
        if len(re.findall(operador, linea)) == len(re.findall(' AN ', linea)):
            pintar(linea)
        else:
            rojo(linea)
    elif patron3.match(linea):
        #En este match se analizaron los ciclos, es decir, que haya codigo despues de la apertura de un ciclo y que sea distinto al cierre de dicho ciclo.
        if patron3.match(linea).group(0) == linea.strip() and patron3.match(linea).group(2) in pilaCiclos:
            if patron3.match(linea).group(2)+" " not in lista[contador+1]:
                pintar(linea)
            else:
                rojo(linea)
        else:
            rojo(linea)
    elif patron3b.match(linea):
        #Para este caso es similar al anterior, solo se que busco que haya codigo antes de un cierre de ciclo y que sea distinto a la apertura del mismo.
        if patron3b.match(linea).group(0) == linea.strip() and patron3b.match(linea).group(2) in pilaCiclos:
            if patron3b.match(linea).group(2)+" " not in lista[contador-1]:
                pintar(linea)
            else:
                rojo(linea)
        else:
            rojo(linea)
    elif patron4.match(linea):
        if patron4.match(linea).group(0) == linea.strip():
            pintar(linea)
        else:
            rojo(linea)
    elif patron5.match(linea):
        if patron5.match(linea).group(0) == linea.strip():
            pintar(linea)
        else:
            rojo(linea)
    elif patron6.match(linea):
        if patron6.match(linea).group(0) == linea.strip():
            pintar(linea)
        else:
            rojo(linea)
    contador+=1
