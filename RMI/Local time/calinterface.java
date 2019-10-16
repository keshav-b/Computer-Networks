import java.rmi.*;

public interface calinterface extends Remote
{
	public String time() throws Exception;
	public  String greet(String user) throws Exception;
}