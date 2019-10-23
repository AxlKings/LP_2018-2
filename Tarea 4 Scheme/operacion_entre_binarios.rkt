#lang scheme

#|
Funcion: operar_binarios
Descripcion: Aplica una funcion a cada par de elementos de la lista, es decir, se realiza la operacion en orden, tomando un elemento de cada lista y operandolo, luego este resultado es agregado a una lista
Parametros:
funcion, Funcion que se utilizara para las listas de numeros binarios
lista1, Lista con numeros en binario
lista2, Lista con numeros en binario
Retorno: Retorna una lista de numeros binarios previamente operados por funcion
|#

(define ((operar funcion) lista1 lista2)
  (let operacion ((l1 lista1) (l2 lista2) (largo (length lista1)) (cont 0) (l3 '()))
    (if (null? lista1) '()
      (if (= cont largo) l3
          (operacion (cdr l1) (cdr l2) largo (+ cont 1) (append l3 (list (funcion (car l1) (car l2)))))
      )
    )
  )  
)