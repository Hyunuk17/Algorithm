package BOJ;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_17135_캐슬디펜스 {

	public static void main(String[] args) throws IOException {
		/**
		 * BOJ 17135. 캐슬 디펜스 ------------------
		 * 
		 * 성을 향해 몰려오는 적을 잡는 턴 방식의 게임
		 * 
		 * NxM 격자판 각 칸에 포함된 적(1)의 수는 최대 1명 격자판의 N+1번째 행의 모든 칸에는 성이 있다
		 * 
		 * 궁수 3명 배치 , 성이 있는 칸에 배치 가능, 1칸에 최대 1명 각 턴마다 적 하나 공격 가능 거리가 D이하 인 적 중 가장 가까운 적
		 * 공격 적이 여럿이라면 가장 왼쪽 같은 적이 동시에 동격받을 수 있음
		 *
		 * 공격이 끝나면 아래로 한 칸 이동 성이 있는 칸에 도달하면 게임에서 제외
		 * 
		 * 공격으로 제거할 수 있는 적의 최대 수를 계산
		 * 
		 * 격자판의 거리 : |r1-r2| + |c1-c2|
		 * 
		 * 3 <= N, M <= 15 1 <= D <= 10
		 */

		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		D = Integer.parseInt(st.nextToken());
		board = new int[N + 1][M];
		archers = new int[3];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				board[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		// 문제풀이

		// 궁수 배치 및 게임 진행
		re(0, 0);

		// 출력
		bw.write(max + "");
		bw.flush();
		bw.close();
		br.close();
	}

	static int N;
	static int M;
	static int D;
	static int[][] board;
	static int[] archers;
	static int max = Integer.MIN_VALUE;
	static int[] dy = { 0, -1, 0 };
	static int[] dx = { -1, 0, 1 };
	static int cnt;

	// 궁수 배치를 구하고 게임을 진행
	static void re(int depth, int start) {
		if (depth == 3) { // 궁수 배치가 완료되었다면
			int[][] tmpBoard = new int[N + 1][M]; // 원본 배열 저장할 임시 게임판
			for (int i = 0; i < N; i++) { // 배열 복사
				for (int j = 0; j < M; j++) {
					tmpBoard[i][j] = board[i][j];
				}
			}

			cnt = 0; // 처치한 적 수
			for (int i = 0; i < N; i++) { // N라운드 게임 진행
				round(i); // 게임을 진행 , 한칸씩 적이 전진할 것
			}

			max = Math.max(max, cnt); // 최대 처치한 적 수

			board = tmpBoard; // 원본 배열로 복귀
			return;
		}

		// 궁수 배치 구하기, 순열
		for (int i = start; i < M; i++) {
			archers[depth] = i;
			re(depth + 1, i + 1);
		}
	}

	// 게임의 한 라운드
	static void round(int t) {
		int start = N - t;
		boolean[][][] visited = new boolean[start + 1][M][3]; // 방문체크, 궁수 3명 - 3차원
		for (int i = 0; i < 3; i++) { // 궁수
			Queue<Archer> queue = new LinkedList();
			queue.add(new Archer(start, archers[i], start, archers[i], i)); // 현재 궁수 투입
			visited[start][archers[i]][i] = true; 

 			while (!queue.isEmpty()) { //  BFS
				Archer archer = queue.poll(); // 현재 궁수
				int y = archer.aimY; // 궁수가 조준하고 있는 y좌표
				int x = archer.aimX; // 궁수가 조준하고 있는 x좌표

				if (board[y][x] == 1) { // 적이 있다면
					cnt++; // 처치 후 카운트 +1
					board[y][x] = -1; // 이미 처지한 적 표시
					break;
				} else if (board[y][x] == -1) { // 다른 궁수가 노린 적을 동시에 노릴 수 있음
					break; // 카운트를 증가하지 않음
				}

				for (int d = 0; d < 3; d++) {
					int ny = y + dy[d]; // 다음 조준 y좌푠
					int nx = x + dx[d]; // 다음 조준 x좌표

					if (ny < 0 || ny >= start || nx < 0 || nx >= M) { // 범위를 벗어난 경우
						continue;
					}

					if (visited[ny][nx][archer.num] == true) { // 이미 지나간 장소 중복처리
						continue;
					}

					if (distance(ny, nx, archer.y, archer.x) == false) { // 적과의 거리가 D 초과이면 continue
						continue;
					}

					visited[ny][nx][archer.num] = true;
					queue.add(new Archer(archer.y, archer.x, ny, nx, archer.num)); // 다음 위치로 이동
				}
			}

		}
		
		// 다음 라운드 진행을 위해 적을 처치한 장소를 비우기
		for (int i = 0; i < start; i++) {
			for (int j = 0; j < M; j++) {
				if (board[i][j] == -1) {
					board[i][j] = 0;
				}
			}
		}
	}

	// 거리 계산 함수
	static boolean distance(int ny, int nx, int y, int x) {
		int dis = Math.abs(y - ny) + Math.abs(x - nx);
		if (dis <= D) { // 적과의 거리가 D 이하인 경우
			return true;
		}
		
		// 적과의 거리가 D 초과이면
		return false;
	}

	// 궁수
	static class Archer {
		int y; // 궁수의 위치
		int x; 
		int aimY; // 궁수가 조준하는 좌표
		int aimX;
		int num; // 궁수 번호

		public Archer(int y, int x, int aimY, int aimX, int num) {
			this.y = y;
			this.x = x;
			this.aimY = aimY;
			this.aimX = aimX;
			this.num = num;
		}
	}
}
