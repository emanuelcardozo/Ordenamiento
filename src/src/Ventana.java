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

public class Ventana extends JApplet {
	private static final long serialVersionUID = 1L;
	private PanelOrdenador sp;
	private int sleepTime;
	private int width = 1200;
	private int height = 500;

	public Ventana(String tipoOrdenamiento, int tiempoDemora) {
		PanelOrdenadorContendor sortPanelHolder = new PanelOrdenadorContendor();
		sortPanelHolder.setPreferredSize(new Dimension(width, height));
		sortPanelHolder.setBackground(Color.BLACK);
		sortPanelHolder.setForeground(Color.BLACK);
		this.sleepTime = tiempoDemora;
		if (tipoOrdenamiento.equals("Burbujeo") || tipoOrdenamiento.equals("Burbuja")
				|| tipoOrdenamiento.equals("Por burbujeo"))
			sp = new PorBurbujeo(sleepTime, width, height);
		else if (tipoOrdenamiento.equals("Seleccion") || tipoOrdenamiento.equals("Por seleccion"))
			sp = new PorSeleccion(sleepTime, width, height);
		else if (tipoOrdenamiento.equals("Quicksort"))
			sp = new PorQuickSort(sleepTime, width, height);
		sp.setVisible(true);
		sortPanelHolder.add(sp);
		add(sortPanelHolder);
	}

	public Ventana() {

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

	public void seleccionarTipoArray(String tipoArray, String tipoOrdenamiento, int size, int tiempoDemora) {
		JFrame frame = new JFrame("Algoritmos de ordenamiento");
		Ventana main = new Ventana(tipoOrdenamiento, tiempoDemora);
		frame.add(main);
		frame.setUndecorated(true);
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		int[] list = new int[size];
		switch (tipoArray) {
		case "Aleatorio":
			for (int i = 0; i < list.length; i++) {
				list[i] = i + 1;
			}
			for (int i = 0; i < list.length; i++) {
				int index = (int) (Math.random() * list.length);
				int temp = list[i];
				list[i] = list[index];
				list[index] = temp;
			}
			main.pintadoAnimacion(list);
			break;
		case "Invertido":
			for (int i = 0; i < list.length; i++) {
				list[i] = size - i;
			}
			main.pintadoAnimacion(list);
			break;
		case "Casi invertido":
			for (int i = 0; i < list.length/2; i++) {
				list[i] = size - i;
			}
			for (int i = list.length/2; i < list.length; i++) {
				list[i] = size;
			}
			main.pintadoAnimacion(list);
			break;
		case "Ordenado":
			for (int i = 0; i < list.length; i++) {
				list[i] = i + 1;
			}
			main.pintadoAnimacion(list);
			break;
		case "Casi ordenado":
			for (int i = 0; i < list.length / 2; i++) {
				list[i] = i + 1;
			}
			for (int i = list.length / 2; i < list.length; i++) {
				list[i] = i + 2;
			}
			list[list.length - 1] = list.length / 2 + 1;
			main.pintadoAnimacion(list);
			break;
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

	public void ejecutarAlgoritmo(String tipoOrdenamiento, String tipoArray, int cantElementos, int tiempoDemora) {
		Ventana ap = new Ventana(tipoOrdenamiento, tiempoDemora);
		int tiempoFinal, tiempoGral;
		int tiempoInicio = (int) System.currentTimeMillis();
		ap.seleccionarTipoArray(tipoArray, tipoOrdenamiento, cantElementos, tiempoDemora);
		tiempoFinal = (int) System.currentTimeMillis();
		tiempoGral = (tiempoFinal-tiempoInicio) /1000;
		ap.escribirResultado(tipoOrdenamiento, tipoArray, cantElementos, tiempoGral);
		System.out.println("Reporte realizado correctamente!");
		System.exit(0);
	}
}