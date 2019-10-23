%Para hacer la consulta debe ser del formato aceptacion(Lista de caratceres, nodo inicial, variable donde se retornara el nodo final)
%alfabeto permitido
sym(x).
sym(y).
sym(z).

%Estado 0:
transicion(0, 1, x).
transicion(0, 3, y).
transicion(0, 6, z).
%Estado 1:
transicion(1, 2, x).
%Estado 2:
transicion(2, 3, x).
transicion(2, 0, y).
%Estado 3:
transicion(3, 4, x).
%Estado 4:
transicion(4, 5, z).
%Estado 5:
transicion(5, 7, z).
transicion(5, 4, y).
%Estado 6:
transicion(6, 7, x).
%Estado 7:
transicion(7, 8, y).
%Estado 8:
transicion(8, 2, y).
transicion(8, 9, z).
%Estado 9:
transicion(9, 5, x).


aceptacion([], EA, EA).

aceptacion([X|Y], EA, Z) :- sym(X), transicion(EA, P, X), aceptacion(Y, P, Z).
aceptacion(_, EA, EA).
