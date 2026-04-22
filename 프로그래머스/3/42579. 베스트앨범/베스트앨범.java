import java.util.*;
class Solution {
    public int[] solution(String[] genres, int[] plays) {
        //일단 장르별 정복 ㅓ필요함. 자료구조
        //그리고 장르마다 노래들 정보가 필요함.인덱스를 들고다니며 ㄴ되겠따.
        
        //원하는거는 장르별로 노래2개씩하는거
        //장르 노래2개는 조회수순으로 고름. 조회수같으ㅓ면재생수
        //그리고 장르의 순서는 전체조회수
        Map<String, Integer> genreMap = new HashMap<>();
        Map<String, PriorityQueue<Integer>> genreSong = new HashMap<>(); //장르별 해당노래인덱스. 조회수내림차순 고유번호 오름차순
        for (int i = 0; i < genres.length; i++) {
            genreMap.put(genres[i], genreMap.getOrDefault(genres[i], 0) + plays[i]);//장르별 조회수
            genreSong.computeIfAbsent(genres[i], k -> new PriorityQueue<>(
                (o1, o2) -> plays[o1] == plays[o2] ? o1 - o2 : plays[o2] - plays[o1]
            )).offer(i);
        }
        //이제 장르 우선부터 
        List<String> genreKeyset = new ArrayList<>(genreMap.keySet());
        genreKeyset.sort((o1, o2) -> genreMap.get(o2) - genreMap.get(o1));
        List<Integer> ans = new ArrayList<>();
        for (String g : genreKeyset) {
            //이제 장르별로 완성하면된다
            PriorityQueue<Integer> pq = genreSong.get(g);//이제 pq얻음
            //1개 또는 2개꺼내면됨
            if (pq == null) continue;
            int size = pq.size();
            if (size == 1) {
                ans.add(pq.poll());
            }else {
                ans.add(pq.poll());
                ans.add(pq.poll());
            }
        }
        return ans.stream().mapToInt(i -> i).toArray();
        
    }
}