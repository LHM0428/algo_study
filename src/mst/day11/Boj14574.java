package mst.day11;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Boj14574 {
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
	
	static class Chef{
		int p;
		int c;
		public Chef(int p, int c) {
			super();
			this.p = p;
			this.c = c;
		}
	}
	
	static class Edge{
		int from;
		int to;
		long value;
		public Edge(int from, int to, long value) {
			super();
			this.from = from;
			this.to = to;
			this.value = value;
		}
	}
	
	static int N;
	static Chef[] list;
	static List<Integer>[] edges;
	static int[] group;
	static boolean[] isVisited;
	static StringBuilder sb = new StringBuilder();
	public static void main(String[] args) {
		Scan sc = new Scan();
		
		N = sc.nextInt();
		
		list = new Chef[N+1];
		edges = new ArrayList[N+1];
		group = new int[N+1];
		isVisited = new boolean[N+1];
		
		for(int i=1; i<=N; i++) {
			group[i] = i;
			list[i] = new Chef(sc.nextInt(), sc.nextInt());
			edges[i] = new ArrayList<Integer>();
		}
		
		PriorityQueue<Edge> pq = new PriorityQueue<Edge>(new Comparator<Edge>() {

			@Override
			public int compare(Edge o1, Edge o2) {
				if(o2.value - o1.value > 0) return 1;
				else return -1;
			}
		});
		
		for(int i=1; i<=N; i++) {
			Chef chef = list[i];
			for(int j=i+1; j<=N; j++) {
				Chef comp = list[j];
				pq.add(new Edge(i, j, ((long)(chef.c+comp.c)/(long)(Math.abs(chef.p-comp.p)))));
			}
		}
		
		long sum = 0;
		while(!pq.isEmpty()) {
			Edge e = pq.poll();
			
			int a = find(e.from);
			int b = find(e.to);
			
			if(a == b) continue;
			
			union(a, b);
			sum += e.value;
			edges[e.from].add(e.to);
			edges[e.to].add(e.from);
		}
		System.out.println(sum);
		dfs(1);
		System.out.println(sb);
	}
	
	private static void dfs(int i) {
		isVisited[i] = true;
		
		for(int next : edges[i]) {
			if(!isVisited[next]) {
				dfs(next);
				sb.append(i).append(" ").append(next).append("\n");
			}
		}
		
	}

	private static void union(int a, int b) {
		group[a] = b;
	}

	private static int find(int a) {
		if(group[a] == a) return a;
		return group[a] = find(group[a]);
	}
}