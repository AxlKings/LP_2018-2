% Para la consulta hacer:
% alcance(nodo inicial, cantidad de combustible, Variable que muestra las ciudades).
coste(a,b,1).
coste(b,c,2).
coste(c,d,3).
coste(d,e,1).
coste(e,f,2).
coste(f,g,3).
coste(g,h,1).
coste(h,i,2).
coste(i,j,3).
coste(j,k,1).
coste(k,l,2).
coste(l,m,3).
coste(m,n,4).
coste(n,o,1).
coste(o,a,6).

adyacencia(a,b).
adyacencia(b,c).
adyacencia(c,d).
adyacencia(d,e).
adyacencia(e,f).
adyacencia(f,g).
adyacencia(g,h).
adyacencia(h,i).
adyacencia(i,j).
adyacencia(j,k).
adyacencia(k,l).
adyacencia(l,m).
adyacencia(m,n).
adyacencia(n,o).
adyacencia(o,a).

alcance(X,C,_) :- adyacencia(X, Y), coste(X, Y, P), C-P =< 0, !, fail.
alcance(X,C,Z) :- adyacencia(X,Y), coste(X,Y,P), C-P>=0, adyacencia(X,Z).
alcance(X,C,Z) :- adyacencia(X,Y), coste(X,Y,P), K is C-P, alcance(Y,K,Z).
