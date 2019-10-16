import java.rmi.*;
import java.rmi.server.*;
public class RemoteCalc extends UnicastRemoteObject implements calinterface
{
	RemoteCalc() throws RemoteException
	{
		super();
	}
	public float add(float a,float b)
	{
		return a+b;
	}
	public float sub(float a,float b)
	{
		return a-b;
	}
	public float mul(float a,float b)
	{	
		return a*b;
	}
	public float div(float a,float b)
	{
		return a/b;
	}
	public float modx(float a,float b)
	{
		return a%b;
	}
}
