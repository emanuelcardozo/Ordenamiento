package utils;

public class GeneradorDeArray {
	
	private static int[] getArrayAleatorio(int size) {
		int[] array = new int[size];
		
		for (int i = 0; i < array.length; i++) {
			array[i] = i + 1;
		}
		for (int i = 0; i < array.length; i++) {
			int index = (int) (Math.random() * array.length);
			int temp = array[i];
			array[i] = array[index];
			array[index] = temp;
		}
		
		return array;
	}
	
	private static int[] getArrayInvertido(int size) {
		int[] array = new int[size];
		
		for (int i = 0; i < array.length; i++) {
			array[i] = size - i;
		}
		
		return array;
	}
	
	private static int[] getArrayCasiInvertido(int size) {
		int[] array = new int[size];
		
		for (int i = 0; i < array.length/2; i++) {
			array[i] = size - i;
		}
		for (int i = array.length/2; i < array.length; i++) {
			array[i] = size;
		}
		
		return array;
	} 
	
	private static int[] getArrayOrdenado(int size) {
		int[] array = new int[size];
		
		for (int i = 0; i < array.length; i++) {
			array[i] = i + 1;
		}
		
		return array;
	} 
	
	private static int[] getArrayCasiOrdenado(int size) {
		int[] array = new int[size];
		
		for (int i = 0; i < array.length / 2; i++) {
			array[i] = i + 1;
		}
		for (int i = array.length / 2; i < array.length; i++) {
			array[i] = i + 2;
		}
		array[array.length - 1] = array.length / 2 + 1;
		
		return array;
	} 
	
	public static int[] get( String name, int size ) {
		switch(name) {
			case "Aleatorio": return getArrayAleatorio(size);
			case "Invertido": return getArrayInvertido(size);
			case "Casi invertido": return getArrayCasiInvertido(size);
			case "Ordenado": return getArrayOrdenado(size);
			}
		return getArrayCasiOrdenado(size);
	}
}
