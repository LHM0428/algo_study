package segment_tree.day06;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj7578 {
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
	
	static class Machine implements Comparable<Machine>{
		int position;
		int num;
		public Machine(int position, int num) {
			super();
			this.position = position;
			this.num = num;
		}
		@Override
		public int compareTo(Machine o) {
			return o.num - this.num;
		}
	}
	
	static int N, n, length;
	static int[] machineA, machineB;
	static int[] tree;
	public static void main(String[] args) {
		Scan sc = new Scan();
		
		N = sc.nextInt();
		
		int MAX_LENGTH = 1000001;
		
		machineA = new int[N+1];
		machineB = new int[MAX_LENGTH];
		for(int i=1; i<=N; i++) {
			machineA[i] = sc.nextInt(); 
		}
		for(int i=1; i<=N; i++) {
			machineB[sc.nextInt()] = i; 
		}
		
		length = 0;
		n = 1;
		while(true) {
			length += n;
			
			if(n >= N) break;
			
			n *= 2;
		}
		n--;
		
		tree = new int[length+1];
		
		long result = 0;
		for(int i=1; i<=N; i++) {
			int num = machineA[i];
			int positionB = machineB[num];
			result += sum(positionB);
			update(positionB);
		}
		
		System.out.println(result);
	}
	
	private static int sum(int positionB) {
		positionB = positionB + n;
		int end = length;
		int sum = 0;
		
		while(positionB <= end) {
			
			if(positionB % 2 == 1) sum += tree[positionB++];
			if(end % 2 == 0) sum += tree[end--];
			
			positionB /= 2;
			end /=2;
		}
		return sum;
	}
	private static void update(int positionB) {
		positionB = positionB + n;
		tree[positionB] = 1;
		
		positionB /= 2;
		
		while(positionB > 0) {
			tree[positionB]++;
			positionB /= 2;
		}
	}
}
