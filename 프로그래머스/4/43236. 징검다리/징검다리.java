import java.util.*;
class Solution {
    public int solution(int distance, int[] rocks, int n) {
        Arrays.sort(rocks);
        int l = 0;
        int r = distance;
        int ans = distance;
        while (l <= r) {
            // System.out.printf("=======\n");
            int m = l + (r - l) / 2; //최소거리가 이거야야한다
            // System.out.printf("m = %d\n",m);
            int cnt = 0;//제거한갯수..너무 멀면 밟아야하고 아니면 제거가능
            int prev = 0;
            for (int ro : rocks) {
                // System.out.printf("checking %d  ..", ro);
                if (ro - prev < m) {//너무 가까워.. 제거해야해
                    cnt++;
                    // System.out.printf("%d 제거\n", ro);
                }else {//얜 제거안해도됨
                    // System.out.println();
                    prev = ro;
                }
            }
            if (distance - prev < m) cnt++;
            // System.out.printf("cnt=%d\n", cnt);
            //제거하고나니까 min이 너무커서.. 제거를 많이해야한다 -> min지키기엔 n이빡세다
            if (cnt > n) {
                //너무 빡셌으니까 m줄여야
                r = m - 1;
            }else {//가능..업데이트
                // System.out.printf("updating: %d\n", m);
                ans = m;
                l = m + 1;//더 도전
            }
        }
        return ans;
    }
}