import java.util.*;
import java.io.*;

public class Main {
    static int[][] directions = {{0,1},{1,0},{0,-1},{-1,0}};//우하좌상 시계방향.
    static boolean[][] map;
    static int N;
    static class Point {
        int r,c;
        Point (int r, int c) {
            this.r = r;
            this.c = c;
        }

    }
    public static void main(String[] args) throws Exception {
        BufferedReader br  = new BufferedReader(new InputStreamReader(System.in));
        N  = Integer.parseInt(br.readLine());
        int K  = Integer.parseInt(br.readLine());
        map = new boolean[N][N];
        StringTokenizer st;
        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            map[r-1][c-1] = true; //사과마킹.0indexed로 전환
        }
        //명령어 저장
        List<String> commands = new ArrayList<>();
        st = new StringTokenizer(br.readLine());
        int lines = Integer.parseInt(st.nextToken());
        for (int i = 0; i < lines; i++) {
            String cmd = br.readLine();
            commands.add(cmd);

        }
        int res = solve(commands);
        System.out.println(res);
        
    }
    static int solve(List<String> commands) {
        Deque<Point> snake = new ArrayDeque<>();
        snake.offerFirst(new Point(0,0));//머리추가
        int prevDir = 0; //
        int dir = 0;//현재오른쪽
        boolean[][] isBody = new boolean[N][N];
        isBody[0][0] = true;
        int time = 0;
        for (String c : commands) {
            String[] parts = c.split(" ");
            int nextTime = Integer.parseInt(parts[0]);
            char dirCmd = parts[1].charAt(0);
            int nextDirIdx = dirCmd == 'D' ? (dir + 1) % 4 : (dir + 3) % 4; 
            int seconds = nextTime - time;
            for (int i = 0; i < seconds; i++) {
                time++;
                Point head = snake.peekFirst();//머리.오래된선출.
                int nr = head.r + directions[dir][0];
                int nc = head.c + directions[dir][1];
                //종료조건
                    //벽
                if (nr < 0 || nr >= N || nc < 0 || nc >= N) return time;
                    //몸통
                if (isBody[nr][nc]) return time;
                
                //머리 추가
                snake.offerFirst(new Point(nr, nc));
                isBody[nr][nc]= true;
                //사과있으면 꼬리 안자르고 없으면 자름
                if (!map[nr][nc]) //꼬리자름
                {
                    Point tail = snake.pollLast();
                    isBody[tail.r][tail.c] = false;
                }
                if (map[nr][nc]) map[nr][nc] = false;
            }
            //이제 이동할차례..
            dir = nextDirIdx;
        }

        while (true) {
            time++;
            Point head = snake.peekFirst();//머리.오래된선출.
                int nr = head.r + directions[dir][0];
                int nc = head.c + directions[dir][1];
                //종료조건
                    //벽
                if (nr < 0 || nr >= N || nc < 0 || nc >= N) return time;
                    //몸통
                if (isBody[nr][nc]) return time;
                
                //머리 추가
                snake.offerFirst(new Point(nr, nc));
                isBody[nr][nc] = true;
                //사과있으면 꼬리 안자르고 없으면 자름
                if (!map[nr][nc]) //꼬리자름
                {
                    Point tail = snake.pollLast();
                    isBody[tail.r][tail.c] = false;
                }
                if (map[nr][nc]) map[nr][nc] = false;

        }
    }
    
}