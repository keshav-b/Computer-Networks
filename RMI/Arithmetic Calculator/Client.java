import java.rmi.*;
import java.util.*;
public class Client
{
	public static void main(String args[])
	{
		try
		{
			float a=0,b=0;int ch=0;
			Scanner sc=new Scanner(System.in);
			calinterface stub=(calinterface)Naming.lookup("rmi://localhost:8000/aaa");
			System.out.println("Enter arithmetic operation :\n1.Add\n2.Subtract\n3.Multiply\n4.Divide\n5.Mod\nEnter Choice: ");
			ch=sc.nextInt();
			System.out.println("Enter first number : ");
			a=sc.nextFloat();
			System.out.println("Enter Second number : ");
			b=sc.nextFloat();
			switch(ch)
			{
				case 1 :System.out.println(stub.add(a,b));break;
				case 2 :System.out.println(stub.sub(a,b));break;
				case 3 :System.out.println(stub.mul(a,b));break;
				case 4 :System.out.println(stub.div(a,b));break;
				case 5 :System.out.println(stub.modx(a,b));break;
				default :System.out.println("Invalid Choice");
			}
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
		
	}
}
