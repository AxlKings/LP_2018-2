#include <stdio.h>
#include <stdlib.h>

typedef struct nodo{
  	void *dato;
  	char tipo;
	struct nodo* next;
}nodo;

typedef struct lista{
	nodo *cabeza;
	nodo *cola;
	nodo *act;
	int tamano;
}lista;

void siguiente(lista *l);
void agregar(void *dat, char type, lista *l);
void asignarNodo(nodo *nodo, char tipo);
void crearNodo(lista *list, char tipo);
