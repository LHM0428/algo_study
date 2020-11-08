package dijkstra.day04;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Boj1486 {
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
	
	static class Edge{
		int from;
		int to;
		int cost;
		public Edge(int from, int to, int cost) {
			super();
			this.from = from;
			this.to = to;
			this.cost = cost;
		}
	}
	static class Point{
		int row;
		int col;
		public Point(int row, int col) {
			super();
			this.row = row;
			this.col = col;
		}
	}
	static int N, M, T, D;
	static int[][] mountain;
	static List<Edge>[] edges;
	static List<Edge>[] revEdges;
	static int[] dRow = {0, 0, 1, -1}, dCol = {1, -1, 0, 0};
	static int[][] sumCost, sumCostBack, tempsumCost;
	public static void main(String[] args) {
		Scan sc = new Scan();
		
		N = sc.nextInt();
		M = sc.nextInt();
		T = sc.nextInt();
		D = sc.nextInt();
		
		mountain = new int[N][M];
		edges = new ArrayList[N*M];
		revEdges = new ArrayList[N*M];
		sumCost = new int[N][M];
		sumCostBack = new int[N][M];
		tempsumCost = new int[N][M];
		
		for(int i=0; i<N*M; i++){
			edges[i] = new ArrayList<>();
			revEdges[i] = new ArrayList<>();
		}
		
		for(int i=0; i<N; i++){
			char temp[] = sc.next().toCharArray();
			for(int j=0; j<M; j++){
				if('A' <= temp[j] && 'Z' >= temp[j] ){
					mountain[i][j] = temp[j] - 'A';
				}else{
					mountain[i][j] = temp[j] - 'a' + 26;
				}
			}
		}
		
		for(int i=0; i<N; i++){
			Arrays.fill(sumCost[i], Integer.MAX_VALUE);
			Arrays.fill(sumCostBack[i], Integer.MAX_VALUE);
			for(int j=0; j<M; j++){
				int from = j + i*M;
				
				for(int k=0; k<4; k++){
					int nRow = i+dRow[k];
					int nCol = j+dCol[k];
					
					if(nRow > -1 && nRow < N && nCol > -1 && nCol < M){
						int cost = mountain[nRow][nCol] - mountain[i][j];
						if(Math.abs(cost) > T) continue;
						
						int to = nCol + nRow*M;
						
						if( cost > 0 ){
							edges[from].add(new Edge(from, to, cost*cost));
						}else{
							edges[from].add(new Edge(from, to, 1));
						}
						if( cost < 0 ){
							revEdges[from].add(new Edge(from, to, cost*cost));
						}else{
							revEdges[from].add(new Edge(from, to, 1));
						}
						
					}
				}
			}
		}
		
		
		dijkstra(sumCost, edges);
		dijkstra(sumCostBack, revEdges);
		
		int max = mountain[0][0];
		
		for(int i=0; i<N; i++){
			for(int j=0; j<M; j++){
				if(sumCost[i][j] + sumCostBack[i][j] <= D){
					max = Math.max(mountain[i][j], max);
				}
			}
		}
		
		System.out.println(max);
	}
	private static void dijkstra(int[][] sumCost, List<Edge>[] edges) {
		Queue<Edge> que = new LinkedList<>();
		sumCost[0][0] = 0;
		que.add(new Edge(0, 0, 0));
		
		while(!que.isEmpty()){
			Edge p = que.poll();
			int n = p.to;
			int row = p.to / M;
			int col = p.to % M;
			
			for(Edge e : edges[n]){
				int nRow = e.to / M;
				int nCol = e.to % M;
				
				if(sumCost[nRow][nCol] > sumCost[row][col] + e.cost){
					sumCost[nRow][nCol] = sumCost[row][col] + e.cost;
					que.add(e);
				}
			}
		}
	}
}