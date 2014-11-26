package p1;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URISyntaxException;

import javax.faces.bean.ManagedBean;

/**
 * This class is the interface manager for the buttons of the xhtml.
 * It also manages the style of the components.
 * 
 * @author Daniel Salas
 *
 */


@ManagedBean(name = "interface")
public class Interface implements Filter{

	private final String turnOnBtnStyleTurnOn = "btn btn-success";
	private final String turnOnBtnStyleTurnOff = "btn btn-danger";
	
	private final String turnOnBtnTextTurnOn = "Turn On";
	private final String turnOnBtnTextTurnOff = "Turn Off";
	
	private final String accelerateBtnStyleNormal = "btn btn-primary";
	private final String accelerateBtnStyleAccelerating = "btn bnt-primary active";
	
	private final String statusOn = "On";
	private final String statusOff = "Off";	
	private final String statusAccelerating = "Accelerating";
	
	private final String interfaceURL = "http://localhost:8080/P1/interface.jsf";

	private static String turnOnBtnStyle;
	private static String turnOnBtnText;
	private static String status;
	private static String accelerateBtnStyle;
		
	/**
	 * @return the accelerateBtnStyle
	 */
	public String getAccelerateBtnStyle() {
		return accelerateBtnStyle;
	}

	/**
	 * @return the turnOnBtnStyle
	 */
	public String getTurnOnBtnStyle() {
		return turnOnBtnStyle;
	}

	/**
	 * @return the turnOnBtnText
	 */
	public String getTurnOnBtnText() {
		return turnOnBtnText;
	}

	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}	
	
	@Override
	public double execute(double number) {
		System.out.println("Executing interface");
		turnOnBtnStyle = turnOnBtnStyleTurnOn;
		turnOnBtnText = turnOnBtnTextTurnOn;
		accelerateBtnStyle = accelerateBtnStyleNormal;
		status = statusOff; 
		if (Desktop.isDesktopSupported()) {
            // Windows
				try {
					java.net.URI url = new java.net.URI (interfaceURL);
		            Desktop.getDesktop().browse(url);
				} catch (URISyntaxException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			
		}
		//System.out.println("Status = " + status);
		return 0;
	}
	
	public void changeTurnOnStyle (){
		//System.out.println("status = " + status);
		if (status == statusOff){
			System.out.println("Turned on");
			turnOnBtnStyle = turnOnBtnStyleTurnOff;
			turnOnBtnText = turnOnBtnTextTurnOff;
			status = statusOn;
		}else{
			System.out.println("Turned off");
			turnOnBtnStyle = turnOnBtnStyleTurnOn;
			turnOnBtnText = turnOnBtnTextTurnOn;			
			status = statusOff;
			accelerateBtnStyle = accelerateBtnStyleNormal;
		}
		//System.out.println("Now status = " + status + "\n");
	}
	
	public void accelerate (){
		if (status == statusOn){
			System.out.println("Accelerating");
			status = statusAccelerating;
			accelerateBtnStyle = accelerateBtnStyleAccelerating;
		}else if (status == statusAccelerating){
			System.out.println("Not accelerating");
			status = statusOn;
			accelerateBtnStyle = accelerateBtnStyleNormal;
		}
	}

}
