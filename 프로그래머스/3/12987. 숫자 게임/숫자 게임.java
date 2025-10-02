import java.util.*;
class Solution {
    public int solution(int[] A, int[] B) {
        Arrays.sort(A);
        Arrays.sort(B);
        int al = 0;
        int ar =  A.length - 1;
        int bl = 0;
        int br = B.length - 1;
        int cnt = 0;
        for (int i = 0; i < A.length; i++) {
            //B팀의 가장 작은애로 큰애 이길수있는지
            if (B[bl] > A[ar]) {
                bl++;
                ar--;
                cnt++;
            }
            //B팀의 가장 작은애로 A의 가장 작은 애 이길 수 있는ㄴ지
            else if (B[bl] > A[al]) {
                bl++;
                al++;
                cnt++;
            }
            //논개
            else {
                ar--;
                bl++;
            }
        }
        
        return cnt;
    }
}
/**
1 3 5 7
2 2 6 8

3 5 7
2 6 8

5 7
6 8

7
8



*/