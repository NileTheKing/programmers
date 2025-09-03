class Solution {
    final int ticks = 60 * 60 * 12; //가장 느린 시침은 초침기준 43200초걸려야 한바퀴
    public int solution(int h1, int m1, int s1, int h2, int m2, int s2) {
        
        
        int firstSecs = h1 * 3600 + m1 * 60 + s1;
        int secondSecs = h2 * 3600 + m2 * 60 + s2;

        
        int cnt = 0;
        //초기 위치세팅
        long init_h = (long)firstSecs * 1 % ticks;
        long init_m = (long)firstSecs * 12 % ticks;
        long init_s = (long)firstSecs * 720 % ticks;
        if (init_s == init_m || init_s == init_h) {
            cnt = 1;
        }
        long prev_s = init_s;
        long prev_m = init_m;
        long prev_h = init_h;
        for (int i = firstSecs + 1; i <= secondSecs; i++) {//시뮬레이션
            long s = i * 720 % ticks;
            long m = i * 12 % ticks;
            long h = i * 1 % ticks;
            //이전값을 읽어와야 하는데 이러면 초기값이 이상함
            //초기값 설정하면 for문에서 이전값을 못읽음
            
            
            boolean sm_crossed = cross(prev_s, prev_m, 720, 12);
            boolean sh_crossed = cross(prev_s, prev_h, 720, 1);
            
            if (sm_crossed && sh_crossed) {
    // 두 겹침이 동시에 일어난 경우
            if (m == h) {
                // 분침과 시침이 같은 위치에서 만났다면(12시 정각), 하나의 이벤트
                cnt++;
            } else {
                // 서로 다른 위치에서 만났다면(e.g., 테스트 6), 두 개의 이벤트
                cnt += 2;
            }
            } else if (sm_crossed || sh_crossed) {
            // 겹침이 하나만 일어난 경우
            cnt++;
            }
            prev_s = s;
            prev_m = m;
            prev_h = h;
            
        }
        
        return cnt;
    }
    public boolean cross(long prev1, long prev2, int speed1, int speed2) {
        //1초동안 이동거리가 거리 차이를 넘어섰냐?
        long delta =  (ticks - (prev2 - prev1)) % ticks;
        int relativeSpeed = speed1 - speed2;
        if (delta > 0 && relativeSpeed + delta >= ticks) return true;
        else return false;
    }
}
/**
시뮬레이션 문제
시간 1초씩 움직이면서 각각
만약5분이라면 43200의 1/12임. 3600이면 5분인거.
만약5초면 위치가 3600이겠지. 그렇다면 3600이면 5초인거
*/

