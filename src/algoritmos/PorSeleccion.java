package algoritmos;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Arrays;
import src.PanelOrdenador;

public class PorSeleccion extends PanelOrdenador implements Estrategia {
	private static final long serialVersionUID = 1L;
	private int columnaRoja = -1;
	private int columnaVerde = -1;
	private int columnaAzul = -1;
	private int cantComparaciones = 0;
	private int cantIntercambios = 0;
	private long timeStart = 0;

	public PorSeleccion(int sleepTime, int width, int height) {
		super("Seleccion", sleepTime, width, height);
	}
	
	@Override
	public int[] ordenar(int[] arregloInmutable) {
		timeStart = System.currentTimeMillis();
		int[] arreglo = Arrays.copyOf(arregloInmutable, arregloInmutable.length);
		arreglo = list;
		int menor;
		int i, j, posicionDelMenor;
		try {
			for (i = 0; i < arreglo.length - 1; i++) {
				menor = arreglo[i];
				posicionDelMenor = i;
				columnaRoja = posicionDelMenor;
				for (j = i + 1; j < arreglo.length; j++) {
					columnaAzul = j; 
					repaint();
					Thread.sleep(sleepTime);
					if ((arreglo[j] - (menor)) < 0) {
						menor = arreglo[j];
						posicionDelMenor = j;
						columnaRoja = posicionDelMenor;
						repaint();
					}
					cantComparaciones++;
				}
				if (posicionDelMenor != i) {
					intercambiar(arreglo, i, posicionDelMenor);
					Thread.sleep(sleepTime);
				}
				columnaVerde++;
				repaint();
			}
			columnaVerde++;
			columnaRoja=-1;
			columnaAzul=-1;
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		repaint();
		return arreglo;
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
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		int columnWidth = (getWidth() - 4 * BORDER_WIDTH) / size;
		int columnHeight = (getHeight() - 4 * BORDER_WIDTH) / size;
		for (int i = (columnaVerde == -1 ? 0 : columnaVerde); i < list.length; i++) {
			g.setColor(Color.WHITE);
			g.fillRect(2 * BORDER_WIDTH + columnWidth * i, getHeight() - list[i] * columnHeight - 2 * BORDER_WIDTH, columnWidth, list[i] * columnHeight);
			g.setColor(Color.BLACK);
			g.drawRect(2 * BORDER_WIDTH + columnWidth * i, getHeight() - list[i] * columnHeight - 2 * BORDER_WIDTH, columnWidth, list[i] * columnHeight);			
		}
		for (int i = 0; i <= columnaVerde; i++) {
			g.setColor(Color.GREEN);
			g.fillRect(2 * BORDER_WIDTH + columnWidth * i, getHeight() - list[i] * columnHeight - 2 * BORDER_WIDTH, columnWidth, list[i] * columnHeight);
			g.setColor(Color.BLACK);
			g.drawRect(2 * BORDER_WIDTH + columnWidth * i, getHeight() - list[i] * columnHeight - 2 * BORDER_WIDTH, columnWidth, list[i] * columnHeight);			
		}
		
		if(columnaRoja != -1) {
			g.setColor(Color.RED);
			g.fillRect(2 * BORDER_WIDTH + columnWidth * columnaRoja, getHeight() - list[columnaRoja] * columnHeight - 2 * BORDER_WIDTH, columnWidth, list[columnaRoja] * columnHeight);
			g.setColor(Color.BLACK);
			g.drawRect(2 * BORDER_WIDTH + columnWidth * columnaRoja, getHeight() - list[columnaRoja] * columnHeight - 2 * BORDER_WIDTH, columnWidth, list[columnaRoja] * columnHeight);
		}
		
		if(columnaAzul != -1) {
			g.setColor(Color.BLUE);
			g.fillRect(2 * BORDER_WIDTH + columnWidth * columnaAzul, getHeight() - list[columnaAzul] * columnHeight - 2 * BORDER_WIDTH, columnWidth, list[columnaAzul] * columnHeight);
			g.setColor(Color.BLACK);
			g.drawRect(2 * BORDER_WIDTH + columnWidth * columnaAzul, getHeight() - list[columnaAzul] * columnHeight - 2 * BORDER_WIDTH, columnWidth, list[columnaAzul] * columnHeight);
		}
		
		g.setColor(Color.RED);
		g.drawString("Comparaciones:" + cantComparaciones, 30, 30);
		g.drawString("Intercambios:" + cantIntercambios, 30, 60);
		long time = timeStart == 0 ? timeStart : System.currentTimeMillis() - timeStart;
		g.drawString("Tiempo:" + time + " ms" , 30, 90);
	}

}