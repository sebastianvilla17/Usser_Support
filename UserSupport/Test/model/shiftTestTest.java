package model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class shiftTestTest {

	@Test
	public void shiftTest() {
		Shift tmp= new Shift (1);
		assertEquals( tmp.getStatus(),'N',"this correct");
		assertEquals( tmp.getComent(),'U',"this correct");
		assertEquals( tmp.getTurnoCodificado(),"A01","this correct");
		
	}

}
