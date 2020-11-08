package segment_tree.day05;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj2042 {
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
	
	static int N, M, K, length, n;
	static long[] nums;
	public static void main(String[] args) {
		Scan sc = new Scan();
		
		N = sc.nextInt();
		M = sc.nextInt();
		K = sc.nextInt();
		
		n = 1;
		length = 0;
		while(true) {
			length += n;
			if(N <= n && N > n/2) {
				break;
			}
			n *= 2;
		}
		
		nums = new long[length+1];
		
		for(int i=0; i<N; i++) {
			nums[n+i] = sc.nextLong();
		}
		
		for(int i=n-1; i>0; i++)
			nums[i] = nums[i*2] + nums[i*2+1];
		
		
		for(int i=0; i<M+K; i++) {
			int a = sc.nextInt();
			int b = sc.nextInt();
			long c = sc.nextLong();
			
			if(a == 1) {
				update(b, c);
			}else{
				System.out.println(sum(b, (int)c));
			}
		}
		
	}
	private static long sum(int b, int c) {
		long sum = 0;
		
		b = n + b - 1;
		c = n + c - 1;
		
		while(b <= c) {
			if(b%2 == 1) {
				sum += nums[b];
			}
			if(c%2 == 0) {
				sum += nums[c];
			}
			
			b = (b+1)/2;
			c = (c-1)/2;
		}
		
		return sum;
	}
	
	private static void update(int b, long c) {
		int position = n + b - 1;
		long diff = c - nums[position];
		
		while(position > 0) {
			nums[position] = nums[position] + diff;
			position /= 2;
		}
		
	}
}
