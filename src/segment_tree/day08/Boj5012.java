package segment_tree.day08;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Boj5012 {
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
	
	static int N, n, length;
	static int[] nums;
	static int[] tree;
	public static void main(String[] args) {
		Scan sc = new Scan();
		
		N = sc.nextInt();
		
		nums = new int[N+1];
		tree = new int[N*4];
		for(int i=1; i<=N; i++) {
			nums[i] = sc.nextInt(); 
		}
		
		long biggerArr[] = new long[N+1];
		long smallerArr[] = new long[N+1];
		for(int i=1; i<=N; i++) {
			biggerArr[i] = calc(1, 1, N, nums[i]+1, N);
			update(1, 1, N, nums[i], 1);
		}
		
		Arrays.fill(tree, 0);
		for(int i=N; i>=1; i--) {
			smallerArr[i] = calc(1, 1, N, 1, nums[i]-1);
			update(1, 1, N, nums[i], 1);
		}
		
		long result = 0;
		for(int i=1; i<=N; i++) result += biggerArr[i]*smallerArr[i];
		
		System.out.println(result);
	}
	
	private static int calc(int node, int start, int end, int left, int right) {
		if(start > right || end < left) return 0;
		
		if(left <= start && end <= right){
			return tree[node];
		}
		
		return calc(node*2, start, (start+end)/2, left, right) + calc(node*2+1, (start+end)/2+1, end, left, right);
	}
	
	private static void update(int node, int start, int end, int num, int score) {
		if(start > num || end < num) return;
		
		if(start == end){
            tree[node] += score;
            return;
        }
		
		update(node*2, start, (start+end)/2, num, score);
		update(node*2+1, (start+end)/2+1, end, num, score);
		tree[node] = tree[node*2] + tree[node*2+1];
	}
}