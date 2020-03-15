package ui;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Scanner;

import javax.imageio.IIOException;

import model.ShiftSystem;
import customException.*;
import model.DateAndTime;

public class Main {

	private static Scanner reader;
	private static ShiftSystem users;
	private static DateAndTime date;

	/**
	 * nombre: main
	 * <p>
	 * desc:
	 * </p>
	 * This method is responsible for interacting with the user by presenting in the
	 * options menu and according to the option chosen to ask the necessary
	 * requirements. at the same time, it makes the necessary validations and
	 * according to this it throws an error or takes it to the corresponding method
	 * 
	 * @throws IOException
	 * @throws FileNotFoundException
	 * @throws ClassNotFoundException
	 *
	 */
	public static void main(String[] args) throws FileNotFoundException, IOException, ClassNotFoundException {

		
		ObjectInputStream entrada = new ObjectInputStream(new FileInputStream("data" + File.separator + "guardar.txt")); 
		 ShiftSystem obj1 = (ShiftSystem)entrada.readObject(); 
		 entrada.close();
		 
		int anioA;
		int mesA;
		int diaA = 0;
		int horaA = 0;
		int minutoA = 0;
		int segundoA = 0;
		int horaFin;
		int minutoFin;
		int segundoFin;
		String shiftName = "";
		String documentType = "";
		String documentNumber = "";
		String name = "";
		String lastName = "";
		String phone = "";
		String adress = "";
		int option = 0;
		String info = "";
		String message = "";
		int netx;
		String creation = "";
		int menu = 0;
		String find = "";
		String repeat = "";
		String shiftCr = "";
		char answer;
		boolean valid = false;
		users = new ShiftSystem("users");
		reader = new Scanner(System.in);
		date = DateAndTime.ObtenerDateAndTimeUnico();
		init();

		do {
			System.out.println("choose the desired option\n" + "1 format the time\n" + "2 handle the system");
			try {
				option = Integer.parseInt(reader.nextLine());
				valid = true;
			} catch (NumberFormatException n) {
				System.out.println("the data entered is not valid");
				valid = false;
			}
		} while (valid == false);

		if (option == 1) {
			do {
				System.out.println("enter the year");
				anioA = Integer.parseInt(reader.nextLine());
			} while (anioA < 2020);
			do {
				System.out.println("enter the month");
				mesA = Integer.parseInt(reader.nextLine());
			} while (mesA > 12);
			do {
				System.out.println("enter the day");
				diaA = Integer.parseInt(reader.nextLine());
			} while (diaA > 31);
			do {
				System.out.println("enter the hour");
				horaA = Integer.parseInt(reader.nextLine());
			} while (horaA > 24);
			do {
				System.out.println("enter the minute");
				minutoA = Integer.parseInt(reader.nextLine());
			} while (minutoA > 59);
			do {
				System.out.println("enter the second");
				segundoA = Integer.parseInt(reader.nextLine());
			} while (segundoA > 59);

			LocalDate fecha = date.formatearFecha(anioA, mesA, diaA);
			LocalTime hora = date.formatearHora(horaA, minutoA, segundoA);

			System.out.println("Date: " + fecha + "\n" + "Time: " + hora);

		} else {
			System.out.println("Date: " + date.fecha());
			System.out.println("Time: " + date.hora());
		}

		do {
			System.out.println("/////////////////////////////////////");
			System.out.println("System time: " + date.mantenerFecha());
			try {
				System.out.println("Welcome to the shifts attention system for the user");
				System.out.println("///////////////////////////////////////////////////");
				System.out.println("1 register shift or\n" + "2 attend shift\n" + "3 reports \n"
						+ "4 show date and time\n" + "5 suspend user\n" + "6 random generation\n"+ "7 Sort by document Number \n" + "8 Sort by name\n"+ "9 Sort by last Name");
				option = Integer.parseInt(reader.nextLine());

			} catch (NumberFormatException x) {
				System.out.println("the value entered is incorrect");
				netx = 1;
			}

			switch (option) {
			case 1:

				System.out.println("Which action do you want to do \n 1 Add User \n 2 Register Shift ");
				menu = Integer.parseInt(reader.nextLine());
				netx = 0;
				switch (menu) {
				case 1:
					do {
						try {
							System.out.println("enter the name of the user");
							name = reader.nextLine().toLowerCase();
							message = users.verification(name);
							System.out.println(message);
							valid = false;

						} catch (StringIndexOutOfBoundsException x) {
							System.out.println("entered value correct");
							System.out.println(x.getMessage());
							valid = true;
						}
					} while (valid == true);

					do {
						try {
							System.out.println("enter the last name of the user");
							lastName = reader.nextLine().toLowerCase();
							message = users.verification(lastName);
							System.out.println(message);
							valid = false;
						} catch (StringIndexOutOfBoundsException x) {
							System.out.println("entered value correct");
							System.out.println(x.getMessage());
							valid = true;
						}
					} while (valid == true);

					do {
						try {
							System.out.println("enter the document Type of the user");
							documentType = reader.nextLine().toLowerCase();
							message = users.verification(documentType);
							System.out.println(message);
							valid = false;
						} catch (StringIndexOutOfBoundsException x) {
							System.out.println("entered value correct");
							System.out.println(x.getMessage());
							valid = true;
						}
					} while (valid == true);

					do {
						try {
							System.out.println("enter the document number of the user");
							documentNumber = reader.nextLine().toLowerCase();
							message = users.verification(documentNumber);
							System.out.println(message);
							try {
								repeat = users.repeated(documentNumber);
								System.out.println(repeat);
							} catch (RepeatedUserException s) {
								System.out.println(s.getMessage());
								valid = true;
								break;
							}
							valid = false;
						} catch (StringIndexOutOfBoundsException x) {
							System.out.println("entered value correct");
							System.out.println(x.getMessage());
							valid = true;
						}
					} while (valid == true);

					if (valid == false) {
						System.out.println("enter the phone of the user");
						phone = reader.nextLine().toLowerCase();
						info = users.blankLine(phone);
						System.out.println(info);

						System.out.println("enter the adress of the user");
						adress = reader.nextLine().toLowerCase();
						info = users.blankLine(adress);
						System.out.println(info);

						System.out.println("enter the name of the shift");
						shiftName = reader.nextLine().toLowerCase();

						System.out.println("enter the hour at which the turn ends");
						horaFin = Integer.parseInt(reader.nextLine());
						System.out.println("enter the minute at which the turn ends");
						minutoFin = Integer.parseInt(reader.nextLine());
						System.out.println("enter the second at which the turn ends");
						segundoFin = Integer.parseInt(reader.nextLine());

						creation = users.addUser(documentType, documentNumber, name, lastName, phone, adress);
						shiftCr = users.addShift(documentNumber, shiftName, horaFin, minutoFin, segundoFin);

						System.out.println(creation);
						System.out.println(shiftCr);
					}

					break;

				case 2:

					System.out.println("enter the document Number of the user");
					documentNumber = reader.nextLine().toLowerCase();

					System.out.println("enter the name of the shift");
					shiftName = reader.nextLine().toLowerCase();

					System.out.println("enter the hour at which the turn ends");
					horaFin = Integer.parseInt(reader.nextLine());
					System.out.println("enter the minute at which the turn ends");
					minutoFin = Integer.parseInt(reader.nextLine());
					System.out.println("enter the second at which the turn ends");
					segundoFin = Integer.parseInt(reader.nextLine());

					try {

						info = users.searchDocument(documentNumber, shiftName, horaFin, minutoFin, segundoFin);
						System.out.println(info);
					}

					catch (noFoundException e) {

						System.out.println(e.getMessage());
					}
					break;
				}

				break;

			case 2:

				creation = users.attendShift();
				System.out.println(creation);
				break;

			case 3:
				System.out.println("1 user report \n" + "2 shift report");
				option = Integer.parseInt(reader.nextLine());
				if(option==1) {
				System.out.println("enter the document Number of the user");
				documentNumber = reader.nextLine().toLowerCase();
				System.out.println("1 see it on screen \n" + "2 save it to file");
				netx = Integer.parseInt(reader.nextLine());
				if (users.discontinued(documentNumber) == true) {
					System.out.println("suspended user");
				} else {
					info = users.report(documentNumber,netx);
					System.out.println(info);
				}
				}else if(option==2) {
					
				}else {
					System.out.println("wrong choice");
				}
				break;

			case 4:
				System.out.println("hora:  " + date.mantenerFecha());
				System.out.println("fecha:  " + date.fechaActual());
				break;

			case 5:
				System.out.println("enter the document number of the person you want to suspend");
				documentNumber = reader.nextLine().toLowerCase();
				if (users.discontinued(documentNumber) == true) {
					System.out.println("suspended user");
				} else {
					System.out.println("the user is not suspended");
				}
				break;

			case 6:
				System.out.println("1 generate users \n" + "2 generate shifts");
				option = Integer.parseInt(reader.nextLine());
				if (option == 1) {

					System.out.println("enter the number of users that is to generate no more than 1000");
					int cantidad = Integer.parseInt(reader.nextLine());

					try {
						String result = users.genrateUsers(cantidad);
						System.out.println(result);
					} catch (IOException x) {

					}
				} else if (option == 2) {

					System.out.println("how many shift days do you want to generate");
					int day = Integer.parseInt(reader.nextLine());

					System.out.println("how many shifts per day");
					int turn = Integer.parseInt(reader.nextLine());

					String total = users.generateShifts(day, turn);
					System.out.println(total);
				}
				break;
			case 7:
				
				System.out.println(users.reporteOrdenadoDocumento("Documento"));
				break;
				
			case 8:
				System.out.println(users.reporteOrdenadoDocumento("Nombre"));
				break;
				
			case 9: 
				System.out.println(users.reporteOrdenadoDocumento("Apellido"));
				break;
			}
			ObjectOutputStream salida = new ObjectOutputStream(
					new FileOutputStream("data" + File.separator + "guardar.txt"));
			salida.writeObject(users);
			salida.close();
			System.out.println("you want to return to the menu \n" + "1 yes\n" + "2 no");
			netx = Integer.parseInt(reader.nextLine());
		} while (netx == 1);

	}

	/**
	 * nombre: init
	 * <p>
	 * desc:
	 * </p>
	 * This method contains the pre-determined user to be used in tests
	 *
	 **/
	public static void init() {

		String name = "sebastian";
		String lastName = "villa";
		String documentType = "trajeta de identidad";
		String documentNumber = "1193269834";
		String phone = "3023558556";
		String adress = "cra 5 #25-14";

		users.addUser(documentType, documentNumber, name, lastName, phone, adress);

		 name = "hector";
		 lastName = "cruz";
		 documentType = "cedula";
		 documentNumber = "66824321";
		 phone = "3023558556";
		 adress = "cra 5 #25-14";

		users.addUser(documentType, documentNumber, name, lastName, phone, adress);
	}

}
