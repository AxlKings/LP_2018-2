#lang scheme

#|
Funcion: calculo
Descripcion: Calcula el costo de una expresion, la cual se hace asosiando a cada elemento un valor (costo) que se ira sumando para obtener un total
Parametros:
costs, Lista de la forma (simbolo, costo)
expr, Lista que contiene una expresion a evaluar
Retorno: Retorna el total de costo que posee una expresion
|#

(define (calculo costs expr)
  (let calc ((exp expr) (suma 0))
    (if (null? exp) suma
        (if (not (assq (car exp) costs))
            (calc (cdr exp) suma)
        (calc (cdr exp) (+ suma (cdr (assq (car exp) costs))))
        )
    )
  )
)