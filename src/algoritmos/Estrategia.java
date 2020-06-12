package algoritmos;

public interface Estrategia {

	public abstract int[] ordenar(final int[] arregloInmutable);

	public abstract void intercambiar(int[] arreglo, int i, int j);
}