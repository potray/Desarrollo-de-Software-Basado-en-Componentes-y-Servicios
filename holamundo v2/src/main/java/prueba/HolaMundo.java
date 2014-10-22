package prueba;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

@ManagedBean(name = "holaMundo", eager = true)
@RequestScoped
public class HolaMundo {
	@ManagedProperty(value="#{mensaje}")
	private Mensaje mensajeBean;
	
	private String mensaje;
	
	public HolaMundo(){
		System.out.println("�Hola Mundo empezado!");
	}
	
	public String getMensaje(){		
		if (mensajeBean != null)		
			mensaje = mensajeBean.getMensaje();
		else
			System.out.println("MensajeBean est� a null");
		
		return mensaje;
	}
	
	public void setMensajeBean (Mensaje newMensajeBean){
		mensajeBean = newMensajeBean;
	}
}
