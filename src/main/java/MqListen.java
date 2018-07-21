package main.java;

import java.io.Serializable;

import javax.jms.Connection;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;

import com.ibm.mq.*;

public class MqListen implements MessageListener {

    private Connection connection;
    private Session session;
    private MessageConsumer replyConsumer;
	
	public MqListen() {
	}
	/*
	public void setConnDetails(Connection connection, Session session2, Destination queue) throws JMSException {
        this.connection = connection;
        this.session = session2;
        replyConsumer = session2.createConsumer(queue);
        //replyConsumer.setMessageListener(this);
	}
	*/
	
	@Override
    public void onMessage(Message message) {
        try {
        		//TextMessage textMessage = (TextMessage) message;
        		System.out.println("Message is "+message);
        		//System.out.println(textMessage.getText());
        } catch (Exception e) {
        		e.printStackTrace();
        }
    }
	/*
    public void cleanUp() throws JMSException {
        session.close();
        connection.close();
    }
    */
	
}
