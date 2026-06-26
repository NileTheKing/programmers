import java.util.*;
class Solution {
    public int[] solution(String msg) {
        Map<String, Integer> dict = new HashMap<>();
        //1자리 초기화
        for (int i = 0; i < 26; i++) dict.put(String.valueOf((char)('A' + i)), i + 1);
        int idx = 27;
        // System.out.println(dict);
        List<Integer> ans = new ArrayList<>();
        int ptr = 0;
        int cnt = 0;
        while (ptr < msg.length()) {
            // System.out.printf("===start of ptr : %d=====\n", ptr);
            int tmp = ptr + 1;
            while (tmp <= msg.length() && dict.containsKey(msg.substring(ptr, tmp))) {
                tmp++;
            }

            ans.add(dict.get(msg.substring(ptr, tmp - 1)));
            if (tmp < msg.length()) dict.put(msg.substring(ptr, tmp), idx++);
            
            // System.out.printf("after while\n");
            // System.out.printf("ans added: %s %d\n", msg.substring(ptr, tmp - 1), dict.get(msg.substring(ptr, tmp -1 )));
            // System.out.printf("dict put: %s %d\n", msg.substring(ptr, tmp), idx);
            // System.out.printf("tmp : %d\n", tmp);
            // idx++;
            // cnt++;
            ptr = tmp - 1;
        }
        return ans.stream().mapToInt(i -> i).toArray();
    }
}