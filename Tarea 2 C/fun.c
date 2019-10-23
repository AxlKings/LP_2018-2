#include <stdio.h>
#include <stdlib.h>
#include "tdaTarea2.h"
#include "fun.h"

/******
*   int fun (PRUEBA)
*******
*   Evalua la calidad de un nodo.
*******
*   Input:
*   	void *node: Nodo de tipo nodo*
*******
*   Retorna el puntaje obtenido para el nodo
******/
int fun(void *node){
	int puntaje;
	if (((nodo *)node)->tipo == 'i'){
		puntaje = 1;
	}
	else if (((nodo *)node)->tipo == 'c'){
		puntaje = 2;
	}
	else{
		puntaje = 3;
	}
	return puntaje;
}
