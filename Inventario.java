import java.util.*;
import java.io.FileWriter;
import java.io.IOException;

public class Inventario<T extends Map>{
		
		//Patron Singleton
		private static Inventario inventario = new Inventario();
		T inventario_info;
		
		private Inventario()
		{
			
		}
		
		//Es util para el patron Factory utilizando
		private Inventario(T t)
		{
			inventario_info = t;
		}
		
		//Asignamos el tipo de Map
		public static <G extends Map> Inventario create(G g)
		{
			inventario = new Inventario<G>(g);
			return inventario;
		}
		
		//Añade un valor al mapa del inventario
		public void add(String str, Producto producto, boolean inicio)
		{
			boolean existe = false;
			try
			{
				ArrayList<Producto> productos = new ArrayList();
				
				if(inventario_info.containsKey(str.trim()))
				{
					
					productos = (ArrayList<Producto>)inventario_info.get(str);
					for (Producto prod : productos)
					{
						
						if(prod.nombre.trim().equalsIgnoreCase(producto.nombre.trim()))
						{
							
							prod.cantidad = prod.cantidad + producto.cantidad;
							existe = true;							
						}
						
					}
					if(!existe && inicio)
					{
						
						productos.add(producto);
					
					}
					else if(!existe && !inicio)
					{
						productos.add(producto);
						
					}
				}
				else if(inicio)
				{
					productos.add(producto);
				}
				else
				{
					
					throw new Exception("Error: Categoria inexistente");
				
				}
				inventario_info.put(str, productos);
			}catch(Exception e)
			{
				System.out.println("Error en Inventario: "+ e.toString());
			}
		}
		
		public String search(String name_producto, boolean bool )
		{
			String info = "No se encontro el prodcuto.";
			try{
				
				Iterator llaves = inventario_info.keySet().iterator();
				while(llaves.hasNext())
				{
					String llave = llaves.next().toString();
					ArrayList<Producto> productos = (ArrayList<Producto>)inventario_info.get(llave);
					for(Producto prod : productos)
					{
						if (prod.nombre.trim().equalsIgnoreCase(name_producto.trim()) && !bool)
						{
							info = "El producto pertenece a la categoria " + llave;
						}
						else if(prod.nombre == name_producto && bool)
						{
							info = "El " + name_producto +" pertenece a la categoria " + llave + " y hay " + prod.cantidad.toString() + " en existencia";
						}
					}
				}
				return info;
			}catch(Exception e)
			{
				
				return "Error en serach: "+ e.toString();
			}
		}
		
		public String showInventario(Integer caso)
		{
			String info = "";
			try{
				
				Iterator llaves = inventario_info.keySet().iterator();
				while(llaves.hasNext())
				{
					String llave = llaves.next().toString();
					ArrayList<Producto> productos = (ArrayList<Producto>)inventario_info.get(llave);
					for(Producto prod : productos)
					{
						switch(caso)
						{
							case 1:
								info = info + "\nEl prodcuto " + prod.nombre +" pertenece a la categoria " + llave + " y hay " + prod.cantidad.toString() + " en existencia";
								break;
							case 2:
								info = info + "\nA la categoria " + llave + " pertenece " + prod.nombre + " y hay " + prod.cantidad.toString() + " en existencia";
								break;
							case 3:
								info = info + "\nEl producto " + prod.nombre + " pertenece a la categoria " + llave;
								break;
							case 4:
								info = info + "\nA la categoria " + llave + " pertenece " + prod.nombre;
								break;
						}
					}
				}
				return info;
			}catch(Exception e)
			{
				
				return "Error al mostrar inventario: "+ e.toString();
			
			}
		}
		
		public void save()
		{
			try
			{
				String inv = "";
				
				Iterator llaves = inventario_info.keySet().iterator();
				while(llaves.hasNext())
				{
					String llave = llaves.next().toString();
					ArrayList<Producto> productos = (ArrayList<Producto>)inventario_info.get(llave);
					
					for(Producto prod : productos)
					{
						inv = inv + "\n"+llave.toString() + " | "+prod.nombre +" | " + prod.cantidad;
					}
				}
				
				FileWriter myWriter = new FileWriter("inventario.txt");
				myWriter.write(inv);
				myWriter.close();
			}
			catch(Exception e)
			{
				System.out.println("Error al guardar inventario");
			}
			
		}
} 	