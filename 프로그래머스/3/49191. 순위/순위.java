import java.util.*;
class Solution {
    public int solution(int n, int[][] results) {
        int ans = 0; //결과값
        HashMap<Integer, List<Integer>> win = new HashMap<>();
        HashMap<Integer, List<Integer>> lose = new HashMap<>();
        
        //graph
        for (int[] r : results) {
            win.computeIfAbsent(r[0], k -> new ArrayList<>()).add(r[1]);
            lose.computeIfAbsent(r[1], k -> new ArrayList<>()).add(r[0]);
        }
        
        //이긴거 카운트 + 진거 카운트
        //매 선수별로 카운트 가능한가 확인
        for (int i = 1; i <= n; i++) {
            boolean[] wvisited = new boolean[n + 1];
            boolean[] lvisited = new boolean[n + 1];
            int lcount = dfs(lose, wvisited, i);
            int wcount = dfs(win, lvisited, i);
            
            
            if (lcount + wcount == n - 1) ans++;
            
        }
        
        
        return ans;
    }
    public int dfs(HashMap<Integer, List<Integer>> map, boolean[] visited, int current) {
        //종료조건
        int cnt = 0;
        visited[current] = true;
        
        List<Integer> list = map.get(current);
        if (list == null) return 0;
        for (int op : list) {
            if (!visited[op])
                cnt += 1 + dfs(map, visited, op);
        }
        
        return cnt;
    }
}

/**
이긴사람 기준으로 연결시키기
    이긴 그래프
    진 그래프 두개?
    or
    그래프를 방향성으로 승패로?
    
    ->방향성대로 하면 진놈을 얻어서 하니까 불편함 그래서 그냥 따로 ㄱ
    
    
    4, <2,3>
    3, <2>
    1, <2>
    2, <5>
    
    
    2는 5한테 이기고. 1한테지고. 3한테짐.3은 4한테 지니까 4한테도 짐.
    이긴거 카운트 + 진거 카운트해서 자기뺀 선수 수면 확정.
*/