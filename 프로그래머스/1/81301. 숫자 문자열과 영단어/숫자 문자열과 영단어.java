import java.util.*;
class Solution {
    public int solution(String s) {
        Map<String, String> map = new HashMap<>();
        map.put("zero","0");
        map.put("one", "1");
        map.put("two", "2");
        map.put("three","3");
        map.put("four", "4");
        map.put("five", "5");
        map.put("six", "6");
        map.put("seven", "7");
        map.put("eight", "8");
        map.put("nine", "9");
        StringBuilder sb = new StringBuilder();
        int i = 0;
        while (i < s.length()) {
            if (Character.isDigit(s.charAt(i))) {
                sb.append(s.charAt(i));
                i++;
                continue;
            }
            int start = i;
            int end = start;
            while (end < s.length() && !Character.isDigit(s.charAt(end))) {
                end++;
                if (map.containsKey(s.substring(start, end))) break;
            }
            String number = s.substring(start, end);
            //System.out.printf("%s\n", number);
            sb.append(map.get(number));
            i = end;
        }
        return Integer.parseInt(sb.toString());
    }
}