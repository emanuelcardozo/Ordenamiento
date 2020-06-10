package src;

import javax.swing.*;

import java.awt.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
//Visualization and Comparison of Sorting Algorithms
public class App extends JApplet {
	private static final long serialVersionUID = 1L;
	private SortPanel sp;
	private static int size = 100;
	private int sleepTime = 2;


	public App() {
		setLayout(new GridLayout(0, 1, 0, 0));
		SortPanelsHolder sortPanelHolder = new SortPanelsHolder();
		sortPanelHolder.setLayout(new  GridLayout(10, 10, 0, 0));
		sortPanelHolder.setBackground(Color.BLACK);
		sortPanelHolder.setForeground(Color.BLACK);
		int width = 500;
		int height = 200;
		sp = new BubbleSortPanel(" Ordenamiento Burbuja ", sleepTime, width, height);
		sp.setVisible(false);
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
			sp.setVisible(false);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		JFrame frame = new JFrame("Algoritmos de ordenamiento");
		App main = new App();
		frame.add(main);
		frame.setUndecorated(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		
		
		int[] list = new int[size];
		
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
		
		for (int i = 0; i < list.length; i++) {
			list[i] = (1 + i / (size / 4) ) * (size / 4);
		}
		for (int i = 0; i < list.length; i++) {
			int index = (int) (Math.random() * list.length);
			int temp = list[i];
			list[i] = list[index];
			list[index] = temp;
		}
		main.beginAnimation(list);

		
		for (int i = 0; i < list.length; i++) {
			list[i] = size - i;
		}
		main.beginAnimation(list);
		
		
		for (int i = 0; i < list.length / 2; i++) {
			list[i] = i + 1;
		}
		for (int i = list.length / 2; i < list.length; i++) {
			list[i] = i + 2;
		}
		list[list.length - 1] = list.length / 2 + 1;
		main.beginAnimation(list);
	}
}