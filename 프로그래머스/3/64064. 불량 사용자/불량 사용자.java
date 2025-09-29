import java.util.*;
class Solution {
    List<List<Integer>> candidates;
    Set<Integer> set = new HashSet<>();
    Map<String, Integer> userIdx = new HashMap<>();
    public int solution(String[] user_id, String[] banned_id) {
        candidates = new ArrayList<>();
        for (int i = 0; i < user_id.length; i++) {
            userIdx.put(user_id[i], i);
        }
        for (String ban : banned_id) {
            // fr*d*에 해당할 수 있는 user_id들 모아서 추가해줌
            List<Integer> users = new ArrayList<>();
            String regex_ban = ban.replaceAll("\\*", ".");
            for (String user : user_id) {
                if (user.matches(regex_ban)) {
                    users.add(userIdx.get(user));
                }
            }
            candidates.add(users);
        }
        
        //이제 banned_id는 가능성있는 유저 리스트들이 있음.
        //backtracking해서 카운팅해야함.
        backtrack(user_id, banned_id, 0, 0);
        
        return set.size();
        
    }
    public void backtrack(String[] user_id, String[] banned_id, int idx, int current) {
        if (idx == banned_id.length) {
            //기존에 있는 조합인지 비교해서 추가. 이떄 비트마스킹을 사용해서 빠르게 비ㅐ교
            if (!set.contains(current)) {
                set.add(current);
            }
            return;
        }
        
        for (int user : candidates.get(idx)) {
            if ((current & (1 << user)) != 0) continue;
            backtrack(user_id, banned_id, idx+1, current | 1 << user);
        }
    }
}