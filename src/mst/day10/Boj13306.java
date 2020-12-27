package mst.day10;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Boj13306 {
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
	static class Query{
		int num;
		int a;
		int b;
	}
	static int N, Q;
	static int[] group, nums;
	static Query[] queries;
	static List<String> ans;
	public static void main(String[] args) {
		Scan sc = new Scan();
		
		N = sc.nextInt();
		Q = sc.nextInt();
		
		group = new int[N+1];
		nums = new int[N+1];
		queries = new Query[N+Q];
		ans = new ArrayList<String>();
		for(int i=1; i<=N; i++) {
			group[i] = i;
		}
		
		for(int i=2; i<=N; i++) {
			int parent = sc.nextInt();
			nums[i] = parent;
		}
		
		StringBuilder sb = new StringBuilder();
		String y = "YES\n";
		String n = "NO\n";
		for(int i=1; i<=N-1+Q; i++) {
			int query = sc.nextInt();
			Query q = new Query();
			q.num = query;
			if(query == 1) {
				q.a = sc.nextInt();
				q.b = sc.nextInt();
				queries[i] = q;
			}else{
				q.a = sc.nextInt();
				queries[i] = q;
			}
		}
		
		for(int i=N-1+Q; i>0; i--) {
			Query q = queries[i];
			if(q.num == 1) {
				if(find(q.a) == find(q.b)) {
					ans.add(y);
				}else {
					ans.add(n);
				}
			}else{
				union(q.a, nums[q.a]);
			}
		}
		for(int i=ans.size()-1; i>-1; i--)sb.append(ans.get(i));
		System.out.println(sb);
	}

	private static int find(int a) {
		if(group[a] == a) return a;
		return group[a] = find(group[a]);
	}

	private static void union(int parent, int a) {
		group[find(a)] = find(parent);
	}
	
}