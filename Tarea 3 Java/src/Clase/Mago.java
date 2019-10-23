package Clase;

import Personaje.Enemigo;
import Personaje.Jugador;

public class Mago extends Clase{
	private String AtaqueMagico;
	private static int Armadura;
	private int Defender;
	private String Clase;

	// Crea la clase correspondiente.

	public Mago() {
		crearClase();
	}

	// Settea los valores predeterminados de la clase.

	public void crearClase() {
		setAtaque("Destreza");
		setArmadura(10);
		setClase("Mago");
		setDefender(0);
	}
	public void ataque(Enemigo enemigo, int damage) {
		System.out.println("Ha aparecido un enemigo!");
	}
	public void defender(int defensa) {
		//0 Si ataca, 1 si defiende
		setDefender(defensa);
	}
	public void setAtaque(String atack) {
		AtaqueMagico = atack;
	}
	public void setArmadura(int armor) {
		Armadura = armor;
	}
	public void setDefender(int defend) {
		Defender = defend;
	}
	public void setClase(String clase) {
		Clase = clase;
	}
	public String getAtaque() {
		return AtaqueMagico;
	}
	public int getArmadura() {
		return Armadura;
	}
	public int getDefender() {
		return Defender;
	}
	public String getClase() {
		return Clase;
	}

	/* Funcion ataque que ve si el objetivo es el jugador o el enemigo. Le resta el dano a la vida correspondiente.
	Recibe como parametros el objeto jugador, el objeto enemigo, el danio y un string que indica si el objetivo es
	el jugador o el enemigo.*/

	public void ataque(Jugador jugador, Enemigo enemy, int damage, String personaje) {

		if (personaje.equals("player")) {
			int life = jugador.getVida() - damage;
			jugador.setVida(life);
		}
		else {
			int life = enemy.getVida() - damage;
			enemy.setVida(life);

		}
	}
}
