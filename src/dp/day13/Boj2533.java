package dp.day13;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Boj2533 {
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
	
	
	static int N;
	static List<Integer>[] list, tree;
	static boolean[] isVisited;
	static int[][] dp;
	static int EARLY = 1, NOT_EARLY = 0;
	public static void main(String[] args) {
		Scan sc = new Scan();
		
		N = sc.nextInt();
		
		list = new ArrayList[N+1];
		tree = new ArrayList[N+1];
		isVisited = new boolean[N+1];
		dp = new int[N+1][2];
		
		
		for(int i=1; i<=N; i++) {
			list[i] = new ArrayList<Integer>();
			tree[i] = new ArrayList<Integer>();
			dp[i][0] = -1;
			dp[i][1] = -1;
		}
		
		
		for(int i=1; i<N; i++) {
			int from = sc.nextInt();
			int to = sc.nextInt();
			
			list[from].add(to);
			list[to].add(from);
		}
		
		dfs(1);
		
		int min = Math.min(getNumEarlyAdaptor(1, NOT_EARLY), getNumEarlyAdaptor(1, EARLY));
		
		System.out.println(min);
	}
	
	
	private static int getNumEarlyAdaptor(int node, int isEarly) {
		int sum = dp[node][isEarly];
		if(sum > 0) return dp[node][isEarly];
		
		if(isEarly == NOT_EARLY) {//not early
			
			sum = 0;
			for(int next : tree[node]) {
				sum += getNumEarlyAdaptor(next, EARLY);
			}
			
		}else if(isEarly == EARLY){//EARLY일 경우 자식이 EARLY, NOT_EARLY 상관 없음
			
			sum = 1;
			for(int next : tree[node]) {
				sum += Math.min(getNumEarlyAdaptor(next, NOT_EARLY), getNumEarlyAdaptor(next, EARLY));
			}
		}
		
		return dp[node][isEarly] = sum;
	}
	private static void dfs(int i) {
		isVisited[i] = true;
		
		for(int next : list[i]) {
			if(isVisited[next]) continue;
			
			tree[i].add(next);
			dfs(next);
		}
	}
}