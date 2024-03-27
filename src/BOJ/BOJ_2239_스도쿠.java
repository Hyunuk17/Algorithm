package src.BOJ;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

public class BOJ_2239_스도쿠 {

	public static void main(String[] args) throws IOException {
		/**
		 * BOJ 2239. 스도쿠
		 * --------------
		 * 
		 * 9x9 보드
		 * 각 행, 각 열, 3x3 크기 보드에 1~9 중복 없이 채우기
		 * 
		 * 하다 만 스도쿠 퍼즐 완성하기
		 * 0(채워지지 않은 칸)
		 * 
		 */
		
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		sb = new StringBuilder();
		
		for(int i=0;i<size;i++) {
			String str = br.readLine();
			for(int j=0;j<size;j++) {
				board[i][j] = str.charAt(j) - '0';
//				System.out.print(board[i][j] + " ");
				if(board[i][j] == 0) {
					list.add(new Node(i, j));
				}
			}
//			System.out.println();
		}
		
		// 문제풀이
		// 9x9라 시간복잡도는 괞찮을듯
		re(0);
		
	}

	static int size = 9;
	static int[][] board = new int[size][size];
	static List<Node> list = new ArrayList<>();
	static StringBuilder sb;
	static BufferedWriter bw;
	
	static void re(int depth) throws IOException {
		if(depth == list.size()) {
			for(int i=0;i<size;i++) {
				for(int j=0;j<size;j++) {
					sb.append(board[i][j]);
				}
				sb.append("\n");
			}
			bw.write(sb.toString());
			bw.flush();
			System.exit(0);
		}
		
		Node now = list.get(depth);
		boolean[] check = new boolean[10];
		
		// 가로
		for(int i=0;i<size;i++) {
			if(board[now.r][i] != 0) {
				check[board[now.r][i]] = true;
			}
		}
		
		// 세로
		for(int i=0;i<size;i++) {
			if(board[i][now.c] != 0) {
				check[board[i][now.c]] = true;
			}
		}
		
		// 3x3 사격형
		int sr = now.r/3 *3;
		int sc = now.c/3 *3;
		for(int i=sr;i<sr+3;i++) {
			for(int j=sc;j<sc+3;j++) {
				if(board[i][j] != 0) {
					check[board[i][j]] = true;
				}
			}
		}
		
		//
		for(int i=1;i<=9;i++) {
			if(!check[i]) {
				board[now.r][now.c] = i;
				re(depth+1);
				board[now.r][now.c] = 0;
			}
		}
	}
	
	static class Node {
		int r;
		int c;
		public Node(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}
}
