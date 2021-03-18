//Elder Guzmna
//19628
//Algoritmos y Estructura de datos
import java.io.File;  
import java.io.FileNotFoundException;  
import java.util.Scanner;
import java.util.*;

public class Main{
	
	private static void print(String a)
	{
		System.out.println(a);
	}
	
	private static <G extends Map> Inventario readFile(String path, G g)
	{
		String data = "";
		Inventario inventario = Inventario.create(g);
		try 
		{
			File myObj = new File(path);
			Scanner myReader = new Scanner(myObj);
			while (myReader.hasNextLine())
			{
				String producto = myReader.nextLine();
				String[] producto_info = new String[3];
				//Convierte la linea del archivo en un array de String con categoria, producto y existencia
				for(int i = 0; i < producto.split("|").length; i = i+1)
				{
					producto_info[i] = producto.split("|")[i];
				}
				
				//Por si no se habia guardado la existencia y unicamente se ingreso el producto
				if( producto_info[2] == null)
				{
					producto_info[2] = "1";
				}
				
				Integer cantidad = Integer.parseInt(producto_info[2]);
				
				Producto nuevo_producto = new Producto(cantidad, producto_info[1]);
				
				inventario.add(producto_info[0], nuevo_producto, true);
			}
			myReader.close();
		}
		catch(FileNotFoundException e)
		{
			print("An error occurred.");
			e.printStackTrace();
		}
		return inventario;
	}
	
	private static boolean menu(Inventario inventario)
	{
		Scanner sc = new Scanner(System.in);
		Integer opcion = sc.nextInt();
		String prod;
		String valor = "";
		switch(opcion){
			case 1:
				
				print("Ingrese la categoria y el nombre del producto separado por una coma: ");
				print("Ingrese la existencia del producto: ");
				Integer cantidad = sc.nextInt();
				prod = sc.nextLine();
				String[] str = prod.split(",",2);
				inventario.add(str[0], new Producto(cantidad, str[1]),false);
				valor = "El producto ha sido añadido con exito";
				break;
			case 2:
				print("Ingrese el nombre del producto que desea buscar: ");
				prod = sc.nextLine();
				valor = inventario.search(prod, false);
				break;
			case 3:
				print("Ingrese el nombre del producto que desea buscar: ");
				prod = sc.nextLine();
				valor = inventario.search(prod, true);
				
				break;
			case 4:
			case 5:
			case 6:
			default:
				print("Esta saliendo del programa");
				return true;
			}
			return false;
	}
	
	public static void main(String args[]) throws Exception
	{
		//Variables
		int opcion;
		boolean correcto = true;
		String path = "inventario.txt";
		Scanner sc = new Scanner(System.in);
		Inventario inventario =readFile(path, new HashMap<String, ArrayList<Producto> >());
		
		while(true){
			try{
				//asegurarse que ingresen una opcion valida
				print("Ingrese el numero de la clase desea emplear: \n1. Hashmap \n2.TreeMap \n3.LinkedHashMap");
				opcion = sc.nextInt();
				//Para elegir el tipo de mapa a implementar
				while(correcto){
					switch(opcion) {
					case 1:
						
						inventario = readFile(path, new HashMap<String, ArrayList<Producto> >());
						correcto = false;
						
						break;
					case 2:
						
						inventario = readFile(path, new TreeMap<String, ArrayList<Producto>>());
						correcto = false;
						break;
					case 3:
					
						inventario = readFile(path, new LinkedHashMap<String, ArrayList<Producto>>());
						correcto = false;
						break;
					default:
						print("Ingrese una opcion valida");
					}
				}
			
				print("Ingrese el numero de la accion que desea realizar: \n1. Agregar un producto a la coleccion \n2. Mostrar categoria de un producto \n3. Mostrar datos de un producto \n4. Mostrar datos de un producto \n5. Mostrar categoria y productos \n6. Mostrar categoria y productos ordenados ");
				opcion = sc.nextInt();
				while(true){
					if(menu(inventario))
					{
						return;
					}
					
				}
				
				
			}catch(Exception e){
				
				print("Error: "+ e.toString());
				
			}
		}
		
	}
}