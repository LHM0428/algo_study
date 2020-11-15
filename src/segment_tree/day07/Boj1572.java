package segment_tree.day07;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj1572 {
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
	
	static final int MAX = 65537;
	static int N, K;
	static int[] tree;
	static int[] nums;
	public static void main(String[] args) {
		Scan sc = new Scan();
		
		N = sc.nextInt();
		K = sc.nextInt();
		
		tree = new int[MAX*4];
		nums = new int[N+1];
		long sum = 0;
		for(int i=1; i<=N; i++) {
			nums[i] = sc.nextInt();
		}
		
		for(int i = 1; i<=N; i++) {
			update(1, 0, MAX, nums[i], 1);
			if(i >= K) {
				sum += find(1, 0, MAX, (K+1)/2);
				update(1, 0, MAX, nums[i-K+1], -1);
			}
		}
		System.out.println(sum);
	}
	
	private static int find(int node, int start, int end, int val) {
		if(start == end){
			return start;
		}
		if(val <= tree[node*2]) return find(node*2, start, (start+end)/2, val);
		else return  find(node*2+1, (start+end)/2+1, end, val - tree[node*2]);
	}
	
	private static void update(int node, int start, int end, int num, int val) {
		if(start > num || end < num) return;
		
		if(start == end){
            tree[node] += val;
            return;
        }
		
		update(node*2, start, (start+end)/2, num, val);
		update(node*2+1, (start+end)/2+1, end, num, val);
		tree[node] = tree[node*2] + tree[node*2+1];
	}
}




















