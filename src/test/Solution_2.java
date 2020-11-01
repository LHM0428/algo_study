package test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Solution_2 {
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
		
		public double nextDouble() {
			return Double.parseDouble(next());
		}
	}
	
	public static void main(String[] args) {
		Scan sc = new Scan();
		
		int N = sc.nextInt();
		int k = sc.nextInt();
		
		int[] scores = new int[N];
		for(int i=0; i<N; i++) scores[i] = sc.nextInt();
		
		System.out.println(solution(scores, k));
	}
	
	public static int solution(int[] scores, int k) {
		int sum = 0;
		
		PriorityQueue<Integer> pq = new PriorityQueue<Integer>(new Comparator<Integer>() {

			@Override
			public int compare(Integer o1, Integer o2) {
				return o2-o1;// 내림차순으로 정렬 (차이가 큰 순서대로 꺼내온다)
			}

		});
		
		for(int i=0; i<scores.length-1; i++) {
			int diff = scores[i+1] - scores[i];
			pq.add(diff);
			sum += diff;
		}
		
		for(int i=0; i<k-1; i++) {
			sum -= pq.poll(); // 차이가 가장 큰 것 부분을 그룹으로 나눠서 그 차이 값을 0으로 만듦
		}
		
		return sum;
	}
}
