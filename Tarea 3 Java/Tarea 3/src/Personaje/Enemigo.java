package Personaje;

import Clase.Barbaro;
import Clase.Clase;
import Clase.Clerigo;
import Clase.Mago;
import Clase.Picaro;
import Raza.Elfo;
import Raza.Enano;
import Raza.Humano;
import Raza.Orco;
import Raza.Raza;

public class Enemigo implements Personaje{
	Raza raza;
	Clase clase;
	private String nombre;
	private int vida = 8;
	private int vidaMax;
	private int razaEnemigo;
	private int claseEnemigo;
	
	// Crea al enemigo con la raza, clase, vida y nombre correspondiente.
	
	public Enemigo(int razaEnemigo1, int claseEnemigo, String nome) {
		asignarRaza(razaEnemigo1);
		asignarClase(claseEnemigo);
		asignarVida();
		asignarNombre(nome);
	}
	
	// Ve la raza que fue escogida para crear al enemigo.
	
	public void asignarRaza(int razaEscogida) {
		if (razaEscogida == 1) {
			raza = new Humano();
		}
		else if (razaEscogida == 2) {
			raza = new Elfo();
		}
		else if (razaEscogida == 3) {
			raza = new Enano();
		}
		else {
			raza = new Orco();
		}
	}
	
	// Ve la clase que fue escogida para crear al enemigo.
	
	public void asignarClase(int claseEscogida) {
		if (claseEscogida == 1) {
			clase = new Barbaro();
		}
		else if (claseEscogida == 2) {
			clase = new Picaro();
		}
		else if (claseEscogida == 3) {
			clase = new Mago();
		}
		else {
			clase = new Clerigo();
		}
	}
	public void asignarVida() {
		vida += raza.getConstitucion();
		vidaMax = vida;
	}
	public void asignarNombre(String name) {
		setNombre(name);
	}
	
	public void setNombre(String name) {
		nombre = name;
	}
	public void setVida(int vid) {
		vida = vid;
	}
	public void setVidaMax(int vida) {
		vidaMax = vida;
	}
	public void setDefensa(int defensa) {
		clase.setDefender(defensa);
	}
	public String getNombre() {
		return nombre;
	}
	public int getVida() {
		return vida;
	}
	public int getVidaMax() {
		return vidaMax;
	}
	public Raza getRaza() {
		return raza;
	}
	public String getClase() {
		return clase.getClase();
	}
	public int getFuerza() {
		return raza.getFuerza();
	}
	public int getDestreza() {
		return raza.getDestreza();
	}
	public int getConstitucion() {
		return raza.getConstitucion();
	}
	public String getHabilidad() {
		return raza.getHabilidad();
	}
	public int getArmadura() {
		return clase.getArmadura();
	}
	public int getDefender() {
		return clase.getDefender();
	}
	public String getAtaque() {
		return clase.getAtaque();
	}
	public void ataque(Jugador jugador, Enemigo enemy, int damage, String personaje) {
		clase.ataque(jugador, enemy, damage, personaje);
	}
}
