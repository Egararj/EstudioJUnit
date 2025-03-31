package test;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.util.Map;
import java.util.Properties;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.RepeatedTest;
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

class CuentaTest4 {
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
	
	@BeforeAll
		static void beforeAll() {
		// podriamos crear ahora una unica instancia que se utilice en toda la clase
		// en vez de antes de cada metodo, pero no es una buena practica tener una
		// instacia de clase
		// para todas las pruebas. Ademas no sabemos el orden en el que se realizan los
		// test.
		System.out.println("iniciando el test");
	}
	
	@AfterAll
		static void afterAll() {
		System.out.println("finalizando el test");
	}
	
	@Nested
	@DisplayName("Probando operaciones cuenta")
	class OperacionesCuentas{
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
	}
	
	@Nested
	@DisplayName("Probando en función del el SO")
	
	class FuncionesSO{
	
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
	}
	
	@Nested
	@DisplayName("Probando en función de las propiedades")
	class PruebasVariablesEntorno_Propiedades{
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
	}
	// ejecucion de pruebas con Assumption
	// es como el AsserTrue pero de forma programatica, por ejemplo queremos que se ejecute un test
	// o parte de un test si un determinado servicio está levantado
	// tenemos que importar import static org.junit.jupiter.api.Assumptions.*; ......
	
	@RepeatedTest(5)
	@DisplayName("Comprobando el saldo de la cuenta Repetir")
	void testSaldoCuentaRepetir() {
		// Cuenta cuenta = new Cuenta("Pepe", new BigDecimal("1000.12345"));
		assertNotNull(cuenta.getSaldo());
		assertEquals(1000.12345, cuenta.getSaldo().doubleValue());
		// queremos comprobar que el saldo no es negativo. Pasaria la prueba si devuelve
		// falso
		assertFalse(cuenta.getSaldo().doubleValue() < 0);
		// idem anterior
		assertFalse(cuenta.getSaldo().compareTo(BigDecimal.ZERO) < 0);
		// idem . probar a poner saldo negativo
		assertTrue(cuenta.getSaldo().compareTo(BigDecimal.ZERO) > 0);
	}
	// ejecucion de pruebas con Assumption
	// es como el AsserTrue pero de forma programatica, por ejemplo queremos que se
	// ejecute un test
	// o parte de un test si un determinado servicio está levantado

	// tenemos que importar import static org.junit.jupiter.api.Assumptions.*;
	// ......

}
