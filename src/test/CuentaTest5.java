package test;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.util.stream.Stream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.MethodSources;
import org.junit.jupiter.params.provider.ValueSource;

import excepciones.DineroInsuficienteException;
import excepciones.DniException;
import modelo.Cuenta;

class CuentaTest5 {
	Cuenta cuenta;
	
	@BeforeEach
	void initMetodoTest() {
		this.cuenta = new Cuenta("Andres", new BigDecimal("1000.12345"));
		System.out.println("Iniciamos el método");
	}
	//@ParameterizedTest(name = "numero {index} ejecutando con valor {0} - {argumentsWithNames}")	
	//@ValueSource(ints = {1, 2, 3, 4, 5})  @ValueSource(doubles = {1.5, 2.5, 3.5}) @ValueSource(booleans = {true, false}) @ValueSource(chars = {'A', 'B', 'C'})
	@ParameterizedTest()
	@ValueSource(strings = {"100","200","300","500","700","1000.12345"})
	
	@Test
	void testDebitoCuenta(String monto) throws DineroInsuficienteException {
		cuenta.debito(new BigDecimal(monto));
		assertNotNull(cuenta.getSaldo());
		assertTrue(cuenta.getSaldo().compareTo(BigDecimal.ZERO)>0);
	}

	@ParameterizedTest(name = "numero {index} ejecutando con valor {0} - {argumentsWithNames}")
	@CsvFileSource(resources = "data.csv")
	void testDebitoCuentaCsvFileSource(String monto) throws DineroInsuficienteException {
		cuenta.debito(new BigDecimal (monto));
		assertNotNull (cuenta.getSaldo());
		assertTrue(cuenta.getSaldo().compareTo(BigDecimal.ZERO)>0);
	}
	
	@ParameterizedTest
	@MethodSources("proveedorDnis")
	void validarDni_parametrizado(String dni, boolean esperadoValido) {
		if(esperadoValido) {
			assertDoesNotThrow(() -> new User("Test", "test", "pass", dni));
		} else {
			assertThrows(DniException.class, () -> new User("Test", "test", "pass", dni));
		}
	}
	
	private static Stream<Arguments> proveedorDnis () {
		
		return Stream.of(
				Arguments.of("12345678Z", true),
				Arguments.of("DWFWEF", false),
				Arguments.of(null, false));
	}
}
