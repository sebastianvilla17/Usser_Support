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
	
	@Test
	public void convertirTurnoTest() {
		
		Shift prueba = new Shift(1);
		assertEquals( prueba.convertirTurno(1),"A01","this correct");
		assertEquals( prueba.convertirTurno(2520),"Z20","this correct");
		assertEquals( prueba.convertirTurno(525),"F25","this correct");
		assertEquals( prueba.convertirTurno(3500),"J00","this correct");
	}
	

}
