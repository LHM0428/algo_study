package dp.day14;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Boj1948 {
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
		int to;
		int time;
		public Road(int to, int time) {
			super();
			this.to = to;
			this.time = time;
		}
	}
	
	static int N, M, start, end, roadNum;
	static List<Road>[] roads;
	static int[] dp;
	static boolean[] isVisited;
	static boolean[][] isVisitedRoad;
	public static void main(String[] args) {
		Scan sc = new Scan();
		
		N = sc.nextInt();
		M = sc.nextInt();
		
		roads = new ArrayList[N+1];
		dp = new int[N+1];
		isVisited = new boolean[N+1];
		isVisitedRoad = new boolean[N+1][N+1];
		
		for(int i=1; i<=N; i++) roads[i] = new ArrayList<Road>();
		
		for(int i=0; i<M; i++) {
			int from = sc.nextInt();
			int to = sc.nextInt();
			int time = sc.nextInt();
			
			roads[from].add(new Road(to, time));
		}
		
		
		start = sc.nextInt();
		end = sc.nextInt();
		
		int max = dfs(start);
		System.out.println(max);
		System.out.println((findRoad(start, 0, max)-1));
	}
	
	private static int findRoad(int from, int time, int max) {
		int cnt = 0;
		
		if(time + dp[from] == max) cnt++;
		else return 0;
		
		for(Road r : roads[from]) {
			if(!isVisitedRoad[from][r.to]) {
				cnt += findRoad(r.to, r.time + time, max);
				isVisitedRoad[from][r.to]= true; 
			}
		}
		
		return cnt;
	}

	private static int dfs(int from) {
		if(from == end) {
			return 0;
		}
		
		for(Road r : roads[from]) {
			if(isVisited[r.to]) {
				dp[from] = Math.max(dp[from], dp[r.to] + r.time);
			}else {
				dp[from] = Math.max(dp[from], dfs(r.to) + r.time);
			}
		}
		
		isVisited[from] = true;
		
		return dp[from];
	}
}