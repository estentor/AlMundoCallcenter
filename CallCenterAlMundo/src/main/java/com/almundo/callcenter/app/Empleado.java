package com.almundo.callcenter.app;

import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.Semaphore;

/**
 * Hilo de empleado, esta clase simula los diferentes empleados 
 * que atenderan las llamadas durante el tiempo de ejecucion del programa
 * se definen 5 operadores, 4 supervisores y 1 director. Estas variables
 * se encargaran de simular los empleados del call center 
 * @author ESILUSOT
 *
 */
public class Empleado implements Runnable {

	private ConcurrentLinkedQueue<Llamada> listaLlamadas;
	
	private static Semaphore operador = new Semaphore(5); //Define la cantidad de operadores activos
	private static Semaphore supervisor = new Semaphore(4); //Define la cantidad de supervisores activos
	private static Semaphore director = new Semaphore(1);	//Define la cantidad de Directores activos
	
	public Empleado(ConcurrentLinkedQueue<Llamada> lista){
		this.listaLlamadas = lista;
	}

	/**
	 * Metodo que inicializa el hilo de "empleado" correspondiente
	 */
	public void run() { //Empieza el trabajo del empleado atendiendo llamadas
		try {
			while(true){
				if (operador.tryAcquire()){ //devuelve false si no hay operadores disponibles
					Llamada llamada = listaLlamadas.remove(); //saca una llamada de la cola y la atiende el operador
					System.out.println("Atendiendo Operador llamada "+llamada.getId());
					llamada.atenderLlamada();
					operador.release(); //Libera el operador de la llamada
				}else if (supervisor.tryAcquire()){//devuelve false si no hay supervisores disponibles
					Llamada llamada = listaLlamadas.remove(); //saca una llamada de la cola y la atiende el supervisor
					System.out.println("Atendiendo Supervisor llamada "+llamada.getId());
					llamada.atenderLlamada();
					supervisor.release(); //Libera el supervisor de la llamada
				}else if (director.tryAcquire()){//devuelve false si no hay directores disponibles
					Llamada llamada = listaLlamadas.remove(); //saca una llamada de la cola y la atiende el director
					System.out.println("Atendiendo Director llamada "+llamada.getId());
					llamada.atenderLlamada();
					director.release(); //Libera el director de la llamada
				}
				
			}
			
			
		} catch (Exception e) {
			System.err.println("No hay mas llamadas en la cola");
			
		}finally{
			operador.release();
			supervisor.release();
			director.release();
		}
	}
}
