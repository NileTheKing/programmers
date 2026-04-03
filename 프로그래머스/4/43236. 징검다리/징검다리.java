import java.util.*;
class Solution {
    public int solution(int distance, int[] rocks, int n) {
        int l = 0;
        int r = distance;
        int ans = 0;
        Arrays.sort(rocks);
        
        while (l <= r) {
            int m = l + (r - l) / 2;
            //바위간 거리가 m을 넘을 수 없다
            
            //실제로 검증... 2개지울수있는데 
            int cnt = 0;
            int start = 0;
            int prev = 0;;
            for(int rock : rocks) { //바위 사이 거리가m보다 작으면 안돼. 지워야해
                if (rock - prev < m) {//m보다 작을수없어 m은최소거리임
                    //rock은없어져야함
                    cnt++;
                }else { //얜 그대로있어야해
                    prev = rock;
                }
            }
            if (distance - prev < m) cnt++;
            //System.out.printf("m = %d, cnt = %d\n", m, cnt);
            if (cnt > n) { // 바위사이 거리중에 제일 작은게 m이려면 너무 많이 지워야함-> m 은 욕심이야
                r = m - 1;
            }else { //거리최소가 m으로 하려했는데 너무 널널해서 뭐 안지워도됨.
                l = m + 1;
                ans = m;
            }
        }
        
        return ans;
    }
}