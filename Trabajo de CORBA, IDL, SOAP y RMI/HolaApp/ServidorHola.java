package HolaApp;
//Implementaci�n del sirviente

import org.omg.CosNaming.*;
import org.omg.CORBA.*;
import org.omg.PortableServer.*;
import org.omg.PortableServer.POA;

class ImplHola extends HolaPOA {
	private ORB orb;


	public void ajustarORB(ORB orb_val) {
		orb = orb_val;
	}

	// implementar el m�todo decirHola()
	public String decirHola() {

		return "\nHola a todos !!\n";
	}

	// implementar el m�todo tirar()
	public void tirar() {
		orb.shutdown(false);
	}
}

//Implementaci�n del servidor
public class ServidorHola {
	public static void main(String args[]) {
		try{
			// crear e inicializar el ORB
			ORB orb = ORB.init(args, null);

			// obtener una referencia de objetos-CORBA para localizar el POA raiz
			POA poaRaiz = POAHelper.narrow(orb.resolve_initial_references("RootPOA"));

			//el m�todo narrow permite transmitir un referencia CORBA a Java
			// activar el objeto manejador del POA
			poaRaiz.the_POAManager().activate();

			// crear un objeto "sirviente" (de la clase ImplHola programada m�s arriba) que envie el mensaje "Hola a todos!"
			ImplHola implHola = new ImplHola();

			// registrar el objeto "sirviente" en el ORB (utilizar el m�todo ajustarORB(...)
			implHola.ajustarORB(orb);

			// obtener una referencia de objeto-CORBA del objeto "sirviente" desde el POA
			org.omg.CORBA.Object ref = poaRaiz.servant_to_reference(implHola);

			//Convertir la referencia anterior a su tipo correspondiente en Java (m�todo narrow) 
			Hola href = HolaHelper.narrow(ref);

			// obtener una referencia de objetos-CORBA que apunte al contexto ra�z del Servicio de Nombres 
			org.omg.CORBA.Object objRef = orb.resolve_initial_references("NameService");
			// Usar NamingContextExt que es parte de la especificaci�n Servicio de Nombres
			// para convertir la referncia de objetos anterior a su tipo correspondiente en Java
			NamingContextExt ncRef = NamingContextExtHelper.narrow(objRef);

			// introducir en la referencia de objetos del servicio de nombres anterior
			// un "string" que porporcione un nombre simb�lico (f�cil de recordar) al servicio que estamos implementando
			String nombre= "Hola";
			NameComponent camino[] = ncRef.to_name(nombre);


			// Ligar la referencia completa (inlcuyeno el nombre del servicio) a la referencia de objetos dee "sirviente"
			ncRef.rebind(camino, href);
			System.out.println("Servidor Hola: preparado y esperando ...");
			// ejecutar el objeto "orb" y esperar las las llamadas de los clientes
			orb.run();
		}

		catch (Exception e) {
			System.err.println("ERROR: " + e);
			e.printStackTrace(System.out);
		}

		System.out.println("Servidor Hola Terminando ...");

	}
}
