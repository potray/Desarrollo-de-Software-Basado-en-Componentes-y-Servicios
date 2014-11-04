package prueba;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

@ManagedBean(name = "controladorNavegacion", eager = true)
@RequestScoped
public class ControladorNavegacion implements Serializable{ //Eete es el MB
	private static final long serialVersionUID = 1L;
	@ManagedProperty(value="#{param.pageId}")
	private String pageId;
	
	public String mostrarPagina(){
	    if(pageId == null){
	       return "home";
	    }
	    if(pageId.equals("1")){
	      return "pagina1";
	      }else if(pageId.equals("2")){
	        return "pagina2";
	        }else{
	          return "home";
	    }
	}
	
	public String getPageId(){
		return pageId;
	}
	
	public void setPageId(String newPageId){
		pageId = newPageId;
	}
	
	public String procesarPagina1(){
		return "pagina1";
	}
	public String procesarPagina2(){
		return "pagina2";
	}
	
	public String irAHome(){
		return "home";		
	}
}