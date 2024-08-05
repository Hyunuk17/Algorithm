package BOJ;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ_21940_가운데에서_만나기 {
	/*
	 * BOJ 21940. 가운데에서 만나기 
	 * ---------------------------
	 * 
	 * [문제설몀] 
	 * 어떤 도시 X에서 만나기 
	 * - 각 도로는 일방통행 : A->B, B-> 가중치 다름
	 * 
	 * 왕복시간 
	 * - 자신이 사는 도시 -> 도시 x -> 자신이 사는 도시 
	 * - 왕복 시간이 최소가 되도록
	 * 
	 * [입력] 
	 * 도시의 개수 N 
	 * 도로의 개수 M 
	 * 도시로 가는 이동 시간 배열 T 
	 * 총 인원 K 
	 * 도시의 번호 배열 C
	 *
	 * [출력] 
	 * 도시 X 출력 
	 * - 도시가 여러 개라면 오름차순으로 출력
	 * 
	 * [제한사항] 
	 * 3 <= N <= 200 
	 * 2 <= K <= N 
	 * 1 <= M <= N * (N -1) 
	 * 1 <= C_i <= N 
	 * 1 <= T <= 1,000
	 * 
	 */
	public static void main(String[] args) throws IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();

		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		distance = new int[N + 1][M + 1];

		// 초기화
		for (int i = 1; i <= N; i++) {
			Arrays.fill(distance[i], Integer.MAX_VALUE / 2);
			distance[i][i] = 0;
		}

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int A = Integer.parseInt(st.nextToken());
			int B = Integer.parseInt(st.nextToken());
			int T = Integer.parseInt(st.nextToken());
			distance[A][B] = T;
		}

		K = Integer.parseInt(br.readLine());

		city = new ArrayList<>();
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < K; i++) {
			city.add(Integer.parseInt(st.nextToken()));
		}

		// 문제풀이
		// 한점 X을 기준으로 모든 사람들의 왕복시간의 총 합이 가장 작은 것 찾기
		// Dijkstra는 시작 -> 끝 까지의 최소 거리
		// Floyd-Warshall : 모든 정점에서 다른 모든 정점들에 대한 최단 경로
		// O(N^3) : 200^3

		for (int k = 1; k <= N; k++) {
			for (int i = 1; i <= N; i++) {
				for (int j = 1; j <= N; j++) {
					if (distance[i][j] > distance[i][k] + distance[k][j]) {
						distance[i][j] = distance[i][k] + distance[k][j];
					}
				}
			}
		}

		// 왕복 거리 최댓값 구하기
		maxDistance = new int[N + 1];
		for (int i = 1; i <= N; i++) {
			for (int j = 0; j < K; j++) {
				int target = city.get(j);
				maxDistance[i] = Math.max(maxDistance[i], distance[i][target] + distance[target][i]);
			}
		}

		// 각 왕복 거리가 최소가 되는 도시 X
		result = new ArrayList<>();
		for (int i = 1; i <= N; i++) {
			if (min > maxDistance[i]) {
				min = maxDistance[i];
				result.clear();
				result.add(i);
			} else if (min == maxDistance[i]) {
				result.add(i);
			}
		}

		for (int i = 0; i < result.size(); i++) {
			sb.append(result.get(i)).append(" ");
		}

		// 출력
		bw.write(sb.toString());
		bw.flush();

		bw.close();
		br.close();
	}

	static int N;
	static int M;
	static int[][] distance;
	static int K;
	static List<Integer> city;
	static int[] maxDistance;
	static int min = Integer.MAX_VALUE;
	static List<Integer> result;
}
