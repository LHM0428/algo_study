package dijkstra.day01;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Boj2644 {
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
	
    static int n, personA, personB, m;
	static int[][] relations;
	static int[] dist;
	public static void main(String[] args) {
		Scan sc = new Scan();
		
		
		n = sc.nextInt();
		personA = sc.nextInt();
		personB = sc.nextInt();
		m = sc.nextInt();
		
		dist = new int[n+1];
		relations = new int[n+1][n+1];
		for(int i=0; i<=n; i++) {
			Arrays.fill(dist, -1);
			
		}
		
		Queue<Integer> q = new LinkedList<Integer>();
		
		
		for(int i=0; i<m; i++) {
			int a = sc.nextInt();
			int b = sc.nextInt();
			
			relations[a][b] = 1;
			relations[b][a] = 1;
		}
		q.add(personA);
		dist[personA] = 0;
		while(!q.isEmpty()) {
			int now = q.poll();
			
			if(now == personB) break;
			
			for(int next=1; next<=n; next++) {
				if(relations[now][next] > 0 && dist[next] < 0 ) {
					dist[next] = dist[now] + 1;
					q.add(next);
				}
			}
		}
		System.out.println(dist[personB]);
	}
}
