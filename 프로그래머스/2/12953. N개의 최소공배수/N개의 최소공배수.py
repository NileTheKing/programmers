def solution(arr):
    #두 수의 최소공배수는 최대공약수의 배수 중의 가장 작은 수
    dp = [0] * len(arr)
    dp[0] = arr[0] #dp[i] 는 arr[i]까지 최소공배수
    def get_gcd(a, b):
        min_val = min(a, b)
        for i in range(min_val, 0, -1):
            if a % i == 0 and b % i == 0:
                return i
        return 1
    for i in range(1, len(dp)):
        #dp[i]값 채우기.
        #최대공약수 구하기
        num1 = dp[i-1]
        num2 = arr[i]
        
        
        #Greatest Common Divisor 구하기
        #6과 8의 최대공약수
        GCD = get_gcd(num1, num2)
        
        LCM = GCD
        while (True):
            if LCM % num1 != 0 or LCM % num2 != 0: #둘중하나라도 안되면
                LCM += GCD
            else:
                break
        
        dp[i] = LCM
    return dp[len(arr) - 1]
    
            