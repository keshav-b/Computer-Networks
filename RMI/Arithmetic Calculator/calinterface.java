import java.rmi.*;
public interface calinterface extends Remote
{
public float add(float a,float b)throws RemoteException;
public float sub(float a,float b)throws RemoteException;
public float mul(float a,float b)throws RemoteException;
public float div(float a,float b)throws RemoteException;
public float modx(float a,float b)throws RemoteException;
}
