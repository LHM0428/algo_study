package dijkstra.day01;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Boj1753 {
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
	
	static class Vertice{
		int next;
		int weight;
		public Vertice(int next, int weight) {
			super();
			this.next = next;
			this.weight = weight;
		}
	}
	static int V,E,TOP;
	static List<Vertice>[] vertices;
	static int[] D;
	static final int MAX = 3000001;
	public static void main(String[] args) {
		Scan sc = new Scan();
		
		V = sc.nextInt();
		E = sc.nextInt();
		TOP = sc.nextInt();
		
		vertices = new ArrayList[V+1];
		D = new int[V+1];
		
		for(int i=1; i<=V; i++){
			vertices[i] = new ArrayList<>();
			D[i] = MAX;
		}
		
		for(int i=0; i<E; i++){
			vertices[sc.nextInt()].add(new Vertice(sc.nextInt(), sc.nextInt()));
		}
		
		PriorityQueue<Vertice> pq = new PriorityQueue<>(new Comparator<Vertice>() {
			@Override
			public int compare(Vertice o1, Vertice o2) {
				return o1.weight - o2.weight;
			}
		});
		
		D[TOP] = 0;
		
		pq.add(new Vertice(TOP, 0));
		
		while(!pq.isEmpty()){
			Vertice v = pq.poll();
			
			for(Vertice next : vertices[v.next]){
				if(D[next.next] > D[v.next] + next.weight){
					D[next.next] = D[v.next] + next.weight;
					pq.add(new Vertice(next.next, D[next.next]));
				}
			}
		}
		
		for(int i=1; i<=V; i++){
			if(D[i] == MAX) System.out.println("INF");
			else System.out.println(D[i]);
		}
	}
}
