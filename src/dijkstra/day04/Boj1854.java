package dijkstra.day04;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Boj1854 {
	static class Scan {
		BufferedReader br;
		StringTokenizer st;

		public Scan() {
			br = new BufferedReader(new InputStreamReader(System.in));
		}

		public String next() {
			while (st == null || !st.hasMoreTokens()) {
				try {
					st = new StringTokenizer(br.readLine());
				} catch (IOException e) {
					e.printStackTrace();
				}

			}
			return st.nextToken();
		}

		public int nextInt() {
			return Integer.parseInt(next());
		}

		public double nextDouble() {
			return Double.parseDouble(next());
		}
	}

	static class Vertice {
		int num;
		int dist;
		public Vertice(int num, int dist) {
			super();
			this.num = num;
			this.dist = dist;
		}
	}
	
	static int N, M, K;
	static List<Vertice>[] list;
	static PriorityQueue<Integer>[] dist;
	public static void main(String[] args) {
		Scan sc = new Scan();
		
		N = sc.nextInt();
		M = sc.nextInt();
		K = sc.nextInt();
		
		list = new ArrayList[N+1];
		dist = new PriorityQueue[N+1];
		
		for(int i=1; i<=N; i++) {
			list[i] = new ArrayList<Vertice>();
			dist[i] = new PriorityQueue<Integer>(new Comparator<Integer>() {
				@Override
				public int compare(Integer o1, Integer o2) {
					return o2 - o1;
				}
			});
		}
		
		for(int i=0; i<M; i++) {
			int from = sc.nextInt();
			int to = sc.nextInt();
			int dist = sc.nextInt();
			
			list[from].add(new Vertice(to, dist));
		}
		
		PriorityQueue<Vertice> pq = new PriorityQueue<Vertice>(new Comparator<Vertice>() {

			@Override
			public int compare(Vertice o1, Vertice o2) {
				return o1.dist - o2.dist;
			}
		});
		
		dist[1].add(0);
		pq.add(new Vertice(1, 0));
		
		List<Integer> result = new ArrayList<Integer>();
		while(!pq.isEmpty()) {
			Vertice curr = pq.poll();
			
			for(Vertice next : list[curr.num]) {
				int minDist = dist[next.num].peek() == null ? Integer.MAX_VALUE : dist[next.num].peek();
				
				if(dist[next.num].size() < K || minDist > curr.dist + next.dist) {
					if(dist[next.num].size() == K) dist[next.num].poll();
					minDist = curr.dist + next.dist;
					dist[next.num].add(minDist);
					pq.add(new Vertice(next.num, minDist));
				}
			}
		}
		
		for(int i=1; i<=N; i++) {
			if(dist[i].size() < K) System.out.println(-1);
			else System.out.println(dist[i].peek());
		}
	}
}
