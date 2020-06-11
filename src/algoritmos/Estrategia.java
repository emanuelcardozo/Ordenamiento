package algoritmos;

public interface Estrategia<T extends Comparable<T>> {

	public abstract int[] ordenar(final int[] arregloInmutable);

	public abstract void intercambiar(int[] arreglo, int i, int j);
}