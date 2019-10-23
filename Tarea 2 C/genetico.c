#include <stdio.h>
#include <stdlib.h>
#include <time.h>
#include <unistd.h>
#include <string.h>
#include "fun.h"
#include "tdaTarea2.h"
#include "genetico.h"
/******
*   void *generarSolucion
*******
*   Crea una lista enlazada de largo n compuesta por elementos al azar, y devuelve el puntero correspondiente.
*******
*   Input:
*   	void *Lista: Lista de tipo *
*******
*   Retorna puntero a una lista
******/
void *generarSolucion(int n){
	sleep(1);
	int i;
	lista *list = (lista *)malloc(sizeof(lista));
	char tipos[] = {'i', 'c', 'b'};
	for (i = 0; i < n; i++){
		int tipo = rand()%3;
		char tipoEscogido = tipos[tipo];
		crearNodo(list, tipoEscogido);
	}
	list->act = list->cabeza;
	return list;
}

/******
*   void copiar
*******
*   Crea una copia de la lista ingresada y retorna el puntero correspondiente.
*******
*  Input:
*   	void *Lista: Lista de tipo *
*******
*   No tiene retorno
******/
void* copiar(void *Lista){
  int i;
  void* dat;
  char tip;
  int largo = ((lista*)Lista)->tamano;
  lista *l;
  l = (lista*)malloc(sizeof(lista));
  ((lista*)Lista)->act = ((lista*)Lista)->cabeza;
  for(i = 0; i<largo; i++){
    tip = ((lista*)Lista)->act->tipo;
		if (tip == 'i'){
			dat = malloc(sizeof(int));
		}
		else if (tip == 'b'){
			dat = malloc(sizeof(int));
		}
		else{
			dat = malloc(sizeof(char));
		}
    dat = ((lista*)Lista)->act->dato;
    agregar(dat,tip,l);
    siguiente((lista*)Lista);
  }
	((lista*)Lista)->act = ((lista*)Lista)->cabeza;
  return (void*)l;
}

/******
*   void borrar
*******
*   Libera el espacio de memoria asignado a una lista.
*******
*   Input:
*   	void *Lista: Lista de tipo *
*******
*   No tiene retorno
******/
void borrar(void *Lista){
	nodo *temp, *aux;
	aux = (nodo *)((lista *)Lista)->cabeza;
	while(aux != NULL){
		temp = aux->next;
		free((void *)aux->dato);
		free((void *)aux);
		aux = temp;
	}
	free((void *)Lista);
}

/******
*   void imprimirSolucion
*******
*   Muestra por consola los elementos de la lista en el formato (dato,tipo).
*******
*   Input:
*   	void *Lista: Lista de tipo *
*******
*   No tiene retorno
******/
void imprimirSolucion(void* Lista){
  int largo = ((lista*)Lista)->tamano;
  int i;
  ((lista*)Lista)->act = ((lista*)Lista)->cabeza;
  for (i = 0; i < largo; i++){
    char tip = ((lista*)Lista)->act->tipo;
    if (tip == 'i'){
      int dat = *(int*)(((lista*)Lista)->act->dato);
      printf("(%d,%c) ", dat,tip);
    }
    else if (tip == 'c'){
      char dat = *(char*)(((lista*)Lista)->act->dato);
      printf("(%c,%c) ", dat,tip);
    }
    else{
      int dat = *(int*)(((lista*)Lista)->act->dato);
      printf("(%d,%c) ", dat,tip);
    }
    siguiente((lista*)Lista);
  }
  printf("\n");
}

/******
*   void cruceMedio
*******
*   Intercambia la secciónde los primeros n/2 elementos de la lista 1 por los de la lista 2. En caso de ser de largo impar, n/2 se trunca. Ambas listas serán del mismo largo.
*******
*   Input:
*   	void *Lista1, *Lista2: Listas de tipo *
*******
*   No tiene retorno
******/
void cruceMedio(void* Lista1, void* Lista2){
  int largo = ((lista*)Lista1)->tamano;
  int i;
  nodo* aux;
  ((lista*)Lista1)->act = ((lista*)Lista1)->cabeza;
  ((lista*)Lista2)->act = ((lista*)Lista2)->cabeza;
  if (largo%2 != 0){
    largo--;
  }
  for (i=0;i<(largo/2);i++){
    if (i != (largo/2)-1){
      siguiente(Lista1);
      siguiente(Lista2);
    }
    else{
      aux = ((lista*)Lista1)->act->next;
      ((lista*)Lista1)->act->next = ((lista*)Lista2)->act->next;
      ((lista*)Lista2)->act->next = aux;
    }
  }
  aux = ((lista*)Lista1)->cabeza;
  ((lista*)Lista1)->cabeza = ((lista*)Lista2)->cabeza;
  ((lista*)Lista2)->cabeza = aux;
}

/******
*   void cruceIntercalado
*******
*   Intercambia los elementos que se encuentren en posiciones pares entre las listas.
*******
*   Input:
*   	void *Lista1, *Lista2: Listas de tipo *
*******
*   No tiene retorno
******/
void cruceIntercalado(void* Lista1, void* Lista2){
  int largo = ((lista*)Lista1)->tamano;
  ((lista*)Lista1)->act = ((lista*)Lista1)->cabeza;
  ((lista*)Lista2)->act = ((lista*)Lista2)->cabeza;
  //((lista*)Lista1)->cabeza = ((lista*)Lista2)->cabeza;
  //((lista*)Lista2)->cabeza = aux;
  char tip;
  void *dat;
  int i;
  for(i=0;i<largo;i+=2){
    tip = ((lista*)Lista1)->act->tipo;
    dat = ((lista*)Lista1)->act->dato;
    ((lista*)Lista1)->act->tipo = ((lista*)Lista2)->act->tipo;
    ((lista*)Lista1)->act->dato = ((lista*)Lista2)->act->dato;
    ((lista*)Lista2)->act->tipo = tip;
    ((lista*)Lista2)->act->dato = dat;
    siguiente(Lista1);
    siguiente(Lista1);
    siguiente(Lista2);
    siguiente(Lista2);
  }
  ((lista*)Lista1)->act = ((lista*)Lista1)->cabeza;
  ((lista*)Lista2)->act = ((lista*)Lista2)->cabeza;
}

/******
*   void mutacionRand
*******
*   Selecciona un elemento aleatoriamente y lo reemplaza por otro valor al azar (que puede ser del mismo o distinto tipo)
*******
*   Input:
*   	void *Lista: Lista de tipo *
*******
*   No tiene retorno
******/
void mutacionRand(void *Lista){
	char tipos[] = {'i', 'c', 'b'};
	char tipo = tipos[rand()%3];
 	int largo = ((lista *)Lista)->tamano;
	int aleatorio = rand()%largo;
  int node = 0;
  nodo *aux = ((lista *)Lista)->cabeza;
  while(aux != NULL){
  	if (node == aleatorio){
    	if (tipo == 'c'){
        char letras[] = {'A', 'B', 'C', 'D', 'E', 'F'};
        int aleatorioLetras = rand()%6;
        char valorLetra = letras[aleatorioLetras];
        while(aux->dato == &aleatorioLetras){
		  		aleatorioLetras = rand()%6;
	      	valorLetra = letras[aleatorioLetras];
				}
      	free(aux->dato);
      	char *valorChar = malloc(sizeof(char));
        *valorChar = valorLetra;
        aux->dato = valorChar;
		  	aux->tipo = tipo;
      }
      else if (tipo == 'i'){
      	int entero = rand()%10;
      	while(aux->dato == &entero){
          entero = rand()%10;
        }
        free(aux->dato);
        int *valorInt = malloc(sizeof(int));
        *valorInt = entero;
        aux->dato = valorInt;
		  	aux->tipo = tipo;
      }
      else{
				int binario = rand()%2;
				while(aux->dato == &binario){
					binario = rand()%2;
				}
				free(aux->dato);
				int *valorBit = malloc(sizeof(int));
				*valorBit = binario;
        aux->dato = valorBit;
				aux->tipo = tipo;
      }
    }
	  aux = aux->next;
    node++;
	}
}

/******
*   void mutacionTipo
*******
*   Selecciona un elemento de la lista aleatoriamente y lo reemplaza por otro valor al azar del mismo tipo. En el caso de los bit, se cambia obligatoriamente.
*******
*   Input:
*   	void *Lista: Lista de tipo *
*******
*   No tiene retorno
******/
void mutacionTipo(void *Lista){
  int largo = ((lista *)Lista)->tamano;
  int aleatorio = rand()%largo;
  int i = 0;
  nodo *aux = ((lista *)Lista)->cabeza;
  while(aux != NULL){
    if (i == aleatorio){
      if (aux->tipo == 'c'){
        char letras[] = {'A', 'B', 'C', 'D', 'E', 'F'};
        int largoLetras = strlen(letras);
        int aleatorioLetras = rand()%largoLetras;
        char valorLetra = letras[aleatorioLetras];
        while(aux->dato == &aleatorioLetras){
          aleatorioLetras = rand()%largoLetras;
          valorLetra = letras[aleatorioLetras];
        }
        free(aux->dato);
        char *valorChar = malloc(sizeof(char));
        *valorChar = valorLetra;
        aux->dato = valorChar;
      }
      else if (aux->tipo == 'i'){
        int entero = rand()%10;
        while(aux->dato == &entero){
          entero = rand()%10;
        }
        free(aux->dato);
        int *valorInt = malloc(sizeof(int));
        *valorInt = entero;
        aux->dato = valorInt;
      }
      else{
        if(aux->dato == (int *)1){
          free(aux->dato);
          int *valorBit0 = malloc(sizeof(int));
          *valorBit0 = 0;
          aux->dato = valorBit0;
        }
        else{
          free(aux->dato);
          int *valorBit1 = malloc(sizeof(int));
          *valorBit1 = 1;
          aux->dato = valorBit1;
        }
      }
    }
	aux = aux->next;
  i++;
  }
}

/******
*   int evaluacionLista
*******
*   Aplica una función de evaluación sobre cada uno de los nodos, la cual evalúa la calidad de éste último
*******
*   Input:
*   	int (*fun)(void *): Funcion que se encarga de evaluar un nodo, es de tipo *, recibe como parametro un void * y retorna un entero
*		void *Lista: Lista de tipo void *
*******
*   Retorna la suma de todos los puntajes obtenidos
******/
int evaluacionLista(int (*fun)(void *), void *Lista){
	int puntaje = 0;
	nodo *aux = ((lista *)Lista)->cabeza;
	while (aux != NULL){
		puntaje += fun((void *)aux);
		aux = aux->next;
	}
	return puntaje;
}

int main(){
  srand(time(NULL));											//El main solo inicia el srand
  return 0;
}
