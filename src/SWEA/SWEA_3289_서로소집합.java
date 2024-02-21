package SWEA;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class SWEA_3289_서로소집합 {

	 public static void main(String[] args) throws NumberFormatException, IOException {
		/**
		 * SWEA 3289. 서로소집합
		 * ------------------
		 * 
		 * 1 ~ n 각각 n개의 집합
		 * Union연산(0)
		 * Find-Set 연산(1)
		 * 
		 * m : 입력으로 주어지는 연산의 개수
		 * 
		 * 1 <= n <= 1,000,000
		 * 1 <= m <= 1,000,000
		 * 
		 * 각 TC마다 1로 시작하는 입력에 대해 같은 집합이라면 1, 아니면 0
		 * 
		 */
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		// Test Case
		T = Integer.parseInt(br.readLine());
		for(int t=1;t<=T;t++ ) {
			StringBuilder sb = new StringBuilder();
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			parents = new int[N+1]; // Disjoint Set
			
			// 문제풀이
			make(); // 각 원소의 집합을 생성하는 연산
			sb.append("#").append(t).append(" ");
			
			// M번 반복하며 입력을 받음
			for(int i=0;i<M;i++) {
				st = new StringTokenizer(br.readLine());
				int n = Integer.parseInt(st.nextToken()); // 연산 종료
				int a = Integer.parseInt(st.nextToken()); // a
				int b = Integer.parseInt(st.nextToken()); // b
				
				if(n == 0) { // union 연산 수행
					union(a, b);
				}
				else { // find 비교 연산 실행
					if(find(a) == find(b)) { // 두 원소가 같은 대표자를 가짐 : 같은 집합
 						sb.append(1);
					}
					else { // 다른 대표자를 가짐 : 다른 집합
						sb.append(0);
					}
				}
			}
			
			// 출력
			sb.append("\n");
			bw.write(sb.toString());
			bw.flush();
		}
		
		bw.close();
		br.close();
	}
	
	static int T;
	static int N;
	static int M;
	static int[] parents;
	
	static void make() {
		for(int i=1;i<=N;i++ ) {
			parents[i] = i; // 각 원소 자체의 집합 생성, 대표자는 자기 자신
		}
	}
	
	static int find(int a) {
		if(parents[a] == a) 
			return a;
		
		return parents[a] = find(parents[a]); // Path Compression
	}
	
	static boolean union(int a, int b) {
		int aRoot = find(a);
		int bRoot = find(b);
		
		if(aRoot == bRoot) // 이미 같은 합집합의 요소 
			return false; 
		
		// Rank를 이용한 최적화를 시도할 수 있음
		
		parents[bRoot] = aRoot; // 합집합 : b의 대표자를 a의 대표자로 변경, Root 밑에 붙히는 것
		return true;
		
	}
}
