package src;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Arrays;

public class PorBurbujeo<T extends Comparable<T>> extends SortPanel implements Estrategia<T> {
	private static final long serialVersionUID = 100L;
	private int columnaRoja = -1;
	private int columnaVerde = -1;
	private int cantComparaciones = 0;
	private int cantIntercambios = 0;
	private long tiempo;
	
	
	public PorBurbujeo(int sleepTime, int width, int height) {
		super("Burbujeo", sleepTime, width, height);
	}

	@Override
	public int[] ordenar(int[] arregloInmutable) {
		int[] arreglo = Arrays.copyOf(arregloInmutable, arregloInmutable.length);
		list = arreglo;
		int cantidadVerdes = 0;
		try {
			boolean huboCambio = false;
			columnaVerde = list.length;
			do {
				huboCambio = false;
				for (int i = 0; i < arreglo.length - 1; i++) {
					columnaRoja = i;
					repaint();
					Thread.sleep(4 * sleepTime);
					if ((arreglo[i] - (arreglo[i + 1])) > 0) {
						intercambiar(arreglo, i, i + 1);
						repaint();
						Thread.sleep(4 * sleepTime);
						huboCambio = true;
					}
					cantComparaciones++;
					columnaRoja = -1;
				}
				cantidadVerdes++;
				columnaVerde = list.length - cantidadVerdes;
				
				repaint();
			} while (huboCambio);
		} catch (InterruptedException e) {
		}
		columnaVerde = 0;
		repaint();
		return arreglo;
	}

	@Override
	public void intercambiar(int[] arreglo, int i, int j) {
		columnaRoja = j;
		int temporal = arreglo[i];
		arreglo[i] = arreglo[j];
		arreglo[j] = temporal;
		cantIntercambios++;
	}

	@Override
	public void run() {
		ordenar(list);
		System.out.println(cantComparaciones);
		System.out.println(cantIntercambios);
	}

	@Override
	public void reset() {
		columnaRoja = -1;
		columnaVerde = -1;
	}
	
	@Override
	protected void paintComponent(Graphics g) {		
		super.paintComponent(g);
		int columnWidth = (getWidth() - 4 * BORDER_WIDTH) / size;
		int columnHeight = (getHeight() - 4 * BORDER_WIDTH) / size;
		for (int i = 0; i < (columnaVerde == -1 ? list.length : columnaVerde); i++) {
			g.setColor(Color.WHITE);
			g.fillRect(2 * BORDER_WIDTH + columnWidth * i, getHeight() - list[i] * columnHeight - 2 * BORDER_WIDTH, columnWidth, list[i] * columnHeight);
			g.setColor(Color.BLACK);
			g.drawRect(2 * BORDER_WIDTH + columnWidth * i, getHeight() - list[i] * columnHeight - 2 * BORDER_WIDTH, columnWidth, list[i] * columnHeight);			
		}
		if(columnaVerde != -1) {
			for (int i = columnaVerde; i < list.length; i++) {
				g.setColor(Color.GREEN);
				g.fillRect(2 * BORDER_WIDTH + columnWidth * i, getHeight() - list[i] * columnHeight - 2 * BORDER_WIDTH, columnWidth, list[i] * columnHeight);
				g.setColor(Color.BLACK);
				g.drawRect(2 * BORDER_WIDTH + columnWidth * i, getHeight() - list[i] * columnHeight - 2 * BORDER_WIDTH, columnWidth, list[i] * columnHeight);			
			}
		}
		if(columnaRoja != -1) {
			g.setColor(Color.RED);
			g.fillRect(2 * BORDER_WIDTH + columnWidth * columnaRoja, getHeight() - list[columnaRoja] * columnHeight - 2 * BORDER_WIDTH, columnWidth, list[columnaRoja] * columnHeight);
			g.setColor(Color.BLACK);
			g.drawRect(2 * BORDER_WIDTH + columnWidth * columnaRoja, getHeight() - list[columnaRoja] * columnHeight - 2 * BORDER_WIDTH, columnWidth, list[columnaRoja] * columnHeight);
		}
		g.setColor(Color.RED);
		g.drawString("Comparaciones:" + cantComparaciones, 10, 30);
		g.drawString("Intercambios:" + cantIntercambios, 210, 30);
		g.drawString("Tiempo:" + tiempo, 390, 30);
	}


	

}
