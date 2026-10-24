import java.util.*;
class Solution {
    Set<Integer> candidates;
    String[] user_id;
    String[] banned_id;
    public int solution(String[] user_id, String[] banned_id) {
        this.user_id = user_id;
        this.banned_id = banned_id;
        this.candidates = new HashSet<>();
        backtrack(0, 0, 0);//유저사용 밴사용
        return candidates.size();
    }
    public void backtrack(int userBit,int banBit, int banIdx) {
        if (banIdx == banned_id.length) {
            candidates.add(userBit);
            return;
        }
        //banned_id에서 banIdx이용해서.. user_id순회하며 일치하면 시도
        String anonName = banned_id[banIdx];
        for (int i = 0; i < user_id.length; i++) {
            String u = user_id[i];
            if (!match(anonName, u)) continue;// 안되니까 다음
            //banBit사용했음...이필요한가?순서대로 올라가고있잖음.조합이라 얜필없다
            //userBit사용했으면..패스.얜가능성있지.즉 userBit의 i번유저가 켜졌냐. >>연산
            if ((userBit & (1 << i))!=0) continue;
            int nextBit = userBit | (1 << i); //i번빗킨다. userBitㅇㄴ바꾸면..큰일
            backtrack(nextBit, banBit, banIdx + 1);
            //되돌릴게없음
        }
    }
    public boolean match(String s1, String s2) {
        if (s1.length() != s2.length()) return false;
        int len = s1.length();
        for (int i = 0; i < len; i++) {
            if (s1.charAt(i)=='*') continue;
            if (s1.charAt(i) != s2.charAt(i)) return false;
        }
        return true;
    }
}