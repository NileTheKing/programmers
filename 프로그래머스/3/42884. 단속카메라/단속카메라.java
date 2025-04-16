import java.util.*;
class Solution {
    public int solution(int[][] routes) {
        
        int cnt = 0;
        
        Arrays.sort(routes, (a, b) -> a[1]-  b[1]);
        boolean[] visited = new boolean[routes.length];
        for (int i = 0; i < routes.length; i++) {
            if (visited[i]) continue;
            
            visited[i] = true;
            for (int j = i + 1; j < routes.length; j++) {
                if (routes[i][1] >= routes[j][0]) {
                    visited[j] = true;
                }
            }
            cnt++;
        }
        return cnt;
    }
}
/**
일단 생각해보면
끝지점에 추가하는 것이 이득이지 하나의 자동차를 위해 카메라를 하나는 설치해야 하는데(왜냐하면 앞에 하면 뒤에 오는 애들의 기회가 없음)
그렇다면 끝지점 정렬하고 범위 포함되는ㅇ ㅐ들 있으면 그거만큼 이득


*/