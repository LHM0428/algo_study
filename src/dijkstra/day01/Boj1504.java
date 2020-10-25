package dijkstra.day01;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Boj1504 {
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
		int dist;
		public Vertice(int next, int dist) {
			super();
			this.next = next;
			this.dist = dist;
		}
	}
	
	static int N, E;
	static List<Vertice>[] list;
	static int[][] dist;
	static final int MAX = 200000001;
	public static void main(String[] args) {
		Scan sc = new Scan();
		
		N = sc.nextInt();
		E = sc.nextInt();
		
		list = new ArrayList[N+1];
		dist = new int[N+1][N+1];
		
		for(int i=1; i<=N; i++) {
			list[i] = new ArrayList<Vertice>();
			Arrays.fill(dist[i], MAX);
		}
		
		for(int i=0; i<E; i++) {
			int from = sc.nextInt();
			int to = sc.nextInt();
			int dist = sc.nextInt();
			list[from].add(new Vertice(to, dist));
			list[to].add(new Vertice(from, dist));
		}
		
		int viaFirst = sc.nextInt();
		int viaSecond = sc.nextInt();
		
		setShortestDist(1);
		setShortestDist(viaFirst);
		setShortestDist(viaSecond);
		
		
		int result = Math.min(dist[1][viaFirst]+dist[viaFirst][viaSecond]+dist[viaSecond][N],
				dist[1][viaSecond]+dist[viaSecond][viaFirst]+dist[viaFirst][N]);
		
		
		System.out.println(result < MAX ? result : -1);
		
	}

	private static void setShortestDist(int start) {
		PriorityQueue<Vertice> pq = new PriorityQueue<Vertice>(new Comparator<Vertice>() {
			@Override
			public int compare(Vertice o1, Vertice o2) {
				return o1.dist - o2.dist;
			}
		});
		
		dist[start][start] = 0;
		
		pq.add(new Vertice(start, 0));
		
		while(!pq.isEmpty()) {
			Vertice curr = pq.poll();
			
			for(Vertice next : list[curr.next]) {
				if(dist[start][next.next] > dist[start][curr.next] + next.dist) {
					dist[start][next.next] =  dist[start][curr.next] + next.dist;
					pq.add(new Vertice(next.next, dist[start][next.next]));
				}
			}
		}
	}
}


































