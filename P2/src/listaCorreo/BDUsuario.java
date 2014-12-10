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
		
		//Hacer la consulta
		Query q = em.createQuery("UPDATE Usuario u SET u.nombre = '" + usuario.getNombre() + "', u.apellido = '" + usuario.getApellido() + "' WHERE u.email = '" + usuario.getEmail() + "'");
		q.executeUpdate();
	}

	public static void eliminar(Usuario usuario) {
		//Conseguir la instancia del EntityManager
		factoria = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
		EntityManager em = factoria.createEntityManager();
		
		//Hacer la consulta
		Query q = em.createQuery("DELETE FROM Usuario u WHERE u.email = '" + usuario.getEmail() + "'");
		q.executeUpdate();
	}

	public static Usuario seleccionarUsuario(String email) {
		//Conseguir la instancia del EntityManager
		factoria = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
		EntityManager em = factoria.createEntityManager();
		
		//Hacer la consulta
		if (existeEmail (email)){
			Query q = em.createQuery("SELECT NEW listaCorreo.Usuario (u.email, u.nombre, u.apellido) FROM Usuario AS u WHERE u.email = '"+ email +"'");
			return (Usuario) q.getResultList().get(0);
		}else 
			return null;
	}

	public static boolean existeEmail(String email) {
		//Conseguir la instancia del EntityManager
		factoria = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
		EntityManager em = factoria.createEntityManager();
		
		//Hacer la consulta
		Query q = em.createQuery("SELECT u FROM Usuario u WHERE u.email = '" + email + "'");
		List <Usuario> result = q.getResultList();
		
		return !result.isEmpty();
	}
}
