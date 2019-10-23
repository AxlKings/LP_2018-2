all: tarea2

tarea2: genetico.o fun.o tdaTarea2.o
	gcc -o tarea2 tdaTarea2.o genetico.o fun.o

fun.o: fun.c fun.h
	gcc -c fun.c fun.h

tdaTarea2.o: tdaTarea2.c tdaTarea2.h
	gcc -c tdaTarea2.c tdaTarea2.h

genetico.o: genetico.c genetico.h
	gcc -c genetico.c genetico.h
