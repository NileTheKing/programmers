import java.util.*;
class Solution {
    public int[] solution(String[] id_list, String[] report, int k) {
        Set<String> duplicate = new HashSet<>();
        Map<String, Integer> reportedTimes = new HashMap<>();//유저마다 당한
        Map<String, Set<String>> mail = new HashMap<>();//메일보낼용..
        
        for (String r : report) {
            String[] parts = r.split(" ");
            String u1 = parts[0]; //한사람
            String u2 = parts[1]; //당한사람
            
            if (duplicate.contains(u1 +" " + u2)) continue; //이미 신고한놈이 또한걱면 무시
            //신고횟수추가, 메일용추가.. 
            //신고횟수
            reportedTimes.put(u2, reportedTimes.getOrDefault(u2, 0) + 1);//1회추가
            duplicate.add(u1 +" " +u2);
            //메일
            mail.computeIfAbsent(u2, key -> new HashSet<>()).add(u1);
            
            //u2가 정지당하면 u1한테알려주기위함
        }
        //이제 당한거 key순회하면서 값이 k이상인애만 뽑아오기
        List<String> toBeBanned = new ArrayList<>();
        for (String key : reportedTimes.keySet()) {
            if (reportedTimes.get(key) >= k) toBeBanned.add(key);//너무많이신고당함
        }
        
        //이제 정지때리면서 메일보내주기
        //정지당한애들 신고한 목록에 있으면 카운트.
        //정지당한애들 최대1000.. 유저1000
        //풀스캔해도됨?
        int[] ans = new int[id_list.length];
        for (int i = 0; i < id_list.length; i++){ 
            //정지먹는애들중에 id가 신고한애들 몇명인지 찾기.
            String id = id_list[i]; //내가 신고한애중에 정지 몇명당했나
            int cnt = 0;
            for (String ban : toBeBanned) {
                if (mail.get(ban).contains(id)) cnt++;
            }
            ans[i] = cnt;
        }
        return ans;
    }
}
/**
유저수 1000
중복닉네임x 아이디10글자

신고내역 200,000개
같은유저가 여러번신고한거 처리해야함.

자 그러면 이제 report가지고 어떠한 자료구조에다가 처리를해야겠찌
그러면 음.. 각 Set으로 한유저가 여러번신고한거 막고
각각 hashmap에 신고당한횟수관리
그리고 누가 누구 신고했는지 관리해야함
1.한사람+당한사람 set ok
2. 유저, 횟수(당한) ok
3. 유저, 신고한사람 or 신고당한사람, 신고한사람.. 후자..

마지막에 2번순회 k회이상 추출 -> 3번에서 찾아서 메일보내기
*/