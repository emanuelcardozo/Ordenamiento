package constantes;

public enum TipoDeArray {
	ORDENADO("Ordenado"),
	CASI_ORDENADO("Casi ordenado"),
	ALEATORIO("Aleatorio"),
	CASI_INVERSO("Casi invertido"),
	INVERSO("Invertido");
	
	private String titulo;
	
	TipoDeArray(String titulo){
		this.titulo = titulo;
	}
	
	@Override
	public String toString() {
		return titulo;
	}
}
