package dp.day15;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj1103 {
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
	
	static int N, M;
	static int[][] board, dp;
	static boolean[][] isVisited, isFinished;
	static boolean isCircle = false;
	public static void main(String[] args) {
		Scan sc = new Scan();
		
		N = sc.nextInt();
		M = sc.nextInt();
		
		board = new int[N+1][M+1];
		dp = new int[N+1][M+1];
		isVisited = new boolean[N+1][M+1];
		isFinished = new boolean[N+1][M+1];
		
		for(int i=1; i<=N; i++) {
			String row = sc.next();
			char[] block = row.toCharArray();
			
			for(int j=1; j<=M; j++) board[i][j] = block[j-1];
		}
		
		
		move(1, 1);
		
		if(isCircle) System.out.println(-1);
		else System.out.println(dp[1][1]);
	}
	
	private static int move(int row, int col) {
		if(isFinished[row][col]) {
			isCircle = true;
			return -1;
		}
		
		if(dp[row][col] > 0) return dp[row][col];
		
		if(board[row][col] == 'H') return 0;
		
		int move = board[row][col] - '0';
		isFinished[row][col] = true;
		
		if(row + move <= N) dp[row][col] = Math.max(dp[row][col], move(row+move, col));
		if(row - move > 0) dp[row][col] = Math.max(dp[row][col], move(row-move, col));
		if(col + move <= M) dp[row][col] = Math.max(dp[row][col], move(row, col+move));
		if(col - move > 0) dp[row][col] = Math.max(dp[row][col], move(row, col-move));
		
		dp[row][col]++;
		
		isFinished[row][col] = false;
		
		return dp[row][col];
		
	}
}