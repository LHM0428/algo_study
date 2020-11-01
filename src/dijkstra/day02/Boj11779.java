package dijkstra.day02;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Boj11779 {
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
	//버스 class 정의
	static class Bus{
		int to;
		int cost;
		public Bus(int to, int cost) {
			super();
			this.to = to;
			this.cost = cost;
		}
	}
	
	static int N, M;
	static List<Bus>[] list; //버스의 경로를 저장할 list[]
	static int start, end;
	static int[] sumCost; // 최소값을 저장할 array
	static int[] viaCity; // 경유지를 저장할 array
	static final int MAX = 100000001;
	public static void main(String[] args) {
		Scan sc = new Scan();
		
		N = sc.nextInt();
		M = sc.nextInt();
		
		sumCost = new int[N+1];
		list = new ArrayList[N+1];
		viaCity = new int[N+1];
		
		for(int i=1; i<=N; i++) {
			list[i] = new ArrayList<Bus>();
		}
		Arrays.fill(sumCost, MAX);
		
		for(int i=0; i<M; i++) {
			int from = sc.nextInt();
			int to = sc.nextInt();
			int dist = sc.nextInt();
			//단반향
			list[from].add(new Bus(to, dist));
		}
		
		start = sc.nextInt();
		end = sc.nextInt();
		
		PriorityQueue<Bus> pq = new PriorityQueue<Bus>(new Comparator<Bus>() {

			@Override
			public int compare(Bus o1, Bus o2) {
				// TODO Auto-generated method stub
				return o1.cost - o2.cost;
			}
		});
		
		pq.add(new Bus(start, 0));
		sumCost[start] = 0;
		
		while(!pq.isEmpty()) {
			Bus currBus = pq.poll();
						
			for(Bus nextBus : list[currBus.to]) {
				if(sumCost[nextBus.to] > sumCost[currBus.to] + nextBus.cost) {
					sumCost[nextBus.to] = sumCost[currBus.to] + nextBus.cost;
					//최단거리가 갱신 될 때마다, 경로 또한 저장
					viaCity[nextBus.to] = currBus.to;
					pq.add(new Bus(nextBus.to, sumCost[nextBus.to]));
				}
			}
		}
		
		List<Integer> viaCityList = new ArrayList<Integer>();
		
		//도착지부터 역순으로 찾아가는 과정
		int via = end;
		viaCityList.add(via);
		while(via != start) {
			via = viaCity[via];
			viaCityList.add(via);
		}
		
		System.out.println(sumCost[end]);
		System.out.println(viaCityList.size());
		for(int i = viaCityList.size()-1; i > -1; i--) {
			// 뒤에서 부터 출력해준다.
			System.out.printf("%d ", viaCityList.get(i));
		}
		
	}
}