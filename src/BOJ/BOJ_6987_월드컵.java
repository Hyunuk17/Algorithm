package BOJ;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ_6987_월드컵 {

	public static void main(String[] args) throws IOException {
		/**
		 * BOJ 6987. 월드컵 
		 * --------------
		 * 
		 * 월드컵 조별 최종 예선, 6개국 리그전 5경기 
		 * 기자가 보낸 결과(승, 무, 패)가 가능한 결과인지 판별
		 * 
		 * 각각의 결과에 대해 가능(1), 불가능(0) 출력
		 * 
		 */

		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		StringBuilder sb = new StringBuilder();

		for (int i = 0; i < R; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < C; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}


		// 각 나라가 붙을 수 있는 경우의 수를 저장한 리스트
		int idx = 0;
		list = new ArrayList<>();
		for (int i = 0; i < 6; i++) {
			for (int j = i + 1; j < 6; j++) {
				list.add(new Node(idx++, i, j));
			}
		}

		// 문제 풀이
		for (int i = 0; i < 4; i++) { // 각 입력 arr[i]에 대해 
			flag = false;
			re(0, i); // 완전탐색-백트래킹을 수행
		}

		
		// 출력
		for (int i : ans) {
			sb.append(i).append(" ");
		}
		bw.write(sb.toString());
		bw.flush();

		bw.close();
		br.close();

	}

	static final int R = 4; // Case
	static final int C = 18; // 내용
	static int[][] arr = new int[R][C];
	static int[] result = new int[C];
	static boolean flag;
	static List<Node> list;
	static int[] ans = new int[4];

	static void re(int depth, int idx) {
		if (flag == true) { // 백트래킹: 이미 찾았다면 더이상 들어가지 않음
			return; 
		}
		if (depth == 15) { // 15번의 승부를 마쳤다면
			check(idx); // 결과를 확인
			return;
		}

		int i = list.get(depth).i; // 나라 i
		int j = list.get(depth).j; // 나라 j

		// (i, j)
		// i승
		result[3 * i]++; // i승
		result[3 * j + 2]++; // j패
		re(depth + 1, idx);
		result[3 * i]--;
		result[3 * j + 2]--;

		// 무
		result[3 * i + 1]++; // i무
		result[3 * j + 1]++; // j무
		re(depth + 1, idx);
		result[3 * i + 1]--;
		result[3 * j + 1]--;

		// i패
		result[3 * i + 2]++; // i패
		result[3 * j]++; // j승
		re(depth + 1, idx);
		result[3 * i + 2]--;
		result[3 * j]--;

	}

	// 일치하는 값이 있는지 확인하는 함수
	static void check(int idx) { 
		for (int i = 0; i < 18; i++) { // arr[idx]와 result를 비교
			if (result[i] != arr[idx][i]) { // 일치 하지 않다면 return
				return;
			}
		}

		// 일치한다면 
		ans[idx] = 1; // 결과를 1로 변환
		flag = true;
	}

	// 나라 i와 나라 j의 승부를 나타냄
	static class Node {
		int idx = 0;
		int i = 0; // 나라 1
		int j = 0; // 나라 2

		public Node(int idx, int i, int j) {
			this.idx = idx;
			this.i = i;
			this.j = j;
		}
	}
}
