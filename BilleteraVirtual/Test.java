package BilleteraVirtual;

public class Test {

	private static BilleteraVirtual billetera;

	private static void probarAgregarCliente(String dni) {
		System.out.println("************************************");
		System.out.println("Agregamos el usuario " + dni + " : " + billetera.agregarCliente(dni));
	}

	private static void probarRegistrarTarjeta(String dni, String numero, NombreTarjeta nombre, double montoDisponible) {
		System.out.println("*******************************************"); 
		System.out.println("Registramos tarjeta al usuario dni " + dni);
		System.out.println("numero "  + numero + " marca " + nombre + " disponible " + montoDisponible 
				+ " : " + billetera.registrarTarjeta(dni, numero, nombre, montoDisponible)); 
	}
	
	private static void probarMostrarTarjetasPuedenComprar(String dni, double monto) {
		System.out.println("*******************************************");
		System.out.println("Vemos que tarjetas del usuario dni " + dni + " pueden hacer una compra de " + monto);
		billetera.mostrarTarjetasPuedenComprar(dni, monto);
	}

	private static void probarMostrarTarjetasConSaldo() {
		System.out.println("***********************************************************"); 
		System.out.println("Ahora listamos las tarjetas con saldo de todos los usuarios");
		billetera.mostrarTarjetasConSaldo();
	}
	
	private static void probarObtenerCompras() {
		System.out.println("**********************************************************************"); 
		System.out.println("Listamos los usuarios y  la cantidad de compras que realizaron");
		for (ClienteComprasRealizadas clienteComprasRealizadas: billetera.obtenerCompras()) {
			System.out.println(clienteComprasRealizadas);
		}
	}
	
	private static void probarComprar(String dni, double monto, int cantidadCuotas) {
		System.out.println("************************************************************"); 
		System.out.println("El usuario dni " + dni + " hace una compra de " + monto + " en " + cantidadCuotas + " cuotas");
		billetera.comprar(dni, monto, cantidadCuotas);
	}
	
	public static void main(String[] args) {
		billetera = new BilleteraVirtual();

		probarAgregarCliente("30000000");
		probarAgregarCliente("40000000");

		probarRegistrarTarjeta("30000000", "2345789000", NombreTarjeta.INSTICARD, 50000);
		probarRegistrarTarjeta("30000000", "4444455555", NombreTarjeta.MONTACARD, 1000);
		probarRegistrarTarjeta("35000000", "1234567890", NombreTarjeta.INSTICARD, 100000);
		probarRegistrarTarjeta("40000000", "6666644444", NombreTarjeta.YATAYPLUS, 100000);
		probarRegistrarTarjeta("40000000", "9999988888", NombreTarjeta.INSTICARD, 0);
		probarRegistrarTarjeta("40000000", "6666644444", NombreTarjeta.YATAYPLUS, 100000);
		
		probarMostrarTarjetasPuedenComprar("30000000", 5000);
		
		probarComprar("40000000", 15000, 3);
		
		probarObtenerCompras();
		
		probarMostrarTarjetasConSaldo();
		
	}

}
