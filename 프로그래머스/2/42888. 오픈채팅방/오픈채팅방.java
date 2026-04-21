import java.util.*;
class Solution {
    public String[] solution(String[] record) {
        Map<String, String> uuidMap = new HashMap<>();
        for (String r : record) {
            String[] parts = r.split(" ");
            String cmd = parts[0];
            if (cmd.equals("Enter") || cmd.equals("Change")) { //uuidMap에 등록
                uuidMap.put(parts[1], parts[2]);//등록과 수정동시
            }else continue;
        }
        //마지막정보 아니까 이제 순회함녀서 결과
        List<String> ans = new ArrayList<>();
        for (String r : record) {
            String[] parts = r.split(" ");
            String cmd = parts[0];
            String uuid = parts[1];
            
            if (cmd.equals("Change")) continue; //닉변은 안함
            String lastPart = cmd.equals("Enter") ? "들어왔습니다." : "나갔습니다.";
            ans.add(uuidMap.get(uuid) + "님이 " + lastPart);
        }
        return ans.toArray(new String[0]);
    }
}
/**
결국 나갔다오든 변경하든 기존 uuid에 대응하는 값 저장..
1go에서 각 uuid마지막 이름기억
2go record에서 출력.
*/