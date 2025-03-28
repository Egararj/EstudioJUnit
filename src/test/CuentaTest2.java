package test;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import excepciones.DineroInsuficienteException;
import modelo.Banco;
import modelo.Cuenta;

class CuentaTest2 {
	
	@Test
	@Disabled
	void transferirDineroCuenta() throws DineroInsuficienteException {
		Cuenta cuenta = new Cuenta("Pepe", new BigDecimal(5000.12345));
		Cuenta cuenta2 = new Cuenta("Ana", new BigDecimal(5000.12345));
		Banco banco = new Banco();
		
		banco.addCuenta(cuenta);
		banco.addCuenta(cuenta2);
		banco.transferir(cuenta, cuenta2, new BigDecimal(500));
		
		assertEquals(cuenta.getSaldo(),new BigDecimal (4500.12345));
		assertEquals(cuenta2.getSaldo(), new BigDecimal(5500.12345));
	}
	
	@Test
	@DisplayName("Comprobar si hay cuentas tras agregar, que tienen la misma cantidad, buscar por nombre del banco y de la cuenta")
	void relacionesBancoCuenta () {
		Cuenta cuenta = new Cuenta("Pepe", new BigDecimal(5000.12345));
		Cuenta cuenta2 = new Cuenta("Andres", new BigDecimal(5000.12345));
		Banco banco = new Banco();
		banco.setNombre("Banco del Estado");
		
		banco.addCuenta(cuenta);
		banco.addCuenta(cuenta2);
		// vamos a probar que si hemos agregado 2 cuentas a un banco, la cantidad de cuentas que tiene son 2
		assertEquals(banco.getCuentas().size(), 2);
		
		// Si el nombre del banco es Banco del Estado, el nombre del banco del la cuenta 1 es Banco del Estado
		assertEquals(banco.getNombre().toString(), "Banco del Estado");
		assertEquals(cuenta.getBanco().getNombre().toString(), "Banco del Estado");
		
		// Si hemos agregado una cuenta con nombre Andres, en las cuenta del banco hay una cuenta Andres
		String nombre = null;
		
		for (Cuenta c: banco.getCuentas()) {
			if(c.getPersona().equals("Andres")) {
				nombre = c.getPersona();
				break;
			}
		}
		assertEquals("Andres", nombre);
		
		assertAll( 
				() -> {assertEquals(2,banco.getCuentas().size());},
				() -> {assertEquals("Banco del Estado", cuenta.getBanco().getNombre());},
				() -> {assertEquals("Andres",banco.getCuentas().stream().filter(c->c.getPersona().equals("Andres"))
						.findFirst().get().getPersona());}
				);
	}
}
