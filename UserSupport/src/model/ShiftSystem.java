package model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalTime;
import java.util.ArrayList;
import customException.*;
import model.DateAndTime;
import model.ShiftType;

public class ShiftSystem {

	private String nameOfDepartament = "";
	ArrayList<User> userList = new ArrayList<User>();
	ArrayList<Shift> shiftList = new ArrayList<Shift>();
	DateAndTime fecha = DateAndTime.ObtenerDateAndTimeUnico();
	ShiftType type;
	private static final String ARCHIVO_NOMBRE = "data" + File.separator + "nombre.txt";
	private static final String ARCHIVO_APELLIDO = "data" + File.separator + "apellido.txt";

	public ShiftSystem(String nameOfDepartament) {
		this.nameOfDepartament = nameOfDepartament;

	}

	/**
	 * addUser
	 * <p>
	 * desc:
	 * </p>
	 * this method creates an object of type user adds it to the list and creates
	 * the coded turn
	 *
	 * @param documentNumber This parameter refers to the user's document
	 *                       number,string type
	 * @param documentType   This parameter refers to the user's document
	 *                       Type,string type
	 * @param name           This parameter refers to the user's name,string type
	 * @param lastName       This parameter refers to the user's last Name,string
	 *                       type
	 * @param phone          This parameter refers to the user's phone,string type
	 * @param adress         This parameter refers to the user's adress,string type
	 *
	 * @return this method returns a message confirming that the user was created
	 *         whit user information and the shift
	 */
	public String addUser(String documentType, String documentNumber, String name, String lastName, String phone,
			String adress) {

		String info = "";

		User usuario = new User(documentType, documentNumber, name, lastName, phone, adress);
		userList.add(usuario);

		info = (usuario.getName() + "  " + usuario.getLastName());
		return info;
	}

	public String addShift(String documentNumber, String shiftName, int horaFin, int minutoFin, int segundoFin) {

		int posicion = 0;
		for (int i = 0; i < userList.size(); i++) {

			User usuarioTemporal = userList.get(i);
			if (usuarioTemporal.getDocumenNumber().equals(documentNumber)) {
				posicion = i;
			}
		}

		LocalTime start = fecha.mantenerFecha();
		LocalTime end = fecha.formatearHora(horaFin, minutoFin, segundoFin);

		int idTurnoTemp = shiftList.size();

		Shift turno = new Shift(posicion, idTurnoTemp, shiftName, start, end);
		shiftList.add(turno);

		String info = turno.getTurnoCodificado() + "  inicio " + start + "///" + "  fin " + end;
		return info;
	}

	public boolean discontinued(String document) {

		boolean penalized = false;
		int conter = 0;
		for (int i = 0; i < userList.size(); i++) {
			User usuarioTemporal = userList.get(i);
			if (usuarioTemporal.getDocumenNumber().equals(document)) {
				for (int j = 0; j < shiftList.size(); j++) {
					Shift tmp = shiftList.get(j);
					if (tmp.getPosicion() == i) {
						if (tmp.getComent() == 'O') {
							conter++;
							if (conter >= 2) {
								usuarioTemporal.setDiscontinued(true);
								penalized = true;
							}
						}
					}

				}
			} else {
				penalized = false;
			}

		}
		if (penalized == false) {
			penalized = false;
		}
		return penalized;

	}

	public String genrateUsers(int cant) throws IOException {
		String informe = "";
		int conter = 0;
		BufferedReader br = new BufferedReader(new FileReader(ARCHIVO_NOMBRE));
		BufferedReader bx = new BufferedReader(new FileReader(ARCHIVO_APELLIDO));
		while (conter < cant) {

			String name = br.readLine();
			String lastName = bx.readLine();
			String documentType = "cedula";
			String phone = String.valueOf(Math.floor(Math.random() * (1000000 - 9999999 + 1) + 9999999));
			String documentNumber = String.valueOf(Math.floor(Math.random() * (1000000 - 99999999 + 1) + 99999999));
			String adress = "carrera" + String.valueOf(Math.floor(Math.random() * (1 - 200 + 1) + 200));

			informe += addUser(documentType, documentNumber, name, lastName, phone, adress) + "\n";
			conter++;

		}
		br.close();
		bx.close();

		return informe;
	}

	public String generateShifts(int day, int cant) {
		String informe="";
		String inforTotal="";
		int conter=0;
		String docNum="";
		int num=0;
		int dayge=1;
		while(num<day) {
			
		while(conter<cant) {
		int tamnio= userList.size();
		int userPos =  (int)(Math.floor(Math.random() * (0 - tamnio + 1) + tamnio));
		for(int i=0; i<userList.size(); i++) {
			if(i==userPos) {
				 docNum= userList.get(i).getDocumenNumber();
			}
		}
		
		String shiftName= "sacar turno";
		int horaFin=  (int)(Math.floor(Math.random() * (1 - 24 + 1) + 24));
		int minutoFin =  (int)(Math.floor(Math.random() * (0 - 60 + 1) + 60));
		int segundoFin=  (int)(Math.floor(Math.random() * (0 - 60 + 1) + 60));
		
		informe+= addShift( docNum,  shiftName,  horaFin,  minutoFin,  segundoFin) + "\n";
		conter++;
	}
		inforTotal+="Day: "+ dayge +"\n"+ "  "+ informe+ "\n";
		num++;
		dayge++;
		}
		return inforTotal;
	}

	/**
	 * repeated
	 * <p>
	 * desc:
	 * </p>
	 * This method is in charge of searching the list if the user is registered, if
	 * he or she throws an exception and if you send us a message
	 *
	 * @param document This parameter refers to the user's document number,string
	 *                 type
	 * @exception RepeatedUserException send a message in case the user has already
	 *                                  been registered
	 * 
	 * @return This method returns the message of the exception or a message
	 *         confirming that the user is not repeated
	 */
	public String repeated(String document) throws RepeatedUserException {

		String message = "";

		for (int i = 0; i < userList.size(); i++) {

			User usuarioTemporal = userList.get(i);
			if (usuarioTemporal.getDocumenNumber().equals(document)) {

				throw new RepeatedUserException(document);
			}
		}
		if (message.equals("")) {
			message = "the user is not listed";
		}

		return message;
	}

	/**
	 * searchDocument
	 * <p>
	 * desc:
	 * </p>
	 * This method is in charge of searching the list if the user is registered, If
	 * the user is registered, return a message with the name, phone number and turn
	 *
	 * @param documentNumber This parameter refers to the user's document
	 *                       number,string type
	 *
	 * @return This method returns the message a message whit the user information
	 *         or message in which the information was not found
	 */

	public String searchDocument(String documentNumber, String shiftName, int horaFin, int minutoFin, int segundoFin)
			throws noFoundException {

		String encontrado = "";

		for (int i = 0; i < userList.size(); i++) {

			String tmp = userList.get(i).getDocumenNumber();
			if (tmp.equals(documentNumber)) {
				encontrado = userList.get(i).toString() + " "
						+ addShift(documentNumber, shiftName, horaFin, minutoFin, segundoFin);
			}
		}
		if (encontrado.equals("")) {
			throw new noFoundException(documentNumber);
		}

		return encontrado;

		/*
		 * String search = ""; for (int i = 0; i < userList.size(); i++) {
		 * 
		 * User usuarioTemporal = userList.get(i); if
		 * (usuarioTemporal.getDocumenNumber().equals(documentNumber)) {
		 * 
		 * search = usuarioTemporal.toString() + " " + addShift(documentNumber,
		 * shiftName, hora, minuto, segundo, horaFin, minutoFin, segundoFin); }
		 * 
		 * else {
		 * 
		 * throw new noFoundException(documentNumber); } } return search;
		 */
	}

	/**
	 * verification
	 * <p>
	 * desc:
	 * </p>
	 * TThis method verifies that the text string entered by the user contains data
	 *
	 * @param info This parameter refers to the string of characters entered by the
	 *             user
	 * @exception StringIndexOutOfBoundsException does not allow strings to be
	 *                                            entered without data
	 *
	 * @return returns a message confirming that the data is correct or incorrect
	 */
	public String verification(String info) throws StringIndexOutOfBoundsException {

		String message = "";

		if (info.charAt(0) == ' ') {
			throw new StringIndexOutOfBoundsException();
		} else {
			message = "correct";
		}
		return message;
	}

	/**
	 * blankLine
	 * <p>
	 * desc:
	 * </p>
	 * This method ensures that the user can enter a string without information to
	 * data that is not mandatory
	 *
	 * @param caracter This parameter refers to the string of characters entered by
	 *                 the user
	 *
	 * @return returns a message confirming that the data is correct
	 */
	public String blankLine(String caracter) {

		String info = "";

		if (caracter.equals("")) {
			info = "correct";
		}

		else {
			info = "correct";
		}

		return info;
	}

	/**
	 * attendShift
	 * <p>
	 * desc:
	 * </p>
	 * This method is responsible for modifying the attributes of the shift
	 * depending on whether the user is or not and if it has been attended
	 * 
	 * @param document This parameter refers to the user´s document number
	 * @param user     This parameter refers to to the confirmation that the user is
	 *                 or not
	 *
	 * @return This method returns a message with information about the attention of
	 *         the shift
	 */
	public String attendShift(String document, char user) {

		String status = "";

		for (int i = 0; i < userList.size(); i++) {

			User usuarioTemporal = (User) userList.get(i);
			if (usuarioTemporal.getDocumenNumber().equals(document)) {

				for (int j = 1; j < shiftList.size(); j++) {

					int tmp = shiftList.get(j).getPosicion();
					if (tmp == i) {

						if (user == 'N') {
							shiftList.get(j).setStatus('A');
							shiftList.get(j).setComent('O');

							status = "the turn was attended but the user was not";
						} else {
							shiftList.get(j).setStatus('A');
							status = "the turn was attended";
						}
					} else {
						status = "This user does not have an assigned shift";
					}
				}
			} else {
				status = "This user does not registered";
			}

		}

		return status;
	}

	public String report(String document) {
		String report = "";
		for (int i = 0; i < userList.size(); i++) {
			User usuarioTemporal = (User) userList.get(i);
			if (usuarioTemporal.getDocumenNumber().equals(document)) {
				for (int j = 0; j < shiftList.size(); j++) {
					if (shiftList.get(j).getPosicion() == i) {
						report += shiftList.get(j).toString() + "\n";
					}
				}
				report = "no tiene turno";
				break;
			}
			report = "no tiene turno";
		}

		return report;
	}

}
