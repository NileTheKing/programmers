import java.util.*;
class Solution {
    Set<Integer> combinations = new HashSet<>();//비트마스킹으로 user_id인덱스스위치
    String[] user_id;
    String[] banned_id;
    public int solution(String[] user_id, String[] banned_id) {
        this.user_id = user_id;
        this.banned_id = banned_id;
        backtrack(0, 0, 0);
        return combinations.size();
    }
    //banned_id를 idx 증가하면서 탐색하며 user_id가 처리되었는지를 확인
    //처리안 된 유저에 한해 처리가능하다면(길이동일,*처리) 추가/추가안하기 백트래킹
    public void backtrack(int currentBit, int banned_id_idx, int bannedBit) {
        if (banned_id_idx == banned_id.length) {
            if (bannedBit != ((1 << banned_id.length) - 1)) return;//다안씀
            combinations.add(currentBit);
            return;
        }
        //banned_id_idx에 해당하는 banned_id에 한해서 user_id순회
        for (int i = 0; i < user_id.length; i++) {
            if ((currentBit & (1 << i)) != 0) continue;//이미처리된유저(못고름)
            if (matched(user_id[i], banned_id[banned_id_idx])) {
                backtrack(currentBit | (1 << i), banned_id_idx + 1, bannedBit | (1 << banned_id_idx));
            }
            //돌릴거없음 바뀐게없으니 다음 for턴에서 다른유저시도해볼거임
            //근데 아예 안쓰고 넘어가면 말안되는디.. 다써야만가능함..<췤~
        }
    }
    public boolean matched(String user, String ban) {
        if (user.length() != ban.length()) return false;
        int len = user.length();
        for (int i = 0; i < len; i++) {
            if (user.charAt(i) == ban.charAt(i)) continue;
            if (ban.charAt(i) == '*') continue;
            return false;
        }
        return true;
    }
}