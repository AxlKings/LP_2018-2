Ethiel Carmona Contalba, 201773533-3
Axel Reyes Orellana, 201773502-3

En primer lugar, se debe abrir la terminal y compilar usando el comando "make", luego se debe ejecutar el programa usando "./tarea2".

En segundo lugar, se debe tener un archivo llamado "fun.c" en la carpeta donde se ejecute la tarea.
Este debe contener la funcion "fun", la cual recibe un nodo de la lista a evaluar.

En tercer lugar, se trabajó con listas enlazadas simples, las cuales contienen nodos con la siguiente estructura:

                                        typedef struct nodo{
                                          	void *dato;
                                          	char tipo;
                                        	struct nodo* next;
                                        }nodo;

Los nodos sólo apuntan al nodo siguiente.

Por último, la lista presenta la siguiente estructura:

                                        typedef struct lista{
                                            nodo *cabeza;
                                            nodo *cola;
                                            nodo *act;
                                            int tamano;
                                        }lista;
