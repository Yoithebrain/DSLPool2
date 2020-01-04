import java.util.*;
import org.apache.xmlrpc.*;
import org.apache.commons.*;

public class JavaClient {
   public static void main (String [] args) throws ClassNotFoundException {
   
      try {
         XmlRpcClient client = new XmlRpcClient("http://localhost/RPC2"); 
         Vector params = new Vector();
         
         params.addElement(new Integer(17));
         params.addElement(new Integer(13));

         Object result = client.execute("sample.sum", params);

         int sum = ((Integer) result).intValue();
         System.out.println("The sum is: "+ sum);

      } catch (Exception exception) {
         System.err.println("JavaClient: " + exception);
      }
   }
}