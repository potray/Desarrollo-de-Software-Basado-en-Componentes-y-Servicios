package listaCorreo;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ListaCorreosServlet extends HttpServlet{

	private static final long serialVersionUID = 1L;

	public void init () throws ServletException{
		System.out.println("Servlet inicializado!!");		
	}
	
	
	protected void doGet(HttpServletRequest peticion, HttpServletResponse respuesta) throws ServletException ,IOException {		
		List <Usuario> usuarios = BDUsuario.seleccionarTodosUsuarios();
		peticion.setAttribute("usuarios", usuarios);
		
		peticion.getRequestDispatcher("index.jsp").forward(peticion, respuesta);
	};
	
	
	protected void doPost(HttpServletRequest peticion, HttpServletResponse respuesta) throws ServletException, IOException {
		System.out.println("POST");
		String url = "/index.html";

		// Obtener la accion a partir de "peticion" (getParameter("action");
		String action = peticion.getParameter("action");
		//Obtener el resto de parámetros
		String email = peticion.getParameter("email");
		String nombre = peticion.getParameter("nombre");
		String apellido = peticion.getParameter("apellido");
		String idUsuarioString = peticion.getParameter("idUsuario");
		
		//Crear un objeto Usuario que será con el que se trabaje con la clase BDUsuario.
		Usuario usuario;
		if (idUsuarioString != null) 
			usuario = new Usuario(email, nombre, apellido, Long.valueOf(idUsuarioString));
		else
			usuario = new Usuario (email, nombre, apellido);

		// Realizar la accion y asignar el URL a la pagina apropiada
		switch (action){
		case "insertar": 
			//Miro si existe un usuario con el mismo email. Si existe muestro mensaje de erorr, si no lo inserto.
			if (BDUsuario.existeEmail(email)){
				peticion.setAttribute("mensaje", "errorUsuario");
			}else{
				BDUsuario.insertar(usuario);
			}
			break;
		case "actualizar":
			//Se actualiza y se comunica
			if (BDUsuario.existeEmail(email)){
				peticion.setAttribute("mensaje", "errorUsuario");				
			}
			else{
				BDUsuario.actualizar(usuario);
				peticion.setAttribute("mensaje", "actualizado");
			}
			break;
		case "eliminar":
			//Se elimina y se comunica
			BDUsuario.eliminar(usuario);
			peticion.setAttribute("mensaje", "eliminado");
			break;
		case "seleccionar":
			//Se selecciona el usuario si existe y se devuelve. Si no, se notifica
			if(BDUsuario.existeEmail(email)){				
				Usuario usuarioExistente = BDUsuario.seleccionarUsuario(email);
				peticion.setAttribute("usuario", usuarioExistente);				
			}else
				peticion.setAttribute("mensaje", "usuarioNoExiste");
			break;
		}
		
		List <Usuario> usuarios = BDUsuario.seleccionarTodosUsuarios();
		peticion.setAttribute("usuarios", usuarios);
		
		peticion.getRequestDispatcher("index.jsp").forward(peticion, respuesta);

	}
	
	
}
