package prueba;

import java.util.HashMap;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.event.ValueChangeEvent;

@ManagedBean(name = "datosUsuario", eager = true)
@RequestScoped
public class DatosUsuario {
	private static String paisSeleccionado = "Reino Unido";

	private static HashMap<String, String> countries = new HashMap<String, String>();
	
	static{
		countries.put("uk", "Reino Unido");
		countries.put("fr", "Francia");
		countries.put("es", "España");
	}
	
	public void localCambiado(ValueChangeEvent e){
		//asignar un nuevo valor al pais
		setPaisSeleccionado(e.getNewValue().toString());
	}

	public String getPaisSeleccionado() {
		return paisSeleccionado;
	}

	public void setPaisSeleccionado(String paisSeleccionado) {
		this.paisSeleccionado = paisSeleccionado;
	}
	
	public HashMap<String, String> getCountries (){
		return countries;
	}
}
