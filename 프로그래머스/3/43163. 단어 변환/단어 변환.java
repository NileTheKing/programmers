import java.util.*;
class Solution {
    public int solution(String begin, String target, String[] words) {
        
        Queue<String> q = new LinkedList<>();
        boolean[] visited = new boolean[words.length];
        int cnt = 0;
        
        q.offer(begin);
        while (!q.isEmpty()) {
            cnt++;
            int size = q.size();
            for (int i = 0; i < size; i++) {
                //가지고 있는 q에서 인접단어(바꿀 수 있는 단어를 다 넣음)
                String current = q.poll();
                if (current.equals(target)) {
                    return cnt - 1;
                }
                for (int j = 0; j < words.length; j++) {
                    //현재와 단어장에 있는 것을 비교해서 하나만 다른거만 넣음. 그리고 방문처리
                    if (isOneDiff(current, words[j]) && !visited[j]) {
                        q.offer(words[j]);
                        visited[j] = true;
                    }
                }
            }
        }

        return 0;
    }
    boolean isOneDiff(String str1, String str2) {
        
        int cnt = 0;
        for (int i = 0; i < str1.length(); i++) {
            if (str1.charAt(i) != str2.charAt(i)) {
                cnt++;
            }
        }
        
        return cnt  == 1;
    }
}
/**
그냥 하나씩 봐야함(미로 찾기처럼)
그니까 매 스텝마다 갈 수 있는 words골라서 bfs를 실행하면 최단거리를 구할 수 있다.

*/