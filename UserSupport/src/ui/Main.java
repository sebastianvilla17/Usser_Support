package ui;

import java.util.Scanner;
import model.ShiftSystem;
import customException.*;

public class Main {

	private static Scanner reader;
	private static ShiftSystem users;
	
	/**nombre: main
	*<p> desc:</p> This method is responsible for interacting with the user 
	*by presenting in the options menu and according to the option chosen to ask 
	*the necessary requirements. at the same time, it makes the necessary validations
	*and according to this it throws an error or takes it to the corresponding method
	*
	*/
	public static void main (String []args){

		String documentType="" ;
		String documentNumber= "";
		String	name="";
		String lastName="";
		String phone="";
		String adress="";
		int option=0;
		String info="";
		String message="";
		int netx;
		String creation="";
		int menu=0;
		String find="";
		String repeat="";
		char answer;
		boolean valid=false;
		users= new ShiftSystem("users");
		reader= new Scanner(System.in);
		init();


		do{
				try {
					System.out.println("Welcome to the shifts attention system for the user");
					System.out.println ("///////////////////////////////////////////////////");
					System.out.println ("1 register shift or\n" +"2 attend shift");
					option= Integer.parseInt(reader.nextLine());


					
				}
				catch(NumberFormatException x){
					System.out.println("the value entered is incorrect");
					netx=1;
				}


			switch (option) {
				case 1:
					
					System.out.println ("Which action do you want to do \n 1 Add User \n 2 Register Shift ");
					menu= Integer.parseInt(reader.nextLine());
					netx=0;
					switch (menu) {
						case 1: 
							do {
								try {
									System.out.println("enter the name of the user");
									name= reader.nextLine().toLowerCase();
									message= users.verification(name);
									System.out.println(message);
									valid=false;
		
								}
								catch(StringIndexOutOfBoundsException x) {
									System.out.println("entered value correct" );
									System.out.println(x.getMessage());
									valid=true;
								}
							}
							while(valid==true);
							
							do {
								try {
									System.out.println("enter the last name of the user");
									lastName= reader.nextLine().toLowerCase();
									message= users.verification(lastName);
									System.out.println(message);
									valid=false;
								}
								catch(StringIndexOutOfBoundsException x) {
									System.out.println("entered value correct" );
									System.out.println(x.getMessage());
									valid=true;
								}
							}
							while(valid==true);
							
							do {
								try {
									System.out.println("enter the document Type of the user");
									documentType= reader.nextLine().toLowerCase();
									message= users.verification(documentType);
									System.out.println(message);
									valid=false;
								}
								catch(StringIndexOutOfBoundsException x) {
									System.out.println("entered value correct" );
									System.out.println(x.getMessage());
									valid=true;
								}
							}
							while(valid==true);

							do {
								try {
									System.out.println("enter the document number of the user");
									documentNumber= reader.nextLine().toLowerCase();
									message= users.verification(documentNumber);
									System.out.println(message);
									try {
										repeat= users.repeated(documentNumber);
										System.out.println(repeat);
									}
									catch(RepeatedUserException s) {
										System.out.println(s.getMessage());
										valid = true;
										break;
									}
									valid=false;
								}
								catch(StringIndexOutOfBoundsException x) {
									System.out.println("entered value correct" );
									System.out.println(x.getMessage());
									valid=true;
								}
							}
							while(valid==true);

							if(valid == false)
							{
								System.out.println("enter the phone of the user");
								phone= reader.nextLine().toLowerCase();
								info= users.blankLine(phone);
								System.out.println(info);
			
								System.out.println("enter the adress of the user");
								adress= reader.nextLine().toLowerCase();
								info= users.blankLine(adress);
								System.out.println(info);		
	
								creation= users.addUser(documentType,  documentNumber, 	name,  lastName,  phone,  adress);
								System.out.println(creation);
							}
		
							break;
		
						case 2 :
		
							System.out.println("enter the document Number of the user");
							documentNumber= reader.nextLine().toLowerCase();
		
							info= users.searchDocument(documentNumber);
							System.out.println(info);
							break;
					}
					
					break;
	
				case 2:
					System.out.println("enter the patient's document number");
					find= reader.nextLine().toLowerCase();
					info= users.searchDocument(find);
					System.out.println(info);
					System.out.println("///////////////");
					System.out.println("is the user?\n"+"yes, enter 'Y'\n"+ "no enter 'N'");	
					answer= reader.nextLine().toUpperCase().charAt(0);
					creation= users.attendShift(find, answer);
					System.out.println(creation);
					break;
			}

			System.out.println ("you want to return to the menu \n"+ "1 yes\n"+ "2 no");
			netx= Integer.parseInt(reader.nextLine());
		} while (netx==1);	
	}

	/**nombre: init
	*<p> desc:</p> This method contains the 
	*pre-determined user to be used in tests
	*
	**/
	public static void init() {

		String name="sebastian";
		String lastName="villa";
		String documentType="trajeta de identidad";
		String documentNumber="1193269834";
		String phone= "3023558556";
		String adress= "cra 5 #25-14";

		users.addUser(documentType,  documentNumber, name,  lastName,  phone,  adress);

	}
	
}