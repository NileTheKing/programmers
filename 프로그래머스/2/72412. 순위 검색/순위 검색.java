import java.util.*;
class Solution {
    public int[] solution(String[] info, String[] query) {
        Map<String, List<Integer>> map = new HashMap<>();
        for (String i : info) {
            //16개 조합 키에 현재 score 넣기
            //parts로 나누고.. 그 String[]를 순회하면서 조합만들게하자
            String[] parts = i.split(" ");
            int score = Integer.parseInt(parts[4]);
            List<String> keys = new ArrayList<>();
            getCombinationKeys(parts, keys, 0, new StringBuilder());
            for (String key : keys) {
                //System.out.printf("key: %s\n", key);
                map.computeIfAbsent(key, k -> new ArrayList<>()).add(score);
            }
        }
        for (String key : map.keySet()) map.get(key).sort((a,b) -> a-b);
        int idx = 0;
        int[] ans = new int[query.length];
        for (String q : query) {
            //q를 이제  map에서 찾으면됨.
            //cpp and - and senior and pizza 250
            //일단 and가지고 뺴면 parts[] {cpp,-,senior,pizza,250}
            //이거 다 이어붙이면 키되
            String clean = q.replace(" and ", "");
            String[] parts = clean.split(" ");
            String key = parts[0];
            // System.out.println();
            // int score = 1;
            int score = Integer.parseInt(parts[1]);
            List<Integer> scores = map.get(key);
            if (scores == null) {ans[idx++] = 0; continue;}
            //score중에 몇명이나 score위로있나.. 그러면 정렬해가지고 score의 인덱스 구하면됨
            // scores.sort((a,b) -> a - b);
            int total = scores.size(); //모든 사람들..이제 score이상인애들 구하면됨.scoreidx
            // System.out.printf("===for Query %s===\n", key);
            // for (int i : scores) System.out.printf("%d ", i);
            // System.out.printf("\n====\n");
            int l = 0;
            int r = total - 1; //total의 index를 m으로 찾아
            int scoreIdx = total;
            while (l <= r) {
                
                int m = l + (r - l) / 2;
                // System.out.printf("trying %d\n",m);
                if (scores.get(m) < score) {
                    l = m + 1;
                }else  {
                    scoreIdx = m;
                    r = m - 1;
                }
            }
            int count = total - scoreIdx;
            //예를들어 1 2 3 4 5야. score기준치가 3임. score인덱스는2고 전체크기가5.
            //만족하는사람은 3인데 5-2하면됨.
            // System.out.printf("For Query %s, count = %d\n total=%d, query score idx = %d\n", key, count, total, scoreIdx);
            // System.out.printf("END OF QUERY\n");
            ans[idx++] = count;
            
        }
        return ans;
    }
    public void getCombinationKeys(String[] parts, List<String> keys, int idx, StringBuilder current) { //idx는 지금 넣을지말지하는 인덱스
        //일단 지금상태 추가.
        //current.append("-");
        //keys.add(new String(current.toString()));
        if (idx == parts.length-1) {//숫자는패스
            keys.add(new String(current.toString()));
            return;
        }
        current.append(parts[idx]);
        getCombinationKeys(parts, keys, idx + 1, current);
        //abcd
        //0123
        //ab
        //length=4 지워야할거 2부터4까지 cd의 length2
        current.delete(current.length() - parts[idx].length(), current.length());
        current.append("-");
        getCombinationKeys(parts, keys, idx + 1, current);
        current.deleteCharAt(current.length()-1);
    }
}