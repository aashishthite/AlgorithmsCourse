import java.util.*;
class Main{
	public static void main(String[] args)
	{
		Scanner scan = new Scanner(System.in);
		while(scan.hasNextInt())
		{
			int N = scan.nextInt();
			int m = scan.nextInt();
			List<Integer> moves = new ArrayList<Integer>();
			
			Map<Integer,Boolean> memory = new HashMap<Integer, Boolean>();
			for(int ii = 0; ii < m; ii++)
				moves.add(scan.nextInt());
			Collections.sort(moves);
			List<Integer> steps = new ArrayList<Integer>(N+1);
			steps.add(0);
			for(int ii = 1; ii <= N; ii++)
			{
				int temp = 0;
				for(Integer move: moves)
				{
					if(ii >= move)
					{
						if(steps.get(ii-move) == 0)
						{
							temp = 1; 
							break;
						}
					} 
				}
				steps.add(temp);
			}
			
		
			
			if(steps.get(N) == 1)
				System.out.println( "Stan wins");
			else
				System.out.println("Ollie wins");
			
			
		}
	}
}
