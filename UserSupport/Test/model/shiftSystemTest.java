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
		
	}
	;
	@Test
	public void addUserTest() {
		
		setup1();
		
		assertEquals( tmp.addUser("cedula","66850347","marina","avila","3207650367","la sirena"),"marina  avila  turno: A01","this correct");
		
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
		
	@Test
		public void searchDocumet () {
			
			User user= new User("cedula","66850347","marina","avila","3207650367","la sirena");
			setup1();
			try {
			assertEquals( tmp.searchDocument(user.getDocumenNumber()),"Name : marina avila//Phone: 3207650367 A00","this correct");
			}
			catch(noFoundException s) {
				
				s.getMessage();
			}
			
			try {
				assertEquals( tmp.searchDocument("1193269834"),"the user 1193269834 is registered","this correct");
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
		public void attendShiftTest () {
			
			
			setup1();
			assertEquals( tmp.attendShift("1193269834", 'Y'),"This user does not have an assigned shift or is not registered","this correct");
			assertEquals( tmp.attendShift("66850347", 'Y'),"the turn was attended","this correct");
			assertEquals( tmp.attendShift("66850347", 'N'),"the turn was attended but the user was not","this correct");
		}

}
