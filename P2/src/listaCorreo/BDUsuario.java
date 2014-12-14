package listaCorreo;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;


public class BDUsuario {
	private static final String PERSISTENCE_UNIT_NAME = "P2";
	private static EntityManagerFactory factoria;


	public static void insertar(Usuario usuario) {
		//Conseguir la instancia del EntityManager
		factoria = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
		EntityManager em = factoria.createEntityManager();

		//Insertar el usuario		
		em.getTransaction().begin();
		em.persist(usuario);
		em.getTransaction().commit();
		em.close();
		
		System.out.println("INSERTADO");
	}

	public static void actualizar(Usuario usuario) {
		//Conseguir la instancia del EntityManager
		factoria = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
		EntityManager em = factoria.createEntityManager();
		
		//Encontrar el usuario
		Usuario u = em.find(Usuario.class, usuario.getIdUsuario());
		
		//Actualizarlo
		em.getTransaction().begin();
		u.setNombre(usuario.getNombre());
		u.setApellido(usuario.getApellido());
		u.setEmail(usuario.getEmail());
		em.getTransaction().commit();
		em.close();
		
		System.out.println("ACTUALIZADO");
	}

	public static void eliminar(Usuario usuario) {
		//Conseguir la instancia del EntityManager
		factoria = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
		EntityManager em = factoria.createEntityManager();
		
		//Encontrar el usuario
		Usuario u = em.find(Usuario.class, usuario.getIdUsuario());
		
		//Eliminarlo
		em.getTransaction().begin();
		em.remove(u);
		em.getTransaction().commit();
		em.close();
		
		System.out.println("ELIMINADO");
	}

	public static Usuario seleccionarUsuario(String email) {
		//Conseguir la instancia del EntityManager
		factoria = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
		EntityManager em = factoria.createEntityManager();
		
		//Devolver el usuario
		String consulta = "SELECT NEW listaCorreo.Usuario (u.email, u.nombre, u.apellido) FROM Usuario AS u WHERE u.email = '"+ email +"'";
		System.out.println(consulta);
		Query q = em.createQuery(consulta);
		return (Usuario) q.getResultList().get(0);
	}

	public static boolean existeEmail(String email) {
		//Conseguir la instancia del EntityManager
		factoria = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
		EntityManager em = factoria.createEntityManager();
		
		//Hacer la consulta
		String consulta = "SELECT u FROM Usuario u WHERE u.email = '" + email + "'";
		System.out.println(consulta);
		Query q = em.createQuery(consulta);
		List <Usuario> result = q.getResultList();
		
		return !result.isEmpty();
	}
	
	public static List<Usuario> seleccionarTodosUsuarios (){
		//Conseguir la instancia del EntityManager
		factoria = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
		EntityManager em = factoria.createEntityManager();		

		//Hacer la consulta
		Query q = em.createQuery("SELECT u FROM Usuario u");
		List <Usuario> result = q.getResultList();
		return result;		
	}
}
