import java.util.*;
class Solution {
    public long solution(int[] weights) {
        //그냥 n^2으로 긁지
        Arrays.sort(weights);
        long cnt = 0;
        for (int i = 0; i < weights.length; i++) {
            for (int j = i + 1; j < weights.length; j++) {
                int w1 = weights[i];
                int w2 = weights[j];
                if (w1 == w2) { //1:1
                    // System.out.printf("%d %d\n", w1, w2);
                    cnt++;
                }else if(3 * w1 == 2 * w2) { //2:3
                    // System.out.printf("%d %d\n", w1, w2);
                    cnt++;
                }else if(2 * w1 == w2) { //1:2
                    // System.out.printf("%d %d\n", w1, w2);
                    cnt++;
                }else if (4 * w1 == 3 * w2) { //3:4
                    cnt++;
                }
            }
        }
        return cnt;
        
    }
}
/**
사람수가 10^5니까 o^2하면 터지고. 그러면 n nlogn정도 찾아야한다..n은 뭐 사실상 힘들겠고
nlogn이면 정렬일테고
정렬해가지고 1. 1배찾기  2. 1.5배 찾기 3. 2배찾기
이러면 끝이잖아
100 100 180 270 360
근데 이래도 결국 n^2아니냐
여기서 on해야하는데...

100 100 180 270 360
어 음 정렬해가지고 작은거부터 ?

*/