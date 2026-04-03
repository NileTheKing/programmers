import java.util.*;
class Solution {
    public int solution(String begin, String target, String[] words) {
        Queue<String> q = new LinkedList<>();
        q.offer(begin);
        Map<String, List<String>> map = new HashMap<>();
        for (String w : words) {
            int cnt = 0;
            for (int i = 0; i < w.length(); i++)  {
                if (begin.charAt(i) !=  w.charAt(i)) cnt++;
            }
            if (cnt == 1) map.computeIfAbsent(begin, k -> new ArrayList<>()).add(w);
        }
        for (String w1 : words) {
            for (String w2 : words) {
                if (w1.equals(w2)) continue;
                int cnt = 0;
                // System.out.printf("===comparing %s %s===\n", w1,w2);
                for (int i = 0; i < w1.length(); i++) {
                    //int cnt = 0;
                    if (w1.charAt(i) != w2.charAt(i)) {
                        // System.out.printf("at %d, different.\n",i);
                        cnt++;
                    }
                    // System.out.printf("diff %s and %s : %d\n", w1,w2,cnt);
                    
                }
                if (cnt == 1) {
                        map.computeIfAbsent(w1, k -> new ArrayList<>()).add(w2);
                        // System.out.printf("!!%s has %s\n", w1, w2);
                    }
                // System.out.printf("===end of comparison===\n");
            }
        }
        
        Set<String> visited = new HashSet<>();
        visited.add(begin);
        int cnt = -1;
        while (!q.isEmpty()) {
            //String polled = q.poll();
            // cnt++;
            //System.out.printf("%s\n", polled);
            cnt++;
            int size = q.size();
            for (int i = 0; i < size; i++) {
                String polled = q.poll();
                if (map.get(polled) == null) continue;
                for (String nei : map.get(polled)) {
                    if (visited.contains(nei)) continue;
                    if (nei.equals(target)) return cnt + 1;
                    q.offer(nei);
                    visited.add(nei);
                }
            }
            
        }
        return 0;
    }
    
}
/*
완탐해야하지..
지금 가지고 있는단어로...  words스캔하면서 변환가능하면 변환.이미 했던 단어로 할필요?없음.
최소니까..
될떄까지.
bfs로 가능한가 이거?
되지?
각 단어별로 이웃을 만들어 그래프 그러고나서 순회..?
*/