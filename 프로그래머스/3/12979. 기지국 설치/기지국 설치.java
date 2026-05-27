class Solution {
    public int solution(int n, int[] stations, int w) {
        int coverWidth = 2 * w + 1;
        int lastCovered = 0;
        int cnt = 0;
        for (int s : stations) {
            int firstCovered = s - w;
            int dist = firstCovered - lastCovered - 1; //3 - 0 - 1 -> 2
            // System.out.printf("s %d,last %d start %d dist %d, cnt %d\n", s, lastCovered, firstCovered, dist, (dist + coverWidth - 1) / coverWidth);
            cnt += (dist + coverWidth - 1)  / coverWidth;
            //2개 커버하려면 1개면 됨.
            // 2 + 3 - 1 / 3 -> 1
            lastCovered = s + w; 
        }
        //N번아파트에서 마지막커버도 더해야한다
        // System.out.printf("before last apt cnt: %d\n", cnt);
        // System.out.printf("(n:%d) (lastCovered:%d)\n", n, lastCovered);
        cnt += (n + 1 - lastCovered - 1 + coverWidth - 1) / coverWidth;
        // System.out.printf("after last apt cnt: %d\n", cnt);
        
        return cnt;
    }
}