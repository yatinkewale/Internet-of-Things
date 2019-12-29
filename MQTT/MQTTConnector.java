package edu.neu.csye6200;


import java.util.logging.Level;
import java.util.logging.Logger;

import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

/**
 * @author Yatin Kewale
 *
 */
public class Mqcn implements MqttCallback
{
	public static final String DEFAULT_PROTOCOL = "tcp";
	public static final String DEFAULT_HOST = "test.mosquitto.org";
	public static final int    DEFAULT_PORT = 1883;
	private static final Logger _Logger = Logger.getLogger(Mqcn.class.getName());

	// params

	private String _clientID;
	private String _protocol;
	private String _host;
	private int    _port;
	private String _brokerAddr;
	private MqttClient _client;


	public Mqcn()
	{
		super();
		_clientID = MqttClient.generateClientId();
		_protocol = DEFAULT_PROTOCOL;
		_host 	  = DEFAULT_HOST;
		_port     = DEFAULT_PORT;

		_brokerAddr = DEFAULT_PROTOCOL + "://" + DEFAULT_HOST + ":" + DEFAULT_PORT;
		_Logger.info("Broker Address: " + _brokerAddr);


	}

	// public methods
	public boolean connect()
	{
		boolean success = false;

		MemoryPersistence persistence = new MemoryPersistence(); 


		try
		{
			_client = new MqttClient(_brokerAddr, _clientID, persistence);

			MqttConnectOptions connOpts = new MqttConnectOptions();
			connOpts.setCleanSession(true);

			_Logger.info("Connecting to a broker: " + _brokerAddr);
			_client.setCallback(this);
			_client.connect(connOpts);
			_Logger.info("Connected to a broker: " + _brokerAddr);

			long waitMillis = 1500L;

			_Logger.info("Waiting at least " + waitMillis + " seconds for ping...");

			try{
				Thread.sleep(waitMillis);	
			}
			catch (Exception e){
				//ignore
			}

			success = true;

		} 
		catch (MqttException e)
		{
			_Logger.log(Level.SEVERE, "Failed to connect to broker: " + _brokerAddr);
		}

		return success;

	}

	
	
	public boolean disconnect()
	{
		boolean success = false;

		try {
			_client.disconnect();
			_Logger.info("Disconnected from Broker" + _brokerAddr);
			success = true;
		}
		catch (Exception e){
			_Logger.log(Level.SEVERE, "Failed to disconnect from broker: " + _brokerAddr);
		}

		return success;
	}

	
	
	public void sendMessage(String topic, int qosLevel, String msg)
	{
		try {
			_Logger.info("Publishing message... ");
			MqttMessage message = new MqttMessage(msg.getBytes());
			message.setQos(qosLevel);
			_client.subscribeWithResponse("SDAP");
			_client.publish(topic, message);
			_Logger.info("Message sync published: " + message.getId() + " - " + message);
			Thread.sleep(2000);
			_client.unsubscribe(topic);
			_Logger.info("Client Unsubscribed to topic: " + topic);
		}
		catch (Exception e) {
			_Logger.log(Level.SEVERE, "Failed to publish sync message", e);
		}
	}


	@Override
	public void connectionLost(Throwable arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deliveryComplete(IMqttDeliveryToken arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void messageArrived(String arg0, MqttMessage arg1) throws Exception {
		_Logger.info("Client Unsubscribed to topic: SDAP" + arg1.toString());
		// TODO Auto-generated method stub

	}




}
