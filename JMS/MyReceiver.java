import javax.jms.QueueConnection;
import javax.jms.QueueConnectionFactory;
import javax.jms.QueueReceiver;
import javax.jms.QueueSession;
import javax.jms.Session;
import javax.naming.Context;
import javax.naming.InitialContext;
import java.util.Properties;
import java.util.Queue;
import lib.*;


public class MyReceiver {  
    public static void main(String[] args) {
        InitialContext ctx;
        Properties props = new Properties();
        props.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.enterprise.naming.SerialInitContextFactory");
        props.put(Context.URL_PKG_PREFIXES, "com.sun.enterprise.naming");
        props.put(Context.PROVIDER_URL, "http://localhost:4848/");
  
        try{  
            //1) Create and start connection  
            ctx=new InitialContext(props);  
            QueueConnectionFactory f=(QueueConnectionFactory)ctx.lookup("myQueueConnectionFactory");  
            QueueConnection con=f.createQueueConnection();  
            con.start();  
            //2) create Queue session  
            QueueSession ses=con.createQueueSession(false, Session.AUTO_ACKNOWLEDGE);  
            //3) get the Queue object  
            Queue t=(Queue)ctx.lookup("myQueue");  
            //4)create QueueReceiver  
            QueueReceiver receiver=ses.createReceiver((javax.jms.Queue) t);
              
            //5) create listener object  
            MyListener listener=new MyListener();  
              
            //6) register the listener object with receiver  
            receiver.setMessageListener(listener);  
              
            System.out.println("Receiver1 is ready, waiting for messages...");  
            System.out.println("press Ctrl+c to shutdown...");  
            while(true){                  
                Thread.sleep(1000);  
            }  
        }catch(Exception e){System.out.println(e);}  
    }  
  
}  