#lang scheme

#|
Funcion: cruzar
Descripcion: Realiza el cruce de los elementos de dos listas, tomando el primer trozo de tamaño n de cada una
Parametros:
lista1, lista de elementos
lista2, lista de elementos
n, entero que indica hasta que elemento se realizará cruce
Retorno: Una lista que contiene las dos nuevas listas cruzadas
|#

(define cruzar
  (lambda (lista1 lista2 n)
    (let cruce ((l1 lista1) (l2 lista2) (l1r '()) (l2r '()) (cont 0))
      (if (and (null? l1) (null? l2)) (list l1r l2r)
          (if (and (null? (cdr l1)) (null? (cdr l2)))
              (if (or (car l1) (car l2)) (cruce (cdr l1) (cdr l2) (append l1r (list (car l1))) (append l2r (list (car l2))) (+ cont 1))
                  (list l1r l2r)
              )
              (cond ((< cont n) (cruce (cdr l1) (cdr l2) (append l1r (list (car l2))) (append l2r (list (car l1))) (+ cont 1)))
                (else (cruce (cdr l1) (cdr l2) (append l1r (list (car l1))) (append l2r (list (car l2))) (+ cont 1)))
              )
          )
      )
    )
  )
)