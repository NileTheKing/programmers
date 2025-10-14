import java.util.*;
class Solution {
    public int[] solution(int[] numbers) {
        Stack<Integer> mono = new Stack<>(); //인덱스
        int[] ans = new int[numbers.length];
        Arrays.fill(ans, -1);
        for (int i = 0; i < numbers.length; i++) {
            int n = numbers[i];
            while (!mono.isEmpty() && n > numbers[mono.peek()]) { //현재 보는 애가 처리해줄수ㅡ 있는애 순회
                //답 적어주기
                ans[mono.peek()] = n;
                mono.pop();
            }
            //스택에넣기
            mono.push(i);
        }
        return ans;
    }
}