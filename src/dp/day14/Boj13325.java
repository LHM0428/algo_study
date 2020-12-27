package dp.day14;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj13325 {
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
	
	static int K, length;
	static int[] tree;
	static long result = 0;
	public static void main(String[] args) {
		Scan sc = new Scan();
		
		K = sc.nextInt();
		int m = 2;
		length = m;
		
		for(int i=1; i<K; i++) {
			length = length + m*2;
			m *= 2;
		}
		
		length++;
		
		tree = new int[length+1];
		
		for(int i=2; i<=length; i++) {
			result += tree[i] = sc.nextInt();
			
		}
		
		update(1, 0);
		
		System.out.println(result);
	}
	
	private static int update(int node, int k) {
		if(k == K) return tree[node];
		
		int diff = update(node*2, k+1) - update(node*2+1, k+1);
//		System.out.println(node+" : "+diff);
		if(diff > 0) {
			tree[node*2+1] += diff;
		}else {
			tree[node*2] -= diff;
		}
		result += Math.abs(diff);
		return tree[node] += tree[node*2];
	}
}