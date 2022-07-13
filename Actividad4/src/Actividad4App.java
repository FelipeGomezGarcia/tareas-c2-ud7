
import java.util.Enumeration;
import java.util.Hashtable;

import javax.swing.JOptionPane;

public class Actividad4App {
	public static void main (String[] args) {
		
		final double IVA_21 = 0.21;
		final double IVA_4 = 0.04;
		
		Hashtable<String,double[]> stock = new Hashtable<String, double[]>();
		Hashtable<String,double[]> venta = new Hashtable<String,double[]>();
		
		rellenar(stock,IVA_21,IVA_4);
		
		boolean menu = true;
		String nombre;
		
		while (menu) {
			String opcion = JOptionPane.showInputDialog("Que opcion quieres hacer?\n1 Añadir producto\n2 Imprimir stock\n3 Imprimir producto\n4 Hacer venta");
			switch (opcion.toLowerCase()) {
			case "1":
				añadirStock(stock,IVA_21, IVA_4);
				break;
			case "2":
				imprimir(stock);
				break;
			case "3":
				nombre = JOptionPane.showInputDialog("Introduce el nombre del producto");
				imprimir(stock,nombre);
				break;
			case "4":
				do {
					imprimir(stock);
					nombre = JOptionPane.showInputDialog("Introduce el nombre del producto\n(Para salir pon =)");
					añadirProducto(venta, stock, nombre);
				} while (!nombre.equals("="));
				calculos(venta);
				break;
			default:
				menu = false;
				break;
			}
		}
	}
	
	public static void rellenar (Hashtable<String, double[]> stock,double IVA_21, double IVA_4) {
		
		stock.put("leche", new double[] {12,0.8,IVA_4});
		stock.put("galletas", new double[] {5,1.5,IVA_4});
		stock.put("cereales", new double[] {4,1.0,IVA_21});
		stock.put("coca-cola", new double[] {6,2.1,IVA_21});
		stock.put("atun", new double[] {5,1.7,IVA_21});
		stock.put("vino", new double[] {8,1.8,IVA_21});
		stock.put("arroz", new double[] {8,0.8,IVA_4});
		stock.put("harina", new double[] {10,1.0,IVA_4});
		stock.put("azucar", new double[] {11,1.2,IVA_4});
		stock.put("lentejas", new double[] {9,1.5,IVA_4});
		stock.put("macarrones", new double[] {15,0.85,IVA_4});
		
	}
	
	public static void añadirStock (Hashtable<String, double[]> stock, double IVA_21, double IVA_4) {
		String nombre = JOptionPane.showInputDialog("Nombre del articulo");
		
		String num = JOptionPane.showInputDialog("Stock");
		
		String precio = JOptionPane.showInputDialog("Precio");
		
		String iva = JOptionPane.showInputDialog("IVA");
		
		Enumeration<String> keysStock = stock.keys();
		boolean estaEnStock = false;
		while (keysStock.hasMoreElements() && estaEnStock == false) {
			if (keysStock.nextElement().toLowerCase().equals(nombre.toLowerCase())) {
				estaEnStock = true;
			}
		}
		
		if (iva.equals("21") && estaEnStock == false) {
			stock.put(nombre, new double[] {Double.parseDouble(num),Double.parseDouble(precio), IVA_21});
		}else if (iva.equals("4") && estaEnStock == false) {
			stock.put(nombre, new double[] {Double.parseDouble(num),Double.parseDouble(precio), IVA_4});
		}
		
	}
	
	public static void imprimir (Hashtable<String, double[]> stock) {
		
		System.out.println("STOCK ACTUAL");
		Enumeration<String> keys = stock.keys();
		while (keys.hasMoreElements()) {
			String clave = keys.nextElement();
			double[] info = stock.get(clave);
			System.out.println(clave);
			System.out.println("Stock: " + (int)info[0]);
			System.out.println("Precio: " + info[1] + " €");
		}
	}
	
	public static void imprimir (Hashtable<String, double[]> stock, String nombre) {
		
		Enumeration<String> keys = stock.keys();
		while (keys.hasMoreElements()) {
			String clave = keys.nextElement();
			if (clave.toLowerCase().equals(nombre.toLowerCase())) {
				double[] info = stock.get(clave);
				System.out.println(clave);
				System.out.println("Stock: " + (int)info[0]);
				System.out.println("Precio: " + info[1] + " €");
			}
		}
	}
	
	public static void añadirProducto (Hashtable<String,double []> venta,Hashtable<String, double[]> stock, String  nombre) {
		
		
		
		double[] info = new double[3];
		boolean encontrado = false;
		
		Enumeration<String> keys = stock.keys();
		while (keys.hasMoreElements()) {
			String clave = keys.nextElement();
			if (clave.toLowerCase().equals(nombre.toLowerCase())) {
				info = stock.get(clave);
				encontrado = true;
			}
		}
		
		if (encontrado) {
			try {
				double cantidad = Double.parseDouble(JOptionPane.showInputDialog("Cantidad"));
				venta.put(nombre, new double[] {cantidad,info[1],info[2]});
				stock.remove(nombre.toLowerCase());
				stock.put(nombre.toLowerCase(), new double[] {info[0] - cantidad,info[1],info[2]});
			} catch (Exception e) {
				// TODO: handle exception
			}
			
		}
	}
	
	public static void calculos(Hashtable<String,double[]> venta) {
		double bruto = 0, total = 0,precio = 0;
		int totalArticulos = 0;
		Enumeration<String> keys = venta.keys();
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
