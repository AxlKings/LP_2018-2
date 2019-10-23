#lang scheme

#|
Funcion: biseccion
Descripcion: Determina la raiz de una funcion dado un intervalo de busqueda, se verifica si el intervalo es valido y que se ejecute el codigo mientras haya iterador
Parametros:
funcion Funcion
intervalo, Lista que contiene el intervalo, pueden ser enteros o flotantes (?
iter, Entero, indica cuantas iteraciones se deben realizar para encontrar la raiz
Retorno: Valor que hace que la funcion se haga cero o el mas cercano a la raiz si el iterador llega a 0, si el intervalo es invalido se retorna null
|#

(define ((biseccion funcion) intervalo iter)
    (let bis ((inter intervalo) (contador iter) (raiz 0))
      (if (>= (car inter) (car(cdr inter))) null
          (if (= contador 0) raiz
              (cond ((> (* (funcion (car inter)) (funcion (car(cdr inter)))) 0) null)
                    ((= (* (funcion (car inter)) (funcion (car(cdr inter)))) 0)
                     (if (= (funcion (car inter)) 0) (car inter)
                         (car(cdr inter))
                     )
                    )
                    (else
                     (cond ((= (funcion(/ (+ (car(cdr inter)) (car inter))  2)) 0) (/ (+ (car(cdr inter)) (car inter))  2))
                           ((< (* (funcion(/ (+ (car(cdr inter)) (car inter))  2)) (funcion (car inter))) 0)
                            (bis (list (car inter) (/ (+ (car(cdr inter)) (car inter))  2)) (- contador 1) (/ (+ (car(cdr inter)) (car inter))  2)))
                           (else
                            (bis (list (/ (+ (car(cdr inter)) (car inter))  2) (car (cdr inter))) (- contador 1) (/ (+ (car(cdr inter)) (car inter))  2)))
                     )
                    )
              )
            )
      )
    )
)
((biseccion (lambda(x) (- x 2))) '(-10 100) 3)
