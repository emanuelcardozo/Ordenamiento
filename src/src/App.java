package src; 

public class App {
 /***
  * ejecutarAlgoritmo( String tipoDeOrdenamiento, String tipoDeArray, int cantidadElementos, int tiempoRetardo) 
  * tipoOrdenamiento: Burbuja - Seleccion - Quicksort
  * tipoDeArray: Aleatorio - Invertido - Casi invertido - Ordenado - Casi ordenado
  */
	public static void main(String[] args) {
		Ventana v = new Ventana();
		v.ejecutarAlgoritmo("Seleccion", "Aleatorio", 200, 4);
	}
}
