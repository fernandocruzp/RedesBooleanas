package ciencias.RedesBooleanas;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean salir = false;
        // Variables para almacenar la configuración inicial
        String archivoTabla = "";
        String archivoGrafica = "";
        Grafica grafica=null;
        HashMap<String ,Integer> tabla=new HashMap<>();

        while (!salir) {
            System.out.println("Redes booleanas de Kauffman");
            System.out.println("1. Generar red");
            System.out.println("2. Cargar configuración inicial de tabla");
            System.out.println("3. Cargar configuración inicial de gráfica");
            System.out.println("4. Salir");
            System.out.print("Seleccione una opción: ");
            int opcion = scanner.nextInt();
            scanner.nextLine(); // Limpiar el buffer

            switch (opcion) {
                case 1:
                    System.out.println("Generando red...");
                    if(archivoGrafica.equals(""))
                        grafica=new Grafica();
                    if (archivoTabla.equals(""))
                        tabla=generaTabla(tabla);
                    imprimeTabla(tabla);
                    DibujadirRed dibujador= new DibujadirRed(grafica,tabla);
                    dibujador.dibujar();
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    break;
                case 2:
                    System.out.print("Ingrese el nombre del archivo de configuración de tabla: ");
                    archivoTabla = scanner.nextLine();
                    tabla=llenarTabla(archivoTabla,tabla);
                    break;
                case 3:
                    System.out.print("Ingrese el nombre del archivo de configuración de gráfica: ");
                    archivoGrafica = scanner.nextLine();
                    grafica=llenarGrafica(archivoGrafica);
                    break;
                case 4:
                    salir = true;
                    break;
                default:
                    System.out.println("Opción no válida. Por favor, seleccione una opción válida.");
            }
        }
        System.out.println("¡Hasta luego!");
        scanner.close();
    }

    private static HashMap<String,Integer> llenarTabla(String archivo,HashMap<String,Integer> tabla) {
        System.out.println("Llenando tabla desde el archivo: " + archivo);
        try {
            FileReader fileReader = new FileReader(archivo);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String linea;

            while ((linea = bufferedReader.readLine()) != null) {
                String[] valores=linea.split(" ");
                tabla.put(valores[0],Integer.parseInt(valores[1]));
            }
            bufferedReader.close();
            return tabla;
        } catch (IOException e) {
            System.err.println("Error al leer el archivo: " + e.getMessage());
        }
        return null;
    }

    private static Grafica llenarGrafica(String archivo) {
        System.out.println("Llenando gráfica desde el archivo: " + archivo);
        try {
            FileReader fileReader = new FileReader(archivo);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String linea;
            ArrayList<Integer> lista=new ArrayList<>();
            while ((linea = bufferedReader.readLine()) != null) {
                lista.add(Integer.parseInt(linea));
            }
            bufferedReader.close();
            Grafica grafica=new Grafica(lista);
            return grafica;
        } catch (IOException e) {
            System.err.println("Error al leer el archivo: " + e.getMessage());
        }
        return null;
    }

    private static void imprimeTabla(HashMap<String,Integer> tabla){
        System.out.println("Tabla");
        for(String valor: tabla.keySet())
            System.out.println(valor+ " " + tabla.get(valor));
    }



    private static HashMap<String,Integer> generaTabla(HashMap<String,Integer> tabla){
        Random random = new Random();
        tabla.put("000",random.nextInt(2));
        tabla.put("001",random.nextInt(2));
        tabla.put("010",random.nextInt(2));
        tabla.put("011",random.nextInt(2));
        tabla.put("100",random.nextInt(2));
        tabla.put("101",random.nextInt(2));
        tabla.put("110",random.nextInt(2));
        tabla.put("111",random.nextInt(2));
        return tabla;
    }
}
