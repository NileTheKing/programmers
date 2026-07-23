import java.util.*;
class Solution {
    public int solution(int[] people, int limit) {
        Arrays.sort(people);
        int l = 0;
        int cnt = 0;
        for (int i = people.length - 1; i >= 0; i--) {
            if (l > i) break;//종료
    
            if (l != i && people[l] + people[i] <= limit) 
                l++;
            // System.out.printf("%d\n",i);
            // System.out.printf("i: %d, sum")
            //이제 l은안태웠지만 다음에 대상으로될거고
            cnt++;
        }
        return cnt;
    }
}