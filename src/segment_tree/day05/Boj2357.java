package segment_tree.day05;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Boj2357 {
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
	
	static int N, M, n;
	static long[] minNums;
	static long[] maxNums;
	
	public static void main(String[] args) {
		
		Scan sc = new Scan();
		N = sc.nextInt();
		M = sc.nextInt();
		
		int length = 0;
		n = 1;
		
		while(true) {
			length += n;
			
			if(n >= N) {
				break;
			}
			
			n *= 2;
		}
		n--;
		
		minNums = new long[length+1];
		maxNums = new long[length+1];
		Arrays.fill(minNums, Long.MAX_VALUE);
		Arrays.fill(maxNums, 0);
		for(int i=1; i<=N; i++) {
			maxNums[n+i] = minNums[n+i] = sc.nextLong();
		}
		
		for(int i=n; i>0; i--) {
			minNums[i] = Math.min(minNums[i*2], minNums[i*2+1]);
			maxNums[i] = Math.max(maxNums[i*2], maxNums[i*2+1]);
		}
		
		long min = 0;
		long max = 0;
		for(int i=0; i<M; i++) {
			int from = sc.nextInt();
			int to = sc.nextInt();
			
			min = findMin(from, to);
			max = findMax(from, to);
			System.out.println(min+" "+max);
		}
		
	}

	private static long findMax(int from, int to) {
		long max = 0;
		from = from + n;
		to = to + n;
		
		while(from <= to) {
			
			if(from % 2 == 1) {
				max = Math.max(max, maxNums[from]);
			}
			
			if(to % 2 == 0) {
				max = Math.max(max, maxNums[to]);
			}
			from = (from+1) /2;
			to = (to-1) /2;
		}
		
		return max;
	}

	private static long findMin(int from, int to) {
		long min = Long.MAX_VALUE;
		from = from + n;
		to = to + n;
		
		while(from <= to) {
			
			if(from % 2 == 1) {
				min = Math.min(min, minNums[from]);
			}
			
			if(to % 2 == 0) {
				min = Math.min(min, minNums[to]);
			}
			
			from = (from+1) /2;
			to = (to-1) /2;
		}
		
		return min;
	}
}














































