import java.util.*;
class Solution {
    public int solution(int[] diffs, int[] times, long limit) {
        int l = 1;
        int r = Arrays.stream(diffs).max().getAsInt();
        //System.out.printf("max: %d\n", r);
        int ans = 1;//가능할떄마다 최신화
        while (l <= r) {
            int m = l + (r - l)/ 2; //현재 가능한지 확인하는 레벨
            //System.out.printf("checking target level: %d\n", m);
            long timeTaken = 0;
            for (int i = 0; i < diffs.length; i++) {
                if (m >= diffs[i]) {
                    //System.out.printf("at %d level, %d is too easy so just moving on. ", m, diffs[i]);
                    timeTaken += times[i];
                }else if(diffs[i] > m) {
                    int numOfFails = diffs[i] - m;
                    //System.out.printf("at %d level, %d was too hard so had to repeat %d times.", m, diffs[i], numOfFails);
                    //처음부터 못푼다면도 계산해야함. 이따가 ㄱ
                    if (i == 0) { //수동으로 탐색범위
                        l = m + 1;
                        break;
                    }
                    timeTaken = timeTaken + ((times[i] + times[i -1]) * numOfFails + times[i]);
                    //System.out.printf("at %d, had to spend %d to solve it\n", i, timeTaken)
                }
            }
            //System.out.printf("timetaken for level %d is: %d\n", m, timeTaken);
            if (timeTaken <= limit) { //만족한다면 답에 일단 담고 극한의 이득
                ans = m;
                r = m - 1;
                //System.out.printf("%d was able to finish. checking the left half\n", m);
            }else { //안된거니까 약간 타협해주기
                l = m + 1;
            }
        }
        return ans;
    }
}
/**
parametric search.

*/