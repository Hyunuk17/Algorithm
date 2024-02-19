package SWEA;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class SWEA_5644_무선충전 {

	public static void main(String[] args) throws IOException {
		/**
		 * SWEA 5644. 무선 충전
		 * -----------------
		 * 
		 * 스마트폰을 무선 충전할 때, 최적의 BC(Battery Charger)를 선택하는 알고리즘
		 * 
		 * 지도 map : 10x10
		 * 위치 (X, Y)
		 * 충전 범위 C
		 * 성능 P
		 * 두 지점 사이의 거리 D : |Xa - Xb| + |Ya - Yb|
		 * 시간 T(Second)
		 * 총 이동시간 M
		 * BC의 개수 A
		 * 
		 * 같은 BC에 여러 사용자가 동시 접속한 경우, 수 만큼 균등하게 분배
		 * 
		 * 모든 사용자가 충전한 양의 최댓값 구하기
		 * 
		 * 사용자 A(1,1), 사용자 B(10,10)
		 * 이동하지 않음(0), 상(1), 우(2), 하(3), 좌(4)
		 * 
		 * 20 <= M <= 100
		 * 1 <= A <= 8
		 * 1 <= C <= 4
		 * 10 <= P <= 500, 짝수
		 * 
		 * 같은 위치에 BC가 설치될 수 없음
		 * 사용자 A,B가 동시에 같은 위치로 이동할 수 있음
		 */
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		// Test Case
		T = Integer.parseInt(br.readLine());
		for(int t=1;t<=T;t++) {
			// 입력
			StringBuilder sb = new StringBuilder();
			StringTokenizer st  = new StringTokenizer(br.readLine());
			M = Integer.parseInt(st.nextToken()); // 총 이동시간
			A = Integer.parseInt(st.nextToken()); // BC의 개수
			
			users = new User[2];
			users[0] = new User(1, 1); // UserA
			users[1] = new User(10, 10); // UserB
			max = 0;
			
			// 사용자 A의 이동 정보
			userA = new int[M]; 
			st = new StringTokenizer(br.readLine());
			for(int i=0;i<M;i++) {
				userA[i] = Integer.parseInt(st.nextToken());
			}

			// 사용자 B의 이동 정보
			userB = new int[M];
			st = new StringTokenizer(br.readLine());
			for(int i=0;i<M;i++) {
				userB[i] = Integer.parseInt(st.nextToken());
			}

			// BC의 정보
			BCs = new BC[A];
			for(int i=0;i<A;i++) {
				st = new StringTokenizer(br.readLine());
				int y = Integer.parseInt(st.nextToken()); // y좌표
				int x = Integer.parseInt(st.nextToken()); // x좌표
				int c = Integer.parseInt(st.nextToken()); // 충전 범위
				int p = Integer.parseInt(st.nextToken()); // 성능 
				BCs[i] = new BC(x, y, c, p, false);
			}
			
			// 문제풀이
			solve();
			
			// 출력
			sb.append("#").append(t).append(" ").append(max).append("\n");
			bw.write(sb.toString());
			bw.flush();
		}
		
		bw.close();
		br.close();
	}

	static int T;
	static int M;
	static int A;
	static int[] userA;
	static int[] userB;
	static User[] users;
	static int[] dy = {0, -1, 0, 1, 0}; // X, Up, Right, Down, Left
	static int[] dx = {0, 0, 1, 0, -1};
	static BC[] BCs;
	static int max;
	static int result;
	
	static void solve() {
		// 초기 위치 충전?
		max += count();
		for(int i=0;i<M;i++) { // 1초마다 주어진 행동 수행 
			users[0].y += dy[userA[i]]; // userA 좌표 이동
			users[0].x += dx[userA[i]];
			
			users[1].y += dy[userB[i]]; // userB 좌표 이동
			users[1].x += dx[userB[i]];
		
			max += count();
		}
		
	}
	
	static int count() {
		result = 0;
		DFS(0, 0);
		return result;
	}
	
	static void DFS(int user, int sum) {
		if(user == 2) {
			result = Math.max(result, sum);
			return;
		}
		
		for(int i=0;i<A;i++) { 
			// 범위 안에 있고
			// 사용자 i가 범위 안에 없으면
			if(check(user, i) && BCs[i].used == false) { // i번 BC를 사용하지 않은 경우  
				BCs[i].used = true; // 사용
				DFS(user+1, sum + BCs[i].p); // 다음 사용자의 경우를 확인
				BCs[i].used = false;
			}
			
			// 두 사용자가 같은 BC에 접근하는 경우는 used로 필터링된다
			// 3개 이상의 BC의 범위가 중첩된 경우의 최대값 선택은  DFS에 의해 최대값으로 선택된다
		}
		
		DFS(user+1, sum); // 다른 유저가 BC의 범위에 없는 경우
			
	}
	
	static boolean check(int user, int bc) {
		int distance = Math.abs(users[user].y - BCs[bc].y) + Math.abs(users[user].x - BCs[bc].x);
		if(distance <= BCs[bc].c) {
			return true;
		}

		return false;
	}
	
	static class BC {
		int y;
		int x;
		int c;
		int p;
		boolean used;
		
		public BC(int y, int x, int c, int p, boolean used) {
			this.y = y;
			this.x = x;
			this.c = c;
			this.p = p;
			this.used = used;
		}
	}
	
	static class User {
		int y;
		int x;
		
		public User(int y, int x) {
			this.y = y;
			this.x = x;
		}
	}
}
