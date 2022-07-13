import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Scanner;

public class Actividad2App {
	
	public static void main (String[] args) {
		Scanner sc = new Scanner(System.in);
		
		Hashtable<String,double[]> venta = new Hashtable<String,double[]>();
		
		System.out.println("Nombre del producto");
		String nombre = sc.next();
		
		do {
			añadirProducto(venta, sc, nombre);
			sc.nextLine();
			System.out.println("Nombre del producto");
			nombre = sc.nextLine();
		} while (!nombre.isEmpty());
		
		Enumeration<String> keys = venta.keys();
		
		calculos(venta, keys);
		
	}
	
	public static void añadirProducto (Hashtable<String,double[]> venta, Scanner sc, String  nombre) {
		final double IVA_21 = 0.21;
		final double IVA_4 = 0.04;
		
		System.out.println("Cantidad");
		double cantidad = sc.nextDouble();
		System.out.println("Precio");
		double precio = sc.nextDouble();
		System.out.println("IVA");
		int iva = sc.nextInt();
		
		if (iva == 21) {
			venta.put(nombre, new double[] {cantidad,precio,IVA_21});
		}else if (iva == 4) {
			venta.put(nombre, new double[] {cantidad,precio,IVA_4});
		}
		
	}
	
	public static void calculos(Hashtable<String,double[]> venta, Enumeration<String> keys) {
		double bruto = 0, total = 0,precio = 0;
		int totalArticulos = 0;
		while (keys.hasMoreElements()) {
			String clave = keys.nextElement();
			double info[] = venta.get(clave);
			totalArticulos += info[0];
			bruto += info[1] * info[0];
			precio = bruto + bruto*info[2];
			total += precio;
		}
		
		imprimirTiquet(venta, bruto, total, totalArticulos);
	}
	
	public static void imprimirTiquet (Hashtable<String,double[]> venta, double bruto, double total, int totalArticulos) {
		
		System.out.println("Producto	Precio/U	Cantidad	Precio");
		Enumeration<String> keys = venta.keys();
		while (keys.hasMoreElements()) {
			String clave = keys.nextElement();
			double info[] = venta.get(clave);
			System.out.println(clave + "\t" + info[1] + "\t\t" + (int)info[0] + "\t\t" + info[1]*info[0] + " €");
		}
		
		System.out.println("Precio total sin IVA = " + bruto + " €");
		System.out.println("Precio total con IVA = " + total + " €");
		System.out.println("Total de articulos comprados: " + totalArticulos);
	}
}
