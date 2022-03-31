package BilleteraVirtual;

public class Tarjeta {
	private String numero;
	private NombreTarjeta nombre;
	private double montoDisponible;
	
	public Tarjeta(String numero, NombreTarjeta nombre, double montoDisponible) {
		this.numero = numero;
		this.nombre = nombre;
		this.setMontoDisponible(montoDisponible);
	}
	
	public boolean comprar(double monto) {
		boolean pudoComprar = false;
		if (this.puedeComprar(monto)) {
			pudoComprar = true;
			this.setMontoDisponible(this.getMontoDisponible()-monto);
		}
		return pudoComprar;
	}
	
	

	public double getMontoDisponible() {
		return montoDisponible;
	}
	
	public boolean puedeComprar(double monto) {
		return monto <= this.getMontoDisponible();
	}

	private void setMontoDisponible(double montoDisponible) {
		if (montoDisponible < 0) {
			montoDisponible = 0;
		}
		this.montoDisponible = montoDisponible;
	}

	public String getNumero() {
		return numero;
	}
	
	

	@Override
	public String toString() {
		return "Tarjeta [numero=" + numero + ", nombre=" + nombre + ", montoDisponible=" + montoDisponible + "]";
	}
	
	
	
}
