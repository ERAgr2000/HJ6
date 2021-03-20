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
				if(producto == null || producto == "")
				{
					
				}
				else
				{
					
					String[] producto_info = new String[3];
					//Convierte la linea del archivo en un array de String con categoria, producto y existencia
					for(int i = 0; i < producto.split("\\|",3).length; i = i+1)
					{
						producto_info[i] = producto.split("\\|")[i].trim();
											
					}
					
					//Por si no se habia guardado la existencia y unicamente se ingreso el producto
					if( producto_info[2] == null)
					{
						producto_info[2] = "1";
					}
					
					Integer cantidad = Integer.parseInt(producto_info[2].trim());
					
					Producto nuevo_producto = new Producto(cantidad, producto_info[1]);
					
					inventario.add(producto_info[0], nuevo_producto, true);
				}
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
		print("\nIngrese el numero de la accion que desea realizar: \n1. Agregar un producto a la coleccion \n2. Mostrar categoria de un producto \n3. Mostrar datos de los productos \n4. Mostrar datos de los productos ordenados \n5. Mostrar categoria y productos \n6. Mostrar categoria y productos ordenados ");
		Scanner sc = new Scanner(System.in);
		int opcion = sc.nextInt();
		String prod;
		String valor = "";
		switch(opcion){
			case 1:
				
				print("\nIngrese la categoria y el nombre del producto separado por una coma: ");
				prod = sc.nextLine();
				prod = sc.nextLine();
				print("\nIngrese la existencia del producto: ");
				Integer cantidad = sc.nextInt();
				String[] str = prod.split(",",2);
				inventario.add(str[0], new Producto(cantidad, str[1]),false);
				valor = "El producto ha sido añadido con exito";
				break;
			case 2:
				print("\nIngrese el nombre del producto que desea buscar: ");
				prod = sc.nextLine();
				prod = sc.nextLine();
				valor = inventario.search(prod, false);
				break;
			case 3:
				print("\nSe mostrara la informacion de los productos: ");
				print(inventario.showInventario(1) );
				
				break;
			case 4:
				print("\nSe mostrara la informacion de los productos de forma ordenada: ");
				print(inventario.showInventario(2) );
				break;
			case 5:
				print("\nSe mostraran las duplas de categoria y productos existentes:");
				print(inventario.showInventario(3) );
				break;
			case 6:
				print("\nSe mostraran las duplas de categoria y productos existentes de forma ordenada:");
				print(inventario.showInventario(4) );
				break;
			default:
				print("\nEsta saliendo del programa");
				return true;
			}
			print(valor);
			return false;
	}
	
	public static void main(String args[]) throws Exception
	{
		//Variables
		int opcion=0;
		boolean correcto = true;
		String path = "inventario.txt";
		Scanner sc = new Scanner(System.in);
		Inventario inventario = readFile(path, new HashMap<String, ArrayList<Producto> >());
		
		while(true){
			try{
				//asegurarse que ingresen una opcion valida
				print("\nIngrese el numero de la clase desea emplear: \n1. Hashmap \n2.TreeMap \n3.LinkedHashMap");
				//Para elegir el tipo de mapa a implementar
				while(correcto){
					try{
						opcion = sc.nextInt();
					}catch(Exception e)
					{
						sc.nextLine();
						print("Ingrese un numero");
					}
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
						break;
					}
				}
			
				
				while(true){
					if(menu(inventario))
					{
						inventario.save();
						return;
					}
					
				}
				
				
			}catch(Exception e){
				
				print("Error: "+ e.toString());
				
			}
		}
		
	}
}