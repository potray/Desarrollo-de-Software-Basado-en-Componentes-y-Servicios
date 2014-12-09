package listaCorreo;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ListaCorreosServlet extends HttpServlet{

	private static final long serialVersionUID = 1L;

	public void init () throws ServletException{
		System.out.println("Servlet inicializado!!");
	}
	
	@Override
	protected void doPost(HttpServletRequest peticion, HttpServletResponse respuesta) throws ServletException, IOException {
		String url = "/index.html";

		// Obtener la accion a partir de "peticion" (getParameter("action");
		String action = peticion.getParameter("action");
		//Obtener el resto de parámetros
		String email = peticion.getParameter("email");
		String nombre = peticion.getParameter("nombre");
		String apellidos = peticion.getParameter("apellidos");
		
		//Crear un objeto Usuario que será con el que se trabaje con la clase BDUsuario.
		Usuario usuario = new Usuario(email, nombre, apellidos);

		// Realizar la accion y asignar el URL a la pagina apropiada
		switch (action){
		case "insertar": 
			//Miro si existe un usuario con el mismo email. Si existe muestro mensaje de erorr, si no lo inserto.
			if (BDUsuario.existeEmail(email)){
				//TODO: crear mensaje de error
				System.out.println("Error, email existe");
			}else{
				BDUsuario.insertar(usuario);
			}
			break;
		case "actualizar":
			//Simplemente se actualiza
			BDUsuario.actualizar(usuario);
			break;
		case "eliminar":
			//Simplemente se elimina
			BDUsuario.eliminar(usuario);
			break;
		case "seleccionar":
			//Se selecciona el usuario y se muestran sus datos
			Usuario usuarioExistente = BDUsuario.seleccionarUsuario(email);
			System.out.println("Encontrado el usuario");
			//TODO: Mostrar los datos
		}
		// almacenar los datos en el objeto de Usuario
		//TODO ¿?

		// validar los parametros utilizando los metodos de BDUsuario; si existe la direccion de email en la base de datos, mostrar un mensaje y pedir otra direccion
		//Insertar los datos del usuario
		
		/*
		request.setAttribute("user", user);
		request.setAttribute("message", message);*/

		getServletContext().getRequestDispatcher(url).forward(peticion, respuesta);

	}
	
	
}
