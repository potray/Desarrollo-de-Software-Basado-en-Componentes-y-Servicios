package p1;

/**
 * 
 * @author Daniel Salas
 *
 */
public interface Filter {
	/**
	 * Every class implementing FIlter interface execute this method.
	 * 
	 * @param number just a parameter. Each class implementing this interface will implement this method in a different way.
	 * @return just a return value. Each class implementing this interface will return a different value, according to the way it implements this method.
	 * 
	 */
	public double execute (double number);
}
