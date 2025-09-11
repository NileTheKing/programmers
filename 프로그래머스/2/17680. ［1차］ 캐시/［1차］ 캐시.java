import java.util.*;
class Solution {
    public int solution(int cacheSize, String[] cities) {
        Cache cache = new Cache(cacheSize);
        int cnt = 0;
        for (String city : cities) {
            if (cache.get(city)) cnt += 1;
            else cnt += 5;
        }
        return cnt;
    }
    public class Cache {
        int capacity;
        Node head;//제일 최근
        Node tail;//제일 덜
        Map<String, Node> map;
        Cache (int capacity) {
            this.capacity = capacity;
            map = new HashMap<>();
            head = new Node("");
            tail = new Node("");
            head.next = tail;
            tail.prev = head;
        }
        //노드 추가(맨앞에)
        public void insert(Node node) {
            Node og = head.next;
            head.next = node;
            node.prev = head;
            node.next = og;
            og.prev = node;
        }
        //노드 삭제(중간도)
        public void remove(Node node) {
            Node prev = node.prev;
            Node next = node.next;
            prev.next = next;
            next.prev = prev;
        }
        //조회
        public boolean get(String city) {
            if (capacity == 0) {
                return false;
            }
            city = city.toLowerCase();
            if (map.containsKey(city)) {//cahce hit
                Node node = map.get(city); // 
                remove(node);
                insert(node); // 
                return true;
            } else { //miss
                if (map.size() >= capacity) { //miss인데 공간 없으면
                    //지우고 새로운거 넣음
                    Node lru = tail.prev;
                    remove(lru);
                    map.remove(lru.value);
                    Node node = new Node(city);
                    insert(node);
                    map.put(city, node);
                } else { //miss인데 공간있으면
                    Node node = new Node(city);
                    insert(node);
                    map.put(city, node);
                }
                return false;
            }
        }
    }
    public class Node {
        String value;
        Node next;
        Node prev;
        Node (String value) {
            this.value = value;
        }
    }
}
/**
시나리오
용량이 남아있으면
    새로운 거면 추가
        추가하고 insert
    아니면 insert
용량이 안남아 있으면
    캐시에 있다면 insert
    캐시에 없다면
        lru제거(remove 및 map에서 제거) 현재거 insert, 맵삽입
*/