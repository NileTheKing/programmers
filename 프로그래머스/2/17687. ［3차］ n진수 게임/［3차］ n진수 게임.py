def solution(n, t, m, p):
    ans = ''
    total = t * m #구하는 숫자중 가장 큰 수

    def transform(number, n):
        if number == 0:
            return "0"
        notation = "0123456789ABCDEF"
        result = ""
        while number > 0:
            
            remainder = number % n
            number //= n
            result = notation[remainder] + result
        return result
    game = ""
    number = 0
    while len(game) < total:
        game += transform(number, n)
        number += 1
    answer = ""
    for i in range(t):
        index_tube = (i * m + p) - 1
        answer += game[index_tube]
    return answer
    
                
#     def transform(number, n):
        
#         string = ''
#         while number > 0:
            
#             remainder = number % n
#             number //= n
#             string = str(remainder) + string
#         return string
            
            
        

'''
일단 현재 세는 숫자를 카운팅하고
누구 차례인지도 카운팅해야함
또 문제는, 여러 자릿수 숫자는 한글자씩 끊어 말한다는 것.
규칙을 찾냐 시뮬레이션을 돌리냐

자기가 말할 숫자(십진법)을 안다면 차례를 계산해서 그것만 알고있으면 되지
굳이 전체 진행을 알 필요 없이.
만약만약 한글자씩 끊어말하지 않았다면 다음 숫자 구하는건 턴수만큼 더한 숫자임
근데근데 한글자씩 끊어말하는 망할 룰때문에..근데 만약 끊어지는거를 계산해서 다음 숫자가 얼마나 밀리는지 계산이 가능하다면?
전혀 문제없지..진행시켜
>사실 잘 못하겠다 숫자 작으니 일단 시뮬레이션으로 풀어라

자신으 ㅣ순서가 중요하다. 이를 반영시키려면
이거를 1바퀴 2바퀴 식으로 진행해야한다
16진법 1바퀴면 m명 참가 기준 16/m의 개의 숫자를 1바퀴에 진행한다.
그런데 망할 진행되는 숫자중에 여러 자리가 있으면 여기에 차질이 생긴다.
(0) (1) (1 0) (1 1) (1 0 0) (1 0 1) (1 1 0) (1 1 1) ...
0 1 2 3 4 5 6 7 8 9 A B C D E F 10 11 12 13 14 15 16 17 18 19 1A 1B 1C 1D 1E 1F 20 21....
'''