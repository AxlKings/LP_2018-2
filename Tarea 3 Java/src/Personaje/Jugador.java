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

public class Jugador implements Personaje{
	private String nombre;
	private int vida = 10;
	private int vidaMax;
	private int razaJugador;
	Raza raza;
	Clase clase;
	
	// Crea al jugador con la raza, clase, vida y nombre correspondiente.
	
	public Jugador(int razaEscogida1, int claseEscogida1, String nome) {
		setRaza(razaEscogida1);
		asignarRaza(razaEscogida1);
		asignarClase(claseEscogida1);
		asignarVida();
		asignarNombre(nome);
	}
	
	// Ve la raza que fue escogida para crear al jugador.
	
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
	
	// Ve la clase que fue escogida para crear al jugador.
	
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
	public String getNombre() {
		return nombre;
	}
	public void setVida(int vid) {
		vida = vid;
	}
	public void setVidaMax(int vida) {
		vidaMax = vida;
	}
	public int getVida() {
		return vida;
	}
	public int getVidaMax() {
		return vidaMax;
	}
	public void setRaza(int raza) {
		razaJugador = raza;
	}
	public void setDefensa(int defensa) {
		clase.setDefender(defensa);
	}
	public int getRaza() {
		return razaJugador;
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
	public String getClase() {
		return clase.getClase();
	}
	public void ataque(Jugador jugador, Enemigo enemy, int damage, String personaje) {
		clase.ataque(jugador, enemy, damage, personaje);
	}
}
