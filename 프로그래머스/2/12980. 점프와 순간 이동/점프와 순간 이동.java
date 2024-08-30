import java.util.*;

public class Solution {
    public int solution(int n) {
        int a = 1;
        int count = 1;
        while(n > 1){
            if(n%2 == 1){
                count++;
            }
            n = n /2;
            
        }
        

        return count;
    }
}
/*
5000
2500
1250
625
312*2 + 1
156
78
39
19*2 +1
9*2 + 1
4*2 + 1
4
2
1
*/