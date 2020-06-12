package algoritmos;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Arrays;
import src.PanelOrdenador;


public class PorBurbujeo extends PanelOrdenador implements Estrategia {
	private static final long serialVersionUID = 1L;
	private int columnaRoja1 = -1;
	private int columnaRoja2 = -1;
	private int columnaVerde = -1;
	private int cantComparaciones = 0;
	private int cantIntercambios = 0;
	private long timeStart = 0;

	public PorBurbujeo(int sleepTime, int width, int height) {
		super("Burbujeo", sleepTime, width, height);
	}

	@Override
	public int[] ordenar(int[] arregloInmutable) {
		timeStart = System.currentTimeMillis();
		int[] arreglo = Arrays.copyOf(arregloInmutable, arregloInmutable.length);
		list = arreglo;
		int cantidadVerdes = 0;
		try {
			boolean huboCambio = false;
			columnaVerde = list.length;
			do {
				huboCambio = false;
				for (int i = 0; i < arreglo.length - 1; i++) {
					columnaRoja1 = i;
					columnaRoja2 = i+1;
					repaint();
					Thread.sleep(sleepTime);
					
					if ((arreglo[i] - (arreglo[i + 1])) > 0) {
						intercambiar(arreglo, i, i + 1);
						repaint();
						Thread.sleep(sleepTime);
						huboCambio = true;
					}
					cantComparaciones++;
					columnaRoja1 = -1;
					columnaRoja2 = -1;
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
		columnaRoja1 = j;
		columnaRoja2 = i;
		int temporal = arreglo[i];
		arreglo[i] = arreglo[j];
		arreglo[j] = temporal;
		cantIntercambios++;
	}

	@Override
	public void run() {
		ordenar(list);
	}

	@Override
	public void reset() {
		columnaRoja1 = -1;
		columnaRoja2 = -1;
		columnaVerde = -1;
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		int columnWidth = (getWidth() - 4 * BORDER_WIDTH) / size;
		int columnHeight = (getHeight() - 4 * BORDER_WIDTH) / size;
		
		for (int i = 0; i < (columnaVerde == -1 ? list.length : columnaVerde); i++) {
			g.setColor(Color.WHITE);
			g.fillRect(2 * BORDER_WIDTH + columnWidth * i, getHeight() - list[i] * columnHeight - 2 * BORDER_WIDTH,
					columnWidth, list[i] * columnHeight);
			g.setColor(Color.BLACK);
			g.drawRect(2 * BORDER_WIDTH + columnWidth * i, getHeight() - list[i] * columnHeight - 2 * BORDER_WIDTH,
					columnWidth, list[i] * columnHeight);
		}
		if (columnaVerde != -1) {
			for (int i = columnaVerde; i < list.length; i++) {
				g.setColor(Color.GREEN);
				g.fillRect(2 * BORDER_WIDTH + columnWidth * i, getHeight() - list[i] * columnHeight - 2 * BORDER_WIDTH,
						columnWidth, list[i] * columnHeight);
				g.setColor(Color.BLACK);
				g.drawRect(2 * BORDER_WIDTH + columnWidth * i, getHeight() - list[i] * columnHeight - 2 * BORDER_WIDTH,
						columnWidth, list[i] * columnHeight);
		}
		if (columnaRoja1 != -1) {
			g.setColor(Color.RED);
			g.fillRect(2 * BORDER_WIDTH + columnWidth * columnaRoja1,
					getHeight() - list[columnaRoja1] * columnHeight - 2 * BORDER_WIDTH, columnWidth,
					list[columnaRoja1] * columnHeight);
			g.setColor(Color.BLACK);
			g.drawRect(2 * BORDER_WIDTH + columnWidth * columnaRoja1,
					getHeight() - list[columnaRoja1] * columnHeight - 2 * BORDER_WIDTH, columnWidth,
					list[columnaRoja1] * columnHeight);
		}
		
		if (columnaRoja2 != -1) {
			g.setColor(Color.RED);
			g.fillRect(2 * BORDER_WIDTH + columnWidth * columnaRoja2,
					getHeight() - list[columnaRoja2] * columnHeight - 2 * BORDER_WIDTH, columnWidth,
					list[columnaRoja2] * columnHeight);
			g.setColor(Color.BLACK);
			g.drawRect(2 * BORDER_WIDTH + columnWidth * columnaRoja2,
					getHeight() - list[columnaRoja2] * columnHeight - 2 * BORDER_WIDTH, columnWidth,
					list[columnaRoja2] * columnHeight);
		}
		
		g.setColor(Color.RED);
		g.drawString("Comparaciones:" + cantComparaciones, 30, 30);
		g.drawString("Intercambios:" + cantIntercambios, 30, 60);
		long time = timeStart == 0 ? timeStart : System.currentTimeMillis() - timeStart;
		g.drawString("Tiempo:" + time + " ms" , 30, 90);
		}
		
	}
}
