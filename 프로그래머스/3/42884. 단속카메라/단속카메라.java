import java.util.*;
class Solution {
    public int solution(int[][] routes) {

        //routes를 끝난 점 기준으로 정렬
        Arrays.sort(routes, (a, b) -> Integer.compare(a[1], b[1]));
        
        int cameraPos = routes[0][1];
        int cnt = 1;
        for (int i = 1; i < routes.length; i++) {
            if (routes[i][0] <= cameraPos) {
            }
            else {
                cnt++;
                cameraPos = routes[i][1];
            }
        }
        return cnt;
    }
}
/**
진입시점, 나간 시점
이거를 수직선 상에 표시하면 풍선 뚫기 그 문제 생각나는데 리트코드. intervals
최소한의 총알로 다 터뜨리는 그 문제. 
정렬해가지고 하면 될듯?
정렬기준은.. 일단 생각해보고 풀이를 봐보자.
정렬해서
*/