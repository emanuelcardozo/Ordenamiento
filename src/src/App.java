package src;

import javax.swing.*;

import java.awt.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
//Visualization and Comparison of Sorting Algorithms
public class App extends JApplet {
	private static final long serialVersionUID = 1L;
	private SortPanel sp;
	private static int size = 50;
	private int sleepTime = 10;
	private int width = 500;
    private	int height = 200;
 

	public App() {
		int width = 500;
		int height = 200;
		SortPanelsHolder sortPanelHolder = new SortPanelsHolder();
		sortPanelHolder.setPreferredSize(new Dimension(width, height));
		sortPanelHolder.setBackground(Color.BLACK);
		sortPanelHolder.setForeground(Color.BLACK);
		sp = new PorQuickSort("QuickcSort", sleepTime, width, height);
		sp.setVisible(true);
		sortPanelHolder.add(sp);
		add(sortPanelHolder);	
	}

	class SortPanelsHolder extends JPanel {
		private static final long serialVersionUID = 1L;
		@Override
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);
			g.setColor(Color.WHITE);
		}
	}
	
	public void beginAnimation(int[] list) {
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
			main.beginAnimation(list);
			break;
		case "Invertido":
			for (int i = 0; i < list.length; i++) {
				list[i] = size - i;
			}
			main.beginAnimation(list);
			break;
		case "Casi invertido":
			break;
		case "Ordenado":
			for (int i = 0; i < list.length; i++) {
				list[i] = i + 1;
			}
			main.beginAnimation(list);
			break;
		case "Casi ordenado":
			for (int i = 0; i < list.length / 2; i++) {
				list[i] = i + 1;
			}
			for (int i = list.length / 2; i < list.length; i++) {
				list[i] = i + 2;
			}
			list[list.length - 1] = list.length / 2 + 1;
			main.beginAnimation(list);
			break;
		}
	}
	
	public static void main(String[] args) {
		App ap = new App();
		ap.seleccionarTipoArray("Aleatorio");
	}
}