package dijkstra.day03;

import java.io.*;
import java.util.*;

public class Boj1162 {
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
		
		public long nextLong() {
			return Long.parseLong(next());
		}
	}
	
	static class Road{
		int roadNum;
		long cost;
		int numBuild;
		public Road(int roadNum, long cost, int numBuild) {
			super();
			this.roadNum = roadNum;
			this.cost = cost;
			this.numBuild = numBuild;
		}
	}
	
	static int N, M, K;
	static List<Road>[] roads;
	static long[][] sum;
	public static void main(String[] args) {
		Scan sc = new Scan();
		
		N = sc.nextInt();
		M = sc.nextInt();
		K = sc.nextInt();
		
		roads = new ArrayList[N+1];
		sum = new long[N+1][K+1];
		for(int i=1; i<=N; i++) {
			roads[i] = new ArrayList<Road>();
			Arrays.fill(sum[i], Long.MAX_VALUE);
		}
		
		for(int i=0; i<M; i++) {
			int from = sc.nextInt();
			int to = sc.nextInt();
			long cost = sc.nextLong();
			
			roads[from].add(new Road(to, cost, 0));
			roads[to].add(new Road(from, cost, 0));
		}
		
		PriorityQueue<Road> pq = new PriorityQueue<Road>(new Comparator<Road>() {

			@Override
			public int compare(Road o1, Road o2) {
				if(o1.cost - o2.cost > 0) return 1;
				else if(o1.cost - o2.cost == 0) return 0;
				else return -1;
			}
		});
		
		
		pq.add(new Road(1, 0, 0));
		sum[1][0] = 0;
		
		while(!pq.isEmpty()) {
			Road curr = pq.poll();
			
			if(curr.cost > sum[curr.roadNum][curr.numBuild]) continue;
			
			for(Road next : roads[curr.roadNum]) {
				int nextRoad = next.roadNum;
				long nextCost = sum[curr.roadNum][curr.numBuild] + next.cost;
				
				if(sum[nextRoad][curr.numBuild] > nextCost) {
					sum[nextRoad][curr.numBuild] = nextCost;
					
					pq.add(new Road(nextRoad, nextCost, curr.numBuild));
				}
				
				if(curr.numBuild < K && 
						sum[nextRoad][curr.numBuild+1] > curr.cost) {
					sum[nextRoad][curr.numBuild+1] = curr.cost;
					
					pq.add(new Road(nextRoad, curr.cost, curr.numBuild+1));
				}
				
			}
		}
		
		long result = Long.MAX_VALUE;
		for(int i=0; i<=K; i++) {
			result = Math.min(result, sum[N][i]);
		}
		
		System.out.println(result);
		
		
	}
}







































