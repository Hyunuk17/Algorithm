package BOJ;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class BOJ_17281_야구 {

	public static void main(String[] args) throws IOException {
		/**
		 * BOJ 17281. 야구
		 * --------------
		 * 
		 * 총 9명, 하나의 이닝에 공격과 수비 번갈아서 한다
		 * N회 이닝 진행, 3아웃 이닝 종료 공수 체인지
		 * 
		 * 타순 정하기, 이닝이 바뀌어도 현재 진행되고 있는 순서는 유지된다
		 * 타자 : 1루타(1), 2루타(2), 3루타(3), 홈런(4), 아웃(0)
		 * 모든 타자와 주자가 같이 전진한다
		 * 
		 * 희생플레이, 도루 없음
		 * 
		 * 1번 선수가 4번 타자,
		 * 나머지 타자 순서 정하기
		 * 
		 * 결과는 정해져 있음, 가장 많이 득점하는 타순찾기
		 * 그 타순의 득점 구하기
		 * 
		 * 이닝 총합 : 2 <= N <= 50
		 * 
		 * 각 이닝에는 아웃당하는 타자가 적어도 1명 존재 == 적어도 9회에 게임이 끝난다
		 *   
		 */

		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		
		N = Integer.parseInt(br.readLine());
		arr = new int[N+1][10]; //
		
		for(int i=1;i<=N;i++ ) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int j=1;j<=9;j++) {
				// 1번 타자 따로 저장?
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		player = new int[10]; // 타순
		selected = new boolean[10];
		
		// 문제풀이
		player[4] = 1; // 1번타자는 4번
		selected[4] = true; // 4번이 이미 선택됨
		
		NP(2); // 타자의 타순 정하기
				
		// 출력
		sb.append(max);
		bw.write(sb.toString());
		bw.flush();
		
		bw.close();
		br.close();
	}
	
	static int N;
	static int[][] arr;
	static int[] player;
	static boolean[] selected;
	static int max = 0;
	static int now;
	
	static void NP(int num) {
		if(num == 10) { // 9명의 위치를 전부 정했으면 return;
			play();
			return;
		}
		
		for(int i=1;i<=9;i++) { // 타자 선택
			if(selected[i] == true) { // 타자의 타순이 이미 정해져있다면 Pass
				continue;
			}
			
			selected[i] = true; // 타자 선택
			player[i] = num; // 해당 타자의 타순
			NP(num+1); // 다음 타순
			selected[i] = false;
		}
	}
	
	static void play() {
		int score = 0;
		int startPlayer = 1; // 이닝에서 처음 시작하는 타자, 1번부터
		boolean[] base;
		
		for(int i=1;i<=N;i++) { // N이닝 반복
			int outCnt = 0;
			base = new boolean[4]; // Base 초기화
			
			outer : while(true) {
				for(int j=startPlayer;j<=9;j++) { // 타순
					int hitter = arr[i][player[j]]; // 현재 타자의 행동
					
					switch(hitter) {
					case 0: // out
						outCnt++;
						break;
					case 1: // 1루타
						for(int k=3;k>=1;k--) {
							if(base[k] == true) { 
								if(k == 3) { // 3루 -> 홈
									score++; // 특점
									base[k] = false; // 3루 초기화
									continue;
								}
								
								base[k] = false;
								base[k+1] = true;
							}
						}
						base[1] = true; // 1루 진출
						break;
					case 2 : // 2루타
						for(int k=3;k>=1;k--) {
							if(base[k] == true) {
								if(k == 3 || k == 2) { // 3루 or 2루 -> 홈
									score++;
									base[k] = false;
									continue;
								}
								
								base[k] = false;
								base[k+2] = true; // 1루 -> 3루
							}
						}
						base[2] = true; // 2루 진출
						break;
					case 3:  // 3루타
						for(int k=3;k>=1;k--) {
							if(base[k] == true) { // 1루, 2루, 3루 -> 홈
								score++;
								base[k] = false;
							}
						}
						
						base[3] = true; // 3루 진출
						break;
					case 4: // 홈런
						for(int k=1;k<=3;k++) {
							if(base[k] == true) { // 1루 
								score++;
								base[k] = false;
							}
						}
						score++;
						break;
					}
					
					if(outCnt == 3) { // 3아웃
						startPlayer = j + 1; // 다음 타순에서 시작
						if(startPlayer == 10) {
							startPlayer = 1;
						}
						break outer;
					}
				}
				
				startPlayer = 1;
			}
		}
		
		max = Math.max(max, score);
	} 
}
