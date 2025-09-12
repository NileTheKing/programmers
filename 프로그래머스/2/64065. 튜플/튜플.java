import java.util.*;
class Solution {
    public int[] solution(String s) {
        Set<Integer> set = new LinkedHashSet<>();
        String[] parts = s.substring(2, s.length() - 2).split("\\},\\{");
        Arrays.sort(parts, (s1, s2) -> s1.length() - s2.length());
        
        for (String part : parts) {
            String[] numbers = part.split(",");
            for (String num : numbers) {
                set.add(Integer.parseInt(num));
            }
        }
        return set.stream().mapToInt(i->i).toArray();
    }
}