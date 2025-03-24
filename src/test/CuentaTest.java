package test;

import static org.junit.jupiter.api.Assertions.*;
import java.math.BigDecimal;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import excepciones.DineroInsuficienteException;
import modelo.Cuenta;

class CuentaTest {

	/*@Test
	void test() {
		fail("Not yet implemented");
	}*/
	
	@Test
	void testNombreCuenta() {
		
		Cuenta cuenta = new Cuenta("Pepe", new BigDecimal(1000.12345));
		String esperado = "Pepe";
		
		String real = cuenta.getPersona();
		
		assertNotNull(real);
		assertEquals(esperado,real);
		assertTrue(real.equals("Pepe"));
	}
	
	@Test
	void testSaldoCuenta () {
		Cuenta cuenta = new Cuenta("Pepe", new BigDecimal(1000.12345));
		assertNotNull(cuenta.getSaldo());
		assertEquals(1000.12345, cuenta.getSaldo().doubleValue());
		assertFalse(cuenta.getSaldo().doubleValue() < 0);
		assertFalse(cuenta.getSaldo().compareTo(BigDecimal.ZERO) < 0);
		assertTrue(cuenta.getSaldo().compareTo(BigDecimal.ZERO) > 0);
	}
	
	@Test
	@DisplayName("testeando referencis que sean iguales con el método equals.")
	void testReferenciaCuenta() {
		Cuenta cuenta = new Cuenta("John Doe", new BigDecimal(8900.9997));
		Cuenta cuenta2 = new Cuenta("John Doe", new BigDecimal(8900.9997));
		
		assertEquals(cuenta2,cuenta);
	}
	
	@Test
	void testDebitoCuenta() throws DineroInsuficienteException {
		Cuenta cuenta = new Cuenta ("Andres",new BigDecimal("1000.12345"));
		cuenta.debito(new BigDecimal(100));
		assertNotNull(cuenta.getSaldo());
		assertEquals(900,cuenta.getSaldo().intValue());
		assertEquals("900.12345",cuenta.getSaldo().toPlainString());
	}
	
	@Test
	void testCreditoCuenta() {
		Cuenta cuenta = new Cuenta ("Andres", new BigDecimal("1000.12345"));
		cuenta.credito(new BigDecimal(100));
		assertNotNull(cuenta.getSaldo());
		assertEquals(1100, cuenta.getSaldo().intValue());
		assertEquals("1100.12345", cuenta.getSaldo().toPlainString());
	}
	
	@Test
	void dineroInsuficienteExceptionCuenta() {
		Cuenta cuenta = new Cuenta ("Andres", new BigDecimal ("1000.12345"));
		Exception exception = assertThrows(DineroInsuficienteException.class, ()->{cuenta.debito(new BigDecimal(1500));});
	}
	
}
