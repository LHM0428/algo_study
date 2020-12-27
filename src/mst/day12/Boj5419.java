package mst.day12;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.StringTokenizer;

public class Boj5419 {
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
		int resizeY;
		public Point(int x, int y) {
			super();
			this.x = x+1000000001;
			this.y = y+1000000001;
		}
		@Override
		public String toString() {
			return "Point [x=" + (x) + ", y=" + (y) + "]";
		}
	}
	
	static int N, T;
	static List<Point> points;
	static int[] tree;
	public static void main(String[] args) throws IOException {
		Scan sc = new Scan();
		
		StringBuilder sb = new StringBuilder();
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		points = new ArrayList<Point>();
		T = sc.nextInt();
		for(int t=0; t<T; t++) {
			points.clear();
			N = sc.nextInt();
			tree = new int[N*4];
			
			for(int i=0; i<N; i++) {
				points.add(new Point(sc.nextInt(), sc.nextInt()));
			}
			
			Collections.sort(points, new Comparator<Point>() {
				@Override
				public int compare(Point o1, Point o2) {
					return o1.y - o2.y;
				}
			});
			
			
			int y = 1;
			points.get(0).resizeY = y;
			for(int i=1; i<N; i++) {
				if(points.get(i-1).y != points.get(i).y) y++;
				points.get(i).resizeY = y;
			}
			
			Collections.sort(points, new Comparator<Point>() {
				@Override
				public int compare(Point o1, Point o2) {
					if(o1.x == o2.x) return o1.y - o2.y;
					return  o2.x - o1.x;
				}
			});
			
			long sum = 0;
			for(int i=0; i<N; i++) {
				Point p = points.get(i);
				
				sum += calc(1, 1, N, 1, p.resizeY);
				update(1, 1, N, p.resizeY, 1);
			}
			sb.append(sum).append("\n");
		}
		bw.write(sb.toString());
		bw.flush();
	}
	private static void update(int node, int start, int end, int num, int val) {
		if(start > num || end < num) return;
		
		tree[node]++;
		if(start == end){
            return;
        }
		
		update(node*2, start, (start+end)/2, num, val);
		update(node*2+1, (start+end)/2+1, end, num, val);
	}
	
	private static int calc(int node, int start, int end, int left, int right) {
		if(start > right || end < left) return 0;
		
		if(left <= start && end <= right){
			return tree[node];
		}
		
		return calc(node*2, start, (start+end)/2, left, right) + calc(node*2+1, (start+end)/2+1, end, left, right);
	}
}