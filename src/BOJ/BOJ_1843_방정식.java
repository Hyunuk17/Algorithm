package BOJ;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class BOJ_1843_방정식 {

	public static void main(String[] args) throws IOException {
		/**
		 * BOJ 1843. 방정식
		 * --------------
		 * 
		 * [문제 설명]
		 * 방정식 X + Y = Z (X <= Y)의 해 개수 구하기
		 * 
		 * 제약조건
		 * - A : 세 자연수 X, Y, Z는 모두 N 이하이며, 서로 다르다
		 * - B : 세 자연수 X, Y, Z는 모두 N의 양의 약수이다
		 * - C : 세 자연수 X, Y, Z는 모두 N 이하의 양의 소수이다
		 * 
		 * 각각의 경우에 따른 해의 개수 구하기
		 * 
		 * [제한사항]
         * 1 <= N <= 500,000
		 */
        
        // 입력
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
    
        N = Integer.parseInt(br.readLine());
        
        // 문제풀이
        long a = calculateA();
        long b = calculateB();
        long c = calculateC();
        
        // 출력
        sb.append(a).append("\n");
        sb.append(b).append("\n");
        sb.append(c).append("\n");
        
        bw.write(sb.toString());
        bw.flush();
	}
    


	static int N;
    
    // 문제 1
    static long calculateA() {
        // X + Y = Z
        // X = Z - Y
        // Z를 N으로 두고
        // X를 1부터 N-1까지 증가시키며 매칭 가능한 Y의 경우의 수 만큼 존재
        long a = 0;
        
        if(N % 2 == 1) {
        	for(int i=1;i<=N/2;i++) {
        		a += N-2*i;
        	}
        }
        else {
        	for(int i=1;i<=N/2-1;i++) {
        		a += N-2*i;
        	}
        }
        
        return a;
    }
    
    static long calculateB() {
    	long b = 0;
		List<Integer> divisors = new ArrayList<>();
		for(int i=1;i*i<=N;i++) { // 
			if(N % i != 0) { // N의 약수가 아니면 continue
				continue;
			}
			
			divisors.add(i);
			
			if(i*i != N) { // 현재 약수의 페어도 같이 add()
				divisors.add(N/i);
			}
		}
		
		Collections.sort(divisors); // 오름차순 정렬
		
		for(int i=0;i<divisors.size()-1;i++) { // 약수 순회
			int x = divisors.get(i);
			for(int j=i;j<divisors.size()-1;j++) { // x보다 큰 y 선택
				int y = divisors.get(j);
				int z = x + y;
				
				// z가 divisors에 있는지 확인
				int idx = i+1;
				while(divisors.get(idx) < z) {
					idx++;
				}
				
				if(divisors.get(idx) == z) {
					b++;
				}
			}
		}
		
		
		return b;
	}

    static long calculateC() {
    	long c = 0;
    	boolean[] num = new boolean[N+1];
    	
    	// x + y = z, x,y,z가 모두 소수
    	// x는 2로 고정
    	// 연속된 소수 2개의 차가 2라면 count
    	Arrays.fill(num, true);
    	num[0] = num[1] = false;
    	
    	for(int i=2;i*i<=N;i++) { // 에라토스테네스의 체
    		if(num[i] == true) { 
    			for(int j=i*i;j<=N;j+=i) { // i*i 부터 시작인 이유 : 2~i-1까지는 앞에서 지웠음
    				num[j] = false;
    			}
    		}
    	}
    	
    	for(int i=0;i<=N-2;i++) {
    		if(num[i] == true && num[i+2] == true) {
    			c++;
    		}
    	}
    	
    	return c;
    }
}
