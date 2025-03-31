import java.util.*;
class Solution {
    public int[] solution(String[] genres, int[] plays) {
        List<Integer> answer = new ArrayList<>();
        Map<String, Integer> playMap = new HashMap<>(); //장르명, 플레이수
        Map<String, Map<Integer, Integer>> biggerMap = new HashMap<>();
        //장르, 고유번호, 플레이수
        
        //플레이리스트 
        for (int i = 0; i < genres.length; i++) {
            playMap.put(genres[i], playMap.getOrDefault(genres[i],0) + plays[i]);
        }
        
        //<장르, <고유번호, 재생목록>> 
        for (int i = 0; i < genres.length; i++) {
            biggerMap.computeIfAbsent(genres[i], v -> new HashMap<>())
                .put(i, plays[i]);
        }
        
        //장르별 플레이리스트 별로 정렬
        List<String> genresKeys = new ArrayList(playMap.keySet());
        Collections.sort(genresKeys, (s1, s2) -> playMap.get(s2) - playMap.get(s1));//내림차순
        
        //정렬된 키별로 biggerMaps를 순회하면서 담아야함
        for (String genreKey : genresKeys) {
            Map<Integer, Integer> genreSongs = biggerMap.get(genreKey);//특정장르의 모든 노래들. <고유번호, 재생횟수>
            List<Integer> sortedSongsByPlay = new ArrayList(genreSongs.keySet());//장르의 고유번호들
            
            Collections.sort(sortedSongsByPlay, (s1, s2) -> 
                             genreSongs.get(s1) != genreSongs.get(s2) ? 
                             genreSongs.get(s2) - genreSongs.get(s1) :
                             Integer.compare(s1, s2));
            
            //이제 장르별안에서 재생목록, 고유번호로 정렬된거를 순회하면서 담기
            answer.add(sortedSongsByPlay.get(0));
            if (sortedSongsByPlay.size() >= 2) {
                answer.add(sortedSongsByPlay.get(1));
            }
        }
        
        
        return answer.stream().mapToInt(i -> i).toArray();
    
    }
}
/**

노래0 클래식,500
노래1 팝, 600
노래2 클래식, 150
노래3 클래식 800
노래4 팝 2500

클래식: 노래0, 노래2,노래3. 500+150+800 = 1450
팝:노래1, 노래4. 600+2500 = 3100

팝먼저 수록
팝에서는 노래4 노래1으로 수록. 조회수 다르지만 같다? 고유번호 비교
팝에서 2개, 클래식에서2개 -> [4,1,3,0]
*/