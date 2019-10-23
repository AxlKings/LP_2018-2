#lang scheme

#|
Funcion: suma_arbol
Descripcion: Busca un nodo dentro de un arbol binario y retorna la suma de los nodos por los que paso incluido el nodo a buscar
Parametros:
arbol, lista de la forma (nodo_raiz (arbol_izquierdo) (arbol_derecho))
numero, entero, nodo a encontrar
Retorno: Suma de los nodos por los que pasa más el nodo a buscar, null si el arbol no posee elementos o si el nodo a buscar no se encuentra
|#

(define suma_arbol
  (lambda(arbol numero)
    (let sum ((tree arbol) (total 0))
      (cond ((null? tree) null)
          ((< numero (car tree)) (sum (car (cdr tree)) (+ total (car tree))))
          ((> numero (car tree)) (sum (car (cdr (cdr tree))) (+ total (car tree))))
          (else (+ total (car tree)))
      )
    )
  )
)