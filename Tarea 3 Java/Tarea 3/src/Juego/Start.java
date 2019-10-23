package Juego;
import java.util.ArrayList;

import java.util.List;
import java.util.Scanner;

import Personaje.Enemigo;
import Personaje.Jugador;



public class Start {
	private static Scanner entero;							//Se crean los scanners para leer la raza, clase y el nombre del personaje.
	private static Scanner name;
	public static int personaje;
	public static void main(String[] args) {
		
		System.out.println("Bienvenido a Dunyons & Doragons!");
		System.out.println("Para empezar, crearemos tu personaje.");
		
		entero = new Scanner(System.in);		
		System.out.println("Escoge la raza que quieres ser:");
		System.out.println("1) Humano");
		System.out.println("2) Elfo");
		System.out.println("3) Enano");
		System.out.println("4) Orco");
		int tipoRaza = entero.nextInt();						// Se guarda la raza escogida.

		
		System.out.println("Escoge la clase que deseas:");
		System.out.println("1) Barbaro");
		System.out.println("2) Picaro");
		System.out.println("3) Mago");
		System.out.println("4) Clerigo");
		int tipoClase = entero.nextInt();							// Se guarda la clase escogida.
		
		name = new Scanner(System.in);								
		System.out.println("Escoge el nombre para tu personaje:");
		String nombreJugador = name.nextLine();							// Se guarda el nombre escogido.
			
		Jugador player = new Jugador(tipoRaza, tipoClase, nombreJugador);		// Se crea el personaje del usuario.
		System.out.println("Ya tenemos todo listo!");
		System.out.println("Tu aventura ha comenzado " + player.getNombre() + "!");
		Enemigo enemigo;
		
		// Se crea una lista con los nombres de los enemigos a los que se puede enfrentar.
		
		List<String> listaEnemigos = new ArrayList<String>();			
		listaEnemigos.add("Klrak");
		listaEnemigos.add("Adran");
		listaEnemigos.add("Isaac");
		listaEnemigos.add("Elysium");
		listaEnemigos.add("Krrogh");
		listaEnemigos.add("Jenkins");
		
		// Se crea una lista con las razas de los enemigos, con el respectivo orden de la lista anterior.
		
		List<Integer> razasEnemigos = new ArrayList<Integer>(6);
		razasEnemigos.add(3);
		razasEnemigos.add(2);
		razasEnemigos.add(1);
		razasEnemigos.add(2);
		razasEnemigos.add(4);
		razasEnemigos.add(1);

		// Se crea una lista con las clases de los enemigos, con el respectivo orden.		
		
		List<Integer> clasesEnemigos = new ArrayList<Integer>(6);
		clasesEnemigos.add(1);
		clasesEnemigos.add(2);
		clasesEnemigos.add(4);
		clasesEnemigos.add(3);
		clasesEnemigos.add(1);
		clasesEnemigos.add(3);		
		boolean flag;							// Se declara la variable flag, la cual nos mantendra luchando contra un enemigo.
		boolean GameOver = false;				// Se declara y define la variable GameOver, la cual nos mantendra en el juego.
		int dado;								// La variable "dado" guardara el d20 original, sin modificaciones, en cada caso.
		int dado20;
		int dado8;
		int dado6;
		int ataque;
		int damage;
		int vidaAct;
		int i;
		for(i = 0; i < 3; i++) {			// Comienza la secuencia de aparicion de enemigos. 					
			if (GameOver) {						// Se verifica de que el juego no haya acabado (caso en que el jugador muera).
				break;															
			}
			flag = true;
			int turno = 1;
			
			/* Se utiliza math random para escoger el enemigo al que se enfrentara
			y se crea con sus respectiva raza, clase y nombre. */
			
			personaje = (int)(Math.random()*6);			
			enemigo = new Enemigo((int)(razasEnemigos.get(personaje)), (int)(clasesEnemigos.get(personaje)), listaEnemigos.get(personaje));
			System.out.print("\n");
			System.out.println("Ha aparecido un enemigo!");
			System.out.println("Te enfrentas a " + listaEnemigos.get(personaje) );
			while (flag) {
				vidaAct = player.getVida();
				System.out.println("Tu vida actual es de " + vidaAct);
				System.out.print("\n");
				
				/* Se verifica de que el jugador no este muerto. En caso de que si, GameOver pasa a ser true y se sale del while.
				En caso contrario, se procede a pelear.*/
				
				if (vidaAct <= 0) {
					System.out.println(enemigo.getNombre() + " te ha vencido.");
					GameOver = true;
					flag = false;
				}
				else {
					System.out.println("Turno " + turno);
					player.setDefensa(0);
					
					/* Se verifica el caso de que algun personaje tenga la habilidad Resistencia
					En caso de tenerla, se le aumenta en 1 su salud a menos de que su salud este al maximo. */
					
					if (player.getHabilidad().equals("Resistencia")) {
						if (turno != 1) {
							if(player.getVida() < player.getVidaMax()) {
								int life = player.getVida() + 1;
								player.setVida(life);
								System.out.println("Has restaurado 1 de vida debido a tu habilidad Resistencia");
							}	
							else {
								System.out.println("La habilidad Resistencia no surtio efecto porque tu vida esta al maximo");
							}
						}	
					}
					
					if (enemigo.getHabilidad().equals("Resistencia")) {
						if (turno != 1) { 
							if(enemigo.getVida() < enemigo.getVidaMax()) {
								int life = enemigo.getVida() + 1;
								enemigo.setVida(life);
								System.out.println(enemigo.getNombre() + " ha restaurado 1 de vida debido a su habilidad Resistencia");
							
							}	
							else {
								System.out.println("La habilidad Resistencia de "+ enemigo.getNombre() + " no surtio efecto porque su vida esta al maximo");
							}
						}	
					}
					
					System.out.println("Es tu turno.");
					System.out.println("Que deseas hacer?");
					System.out.println("0) Atacar");
					System.out.println("1) Defender");	
					ataque = entero.nextInt();
					
					// Se ve el caso en que el jugador decida atacar.
					
					if(ataque == 0) {
						
						// Se ve el caso en que el ataque sea tipo fisico.
						
						if(player.getClase().equals("Barbaro") || player.getClase().equals("Picaro")){
							
							// Se ve el caso en que el jugador no posea la habilidad Suerte.
							
							if(!player.getHabilidad().equals("Suerte")) {
								dado20 = Juego.lanzarDados(20);
								
								// Se ve el caso en que el enemigo este defendiendo, el d20 toma el menor valor de ambos lanzamientos.
								
								if (enemigo.getDefender() == 1) {
									System.out.println("El enemigo esta en posicion de defensa");
									int dado1 = dado20;
									System.out.println("Del primer lanzamiento del dado se obtiene un " + dado1);
									dado20 = Juego.lanzarDados(20);
									System.out.println("Del segundo lanzamiento del dado se obtiene un " + dado20);
									if (dado20 > dado1) {
										dado20 = dado1;
									}
									System.out.println("El valor del dado de 20 debido a la posicion de defensa del rival resulta ser de " + dado20);
								}
							}
							
							// En caso contrario, se debe analizar el caso en que el d20 sea 1.
							
							else {
								
								// Se ve el caso en que el enemigo este defendiendo
								// En ese caso, hay que considerar que el caso en que el d20 sea 1 es un valor invalido.
								// Por lo tanto, hay que lanzar el d20 hasta que tengamos 2 valores validos y asi tomar el menor.
								
								if (enemigo.getDefender() == 1) {
									System.out.println("El enemigo esta en posicion de defensa");
									dado20 = Juego.lanzarDados(20);							
									while(dado20 == 1) {								
										System.out.println("El dado de 20 resulto 1, por lo que debido a la habilidad de Suerte, se procede a lanzar el dado de nuevo");							
										dado20 = Juego.lanzarDados(20);
									}
									int dado1 = dado20;
									System.out.println("Del primer lanzamiento del dado se obtiene un " + dado1);
									dado20 = Juego.lanzarDados(20);
									while(dado20 == 1) {								
										System.out.println("El segundo tiro de dado de 20 causado por la posicion de defensa del rival resulto 1, por lo que debido a la habilidad de Suerte, se procede a lanzar el dado de nuevo");							
										dado20 = Juego.lanzarDados(20);
									}
									System.out.println("Del segundo lanzamiento del dado se obtiene un " + dado20);
									if (dado20 > dado1) {
										dado20 = dado1;
									}
									System.out.println("El valor del dado de 20 debido a la posicion de defensa del rival resulta ser de " + dado20);
									
								}
								
								// En caso contrario, lanzamos el d20 hasta tener un resultado valido.
								
								else {
									dado20 = Juego.lanzarDados(20);							
									while(dado20 == 1) {								
										System.out.println("El dado de 20 resulto 1, por lo que debido a la habilidad de Suerte, se procede a lanzar el dado de nuevo");							
										dado20 = Juego.lanzarDados(20);
									}
								}
							}
							dado = dado20;
							if (dado20 != 20) {
								System.out.println("El dado de 20 resulta un " + dado20);
							}
							else {
								System.out.println("Sacaste 20 en el dado! El danio total se vera duplicado");
							}
							
							// Se suma +2 al d20 en caso de que la habilidad del jugador sea Atacante.
							
							if(player.getHabilidad().equals("Atacante")) {
								dado20 +=2;
								System.out.println("Debido a la habilidad de Atacante, se le suma +2 al dado de 20, la cifra final es de " + dado20);
							}
							System.out.println("La armadura de " + enemigo.getNombre() + " es de " + enemigo.getArmadura());
							
							// Se ve de que el d20 sea mayor o igual que la armadura para efectuar el ataque.
							
							if (dado20 >= enemigo.getArmadura()) {
								System.out.println("El ataque resulto efectivo");
								dado8 = Juego.lanzarDados(8);
								System.out.println("Al lanzar el dado de 8 se obtuvo "+ dado8);
								
								// Se ve la clase del jugador para ver con que stat sumar al d20.
								
								if (player.getClase().equals("Barbaro")) {
									damage = dado8 + player.getFuerza();
									System.out.println("Debido a los atributos de Barbaro, se le sumo +" + player.getFuerza() + ". El danio total es de " + damage);
								}
								else{
									damage = dado8 + player.getDestreza();
									System.out.println("Debido a los atributos de Picaro, se le sumo +" + player.getDestreza() + ". El danio total es de " + damage);
								}
								
								// En caso de que el d20 originalmente haya salido 20, se duplica el danio final.
								
								if (dado == 20) {
									damage *=2;
									System.out.println("El danio final resulta ser de " + damage + " debido al bono de duplicar danio");
								}
								System.out.println(enemigo.getNombre() + " recibe " + damage + " de danio");
								
								//Se efectua el ataque.
								
								player.ataque(player, enemigo, damage, "enemy");	
							}
							
							// En caso contrario, no se efectua el ataque.
							
							else {
								System.out.println("El ataque fallo");
								
							}
						}
						
						// En caso contrario, el ataque sera tipo magico.
						
						else {
							
							// Se ve el caso en que el jugador no posea la habilidad Suerte.
							
							if(enemigo.getHabilidad() != "Suerte") {
								dado20 = Juego.lanzarDados(20);
								
								// Se ve el caso en que el enemigo este defendiendo, el d20 toma el mayor valor de ambos lanzamientos.
								
								if (enemigo.getDefender() == 1) {
									System.out.println("El enemigo esta en posicion de defensa");
									int dado1 = dado20;
									System.out.println("Del primer lanzamiento del dado se obtiene un " + dado1);
									dado20 = Juego.lanzarDados(20);
									System.out.println("Del segundo lanzamiento del dado se obtiene un " + dado20);
									if (dado20 < dado1) {
										dado20 = dado1;
									}
									System.out.println("El valor del dado de 20 debido a la posicion de defensa del rival resulta ser de " + dado20);
								}
							}
							
							// En caso contrario, se debe analizar el caso en que el d20 sea 1.
							
							else {
								
								// Se ve el caso en que el enemigo este defendiendo
								// En ese caso, hay que considerar que el caso en que el d20 sea 1 es un valor invalido.
								// Por lo tanto, hay que lanzar el d20 hasta que tengamos 2 valores validos y asi tomar el mayor.
								
								if (enemigo.getDefender() == 1) {
									System.out.println("El enemigo esta en posicion de defensa");
									dado20 = Juego.lanzarDados(20);							
									while(dado20 == 1) {								
										System.out.println("El dado de 20 resulto 1, por lo que debido a la habilidad de Suerte, se procede a lanzar el dado de nuevo");							
										dado20 = Juego.lanzarDados(20);
									}
									int dado1 = dado20;
									System.out.println("Del primer lanzamiento del dado se obtiene un " + dado1);
									dado20 = Juego.lanzarDados(20);
									while(dado20 == 1) {								
										System.out.println("El segundo tiro de dado de 20 causado por la posicion de defensa del rival resulto 1, por lo que debido a la habilidad de Suerte, se procede a lanzar el dado de nuevo");							
										dado20 = Juego.lanzarDados(20);
									}
									System.out.println("Del segundo lanzamiento del dado se obtiene un " + dado20);
									if (dado20 < dado1) {
										dado20 = dado1;
									}
									System.out.println("El valor del dado de 20 debido a la posicion de defensa del rival resulta ser de " + dado20);
								}
								
								// En caso contrario, lanzamos el d20 hasta tener un resultado valido.
								
								else {
									dado20 = Juego.lanzarDados(20);							
									while(dado20 == 1) {								
										System.out.println("El dado de 20 resulto 1, por lo que debido a la habilidad de Suerte, se procede a lanzar el dado de nuevo");							
										dado20 = Juego.lanzarDados(20);
									}
								}
							}
							dado = dado20;
							
							// En caso de que el d20 no sea 20 se efectua el ataque.
							
							if (dado20 != 20) {
								System.out.println("El dado de 20 resulta un " + dado20);
								
								// Se ve la clase del jugador para ver con que stat del enemigo sumar al d20.
								
								if(player.getClase().equals("Mago")) {
									System.out.println("El valor del dado de 20 se le sumo +" + enemigo.getDestreza() + " por atributo de Mago");
									dado20 += enemigo.getDestreza();	
								}
								else{
									System.out.println("El valor del dado de 20 se le sumo +" + enemigo.getConstitucion() + " por atributo de Clerigo");
									dado20 += enemigo.getConstitucion();
								}
								System.out.println("El valor final del dado de 20 es de " + dado20);
								dado6 = Juego.lanzarDados(6);
								damage = dado6;
								
								// Se ve el caso en que la habilidad del enemigo sea Evasion, en ese caso se le suma +2 al d20.
								
								if (enemigo.getHabilidad().equals("Evasion")) {
									dado20 += 2;
									System.out.println("Debido a la habilidad de Evasion del rival, el valor arrojado por el dado aumento en 2");
									System.out.println("A causa de esto, el dado 20 finalmente resulta ser "+ dado20);
								}
								System.out.println("Al lanzar el dado de 6 se obtuvo " + dado6);
								
								// Si el d20 es mayor o igual a 13, el danio se reduce a la mitad (el resultado de la division se ve truncado).
								
								if (dado20 > 13) {
									System.out.println("Debido a que el valor final del dado de 20 es mayor que 13, el danio se reduce a la mitad");
									damage /= 2; 
								}
								else if (dado20 == 13) {
									System.out.println("Debido a que el valor final del dado de 20 es igual a 13, el danio se reduce a la mitad");
									damage /= 2; 
								}
								System.out.println(enemigo.getNombre() + " recibe " + damage + " de danio");
								
								// Se efectua el ataque.
								
								player.ataque(player, enemigo, damage, "enemy");
							}
							
							// En caso de que el d20 originalmente haya resultado 20, el ataque no se efectua.
							
							else if (dado == 20) {
								System.out.println("El dado resulto ser 20. El ataque magico fallo.");
							}
						}
						
					}
					
					// En caso contrario, el jugador toma posicion defensiva.
					
					else{
						player.setDefensa(1);
						System.out.println("Decidiste defender.");
					}
					System.out.println("La vida actual de " + enemigo.getNombre() + " es de " + enemigo.getVida());
					
					// Se ve el caso en que el enemigo ya no le quede vida. Si se cumple, flag se vuelve false y se sale del while.
					
					if (enemigo.getVida() <= 0) {
						flag = false;
						System.out.println(enemigo.getNombre() + " ha sido derrotado.");
						System.out.println("Tu vida actual es de " + player.getVida());
					}
					
					//  En caso contrario, es el turno del enemigo.
					
					else{
						System.out.println("Ahora es el turno de " + enemigo.getNombre());
						enemigo.setDefensa(0);
						
						// Se usa math random para determinar la accion del enemigo.
						
						int defender = (int)(Math.random()*2); 	
						
						// Se ve el caso en que el enemigo ataque.
						
						if (defender == 0) { 
							System.out.println(enemigo.getNombre() + " decidio atacar");
							
							// Se ve el caso en que el ataque sea tipo fisico.
							
							if(enemigo.getClase().equals("Barbaro") || enemigo.getClase().equals("Picaro")){
								
								// Se ve el caso en que el enemigo no posea la habilidad Suerte.
								
								if(!enemigo.getHabilidad().equals("Suerte")) {
									dado20 = Juego.lanzarDados(20);
									
									// Se ve el caso en que el jugador este defendiendo, el d20 toma el menor valor de ambos lanzamientos.
									
									if (player.getDefender() == 1) {
										System.out.println("Estas en posicion de defensa");
										int dado1 = dado20;
										System.out.println("Del primer lanzamiento del dado se obtiene un " + dado1);
										dado20 = Juego.lanzarDados(20);
										System.out.println("Del segundo lanzamiento del dado se obtiene un " + dado20);
										if (dado20 > dado1) {
											dado20 = dado1;
										}
										System.out.println("El valor del dado de 20 debido a tu posicion de defensa resulta ser de " + dado20);
									}
								}
								
								// En caso contrario, se debe analizar el caso en que el d20 sea 1.
							
								else {
									
									// Se ve el caso en que el jugador este defendiendo
									// En ese caso, hay que considerar que el caso en que el d20 sea 1 es un valor invalido.
									// Por lo tanto, hay que lanzar el d20 hasta que tengamos 2 valores validos y asi tomar el menor.
									
									if (player.getDefender() == 1) {
										System.out.println("Estas en posicion de defensa");
										dado20 = Juego.lanzarDados(20);							
										while(dado20 == 1) {								
											System.out.println("El dado de 20 resulto 1, por lo que debido a la habilidad de Suerte, se procede a lanzar el dado de nuevo");							
											dado20 = Juego.lanzarDados(20);
										}
										int dado1 = dado20;
										System.out.println("Del primer lanzamiento del dado se obtiene un " + dado1);
										dado20 = Juego.lanzarDados(20);
										while(dado20 == 1) {								
											System.out.println("El segundo tiro de dado de 20 causado por tu posicion de defensa resulto 1, por lo que debido a la habilidad de Suerte, se procede a lanzar el dado de nuevo");							
											dado20 = Juego.lanzarDados(20);
										}
										System.out.println("Del segundo lanzamiento del dado se obtiene un " + dado20);
										if (dado20 > dado1) {
											dado20 = dado1;
										}
										System.out.println("El valor del dado de 20 debido a tu posicion de defensa resulta ser de " + dado20);
										
									}
									
									// En caso contrario, lanzamos el d20 hasta tener un resultado valido.
									
									else {
										dado20 = Juego.lanzarDados(20);							
										while(dado20 == 1) {								
											System.out.println("El dado de 20 resulto 1, por lo que debido a la habilidad de Suerte, se procede a lanzar el dado de nuevo");							
											dado20 = Juego.lanzarDados(20);
										}
									}
								}
								dado = dado20;
								if (dado20 != 20) {
									System.out.println("El dado de 20 resulta un " + dado20);
								}
								else {
									System.out.println("" + enemigo.getNombre() + " saco 20 en el dado! El danio total se vera duplicado");
								}
								
								// Se suma +2 al d20 en caso de que la habilidad del jugador sea Atacante.
								
								if(enemigo.getHabilidad().equals("Atacante")) {
									dado20 +=2;
									System.out.println("Debido a la habilidad de Atacante, se le suma +2 al dado de 20, la cifra final es de " + dado20);
								}
								System.out.println("Tu armadura es de " + player.getArmadura());
								
								// Se ve que el d20 sea mayor o igual que la armadura para efectuar el ataque.
								
								if (dado20 >= player.getArmadura()) {
									System.out.println("El ataque resulto efectivo");
									dado8 = Juego.lanzarDados(8);
									System.out.println("Al lanzar el dado de 8 se obtuvo "+ dado8);
									
									// Se ve la clase del jugador para ver con que stat sumar al d20.
									
									if (enemigo.getClase().equals("Barbaro")) {
										damage = dado8 + enemigo.getFuerza();
										System.out.println("Debido a los atributos de la clase, se le sumo +" + enemigo.getFuerza() + ". El danio total es de " + damage);
									}
									else{
										damage = dado8 + enemigo.getDestreza();
										System.out.println("Debido a los atributos de la clase, se le sumo +" + enemigo.getDestreza() + ". El danio total es de " + damage);
									}
									
									// En caso de que el d20 originalmente haya salido 20, se duplica el danio final.
									
									if (dado == 20) {
										damage *=2;
										System.out.println("El danio final resulta ser de " + damage + ", debido al bono de duplicar danio");
									}
									System.out.println("Recibes " + damage + " de danio");
									
									//Se efectua el ataque.
									
									enemigo.ataque(player, enemigo, damage, "player");	
								}
								
								// En caso contrario, el ataque no se efectua.
								
								else {
									System.out.println("El ataque fallo");
								}
							}
							
							// Se ve el caso de que sea un ataque magico.
							
							else {
								
								// Se ve el caso de que el jugador no tenga la habilidad Suerte.
								
								if(player.getHabilidad() != "Suerte") {
									dado20 = Juego.lanzarDados(20);
									
									// Si el jugador esta en modo defensivo, el d20 se lanza 2 veces y se escoge el mayor.
									
									if (player.getDefender() == 1) {
										System.out.println("Estas en posicion de defensa");
										int dado1 = dado20;
										System.out.println("Del primer lanzamiento del dado se obtiene un " + dado1);
										dado20 = Juego.lanzarDados(20);
										System.out.println("Del segundo lanzamiento del dado se obtiene un " + dado20);
										if (dado20 < dado1) {
											dado20 = dado1;
										}
										System.out.println("El valor del dado de 20 debido a tu posicion de defensa resulta ser de " + dado20);
									}
								}
								
								// En caso contrario, se debe analizar el caso en que el d20 sea 1.
								
								else {
									
									// Se ve el caso en que el enemigo este defendiendo
									// En ese caso, hay que considerar que el caso en que el d20 sea 1 es un valor invalido.
									// Por lo tanto, hay que lanzar el d20 hasta que tengamos 2 valores validos y asi tomar el mayor.
									
									if (player.getDefender() == 1) {
										System.out.println("Estas en posicion de defensa");
										dado20 = Juego.lanzarDados(20);							
										while(dado20 == 1) {								
											System.out.println("El dado de 20 resulto 1, por lo que debido a la habilidad de Suerte, se procede a lanzar el dado de nuevo");							
											dado20 = Juego.lanzarDados(20);
										}
										int dado1 = dado20;
										System.out.println("Del primer lanzamiento del dado se obtiene un " + dado1);
										dado20 = Juego.lanzarDados(20);
										while(dado20 == 1) {								
											System.out.println("El segundo tiro de dado de 20 causado por tu posicion de defensa resulto 1, por lo que debido a la habilidad de Suerte, se procede a lanzar el dado de nuevo");							
											dado20 = Juego.lanzarDados(20);
										}
										System.out.println("Del segundo lanzamiento del dado se obtiene un " + dado20);
										if (dado20 < dado1) {
											dado20 = dado1;
										}
										System.out.println("El valor del dado de 20 debido a tu posicion de defensa resulta ser de " + dado20);
										
									}
									
									// En caso contrario, lanzamos el d20 hasta tener un resultado valido.
									
									else {
										dado20 = Juego.lanzarDados(20);							
										while(dado20 == 1) {								
											System.out.println("El dado de 20 resulto 1, por lo que debido a la habilidad de Suerte, se procede a lanzar el dado de nuevo");							
											dado20 = Juego.lanzarDados(20);
										}
									}
								}
								dado = dado20;
								
								// En caso de que el d20 no sea 20 se efectua el ataque.
								
								if (dado20 != 20) {
									System.out.println("El dado de 20 resulta un " + dado20);
									
									// Se ve la clase del enemigo para ver con que stat del jugador sumar al d20.
									
									if(enemigo.getClase().equals("Mago")) {
										System.out.println("El valor del dado de 20 se le sumo +" + player.getDestreza() + " por atributo de Mago");
										dado20 += player.getDestreza();	
									}
									else{
										System.out.println("El valor del dado de 20 se le sumo +" + player.getConstitucion() + " por atributo de Clerigo");
										dado20 += player.getConstitucion();
									}
									System.out.println("El valor modificado del dado de 20 es de " + dado20);
									dado6 = Juego.lanzarDados(6);
									damage = dado6;
									
									// Se ve el caso en que la habilidad del enemigo sea Evasion, en ese caso se le suma +2 al d20.
									
									if (player.getHabilidad().equals("Evasion")) {
										dado20 += 2;
										System.out.println("Debido a la habilidad de Evasion, el valor arrojado por el dado aumento en 2");
										System.out.println("A causa de esto, el dado 20 finalmente resulta ser "+ dado20);
									}
									System.out.println("Al lanzar el dado de 6 se obtuvo " + dado6);
									System.out.println("El valor final del dado de 20 es de " + dado20);
									
									// Si el d20 es mayor o igual a 13, el danio se reduce a la mitad (el resultado de la division se ve truncado).
									
									if (dado20 > 13) {
										System.out.println("Debido a que el valor final del dado de 20 es mayor que 13, el danio se reduce a la mitad");
										damage /= 2; 
									}
									else if (dado20 == 13) {
										System.out.println("Debido a que el valor final del dado de 20 es igual a 13, el danio se reduce a la mitad");
										damage /= 2; 
									}
									System.out.println("Recibes " + damage + " de danio");
									
									// Se efectua el ataque.
									
									enemigo.ataque(player, enemigo, damage, "player");
								}
								
								// En caso de que el d20 originalmente haya resultado 20, el ataque no se efectua.
								
								else if(dado == 20) {
									System.out.println("El dado resulto ser 20. El ataque magico fallo.");
								}				
							
							}
						}
						
						// En caso contrario, el enemigo toma posicion defensiva.
						
						else {
							enemigo.setDefensa(1);
							System.out.println(enemigo.getNombre() + " ha decidido defender.");
						}
					}
				}
				turno += 1;
			}
		}
		
		// En caso de que se salga del for y GameOver sea false, el jugador gana.
		// En caso contrario, el jugador pierde y se acaba el juego.
		
		if (!GameOver) {
			System.out.println("Lograste vencer a todos los enemigos!");
			System.out.println("Felicitaciones " + player.getNombre() + "!!!");
		}	
		System.out.println("Fin del juego.");
	}
}