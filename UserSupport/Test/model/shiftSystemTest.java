package model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class shiftSystemTest {

	
	private void setup1() {
		
		User tmp = new User("cedula","66850347","marina","avila","3207650367","la sirena");
	}
	
	@Test
	public void addUserTest() {
		
		setup1();
		ShiftSystem clinica= new ShiftSystem ("clinica");
		assertEquals( clinica.addUser("cedula","66850347","marina","avila","3207650367","la sirena"),"marina  avila  turno: A01","this correct");

		
	}

}
