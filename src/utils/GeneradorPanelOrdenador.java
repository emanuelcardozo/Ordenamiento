package utils;

import algoritmos.PorBurbujeo;
import algoritmos.PorQuickSort;
import algoritmos.PorSeleccion;
import src.PanelOrdenador;

public class GeneradorPanelOrdenador {

	public static PanelOrdenador get( String tipoOrdenamiento, int width, int height, int delay ) {
		if (tipoOrdenamiento.equals("Burbujeo") || tipoOrdenamiento.equals("Burbuja")
				|| tipoOrdenamiento.equals("Por burbujeo"))
			return new PorBurbujeo(delay, width, height);
		else if (tipoOrdenamiento.equals("Seleccion") || tipoOrdenamiento.equals("Por seleccion"))
			return new PorSeleccion(delay, width, height);
		
		return new PorQuickSort(delay, width, height);
	}
}
