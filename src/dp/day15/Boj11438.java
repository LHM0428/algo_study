package dp.day15;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Boj11438 {
	static class Scan{
		private BufferedReader br;
		private StringTokenizer st;
		
		public Scan() {
			br = new BufferedReader(new InputStreamReader(System.in)); 
		}
		
		public String next(){
			while( st == null || !st.hasMoreTokens()){
				try {
					st = new StringTokenizer(br.readLine());
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			return st.nextToken();
		}

		public int nextInt(){
			return Integer.parseInt(next());
		}
	}
	
	static class Node{
		int depth;
		int parent;
		public Node(int depth, int parent) {
			super();
			this.depth = depth;
			this.parent = parent;
		}
	}
	
	static int N, M, KMAX = 17;
	static Node[] nodes;
	static List<Integer>[] list;
	static boolean[] isVisted;
	static int[][] parents;
	static int[] depth;
	
	public static void main(String[] args) {
		Scan sc = new Scan();
		
		N = sc.nextInt();
		nodes = new Node[N+1];
		depth = new int[N+1];
		list = new ArrayList[N+1];
		isVisted = new boolean[N+1];
		parents = new int[KMAX+1][N+1];
		
		for(int i=1; i<=N; i++) list[i] = new ArrayList<Integer>();
		
		for(int i=1; i<N; i++){
			int parent = sc.nextInt();
			int child = sc.nextInt();
			list[parent].add(child);
			list[child].add(parent);
		}
		
		dfs(1, 1, 0);
		for(int k=1; k<=KMAX; k++){
			for(int i=1; i<=N; i++){
				parents[k][i] = parents[k-1][parents[k-1][i]];
			}
		}		
		M = sc.nextInt();
		
		StringBuilder sb = new StringBuilder();
		for(int i=0; i<M; i++){
			int a = sc.nextInt();
			int b = sc.nextInt();
			
			
			if(depth[a] < depth[b]){
				for(int k=KMAX; k>=0 ; k--){
					if( depth[parents[k][b]] >= depth[a])
						b = parents[k][b];
				}
			}else if(depth[a] > depth[b]){
				for(int k=KMAX; k>=0 ; k--){
					if( depth[parents[k][a]] >= depth[b])
						a = parents[k][a];
				}
			}
			
			for(int k=KMAX; k>=0 && a != b; k--){
				if(parents[k][a] != parents[k][b]){
					a = parents[k][a];
					b = parents[k][b];
				}
			}
			
			if(a == b) {
				sb.append(a+"\n");
			}else{
                sb.append(parents[0][a]+"\n");
            }
		}
		System.out.println(sb);
		
	}
	private static void dfs(int i, int d, int parent) {
		if(isVisted[i]) return;
		isVisted[i] = true;
		
		for(int j=0; j<list[i].size(); j++){
			int next = list[i].get(j);
			dfs(next, d+1, i);
		}
		parents[0][i] = parent;
		depth[i] = d;
	}
}
