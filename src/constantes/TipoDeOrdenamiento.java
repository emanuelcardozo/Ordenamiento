package constantes;

public enum TipoDeOrdenamiento {
	BURBUJEO("Burbujeo"),
//	INSERCION("Insercion"),
	SELECCION("Seleccion"),
//	MERGE_SORT("MergeSort"),
	QUICK_SORT("QuickSort");
//	SHELL_SORT("ShellSort");
	
	private String titulo;
	
	TipoDeOrdenamiento(String titulo){
		this.titulo = titulo;
	}
	
	@Override
	public String toString() {
		return titulo;
	}
}
