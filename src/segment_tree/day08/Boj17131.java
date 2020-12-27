package segment_tree.day08;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Boj17131 {
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
	static class Point {
		int x;
		int y;
		public Point(int x, int y) {
			super();
			this.x = x+200001;
			this.y = y+200001;
		}
		@Override
		public String toString() {
			return "Point [x=" + (x-200001) + ", y=" + (y-200001) + "]";
		}
	}
	
	static int N, n, length;
	static Point[] points;
	static long[] tree;
	static final int MOD = 1000000007;
	static final int MAX = 400001;
	public static void main(String[] args) {
		Scan sc = new Scan();
		
		N = sc.nextInt();
		
		tree = new long[MAX*4];
		points = new Point[N+1];
		points[0] = new Point(-MAX, -MAX);
		
		for(int i=1; i<=N; i++) {
			Point p = new Point(sc.nextInt(), sc.nextInt());
			points[i] = p;
		}
		
		Arrays.sort(points, new Comparator<Point>() {
			@Override
			public int compare(Point o1, Point o2) {
				if(o1.x == o2.x) return o1.y - o2.y;
				return o1.x - o2.x;
			}
		});
		
		Map<String, Long> left = new HashMap<>();
		Map<String, Long> right = new HashMap<>();
		
		
		for(int i=1; i<=N; i++) {
			Point p = points[i];
			left.put(p.x+"-"+p.y, calc(1, 1, MAX, p.y+1, MAX)%MOD);
//			System.out.println(p+" => "+left.get(p.x+"-"+p.y));
			update(1, 1, MAX, p.y, 1);
		}
//		System.out.println(Arrays.toString(left.values().toArray()));
		
		Arrays.fill(tree, 0);
		
		Arrays.sort(points, new Comparator<Point>() {
			@Override
			public int compare(Point o1, Point o2) {
				if(o1.x == o2.x) return o2.y - o1.y;
				return  o1.x - o2.x;
			}
		});
		
		for(int i=N; i>=1; i--) {
			Point p = points[i];
			right.put(p.x+"-"+p.y, calc(1, 1, MAX, p.y+1, MAX)%MOD);
//			System.out.println(p+" => "+right.get(p.x+"-"+p.y));
			update(1, 1, MAX, p.y, 1);
		}
//		System.out.println(Arrays.toString(right.values().toArray()));
		
		long result = 0;
		for(int i=1; i<=N; i++) {
			Point p = points[i];
			String key = p.x+"-"+p.y;
			result = (result+(left.get(key)*right.get(key))%MOD)%MOD;
		}
		
		System.out.println(result);
	}
	
	private static long calc(int node, int start, int end, int left, int right) {
		if(start > right || end < left) return 0;
		
		if(left <= start && end <= right){
			return tree[node];
		}
		
		return (calc(node*2, start, (start+end)/2, left, right) + calc(node*2+1, (start+end)/2+1, end, left, right))%MOD;
	}
	
	private static void update(int node, int start, int end, int num, int val) {
		if(start > num || end < num) return;
		
		if(start == end){
            tree[node] += val;
            return;
        }
		
		update(node*2, start, (start+end)/2, num, val);
		update(node*2+1, (start+end)/2+1, end, num, val);
		tree[node] = (tree[node*2] + tree[node*2+1])%MOD;
	}
}