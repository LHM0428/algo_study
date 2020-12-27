package mst.day12;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Boj2465 {
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
	static int MAX = 2000000000;
	static int[] height, order, tree, result;
	public static void main(String[] args) {
		Scan sc = new Scan();
		
		N = sc.nextInt();
		
		height = new int[N+1];
		order = new int[N+1];
		result = new int[N+1];
		
		tree = new int[N*4+1];
		
		
		for(int i=1; i<=N; i++) {
			height[i] = sc.nextInt();
		}
		for(int i=1; i<=N; i++) order[i] = sc.nextInt();
		
		Arrays.sort(height);
		
		init(1, 1, N);
		
		for(int i=N; i>0; i--) {
			int position = find(1, 1, N, order[i]+1);
			
			result[i] = height[position];
			update(1, 1, N, order[i]+1, 0);
		}
		StringBuilder sb = new StringBuilder();
		for(int i=1; i<=N; i++) sb.append(result[i]+"\n");
		System.out.println(sb);
		
	}
	private static void update(int n, int start, int end, int order, int value) {
		if(start == end) {
			tree[n] = value;
			return;
		}
		
		if(order <= tree[n*2]) update(n*2, start, (start+end)/2, order, value);
		else update(n*2+1, (start+end)/2+1, end, order-tree[n*2], value);
		
		tree[n] = tree[n*2] + tree[n*2+1];
	}
	
	private static void init(int n, int start, int end) {
		if(start == end) {
			tree[n] = 1;
			return;
		}
		
		init(n*2, start, (start+end)/2);
		init(n*2+1, (start+end)/2+1, end);
		
		tree[n] = tree[n*2] + tree[n*2+1];
	}
	
	
	private static int find(int n, int start, int end, int order) {
		if(start == end) {
			return start;
		}
		if(order <= tree[n*2]) return find(n*2, start, (start+end)/2, order);
		else return find(n*2+1, (start+end)/2+1, end, order-tree[n*2]);
	}
}