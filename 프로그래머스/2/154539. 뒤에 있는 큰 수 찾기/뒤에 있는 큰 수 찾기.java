import java.util.*;
class Solution {
    public int[] solution(int[] numbers) {
        int[] ans = new int[numbers.length];
        Arrays.fill(ans, -1);
        Stack<Integer> mono = new Stack<>();//인덱스정보 담은 모노스택
        mono.add(0);
        int i= 1;
        while (!mono.isEmpty() && i < numbers.length) {

            while (!mono.isEmpty() && numbers[mono.peek()] <  numbers[i]) {
                //System.out.printf("debug\n");
                int poppedIdx = mono.pop();
                ans[poppedIdx] = numbers[i];// 꺼낸거는 다 해결해주기
            }
            mono.push(i);
            i++;
            
        }
        return ans;
        
    }
}
/**


*/