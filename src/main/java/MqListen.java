package main.java;

import java.io.Serializable;

import javax.jms.Connection;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;
import javax.jms.Queue;
import javax.jms.Session;

import com.ibm.mq.*;

public class MqListen implements MessageListener {

    private Connection connection;
    private Session session;
    private MessageConsumer replyConsumer;
	
	public MqListen(Connection connection, Session session, Queue replyQueue) throws JMSException {
        this.connection = connection;
        this.session = session;
        replyConsumer = session.createConsumer(replyQueue);
        replyConsumer.setMessageListener(this);
	}
	
    @Override
    public void onMessage(Message message) {
        try {
            final ObjectMessage objectMessage = (ObjectMessage) message;
            final Serializable object = objectMessage.getObject();
            cleanUp();
        } catch (Exception e) {
        		e.printStackTrace();
        }
    }

    private void cleanUp() throws JMSException {
        replyConsumer.close();
        session.close();
        connection.close();
    }
	
}
