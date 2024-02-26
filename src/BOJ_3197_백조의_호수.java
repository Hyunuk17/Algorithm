import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_3197_백조의_호수 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		/**
		 * 지구온난화 := BOJ 백조의 호수
		 * -------
		 * 
		 * RxC : 지도 
		 * 일반 땅과 인접한 빙하가 매일 녹는다
		 * 대각선 고려X
		 * 
		 * 두 사람이 몇일마다 만나는지 계산
		 * 땅(.), 빙하(X), 사람(L)
		 * 
		 * 1 <= R, C <= 1,500
		 *
		 */

		// Input
		File file = new File("3번_Input.txt");
		BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
				
		// Test Case
		for(int t=1;t<=3;t++) {
			StringBuilder sb = new StringBuilder();

			// 입력
			br.readLine();
			StringTokenizer st = new StringTokenizer(br.readLine());
			R = Integer.parseInt(st.nextToken());
			C = Integer.parseInt(st.nextToken());
			board = new char[R][C];
			
			for(int i=0;i<R;i++) {
				String str = br.readLine();
				for(int j=0;j<C;j++) {
					board[i][j] = str.charAt(j);
					
					if(board[i][j] == 'L') {
						if(start == null)  {
							start = new Node(i,j,0);
							continue;
						}
						
						end = new Node(i,j,0);
					}
					
				}
			}
						
			// 문제풀이
			BFS();
			
			// 출력
			sb.append("#").append(t).append(" ").append(ans).append("\n");
			bw.write(sb.toString());
			bw.flush();
		}
		bw.close();
		br.close();
	}

	static int R;
	static int C;
	static char[][] board;
	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = {0, 0, -1, 1};
	static Node start;
	static Node end;
	static Queue<Node> queue;
	static boolean[][] visited;
	static int ans;
	
	static void BFS() {
		queue = new ArrayDeque<>();
		visited = new boolean[R][C];
		queue.add(start);
		visited[start.r][start.c] = true;
		
		while(!queue.isEmpty()) {
			// 하루에 몇 칸 전진 가능한지?
			for(int i=0;i<queue.size();i++) {
				
				Node now = queue.poll();
				int r = now.r;
				int c = now.c;
				
				if(start != now && board[r][c] == 'L') {
					ans = now.cnt;
					break;
				}
				
				for(int d=0;d<4;d++) {
					int nr = r + dr[d];
					int nc = c + dc[d];
					
					if(nr < 0 || nr >= R || nc < 0 || nc >= C) {
						continue;
					}
					
					if(visited[nr][nc]) {
						continue;
					}
					
					visited[nr][nc] = true;
					queue.add(new Node(nr,nc, now.cnt+1));
				}	
			}
			
			// 빙하 녹이기
			breakIce();
		}
	}
	
	static void breakIce() {
		Queue<Node> ice = new ArrayDeque<>();
		for(int i=0;i<R;i++) {
			for(int j=0;j<C;j++) {
				int r = i;
				int c = j;
				if(board[r][c] == 'X') { // 빙하일 때
					
					for(int d=0;d<4;d++) {
						int nr = r + dr[d];
						int nc = c + dc[d];
						
						if(nr < 0 || nr >= R || nc < 0 || nc >= C) {
							continue;
						}
						
						if(board[nr][nc] == '.') { // 인접한 땅이 있으면
							ice.add(new Node(r,c ,0));
						}
					}
				}
			}
		}
		
		while(!ice.isEmpty()) {
			Node now = ice.poll();
			
			int r = now.r;
			int c = now.c;
			
			board[r][c] = '.';
		}
	}
	
	
	static class Node {
		int r;
		int c;
		int cnt;
		
		public Node(int r, int c, int cnt) {
			this.r = r;
			this.c = c;
			this.cnt = cnt;
		}
	}
}
