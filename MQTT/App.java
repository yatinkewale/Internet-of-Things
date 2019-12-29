package app;
import java.util.logging.Level;
import java.util.logging.Logger;
import edu.neu.csye6200.Mqcn;
/**
 * 
 * @author Yatin Kewale
 *
 */
public class App 
{
	// static
	private static final Logger _Logger = Logger.getLogger(App.class.getName());
	private	static App _App;
// private
	private Mqcn _mqttConnector;
	// constructors
	// static methods
	public static void main(String[] args) 
	{
		_App = new App();
		try 
		{
			_Logger.info("Hello World");
			_App.start();
		}
		catch (Exception e) 
		{
			_Logger.log(Level.SEVERE, "Something bad happened.", e);
		}
	}
	// public methods
	public void start()
	{
		try {
			_mqttConnector = new Mqcn();
			if (_mqttConnector.connect()) {
				String msg 	 = "Testing 1..2..3...";
				String topic = "RAESSL /8";
				int    qos   = 2;
				_mqttConnector.sendMessage(topic, qos, msg);
			}
			else {
				_Logger.log(Level.WARNING, "Failed to connect to broker. ");
			}
		}catch (Exception e) {
			_Logger.log(Level.SEVERE, "Failed to start app.", e);
		}finally {
			if (_mqttConnector.disconnect()) {
				_Logger.info("Done");
			} else {
				_Logger.log(Level.WARNING, "Failed to disconnect from broker. ");
			}
		}
	}
	// private methods
}
