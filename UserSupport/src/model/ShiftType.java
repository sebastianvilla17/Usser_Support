package model;

import java.time.LocalTime;

public class ShiftType {

	private String name = "";
	private LocalTime inicio;
	private LocalTime fin;

	public ShiftType(String name, LocalTime inicio, LocalTime fin) {
		this.name = name;
		this.inicio = inicio;
		this.fin = fin;
	}

	public String getName() {
		return name;
	}

	public LocalTime getInicio() {
		return inicio;
	}

	public LocalTime getFin() {
		return fin;
	}

}
