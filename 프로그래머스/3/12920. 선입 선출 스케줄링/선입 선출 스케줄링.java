import java.util.*;
class Solution {
    public int solution(int n, int[] cores) {
        if (cores.length >= n) return n;
        int t = 0;
        int l = 0;
        int r = 10000 / 2 * 50000 * 2;
        // System.out.printf("===parametric===\n");
        while (l <= r) {
            int m = l + (r - l) / 2;
            // System.out.printf("===m=%d===\n",m);
            long jobsDone = cores.length;
            for (int c : cores) {
                jobsDone += (m / c);
            }
            // System.out.printf("m:%d, jobsDone:%d\n", m, jobsDone);
            //n - jobsDone -> 남은일갯수
            if (jobsDone >= n) { //처리한일이 너무많음
                r = m - 1;
                t = m;
            }else {//시간이부족해서 처리못했음
                l = m  + 1;
            }
        }
        
        long done = cores.length;
        for (int c : cores) done += ((t - 1) / c);
        long left = n - done;
        // System.out.printf("===DEBUG===\n");
        // System.out.printf("t-1:%d, left:%d\n", t-1, left);
        for (int i = 0; i < cores.length; i++) {
            //현재 코어가 일중이면 패스
            if ( t % cores[i] != 0) {
                // System.out.printf("core%d working..at %d,pass\n", i+1, t-1);
                continue;
            }
            if (left == 1) return i + 1;
            left--;
        }
        return -1;
    }
}
/**
시뮬레이션한다면? 
작업 50,000 / 코어 2개 * 10000 = 2억5천초 소요
2억5천초 * 코어2개 => 5억번연산 TLE

n=6 cores:{1,2,3}

t=0 1 2 3
t=1 4
t=2 5 6
t=3

고로 구하는 t는 2여야하고..
t-1에서 다된거 구하고 t에서 수행임
*/