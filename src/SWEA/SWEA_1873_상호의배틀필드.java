package SWEA;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class SWEA_1873_상호의배틀필드 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		/**
		 * SWEA 1873. 상호의 배틀필드
		 * ---------------------
		 * 
		 * 전차로 시가전을 하는 게임 배틀 필드
		 * 
		 * 맵의 구성 요소
		 * . 평지(전차가 들어갈 수 있다)
		 * * 벽돌로 만들어진 벽
		 * # 강철로 만들어진 벽
		 * - 물(전차를 들어갈 수 없다)
		 * ^ 위쪽을 바라보는 전차(아래는 평지이다)
		 * v 아래쪽을 바라보는 전차(아래는 평지이다)
		 * < 왼쪽을 바라보는 전차(아래는 평지이다)
		 * > 오른쪽을 바라보는 전차(아래는 평지이다)
		 * 
		 * 문자 동작
		 * U : 방향을 위쪽으로 바꾸고, 한 칸 위의 칸이 평지라면 이동
		 * D : 방향을 아래쪽으로 바꾸고, 아래로 이동
		 * L : 방향을 왼쪽으로 바꾸고, 왼쪽으로 이동
		 * R : 방향을 오른쪽으로 바꾸고, 오른쪽으로 이동
		 * S : 바라보고 있는 방향으로 포탄 발사
		 * 
		 * 포탄은 직진, 벽돌 벽을 부수나, 강철 벽을 부수지 못함
		 * 
		 * 모든 입력을 처리한 후 게임 맵의 상태 구하기
		 * 
		 * 2 <= H(높이), W(너비) <= 20
		 */

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		// Test Case
		T = Integer.parseInt(br.readLine());
		for(int t=1;t<=T;t++) {
			// 입력
			StringBuilder sb = new StringBuilder();
			StringTokenizer st=  new StringTokenizer(br.readLine());
			H = Integer.parseInt(st.nextToken());
			W = Integer.parseInt(st.nextToken());
			board = new char[H][W]; // 게임판 할당
			for(int i=0;i<H;i++) {
				String s = br.readLine();
				for(int j=0;j<W;j++) {
					board[i][j] = s.charAt(j);
					
					// 전차 위치 저장
					if(board[i][j] == '^') {
						node = new Node(i, j, 0); // 전차의 위치와 방향을 같이 저장, 위
					}
					else if(board[i][j] == 'v') {
						node = new Node(i, j, 1); // 아래
					}
					else if(board[i][j] == '<') {
						node = new Node(i, j, 2); // 왼쪽
					}
					else if(board[i][j] == '>') {
						node = new Node(i, j, 3); // 오른쪽
					}
				}
			}
			
			N = Integer.parseInt(br.readLine()); // 문자열 길이
			str = br.readLine(); // 실행할 문자열
			
			for(int i=0;i<N;i++) { // 각 동작을 하나씩 수행
				char c = str.charAt(i);
				switch(c) { // 동작의 종류
				case 'U':
					move(0, '^'); // 이동
					break;
				case 'D' :
					move(1, 'v');
					break;
				case 'L' :
					move(2, '<');
					break;
				case 'R' :
					move(3, '>');
					break;
				case 'S' : // 포탄 발사
					shoot();
					break;
				}
			}
			
			// 출력
			sb.append("#").append(t).append(" ");
			
			for(int i=0;i<H;i++) {
				for(int j=0;j<W;j++) {
					sb.append(board[i][j]);
				}
				sb.append("\n");
			}
			
			bw.write(sb.toString());
			bw.flush();
		}
		
		bw.close();
		br.close();
	}

	static int T;
	static int H;
	static int W;
	static char[][] board;
	static int N;
	static String str;
	static Node node;
	static int[] dy = {-1, 1, 0, 0};
	static int[] dx = {0, 0, -1, 1};

	// 전차가 이동하는 함수
	static void move(int d, char c) {
		node.d = d; // 받은 위치로 전차의 방향 전환
		board[node.y][node.x] = c; // 게임판에서의 전차 방향 전환
		int ny = node.y + dy[node.d]; // 이동할 y좌표
		int nx = node.x + dx[node.d]; // 이동할 x좌표
		
		if(ny < 0 || ny >= H || nx < 0 || nx >= W) { // 게임판을 벗어난 경우
			return;
		}
		
		if(board[ny][nx] == '.') { // 이동할 좌표가 평지인 경우
			board[node.y][node.x] = '.'; // 현재 전차의 위치를 평지로 전환
			node.y = ny; // 전차의 y좌표를 변경
			node.x = nx; // 전차의 x좌표를 변경
			board[ny][nx] = c; // 게임판에서의 평지를 전차의 모습으로 변환
		}
		
	}
	
	// 전차가 포탄을 발사하는 함수
	static void shoot() {
		int y = node.y;
		int x = node.x;
		int d = node.d;
		
		// 포탄 발사
		while(true) {
			int ny = y + dy[d]; // 포탄이 지나갈 y좌표
			int nx = x + dx[d]; // 포탄이 지나갈 x좌표
			
			if(ny < 0 || ny >= H || nx < 0 || nx >= W) { // 범위를 벗어나면 종료
				break;
			}
			
			if(board[ny][nx] == '#') { // 전차가 쏜 포탄이 강철벽에 맞으면
				break; // 아무일도 일어나지 않고 종료
			}
			
			if(board[ny][nx] == '*') { // 전차가 쏜 포탄이 벽돌에 맞으면
				board[ny][nx] = '.'; // 벽 파괴 평지로 전환
				break;
			}		
			
			y = ny; // 아무 특이사항 없으면 다음칸으로 나아가기 위해 최신화
			x = nx;
		}
	}
	
	// 전차의 정보를 저장할 클래스
	static class Node {
		int y; // y좌표
		int x; // x좌표
		int d; // 방향
		
		public Node(int y, int x, int d) {
			this.y = y;
			this.x = x;
			this.d = d;
		}
	}
}
