class Solution {
    public int[] solution(String[] park, String[] routes) {
        
        int[] current = new int[2];
        for (int i = 0; i < park.length; i++) { //현재 위치 구하기(시작지점)
            for (int j = 0; j < park[i].length(); j++) {
                if (park[i].charAt(j) == 'S') {
                    current[0] = i;
                    current[1] = j;
                }
            }
        }
        
        for (String s : routes) {
            move(park, current, s);
            //System.out.println("current[0], current[1]" + current[0] + "," + current[1]);
        }
        
        return current;
        
    }
    public void move(String[] park, int[] current, String command) {
        
        int[] currentcpy = new int[2];
        currentcpy[0] = current[0];
        currentcpy[1] = current[1];
        //System.out.println("move called"); OK
        char direction = command.charAt(0);
        //System.out.println("direction:" + direction); OK
        int steps = command.charAt(2) - '0';
        int[] vector = new int[2];
        
        if (direction == 'N') {
            vector[0] = -1;
            vector[1] = 0;
        }else if(direction == 'E') {
        
            vector[0] = 0;
            vector[1] = 1;
        }else if (direction == 'S') {
            
            vector[0] = 1;
            vector[1] = 0;
        }else if (direction == 'W') {
            
            vector[0] = 0;
            vector[1] = -1;
        }
        
        //System.out.println("vector:" + vector[0] + vector[1]); OK
        
        //System.out.println("steps: " + steps);
    
        for (int i = 0; i < steps; i++) {
            int nx = current[0] + vector[0];
            int ny = current[1] + vector[1];
            if (isValid(park, new int[] {nx, ny})) {
                current[0] = nx;
                current[1] = ny;
            }else { //불가능한 명령어 하나라도있으면 무시 초기화
                current[0] = currentcpy[0];
                current[1] = currentcpy[1];
                //System.out.print("invalidMove ");
                //System.out.println(nx + "," + ny);
                return;
            }
        }
        //System.out.println("current[0], current[1]" + current[0] + "," + current[1]);
        
        
    }
    public boolean isValid(String[] park, int[] coord) {
        //벗어나는지
        if (coord[0] >= park.length || coord[0] < 0 || coord[1] >= park[0].length() || coord[1] < 0) return false;
        
        //장애물인지
        if (park[coord[0]].charAt(coord[1]) == 'X') return false;
        
        return true;
        
    }
}/**
일단 시작지점 메모
그러고 명령어 수행
    명령어 수행하면서 현재지점이랑 명령어를 매개변수로 받아서 이동가능한지 확인
    업데이트
    
    최종리ㅣ턴
*/