package com.almundo.callcenter.app;

import java.util.ArrayList;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * clase que contiene el metodo dispatch call 
 * recibe la cantidad finita de llamadas y la cantidad de operadores definida en el 
 * enunciado del problema (10) 
 * @author Luis Soto
 *
 */
public class Dispatch{
	
	
	private ArrayList<Thread> arr;
	private ConcurrentLinkedQueue<Llamada> llamadasCola; 

	/**
	 * Metodo main para ejecutar la simulacion del call center
	 * @param args
	 */
	public static void main(String[] args) {
		Dispatch d = new Dispatch();
		try {
			d.dispatchCall(10, 24);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Metodo para realizar la asignacion de llamadas a una cola 
	 * y distribuir llamadas a los empleados
	 * @param numeroEmpleados
	 * @param numeroLlamadas
	 */
	public void dispatchCall(int numeroEmpleados, int numeroLlamadas) throws InterruptedException{
		arr = new ArrayList<Thread>();
		llamadasCola = new ConcurrentLinkedQueue<Llamada>();
		//Genera la lista de hilos que representan cantidad de empleados (10)
		for (int i = 0; i < numeroEmpleados; i++) {
			Thread t = new Thread(new Empleado(llamadasCola)); 
			arr.add(t);
		}
		//Añade el total de llamadas definidas a la lista de llamadas 24 en el metodo main
		for (int i = 0; i < numeroLlamadas; i++) {
			llamadasCola.add(new Llamada(i));
		}
		//inicia el trabajo de los empleados
		for (int i = 0; i < numeroEmpleados; i++) {
			arr.get(i).start();
		}
		//Se verifican y finalizan los hilos
		for (int i = 0; i < numeroEmpleados; i++) {
			try {
				arr.get(i).join();
				System.out.println("El empleado "+(i+1)+" finalizo");
			} catch (InterruptedException e) {
				throw new InterruptedException();
			}
		}
	}
}
