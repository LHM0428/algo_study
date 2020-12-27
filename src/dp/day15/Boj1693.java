package dp.day15;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Boj1693{
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
	
	static int N, K;
	static List<Integer>[] trees;
	static int[][] dp;
	static boolean[] isVisited;
	public static void main(String[] args) {
		Scan sc = new Scan();
		
		N = sc.nextInt();
		
		K = 1;
		int length = 1;
		while(true) {
			if(length > N) break;
			length *= 2;
			K++;
		}
		
		trees = new ArrayList[N+1];
		isVisited = new boolean[N+1];
		dp = new int[N+1][K+1];
		
		
		for(int i=0; i<=N; i++) {
			trees[i] = new ArrayList<Integer>();
		}
		
		for(int i=1; i<N; i++) {
			int a = sc.nextInt();
			int b = sc.nextInt();
			
			trees[a].add(b);
			trees[b].add(a);
		}
		trees[0].add(1);
		
		System.out.println(dfs(0, 0));
		
	}
	private static int dfs(int num, int val) {
		
		if(dp[num][val] > 0) return dp[num][val];
		
		for(int next : trees[num]) {
			if(isVisited[next]) continue;
			
			isVisited[next] = true;
			int temp = Integer.MAX_VALUE;
			
			for(int i=1; i<K; i++) {
				if(i != val) {
					temp = Math.min(temp, dfs(next, i) + i);
				}
			}
			
			dp[num][val] += temp;
			
			isVisited[next] = false;
		}
		
		return dp[num][val];
	}
}
