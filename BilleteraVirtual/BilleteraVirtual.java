package BilleteraVirtual;

import java.util.ArrayList;

public class BilleteraVirtual {
	private ArrayList<Cliente> clientes;

	public BilleteraVirtual() {
		this.clientes = new ArrayList<>();
	}
	
	private Cliente buscarCliente(String dni) {
		Cliente cliente = null;
		int index = 0;
		int size = this.getClientes().size();
		while (cliente == null && index < size) {
			if (this.getClientes().get(index).getDni().equals(dni)) {
				cliente = this.getClientes().get(index);
			}
			else {
				index++;
			}
		}
		return cliente;
	}
	
	public boolean agregarCliente(String dni) {
		boolean pudoAgregar = false;
		if (this.buscarCliente(dni) == null) {
			this.getClientes().add(new Cliente(dni));
			pudoAgregar = true;
		}
		
		return pudoAgregar;
	}
	
	public boolean registrarTarjeta(String dni, String numero, NombreTarjeta nombre, double montoDisponible) {
		boolean pudoRegistrar = false;
		Cliente cliente = this.buscarCliente(dni);
		if (cliente != null) {
			pudoRegistrar = cliente.agregarTarjeta(numero, nombre, montoDisponible);
		}
		return pudoRegistrar;
	}
	
	public void mostrarTarjetasPuedenComprar(String dni, double monto) {
		Cliente cliente = this.buscarCliente(dni);
		if (cliente != null) {
			System.out.println("Lista de tarjetas ok de " + dni);
			cliente.mostrarTarjetasPuedenComprar(monto);
		}
	}
	
	public void mostrarTarjetasConSaldo() {
		for (Cliente cliente: this.getClientes()) {
			System.out.println("Tarjetas de " + cliente.getDni());
			cliente.mostrarTarjetasConSaldo();
		}
	}
	
	public ArrayList<ClienteComprasRealizadas> obtenerCompras() {
		ArrayList<ClienteComprasRealizadas> clienteComprasRealizadas = new ArrayList<>();
		for (Cliente cliente: this.getClientes()) {
			clienteComprasRealizadas.add(new ClienteComprasRealizadas(cliente.getDni(), cliente.getCantidadComprasRealizadas()));
		}
		return clienteComprasRealizadas;
	}
	
	public ResultadoRealizarCompra realizarCompra(String dni, double monto, int cantidadCuotas) {
		ResultadoRealizarCompra resultado = ResultadoRealizarCompra.TRANSACCION_OK;
		Cliente cliente = this.buscarCliente(dni);
		
		if (cliente == null) {
			resultado = ResultadoRealizarCompra.USUARIO_INEXISTENTE;
		}
		else if (monto<=0 || cantidadCuotas<=0) {
			resultado = ResultadoRealizarCompra.ERROR;
		}
		else {
			Tarjeta tarjetaMayorSaldo = cliente.getTarjetaMayorSaldo();
			
			if (tarjetaMayorSaldo == null || tarjetaMayorSaldo.getMontoDisponible()<monto) {
				resultado = ResultadoRealizarCompra.SIN_TARJETA_PARA_COMPRA;
			}
			else {
				if (!tarjetaMayorSaldo.comprar(monto)) {
					resultado = ResultadoRealizarCompra.ERROR;
				}
				else {
					cliente.registrarCompra();
				}
			}
			
		}
		
		return resultado;
	}
	
	public void comprar(String dni, double monto, int cantidadCuotas) {
		ResultadoRealizarCompra resultado = this.realizarCompra(dni, monto, cantidadCuotas);
		if (resultado == ResultadoRealizarCompra.TRANSACCION_OK) {
			System.out.println("Compra realizada por " + dni); 
			System.out.println("Monto Compra: " + monto + " - coutas " + cantidadCuotas); 
			System.out.println("Monto por Cuota:" + monto/cantidadCuotas);
		}
	}
	
	

	private ArrayList<Cliente> getClientes() {
		return clientes;
	}
}
