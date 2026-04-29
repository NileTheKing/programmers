import java.util.*;
class Solution {
    public int[] solution(int[] numbers) {
        Stack<Integer> stack = new Stack<>();//인덱스
        //stack.push(numbers[0]);
        int[] ans = new int[numbers.length];
        Arrays.fill(ans, -1);//초기값
        for (int i = 0; i < numbers.length; i++) {
            while (!stack.isEmpty() && numbers[stack.peek()] < numbers[i]) {
                //i가 계속 크면 갱신
                int poppedIdx = stack.pop();
                ans[poppedIdx] = numbers[i]; //답은 숫자..idx넣는이유는 ans때문
            }//다 처리되면 이제 stack은 더큰값이 있고 ..제일작은 number[i].
            stack.push(i);
        }
        return ans;
    }
}
/**
단조스택이네.
10^7이므로 O(N)이 맥스임.
*/