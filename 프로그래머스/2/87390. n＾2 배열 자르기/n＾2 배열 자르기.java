class Solution {
    public int[] solution(int n, long left, long right) {
        int size = (int)(right - left + 1);
        int[] ans = new int[size];
        int idx = 0;
        //left 0 right 3이면 
        for (long i = left; i <= right; i++) {
            int r0 = (int)(i / n); //0indexed
            int c0 = (int)(i % n);
            int r1 = r0 + 1;
            int c1 = c0 + 1;
            
            int max = Math.max(r1, c1);
            ans[idx++] = max;
        }
        return ans;
    }
}
/**
n= 10^7 천만.
right-left는 10^5. 십만.
n이 차원크기고 정방형
문제를 보면 음.. 완성 다해가지고 자르는건 아님. 완성하는데 n^2니까.
그러면 수학적 공식 이 있다는 말.
그렇다는건 그냥 left right 사이 왔다갔다 o(n)순회
left는 인덱스인데 이거 2차원배열잉께 몫과 나머지겠네.
이거가지고 거기 값이 몇인지 도출가능.
알았따 ㅋㅋ 행과 열 번호(0index)구해서 1인덱스로 바꾸면 (+1) 뭔숫자인지나옴. or값임.
그ㅜㄴ데 둘중 뭐를 따르냐? 더 큰것
*/