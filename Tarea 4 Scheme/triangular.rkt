#lang scheme

#|
Funcion: triangular_simple
Descripcion: Determina si un numero es triangular o no con recursion simple
Parametros:
numero Entero
Retorno: #t si es triangular, #f si no lo es
|#

(define triangular_simple
  (lambda (numero)
    (let t_simple ((cont 1))
      (cond ((= numero (/ (* cont (+ cont 1)) 2)) #t)
            ((< numero (/ (* cont (+ cont 1)) 2)) #f)
            (else (or (t_simple(+ cont 1)) (t_simple(+ cont 2))))
      )
    )
  )
)

#|
Funcion: triangular_cola
Descripcion: Determina si un numero es triangular o no con recursion de cola
Parametros:
numero Entero
Retorno: #t si es triangular, #f si no lo es
|#

(define triangular_cola
  (lambda (numero)
    (let t_cola ((cont 1))
      (cond ((= numero (/ (* cont (+ cont 1)) 2)) #t)
            ((< numero (/ (* cont (+ cont 1)) 2)) #f)
            (else (t_cola(+ cont 1)))
      )
    )
  )
)