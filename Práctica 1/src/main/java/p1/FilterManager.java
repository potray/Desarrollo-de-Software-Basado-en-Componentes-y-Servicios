package p1;

public class FilterManager {
	private FilterChain filterChain;
	
	private Filter target;
	
	public FilterManager (){
		filterChain = new FilterChain();
	}
	
	/**
	 * Executes all <code>execute</code> method in every item in <code>filters</code>.
	 * @param petition a number to use as a parameter in every <code>execute</code> method.
	 */
	public void execute (double petition){
		for (Filter filter: filterChain){
			filter.execute(petition);
		}
		target.execute(petition);
	}
	
	/**
	 * Sets a new target.
	 * @param target the new target.
	 */
	public void setTarget (Filter target){
		this.target = target;
	}
	
	/**
	 * Adds a new filter to <code>filterChain</code>
	 * @param filter the filter to add.
	 */
	public void addFilter (Filter filter){
		filterChain.add(filter);
	}
}
