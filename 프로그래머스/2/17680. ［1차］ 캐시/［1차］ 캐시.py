import collections
def solution(cacheSize, cities):
    answer = 0
    if cacheSize == 0:
        return len(cities) * 5
    cache = collections.deque()
    
    for city in cities:
        city_lower = city.lower()
        
        if city_lower in cache: #cache hit
            cache.remove(city_lower)
            cache.appendleft(city_lower)
            answer += 1
        else : #cache miss
            if len(cache) >= cacheSize: #공간이 없음
                cache.pop()
            cache.appendleft(city_lower)
            answer += 5
            
    return answer