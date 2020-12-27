package dp.day14;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Boj2252 {
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
	
	static int N;
	static List<Integer>[] vertices;
	static int[] degree;
	public static void main(String[] args) {
		Scan sc = new Scan();
		N = sc.nextInt();
		int M = sc.nextInt();
		
		vertices = new ArrayList[N+1];
		degree = new int[N+1];
		
		for(int i=1; i<=N; i++) vertices[i] = new ArrayList<>();
		
		for(int i=0; i<M; i++){
			int a = sc.nextInt();
			int b = sc.nextInt();
			
			vertices[a].add(b);
			degree[b]++;
		}
		
		Queue<Integer> que = new LinkedList<>();
		
		for(int i=1; i<=N; i++) 
			if(degree[i] == 0)que.add(i);
		
		StringBuilder sb = new StringBuilder();
		while(!que.isEmpty()){
			int idx = que.poll();
			
			for(int i=0; i<vertices[idx].size(); i++){
				int k = vertices[idx].get(i);
				
				degree[k]--;
				if(degree[k] == 0) que.add(k);
			}
			
			sb.append(idx+" ");
		}
		System.out.println(sb);
	}
}