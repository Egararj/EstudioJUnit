package modelo;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import excepciones.DineroInsuficienteException;

public class Banco {
	
	private List<Cuenta> cuentas;
	private String nombre;
	
	public Banco() {
		cuentas = new ArrayList<>();
	}

	public List<Cuenta> getCuentas() {
		return cuentas;
	}

	public void setCuentas(List<Cuenta> cuentas) {
		this.cuentas = cuentas;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public void addCuenta(Cuenta cuenta) {
		cuentas.add(cuenta);
		cuenta.setBanco(this);
	}

	public void transferir(Cuenta origen, Cuenta destino, BigDecimal monto) throws DineroInsuficienteException {
		origen.debito(monto);
		destino.credito(monto);
	}

}
