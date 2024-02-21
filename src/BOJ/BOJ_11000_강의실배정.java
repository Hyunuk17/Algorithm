package BOJ;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ_11000_강의실배정 {

	public static void main(String[] args) throws IOException {
		/**
		 * BOJ 11000. 강의실 배정
		 * 
		 * ------------------
		 * 
		 * S_i에 시작해서 T_i에 끝나는 N개의 수업
		 * 
		 * 최소의 강의실을 사용해서 모든 수업을 가능하게 해야 한다
		 * 
		 * 수업이 끝난 직후에 다음 수업 시작 가능
		 * 
		 * 1 <= N <= 200,000
		 * 
		 * 0 <= S_i, T_i <= 10^9 := Integer값 범위
		 * 
		 * 최소 강의실의 개수를 출력
		 */

		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();

		N = Integer.parseInt(br.readLine());
		arr = new int[N][2];

		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			arr[i][0] = Integer.parseInt(st.nextToken());
			arr[i][1] = Integer.parseInt(st.nextToken());
		}

		// 문제풀이
		// 정렬
		Arrays.sort(arr, new Comparator<int[]>() { // 시작시간 순 정렬: 우선순위큐에 저장된 종료시간과 비교하기 위해

			@Override
			public int compare(int[] o1, int[] o2) {
				if (o1[0] == o2[0]) { // 시작시간이 같으면
					return o1[1] - o2[1]; // 종료시간 정렬
				}
				return o1[0] - o2[0]; // 시작시간 정렬
			}
		});

		pq = new PriorityQueue<>(); // 우선순위 큐
		pq.add(arr[0][1]); // 처음의 종료시간 offer

		//
		for (int i = 1; i < N; i++) {
			if (pq.peek() <= arr[i][0]) { // 현재 가장 빠른 종료시간보다 시작시간이 늦으면
				pq.poll(); // 현재 종료시간 갱신
			}

			pq.add(arr[i][1]); // 새 종료시간 offer
		}

		min = pq.size(); // 마지막까지 남은 종료시간의 수 == 강의실의 수

		// 출력
		sb.append(min);
		bw.write(sb.toString());
		bw.flush();

		bw.close();
		br.close();
	}

	static int N;
	static int[][] arr;
	static int min = 0;
	static PriorityQueue<Integer> pq;
}
