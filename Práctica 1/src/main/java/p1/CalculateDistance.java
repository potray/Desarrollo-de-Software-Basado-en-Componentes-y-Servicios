package p1; 

/**
 * @author Daniel Salas
 */
public class CalculateDistance implements Filter{
	
	private double distance = 0;
	
	/**
	 * This method recibes a speed and return the distance traveled.
	 * 
	 * @param number current speed.
	 * @return distance traveled.
	 */
	@Override
	public double execute(double number) {
		distance =+ number;
		System.out.println("Current distance = " + distance);
		return distance;
	}
}
