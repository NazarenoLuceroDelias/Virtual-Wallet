package BilleteraVirtual;

import java.util.ArrayList;

public class Cliente {
	private String dni;
	private ArrayList<Tarjeta> tarjetas;
	private int cantidadComprasRealizadas;
	
	public Cliente(String dni) {
		this.dni = dni;
		this.cantidadComprasRealizadas = 0;
		this.tarjetas = new ArrayList<>();
	}
	
	private Tarjeta buscarTarjeta(String numero) {
		Tarjeta tarjeta = null;
		int index = 0;
		int size = this.getTarjetas().size();
		
		while (tarjeta == null && index < size) {
			if (this.getTarjetas().get(index).getNumero().equals(numero)) {
				tarjeta = this.getTarjetas().get(index);
			}
			else {
				index++;
			}
		}
		return tarjeta;
	}
	
	public boolean agregarTarjeta(String numero, NombreTarjeta nombre, double montoDisponible) {
		boolean pudoAgregar = false;
		if (this.buscarTarjeta(numero) == null) {
			this.getTarjetas().add(new Tarjeta(numero, nombre, montoDisponible));
			pudoAgregar = true;
		}
		return pudoAgregar;
	}
	
	public void mostrarTarjetasPuedenComprar(double monto) {
		for (Tarjeta tarjeta: this.getTarjetas()) {
			if (tarjeta.puedeComprar(monto)) {
				System.out.println(tarjeta);
			}
		}
	}
	
	public void mostrarTarjetasConSaldo() {
		for (Tarjeta tarjeta: this.getTarjetas()) {
			if (tarjeta.getMontoDisponible()>0) {
				System.out.println(tarjeta);
			}
		}
	}
	
	public Tarjeta getTarjetaMayorSaldo() {
		Tarjeta tarjetaMayorSaldo = null;
		double saldo = 0;
		for (Tarjeta tarjeta: this.getTarjetas()) {
			if (tarjeta.getMontoDisponible()>saldo) {
				saldo = tarjeta.getMontoDisponible();
				tarjetaMayorSaldo = tarjeta;
			}
		}
		return tarjetaMayorSaldo;
	}
	
	public void registrarCompra() {
		this.setCantidadComprasRealizadas(this.getCantidadComprasRealizadas()+1);
	}
	
	
	public String getDni() {
		return dni;
	}

	private ArrayList<Tarjeta> getTarjetas() {
		return tarjetas;
	}

	public int getCantidadComprasRealizadas() {
		return cantidadComprasRealizadas;
	}

	private void setCantidadComprasRealizadas(int cantidadComprasRealizadas) {
		this.cantidadComprasRealizadas = cantidadComprasRealizadas;
	}

	@Override
	public String toString() {
		return "Cliente [dni=" + dni + ", tarjetas=" + tarjetas + ", cantidadComprasRealizadas="
				+ cantidadComprasRealizadas + "]";
	}
	
	
}
