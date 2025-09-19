def solution(n, k):
    
    def transform(number):
        res = ""
        while number > 0:
            res = str(number % k) + res
            number //= k
        return res
    def isPrime(number):
        if number < 2: return False
        for i in range(2,int(number ** 0.5) + 1):
            if number % i == 0:
                return False
        return True
 
    transformed = transform(n)
    splited = transformed.split('0')
    cnt = 0
    for s in splited:
        if s != '' and isPrime(int(s)): cnt += 1
    return cnt
        

'''
1. 진수변환 ->string

2. string을 split한다.
3. split한거를 소수인지 판별한다.
'''