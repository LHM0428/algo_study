package dp.day13;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Boj2096 {
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
	static int[][] stairs, dpMax, dpMin;
	public static void main(String[] args) {
		Scan sc = new Scan();
		
		N = sc.nextInt();
		
		dpMax = new int[N+1][3];
		dpMin = new int[N+1][3];
		stairs = new int[N+1][3];
		
		for(int i=1; i<=N; i++) {
			Arrays.fill(dpMin[i], Integer.MAX_VALUE);
			stairs[i][0] = sc.nextInt();
			stairs[i][1] = sc.nextInt();
			stairs[i][2] = sc.nextInt();
			
			
			for(int position=0; position < 3; position++) {
				
				switch(position) {
				case 0:
					for(int j=0; j<2; j++) {
						dpMax[i][position] = Math.max(dpMax[i][position], dpMax[i-1][j]+stairs[i][position]);
						dpMin[i][position] = Math.min(dpMin[i][position], dpMin[i-1][j]+stairs[i][position]);
					}
					break;
				case 1:
					for(int j=0; j<3; j++) {
						dpMax[i][position] = Math.max(dpMax[i][position], dpMax[i-1][j]+stairs[i][position]);
						dpMin[i][position] = Math.min(dpMin[i][position], dpMin[i-1][j]+stairs[i][position]);
					}
					break;
				case 2:
					for(int j=1; j<3; j++) {
						dpMax[i][position] = Math.max(dpMax[i][position], dpMax[i-1][j]+stairs[i][position]);
						dpMin[i][position] = Math.min(dpMin[i][position], dpMin[i-1][j]+stairs[i][position]);
					}
					break;
				}
			}
		}
		
		int max = Math.max(dpMax[N][0], Math.max(dpMax[N][1], dpMax[N][2]));
		int min = Math.min(dpMin[N][0], Math.min(dpMin[N][1], dpMin[N][2]));
		
		System.out.println(max+" "+min);
	}
}