package SWEA;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class SWEA_1210_Ladder1 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		/**
		 * SWEA 1210. Ladder1 ------------------
		 * 
		 * 사다리 게임, 아이스크림 사기 어느 사다리를 고르면 X 표시에 도착하는지 구하기
		 * 
		 * 정해진 위치에서 출발, 좌우방향으로 이동 가능하면 방향 전환
		 * 
		 * 사다리 크기 100X100 빈 공간(0), 사다리(1), 도착(2)
		 * 
		 * 지정된 도착점에 대응되는 출발점(x좌표) 반환
		 *
		 */

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		// 10개의 케이스 고정
		for (int t = 1; t <= 10; t++) {
			StringBuilder sb = new StringBuilder();
			
			// 케이스 번호
			T = Integer.parseInt(br.readLine());
			
			// 주어진 사다리 맵 초기화
			for (int i = 0; i < 100; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for (int j = 0; j < 100; j++) {
					ladder[i][j] = Integer.parseInt(st.nextToken());
					
					// 끝점 저장
					if (ladder[i][j] == 2) {
						end = j;
					}
				}
			}

			// 방문했던 좌표 기억
			visited = new boolean[100][100];
			// 탐색 시작점 - X가 있는 좌표에서 시작
			int y = 99;
			int x = end;
			
			// y==0 : 맨 위에 도달할 때까지 탐색
			while (true) {
				// 맨 위에 도착한 지점이 사다리가 있는 장소일때
				if(y == 0) {
					// x좌표를 저장
					ans = x;
					break;
				}
				
				// 3방향 탐색
				for (int d = 0; d < 3; d++) {
					int ny = y + dy[d];
					int nx = x  +dx[d];
					
					// 맵 범위 밖 제한
					if(ny < 0 || ny >= 100 || nx < 0 || nx >= 100) {
						continue;
					}
					
					// 사다리가 있는 장소(1)만 탐색 가능하게 제한
					if(ladder[ny][nx] == 0 ) {
						continue;
					}
					
					// 방문하지 않은 장소라면, 이동
					if(visited[ny][nx] == false) {
						visited[ny][nx] = true;
						y = ny;
						x = nx;
					}
				}
			}

			// 출력
			sb.append("#").append(T).append(" ").append(ans).append("\n");
			bw.write(sb.toString());
			bw.flush();
		}
	}

	static int T;
	static int[][] ladder = new int[100][100];
	static boolean[][] visited;
	static int ans;
	static int end;
	static int[] dy = { -1, 0, 0 };
	static int[] dx = { 0, -1, 1 };

}
