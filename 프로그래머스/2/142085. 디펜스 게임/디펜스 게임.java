import java.util.*;
class Solution {
    public int solution(int n, int k, int[] enemy) {
        int l = 0;
        int r = enemy.length;
        int ans = 0;
        while (l <= r) {
            int m = l + (r - l) / 2;
            //top k
            //전체sum
            int[] partialEnemy = Arrays.copyOfRange(enemy, 0, m); //이러면 m-1인덱스까지..그럼m라운드까지 잘림
            Arrays.sort(partialEnemy);
            long sum = 0;
            for (int i = 0; i < partialEnemy.length - k; i++) {
                sum += partialEnemy[i];
            }
            // System.out.printf("m: %d, partialSum: %d, topk: %d, sum: %d\n", m, partialSum, topKSum, sum);
            if (n >= sum) {
                ans = Math.max(m, ans);
                l = m + 1;
            }else {
                r = m - 1;
            }
        }
        return ans;
    }
}
/**
무적권쓰냐 안쓰냐 백트래킹하면 터짐
라운드 10^7 라서 ON아니면 터짐. 그러면 enemy순회가아니다
이분탐색을 생각해보자.
막을수 있냐 없냐여부는. 라운드까지갔다치자. 되려면? 그라운드까지 enemy배열잘라서? 무적권은 무조건 쓰는게 이득이기떄문에..그중에 top k개 자르고.. 나머지애들의 합이 n보다 작으면 된다.
*/