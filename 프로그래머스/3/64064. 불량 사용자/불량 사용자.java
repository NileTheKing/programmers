import java.util.*;
class Solution {
    Set<Integer> candidates = new HashSet<>();
    String[] user_id;
    String[] banned_id;
    public int solution(String[] user_id, String[] banned_id) {
        this.user_id = user_id;
        this.banned_id = banned_id;
        backtrack(0, 0);//TODO
        return candidates.size();
    }
    public void backtrack(int idx, int user_bit) {
        if (idx >= banned_id.length) { //종료시 경우의스체크.
            if (candidates.contains(user_bit)) return;
            candidates.add(user_bit);
            return;
        }
        //지금 처리중인 banned_id인덱스의 케이스를 user_id에서 풀스캔.
        String ban = banned_id[idx];
        Outter : for (int i = 0; i < user_id.length; i++) {
            //지금 유저 사용한 유저면 패스
            if ((user_bit & (1 << i)) != 0) continue;
            //ban이랑 user_id가 *뺴고 똑같아야함
            //이거를 음.. 그냥 하드코딩하든가 아님 뭐 regex되니ㅏ?근데모름걍하드코딩
            String user = user_id[i];
            if (ban.length() != user.length()) continue; //길이안맞음.패스
            boolean possible = true;
            for (int j = 0; j < user.length(); j++) {//
                //같으면 ㅇㅋ 달라도 *이면 ㅇㅋ 아예 다르면 안됨
                if (ban.charAt(j) == user.charAt(j)) continue;//다음 문자
                if (ban.charAt(j) == '*') continue;//다음문자
                if (ban.charAt(j) != user.charAt(j)) { //다르면 다음 유저
                    continue Outter;
                }
            }
            //다 통과했으면 비트마스킹칠해서 보내버려. 백트래킹응ㄴ 필요없다.
            int next_bit = user_bit | (1 << i);//user i는 켜가지고 상태바꾸기
            backtrack(idx + 1, next_bit); //기존bit은 안전함.안건들임.
        }
    }
}