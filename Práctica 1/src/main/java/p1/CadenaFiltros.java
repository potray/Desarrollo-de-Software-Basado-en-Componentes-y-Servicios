package p1;

import java.util.ArrayList;

public class CadenaFiltros {
	private ArrayList <Filtro> filtros;
	private Interfaz objetivo;
	
	public void ejecutar (double peticion){
		for (Filtro filtro: filtros){
			System.out.println("Nueva velocidad (m/s)" + filtro.ejecutar(peticion));
		}
		objetivo.ejecutar(peticion);
	}
	
	public void setObjetivo (Interfaz objetivo){
		this.objetivo = objetivo;
	}
}
