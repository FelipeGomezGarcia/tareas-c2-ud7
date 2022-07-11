import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Scanner;

public class Actividad1Appp {
	public static void main (String[] args) {
		Scanner sc = new Scanner (System.in);
		
		System.out.println("Cuantos  alumnos tienes");
		int num = sc.nextInt();
		
		Hashtable<String,Double> medias = new Hashtable<String,Double>();
		
		for (int i = 0; i < num; i++) {
			System.out.println("Introduce el nombre del alumno");
			String alumno = sc.next();
		
			double media = notaMedia(sc);
		
			medias = rellenarDiccionario(medias, alumno, media);
		}
		
		imprimir(medias);
		
		
	}
	
	public static double notaMedia (Scanner sc) {
		
		System.out.println("Introduce las notas, para salir pon -1");
		
		double nota = 0, suma = 0, media = 0;
		ArrayList<Double> notas = new ArrayList<Double>();		
		
		while (nota>-1) {
			nota = sc.nextDouble();
			if (nota >= 0) {
				notas.add(nota);
				suma += nota;
			}
		}
		media = suma / notas.size();
		
		return media;
	}
	
	public static Hashtable<String,Double> rellenarDiccionario (Hashtable<String,Double> medias,String alumno, double media) {
		
		medias.put(alumno, media);
		
		return medias;
		
	}
	
	public static void imprimir (Hashtable<String,Double> mediaAlumnos) {
		
		Enumeration<String> enumeration = mediaAlumnos.keys();
		while (enumeration.hasMoreElements()) {
			String clave = enumeration.nextElement();
			System.out.println(clave + " " + mediaAlumnos.get(clave));
		}
	}
	
	
}
