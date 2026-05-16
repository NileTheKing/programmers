import java.util.*;
class Solution {
    public int solution(int storey) {
        int at = 0;//지금위치
        int cnt = 0; //사용갯수
        while (at != storey) {
            at = getTheClosest(at, storey);
            cnt++;
        }
        return cnt;
    }
    public int getTheClosest(int start, int dest) {
        //start에서 dest로 가는 것중에 가장 가까운 곳
        int res = start;//가장가까운곳갱신
        int minDiff = Math.abs(start-  dest);
        if (start == dest) return start;
        //경우의수1 start < dest -> 1, 10, 100,1000 점프
        else if(start < dest) {
            int currentDiffBest = dest - start;
            int unit = 1; //1 10 100
            while (true) {
                int afterPosition = start + unit;//점프한 위치
                if (Math.abs(dest - afterPosition) > currentDiffBest) {
                    break;
                }
                if (Math.abs(dest - afterPosition) < minDiff) {
                    res = start + unit;
                    minDiff = Math.abs(dest - afterPosition);
                }
                unit *= 10;//다음시도
            }
        }
        //경우의수2 start > dest -> -1, -10, -100 점프.
        else {
            int currentDiffBest = start - dest;
            int unit = -1; //1 10 100
            while (true) {
                int afterPosition = start + unit;//점프한 위치
                if (Math.abs(dest - afterPosition) > currentDiffBest) {
                    break;
                }
                if (Math.abs(dest - afterPosition) < minDiff) {
                    res = start + unit;
                    minDiff = Math.abs(dest - afterPosition);
                }
                unit *= 10;//다음시도
            }
        }
        return res;
    }
}