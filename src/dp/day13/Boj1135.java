package dp.day13;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Boj1135 {
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
	static class Node{
		int idx;
		int cost;
		public Node(int idx, int cost) {
			super();
			this.idx = idx;
			this.cost = cost;
		}
		public Node(int idx) {
			this.idx = idx;
		}
	}
	
	static int N, time;
	static List<Node>[] vertices;
	static Node[] nodes;
	public static void main(String[] args) {
		Scan sc = new Scan();
		N = sc.nextInt();
		
		vertices = new ArrayList[N];
		nodes = new Node[N];
		for(int i=0; i<N; i++) {
			vertices[i] = new ArrayList<>();
			nodes[i] = new Node(i);
		}
		
		sc.nextInt();
		
		for(int i=1; i<N; i++){
			vertices[sc.nextInt()].add(nodes[i]);
		}
		checkDepth(0);

		
		System.out.println(nodes[0].cost);
	}
	
	private static int checkDepth(int i) {
		PriorityQueue<Integer> que = new PriorityQueue<>(new Comparator<Integer>() {
			@Override
			public int compare(Integer o1, Integer o2) {
				return o2 - o1;
			}
		});
;
		for(int j=0; j<vertices[i].size(); j++){
			Node node = vertices[i].get(j);
			que.add(checkDepth(node.idx));
		}
		int size = que.size();
		for(int j=1; j<=size; j++){
			nodes[i].cost = Math.max(nodes[i].cost,  j+que.poll());
		}
		
		//System.out.println(i+" : "+nodes[i].cost);
		return nodes[i].cost;
	}
	
}