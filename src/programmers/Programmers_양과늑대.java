package programmers;

import java.util.*;

class Programmers_양과늑대 {
    /**
     * Programmers. KAKAO BLIND RECRUITMENT : 양과 늑대
     * -----------------------------------------------
     * 
     * 2진 트리 각 노드에 늑대와 양 1마리씩 존재
     * Root에서 출발, 양을 모음
     * 모은 양의 수 <= 늑대 : 늑대가 모든 양을 잡아먹음
     * 
     * 양이 잡아먹히지 않게 하면서 최대한 많은 수의 양을 모아 Root로 복귀
     *
     * 2 <= info.length <= 17
     * - 양(0), 늑대(1)
     * - info[0] = 0
     *
     * edges의 세로(행) 길이 = info.length - 1
     * - edges의 가로(열) 길이 = 2
     * - [부모 노드, 자식 노드] : 연결 상태를 표현
     */
    public int solution(int[] info, int[][] edges) {
        // 입력
        // 인접 리스트 생성
        graph = new ArrayList[info.length+1];
        for(int i=0;i<info.length;i++) {
            graph[i] = new ArrayList<>();
        }
        
        // 트리 간선 넣어주기
        for(int[] edge : edges) {
            graph[edge[0]].add(edge[1]);
        }
        
        List<Integer> next = new ArrayList<>();
        next.add(0); // 루트
        
        // 문제풀이
        // 트리 탐색을 어떻게 하는가
        // 다시 루트 노드로 돌아오기 == 의미 없음 
        // - 이미 방문하여 늑대와 양을 데리고 간 노드만 방문하기 때문 : 그대로 돌아오면 됨
        
        // 어떻게 탐색하느냐가 관건
        DFS(info, next, 0, 0, 0);
        
        // 반환
        return answer;
    }
    
    static int answer = 0;
    static List<Integer>[] graph;
   
    public void DFS(int[] info, List<Integer> list, int now, int sheep, int wolf) {
        // 현재 노드 node 양인지 늑대인지
        if(info[now] == 0) 
            sheep++;
        else
            wolf++;
        
        // 늑대가 많으면 중단
        if(sheep <= wolf)
            return;
        
        // 양이 더 많으면 갱신
        answer = Math.max(answer, sheep);
        
        // 다음에 넣어줄 리스트에 현재 리스트를 담음
        List<Integer> next = new ArrayList<>(list); // List 깊은 복사(Deep Copy)
        
        if(!graph[now].isEmpty()) { // 자식 노드가 존재하면
            next.addAll(graph[now]); // 자식 노드를 넣기(Deep Copy?)
        }
        next.remove(Integer.valueOf(now)); // 현재 노드 삭제(값, Obejct) : 방문 완료
        
        // 다음 방문 가능한 노드들 DFS
        for(int node : next) {
            DFS(info, next, node, sheep, wolf);
        }
    }
}