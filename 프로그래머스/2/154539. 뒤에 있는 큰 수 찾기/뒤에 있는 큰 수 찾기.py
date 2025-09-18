def solution(numbers):
    answer = [-1] * len(numbers)
    stack = []
    stack.append(0)
    
    for i in range(1, len(numbers)):
        while stack and numbers[stack[-1]] < numbers[i]:
            popped = stack.pop()
            answer[popped] = numbers[i]
        stack.append(i)
    return answer

'''
O(N^2) 하면 10^12
10^8보다 한참

모노스택 써서 푸는거같다.
모노스택의 탑에는 가장 큰게 와있어야하나?
or 가장 작은게 와있어야 하나?
    직관적으로는 가장 큰게 와있어야 할거같은데 생각을 해보자
    
'''