import java.util.*;
class Solution {
    public int[] solution(String[] genres, int[] plays) {
        ArrayList<Integer> ans = new ArrayList<>();
        
        HashMap<String, Integer> genre = new HashMap<>();//장르별 총재생횟수 저장
        HashMap<String, HashMap<Integer, Integer>> music = new HashMap<>();//장르에 있는 노래(고유번호) 및 재생횟수
        
        for (int i = 0; i < plays.length; i++) {
            if (!genre.containsKey(genres[i])) {
                HashMap<Integer, Integer> map = new HashMap<>();
                map.put(i, plays[i]);//고유번호와 재생횟수를 담음(노래정보)
                music.put(genres[i], map);
                genre.put(genres[i], plays[i]);//없으니까 장르추가
            } else {
                //music에 노래추가
                //장르에 갯수업데이트
                genre.put(genres[i], genre.get(genres[i]) + plays[i]);//기존에다가 더하기
                music.get(genres[i]).put(i, plays[i]);
            }
        }
        
        //이제 장르별, 우선순위(재생횟수, 고유번호)로 정렬
        List<String> keySet = new ArrayList(genre.keySet());
        Collections.sort(keySet, (s1, s2) -> genre.get(s2) - genre.get(s1));//내림차순
        
        for (String key : keySet) {//키셋은 장르이름들
            HashMap<Integer, Integer> map = music.get(key);//장르에 접근해서 장르의 노래들목록(해시구조. 키-밸류)를 얻음
            List<Integer> genreKey = new ArrayList(map.keySet());//장르안의 노래의 고유번호목록을 뽑아옴
            
            Collections.sort(genreKey, (s1, s2) -> map.get(s2) - map.get(s1));
            //장르안에서 노래목록을 정렬. 재생목록을 map에서 읽어서 정렬
            
            ans.add(genreKey.get(0));
            if (genreKey.size() > 1) {
                ans.add(genreKey.get(1));
            }
        }
        
        return ans.stream().mapToInt(i -> i).toArray();
        
    }
}