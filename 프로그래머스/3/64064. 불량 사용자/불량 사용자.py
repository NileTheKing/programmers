import re 
def solution(user_id, banned_id):
    banToUser = []
    for ban in banned_id:
        reg = ban.replace('*', '.')
        matches = []
        for i, user in enumerate(user_id):
            if re.fullmatch(reg, user):
                matches.append(i)
        banToUser.append(matches)
        
    final_combinations = set()
    def helper(mask, idx):
        if idx == len(banToUser):
            final_combinations.add(mask)
            return
            
        userlists = banToUser[idx]
        for userIdx in userlists:
            if mask & (1 << userIdx): continue
            helper(mask | (1 << userIdx), idx + 1)
        
    helper(0,0)
    return len(final_combinations)

"""
우선 불량사용자에 해당할 수 있는 자료구조를 매핑한다(리스트)
그다음에 모든 경우의 수를 봐야하므로 조합을 해야한다
조합을 하려면 백트래킹을 해야한다
백트래킹하면서 유저아이디 사용여부(조합에 사용되었는지)를 확인한다. 이떄 비트마스킹을 사용한다
백트래킹의 종료조건은 매핑한 리스트의 엔딩에 도달하면이다. 이떄 set에 마스킹정보를 확인해서 경우의수를 확인한다.
"""