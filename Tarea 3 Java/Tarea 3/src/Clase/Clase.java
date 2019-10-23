package Clase;

import Personaje.Enemigo;
import Personaje.Jugador;

public abstract class Clase {
	public abstract void crearClase();
	public abstract void ataque(Jugador jugador, Enemigo enemy, int damage, String personaje);
	public abstract void defender(int valor);
	public abstract int getArmadura();
	public abstract int getDefender();
	public abstract String getAtaque();
	public abstract String getClase();
	public abstract void setDefender(int defensa);
}
