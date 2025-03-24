package modelo;

import java.math.BigDecimal;
import java.util.Objects;

import excepciones.DineroInsuficienteException;

public class Cuenta {

	private String persona;
	private BigDecimal saldo;
	private BigDecimal monto;
	private Banco banco;

	public Cuenta() {
		// TODO Auto-generated constructor stub
	}

	public Cuenta(String persona, BigDecimal saldo) {
		this.persona = persona;
		this.saldo = saldo;
	}
	
	public void debito(BigDecimal monto) throws DineroInsuficienteException {
		BigDecimal nuevoSaldo= this.saldo.subtract(monto);
		if(nuevoSaldo.compareTo(BigDecimal.ZERO) < 0) {
			throw new DineroInsuficienteException();
		}
		this.saldo = nuevoSaldo;
	}
	
	public void credito (BigDecimal monto) {
		this.saldo = this.saldo.add(monto);
	}

	public BigDecimal getMonto() {
		return monto;
	}

	public void setMonto(BigDecimal monto) {
		this.monto = monto;
	}

	public String getPersona() {
		return persona;
	}

	public void setPersona(String persona) {
		this.persona = persona;
	}

	public BigDecimal getSaldo() {
		return saldo;
	}

	public void setSaldo(BigDecimal saldo) {
		this.saldo = saldo;
	}
	

	public Banco getBanco() {
		return banco;
	}

	public void setBanco(Banco banco) {
		this.banco = banco;
	}

	@Override
	public String toString() {
		return "Cuenta [persona=" + persona + ", saldo=" + saldo + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(persona, saldo);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cuenta other = (Cuenta) obj;
		return Objects.equals(persona, other.persona) && Objects.equals(saldo, other.saldo);
	}
	
	

}
