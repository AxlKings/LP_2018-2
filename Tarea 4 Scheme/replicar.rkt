#lang scheme

#|
Funcion: replicar
Descripcion: Replica cada elemento de lista1 tantas veces como el elemento de lista2 indique (A un elemento de lista1 le corresponde solo un elemento de lista2, en orden)
Parametros:
lista, lista con los elementos a replicar
lista_replica, lista con la cantidad de veces que se replicara cada elemento
Retorno: Retorna una lista con los elementos replicados en el orden correspondiente
|#

(define replicar
  (lambda (lista lista_replica)
    (let rep ((l lista) (l_r lista_replica) (retorno '()) (num (car lista_replica)))
      (cond ((null? l) retorno)
          ((> num 0) (rep l l_r (append retorno (list (car l))) (- num 1)))
          (else (if (null? (cdr l_r)) retorno
                    (rep (cdr l) (cdr l_r) retorno (car (cdr l_r)))
                )
          )
      )
    )
  )
)