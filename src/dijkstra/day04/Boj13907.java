package dijkstra.day04;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Boj13907 {
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
	
	static class Road{
		int num;
		int cost;
		int cnt;
		public Road(int num, int cost) {
			super();
			this.num = num;
			this.cost = cost;
			this.cnt = 0;
		}
		public Road(int num, int cost, int cnt) {
			super();
			this.num = num;
			this.cost = cost;
			this.cnt = cnt;
		}
		@Override
		public String toString() {
			return "Road [num=" + num + ", cost=" + cost + ", cnt=" + cnt + "]";
		}
		
	}
	
	static int N, M, K, S, D;
	static List<Road>[] list;
	static int[][] costs;
	public static void main(String[] args) {
		Scan sc = new Scan();
		
		N = sc.nextInt();
		M = sc.nextInt();
		K = sc.nextInt();
		S = sc.nextInt();
		D = sc.nextInt();
		
		list = new ArrayList[N+1];
		costs = new int[N+1][N+1];// ����, ������ ���� ���� �� ������ 2���� �迭
		
		for(int i=1; i<=N; i++) {
			list[i] = new ArrayList<Road>(); 
			Arrays.fill(costs[i], Integer.MAX_VALUE);
		}
		
		for(int i=0; i<M; i++) {
			int from = sc.nextInt();
			int to = sc.nextInt();
			int cost = sc.nextInt();
			
			list[from].add(new Road(to, cost));
			list[to].add(new Road(from, cost));
		}
		

		PriorityQueue<Road> pq = new PriorityQueue<Road>(new Comparator<Road>() {
			@Override
			public int compare(Road o1, Road o2) {
				// TODO Auto-generated method stub
				return o1.cost - o2.cost;
			}
		});
		costs[S][0] = 0;
		pq.add(new Road(S, 0));
		
		int result = Integer.MAX_VALUE;
		int max = 0;
		while(!pq.isEmpty()) {
			Road curr = pq.poll();
			boolean isHigher = false;
			// ������ ���� �� ���ų� ������ cost�߿� ���� ��뺸�� �� �۴ٸ� �ּ� ����� �� �� �����Ƿ� pass
			for(int i=0; i <= curr.cnt; i++) {
				if(costs[curr.num][i] < curr.cost) {
					isHigher = true;
					break;
				}
			}
			
			if(isHigher) continue;
			
			if(curr.num == D) {
				max = Math.max(max, curr.cnt);
				result = Math.min(result, costs[curr.num][curr.cnt]);
				continue;
			}
			
			for(Road next : list[curr.num]) {
				if(curr.cnt + 1 <= N && costs[next.num][curr.cnt+1] > costs[curr.num][curr.cnt] + next.cost) {
					costs[next.num][curr.cnt+1] = costs[curr.num][curr.cnt] + next.cost;
					pq.add(new Road(next.num, costs[next.num][curr.cnt+1], curr.cnt+1));
				}
			}
		}
		System.out.println(result);
		
		int accmulation = 0;
		for(int i=0; i<K; i++) {
			int p = sc.nextInt();
			accmulation += p;
			result = Integer.MAX_VALUE;
			
			for(int j=1; j<=max; j++) {
				if(costs[D][j] == Integer.MAX_VALUE) continue;
				
				result = Math.min(result, costs[D][j] + j*accmulation);
			}
			System.out.println(result);
		}
	}
}