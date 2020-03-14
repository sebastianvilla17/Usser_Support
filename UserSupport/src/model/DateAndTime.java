package model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Calendar;

public class DateAndTime {

	public static DateAndTime dateAndTimeUnico = new DateAndTime();
	private LocalDate actualizaFecha;
	private LocalTime actualiza;
	private int horaF;
	private int minutoF;
	private int segundoF;
	int anioF;
	int mesF;
	int diaF;

	private DateAndTime() {

	}

	public static DateAndTime ObtenerDateAndTimeUnico() {
		return dateAndTimeUnico;
	}

	public LocalDate fecha() {
		LocalDate date = LocalDate.now();
		return date;
	}

	public LocalTime hora() {
		LocalTime time = LocalTime.now();
		return time;
	}

	public LocalTime formatearHora(int horaA, int minutoA, int segundoA) {

		LocalTime sistema = hora();
		int hora = sistema.getHour();
		int minuto = sistema.getMinute();
		int segundo = sistema.getSecond();

		horaF = horaA - hora;
		minutoF = minutoA - minuto;
		segundoF = segundoA - segundo;

		actualiza = sistema.plusHours(horaF).plusMinutes(minutoF).plusSeconds(segundoF);

		return actualiza;

	}

	public LocalDate formatearFecha(int anioA, int mesA, int diaA) {

		LocalDate sistema = fecha();
		int anio = sistema.getYear();
		int mes = sistema.getMonthValue();
		int dia = sistema.getDayOfMonth();

		anioF = anioA - anio;
		mesF = mesA - mes;
		diaF = diaA - dia;

		actualizaFecha = sistema.plusYears(anioF).plusMonths(mesF).plusDays(diaF);
		return actualizaFecha;
	}

	public LocalTime mantenerFecha() {

		LocalTime sistema = hora();

		LocalTime total = sistema.plusHours(horaF).plusMinutes(minutoF).plusSeconds(segundoF);

		return total;
	}

	public LocalDate fechaActual() {

		LocalDate date = fecha();

		LocalDate result = date.plusYears(anioF).plusMonths(mesF).plusDays(diaF);
		return result;
	}

}
