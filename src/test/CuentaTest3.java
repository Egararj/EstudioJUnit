package test;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.util.Map;
import java.util.Properties;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.DisabledIfSystemProperty;
import org.junit.jupiter.api.condition.DisabledOnOs;
import org.junit.jupiter.api.condition.EnabledIfEnvironmentVariable;
import org.junit.jupiter.api.condition.EnabledIfSystemProperty;
import org.junit.jupiter.api.condition.EnabledOnJre;
import org.junit.jupiter.api.condition.EnabledOnOs;
import org.junit.jupiter.api.condition.JRE;
import org.junit.jupiter.api.condition.OS;

import modelo.Cuenta;

class CuentaTest3 {
	Cuenta cuenta;

	@BeforeEach
	void initMetodoTest() {
		this.cuenta = new Cuenta("Andres", new BigDecimal("1000.12345"));
		System.out.println("Iniciamos el método");
	}
	
	@AfterEach
	void endMetodoTest() {
		cuenta = null;
		System.out.println("Finalizamos el método de prueba");
	}
	
	
	@Test
	void testNombreCuenta() {
		
		Cuenta cuenta = new Cuenta("Pepe", new BigDecimal(1000.12345));
		String esperado = "Pepe";
		
		String real = cuenta.getPersona();
		
		assertNotNull(real);
		assertEquals(esperado,real);
		assertTrue(real.equals("Pepe"));
		System.out.println("Primer test");
	}
	
	@Test
	void testSaldoCuenta () {
		Cuenta cuenta = new Cuenta("Pepe", new BigDecimal(1000.12345));
		assertNotNull(cuenta.getSaldo());
		assertEquals(1000.12345, cuenta.getSaldo().doubleValue());
		assertFalse(cuenta.getSaldo().doubleValue() < 0);
		assertFalse(cuenta.getSaldo().compareTo(BigDecimal.ZERO) < 0);
		assertTrue(cuenta.getSaldo().compareTo(BigDecimal.ZERO) > 0);
		System.out.println("Segundo test");
	}
	
	@Test
	@EnabledOnOs(OS.WINDOWS)
	void testSoloWindows() {
		System.out.println("Solo windows");
	}
	@DisabledOnOs(OS.WINDOWS)
	void testWindows() {
		System.out.println("No windows");
	}
	
	@Test
	@EnabledOnOs({OS.LINUX,OS.MAC})
	void testSoloLinuxMac() {
		System.out.println("Solo linux y mac");
	}
	@DisabledOnOs({OS.LINUX,OS.MAC})
	void testLinuxMac() {
		System.out.println("No linux y mac");
	}
	
	@Test
	@EnabledOnJre(JRE.JAVA_10)
	void testJRE() {
		System.out.println("habilitado");
	}
	
	@Test
	void imprimirProperties() {
		Properties properties = System.getProperties();
	}
	
	@Test
	@EnabledIfSystemProperty(named="java.version",matches="17.0.14")
	void testJavaVersion() {
		System.out.println("Test java version");
	}
	
	@Test
	@DisabledIfSystemProperty(named="os.arch", matches=".*32.*")
	void testSolo64() {
		System.out.println("Solo arquitectura 64");
	}
	
	@Test
	void imprimirVariableAmbiente() {
		
		Map<String,String> getenv=System.getenv();	
		
		getenv.forEach((k,v)->System.out.println(k+" = "+v));
		
	}
	
	@Test
	@EnabledIfEnvironmentVariable(named="NUMBER_OF_PROCESSORS",matches="12")
	void numProcesadores() {
	System.out.println("Doce procesadores");
	}
	// ejecucion de pruebas con Assumption
	// es como el AsserTrue pero de forma programatica, por ejemplo queremos que se ejecute un test
	// o parte de un test si un determinado servicio está levantado
	// tenemos que importar import static org.junit.jupiter.api.Assumptions.*; ......
	

}
