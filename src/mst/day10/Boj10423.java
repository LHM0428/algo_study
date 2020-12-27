package mst.day10;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Boj10423 {
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
	
	static class Cable{
		int from;
		int to;
		int cost;
		public Cable(int from, int to, int cost) {
			super();
			this.from = from;
			this.to = to;
			this.cost = cost;
		}
	}
	
	static int N, M, K;
	static int[] group;
	public static void main(String[] args) {
		Scan sc = new Scan();
		N = sc.nextInt();
		M = sc.nextInt();
		K = sc.nextInt();
		
		group = new int[N+1];
		
		Arrays.fill(group, -1);
		for(int i=0; i<K; i++) {
			union(sc.nextInt(), 0);
		}
		
		PriorityQueue<Cable> pq  = new PriorityQueue<Cable>(new Comparator<Cable>() {

			@Override
			public int compare(Cable o1, Cable o2) {
				return o1.cost - o2.cost;
			}
		});
		
		for(int i=0; i<M; i++) pq.add(new Cable(sc.nextInt(), sc.nextInt(), sc.nextInt()));
		
		int sum = 0;
		while(!pq.isEmpty()) {
			Cable c = pq.poll();
			
			int from = find(c.from);
			int to = find(c.to);
			
			if(from == to) continue;
			
			union(from, to);
			sum += c.cost;
		}
		System.out.println(sum);
	}
	private static void union(int a, int b) {
		group[a] = b;
	}
	private static int find(int a) {
		if(group[a] < 0) return a;
		return group[a] = find(group[a]);
	}
}