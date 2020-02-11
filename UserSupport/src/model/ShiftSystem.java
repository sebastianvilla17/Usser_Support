package model;

import java.util.ArrayList;
import customException.*;

public class ShiftSystem {

	private String nameOfDepartament="";
	ArrayList<Object[]> listaUsuarios = new ArrayList<Object[]>();

	
	public ShiftSystem (String nameOfDepartament){
		this.nameOfDepartament= nameOfDepartament;
	}
	
	/** addUser
	*<p> desc:</p> this method creates an object of 
	*type user adds it to the list and creates the coded turn
	*
	*@param documentNumber This parameter refers to the user's document number,string type
	*@param documentType This parameter refers to the user's document Type,string type 
	*@param name This parameter refers to the user's name,string type
	*@param lastName This parameter refers to the user's last Name,string type
	*@param phone This parameter refers to the user's phone,string type
	*@param adress This parameter refers to the user's adress,string type
	*
	*@return this method returns a message confirming that the user
	* was created whit user information and the shift
	*/
	public String addUser(String documentType, String documentNumber, String name, String lastName, String phone, String adress) {
		
		String info="";
		int idTurnoTemp = listaUsuarios.size(); 
		
		User  usuario = new User(documentType,  documentNumber, 	name,  lastName,  phone,  adress);
		Shift turno = new Shift(idTurnoTemp);
		
		Object usuarioTurno[] = new Object[2];	
		usuarioTurno[0] = usuario;
		usuarioTurno[1] = turno;
		
		listaUsuarios.add(usuarioTurno);
		
		
		info= (usuario.getName() +"  "+ usuario.getLastName() +"  "+ "turno: " + turno.getTurnoCodificado());
		return info;
	}
		
		
		/** repeated
		*<p> desc:</p> This method is in charge of searching the list if 
		*the user is registered, if he or she throws an exception and if 
		*you send us a message
		*
		*@param document This parameter refers to the user's document number,string type
		*@exception RepeatedUserException send a message in case the user
		* has already been registered
		* 
		*@return This method returns the message of the exception or a message 
		*confirming that the user is not repeated
		*/
		public String repeated(String document) throws RepeatedUserException {
			
			String message="";
			
			for (int i = 0; i<listaUsuarios.size(); i++){
				
				User usuarioTemporal = (User)listaUsuarios.get(i)[0];
				if (usuarioTemporal.getDocumenNumber().equals (document) ){
					
					throw new RepeatedUserException(document);
				}
				else {
					
					message= "the user is not listed";
				}
					
			}
			
			return message;
		}
		
		/** searchDocument
		*<p> desc:</p> This method is in charge of searching the list if 
		*the user is registered, If the user is registered, return a message
		* with the name, phone number and turn
		*
		*@param documentNumber This parameter refers to the user's document number,string type
		*
		*@return This method returns the message a message 
		*whit the user information or message in which the information
		* was not found
		*/
		public String searchDocument(String documentNumber){
			
			String search="";
			for (int i = 0; i<listaUsuarios.size(); i++){
				
				User usuarioTemporal = (User)listaUsuarios.get(i)[0];
				if (usuarioTemporal.getDocumenNumber().equals (documentNumber) ){
					Shift turn=(Shift)listaUsuarios.get(i)[1];
					search=  usuarioTemporal.toString()+" "+ turn.getTurnoCodificado() ;
				}
			}
			return search;
		}
		
		/** verification
		*<p> desc:</p> TThis method verifies that the text string entered by 
		*the user contains data
		*
		*@param info This parameter refers to the string of characters entered by the user
		*@exception StringIndexOutOfBoundsException does not allow strings to be entered without data
		*
		*@return returns a message confirming that the data is correct or incorrect
		*/
		public String verification(String info) throws StringIndexOutOfBoundsException {
			
			String message="";
			
			if(info.charAt(0)==' '){
			throw new StringIndexOutOfBoundsException();	
			}
			else{
				message= "correct";
			}
			return message;
		}
		
		/** blankLine
		*<p> desc:</p> This method ensures that the user can enter a string 
		*without information to data that is not mandatory
		*
		*@param caracter This parameter refers to the string of characters entered by the user
		*
		*@return returns a message confirming that the data is correct 
		*/
		public String blankLine(String caracter) {
			
			String info="";
			
			if(caracter.equals("")) {
				info= "correct";
			}
			
			else {
				info="correct";
			}
			
			return info;
		}
		
		/** blankLine
		*<p> desc:</p> This method is responsible for modifying the attributes
		* of the shift depending on whether the user is or not and if 
		* it has been attended
		* 
		*@param document This parameter refers to the user´s document number
		*@param user This parameter refers to to the confirmation that 
		*the user is or not
		*
		*@return This method returns a message with information about
		* the attention of the shift	
		*/
		public String attendShift(String document, char user) {
			
			String status="";
			
			for (int i = 0; i<listaUsuarios.size(); i++){
				
				User usuarioTemporal = (User)listaUsuarios.get(i)[0];
				if (usuarioTemporal.getDocumenNumber().equals (document) ){
					Shift turn=(Shift)listaUsuarios.get(i)[1];
					if(user=='N') {
					turn.setStatus('A');
					turn.setComent('O');
					
					status="the turn was attended but the user was not";
					} 
					else {
					turn.setStatus('A');
					status="the turn was attended";
					
					}
				}
				else {
					status="user not found";
				}
			}
			
			return status;
		}

		
}
