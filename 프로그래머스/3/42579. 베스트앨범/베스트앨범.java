import java.util.*;
class Solution {
    public int[] solution(String[] genres, int[] plays) {
        List<Integer> ans = new ArrayList<>();
        HashMap<String, Integer> genrePlays = new HashMap<>();
        HashMap<String, HashMap<Integer, Integer>> music = new HashMap<>();
        //장르, <고유번호, 재생횟수>
        
        //장르별로 재생횟수 구하기
        for(int i = 0; i < genres.length; i++) {
            genrePlays.put(genres[i], genrePlays.getOrDefault(genres[i], 0) + plays[i]);
        }
        
        //장르별로 노래들 추가하기. 장르를 키로 노래를 정리
        for (int i = 0; i < genres.length; i++) {
            music.computeIfAbsent(genres[i], k -> new HashMap<>())
                .put(i, plays[i]);
        }
        
        //장르정렬
        List<String> keys = new ArrayList(genrePlays.keySet());
        Collections.sort(keys, (s1, s2) -> genrePlays.get(s2) - genrePlays.get(s1));//내림차순
        
        //앨범 속 장르 내에서 재생횟수별로 수록. 안된다면 고유번호가 낮은순으로 수록.[]
        for (String key : keys) {
            HashMap<Integer, Integer> map = music.get(key);//장르에 접근해서 장르의 노래들목록(해시구조. 키-밸류)를 얻음
            List<Integer> genreKey = new ArrayList(map.keySet());//장르안의 노래의 고유번호목록을 뽑아옴
            
            Collections.sort(genreKey, (s1, s2) -> map.get(s2) != map.get(s1) ? map.get(s2) - map.get(s1) : Integer.compare(s1, s2));
            
            ans.add(genreKey.get(0));
            if (genreKey.size() > 1) {
                ans.add(genreKey.get(1));
            }
        }
        
        return ans.stream().mapToInt(i->i).toArray();
    }
}
/**
장르1 30
장르2 50

앨범1 장르1(노래1, 노래2)
앨범2 장르2(노래1, 노래2)
*/