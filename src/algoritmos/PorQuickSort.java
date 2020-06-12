package algoritmos;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Arrays;
import src.PanelOrdenador;


public class PorQuickSort<T extends Comparable<T>> extends PanelOrdenador implements Estrategia<T> {
	private static final long serialVersionUID = 1L;
	private int columnaRoja = -1;
	private int columnaVerde = -1;
	private int columnaAzul = -1;
	private int columnaCyan = -1;
	private int cantComparaciones = 0;
	private int cantIntercambios = 0;
	private long timeStart = 0;
	private int contadorAzul = 0;

	public PorQuickSort(int sleepTime, int width, int height) {
		super("QuickSort", sleepTime, width, height);
	}

	public int[] ordenar(final int[] arregloInmutable) {
		int[] arreglo = Arrays.copyOf(arregloInmutable, arregloInmutable.length);
		arreglo = list;
		timeStart = System.currentTimeMillis();
		ordenarQ(arreglo, 0, arreglo.length - 1);
		columnaVerde = arreglo.length-1;
		columnaAzul = -1;
		columnaRoja = -1;
		repaint();

		return arreglo;
	}

	private int partition(int arreglo[], int inferior, int superior) {
		try {
			Thread.sleep(sleepTime);
			repaint();
			int pivot = arreglo[superior];
			columnaRoja = inferior + (superior - inferior) / 2;
			int i = (inferior - 1);
			for (int j = inferior; j < superior; j++) {
				cantComparaciones++;
				Thread.sleep(sleepTime);
				repaint();
				if ((arreglo[j] - (pivot)) < 0) {
					i++;
					columnaAzul = i;
					
					Thread.sleep(sleepTime);
					repaint();
					intercambiar(arreglo, i, j);
				}				
				
				columnaRoja = j;
					
				repaint();
			}
			intercambiar(arreglo, i + 1, superior);
			return i + 1;
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return 0;
	}

	private void ordenarQ(int arreglo[], int inferior, int superior) {
		cantComparaciones++;
		if (inferior < superior) {
			int pivot = partition(arreglo, inferior, superior);
			columnaAzul = inferior;
			
			if( columnaVerde < inferior ) {
				columnaVerde = inferior-1;
			}
			
			repaint();
			ordenarQ(arreglo, inferior, pivot - 1);
			ordenarQ(arreglo, pivot + 1, superior);
			
			
				
			
		}
	}

	@Override
	public void intercambiar(int[] arreglo, int i, int j) {
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
		columnaRoja = -1;
		columnaVerde = -1;
		columnaAzul = -1;
		columnaCyan = -1;
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		int columnWidth = (getWidth() - 4 * BORDER_WIDTH) / size;
		int columnHeight = (getHeight() - 4 * BORDER_WIDTH) / size;
		for (int i = (columnaVerde == -1 ? 0 : columnaVerde); i < list.length; i++) {
			g.setColor(Color.WHITE);
			g.fillRect(2 * BORDER_WIDTH + columnWidth * i, getHeight() - list[i] * columnHeight - 2 * BORDER_WIDTH,
					columnWidth, list[i] * columnHeight);
			g.setColor(Color.BLACK);
			g.drawRect(2 * BORDER_WIDTH + columnWidth * i, getHeight() - list[i] * columnHeight - 2 * BORDER_WIDTH,
					columnWidth, list[i] * columnHeight);
		}
		for (int i = 0; i <= columnaVerde; i++) {
			g.setColor(Color.GREEN);
			g.fillRect(2 * BORDER_WIDTH + columnWidth * i, getHeight() - list[i] * columnHeight - 2 * BORDER_WIDTH,
					columnWidth, list[i] * columnHeight);
			g.setColor(Color.BLACK);
			g.drawRect(2 * BORDER_WIDTH + columnWidth * i, getHeight() - list[i] * columnHeight - 2 * BORDER_WIDTH,
					columnWidth, list[i] * columnHeight);
		}
		if (columnaRoja != -1) {
			g.setColor(Color.RED);
			g.fillRect(2 * BORDER_WIDTH + columnWidth * columnaRoja,
					getHeight() - list[columnaRoja] * columnHeight - 2 * BORDER_WIDTH, columnWidth,
					list[columnaRoja] * columnHeight);
			g.setColor(Color.BLACK);
			g.drawRect(2 * BORDER_WIDTH + columnWidth * columnaRoja,
					getHeight() - list[columnaRoja] * columnHeight - 2 * BORDER_WIDTH, columnWidth,
					list[columnaRoja] * columnHeight);
		}
		if (columnaAzul != -1) {
			g.setColor(Color.BLUE);
			g.fillRect(2 * BORDER_WIDTH + columnWidth * columnaAzul,
					getHeight() - list[columnaAzul] * columnHeight - 2 * BORDER_WIDTH, columnWidth,
					list[columnaAzul] * columnHeight);
			g.setColor(Color.BLACK);
			g.drawRect(2 * BORDER_WIDTH + columnWidth * columnaAzul,
					getHeight() - list[columnaAzul] * columnHeight - 2 * BORDER_WIDTH, columnWidth,
					list[columnaAzul] * columnHeight);
		}
		if (columnaCyan != -1) {
			g.setColor(Color.CYAN);
			g.fillRect(2 * BORDER_WIDTH + columnWidth * columnaCyan,
					getHeight() - list[columnaCyan] * columnHeight - 2 * BORDER_WIDTH, columnWidth,
					list[columnaAzul] * columnHeight);
			g.setColor(Color.BLACK);
			g.drawRect(2 * BORDER_WIDTH + columnWidth * columnaCyan,
					getHeight() - list[columnaCyan] * columnHeight - 2 * BORDER_WIDTH, columnWidth,
					list[columnaAzul] * columnHeight);
		}
		g.setColor(Color.RED);
		g.drawString("Comparaciones:" + cantComparaciones, 30, 30);
		g.drawString("Intercambios:" + cantIntercambios, 30, 60);
		long time = timeStart == 0 ? timeStart : System.currentTimeMillis() - timeStart;
		g.drawString("Tiempo:" + time + " ms" , 30, 90);
	}
}