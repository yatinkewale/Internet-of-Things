package coapcon;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * 
 * @author Yatin Kewale
 *
 */
public class Driver 
{
	// static
	private static final Logger _Logger = Logger.getLogger(Driver.class.getName());
	private	static Driver _Driver;
	// private
	private static CoapResource _cp;
	// constructors
	// static methods
	public static void main(String[] args) 
	{
		_Driver = new Driver();
		Driver.start();}

	public static void start()
	{
		try {

			_cp = new CoapResource();

			_cp.getMessage("test");

			_cp.putMessage("test", "Hi there");
			_cp.discoverMessage();


			_cp.postMessage("test", "Got it");
			_cp.deleteMessage("test");
			_Logger.log(Level.SEVERE, "Done%");


		}catch (Exception e) {
			_Logger.log(Level.SEVERE, "Failed to start Driver.", e);
		}
	}
	
}
