%La consulta debe ser diccionario([]) y para escoger las opciones debe ingresar el numero de la opcion.
diccionario(D):-
    write('Ingrese una opcion: '), nl,
    write('0.- Fin.'), nl,
    write('1.- Agregar llave.'), nl,
    write('2.- Obtener valor.'), nl,
    write('3.- Obtener llaves.'), nl,
    write('4.- Obtener todos los valores.'), nl,
    write('5.- Obtener largo.'), nl,
    read(X),
    funcion(X,D).

funcion(0,_):- !.

funcion(1,D):- 
    write('Ingrese la llave y su valor en una lista: '), nl,
    read(K),
    append([K],D,D2), nl,
    diccionario(D2).

funcion(2,D):-
    write('Ingrese la llave de la cual desea saber su valor: '), nl,
    read(K), nl,
    buscarValor(K,D,V),
    write('El valor es: '), write(V), nl,
    diccionario(D).

funcion(3,D):-
    obtenerLLaves(D,[],L), nl,
    write('Las llaves son: '), write(L), nl,
    diccionario(D).
    

funcion(4,D):-
    obtenerValores(D,[],L), nl,
    write('Los valores son: '), write(L), nl,
    diccionario(D).
    
funcion(5,D):-
    obtenerLargo(D,0,N), nl,
    write('El largo del diccionario es de: '), write(N), nl,
    diccionario(D).

buscarValor(K,[X|_],V):-
    X = [K2|V2],
    K2 = K, 
    V = V2.

buscarValor(K,[_|D],V):-
    buscarValor(K,D,V).


obtenerLLaves([],L,L).
obtenerLLaves([X|D],L,L2):-
    X = [K|_],
    append([K],L,L3),
    obtenerLLaves(D,L3,L2).
    
obtenerValores([],L,L).
obtenerValores([X|D],L,L2):-
    X = [_|V],
    append([V],L,L3),
    obtenerValores(D,L3,L2).

obtenerLargo([],N,N).
obtenerLargo([_|D],N,N2):-
    N3 is N+1,
    obtenerLargo(D,N3,N2).
    

    
