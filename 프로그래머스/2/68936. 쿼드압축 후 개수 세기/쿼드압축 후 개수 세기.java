import java.util.*;

class Solution {
    /*
     * Programmers. 쿼드압축 후 개수 세기
     * -------------------------------
     * 
     * [문제 설명]
     * 2차원 정수 배열 arr
     * - 0 or 1
     * - 2^n * 2^n
     *
     * 쿼드 트리와 같은 방식으로 압축
     * - S: 압축하고자 하는 영역
     * - S 내부의 모든 수가 같은 값이면, S를 수 하나로 압축
     * - 그렇지 않으면, S를 4개의 정사각형 영으로 쪼개고, 각 영역 압축 시도
     *
     * [출력]
     * 배열에 최종적으로 남는 0과 1의 개수
     *
     * [제한사항]
     * 1 <= arr.length <= 1,024 : 2^n
     * arr[i][j] = 0 or 1
     */
    public int[] solution(int[][] arr) {
        // 입력
        this.arr = arr;
        this.N = arr.length;
        
        // 문제풀이
        // 1~N까지의 4분면(r,c)
        // 1사분면: 0~N/2, 0~N/2
        // 2사분면: 0~N/2, N/2~N
        // 3사분면: N/2~N, 0~N/2
        // 4사분면: N/2~N, N/2~N
        
        // topDown의 형태
        topDown(0, N, 0, N);
        
        
        // 반환
        return new int[] {zeroCount, oneCount};
    }
    
    static int[][] arr;
    static int N;
    static int zeroCount;
    static int oneCount;
    
    static void topDown(int sr, int er, int sc, int ec) {
        
        boolean flag = true;
        for(int i=sr;i<er;i++) {
            for(int j=sc;j<ec;j++) {
                if(arr[i][j] != arr[sr][sc]) {
                    flag = false;
                }
            }
        }
    
        
        if(flag == false) { // false
            topDown(sr, sr+(er-sr)/2, sc, sc+(ec-sc)/2); // 0, 2, 0, 2
            topDown(sr, sr+(er-sr)/2, sc+(ec-sc)/2, ec); // 0, 2, 2, 4
            topDown(sr+(er-sr)/2, er, sc, sc+(ec-sc)/2); // 2, 4, 0, 2
            topDown(sr+(er-sr)/2, er, sc+(ec-sc)/2, ec); // 2, 4, 2, 4
        } 
        else {
            if(arr[sr][sc] == 0) {
                zeroCount++;   
            }
            else {
                oneCount++;    
            }
        }
    }
}