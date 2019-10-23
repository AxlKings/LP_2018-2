#lang scheme

#|
Funcion: factorial
Descripcion: Calcula el factorial de un entero n
Parametros:
n entero
Retorno: Retorna el factorial de n
|#

(define factorial
  (lambda (n)
    (let fact ((i n) (a 1))
      (if (<= i 0) a
          (fact (- i 1) (* a i))
      )
    )
  )
)

#|
Funcion: rec_cos
Descripcion: Aproxima el valor de coseno (cos(x)) utilizando el teorema de Maclaurin dado un x y un numero que indicara cuantas sumas se realizaran, mediante recursión
Parametros:
valor, Entero que será el valor de x para cos(x)
iter, Entero que indicara la cantidad de sumas
Retorno: Retorna la aproximación de coseno
|#

(define rec_cos
  (lambda (valor iter)
     (let rec ((suma 0) (val valor) (n iter))
       (if (< n 0) suma
             (rec (+ suma (/ (* (expt -1 n) (expt valor (* 2 n))) (factorial(* 2 n)))) val (- n 1))
       )
     )
  )
)

#|
Funcion: iter_cos
Descripcion: Aproxima el valor de coseno (cos(x)) utilizando el teorema de Maclaurin dado un x y un numero que indicara cuantas sumas se realizaran, mediante iteración
Parametros:
valor, Entero que será el valor de x para cos(x)
iter, Entero que indicara la cantidad de sumas
Retorno: Retorna la aproximación de coseno
|#

(define iter_cos
  (lambda (valor iter)
    (let iter ((suma 0) (val valor) (n iter))
      (do ((num n (- num 1)) (suma 0 (+ suma (/ (* (expt -1 num) (expt valor (* 2 num))) (factorial(* 2 num))))))
      ((< num 0) suma)
      )
    )
  )
)