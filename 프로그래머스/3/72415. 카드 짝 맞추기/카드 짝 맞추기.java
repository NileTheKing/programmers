import java.util.*;
class Solution {
    int M = 4;
    int N = 4;
    int[][] directions = {{1,0},{-1,0},{0,-1},{0,1}};

    public int solution(int[][] board, int r, int c) {
        int goalBit = 0;
        Map<Integer, List<int[]>> cardCoords = new HashMap<>();
        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++) {
                if (board[i][j] == 0) continue;
                goalBit |= 1 << (board[i][j] - 1);
                cardCoords.computeIfAbsent(board[i][j], k -> new ArrayList<>()).add(new int[]{i, j});
            }
        }

        Queue<Info> q = new LinkedList<>();
        q.offer(new Info(r, c, 0, -1, 0));
        boolean[][][][] visited = new boolean[M][N][1 << 6][M * N + 1]; // target: -1~(M*N-1) -> tIdx: 0~(M*N)
        visited[r][c][0][0] = true;

        while (!q.isEmpty()) {
            int size = q.size();
            for (int k = 0; k < size; k++) {
                Info polled = q.poll();

                if (polled.currentBit == goalBit) return polled.cost;

                for (int[] d : directions) {
                    int nr = polled.r + d[0];
                    int nc = polled.c + d[1];
                    if (nr < 0 || nr >= M || nc < 0 || nc >= N) continue;
                    tryOffer(q, visited, nr, nc, polled.currentBit, polled.target, polled.cost + 1);
                }

                for (int[] d : directions) {
                    int nr = polled.r, nc = polled.c;
                    int cr = polled.r + d[0], cc = polled.c + d[1];
                    while (cr >= 0 && cr < M && cc >= 0 && cc < N) {
                        nr = cr; nc = cc;
                        if (!isGone(board, polled.currentBit, cr, cc)) break;
                        cr += d[0]; cc += d[1];
                    }
                    if (nr == polled.r && nc == polled.c) continue;
                    tryOffer(q, visited, nr, nc, polled.currentBit, polled.target, polled.cost + 1);
                }

                if (!isGone(board, polled.currentBit, polled.r, polled.c)) {
                    int cardHere = board[polled.r][polled.c];

                    if (polled.target == -1) {
                        int[] other = otherCoord(cardCoords, cardHere, polled.r, polled.c);
                        int newTarget = other[0] * N + other[1];
                        tryOffer(q, visited, polled.r, polled.c, polled.currentBit, newTarget, polled.cost + 1);
                    } else {
                        int curTargetR = polled.target / N, curTargetC = polled.target % N;
                        if (polled.r == curTargetR && polled.c == curTargetC) {
                            int nextBit = polled.currentBit | (1 << (cardHere - 1));
                            tryOffer(q, visited, polled.r, polled.c, nextBit, -1, polled.cost + 1);
                        } else {
                            tryOffer(q, visited, polled.r, polled.c, polled.currentBit, -1, polled.cost + 1);
                        }
                    }
                }
            }
        }
        return -1;
    }

    int[] otherCoord(Map<Integer, List<int[]>> cardCoords, int cardNum, int r, int c) {
        List<int[]> coords = cardCoords.get(cardNum);
        for (int[] coord : coords) {
            if (!(coord[0] == r && coord[1] == c)) return coord;
        }
        return coords.get(0);
    }

    void tryOffer(Queue<Info> q, boolean[][][][] visited, int nr, int nc, int bit, int target, int cost) {
        int tIdx = target + 1;
        if (visited[nr][nc][bit][tIdx]) return;
        visited[nr][nc][bit][tIdx] = true;
        q.offer(new Info(nr, nc, bit, target, cost));
    }

    boolean isGone(int[][] board, int currentBit, int r, int c) {
        if (board[r][c] == 0) return true;
        return (currentBit & (1 << (board[r][c] - 1))) != 0;
    }

    class Info {
        int r, c, currentBit, target, cost;
        Info(int r, int c, int currentBit, int target, int cost) {
            this.r = r; this.c = c; this.currentBit = currentBit;
            this.target = target; this.cost = cost;
        }
    }
}