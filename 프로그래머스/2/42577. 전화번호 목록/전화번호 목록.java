import java.util.*;
class Solution {
    public boolean solution(String[] phone_book) {
        Map<String, Integer> map = new HashMap<>();
        //경우의수 추가
        for (String p : phone_book) {
            //추가
            for (int i = 0; i < p.length(); i++) {
                map.put(p.substring(0, i + 1), map.getOrDefault(p.substring(0, i + 1), 0) + 1);
            }
        }
        for (String p : phone_book) { 
            //확인
            if (map.getOrDefault(p, 0) >= 2) return false;
        }
        return true;
    }
}
/**
n^2안됨
그래서 hashmap활용해야함

추가할떄:
    미리 모든 경우의수를 추가 -> 본인게 항상있음
    순서대로 추가 -> startsWith를하든 시간복잡도가.그렇다면 map을써서 2개이상일떄만 가능?
    
    
지금 내가한코드
1.모든 접두사 추가
2. 내번호가 다른번호의 접두사인가?검사인데 좀이상함
*/