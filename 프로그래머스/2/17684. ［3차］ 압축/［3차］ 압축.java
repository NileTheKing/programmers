import java.util.*;
class Solution {
    public int[] solution(String msg) {
        List<Integer> ans = new ArrayList<>();
        Map<String, Integer> map =  new HashMap<>();
        int idx = 0; //idx: idx까지 처리완
        
        for (int i = 0; i < 26; i++) {
            map.put(String.valueOf((char)('A' + i)), i + 1);
            //System.out.printf("%c added\n", 'A' + i);
        }
        //System.out.printf("test: %s\n", map.get("A"));
        int dictIdx = 27;
        while (idx < msg.length()) {
            //안될떄까지 옮겨놓기
            int end = idx + 1;
            String w = new String();
            while (end <= msg.length() && map.containsKey(msg.substring(idx, end))) {
                end++;
            }
            //옮겨놨으니까.. 될때까지 했던 번호를 출력..
            ans.add(map.get(msg.substring(idx, end - 1)));
            //그리고 다음거 등록
            if (end <= msg.length())
                map.put(msg.substring(idx, end), dictIdx++);
            
            idx = end - 1;
        }
        return ans.stream().
                mapToInt(i->i).toArray();
    }
}