package model;

public class ShiftType {
	
	private String name="";
	private String inicio;
	private String fin;
	
	public ShiftType(String name, String inicio, String fin) {
		this.name = name;
		this.inicio = inicio;
		this.fin = fin;
	}

	public String getName() {
		return name;
	}

	public String getInicio() {
		return inicio;
	}

	public String getFin() {
		return fin;
	}

}
