package prueba;

import javax.faces.context.FacesContext;
import javax.faces.event.AbortProcessingException;
import javax.faces.event.ValueChangeEvent;
import javax.faces.event.ValueChangeListener;

public class CambioLocalListener implements ValueChangeListener {

	private String paisSeleccionado;
	
	public void processValueChange(ValueChangeEvent arg0) throws AbortProcessingException {
		  //aceso al bean de pais directamente
		  DatosUsuario datosUsuario = (DatosUsuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("datosUsuario");
		  datosUsuario.setPaisSeleccionado(arg0.getNewValue().toString());
	}
	
	public String getPaisSeleccionado() {
		return paisSeleccionado;
	}

	public void setPaisSeleccionado(String paisSeleccionado) {
		this.paisSeleccionado = paisSeleccionado;
	}

}
