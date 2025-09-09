import java.util.*;
class Solution {
    public int solution(int[] elements) {
        Set<Integer> set = new HashSet<>();
        int n = elements.length;
        
        for (int i = 1; i <= n; i++) {
            //i는 갯수
            //이제 sliding window인데 원순열로
            //System.out.printf("counting sequence of %d\n", i);
            int sum = 0;
            for (int j = 0; j < i; j++) {
                sum += elements[j];
            }
            set.add(sum);
            //System.out.printf("adding %d \n", sum);
            for (int l = 1; l < n; l++) {//0번인덱스로 시작하는 합부터 n-1번 인덱스로 시작하는 합을 모두 구한다
                int r = l + i - 1 > n - 1 ? l + i - 1 - n : l + i - 1;
                //System.out.printf("current left:%d, right:%d, substracting %d, adding %d%n", l, r, l-1, r);
                //슬라이딩 윈도우 시작.
                sum -= elements[l-1];
                sum += elements[r];
                //if(!set.contains(sum)) System.out.printf("adding %d \n", sum);
                set.add(sum);
            }
        }
        return set.size();
    }
}