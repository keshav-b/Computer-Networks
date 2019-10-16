import java.util.*;
import java.text.*;
import java.rmi.*;
import java.rmi.server.*;

public class RemoteCalc extends UnicastRemoteObject implements calinterface
{
	RemoteCalc() throws RemoteException
	{
		super();
	}
	public String time()
	{
		DateFormat df = new SimpleDateFormat("HH:mm:ss");
		Date d = new Date();
		String date = df.format(d);
		return date;
	}
	public String greet(String user)
	{
		return("Hello "+user);
	}
}