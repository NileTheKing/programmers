import java.util.*;
class Solution {
    public int solution(int[] topping) {
        Map<Integer,Integer> right = new HashMap<>();
        for (int top : topping) {
            right.put(top, right.getOrDefault(top, 0) + 1);
        }
        
        Map<Integer,Integer> left = new HashMap<>();
        int cnt = 0;
        for (int i = 0; i < topping.length; i++) {
            //System.out.printf("at %d, \n", i);
            left.put(topping[i], left.getOrDefault(topping[i], 0) + 1);
            if (right.containsKey(topping[i])) {
                if (right.get(topping[i]) == 1) {
                    right.remove(topping[i]);
                }else {
                    right.put(topping[i], right.get(topping[i]) - 1);
                }
            }
            //System.out.printf("size: %d %d\n", left.size(), right.size());
            if (left.size() == right.size()) {
                cnt++;
            }
        }
        return cnt;
    }
}
/**
해시맵 필요
일단 다넣어
그리고 for문 돌면서 인덱스까지 left가 가지는거고
right는 ㄷ ㅏ넣은 해시맵에서 뺴.
*/