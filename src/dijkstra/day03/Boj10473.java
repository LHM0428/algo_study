package dijkstra.day03;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Boj10473 {
	static class Scan{
		BufferedReader br;
		StringTokenizer st;
		
		public Scan() {
			br = new BufferedReader(new InputStreamReader(System.in));
		}
		
		public String next(){
			while(st == null || !st.hasMoreTokens()){
				try {
					st = new StringTokenizer(br.readLine());
				} catch (IOException e) {
					e.printStackTrace();
				}
				
			}
			return st.nextToken();
		}
		
		public int nextInt(){
			return Integer.parseInt(next());
		}
		
		public double nextDouble() {
			return Double.parseDouble(next());
		}
	}
	
	static class Position{
		double x;
		double y;
		
		public Position(double x, double y) {
			super();
			this.x = x;
			this.y = y;
		}
	}
	
	static class Vertice{
		int positionNum;
		double time;
		public Vertice(int positionNum, double time) {
			super();
			this.positionNum = positionNum;
			this.time = time;
		}
	}
	
	static Position start;
	static Position end;
	static int N;
	static Position[] list;
	static double[] time;
	static boolean isFirst = true;
	public static void main(String[] args) {
		Scan sc = new Scan();
		
		start = new Position(sc.nextDouble(), sc.nextDouble());
		end = new Position(sc.nextDouble(), sc.nextDouble());
		
		N = sc.nextInt();
		list = new Position[N+2];
		time = new double[N+2];
		
		Arrays.fill(time, Integer.MAX_VALUE);
		list[0] = start;
		for(int i=1; i<=N; i++) list[i] = new Position(sc.nextDouble(), sc.nextDouble());
		list[N+1] = end;
		
		PriorityQueue<Vertice> pq = new PriorityQueue<Vertice>(new Comparator<Vertice>() {
			@Override
			public int compare(Vertice o1, Vertice o2) {
				if(o1.time - o2.time > 0) return 1;
				else if(o1.time == o2.time) return 0;
				else return -1;
			}
		});
		time[0] = 0;
		pq.add(new Vertice(0, time[0]));
		
		while(!pq.isEmpty()) {
			Vertice v = pq.poll();
			Position curr = list[v.positionNum];
			
			for(int i=1; i<=N+1; i++) {
				if(v.positionNum == i) continue;
				Position next = list[i];
				
				double t = getTime(getDistance(curr, next));
				
				if(time[i] > time[v.positionNum] + t) {
					time[i] = time[v.positionNum] + t;
					pq.add(new Vertice(i, time[i]));
				}
			}
			isFirst = false;
		}
		
		System.out.println(time[N+1]);
		
	}
	
	public static double getDistance(Position a, Position b) {
		double x = a.x - b.x;
		double y = a.y - b.y;
		
		return Math.sqrt(x*x+y*y);
	}
	
	public static double getTime(double dist) {
		double t1 = Math.abs(dist-50) / 5.0 + 2;
		double t2 = dist / 5.0;
		if(isFirst) t1 = t2;
		return t1 < t2 ? t1 : t2;
	}
}





































