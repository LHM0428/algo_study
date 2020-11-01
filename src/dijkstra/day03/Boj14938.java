package dijkstra.day03;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Boj14938 {
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
	
	static class Area{
		int areaNum;
		int numItem;
		int distance;
		public Area() {
		}
		public Area(int areaNum, int numItem, int distance) {
			super();
			this.areaNum = areaNum;
			this.numItem = numItem;
			this.distance = distance;
		}
		
	}
	
	static int N, M, R;
	static List<Area>[] list;
	static Area[] areas;
	static int[] distance;
	static int sum = 0;
	public static void main(String[] args) {
		Scan sc = new Scan();
		
		N = sc.nextInt();
		M = sc.nextInt();
		R = sc.nextInt();
		
		
		list = new ArrayList[N+1];
		areas = new Area[N+1];
		distance = new int[N+1];
		
		for(int i=1; i<=N; i++) list[i] = new ArrayList<Area>();
		
		
		for(int i=1; i<=N; i++) {
			areas[i] = new Area();
			areas[i].areaNum = i;
			areas[i].numItem = sc.nextInt();
		}
		
		for(int i=0; i<R; i++) {
			int from = sc.nextInt();
			int to = sc.nextInt();
			int dist = sc.nextInt();
			
			Area area = new Area();
			area.areaNum = to;
			area.distance = dist;
			list[from].add(area);
			
			area = new Area();
			area.areaNum = from;
			area.distance = dist;
			list[to].add(area);
		}
		
		
		for(int i=1; i<=N; i++)
			sum = Math.max(sum, dijkstra(i));
		
		System.out.println(sum);
	}
	
	private static int dijkstra(int start) {
		PriorityQueue<Area> pq = new PriorityQueue<Area>(new Comparator<Area>() {

			@Override
			public int compare(Area o1, Area o2) {
				return o1.distance - o2.distance;
			}
		});
		
		Arrays.fill(distance, Integer.MAX_VALUE);
		
		distance[start] = 0;
		pq.add(new Area(start, areas[start].numItem, 0));
		
		while(!pq.isEmpty()) {
			Area curr = pq.poll();
			
			for(Area next : list[curr.areaNum]) {
				int nextArea = next.areaNum;
				if(distance[nextArea] > distance[curr.areaNum] + next.distance) {
					distance[nextArea] = distance[curr.areaNum] + next.distance;
					pq.add(new Area(nextArea, areas[nextArea].numItem, distance[nextArea]));
				}
			}
		}
		
		int sum = 0;
		for(int i=1; i<=N; i++) {
			if(distance[i] <= M) sum += areas[i].numItem;
		}
		
		return sum;
	}
}





























