package BOJ;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class BOJ_3109_빵집 {

	public static void main(String[] args) throws IOException {
		/**
		 * BOJ 3109. 빵집
		 * -------------
		 * 
		 * 빵집의 지출을 줄이기 위해 가스관에 몰래 파이프를 설치하여 사용
		 * [R][C] : 첫 째 열 - 근처 빵집의 가스관, 마지막 열 - 원웅이의 빵집
		 * 가스관과 빵집을 연결하는 파이프 설치, 사이에 건물이 있을 수 있음-건물에는 설치 불가능
		 * 
		 * 모든 파이프라인은 첫째 열에서 시작, 마지막열에서 종료
		 * 
		 * 오른쪽, 오른쪽 위 대각선, 오른쪽 아래 대각선 이동 가능
		 * 각 칸의 중심끼리 연결
		 * 
		 * 각 칸을 지나는 파이프는 하나
		 * 파이프라인 여러 개 설치 할 것
		 * 
		 * 가스관과 빵집을 연결하는 파이프라인의 최대 개수 구하기
		 * 
		 * 1 <= R <= 10,000
		 * 5 <= C <= 500
		 * 
		 * 완전 탐색 - R * 3^C > 100,000,000 -> 불가능
		 * 백 트래킹 - 시간복잡도가 극단적으로 좋아지지 않음 -> 불가능
		 * DP - Overlapping Subproblems: 동일한 작은 문제 반복 없음 -> 불가능
		 * Greedy?
		 * 
		 * . : 빈칸
		 * x : 건물		  
		 * 
		 */

		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		board = new char[R][C];
		visited = new boolean[R][C];
		
		for(int i=0;i<R;i++) {
			String str = br.readLine();
			for(int j=0;j<C;j++) {
				board[i][j] = str.charAt(j);
			}
		}
		
		// 문제풀이
		// 그리디		
		// 끝 지점부터 시작
		for(int i=0;i<R;i++) { // 첫 번쨰 열에 도착하면 종료
			DFS(i, 0, 0);			
		}
		
	
		// 출력
		bw.write(cnt+"");
		bw.flush();
		bw.close();
		br.close();
	}
	
	static int R;
	static int C;
	static char[][] board;
	static int[] dy = {-1, 0, 1};
	static int[] dx = {1, 1, 1};
	static boolean[][] visited;
	static int cnt = 0;
	
	static boolean DFS(int y, int x, int depth) {
		visited[y][x] = true; // 현재 좌표에 파이프 저장
		if(depth == C-1) { // 끝 열에 도착했다면
			cnt++;
			return true; // 종료
		}
		
		for(int d = 0; d<3;d++) {
				
			// 현재 위치에서 이동 가능한 좌표
			int ny = y + dy[d]; 
			int nx = x + dx[d];
			
			if(ny < 0 || ny >= R || nx < 0 || nx >= C) { // 범위를 벗어난 경우
				continue;
			}
			
			if(board[ny][nx] == 'x') { // 벽을 만난 경우
				continue;
			}
			
			if(visited[ny][nx] == true) { // 다른 파이프가 이미 지나는 경우 
				continue;
			}
			
			// DFS 결과가 True라면 더이상 탐색하지 않음
			if(DFS(ny, nx, depth+1) == true) {				
				return true;
			}
					
		}
		
		// 탐색 가능한 경로가 없는 경우
		return false;
	}
	
}
