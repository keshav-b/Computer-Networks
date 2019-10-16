import java.util.Scanner;
class CRC
{
	public static int xor(int x,int y)
	{
		if(x==y)
		return(0);
		else
		return(1);
	}
	public static void main(String args[])
	{
		Scanner sc=new Scanner(System.in);
		int m,g[],n,d[],z[],r[],msb,i,j,k;
		System.out.print("Enter no. of data bits : ");
		n=sc.nextInt();
		System.out.print("Enter no. of generator bits : ");
		m=sc.nextInt();
		d=new int[n+m];
		g=new int[m];
		
		System.out.print("Enter data bits : ");
		for(i=0;i<n;i++)
			d[i]=sc.nextInt();
		
		System.out.print("Enter generator bits : ");
		for(j=0;j<m;j++)
			g[j]=sc.nextInt();

		// last m-1 bits 0
		for(i=0;i<m-1;i++)
			d[n+i]=0;

		// intermediate dividend
		r=new int[m+n];
		
		// divisor(outside) size and divident size shud be same to divide 1101|100100.000|0
		for(i=0;i<m;i++)
			r[i]=d[i];

		// intermediate remainder
		z=new int[m];

		for(i=0;i<m;i++)
			z[i]=0;

		// main division
		/*
			1101|100100.000|
		*/
		for(i=0;i<n;i++)
		{
			k=0;
			msb=r[i];//1
			for(j=i;j<m+i;j++)
			{
				if(msb==0)
				r[j]=xor(r[j],z[k]);// z[k] is always 0?
				else
				r[j]=xor(r[j],g[k]);
				k++;
			}
			r[m+i]=d[m+i];
		}
		System.out.print("The code bits added are : ");
		for(i=n;i<n+m-1;i++)
		{
			d[i]=r[i];
			System.out.print(d[i]);
		}
		System.out.print("\nThe code data is : ");
		for(i=0;i<n+m-1;i++)
		{
			System.out.print(d[i]);
		}
	}
	
}