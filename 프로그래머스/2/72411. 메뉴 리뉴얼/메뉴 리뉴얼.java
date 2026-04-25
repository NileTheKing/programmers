import java.util.*;
class Solution {
    Map<String, Integer> candi = new HashMap<>();
    public String[] solution(String[] orders, int[] course) {
        
        //각 손님순회.. 정렬!해서 2개고른 거를 전체 자료구조에 더함
        for (String o : orders) {
            //정렬해서 
            char[] tmp = o.toCharArray();
            Arrays.sort(tmp);
            String orderedOrder = new String(tmp);
            helper(orderedOrder, 0, new StringBuilder());
        }
        //for (String key : candi.keySet()) System.out.printf("%s, %d\n", key, candi.get(key));
        //2개이상인거만 필터링하고 course에 맞게 길이맞는애들만 result에 추가. 사전순으로
        List<String> ans = new ArrayList<>();
        for (int c : course) {
            //System.out.printf("===start of length %d====\n", c);
            //풀스캔하면서 길이가 반복이 2이상인거이되 제일 큰 애 찾기.. 흠..이건 정렬하면될듯?
            int max = Integer.MIN_VALUE; //제일 많이시킨 횟수
            for (String key : candi.keySet()) {
                if (candi.get(key) < 2 || key.length() != c) continue;
            
                max = Math.max(max, candi.get(key));
            }
            //System.out.printf("for length %d, max : %d\n", c, max);
            for (String key : candi.keySet()) {
                if (candi.get(key) == max && key.length() == c) 
                {
                    ans.add(key);
                    //System.out.printf("%s added.\n", key);
                }
            }
            //System.out.printf("===end of length %d====\n", c);
            
        }
        //정렬
        ans.sort(null);
        return ans.toArray(new String[0]);
        
    }
    public void helper(String o, int idx, StringBuilder current) {
            //이제 o중에 이제...먹냐안먹냐..이거는이제 무슨개념이지? 순열아니고 조합? 집합?
            //질문은 끝까지가서 하냐 아니면 매 선택마다 하냐인데.. 그냥끝까지가서 뭐..하는거지.. 단 길이가 2는넘어야함.
            if (idx == o.length()) {
                if (current.length() >= 2) {//코스가2이상이니까 추가
                    candi.put(current.toString(), candi.getOrDefault(current.toString(), 0) + 1);
                }
                return;
            }
            current.append(o.charAt(idx));
            helper(o, idx + 1, current);
            current.deleteCharAt(current.length() - 1);
            helper(o, idx + 1, current);
        }
}