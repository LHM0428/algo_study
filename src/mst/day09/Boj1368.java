package mst.day09;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Boj1368 {
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
	}
	
	static class Edge{
		int from;
		int to;
		int cost;
		public Edge(int from, int to, int cost) {
			super();
			this.from = from;
			this.to = to;
			this.cost = cost;
		}
		@Override
		public String toString() {
			return "Edge [from=" + from + ", to=" + to + ", cost=" + cost + "]";
		}
	}
	
	static int N;
	static int[] group = new int[301];
	public static void main(String[] args) {
		Scan sc = new Scan();
		
		N = sc.nextInt();
		PriorityQueue<Edge> pq = new PriorityQueue<Edge>(new Comparator<Edge>() {

			@Override
			public int compare(Edge o1, Edge o2) {
				return o1.cost - o2.cost;
			}
		});
		
		for(int i=1; i<=N; i++) {
			pq.add(new Edge(0, i, sc.nextInt()));
			group[i] = i;
		}
		
		for(int i=1; i<=N; i++) {
			for(int j=1; j<=N; j++) {
				int cost = sc.nextInt();
				if(i < j) pq.add(new Edge(i, j, cost));
			}
		}
		int sum = 0;
		while(!pq.isEmpty()) {
			Edge e = pq.poll();
			int from = find(e.from);
			int to = find(e.to);
			
			if(from == to) continue;
			
			sum += e.cost;
			union(from, to);
		}
		
		System.out.println(sum);
	}
	
	private static void union(int from, int to) {
		group[from] = to;
	}

	private static int find(int a) {
		if(group[a] == a) return a;
		return group[a] = find(group[a]);
	}
}