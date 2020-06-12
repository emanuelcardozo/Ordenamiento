package src; 

public class App {
 /***
  * ejecutarAlgoritmo( String tipoDeOrdenamiento, String tipoDeArray, int cantidadElementos, int tiempoRetardo) 
  * tipoOrdenamiento: Burbuja - Seleccion - Quicksort
  * tipoDeArray: Aleatorio - Invertido - Casi invertido - Ordenado - Casi ordenado
  */
	public static void main(String[] args) {
		new Ventana("Burbuja", "ordenado", 200, 4).run();
	}
}
