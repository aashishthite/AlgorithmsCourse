import java.util.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
class Slide
{
	int x,y,z,w;
	char id;
	public Slide(char aa, int a, int b, int c, int d)
	{
		x=a;y=b;z=c;w=d;
		id = aa;
	}
	public boolean isPointInside(Point p)
	{
		return p.x>x && p.x<y && p.y>z && p.y<w;
	}
}
class Point
{
	int x,y;
	public Point(int a, int b)
	{
		x=a;y=b;
	}
}
class Match
{
	int ii,jj;
	public Match(int i, int j)
	{
		ii = i; jj = j;
	}
}
class Answer
{
	char c;
	int n;
	public Answer(char aa, int num)
	{
		c = aa;
		n = num;
	}
}
public class Main{
	int N;
	static List<Slide> slides;
	static List<Point> points;
	//static List<Boolean> visited;
	static List<Answer> answers;
	static int[][] graph;
	//public static boolean nasNeighbor(int ii){
		//if(visited.get();

//	}
	public static void main(String[] args) throws Exception
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String curLine;
		int cnt  = 0;
		while((curLine = br.readLine()) != null){
			int n = Integer.parseInt(curLine);
			if(n>0){
				System.out.println("Heap " + ++cnt);
//	System.out.println(n);
				slides = new ArrayList<Slide>(n);
				points = new ArrayList<Point>(n);
				answers = new ArrayList<Answer>(n);
				int ii;
				for( ii = 0; ii <n; ii++){
					String cur = br.readLine();
					String[] splitline = cur.split(" ");
					//System.out.println(splitline[3]);
					slides.add(new Slide((char)(ii+'A'),Integer.parseInt(splitline[0]),Integer.parseInt(splitline[1]),Integer.parseInt(splitline[2]),Integer.parseInt(splitline[3])));
				}
				for( ii = 0; ii < n; ii++){
					String[] curSplit = br.readLine().split(" ");
					points.add(new Point(Integer.parseInt(curSplit[0]),Integer.parseInt(curSplit[1])));
				}
				//System.out.println(slides.get(0).z + " " + slides.get(0).w);
				graph = new int[n*2+2][n*2+2];
				for(ii=1;ii<=n;ii++)graph[0][ii]=1;
				for(ii = n+1; ii<= 2*n; ii++) graph[ii][2*n+1] = 1;
				for(ii = 1; ii<=n; ii++){
					for(int jj = n+1; jj<=2*n; jj++){
						if(slides.get(ii-1).isPointInside(points.get(jj-n-1))){
							graph[ii][jj] = 1;// Integer.MAX_VALUE;//System.out.println("Here");
						}
					}
				}

				int[][] flow = new int[2*n+2][2*n+2];
				//flow(graph,0,2*n-1,f);  
			/*	for(ii = 0; ii<2*n+2; ii++){
					for(int jj = 0; jj < 2*n+2; jj++)
						System.out.print(graph[ii][jj]);
					System.out.print("\n");

				}
				System.out.println();
			*/
				int[][] rGraph = new int[graph.length][graph.length];
				for( ii = 0; ii <graph.length; ii++)
					for(int jj = 0; jj <graph.length; jj++)
						rGraph[ii][jj] = graph[ii][jj];

				int totFlow = FF(graph,rGraph,flow,0,2*n+1);

				List<Match> matches = new ArrayList<Match>();
				for(ii = 1; ii<=n; ii++){
					for(int jj = n; jj < 2*n+1; jj++)
					{
						if(flow[ii][jj] == 1)
							matches.add(new Match(ii-1,jj-n));

						//System.out.print(flow[ii][jj]);
					}
					//System.out.println();	

				}//System.out.print(totFlow);
				for(ii = 0; ii < matches.size(); ii++)
				{
					//System.out.println("Here");
					Match temp = matches.get(ii);
					graph[temp.ii+1][temp.jj+n]= 0;
					rGraph = new int[graph.length][graph.length];	
					for(int kk = 0; kk <graph.length; kk++){
						for(int jj = 0; jj <graph.length; jj++)
						{
							//System.out.print(graph[kk][jj]);
							rGraph[kk][jj] = graph[kk][jj];
						}
						//System.out.println();
					}

					int tempFlow = FF(graph,rGraph,flow,0,2*n+1);
					//System.out.println("tempFlow: "+ tempFlow);
					if(tempFlow < totFlow)
						answers.add(new Answer((char)(temp.ii+'A'),temp.jj));
					graph[temp.ii+1][temp.jj+n] = 1;
					//System.out.print(matches.size() +" " + ii);

				}
				//System.out.println(answers.size());
				ii=0;
				if(answers.size()>0)
				{
					
					for(Answer ans:answers)
					{	
						ii++;
						if(ii<answers.size())
							System.out.print("("+ans.c+","+ans.n+") ");
						else
							System.out.print("("+ans.c+","+ans.n+")");
					}
				}					
				else
					System.out.print("none");
			System.out.println("\n");

			}

		}
	}
	public static boolean bfs(int[][] rGraph,  int s, int t, int[] parent)
	{
		boolean[] visited = new boolean[rGraph.length];

		Queue<Integer> q = new LinkedList<Integer>();
		q.offer(s);
		visited[s] = true;
		parent[s] = -1;
		while(!q.isEmpty())
		{
			int u = q.poll();
			for(int ii = 0; ii < rGraph.length; ii++)
			{
				if(!visited[ii] && rGraph[u][ii] > 0)
				{
					q.offer(ii);
					parent[ii] = u;
					visited[ii] = true;
				}
			}
		}
		return visited[t];

	}
	public static int FF(int[][] graph,int[][] rGraph,int[][] flow,  int s, int t)
	{
		int u,v;
			int[] parent = new int[graph.length];
		int maxFlow = 0;
		while(bfs(rGraph,s,t,parent))
		{
			int pathFlow = Integer.MAX_VALUE;
			for(v=t; v!=s; v=parent[v])
			{
				u =parent[v];
				pathFlow = Math.min(pathFlow,rGraph[u][v]);
			}
			for(v=t; v !=s; v=parent[v])
			{
				u = parent[v];
				rGraph[u][v] -= pathFlow;
				rGraph[v][u] += pathFlow;

			}
			maxFlow += pathFlow;
		}
		for(int ii = 1 ; ii < graph.length/2; ii++)
			for(int jj = graph.length/2; jj < graph.length; jj++)
			{
				if(graph[ii][jj] > 0)
					flow[ii][jj] = rGraph[ii][jj] > 0 ? 0: graph[ii][jj];
			}
		return maxFlow;
	}
}
