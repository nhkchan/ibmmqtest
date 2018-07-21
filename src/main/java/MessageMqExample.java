package main.java;

import javax.jms.Connection;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageConsumer;
import javax.jms.Queue;
import javax.jms.QueueConnection;
import javax.jms.QueueConnectionFactory;
import javax.jms.Session;

import com.ibm.mq.*;
import com.ibm.msg.client.jms.JmsConnection;
import com.ibm.msg.client.jms.JmsConnectionFactory;
import com.ibm.msg.client.jms.JmsConstants;
import com.ibm.msg.client.jms.JmsFactoryFactory;
import com.ibm.msg.client.jms.JmsQueue;
import com.ibm.msg.client.wmq.WMQConstants;

public class MessageMqExample {

	public static String qManagerName = "QM1";
	public static String testqueue = "DEV.QUEUE.1";
	public static String channel = "DEV.ADMIN.SVRCONN";
	public static String hostname = "192.168.1.238"; 
	public static int port = 1414;
	public static String user = "admin";
	public static String password = "passw0rd";
	
	
	public static void main(String[] args) throws InterruptedException {
		//Produce Message
		/*
		MqSample mqs = new MqSample(qManagerName);
		System.out.println("Start INIT");
		mqs.init();
		System.out.println("END INIT");
		System.out.println("Start MQSEND");
		mqs.send(testqueue);
		System.out.println("END MQSEND");
		*/
		//Consume Message
		try {
			JmsFactoryFactory ff = JmsFactoryFactory.getInstance(JmsConstants.WMQ_PROVIDER);
			JmsConnectionFactory factory = ff.createConnectionFactory();
			factory.setIntProperty(WMQConstants.WMQ_CONNECTION_MODE,
                    WMQConstants.WMQ_CM_CLIENT);
			factory.setStringProperty(WMQConstants.WMQ_QUEUE_MANAGER, qManagerName);
			factory.setStringProperty(WMQConstants.WMQ_HOST_NAME, hostname);
			factory.setIntProperty(WMQConstants.WMQ_PORT, port);
			factory.setStringProperty(WMQConstants.WMQ_CHANNEL, channel);
			factory.setStringProperty(WMQConstants.WMQ_APPLICATIONNAME, "NHKchan");
			JmsQueue q1 = ff.createQueue(testqueue);
			Connection connection = factory.createConnection(user,password);
			Session session = connection.createSession();
			MessageConsumer consumer = session.createConsumer(q1);
			consumer.setMessageListener(new MqListen());
	        connection.start();
	        Thread.sleep(1000);
		} catch (JMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
