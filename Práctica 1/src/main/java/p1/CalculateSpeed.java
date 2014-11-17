package p1;

/**
 * @author Daniel Salas
 */
public class CalculateSpeed implements Filter {

	private double speed = 0;
	
	/**
	 * This method increases the speed.
	 * 
	 * @param number an increment in the speed.
	 * @return current speed.
	 */
	@Override
	public double execute(double number) {
		speed += number;
		System.out.println("Current speed = " + speed);
		return speed;
	}
}
