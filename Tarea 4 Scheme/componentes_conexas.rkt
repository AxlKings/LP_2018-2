#lang scheme

#|
Funcion: unir
Descripcion: Crea un conjunto el cual es la union de dos listas
Parametros:
lista1 lista
lista2 lista
Retorno: Retorna la lista correspondiente a la union de las 2 listas (sin elementos repetidos)
|#

(define unir
  (lambda (lista1 lista2)
    (let union ((l2 lista2) (lista '()) (cont 0) (largo (length lista2)))
      (if (not (< cont largo)) (append lista1 lista) 
        (if (member (car l2) lista1) (union (cdr l2) lista (+ cont 1) largo)
          (union (cdr l2) (append lista (list (car l2))) (+ cont 1) largo)
        )
      )
    )
  )
)

#|
Funcion: restar
Descripcion: Crea una lista que tiene los elementos de lista1 que no están en lista2
Parametros:
lista1 lista
lista2 lista
Retorno: Retorna la lista correspondiente a la resta de las 2 listas
|#

(define restar
  (lambda (lista1 lista2)
    (let resta ((l1 lista1) (lista '()) (cont 0) (largo (length lista1)))
      (if (not (< cont largo)) lista 
        (if (member (car l1) lista2) (resta (cdr l1) lista (+ cont 1) largo)
          (resta (cdr l1) (append lista (list (car l1))) (+ cont 1) largo)
        )
      )
    )
  )
)

#|
Funcion: BFS
Descripcion: Recorre el grafo con busqueda a lo ancho desde un nodo de partida. El let tiene 4 elementos: el nodo actual, la lista de nodos visitados, la lista de nodos por recorrer
(queue), y la lista de nodos ya recorridos. Primero verifica que hayan nodos que falten por recorrer (queue).Luego revisa si quedan nodos por recorrer
aparte del nodo actual y también si el nodo actual tiene vecinos (en caso de que ambos sean vacios se acaba el BFS). Luego revisa si el nodo actual ya fue recorrido
(si es así, recorrerá el nodo que sigue en queue). Finalmente revisa si el nodo está visitado, en caso de que lo esté se recorrerá el nodo que sigue en queue y no se agregará el nodo actual
a la lista de visitados, en caso contrario se procederá a lo mismo pero agregando al nodo actual a la lista de visitados. Cada vez que se llama a recorrer, se agregan (con unir) los vecinos del nodo
actual a la lista de visitados, se agregan (con unir) también a queue para recorrerlo y luego a queue resultante se le restan los recorridos. También se agrega el nodo actual a la lista de recorridos.
Parametros:
grafo lista
nodo entero
Retorno: Retorna la lista de nodos visitados (sin repetirse)
|#

(define BFS
  (lambda (grafo nodo)
    (let recorrer ((vertice nodo) (visitados '()) (queue (list nodo)) (recorridos '()))
      (if (null? queue) visitados
        (if (and (null? (cdr queue)) (null? (cadr (assq vertice grafo)))) (append visitados (list vertice))
          (if (not (member vertice recorridos))
            (if (member vertice visitados)
                (recorrer (car (unir (cdr queue) (cadr (assq vertice grafo)))) (unir visitados (cadr (assq vertice grafo))) (restar (unir (cdr queue) (cadr (assq vertice grafo))) recorridos) (append recorridos (list vertice)))
              (recorrer (car (unir (cdr queue) (cadr (assq vertice grafo)))) (unir (append visitados (list vertice)) (cadr (assq vertice grafo))) (restar (unir (cdr queue) (cadr (assq vertice grafo))) recorridos) (append recorridos (list vertice)))
            )
          (recorrer (car (unir (cdr queue) (cadr (assq vertice grafo)))) (unir visitados (cadr (assq vertice grafo))) (restar (unir (cdr queue) (cadr (assq vertice grafo))) recorridos) (append recorridos (list vertice)))
          )
        )
      )
    )
  )
)

#|
Funcion: obtener_nodos
Descripcion: Recorre el grafo para obtener los nodos.
Parametros:
grafo lista
Retorno: Retorna una lista que solo tiene a los nodos (sin sus vecinos)
|#

(define obtener_nodos
  (lambda(grafo)
    (let obtener ((g grafo) (lista_nodos '()) (largo (length grafo)) (cont 0))
      (if (not (< cont largo)) lista_nodos
        (obtener (cdr g) (append lista_nodos (list (caar g))) largo (+ cont 1))
      )
    )
  )
)

#|
Funcion: componentes
Descripcion: Identifica cuántas componentes conexas tiene un grafo dado. Resta la lista de nodos con el retorno de BFS (nodos visitados), aumentando el contador de componentes,
hasta que la lista de nodos está vacía 
Parametros:
grafo lista
Retorno: Retorna un entero que indica las componentes conexas del grafo
|#

(define componentes
  (lambda (grafo)
    (let components ((lista_nodos (obtener_nodos grafo)) (comp 0))
      (if (null? grafo) comp
        (if (null? lista_nodos) comp
          (components (restar lista_nodos (BFS grafo (car lista_nodos))) (+ comp 1))
        )
      )
    )
  )
)

(componentes '((1 (2 3)) (2 (1 4)) (3 (1)) (4 (2)) (5
(6)) (6 (5)) (7 ())))
