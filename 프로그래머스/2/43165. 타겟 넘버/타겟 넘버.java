class Solution {
    int cnt = 0;
    public int solution(int[] numbers, int target) {
        backtrack(numbers, 0, target, 0);
        return cnt;
    }
    public void backtrack(int[] numbers, int idx, int target, int current) {
        if (idx == numbers.length) {
            if (current == target) cnt++;
            return;
        }
        
        //더하기
        backtrack(numbers, idx + 1, target, current + numbers[idx]);
        //뺴기
        backtrack(numbers, idx + 1, target, current - numbers[idx]);
        
    }
}