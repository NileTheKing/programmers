import java.util.*;
class Solution {
    public int solution(int n, int k, int[] enemy) {
        int l = 0;
        int r = enemy.length;
        int ans = 0;
        while (l <= r) {
            int m = (l + r) / 2;
            //m라운드까지 갔다치자. 그러면 m라운드 치르고.. sum
            //그중에 top 3 빼면 성공
            //성공이란
            //예를 들어  4 2 4 5 3 3 1에서
            //답 5라운드까지가면 18인데 k가 3이라 13뺴주면 5임..그러면2가남아서 ㅇㅋ
            //m까지 자르고 정렬하고 sum구하고 top3
            // System.out.printf("===== m = %d =====\n", m);
            int[] cut = Arrays.copyOf(enemy, m);
            Arrays.sort(cut);
            long sum = 0;//m = 5... length 5.. k 3이면 0 1 2 3 4에서 2까지만
            //length 5 k 3이면 0 1 2까지해야함 그니까
            for (int i = 0; i < cut.length - k; i++) {
                // System.out.printf("%d, adding %d\n", i, cut[i]);
                sum += cut[i];
            }
            // System.out.printf("sum = %d\n", sum);
            //sum이 n이하이면 m라운드가능.
            if (sum <= n) {
                ans = m;
                l = m + 1;
            }else {
                r = m - 1;
            }
        }
        return ans;
    }
}