package segment_tree.day05;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class Boj2517 {
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
		public long nextLong() {
			return Long.parseLong(next());
		}
	}
	static class Player{
		int rank;
		int speed;
		public Player(int rank, int speed) {
			super();
			this.rank = rank;
			this.speed = speed;
		}
	}
	
	static int N, n, length;
	static Player[] players;
	static int[] tree, numPass;
	public static void main(String[] args) {
		Scan sc = new Scan();
		
		N = sc.nextInt();
		
		n = 1;
		
		while(true) {
			length += n;
			
			if(n >= N) {
				break;
			}
			
			n *= 2;
		}
		
		n--;
		
		tree = new int[length+1];
		numPass = new int[N+1];
		players = new Player[N+1];
		players[0] = new Player(0, 0);
		for(int i=1; i<=N; i++) players[i] = new Player(i, sc.nextInt());
		
		Arrays.sort(players, new Comparator<Player>() {

			@Override
			public int compare(Player o1, Player o2) {
				return o1.speed - o2.speed;
			}
		});
		
		for(int i=1; i<=N; i++) {
			update(players[i]);
			numPass[players[i].rank] = sum(1, players[i].rank -1);
		}
		
		StringBuilder sb = new StringBuilder();
		for(int i=1; i<=N; i++) sb.append(i - numPass[i]+"\n");
		System.out.println(sb.toString());
		
	}
	
	private static int sum(int from, int to) {
		from = from + n;
		to = to + n;
		int sum = 0;
		
		while(from <= to) {
			
			if(from % 2 == 1) {
				sum += tree[from++];
			}
			
			if(to % 2 == 0) {
				sum += tree[to--];
			}
			
			from /= 2;
			to /= 2;
		}
		return sum;
	}

	private static void update(Player player) {
		int idx = n + player.rank;
		tree[idx] = 1;
		
		idx /= 2;
		while(idx > 0) {
			tree[idx] = tree[idx*2] + tree[idx*2+1];
			idx /= 2;
		}
	}
}
































