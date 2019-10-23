%Para la consulta hacer:
%subconjunto(subconjunto a buscar, conjunto, entero).
%Ejemplo: subconjunto(W,[1,2,3], 3).
suma([], 0).
suma([E|L], S) :- suma(L,S1), S is S1+E.

subc([], []).
subc([E|L], [E|L1]) :- subc(L, L1).
subc([_|L], L1) :- subc(L, L1).

subconjunto([],[],_).
subconjunto(W, C, E) :- subc(C, W), suma(W, E).