import java.util.*;
class Solution {
    Map<Long, Integer> memo = new HashMap<>();
    
    public int solution(int n, int[] weak, int[] dist) {
        Arrays.sort(dist);
        int[] weakPlus = new int[weak.length * 2];
        for (int i = 0; i < weak.length; i++) {
            weakPlus[i] = weak[i];
            weakPlus[i + weak.length] = weak[i] + n;
        }
        int goalBit = (1 << weak.length) - 1;
        return backtrack(weakPlus, dist, 0, goalBit, 0);
    }

    public int backtrack(int[] weakPlus, int[] dist, int currentBit, int goalBit, int usedCnt) {
        if (currentBit == goalBit) return usedCnt;
        if (usedCnt >= dist.length) return -1;

        long key = ((long) currentBit << 4) | usedCnt;   // 상태를 하나의 키로 합치기
        if (memo.containsKey(key)) return memo.get(key);

        int currentDistIdx = dist.length - 1 - usedCnt;
        int weakLength = weakPlus.length / 2;
        int best = -1;

        for (int i = 0; i < weakLength; i++) {
            if ((currentBit & (1 << i)) != 0) continue;
            int bit = currentBit | (1 << i);
            for (int j = i + 1; j < weakPlus.length; j++) {
                if (weakPlus[j] - weakPlus[i] > dist[currentDistIdx]) break;
                bit |= (1 << (j % weakLength));
            }
            int res = backtrack(weakPlus, dist, bit, goalBit, usedCnt + 1);
            if (res != -1 && (best == -1 || res < best)) best = res;
        }

        for (int i = weakPlus.length - 1; i >= weakLength; i--) {
            int originalI = i % weakLength;
            if ((currentBit & (1 << originalI)) != 0) continue;
            int bit = currentBit | (1 << originalI);
            for (int j = i - 1; j >= 0; j--) {
                if (weakPlus[i] - weakPlus[j] > dist[currentDistIdx]) break;
                bit |= (1 << (j % weakLength));
            }
            int res = backtrack(weakPlus, dist, bit, goalBit, usedCnt + 1);
            if (res != -1 && (best == -1 || res < best)) best = res;
        }

        memo.put(key, best);
        return best;
    }
}