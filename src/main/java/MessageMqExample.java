package main.java;

public class MessageMqExample {

	public static String qManagerName = "QM1";
	public static String queue = "DEV.QUEUE.1";
	
	public static void main(String[] args) {
		MqSample mqs = new MqSample(qManagerName);
		
		System.out.println("Start INIT");
		mqs.init();
		System.out.println("END INIT");
		System.out.println("Start MQSEND");
		mqs.send(queue);
		System.out.println("END MQSEND");
	}

}
