package BOJ;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class BOJ_27881_Gahui_and_ILGAM_lake {
	/*
	 * BOJ 27881. Gahui and ILGAM lake -------------------------------
	 *
	 * [문제설명] 가희는 종종 건국대에 방문한다. 가희가 건국대에 방문할 때면 항상, 그녀는 거대한 일감호수를 보고 놀란다. 그 호수는,
	 * 서경대학교나, 한성대학교나 같은 몇몇 대학보다 크다.
	 *
	 * 그 호수는, 원형으로 4n개의 점을 가지고, 1~4n까지의 번호가 반시계방향으로 부여된다. 각 인접한 점은 양방향으로 연결되어 있다. -
	 * 1 <= a <= 4n, 점 a는 a+1과 연결되어 있다. - 점 1은 점 4n과 연결되어 있다. 점 n, 2n, 3n, 4n은 3개의
	 * 지하철역(건국대, 구의, 세종대)과 연결되어 있다. 일감호수는 너무 크기 때문에, 가장 가까운 역은 점에 따라 다르다. 가희는 어느
	 * 지하철역이 현재 점 위치에서 가장 가까운지 알고 싶다. 가희를 도와주자.
	 *
	 * [입력] n
	 * 
	 * d : 각 점 사이의 거리 - di : i -> i+1 까지의 거리 - d4n : 4n -> 1 까지의 거리
	 *
	 * info : 각 n에서의 지하철역까지의 거리 - n -> 건대, n -> 구의, n -> 세종대
	 *
	 * Q : 질문 수 k : 점 k
	 *
	 * [출력] 점 k에서 가장 가까운 역을 한줄에 하나씩 출력
	 *
	 * [제한사항] 1 <= distances <= 10^5 1 <= n <= 10^5 1 <= Q <= 10^5
	 *
	 */

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();

		// 입력
		int n = Integer.parseInt(br.readLine());

		// 누적 거리 배열 (acc) 초기화
		long[] acc = new long[n * 7 + 1];
		StringTokenizer st = new StringTokenizer(br.readLine());

		for (int i = 2; i <= n * 4; i++) {
			acc[i] = Long.parseLong(st.nextToken());
		}
		acc[1] = Long.parseLong(st.nextToken());
		for (int i = n * 4 + 1; i <= n * 7; i++) {
			acc[i] = acc[i - n * 4];
		}
		for (int i = 1; i <= n * 7; i++) {
			acc[i] += acc[i - 1];
		}

		long L = acc[n * 4];

		// 각 지하철역까지의 거리 정보
		int[] arr = new int[4];
		for (int i = 1; i <= 4; i++) {
			int res = Integer.MAX_VALUE;
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < 3; j++) {
				res = Math.min(res, Integer.parseInt(st.nextToken()));
			}
			arr[i & 3] = res;
		}

		int Q = Integer.parseInt(br.readLine());
		while (Q-- > 0) {
			int v = Integer.parseInt(br.readLine());
			long ans = Long.MAX_VALUE;
			int s = (v + n - 1) / n * n;
			for (int i = 0; i < 4; i++) {
				long d = acc[s] - acc[v];
				ans = Math.min(ans, Math.min(d, L - d) + arr[s / n % 4]);
				s += n;
			}
			sb.append(ans).append('\n');
		}

		// 출력
		bw.write(sb.toString());
		bw.flush();
		bw.close();
		br.close();
	}
}
