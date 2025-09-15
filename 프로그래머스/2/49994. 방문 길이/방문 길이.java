import java.util.*;
class Solution {
    int coordX = 0;
    int coordY = 0;
    public int solution(String dirs) {
        Set<Path> walked = new HashSet<>();//{0,1,2,3} -> 0,1에서 2,3을 걸어봄.
        int cnt = 0;
        for (char d : dirs.toCharArray()) {
            int preX = coordX;
            int preY = coordY;
            move(d);
            int postX = coordX;
            int postY = coordY;
            //System.out.printf("%d, %d -> %d, %d\n", preX,preY,postX,postY);
            //움직였고 처음 가본 길이라면
            if (preX == postX && preY == postY) continue; //안움직였으니까 패스
            Path path1 = new Path(preX, preY, postX, postY);
            Path path2 = new Path(postX, postY, preX, preY);
            if (!walked.contains(path1) && !walked.contains(path2)) {
                cnt++;
            }
            walked.add(path1);
        }
        return cnt;

    }
    
    public void move(char d) {
        //System.out.printf("%d,%d, moving %c\n", coordX, coordY, d);
        switch (d) {
            case 'U':
                if (isAvailable(coordX, coordY + 1)) coordY++;
                break;
            case 'R':
                if (isAvailable(coordX + 1, coordY)) coordX++;
                break;
            case 'D':
                if (isAvailable(coordX, coordY -1)) coordY--;
                break;
            case 'L':
                if (isAvailable(coordX - 1,  coordY)) coordX--;
                break;
        }
    }
    public boolean isAvailable(int x,int y) {
        //System.out.printf("checking %d, %d\n", x,y);
        if (x > 5 || x < -5 || y < -5 || y > 5) {
            //System.out.printf("not available at %d, %d\n", x, y);
            return false;
        }
        else return true;
    }
    class Path {
        int preX, preY, postX, postY;

        public Path(int preX, int preY, int postX, int postY) {
            this.preX = preX;
            this.preY = preY;
            this.postX = postX;
            this.postY = postY;
        }

        // 2. 내용이 같으면 true를 반환하도록 equals 재정의
        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Path path = (Path) o;
            return preX == path.preX && preY == path.preY && postX == path.postX && postY == path.postY;
        }

        // 3. 내용이 같으면 같은 해시코드를 반환하도록 hashCode 재정의
        @Override
        public int hashCode() {
            return Objects.hash(preX, preY, postX, postY);
        }
    }
    
}