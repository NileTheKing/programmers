class Solution {
    public int solution(int n, int w, int num) {
        // 1. num의 위치(층과 열 인덱스) 구하기
        int mok = (num - 1) / w; // num이 있는 층 (0부터 시작)
        int boxindex = 0; // num의 열 위치 (왼쪽에서부터 0 ~ w-1)
        
        // mok % 2 == 0이면 왼쪽->오른쪽, mok % 2 == 1이면 오른쪽->왼쪽
        if (mok % 2 == 1) { 
            // 오른쪽으로부터 온 거 (역방향)
            int firstNumOnLine = mok * w + 1;
            int order = num - firstNumOnLine;
            boxindex = (w - 1) - order; 
        } else {
            // 왼쪽으로부터 온 거 (정방향)
            int firstNumOnLine = mok * w + 1;
            int order = num - firstNumOnLine;
            boxindex = order;
        }

        // 2. 마지막 박스(n)의 위치 정보 구하기
        int lastMok = (n - 1) / w; // 마지막 층
        int lastFirstNum = lastMok * w + 1;
        int lastOrder = n - lastFirstNum; // 마지막 층에서 몇 번째인지 (0부터)
        
        int lastLineMaxIndex = 0; // 마지막 층이 차지하는 인덱스 범위
        if (lastMok % 2 == 1) {
            // 마지막 층이 오른쪽에서 왼쪽으로 채워지는 경우
            lastLineMaxIndex = (w - 1) - lastOrder; 
        } else {
            // 마지막 층이 왼쪽에서 오른쪽으로 채워지는 경우
            lastLineMaxIndex = lastOrder;
        }

        // 3. 전체 높이 차이 계산
        int totalHeight = lastMok + 1;
        int numHeight = mok + 1;
        int diff = totalHeight - numHeight;

        // 4. 마지막 줄에서 num의 열 위치(boxindex)에 박스가 있는지 확인
        boolean hasBoxAbove = false;
        if (lastMok % 2 == 1) {
            // 마지막 층이 우->좌: 인덱스가 lastLineMaxIndex 이상 w-1 이하에 박스가 있음
            if (boxindex >= lastLineMaxIndex) {
                hasBoxAbove = true;
            }
        } else {
            // 마지막 층이 좌->우: 인덱스가 0 이상 lastLineMaxIndex 이하에 박스가 있음
            if (boxindex <= lastLineMaxIndex) {
                hasBoxAbove = true;
            }
        }

        if (hasBoxAbove) {
            return diff + 1;
        } else {
            return diff;
        }
    }
}