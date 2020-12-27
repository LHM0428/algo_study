package mst.day12;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj1321 {
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
	
	static int N, M;
	static int[] tree;
	public static void main(String[] args) {
		Scan sc = new Scan();
		StringBuilder sb = new StringBuilder();
		
		N = sc.nextInt();
		tree = new int[N*4];
		
		for(int i=1; i<=N; i++) update(1, 1, N, i, sc.nextInt());
		
		M = sc.nextInt();
		for(int i=0; i<M; i++) {
			int q = sc.nextInt();
			int num = sc.nextInt();
			
			if(q == 1) {
				int val = sc.nextInt();
				
				update(1, 1, N, num, val);
			}else {
				
				sb.append(find(1, 1, N, num)+"\n");
			}
		}
		System.out.println(sb);
		
	}
	
	private static int find(int node, int start, int end, int num) {
		if(start == end) return start;
		
		if(num <= tree[node*2]) {
			return find(node*2, start, (start+end)/2, num);
		}else
			return find(node*2+1, (start+end)/2+1, end, num-tree[node*2]);
	}

	private static void update(int node, int start, int end, int num, int val) {
		if(start > num || end < num) return;
		
		if(start == end){
            tree[node] += val;
            return;
        }
		
		update(node*2, start, (start+end)/2, num, val);
		update(node*2+1, (start+end)/+1, end, num, val);
		tree[node] = tree[node*2] + tree[node*2+1];
	}
}