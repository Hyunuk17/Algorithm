package SWEA;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class SWEA_6808_규영이와인영이의카드게임 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		/**
		 * SWEA 6808. 규영이와 인영이의 카드게임
		 * ----------------------------
		 * 
		 * 1~18까지 수가 적힌 카드
		 * 
		 * 9장씩 나누어 9라운드 진행
		 * 한 장씩 카드를 낸 다음, 적힌 수를 비교해서 점수 계산
		 * 높은 수 : 두 카드에 적힌 수의 합
		 * 낮은 수 : 점수 없음
		 * 
		 * 총점이 더 높은 사람이 승자, 무승부 있음
		 * 
		 * 규영이 카드 순서 입력받음, 인영이의 카드 순서에 따라 승패 달라짐(9!) 
		 * 규영이가 이기는 경우와 지는 경우가 총 몇 가지 인지 구하기
		 */

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		T= Integer.parseInt(br.readLine());
		
		for(int t=1;t<=T;t++) {
			// 입력 및 초기화
			StringBuilder sb  =new StringBuilder();
			win = 0;
			lose = 0;
			
			// 규영이의 카드
			arr = new int[9];
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int i=0;i<9;i++) {
				arr[i] = Integer.parseInt(st.nextToken());
			}
			
			// 인영이의 카드
			arr2 = new int[9];
			int idx = 0;
			for(int i=1;i<=18;i++) { // 규영이가 가지지 않은 카드만 가짐
				boolean flag = false;
				for(int n : arr) {
					if(n == i) {
						flag = true;
					}
				}
				
				if(flag == false) {
					arr2[idx++] = i;
				}
			}
			
			
			visited = new boolean[9]; // 순열을 위한 visited
			result = new int[9]; // 순열을 담을 배열
			
			// 문제풀이
			re(0); // 완전탐색
			
			// 출력
			sb.append("#").append(t).append(" ").append(win).append(" ").append(lose).append("\n");
			bw.write(sb.toString());
			bw.flush();
		}
		
		bw.close();
		br.close();
	}

	static int T;
	static int[] arr;
	static int[] arr2;
	static boolean[] visited;
	static int[] result;
	static int win;
	static int lose;
	
	static void re(int depth) {
		if(depth == 9) { // 순열이 완성되면
			check(); // 승패 검사
			return;
		}
		
		for(int i=0;i<9;i++) {
			if(visited[i] == false) {
				visited[i] = true;
				result[depth] = arr2[i];
				re(depth+1); // 재귀
				visited[i] = false;
			}
		}
	}
	
	// 승패 검사
	static void check() {
		int sum = 0; // 규영이의 총점
		int sum2 = 0; // 인영이의 총점
		
		for(int i=0;i<9;i++ ) { // 9라운드 진행
			if(arr[i] > result[i]) { // 규영이 승
				sum += arr[i] + result[i];
			}
			else { // 인영이 승
				sum2 += arr[i] + result[i];
			}
		}
		
		// 총점 비교
		if(sum > sum2) { // 규영이 승
			win++;
		}
		else if(sum < sum2) { // 인영이 승
			lose++;
		}
	}
}
