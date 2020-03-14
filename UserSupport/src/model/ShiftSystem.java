package model;

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

	public String addShift(String documentNumber, String shiftName, int hora, int minuto, int segundo, int horaFin,
			int minutoFin, int segundoFin) {

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

	/*public String creation(String documentType, String documentNumber, String name, String lastName, String phone,
			String adress, String shiftName, int hora, int minuto, int segundo, int horaFin, int minutoFin,
			int segundoFin) {

		String user = addUser(documentType, documentNumber, name, lastName, phone, adress);

		String shift = addShift(documentNumber, shiftName, hora, minuto, segundo, horaFin, minutoFin, segundoFin);

		return user + shift;
	}*/

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
			} else {

				message = "the user is not listed";
			}

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
	
	public String searchDocument(String documentNumber, String shiftName, int hora, int minuto, int segundo,
			int horaFin, int minutoFin, int segundoFin) throws noFoundException {

		String encontrado="";
		
		for(int i=0; i<userList.size(); i++) {
			
			String tmp= userList.get(i).getDocumenNumber();
			if(tmp.equals(documentNumber)) {
				encontrado= userList.get(i).toString()+" "+ addShift(documentNumber, shiftName, hora, minuto, segundo, horaFin, minutoFin, segundoFin);
			}
		}
		if(encontrado.equals("")) {
			throw new noFoundException(documentNumber);
			}
		
		
		return encontrado;
		
		/*String search = "";
		for (int i = 0; i < userList.size(); i++) {

			User usuarioTemporal = userList.get(i);
			if (usuarioTemporal.getDocumenNumber().equals(documentNumber)) {

				search = usuarioTemporal.toString() + " " + addShift(documentNumber, shiftName, hora, minuto, segundo, horaFin, minutoFin, segundoFin);
			}

			else {

				throw new noFoundException(documentNumber);
			}
		}
		return search;*/
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
