package mst.day11;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Boj2463 {
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
	
	static int N, M;
	static PriorityQueue<Edge> pq;
	static int[] group;
	static long[] numGroup;
	public static void main(String[] args) {
		Scan sc = new Scan();
		
		N = sc.nextInt();
		M = sc.nextInt();
		
		long sum = 0;
		long total = 0;
		int MOD = 1000000000;
		group = new int[N+1];
		numGroup = new long[N+1];
		Arrays.fill(numGroup, 1);
		
		for(int i=1; i<=N; i++) group[i] = i;
		
		pq = new PriorityQueue<Edge>(new Comparator<Edge>() {

			@Override
			public int compare(Edge o1, Edge o2) {
				return o2.value - o1.value > 0 ? 1 : -1;
			}
		});
		
		for(int i=0; i<M; i++) {
			int from = sc.nextInt();
			int to = sc.nextInt();
			long value = sc.nextInt();
			
			pq.add(new Edge(from, to, value));
			
			total += value;
		}
		
		while(!pq.isEmpty()) {
			Edge e = pq.poll();
			
			
			int a = find(e.from);
			int b = find(e.to);
			
			if(a != b){
                sum += (total * (numGroup[a] * numGroup[b] % MOD)) % MOD;
			    sum %= MOD;
			
			    union(a, b);
            }
			
			total -= e.value;
		}
		System.out.println(sum);
	}
	
	private static void union(int a, int b) {
		group[b] = a;
		numGroup[a] += numGroup[b];
		numGroup[b] = 1;
	}
	private static int find(int a) {
		if(group[a] == a) return a;
		return group[a] = find(group[a]);
	}
}