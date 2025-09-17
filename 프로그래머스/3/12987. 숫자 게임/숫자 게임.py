def solution(A, B):
    A.sort()
    B.sort()
    
    aLeft = 0
    aRight = len(A) - 1
    bLeft = 0
    bRight = len(B) - 1
    cnt = 0
    
    for i in range(len(A)):
        #A의 가장 큰 애를 B의 가장 작은애가 이길 수 있는지
        if A[aRight] < B[bLeft]:
            aRight -= 1
            bLeft += 1
            cnt += 1
        #A의 가장 작은 애를 B의 가장 작은 애가 이길 수 있는지
        elif A[aLeft] < B[bLeft]:
            aLeft += 1
            bLeft += 1
            cnt += 1
        #A의 A의 가장 큰 애를 B의 가장 큰 애가 이길 수 있는지 < 이건 손해임.안해야해
        else:
            aRight -= 1
            bLeft += 1
    return cnt
    
    # 1 3 5 7
    # 2 2 6 8
    
    # 3 5 7 
    # 2 6 8
    
    
        