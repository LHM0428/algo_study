package segment_tree.day07;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Boj14288 {
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
	static int[] tree, revTree, lazy, left, right;
	static int idx;
	static boolean upToDown = true;
	static List<Integer>[] employees;
	public static void main(String[] args) {
		Scan sc = new Scan();
		
		N = sc.nextInt();
		M = sc.nextInt();
		
		employees = new ArrayList[N+1];
		left = new int[N+1];
		right = new int[N+1];
		
		
		tree = new int[N*4];
		revTree = new int[N*4];
		lazy = new int[N*4];
		idx = 0;
		
		for(int i=1; i<=N; i++) {
			employees[i] = new ArrayList<Integer>();
		}
		
		sc.nextInt();
		for(int i=2; i<=N; i++) {
			int empNum = sc.nextInt();
			employees[empNum].add(i);
		}
		
		dfs(1);
		StringBuilder sb = new StringBuilder();
		for(int i=0; i<M; i++) {
			int query = sc.nextInt();
			
			if(query == 1) {
				int empNum = sc.nextInt();
				int score = sc.nextInt();
				if(upToDown) update(1, 1, N, left[empNum], right[empNum], score);
				else revUpdate(1, 1, N, left[empNum], score);
			}else if(query == 2){
				int empNum = sc.nextInt();
				sb.append((calc(1, 1, N, left[empNum])+revCalc(1, 1, N, left[empNum], right[empNum]))+"\n");
			}else {
				upToDown = !upToDown;
			}
		}
		System.out.println(sb);
	}
	

	private static int calc(int node, int start, int end, int left) {
		lazyUpdate(node,start,end);
		if(start > left || end < left) return 0;
		
		if(start == end){
			return tree[node];
		}
		
		return calc(node*2, start, (start+end)/2, left) + calc(node*2+1, (start+end)/2+1, end, left);
	}

	private static void update(int node, int start, int end, int left, int right, int score) {
		lazyUpdate(node,start,end);
		if(start > right || end < left) return;
		
		if(left<=start && end<=right){
            tree[node] += score;
            if(start!=end){
                lazy[node*2]+=score;
                lazy[node*2+1]+=score;
            }
            return;
        }
		
		update(node*2, start, (start+end)/2, left, right, score);
		update(node*2+1, (start+end)/2+1, end, left, right, score);
		tree[node] = tree[node*2] + tree[node*2+1];
	}

	private static void lazyUpdate(int node, int start, int end) {
		if(lazy[node] != 0) {
			tree[node] += lazy[node];
			if(start!=end) {
				lazy[node*2]+=lazy[node];
                lazy[node*2+1]+=lazy[node];
			}
			lazy[node] = 0;
		}
	}

	private static void dfs(int i) {
		left[i] = ++idx;
		
		for(int next : employees[i]) dfs(next);
		
		right[i] = idx;
	}
	
	private static int revCalc(int node, int start, int end, int left, int right) {
		if(start > right || end < left) return 0;
		
		if(left<=start && end <=right){
			return revTree[node];
		}
		
		return revCalc(node*2, start, (start+end)/2, left, right) + revCalc(node*2+1, (start+end)/2+1, end, left, right);
	}

	private static void revUpdate(int node, int start, int end, int left, int score) {
		if(start > left || end < left) return;
		
		if(start == end){
			revTree[node] += (end-start+1)*score;
            return;
        }
		
		revUpdate(node*2, start, (start+end)/2, left, score);
		revUpdate(node*2+1, (start+end)/2+1, end, left, score);
		revTree[node] = revTree[node*2] + revTree[node*2+1];
	}
}
