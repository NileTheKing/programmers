import java.util.*;
class Solution {
    
    public int solution(int[][] game_board, int[][] table) {
        int rows = game_board.length;
        int cols = game_board[0].length;
        boolean[][] visitedTable = new boolean[rows][cols];
        List<List<int[]>> shapes = new ArrayList<>();
        List<List<int[]>> emptySpaces = new ArrayList<>();
        
        // Find shapes from table
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (table[i][j] == 1 && !visitedTable[i][j]) {
                    List<int[]> shape = bfs(table, visitedTable, new int[] {i, j}, 1);
                    if (!shape.isEmpty()) {
                        shapes.add(normalizeShape(shape));
                    }
                }
            }
        }
        
        // Create a deep copy of game_board to avoid modifying the original
        int[][] gameboardCopy = new int[rows][cols];
        for (int i = 0; i < rows; i++) {
            gameboardCopy[i] = game_board[i].clone();
        }
        
        boolean[][] visitedBoard = new boolean[rows][cols];
        
        // Find empty spaces from game_board
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (gameboardCopy[i][j] == 0 && !visitedBoard[i][j]) {
                    List<int[]> space = bfs(gameboardCopy, visitedBoard, new int[] {i, j}, 0);
                    if (!space.isEmpty()) {
                        emptySpaces.add(normalizeShape(space));
                    }
                }
            }
        }
        
        // Match shapes with empty spaces
        int answer = 0;
        boolean[] used = new boolean[emptySpaces.size()];
        
        for (List<int[]> shape : shapes) {
            boolean matched = false;
            
            // Try all possible rotations
            List<List<int[]>> rotations = getAllRotations(shape);
            
            for (int i = 0; i < emptySpaces.size(); i++) {
                if (used[i]) continue;
                List<int[]> emptySpace = emptySpaces.get(i);
                
                // Skip if sizes don't match
                if (shape.size() != emptySpace.size()) continue;
                
                // Try each rotation
                for (List<int[]> rotatedShape : rotations) {
                    if (isShapeEqual(rotatedShape, emptySpace)) {
                        matched = true;
                        used[i] = true;
                        answer += shape.size();
                        break;
                    }
                }
                
                if (matched) break;
            }
        }
        
        return answer;
    }
    
    // BFS to find connected blocks
    public List<int[]> bfs(int[][] board, boolean[][] visited, int[] start, int target) {
        int[][] directions = {{-1, 0}, {1, 0}, {0, 1}, {0, -1}};
        int rows = board.length, cols = board[0].length;
        Queue<int[]> queue = new LinkedList<>();
        List<int[]> shape = new ArrayList<>();
        
        if (board[start[0]][start[1]] != target) return shape;
        
        queue.offer(start);
        visited[start[0]][start[1]] = true;
        
        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            shape.add(new int[] {current[0], current[1]});
            
            for (int[] dir : directions) {
                int nextR = current[0] + dir[0];
                int nextC = current[1] + dir[1];
                
                if (nextR < 0 || nextR >= rows || nextC < 0 || nextC >= cols) continue;
                if (visited[nextR][nextC] || board[nextR][nextC] != target) continue;
                
                visited[nextR][nextC] = true;
                queue.offer(new int[] {nextR, nextC});
            }
        }
        
        return shape;
    }
    
    // Normalize shape coordinates relative to top-left corner
    public List<int[]> normalizeShape(List<int[]> shape) {
        // Find the top-left corner
        int minRow = Integer.MAX_VALUE;
        int minCol = Integer.MAX_VALUE;
        
        for (int[] point : shape) {
            minRow = Math.min(minRow, point[0]);
            minCol = Math.min(minCol, point[1]);
        }
        
        // Normalize all points relative to top-left corner
        List<int[]> normalized = new ArrayList<>();
        for (int[] point : shape) {
            normalized.add(new int[] {point[0] - minRow, point[1] - minCol});
        }
        
        // Sort for consistent comparison
        Collections.sort(normalized, (a, b) -> {
            if (a[0] == b[0]) return Integer.compare(a[1], b[1]);
            return Integer.compare(a[0], b[0]);
        });
        
        return normalized;
    }
    
    // Get all possible rotations of a shape (0°, 90°, 180°, 270°)
    public List<List<int[]>> getAllRotations(List<int[]> shape) {
        List<List<int[]>> rotations = new ArrayList<>();
        List<int[]> currentRotation = shape;
        
        // Add all 4 rotations
        for (int i = 0; i < 4; i++) {
            rotations.add(currentRotation);
            currentRotation = rotateClockwise(currentRotation);
        }
        
        return rotations;
    }
    
    // Rotate shape 90 degrees clockwise
    public List<int[]> rotateClockwise(List<int[]> shape) {
        List<int[]> rotated = new ArrayList<>();
        
        // 90 degrees clockwise: (r, c) -> (c, -r)
        for (int[] point : shape) {
            rotated.add(new int[] {point[1], -point[0]});
        }
        
        return normalizeShape(rotated);
    }
    
    // Check if two shapes are equal (same points)
    public boolean isShapeEqual(List<int[]> shape1, List<int[]> shape2) {
        if (shape1.size() != shape2.size()) return false;
        
        for (int i = 0; i < shape1.size(); i++) {
            if (!Arrays.equals(shape1.get(i), shape2.get(i))) {
                return false;
            }
        }
        
        return true;
    }
}
/**
돌리는 함수 구현
greedy? backtracking?

필요없이 그냥 되는거 넣으면 됨 순서대로 확인해서. 뭐먼지 넣는지는 상관이 전혀 없다.

이게 bfs랑 무슨 관련이 있을가요
table에 있는 도형은 따로 알려주질 않아서 직접 인식시켜야함.
table을 순회하며 bfs로 블럭 하나를 직접 인식해야함
    순회하면서 visited확인해놓고 상하좌우 연결되어있으면 거기로 
    bfs든 dfs든 해서 도형위치를 기억해놓는 기능이 필요
    bfs로 가자.

그리고 회전해서 모양을 변환시키는 기능이 필요
좌표를 바탕으로 어떻게 상대적인 위치를 표현하나? 그것은 점하나를 기준으로 상대위치를 표시해야함
그리고 회전기능으로 상대위치를 기억시켜야함.
맨윗칸 맨좌측칸 기준으로 상대위치를 표시해서 담아두고 회전으로 상대위치를 바꾸는 로직을 구현해야함
그렇담 table을 2중 for loop으로 iterate하면서 처음만난 점을 기억해두고 bfs로 하면서 상대위치를 다 구해놓으면 됨. 순서는 상관없고 그냥 기록하면 됨.
*/