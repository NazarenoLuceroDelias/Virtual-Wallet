package BilleteraVirtual;

public class ClienteComprasRealizadas {
	private String dni;
	private int comprasRealizadas;
	
	public ClienteComprasRealizadas(String dni, int comprasRealizadas) {
		this.dni = dni;
		this.comprasRealizadas = comprasRealizadas;
	}

	@Override
	public String toString() {
		return "Usuario: " + this.dni + " - Cant Compras: " + this.comprasRealizadas;
	}
}
