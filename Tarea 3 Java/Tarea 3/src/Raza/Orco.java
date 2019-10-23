package Raza;

public class Orco extends Raza{
	private int Fuerza;
	private int Destreza;
	private int Constitucion;
	private String Habilidad;
	
	// Crea la raza y asigna la habilidad correspondiente.
	
	public Orco() {
		crearRaza();
		habilidad();
	}
	
	// Settea los valores predeterminados de la raza.
	
	public void crearRaza() {
		setFuerza(2);
		setDestreza(0);
		setConstitucion(1);
	}
	public void habilidad() {
		setHabilidad("Atacante");
	}
	public void setFuerza(int fuer) {
		Fuerza = fuer;
	}
	public void setDestreza(int destr) {
		Destreza = destr;
	}
	public void setConstitucion(int consti) {
		Constitucion = consti;
	}
	public void setHabilidad(String habil) {
		Habilidad = habil;
	}
	public int getFuerza() {
		return Fuerza;
	}
	public int getDestreza() {
		return Destreza;
	}
	public int getConstitucion() {
		return Constitucion;
	}
	public String getHabilidad() {
		return Habilidad;
	}	
}
