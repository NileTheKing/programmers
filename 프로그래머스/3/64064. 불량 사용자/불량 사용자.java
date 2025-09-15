import java.util.*;

class Solution {
    Set<Integer> finalCombinations = new HashSet<>();
    Map<String, Integer> userIdx = new HashMap<>();
    List<List<Integer>> ways; // 인덱스별 후보 저장

    public int solution(String[] user_id, String[] banned_id) {
        // 유저 인덱스 매핑
        for (int i = 0; i < user_id.length; i++) {
            userIdx.put(user_id[i], i);
        }

        // banned_id 개수만큼 리스트 준비
        ways = new ArrayList<>();
        for (int i = 0; i < banned_id.length; i++) {
            ways.add(new ArrayList<>());
        }

        // 각 banned_id별 후보 찾기
        for (int i = 0; i < banned_id.length; i++) {
            String ban = banned_id[i];
            for (String user : user_id) {
                if (match(ban, user)) {
                    ways.get(i).add(userIdx.get(user));
                }
            }
        }

        backtrack(banned_id.length, 0, 0);
        return finalCombinations.size();
    }

    public void backtrack(int banCount, int idx, int mask) {
        if (idx == banCount) {
            finalCombinations.add(mask);
            return;
        }

        for (int user : ways.get(idx)) {
            if ((mask & (1 << user)) != 0) continue;
            backtrack(banCount, idx + 1, mask | (1 << user));
        }
    }

    private boolean match(String ban, String user) {
        if (ban.length() != user.length()) return false;
        for (int j = 0; j < user.length(); j++) {
            if (ban.charAt(j) == '*') continue;
            if (ban.charAt(j) != user.charAt(j)) return false;
        }
        return true;
    }
}
