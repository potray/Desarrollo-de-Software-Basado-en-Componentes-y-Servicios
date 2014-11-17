package p1;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;


@ManagedBean(name = "client", eager = true)
@RequestScoped
public class Client {	
	
	private double value;	
	private FilterManager manager;

	public Client(){
		manager = new FilterManager();
		Filter speedCalculator = new CalculateSpeed();
		Filter ditanceCalculator = new CalculateDistance();
		Filter interfaceFilter = new Interface();
		
		manager.addFilter(speedCalculator);
		manager.addFilter(ditanceCalculator);
		manager.setTarget(interfaceFilter);
	}
	
	/**
	 * Tells <code>manager</code> to run all <code>execute</code> methods in every <code>Filter</code>.
	 */
	public void executeFilterManager (){
		manager.execute(value);
	}	
	
	/**
	 * @return the value
	 */
	public double getValue() {
		return value;
	}
	
	/**
	 * @param value the new value to set.
	 */
	public void setValue (double value){
		this.value = value;
	}
}
