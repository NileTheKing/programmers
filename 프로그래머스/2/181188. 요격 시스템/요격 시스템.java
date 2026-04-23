import java.util.*;
class Solution {
    public int solution(int[][] targets) {
        Arrays.sort(targets, (o1, o2) -> o1[1] - o2[1]);//끝점오름차순
        int ans = 0;
        boolean[] visited = new boolean[targets.length];
        for (int i = 0; i < targets.length; i++) {
            if (visited[i]) continue;
            int cnt = 1; //써야함.
            visited[i] = true;
            //같이 솔수있는 애를 구한다..나머지 다 순회하면서(본인뺴고) 시작점
            for (int j = i + 1; j < targets.length; j++) {
                if (targets[j][0] < targets[i][1]) visited[j] = true;
            }
            ans += cnt;
        }
        return ans;
    }
}
/**
끝점에서는 불가능
정렬해서 하면되지
앞점정렬 or 끝점정렬
끝점정렬을 한다치셈 그러면 
어차피 해당 끝점-1에서 한발은 쏴야함.
문제푼 사람은 시작점 정렬한듯?

일단 모두 터트려야해 그래서 발당하나는 해야한다.
근데 그게 여러개 커버되면 좋다...ㄱ
끝점으로 오른ㅁ차순정렬하면 
*/