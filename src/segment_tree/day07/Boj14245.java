package segment_tree.day07;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj14245 {
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
	static int[] tree, lazy;
	static int idx;
	public static void main(String[] args) {
		Scan sc = new Scan();
		
		N = sc.nextInt();
		
		tree = new int[N*4];
		lazy = new int[N*4];
		idx = 0;
		
		for(int i=0; i<N; i++) {
			update(1, 0, N-1, i, i, sc.nextInt());
		}
		
		M = sc.nextInt();
		StringBuilder sb =  new StringBuilder();
		for(int i=0; i<M; i++) {
			int query = sc.nextInt();
			
			if(query == 1) {
				int from = sc.nextInt();
				int to = sc.nextInt();
				int num = sc.nextInt();
				update(1, 0, N-1, from, to, num);
			}else{
				int num = sc.nextInt();
				sb.append(calc(1, 0, N-1, num)+"\n");
			}
		}
		System.out.println(sb);
	}

	private static int calc(int node, int start, int end, int num) {
		lazyUpdate(node,start,end);
		if(start > num || end < num) return 0;
		
		if(start == end){
			return tree[node];
		}
		
		return calc(node*2, start, (start+end)/2, num) + calc(node*2+1, (start+end)/2+1, end, num);
	}

	private static void update(int node, int start, int end, int left, int right, int num) {
		lazyUpdate(node,start,end);
		if(start > right || end < left) return;
		
		if(left<=start && end<=right){
            tree[node] ^= num;
            if(start!=end){
                lazy[node*2] ^=num;
                lazy[node*2+1] ^=num;
            }
            return;
        }
		
		update(node*2, start, (start+end)/2, left, right, num);
		update(node*2+1, (start+end)/2+1, end, left, right, num);
	}

	private static void lazyUpdate(int node, int start, int end) {
		if(lazy[node] != 0) {
			tree[node] ^= lazy[node];
			if(start!=end) {
				lazy[node*2] ^=lazy[node];
                lazy[node*2+1] ^=lazy[node];
			}
			lazy[node] = 0;
		}
	}

}
