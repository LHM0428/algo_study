package mst.day09;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Boj6497 {
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
	
	static class Road {
		int from;
		int to;
		int dist;
		public Road(int from, int to, int dist) {
			super();
			this.from = from;
			this.to = to;
			this.dist = dist;
		}
	}
	
	static int N, M;
	static int[] group = new int[200001];
	public static void main(String[] args) {
		Scan sc = new Scan();
		
		
		while(true) {
			M = sc.nextInt();
			N = sc.nextInt();
			if(N == 0 && M == 0) break;
			
			Arrays.fill(group, -1);
			
			PriorityQueue<Road> pq = new PriorityQueue<Road>(new Comparator<Road>() {
				@Override
				public int compare(Road o1, Road o2) {
					return o1.dist - o2.dist;
				}
			});
			
			int total = 0;
			int sum = 0;
			for(int i=0; i<N; i++) {
				int from = sc.nextInt();
				int to = sc.nextInt();
				int dist = sc.nextInt();
				total += dist;
				pq.add(new Road(from, to, dist));
			}
			
			while(!pq.isEmpty()) {
				
				Road r = pq.poll();
				
				int from = find(r.from);
				int to = find(r.to);
				
				if(from == to) continue;
				
				union(from, to);
				sum += r.dist;
			}
			System.out.println(total - sum);
		}
		
	}
	
	private static void union(int from, int to) {
		group[from] = to;
	}

	private static int find(int a) {
		if(group[a] < 0) return a;
		return group[a] = find(group[a]);
	}
}