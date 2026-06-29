import java.util.*;
class Solution {
    int[] ans = {-1};
    int max = -1;
    public int[] solution(int n, int[] info) {
        backtrack(n, new int[info.length], 0, info);
        return ans;
    }
    public void backtrack(int arrows, int[] current, int idx, int[] info) {
        if (idx == info.length) {
            finalize(arrows, current, info);   
            return;
        }
        current[idx] = 0;//안쏨
        backtrack(arrows, current, idx + 1, info);
        if (arrows > info[idx]) { //어피치가 쏜거보다 1개 더쏠것. 근데 남아야함d
            current[idx] = info[idx] + 1;
            backtrack(arrows - current[idx], current, idx + 1, info);
            //되돌릴거는..없는듯?어차피 안쏘는건해가지고 피해볼것이없다.
        }
        return;
    }
    public void finalize(int arrows, int[] current, int[] info) {//계산, 나머지 다 정리, 갱신..
        //남은 화살 다 때려박기 0점에
        for (int i = 0; i < arrows; i++) {
            current[10]++;
        }
        int apeach = 0;
        int lion = 0;
        for (int i = 0; i < info.length; i++) {
            if (info[i] == 0 && info[i] == current[i]) continue;
            if (current[i] > info[i]) lion += (10 - i);
            else apeach += (10 - i);
        }
        int diff = lion - apeach;
        if (diff <= 0) return;
        if (diff > max) {
            max = diff;
            ans = Arrays.copyOf(current, current.length);
            return;
        }
        if (diff == max) {
            for (int i = 10; i >= 0; i--) {
                if (ans[i] == current[i]) continue;
                else if (ans[i] > current[i]) {
                    return;
                }else {
                    max = diff;
                    ans = Arrays.copyOf(current, current.length);
                    return;
                }
            }
        }
    }
}
/**
n발쏜다
info->어피치가 맞춘 점수판. (점수,갯수)

완탐하면된다.
    쏜다: 어피치 + 1
    안쏜다: 0

다쏴야하니까 마지막에 점수집계

*/