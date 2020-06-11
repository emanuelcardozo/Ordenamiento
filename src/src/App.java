package src;

import javax.swing.*;

import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
//Visualization and Comparison of Sorting Algorithms
public class App extends JApplet {
	private static final long serialVersionUID = 1L;
	private SortPanel sp;
	private static int size = 50;
	private int sleepTime = 10;
	private int width = 700;
    private	int height = 200;
    private String tipoArray="";
    private String tipoOrdenamiento="";

	public App() {
		int width = 500;
		int height = 200;
		SortPanelsHolder sortPanelHolder = new SortPanelsHolder();
		sortPanelHolder.setPreferredSize(new Dimension(width, height));
		sortPanelHolder.setBackground(Color.BLACK);
		sortPanelHolder.setForeground(Color.BLACK);
		sp = new PorBurbujeo(sleepTime, width, height);
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
			while(!executor.isTerminated()) {
				Thread.sleep(100);
			}
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void seleccionarTipoArray(String tipoArray) {
		JFrame frame = new JFrame("Algoritmos de ordenamiento");
		App main = new App();
		this.tipoArray = tipoArray;
		frame.add(main);
		frame.setUndecorated(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
	
	public void escribirResultado() {
		try {
			PrintWriter pw = new PrintWriter (new File("resultado.out"));
			pw.print(sp.getName()+" "+this.tipoArray+" "+this.size+" "+0);
			pw.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		App ap = new App();
		ap.seleccionarTipoArray("Aleatorio");
		ap.escribirResultado();
	}
}