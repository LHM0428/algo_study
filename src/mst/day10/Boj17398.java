package mst.day10;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Boj17398 {
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
	
	static class Node {
		int from;
		int to;
		boolean isConnected;
		public Node(int from, int to) {
			super();
			this.from = from;
			this.to = to;
			this.isConnected = true;
		}
	}
	
	static int N, M, Q;
	static int[] group, disConnect;
	static ArrayList<Node> list;
	public static void main(String[] args) {
		Scan sc = new Scan();
		
		N = sc.nextInt();
		M = sc.nextInt();
		Q = sc.nextInt();
		
		group = new int[N+1];
		disConnect = new int[Q+1];
		list = new ArrayList<Node>();
		
		Arrays.fill(group, -1);
		list.add(new Node(0, 0));
		for(int i=1; i<=M; i++) {
			Node n = new Node(sc.nextInt(), sc.nextInt());
			list.add(n);
		}
		
		for(int i=1; i<=Q; i++) {
			disConnect[i] = sc.nextInt();
			list.get(disConnect[i]).isConnected = false;
		}
		
		for(int i=1; i<=M; i++) {
			Node n = list.get(i);
			if(n.isConnected) union(n.from, n.to);
		}
		
		long sum = 0;
		for(int i=Q; i>=1; i--) {
			Node n = list.get(disConnect[i]);
			
			int from = find(n.from);
			int to = find(n.to);
			
			if(from == to) continue;
			sum += group[from] * group[to];
			
			union(from, to);
			
		}
		
		System.out.println(sum);
	}
	
	
	private static void union(int from, int to) {
		from = find(from);
		to = find(to);
		
		if(from == to) return;
		
		group[from] += group[to];
		group[to] = from;
		
	}
	private static int find(int a) {
		if(group[a] < 0) return a;
		return group[a] = find(group[a]);
	}
}