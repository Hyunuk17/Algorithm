package BOJ;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.*;

public class BOJ_16434_드래곤_앤_던전 {
    /*
     * BOJ 16434. 드래곤 앤 던전
     * ----------------------
     * 
     * [문제설몀]
     * 용사가 공주를 구하기 위해 던전으로 향함
     * - N번 방에 있는 용을 쓰러트리기 위한 최소의 H_MaxHP 구하기
     * 
     * H_MaxHP
     * - 용사의 최대 생명력
     * - 1이상
     * - 던전에 들어간 이후 변하지 않음
     * 
     * H_CurHP
     * - 현재 용사의 생명력
     * - 던전에 들어가기 전 용사의 최대 생명력 H_MaxHP와 같음
     * - H_MaxHP보다 커질 수 없음
     * 
     * H_ATK
     * - 용사의 공격력
     * 
     * 던전 
     * - N개의 방
     * - i번째 방을 통해서만 i+1번째 방으로 이동 가능
     * - 방에 몬스터가 있다면 쓰려트려야지 다음방으로 이동 가능
     * - N번쨰 방에는 공주와 용이 있음
     * - 용을 무찌르면 공주를 구할 수 있음
     * 
     * 전투
     * - 몬스터가 있는 방에 올 경우
     * - 1. 용사의 공격력 H_ATK만큼 몬스터의 생명력 깎음
     * - 2. 몬스터의 생명령이 0이하라면 전투 종료, 용사는 다음방 이동
     * - 3. 몬스터의 공격력만큼 용사의 생명령 H_CurHP를 깎음
     * - 4. 용사의 생명령이 0 이하이면 전투 종료, 용사 사망
     * 
     * 포션
     * - 포션이 있는 방에 올 경우
     * - 현재 용사의 생명력 H_CurHP가 일정량 회복
     * - 공력력 H_ATK 일정량 증가
     * - 생명력은 최대 생명력 H_MaxHP 이상이 될 수 없음
     * 
     * 수련
     * - 수련을 통해 용사의 최대 생명력 H_MaxHP를 증가
     * 
     * 
     * [입력]
     * N : 방 개수
     * H_ATK : 공격력
     * t : 방 내용
     * a : 공격력 증가분
     * h : 회복량
     * 
 	 *  
     * [출력]
     * N번째 방의 용을 쓰러트리기 위한 최소의 H_MaxHP 출력
     * 
     * [제한사항]
     * 1 <= N <= 123,456
     * 1 <= H_ATK <= 1,000,000
     * 
     * t_i : 1(몬스터) or 2(포션)
     * 1 <= a_i, h_i <= 1,000,000
     */
    public static void main(String[] args) throws IOException {
        // 입력
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        atk = Integer.parseInt(st.nextToken());
        
        t = new int[N];
        a = new int[N];
        h = new int[N];
        
        long left = 1;
        long right = 0;
        
        for(int i=0;i<N;i++) {
        	st = new StringTokenizer(br.readLine());
        	t[i] = Integer.parseInt(st.nextToken());
        	a[i] = Integer.parseInt(st.nextToken());
        	h[i] = Integer.parseInt(st.nextToken());
        	
        	if(t[i] == 1) { // 최대 HP 필요량 
        		right += (long)a[i] * h[i];
        	}
        }
        
        // 문제풀이
        // 최솟값 찾기 문제
        // O(N) * O(N)으로 안되나? : 123,456 * (1,000,000/1) => TLE
        // 더 빨리 O(N) * O(logN)? : 이분 탐색 
        // mid 값을 최소 HP로 
        
        while(left <= right) {
        	long mid = (left + right) / 2;
        	long curHP = mid;
        	long curAtk = atk;
        	boolean isSuccess = true;
        	
        	for(int i=0;i<N;i++) {
        		if(t[i] == 1) { // 몬스터
        			if(h[i] % curAtk == 0) { // 몬스터 공격력 
        				curHP -= a[i] * ((h[i] / curAtk) -1);
        			}
        			else {
        				curHP -= a[i] * (h[i] / curAtk);
        			}
        			
        			if(curHP <= 0) {
        				isSuccess = false;
        				break;
        			}
        		}
        		else if(t[i] == 2) { // 포션
        			curAtk += a[i];
        			curHP += h[i];
        			
        			if(curHP > mid) { // 과회복
        				curHP = mid;
        			}
        		}	
        	}
        	
        	if(isSuccess == true) {
        		right =  mid - 1;
        	}
        	else {
        		left = mid + 1;
        	}
        }
        
        sb.append(left); 
        
        // 출력
        bw.write(sb.toString());
        bw.flush();

        bw.close();
        br.close();
    }
 
    static int N;
    static int atk;
    static int[] t;
    static int[] a;
    static int[] h;
 }
