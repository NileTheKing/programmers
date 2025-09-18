def solution(sticker):
    if len(sticker) == 1:
        return sticker[0]
    def helper(arr):
        if len(arr) == 0:
            return 0
        if len(arr) == 1:
            return arr[0]
        dp = [0] * len(arr) #dp[i]는 i번째 스티커에서 최댓값을 추적
        dp[0] = arr[0]
        dp[1] = max(arr[0], arr[1])
        
        for i in range(2, len(dp)):
            dp[i] = max(dp[i-2] + arr[i], dp[i-1])
        return dp[len(dp) - 1]
    
    return max(helper(sticker[1:]), helper(sticker[:-1]))
    