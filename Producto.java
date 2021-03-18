public class Producto{

	public Integer cantidad;
	public String nombre;
	
	public Producto(Integer a, String b)
	{
		cantidad = a;
		nombre = b.trim();
	}
	
	public String getInfo()
	{
		String info = nombre + " tiene " + cantidad.toString() + " en exitencia.";
		return info;
	}
}