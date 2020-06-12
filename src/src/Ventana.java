package src;

import javax.swing.*;

import utils.GeneradorDeArray;
import utils.GeneradorPanelOrdenador;

import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@SuppressWarnings("deprecation")
public class Ventana extends JApplet implements Runnable {
	private static final long serialVersionUID = 1L;
	
	private final int WIDTH = 900;
	private final int HEIGHT = 600;
	
	private PanelOrdenador sp;
	private int delay;
	private int cantElementos;
	private String tipoOrdenamiento;
	private String tipoArray;
	

	public Ventana(String tipoOrdenamiento, String tipoArray, int cantElementos, int tiempoDemora) {
		this.delay = tiempoDemora;
		this.tipoArray = tipoArray;
		this.cantElementos = cantElementos;
		this.tipoOrdenamiento = tipoOrdenamiento;
		this.sp = GeneradorPanelOrdenador.get( tipoOrdenamiento, WIDTH, HEIGHT, delay );
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

	public void mostrarVentana() {
		JFrame frame = new JFrame("Algoritmos de ordenamiento");

		frame.add(this);
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		
		pintadoAnimacion(GeneradorDeArray.get(tipoArray, cantElementos));	
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

	@Override
	public void run() {
		int tiempoFinal, tiempoGral;
		int tiempoInicio = (int) System.currentTimeMillis();
		
		mostrarVentana();
		tiempoFinal = (int) System.currentTimeMillis();
		tiempoGral = (tiempoFinal-tiempoInicio) /1000;
		
		escribirResultado(tipoOrdenamiento, tipoArray, cantElementos, tiempoGral);
		
		System.out.println("Reporte realizado correctamente!");
		System.exit(0);
		
	}
}