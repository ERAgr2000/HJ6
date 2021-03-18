import java.util.*;

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
				
				if(inventario_info.containsKey(str))
				{
					productos = (ArrayList<Producto>)inventario_info.get(str);
					for (Producto prod : productos)
					{
						if(prod.nombre == producto.nombre)
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
						throw new Exception("Error: Categoria inexistente");
					}
				}
				else
				{
					productos.add(producto);
				}
				inventario_info.put(str, productos);
			}catch(Exception e)
			{
				System.out.println("Error en Inventario: "+ e.toString());
			}
		}
		
		public String search(String name_producto, boolean bool )
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
						if (prod.nombre == name_producto && !bool)
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
		
		public String inventario()
		{
			String list_inventario;
			for(int i = 0; i < inventario_info.size(); i = i+1)
			{
				Iterator it1 = inventario_info.keySet().iterator();
				Iterator it2 = inventario_info.values().iterator();
			}
			return "";
		}
} 	