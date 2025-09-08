import java.util.*;
class Solution {
    public int[] solution(int n, String[] words) {
        Set<String> set = new HashSet<>();
        String prev = words[0];
        set.add(prev);
        
        for (int i = 1; i < words.length; i++)  {
            if (words[i].length() <= 1) {
                return new int[] {i % n + 1, i / n + 1};
            }
            char prevLastChar = prev.charAt(prev.length() - 1);
            if (words[i].charAt(0) != prevLastChar) {
                return new int[] {i % n + 1, i / n + 1};
            }
            if (set.contains(words[i])) {
                return new int[] {i % n + 1, i / n + 1};
            }
            prev = words[i];
            set.add(words[i]);
        }
        return new int[] {0,0};
    }
}
/**
result[0]은 겹치는 위치 인덱스/사람수 + 1
    i: 012 -> 1
        345
        678
reuslt[1]은 사람수나눈 나머지
    036 0 
    147   1      
    258 2
*/