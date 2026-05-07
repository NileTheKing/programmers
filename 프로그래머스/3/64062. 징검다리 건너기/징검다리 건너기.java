class Solution {
    public int solution(int[] stones, int k) {
        int r = Integer.MAX_VALUE;
        int l = 0;
        int ans = 0;
        //int debugLimit = 0;
        while (l <= r) {
            //debugLimit++;
            int m = l + (r - l)/ 2;
            
            int prev = -1;//마지막 인덱스
            int max = Integer.MIN_VALUE;//건너가기위해 필요한 점프능력
            for (int i = 0; i < stones.length; i++) {
                //m만큼 숫자를 깐 상태에서..건널수있으면
                if (stones[i] - m > 0) {
                    max = Math.max(max, i - prev);//이칸 뛰어넘음.
                    prev = i;
                }else {
                    //이건 패스해야함
                }
            }
            //마지막prev에서 길이도재야함
            max = Math.max(stones.length - prev, max);
            // System.out.printf("m, max, k : %d %d %d\n", m,max,k);
            //너무 많이 뛰어야한다 -> 너무 많이 건너가는게 목표다
            if (max > k) {
                r = m - 1;
            }else {
                ans = m + 1;
                l = m + 1;
            }
        }
        return ans;
    }
}
/**
뭔가 시뮬레이션같기도 하면서
야 이거 그거네.......잔디깎기;;;그거아니면 그내가 했떤 카드뽑기; 하
풀었던거네 ㄹㅈㄷ
stone배열이 20만이라 시뮬하면 백~~퍼 터지지
아하 ㅋ그러면 이분탐색이고.. 아 쉽다
뭐 이것저것해야하니까 자료형 주의하시고~
근데 어떻게하드라 ㅋㅋ 일단 음..
이분탐색에서 ..음... 일단 뭐 깎고 뭐 어쩌고 하는 작업이 있을거임.
아 머리아픔
음..자 생각해보자
1000명이 간다쳐봐 그러면 .. 뭐야 음수인 애들있을거아니야.
그렇다치면...

-1 1 2 0 -1 -2 1 -1 2 -2
3일때.. 다 깎아버려. 그러면 이렇게되ㅗㄱ 이상태에서 k조건으로 건널수있는지 체크하면 되는거아님?
간단한디? 뭐 할것도없고.. 이게끝..? 이상태에서..못건넘
*/