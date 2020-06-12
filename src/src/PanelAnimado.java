package src;

import javax.swing.*;

import algoritmos.PorBurbujeo;
import algoritmos.PorQuickSort;
import algoritmos.PorSeleccion;

import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class PanelAnimado extends JApplet {
	private static final long serialVersionUID = 1L;
	private PanelOrdenador sp;
	private PanelOrdenadorContendor panelContenedor;
	private int delay;
	private final int WIDTH = 900;
	private final int HEIGHT = 600;
	private final int WIDTH_ANIMATION = WIDTH;
	private final int HEIGHT_ANIMATION = HEIGHT / 2;
	

//	public PanelAnimado(String tipoOrdenamiento, int tiempoDemora) {
//		PanelOrdenadorContendor sortPanelHolder = new PanelOrdenadorContendor();
//		sortPanelHolder.setPreferredSize(new Dimension(WIDTH_ANIMATION, HEIGHT_ANIMATION));
//		sortPanelHolder.setBackground(Color.BLACK);
//		sortPanelHolder.setForeground(Color.BLACK);
//		this.delay = tiempoDemora;
//		
//		if (tipoOrdenamiento.equals("Burbujeo") || tipoOrdenamiento.equals("Burbuja")
//				|| tipoOrdenamiento.equals("Por burbujeo"))
//			sp = new PorBurbujeo(delay, WIDTH_ANIMATION, HEIGHT);
//		else if (tipoOrdenamiento.equals("Seleccion") || tipoOrdenamiento.equals("Por seleccion"))
//			sp = new PorSeleccion(delay, WIDTH_ANIMATION, HEIGHT);
//		else if (tipoOrdenamiento.equals("Quicksort"))
//			sp = new PorQuickSort(delay, WIDTH_ANIMATION, HEIGHT);
//		sp.setVisible(true);
//		sortPanelHolder.add(sp);
//		add(sortPanelHolder);
//	}

	public PanelAnimado(){
		panelContenedor = new PanelOrdenadorContendor();
		panelContenedor.setPreferredSize(new Dimension(WIDTH_ANIMATION, HEIGHT_ANIMATION));
		panelContenedor.setForeground(Color.BLACK);
		setOrdenamiento("Burbujeo");
	}
	
	public void setDelay(int delay) {
		this.delay = delay;
	}
	
	public void setOrdenamiento( String nombreOrdenamiento ) {
		switch (nombreOrdenamiento) {
		case "Burbujeo":
			sp = new PorBurbujeo(delay, WIDTH_ANIMATION, HEIGHT);
			break;
		case "Seleccion":
			sp = new PorSeleccion(delay, WIDTH_ANIMATION, HEIGHT);
		break;
		case "QuickSort":
			sp = new PorQuickSort(delay, WIDTH_ANIMATION, HEIGHT);
		break;
		default: break;
		}
		
		sp.setVisible(true);
		panelContenedor.add(sp);
	}

	public void pintadoAnimacion(int[] list) {
		try {
			repaint();
			sp.setList(list);
			sp.setVisible(true);
			Thread.sleep(1000);
			ExecutorService executor = Executors.newFixedThreadPool(1);
			executor.execute(sp);
			executor.shutdown();
			while (!executor.isTerminated()) {
				Thread.sleep(100);
			}
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void escribirResultado(String tipoOrdenamiento, String tipoArray, int cantElementos, int tiempoGral) {
		try {
			PrintWriter pw = new PrintWriter(new File("Reportes/resultado.out"));
			pw.println("Tipo Ordenamiento: " + tipoOrdenamiento);
			pw.println("Tipo de array: " + tipoArray);
			pw.println("Cantidad de elementos: " + cantElementos);
			pw.println("Tiempo de ejecucion: " + tiempoGral+" segundos");
			pw.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public void seleccionarTipoArray(String tipoArray, String tipoOrdenamiento, int size, int tiempoDemora) {		
		int[] array = generarArray( tipoArray, size );
		setDelay(tiempoDemora);
		pintadoAnimacion(array);
	}
		
	private int[] generarArray(String tipoArray, int size) {
		int[] array = new int[size];
		switch (tipoArray) {
		case "Aleatorio":
			for (int i = 0; i < array.length; i++) {
				array[i] = i + 1;
			}
			for (int i = 0; i < array.length; i++) {
				int index = (int) (Math.random() * array.length);
				int temp = array[i];
				array[i] = array[index];
				array[index] = temp;
			}
			
			break;
		case "Invertido":
			for (int i = 0; i < array.length; i++) {
				array[i] = size - i;
			}
		
			break;
		case "Casi invertido":
			for (int i = 0; i < array.length/2; i++) {
				array[i] = size - i;
			}
			for (int i = array.length/2; i < array.length; i++) {
				array[i] = size;
			}
			
			break;
		case "Ordenado":
			for (int i = 0; i < array.length; i++) {
				array[i] = i + 1;
			}
			
			break;
		case "Casi ordenado":
			for (int i = 0; i < array.length / 2; i++) {
				array[i] = i + 1;
			}
			for (int i = array.length / 2; i < array.length; i++) {
				array[i] = i + 2;
			}
			array[array.length - 1] = array.length / 2 + 1;
			
			break;
		}
		return array;
	}

	public void ejecutarAlgoritmo(String tipoOrdenamiento, String tipoArray, int cantElementos, int tiempoDemora) {
		int tiempoFinal, tiempoGral;
		int tiempoInicio = (int) System.currentTimeMillis();
		
		seleccionarTipoArray(tipoArray, tipoOrdenamiento, cantElementos, tiempoDemora);
		tiempoFinal = (int) System.currentTimeMillis();
		tiempoGral = (tiempoFinal-tiempoInicio) /1000;
		escribirResultado(tipoOrdenamiento, tipoArray, cantElementos, tiempoGral);
		
		System.out.println("Reporte realizado correctamente!");
		System.exit(0);
	}
}