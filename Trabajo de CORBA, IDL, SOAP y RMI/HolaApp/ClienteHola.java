package HolaApp;
import org.omg.CORBA.ORB;
import org.omg.CosNaming.NamingContextExt;
import org.omg.CosNaming.NamingContextExtHelper;
public class ClienteHola {
	static Hola implHola;
	public static void main(String args[])
	{
		try{
			// crear e inicializar el ORB
			ORB orb = ORB.init(args, null);
			// obtener una referencia de objetos-CORBA del Servicio de Nombres
			org.omg.CORBA.Object refObj = orb.resolve_initial_references("NameService");

			// Usar el NamingContextExt y el método narrow para convertir la referencia anterior a su tipo correspondiente en Java
			NamingContextExt ncRef = NamingContextExtHelper.narrow(refObj);

			// obtener una referencia de objetos del objeto remoto a partir de su nombre "Hola" desde el Servicio de Nombres
			String nombre= "Hola";
			// convertir la referencia de objetos anterior a una referencia a un objeto de la clase ImplHola
			implHola = HolaHelper.narrow(ncRef.resolve_str(nombre));

			System.out.println("Se ha obtenido un manejador del objeto servidor: " + implHola);
			System.out.println(implHola.decirHola());
			try {
				Thread.sleep(100000);//para esperar un poco antes de terminar...
			}
			catch (Exception ex) { }
			// Ahora si se puedde terminar al objeto de la clase ImplHola (con el método "tirar") 
			implHola.tirar();

		} catch (Exception e) {
			System.out.println("ERROR : " + e) ;
			e.printStackTrace(System.out);
		}
	}
}
