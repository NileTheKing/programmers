import java.util.*;
class Solution {
    public int[] solution(String msg) {
        Map<String, Integer> dict = new HashMap<>();
        for (int i = 0; i < 26; i++) {
            char c = (char)('A' + i);
            dict.put(String.valueOf(c), i + 1);
        }
        //msg순회하면서..
        int idx = 0; //msg인덱스
        int dictNextIndex = 27;
        List<Integer> ans = new ArrayList<>();
        while (idx < msg.length()) {
            int tmp = idx + 1; //substring해야하니까 ..1뒤부터.
            while (tmp <= msg.length() && dict.containsKey(msg.substring(idx, tmp))) {
                tmp++;
            } //01하면 0 02했는데2글자 안됨
            //지금 tmp2이니까 안됨.. 01해서 있었으면..0끝낸거고 다음ㅊ차례는1
            int dictIndex = dict.get(msg.substring(idx, tmp - 1));
            ans.add(dictIndex);
            
            //없는단어면 추가.뭘? idx, tmp+1 substring을..ㅇ
            if (tmp < msg.length()) {
                dict.put(msg.substring(idx, tmp), dictNextIndex++);
            }
            idx = tmp - 1; //다음거
        }
        return ans.stream().mapToInt(i -> i).toArray();
        
        // return new int[0];//debug
    }
}