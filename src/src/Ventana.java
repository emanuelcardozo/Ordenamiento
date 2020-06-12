package src;

import javax.swing.*;

import algoritmos.PorBurbujeo;
import algoritmos.PorQuickSort;
import algoritmos.PorSeleccion;
import utils.GeneradorDeArray;
import utils.GeneradorPanelOrdenador;

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
	private final int WIDTH = 900;
	private final int HEIGHT = 600;

	public Ventana(String tipoOrdenamiento, int tiempoDemora) {
		this.sleepTime = tiempoDemora;
		this.sp = GeneradorPanelOrdenador.get( tipoOrdenamiento, WIDTH, HEIGHT, sleepTime );
		init();
	}
	
	public void init() {
		PanelOrdenadorContendor sortPanelHolder = new PanelOrdenadorContendor();
		sortPanelHolder.setPreferredSize(new Dimension(WIDTH, HEIGHT));
		sortPanelHolder.setBackground(Color.BLACK);
		sortPanelHolder.setForeground(Color.BLACK);
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
//		frame.setUndecorated(true);
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		
		main.pintadoAnimacion(GeneradorDeArray.get(tipoArray, size));
		
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