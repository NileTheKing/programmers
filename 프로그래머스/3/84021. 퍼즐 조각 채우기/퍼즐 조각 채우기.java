import java.util.*;
class Solution {
    public int solution(int[][] game_board, int[][] table) {
        int cnt = 0;
        int r = table.length;
        int c = table[0].length;
        List<List<int[]>> shapes = new ArrayList<>();//table에서
        List<List<int[]>> empty = new ArrayList<>();
        
        //bfs하면서 퍼즐 및 빈 공간찾기. 절대좌표 이용
        boolean[][] table_visited = new boolean[r][c];
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                if (table_visited[i][j] || table[i][j] == 0) continue;
                shapes.add(bfs(i,j, table_visited, table, 1));
            }
        }
        boolean[][] board_visited = new boolean[r][c];
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                if (board_visited[i][j] || game_board[i][j] == 1) continue;
                empty.add(bfs(i,j, board_visited, game_board, 0));
            }
        }
        
        //모양을 순회하며 모양을 회전한것들과 game_board의 빈칸 hash에 있는지 확인. 있으면 모양만큼카운트하고 해당 모양세트 제거
        

        for (int i=0; i<empty.size(); i++) {
            List<int[]> hole = normalize(empty.get(i));
            boolean matched = false;
            for (int j=0; j<shapes.size(); j++) {
                for (List<int[]> rotated : getFour(shapes.get(j))) {
                    if (isSame(rotated, hole)) {
                        cnt += hole.size();
                        shapes.remove(j);
                        empty.remove(i);   // ← 여기에 hole 제거 추가
                        i--;               // 인덱스 보정
                        matched = true;
                        break;
                    }
                }
                if (matched) break;
            }

        }
    return cnt;   
    }
    
    
    public List<int[]> bfs(int r, int c, boolean[][] visited,
                           int[][] table, int target) {
        Queue<int[]> q = new LinkedList<>();
        int[][] directions = {{-1,0}, {1,0}, {0, 1}, {0, -1}};
        int rLim = table.length;
        int lLim = table[0].length;
        List<int[]> list = new ArrayList<>();
        //System.out.println("bfs called");
        
        q.offer(new int[] {r, c});
        visited[r][c] = true;
        list.add(new int[] {0, 0});
        while (!q.isEmpty()) {
            int size = q.size();
            for (int i = 0; i < size; i++) {
                int[] polled = q.poll();   
                for (int[] direction : directions) {
                    int newX = polled[0] + direction[0];
                    int newY = polled[1] + direction[1];
                    
                    if (newX < 0 || newX >= rLim || newY < 0 || newY >= lLim) continue;
                    if (visited[newX][newY]) continue;
                    
                    if (table[newX][newY] == target) {
                        visited[newX][newY] = true;
                        list.add(new int[] {newX - r, newY - c});
                        q.offer(new int[] {newX, newY});
                    }
                    
                }
            }
        }
        
        return normalize(list); //정렬
    }
    public List<List<int[]>> getFour(List<int[]> shape) {
        List<List<int[]>> four = new ArrayList<>();
        List<int[]> currentRotation = shape;
        for (int i = 0; i < 4; i++) {
            four.add(currentRotation);
            currentRotation = rotate(currentRotation);
        }
        
        return four;
        
    }
    public List<int[]> normalize(List<int[]> input) {
        List<int[]> normalized = new ArrayList<>();
        int minR = Integer.MAX_VALUE;
        int minC = Integer.MAX_VALUE;
        
        for (int[] coord : input) {
            minR = Math.min(coord[0], minR);
            minC = Math.min(coord[1], minC);
        }
        
        for (int[] coord : input) {
            normalized.add(new int[] {coord[0] - minR, coord[1] - minC});
        }
        normalized.sort((a,b) -> a[0]!=b[0]? a[0]-b[0] : a[1]-b[1]);
        return normalized;
    }
    
    public List<int[]> rotate(List<int[]> input) {
        List<int[]> added = new ArrayList<>();
        for (int[] coord : input) {
            added.add(new int[] {coord[1], -coord[0]});
        }
        
        return normalize(added);
    }
    public boolean isSame(List<int[]> shape, List<int[]> empty) {
        
        int sizeS = shape.size();
        int sizeE = empty.size();
        
        if (sizeS != sizeE) return false;
        
        for (int i = 0; i < sizeS; i++) {
            if (shape.get(i)[0] != empty.get(i)[0] ||
               shape.get(i)[1] != empty.get(i)[1]) {
                return false;
            }
        }
        
        return true;
        
    }
}
/**
-회전 가능
-끼워 넣은 조각의 주변은 비어있을 수 없음
    = 무조건 현재 모양의(회전 포함) 모양과 동일해야 삽입 가능

Q: 넣을 수 있으면 무조건 넣는 게 맞나?
A: yes

로직
    table 순회하면서 조각 정보를 담은 조각 정보를 담기 shapes(Set<List<Integer>> or List<List<Integer>>)
    game_board를 순회하면서(left top corner)
    회전로직을 돌리며 isFittable로 해당 칸에 넣을 수 있는지 확인
        빈칸 목록을 따로 만들어놓고 shapes랑 회전하면서 넣을 수 있는지 확인
    넣을 수 있다면 현재 순회중인 shape의 칸 갯수를 추가
*/