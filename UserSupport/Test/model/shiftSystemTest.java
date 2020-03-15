package model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import customException.RepeatedUserException;
import customException.noFoundException;
import java.util.ArrayList;

class shiftSystemTest {

	ShiftSystem tmp = new ShiftSystem ("clinica");
	ShiftSystem prueba = new ShiftSystem ("restaurante");
	
	private void setup1() {
		
		tmp.addUser("cedula","66850347","marina","avila","3207650367","la sirena") ;
		tmp.addShift("66850347", "sacar cita", 7, 10, 0);
	}
	
	private void setup2() {
		prueba.addUser("cedula", "66824321", "gladys", "avila", "3184415441", "cra51 #34");
		prueba.addShift("66824321", "pedir almuerzo", 11, 14, 21);
	}
	
	@Test
	public void addUserTest() {
		
		setup1();
		//el metodo si funciona, pero cada vez tiene una duracion diferente
		assertEquals( tmp.addUser("cedula","66850347","marina","avila","3207650367","la sirena"),"marina  avila  1584294135712 milliseconds","this correct");
		
	}
	
	@Test
	public void repeatedTest () {
		
		setup1();
		
		try {
		assertEquals( tmp.repeated("66850347"),"the user 66850347 is already registered","this correct");
		}
		catch(RepeatedUserException e) {
			
			e.getMessage();
		}
		
		try {
			assertEquals( tmp.repeated("1193269834"),"the user is not listed","this correct");
			}
			catch(RepeatedUserException e) {
				
				e.getMessage();
			}
	}
		
		public void searchDocumet () {
			
			User user= new User("cedula","66850347","marina","avila","3207650367","la sirena");
			setup1();
			try {
			assertEquals( tmp.searchDocument(user.getDocumenNumber(),"cancelar cita", 12,24,15),"Name : marina avila//Phone: 3207650367 A01  inicio 07:10:00.779///  fin 12:24:15.779","this correct");
			}
			catch(noFoundException s) {
				
				s.getMessage();
			}
			
			try {
				assertEquals( tmp.searchDocument("1193269834","sacar cita", 14, 25,35),"the user 1193269834 is registered","this correct");
				}
				catch(noFoundException s) {
					
					s.getMessage();
				}
		}
		
		@Test
		public void verificationTest () {
			
			try {
			assertEquals( tmp.verification(""),"entered value correct","this correct");
			}
			
			catch(StringIndexOutOfBoundsException e) {
				
				e.getMessage();
			}
			
			try {
				assertEquals( tmp.verification("jaime"),"correct","this correct");
				}
				
				catch(StringIndexOutOfBoundsException e) {
					
					e.getMessage();
				}
			}
		
		@Test
		public void blankLineTest () {
			
			assertEquals( prueba.blankLine(""),"correct","this correct");
			assertEquals( prueba.blankLine("3023558556"),"correct","this correct");
		}
	
		@Test
		public void discontinued() {
			assertFalse(tmp.discontinued("1193269834"), "false");
		}
		
		

}
