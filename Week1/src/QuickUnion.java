import java.util.Scanner;


public class QuickUnion {

	private int[] id;
	
	public QuickUnion(int N)
	{
		id = new int[N];
		for(int i=0; i<N; i++)
		{
			id[i] = i;
		}
	}
	
	public int root(int p)
	{
		int i = id[p];
		while(i != id[i]) i = id[i];
		return i;
	}
	
	public boolean IsConnected(int p, int q)
	{
		int i = root(p);
		int j = root(q);
		return i == j;
	}
	
	public void Union(int p, int q)
	{
		int i = root(p);
		int j = root(q);
		id[i] = j;
	}
	public static void main(String[] args) {
		/* TODO Auto-generated method stub */
		
		System.out.print("Enter number of elements: ");
		Scanner scan = new Scanner(System.in);
		
		QuickUnion uf = new QuickUnion(scan.nextInt());
		
		uf.Union(4, 3);
		uf.Union(3, 5);
		
		System.out.println("Check whether '4' & '5' are connected : "+ uf.IsConnected(4, 5));
		System.out.println("Check whether 1 & 5 are connected : "+ uf.IsConnected(1, 5));

	}

}
