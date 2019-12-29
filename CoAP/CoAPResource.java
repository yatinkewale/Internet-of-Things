package coapcon;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.eclipse.californium.core.*;
import org.eclipse.californium.core.coap.MediaTypeRegistry;

public class CoapResource {
	private CoapClient _client;
	private static final String DEFAULT_PROTOCOL = "coap"; 
	private static final String DEFAULT_HOST = "californium.eclipse.org"; 
	private static final int DEFAULT_PORT = 5683; 
	private static final Logger _Logger = Logger.getLogger(Driver.class.getName()); 
	private String _protocol; 
	private String _host; 
	private int _port; 
	private String _serverAddr; 

	public CoapResource()
	{
		super(); 
		_protocol = DEFAULT_PROTOCOL;
		_host = DEFAULT_HOST; 
		_port = DEFAULT_PORT; 
		_serverAddr = DEFAULT_PROTOCOL + "://" + DEFAULT_HOST + ":" + DEFAULT_PORT; _Logger.info("Broker address: " + _serverAddr);
	}
	
// public method for GET message
	public void getMessage(String topic)
	{
		try { 
			if(_client == null)
			{ _client = new CoapClient(_serverAddr); } 
			else { _client.setURI(_serverAddr + "/" + topic); 
			}
			_client.get();
		}catch (Exception e) { _Logger.log(Level.SEVERE, "Something went wrong!");}
	}


	
	// public method for POST message	
	public void postMessage(String topic, String string2)
	{
		try { 
			if(_client == null)
			{ _client = new CoapClient(_serverAddr); } 
			else { _client.setURI(_serverAddr + "/" + topic); 
			}
			_client.post("test",MediaTypeRegistry.TEXT_PLAIN);
		}catch (Exception e) { _Logger.log(Level.SEVERE, "Something went wrong!");}
	}
	
	
	// public method for DISCOVER message
	public void discoverMessage(String topic)
	{
		try { 
			if(_client == null)
			{ _client = new CoapClient(_serverAddr); } 
			else { _client.setURI(_serverAddr + "/" + topic); 
			}
			_client.discover();
		}catch (Exception e) { _Logger.log(Level.SEVERE, "Something went wrong!");}
	}
	
	// public method for PUT message
	public void putMessage(String topic, String string2)
	{
		try { 
			if(_client == null)
			{ _client = new CoapClient(_serverAddr); } 
			else { _client.setURI(_serverAddr + "/" + topic); 
			}
			_client.put("test",MediaTypeRegistry.TEXT_PLAIN);
		}catch (Exception e) { _Logger.log(Level.SEVERE, "Something went wrong!");}
	}
	
	// public method for DELETE message
	public void deleteMessage(String topic) {
		try { 
			if(_client == null)
			{ _client = new CoapClient(_serverAddr); } 
			else { _client.setURI(_serverAddr + "/" + topic); 
			}
			_client.delete();
		}catch (Exception e) { _Logger.log(Level.SEVERE, "Something went wrong!");}
	}}
