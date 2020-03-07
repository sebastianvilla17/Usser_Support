package model;

import java.util.Calendar;

public class DateAndTime {

	Calendar fecha = Calendar.getInstance();

	int anio;
	int mes;
	int dia;
	int hora;
	int minuto;
	int segundo;

	public DateAndTime() {
		anio = fecha.get(Calendar.YEAR);
		mes = fecha.get(Calendar.MONTH) + 1;
		dia = fecha.get(Calendar.DAY_OF_MONTH);

		hora = fecha.get(Calendar.HOUR_OF_DAY);
		minuto = fecha.get(Calendar.MINUTE);
		segundo = fecha.get(Calendar.SECOND);
	}

	public String darHora() {
		hora = fecha.get(Calendar.HOUR_OF_DAY);
		minuto = fecha.get(Calendar.MINUTE);
		segundo = fecha.get(Calendar.SECOND);
		
		return hora + " " + minuto + " " + segundo;
	}
	
	public int getAnio() {
		return anio;
	}


	public int getMes() {
		return mes;
	}


	public int getDia() {
		return dia;
	}


	public int getHora() {
		return hora;
	}


	public int getMinuto() {
		return minuto;
	}


	public int getSegundo() {
		return segundo;
	}


	public String DateAndTIME(int anioA, int mesA, int diaA, int horaA, int minutoA, int segundoA) {

		String time = formattedTime(horaA, minutoA, segundoA);
		String date = formattedDate(anioA, mesA, diaA);

		return date + "/////" + time;
	}

	public String dateActual(int anioA, int mesA, int diaA) {

		int anioF = anioA - anio;
		int mesF = mesA - mes;
		int diaF = diaA - dia;

		return anioF + " " + mesF + " " + diaF;
	}

	public String timeActual(int horaA, int minutoA, int segundoA) {

		String result = darHora();
		String[] array = result.split(" ");
		for (int i = 0; i < array.length; i++) {
			hora = Integer.parseInt(array[0]);
			minuto = Integer.parseInt(array[1]);
			segundo = Integer.parseInt(array[2]);
		}
		int horaF = horaA - hora;
		int minutoF = minutoA - minuto;
		int segundoF = segundoA - segundo;

		return  horaF + " " + minutoF + " " + segundoF;
	}

	public String formattedDate(int anioA, int mesA, int diaA) {

		int anioF = 0;
		int mesF = 0;
		int diaF = 0;
		String result = dateActual(anioA, mesA, diaA);
		String[] array = result.split(" ");
		for (int i = 0; i < array.length; i++) {
			anioF = Integer.parseInt(array[0]);
			mesF = Integer.parseInt(array[1]);
			diaF = Integer.parseInt(array[2]);
		}
		int anioS = anioF + anio;

		int mesS = mesF + mes;

		int diaS = diaF + dia;

		return " fecha :" + anioS + "/" + mesS + "/" + diaS;
	}

	public String formattedTime(int horaA, int minutoA, int segundoA) {

		int horaF = 0;
		int minutoF = 0;
		int segundoF = 0;
		String result = timeActual(horaA, minutoA, segundoA);
		String[] array = result.split(" ");
		for (int i = 0; i < array.length; i++) {
			horaF = Integer.parseInt(array[0]);
			minutoF = Integer.parseInt(array[1]);
			segundoF = Integer.parseInt(array[2]);
		}
		int horaS = horaF + hora;
		int minutoS = minutoF + minuto;
		int segundoS = segundoF + segundo;

		return " hora : " + horaS + ":" + minutoS + ":" + segundoS;
	}

}
