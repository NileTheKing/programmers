import java.util.*;
class Solution {
    public int[] solution(String s) {
        String s1 = s.replace("{{", "");
        String s2 = s1.replace("}}", "");
        String[] parts = s2.split("\\},\\{");
        Arrays.sort(parts, (o1, o2) -> o1.length() - o2.length());
        List<Integer> ans = new ArrayList<>();
        Set<Integer> set = new HashSet<>();
        for (String p : parts) {
            // [2] , [2,1], [2,1,3], [2,1,3,4]
            String[] nums = p.split(",");
            for (String n : nums) {
                int num = Integer.parseInt(n);
                if (set.contains(num)) continue;
                set.add(num);
                ans.add(num);
            }
        }
        return ans.stream().mapToInt(i->i).toArray();
    }
}