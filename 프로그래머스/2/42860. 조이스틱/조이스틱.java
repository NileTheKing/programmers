class Solution {
    public int solution(String name) {
        
        int updown = 0; //상하 총 이동거리
        int end = name.length() - 1; // endindex
        int length = name.length();
        int jump = length - 1;
        
        for (int i = 0; i < length; i++) {//다 바꿀 떄 까지 진행
            char c = name.charAt(i);
            int alphabet = 0;
            
            alphabet = Math.min(c - 'A', 'Z' - c + 1);
            // A에서 Z는 뒤로 가는게 더 이득. 이거 이동거리 1을 어떻게 계산하나?
            // 순방향으로 가는 거는 Z - A 하면 나옴
            // 역방향은 A - 
            updown += alphabet;
            
            //다음 위치 찾기
            //A는 건너뛰기
            int next = i + 1;
            while (next < length&& name.charAt(next) == 'A') {
                next++;
            }
            //다음 이동 커서까지 좌우이동
            jump = Math.min(jump, Math.min(i + i + length - next, (length - next) * 2 + i));
            //end = 끝인덱스. next= 다음인덱스. 아니 
        }
        return jump + updown; //좌우이동거리와 상하거리를 더함
    }
}
/**
커서 이동횟수, 알파벳 변경횟수
AAA -> JAZ
A는 점프할테고..

가정1. 오른쪽으로 순서대로 쭉가는법
가정2. 왼쪽으로 쭉 가는 법
가정3. 오른쪽으로 가서 다음가는 거보다 왼쪽으로 가서 다음문자 도달하는 게 빠르면 그렇게 가는법
가정4. 왼쪽으로 쭉가는 것이 그 다음 보다 더 빠른 법
그렇다면 매번 다음으로 가는 방법중에 최선인 것을 가지고 있음

*/