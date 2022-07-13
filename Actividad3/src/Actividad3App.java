import java.util.Enumeration;
import java.util.Hashtable;

import javax.swing.JOptionPane;

public class Actividad3App {
	public static void main (String[] args) {
		Hashtable<String,double[]> stock = new Hashtable<String, double[]>();
		
		rellenar(stock);
		
		boolean menu = true;
		
		while (menu) {
			String opcion = JOptionPane.showInputDialog("Que opcion quieres hacer?\n1 Añadir producto\n2 Imprimir stock\n3 Imprimir producto");
			switch (opcion.toLowerCase()) {
			case "1":
				añadir(stock);
				break;
			case "2":
				imprimir(stock);
				break;
			case "3":
				String nombre = JOptionPane.showInputDialog("Introduce el nombre del producto");
				imprimir(stock,nombre);
				break;
			default:
				menu = false;
				break;
			}
		}
		
	}
	
	public static void rellenar (Hashtable<String, double[]> stock) {
		
		stock.put("Leche", new double[] {12,0.8});
		stock.put("Galletas", new double[] {5,1.5});
		stock.put("Cereales", new double[] {4,1.0});
		stock.put("Coca-Cola", new double[] {6,2.1});
		stock.put("Atun", new double[] {5,1.7});
		stock.put("Vino", new double[] {8,1.8});
		stock.put("Arroz", new double[] {8,0.8});
		stock.put("Harina", new double[] {10,1.0});
		stock.put("Azucar", new double[] {11,1.2});
		stock.put("Lentejas", new double[] {9,1.5});
		stock.put("Macarrones", new double[] {15,0.85});
		
	}
	
	public static void añadir (Hashtable<String, double[]> stock) {
		String nombre = JOptionPane.showInputDialog("Nombre del articulo");
		
		String num = JOptionPane.showInputDialog("Stock");
		
		String precio = JOptionPane.showInputDialog("Precio");
		
		stock.put(nombre, new double[] {Double.parseDouble(num),Double.parseDouble(precio)});
	}
	
	public static void imprimir (Hashtable<String, double[]> stock) {
		
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
}
