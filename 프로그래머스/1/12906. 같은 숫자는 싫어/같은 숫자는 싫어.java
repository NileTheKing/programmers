import java.util.*;

public class Solution {
    public int[] solution(int []arr) {
        
        Stack<Integer> stack = new Stack<>();
        ArrayList<Integer> alist = new ArrayList<>();
        
        stack.push(arr[0]);
        alist.add(arr[0]);
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] != stack.peek()) {
                alist.add(arr[i]);
            }
            stack.push(arr[i]);
        }

        return alist.stream().mapToInt(i -> i).toArray();
    }
}
/**
아이디어1 그냥 for문 돌면서 sb로 만들기
아이디어2 스택이용
*/