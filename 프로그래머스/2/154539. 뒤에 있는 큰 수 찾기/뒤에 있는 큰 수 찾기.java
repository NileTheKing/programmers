import java.util.*;
class Solution {
    public int[] solution(int[] numbers) {
        Stack<Integer> mono = new Stack<>();//정수의 인덱스를 담음

        int[] ans = new int[numbers.length];
        Arrays.fill(ans, -1);
        

        int idx = 0;
        for (int i = 0; i < numbers.length; i++) {
            int num = numbers[i];
            //System.out.printf("checking idx %d, val %d\n", i, numbers[i]);
            while (!mono.isEmpty() && num > numbers[mono.peek()]) {
                ans[mono.pop()] = num;
                //System.out.printf("ans[%d] = %d\n", idx, num);
            }
            mono.push(i);
        }
        //System.out.printf("stack size: %d\n", mono.size());
        return ans;
    }
}