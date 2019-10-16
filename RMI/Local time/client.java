import java.rmi.*;
import java.util.*;
import java.rmi.registry.*;		

public class client
{
	public static void main(String[] args) 
	{
		try
		{
			String user;
			int ch;
			System.out.println("1. Greet\n2. Local Time\nEnter:");
			Scanner scan = new Scanner(System.in);
			ch = scan.nextInt();

			calinterface stub = (calinterface) Naming.lookup("rmi://localhost:8000/aaa");

			switch(ch)
			{
				case 1:
				System.out.print("Enter name:");
				Scanner scan1 = new Scanner(System.in);
				user = scan1.nextLine();
				System.out.println(stub.greet(user));
				break;

				case 2:
				System.out.println(stub.time());
				break;
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}

		
	}
}