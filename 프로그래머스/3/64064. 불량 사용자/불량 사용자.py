import re
def solution(user_id, banned_id):
    possibilities = []
    for ban in banned_id:
        regex_pattern = ban.replace('*', '.')
        matches = []
        for i, user in enumerate(user_id):
            if re.fullmatch(regex_pattern, user):
                matches.append(i)
        possibilities.append(matches)
        
    final_combinations=  set()
        
    def backtrack(ban_idx, mask):
        if ban_idx == len(banned_id):
            final_combinations.add(mask)
            return
        
        for user_index in possibilities[ban_idx]:
            if (mask & (1 << user_index)) != 0:               
                continue
            backtrack(ban_idx + 1, mask | (1 << user_index))
        
    backtrack(0, 0)
    return len(final_combinations)
    