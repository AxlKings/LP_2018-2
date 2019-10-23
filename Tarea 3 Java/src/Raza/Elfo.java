package Raza;

public class Elfo extends Raza{
	private int Fuerza;
	private int Destreza;
	private static int Constitucion;
	private String Habilidad;
	
	// Crea la raza y asigna la habilidad correspondiente.
	
	public Elfo() {
		crearRaza();
		habilidad();
	}
	
	// Settea los valores predeterminados de la raza.
	
	public void crearRaza() {
		setFuerza(0);
		setDestreza(2);
		setConstitucion(1);
	}
	public void habilidad() {
		setHabilidad("Evasion");
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