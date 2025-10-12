import java.util.*;
class Solution {
    Map<String, Integer> userId2number = new HashMap<>();
    List<List<String>> candidates = new ArrayList<>();
    Set<Integer> combinations = new HashSet<>();
    public int solution(String[] user_id, String[] banned_id) {
        for (int i = 0; i < user_id.length; i++) {
            userId2number.put(user_id[i], i);
        }
        for (String ban : banned_id) {
            String regex = ban.replaceAll("\\*", ".");
            List<String> match = new ArrayList<>();
            for (String user : user_id) {
                if (user.matches(regex)) match.add(user);
            }
            candidates.add(match);
        }
        
        dfs(banned_id, 0, 0);
        return combinations.size();
    }
    public void dfs(String[] banned_id, int banIdx, int current) {
        if (banIdx == banned_id.length) {
            combinations.add(current);
            return;
        }
        
        for (String can : candidates.get(banIdx)) {
            int userIdx = userId2number.get(can);
            if ((1 << userIdx & current) != 0) continue;
            dfs(banned_id, banIdx + 1, ((1 << userIdx) | current));
        }
    }
}