package model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;


class userTest {

	@Test
	public void UserTest() {
		
		User tmp= new User ("cedula","66850347", "carla", "solis", "3158562556", "cra 5 #27-56");
		assertEquals( tmp.getDocumenType(),"cedula","this correct");
		assertEquals( tmp.getAdress(),"cra 5 #27-56","this correct");
		assertEquals( tmp.getDocumenNumber(),"66850347","this correct");
		assertEquals( tmp.getName(),"carla","this correct");
		assertEquals( tmp.getLastName(),"solis","this correct");
		assertEquals( tmp.getPhone(),"3158562556","this correct");
		
	}

}
