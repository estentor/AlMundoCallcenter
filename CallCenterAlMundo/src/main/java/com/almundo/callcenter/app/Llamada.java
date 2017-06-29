package com.almundo.callcenter.app;

import java.util.Random;
/**
 * Esta clase representa las llamadas que se atienden en el call center
 * 
 * @author Luis Soto
 *
 */
public class Llamada {

	//Atributo para identificar la llamada, fines didacticos y de mensajes
	private int id;
	
	/**
	 * constructor
	 * @param id
	 */
	public Llamada(int id) {
		this.id = id;
	}

	/**
	 * Metodo para atender la llamada, lo que hace basicamente es
	 * poner en sleep el hilo simulando el tiempo de la llamada
	 */
	public void atenderLlamada() {
		try {
			System.out.println("Llamada "+(this.id+1)+" en ejecucion.... ");
			Thread.sleep(tiempoVida()); //define el tiempo de la llamada
		} catch (Exception e) {
			e.printStackTrace();
		}
		//Para cambiar el color de la salida por consola uso el "err"
		System.err.println("Llamada "+(this.id+1)+" finalizada...");  
	}
	
	/**
	 * deuelve un numero aleatorio entre 5mil y 10mil
	 * @return int 5000 - 10000
	 */
	public int tiempoVida(){
		Random aleatorio = new Random();
    	return (5 + aleatorio.nextInt((10+1)-5))*1000;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}
	
}
