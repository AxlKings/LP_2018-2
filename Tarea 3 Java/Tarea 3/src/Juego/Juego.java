package Juego;

// Funcion que retorna un entero entre 1 y el valor del dado.

public class Juego {
	public static int lanzarDados(int dado) {
		return (int)(Math.random()*dado) + 1;
	}
}

