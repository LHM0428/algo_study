package mst.day09;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Boj4195 {
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
	
	static int T, F;
	static Map<String, Integer> person;
	static int[] group = new int[200001];
	static int[] num = new int[200001];
	public static void main(String[] args) {
		Scan sc = new Scan();
		
		T = sc.nextInt();
		person = new HashMap<String, Integer>();
		
		int personId = 0;
		for(int i=0; i<T; i++) {
			Arrays.fill(group, -1);
			Arrays.fill(num, 1);
			F = sc.nextInt();
			for(int f = 0; f<F; f++) {
				String a = sc.next();
				String b = sc.next();
				
				if(!person.containsKey(a)) person.put(a, personId++);
				if(!person.containsKey(b)) person.put(b, personId++);
				
				int idA = person.get(a);
				int idB = person.get(b);
				
				idA = find(idA);
				idB = find(idB);
				
				if(idA == idB) {
					System.out.println(num[idA]);
				}else {
					join(idA, idB);
					System.out.println(num[idA]);
				}
			}
		}
	}
	private static void join(int idA, int idB) {
		group[idA] = idB;
		int a = num[idA];
		num[idA] += num[idB];
		num[idB] += a;
		
	}
	
	private static int find(int id) {
		if(group[id] < 0) return id;
		return group[id] = find(group[id]);
	}
}