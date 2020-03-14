package model;

import model.User;

import java.time.LocalTime;

import model.DateAndTime;

public class Shift {

	private int idTurno;
	private String turnoCodificado;
	private char status;
	private char coment;
	private int posicionUser;
	private double horaI;
	private double horaF;
	private ShiftType type;
	private LocalTime startTime;
	private LocalTime endTime;
	public final static char ATTENDED = 'A';
	public final static char NO_ATTENDED = 'N';
	public final static char NO_USER = 'O';
	public final static char USER = 'U';

	public Shift(int posicionUser, int turno, String shiftName, LocalTime startTime, LocalTime endTime) {

		this.posicionUser = posicionUser;
		this.idTurno = turno;
		this.turnoCodificado = convertirTurno(turno);
		this.status = 'N';
		this.coment = 'O';

		this.type = new ShiftType(shiftName, startTime, endTime);

	}

	/**
	 * addUser
	 * <p>
	 * desc:
	 * </p>
	 * This method is responsible for coding the turn, to assign it to the user
	 *
	 * @param turno This parameter refers to the size of the user list
	 *
	 * @return this method returns the code of the shift
	 */
	public String convertirTurno(int turno) {
		final int ASCCI_INICIAL = 65;
		int ascciLetra = 0;
		int posicionLetra = 0;
		int posicion = 0;
		String turnoConvertido = "";

		posicion = Math.floorMod(turno, 100);
		posicionLetra = Math.floorDiv(turno, 100);
		posicionLetra = Math.floorMod(posicionLetra, 26); // (posicionLetra>25)? posicionLetra-25 : posicionLetra;

		ascciLetra = ASCCI_INICIAL + posicionLetra;

		turnoConvertido = "" + (char) (ascciLetra) + String.format("%02d", posicion);

		return turnoConvertido;
	}

	public int getPosicion() {
		return posicionUser;
	}

	public double getHoraI() {
		return horaI;
	}

	public double getHoraF() {
		return horaF;
	}

	/**
	 * getStatus
	 * <p>
	 * desc:
	 * </p>
	 * This method allows to obtain private data
	 *
	 * @return this method returns a type data String whit the status of the shift
	 */

	public char getStatus() {
		return status;
	}

	/**
	 * setStatus
	 * <p>
	 * desc:
	 * </p>
	 * This method allows you to modify an attribute of the object
	 * 
	 * @param status This parameter refers to the status of the shift
	 */
	public void setStatus(char status) {
		this.status = status;
	}

	/**
	 * getComent
	 * <p>
	 * desc:
	 * </p>
	 * This method allows to obtain private data
	 *
	 * @return this method returns a type data String whit the document the comment
	 *         of the turn referring to whether the user was or not
	 */
	public char getComent() {
		return coment;
	}

	/**
	 * setComent
	 * <p>
	 * desc:
	 * </p>
	 * This method allows you to modify an attribute of the object
	 *
	 * @param coment This parameter refers to whether the user was or not
	 *
	 */
	public void setComent(char coment) {
		this.coment = coment;
	}

	/**
	 * getTurnoCodificado
	 * <p>
	 * desc:
	 * </p>
	 * This method allows to obtain private data
	 *
	 * @return this method returns a type data String whit code of the Shift
	 */
	public String getTurnoCodificado() {
		return turnoCodificado;
	}

	public String toString() {
		return "codigo: " + turnoCodificado + " estaba el usuario: " + coment + "se atendio: " + status;

	}
}
