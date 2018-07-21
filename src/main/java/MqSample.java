package main.java;

import java.io.IOException;

import com.ibm.mq.*;

public class MqSample {
	private String qMgr;
	
	//public static String hostname = "192.210.166.46";
	public static String hostname = "192.168.64.1";
	public static String channel = "DEV.ADMIN.SVRCONN";
	public static String user = "admin";
	public static String password = "passw0rd";
	
	public MqSample(String qMgr) {
		this.qMgr = qMgr;
	}

	
	public void init()
	  {
	     // Set up MQSeries environment
		 MQEnvironment.hostname = hostname;
		 MQEnvironment.channel  = channel;
		 MQEnvironment.userID = user;
		 MQEnvironment.password = password;
		 /*
	     MQEnvironment.properties.put(MQC.TRANSPORT_PROPERTY,//Set TCP/IP or server
	                              MQC.TRANSPORT_MQSERIES);//Connection
		*/
	  
	  } // end of init

	@SuppressWarnings("deprecation")
	public void send(String qName) {
		// Produce/Put message to Queue
		 try {
			MQQueueManager mqqm = new MQQueueManager(qMgr);
			MQQueue queue = mqqm.accessQueue(qName, MQC.MQOO_OUTPUT);
			System.out.println("Create MQ Message");
			MQMessage mqMsg = new MQMessage();
			mqMsg.writeString("Hellow");
			MQPutMessageOptions pmo = new MQPutMessageOptions();
			System.out.println("PUT MQ Message");
			System.out.println(mqMsg);
			queue.put(mqMsg,pmo);
			System.out.println("Close connection to Queue");
			queue.close();
			System.out.println("QM Disconnect");
			mqqm.disconnect();
		} catch (MQException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
}
