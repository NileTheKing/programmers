import java.util.*;
class Solution {
    public int solution(int n, int k, int[] enemy) {
        int l = 0;
        int r = enemy.length;
        int ans = 0;
        while (l <= r) {
            int m = (l + r) / 2;
            int[] cut = Arrays.copyOf(enemy, m); //enemy[m]은 m+1라운드고 cut은 m-1까지니까 m라운드까지는 계산함
            Arrays.sort(cut);
            int length = cut.length;//길이6이면 인덱스5부터..무적권3이면 5 4 3
            //반대로 길이6인덱스5가끝일떄 무족권3개있으면 543안할테니 2까지만
            //2는 length - 1 =>5 에서 k뺀거
            long sum = 0;
            for (int i = 0; i <= length - 1 - k; i++) sum += cut[i];
            
            if (sum <= n) { //가능
                ans = m;
                l = m + 1;
            }else {//불가능
                r = m - 1;
            }
        }
        return ans;
    }
}