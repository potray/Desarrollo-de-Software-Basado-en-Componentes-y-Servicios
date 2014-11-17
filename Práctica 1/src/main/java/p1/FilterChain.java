package p1;

import java.util.ArrayList;
import java.util.Iterator;


/**
 * This class has an array of instances of classes that implement the Filter interface.
 * It will call the <code>execute</code> method in every object in <code>filters</code> until the <code>execute</code> of <code>target</code> is executed.
 * 
 * 
 * 
 * @author  Daniel Salas
 *
 */
public class FilterChain implements Iterable<Filter>{
	private ArrayList <Filter> filters;

	public FilterChain (){
		filters = new ArrayList<Filter>();
	}
	
	/**
	 * Adds a new filter to <code>filters</code>.
	 * @param filter the filter to add.
	 */
	public void add (Filter filter){
		filters.add(filter);
	}

	/**
	 * Deletes the first element of <code>filters</code>.
	 */
	public void delete (){
		filters.remove(0);
	}

	@Override
	public Iterator<Filter> iterator() {
		Iterator<Filter> it = new Iterator<Filter>(){

			private int index = 0;

			@Override
			public boolean hasNext() {
				return index < filters.size() && filters.get(index) != null;
			}

			@Override
			public Filter next() {
				return filters.get(index++);
			}

		};
		return it;
	}
}
