package mst.day11;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Boj17132 {
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
		long value;
		public Edge(int from, int to, long value) {
			super();
			this.from = from;
			this.to = to;
			this.value = value;
		}
	}
	
	static int N;
	static PriorityQueue<Edge> pq;
	static int[] group;
	public static void main(String[] args) {
		Scan sc = new Scan();
		
		N = sc.nextInt();
		group = new int[N+1];
		Arrays.fill(group, -1);
		
		pq = new PriorityQueue<Edge>(new Comparator<Edge>() {

			@Override
			public int compare(Edge o1, Edge o2) {
				return o2.value > o1.value ? 1 : -1 ;
			}
		});
		
		for(int i=1; i<N; i++) pq.add(new Edge(sc.nextInt(), sc.nextInt(), sc.nextInt()));
		
		long sum = 0;
		while(!pq.isEmpty()) {
			Edge e = pq.poll();
			
			sum += e.value * group[find(e.from)] * group[find(e.to)];
//			System.out.println(Arrays.toString(group));
//			System.out.println(e.value+" * "+group[find(e.from)]+" * "+group[find(e.to)]+" : " +sum);
			union(e.from, e.to);
		}
		
		System.out.println(sum);
	}
	
	private static void union(int a, int b) {
		a = find(a);
		b = find(b);
		
		if(a == b) return;
		
		if(group[a] < group[b]) {
			group[a] += group[b];
			group[b] = a;
		}else {
			group[b] += group[a];
			group[a] = b;
		}
	}
	
	private static int find(int a) {
		if(group[a] < 0) return a;
		return group[a] = find(group[a]);
	}
}