#include <stdio.h>
#include <stdlib.h>
#include "tdaTarea2.h"

/******
*   void siguiente
*******
*   Cambia el nodo actual al siguiente
*******
*   Input:
*   	lista *l: Lista de tipo lista *
*******
*   No retorna nada
******/
void siguiente(lista *l){
  nodo *temp;
	temp = l->act->next;																//crea un nodo temporal para apuntar al siguiente del actual
	l->act = temp;																		// y luego el actual apunta al nodo guardado en el temporal.
}
/******
*   void agregar
*******
*   Agrega los valores ingresados por parametro a un nodo de la lista
*******
*   Input:
*		void *dat: Dato de tipo void*
*		char type: Dato de tipo char, este puede ser i, c o b
*   	lista *l: Lista de tipo lista*
*******
*   No retorna nada
******/
void agregar(void *dat, char type, lista *l){
  nodo *temp = (nodo*)malloc(sizeof(nodo));
  temp->dato = dat;																//Se crea un nodo el cual se le otorgan los datos
  temp->tipo = type;															// recibidos en el input (dato y tipo)
  temp->next = NULL;
	if (l->tamano != 0){																// Hacemos que la cola apunte al nodo creado
		l->cola->next = temp;															// y luego el nodo creado pasa a ser la cola
  	l->cola = temp;
	}
	else{
		l->cola = temp;
    l->cabeza =temp;														// En caso de que la lista esté vacía, simplemente
	}																					           // Hacemos que la cola y la cabeza apunte al nodo creado
  (l->tamano)++;
}
/******
*   void asignarNodo
*******
*   Asigna los valores de dato aleatoriamente a un nodo entregado por parametro
*******
*   Input:
*		nodo *nodo: Nodo de tipo nodo*
*		char tipo: Dato de tipo char, este puede ser i, c o b
*******
*   No retorna nada
******/
void asignarNodo(nodo *nodo, char tipo){
	nodo->tipo = tipo;
	if (tipo == 'c'){
		char letras[] = {'A', 'B', 'C', 'D', 'E', 'F'};
		char *letra = malloc(sizeof(char));
		char letraEscogida = letras[rand()%6];
    *letra = letraEscogida;
		nodo->dato = letra;
	}
	else if (tipo == 'i'){
		int *numero = malloc(sizeof(int));
		int enteroEscogido = rand()%10;
    *numero = enteroEscogido;
		nodo->dato = numero;
	}
	else {
		int *bit = malloc(sizeof(int));
		int bitEscogido = rand()%2;
    *bit = bitEscogido;
		nodo->dato = bit;
	}
}
/******
*   void crearNodo
*******
*   Crea el espacio para un nodo y modifica valores generales de la lista
*******
*   Input:
*		lista *list: Lista de tipo lista*
*		char tipo: Dato de tipo char, este puede ser i, c o b
*******
*   No retorna nada
******/
void crearNodo(lista *list, char tipo){
	if(list->cabeza == NULL){
		list->cabeza = (nodo *)malloc(sizeof(nodo));
		list->act = list->cabeza;
		list->tamano = 1;
		asignarNodo(list->act, tipo);
		list->cola = list->act;
	}
	else if((list->act)->next == NULL){
		(list->act)->next = (nodo *)malloc(sizeof(nodo));
		list->tamano += 1;
		list->act = (list->act)->next;
		asignarNodo(list->act, tipo);
		list->cola = list->act;
	}
}
