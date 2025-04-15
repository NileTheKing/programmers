class Solution {
    public int solution(String name) {
        
        int length =  name.length();
        int updown = 0;
        int leftright = length - 1; //최소 이동해야할 횟수
        
        //알파벳 바꿔야하는 갯수
        for (int i = 0; i < length; i++) {
            updown += Math.min('Z' - name.charAt(i) + 1, name.charAt(i) - 'A');
        }
        
        for (int i = 0; i < length; i++) {
            int next = i + 1;// A들을 건너뛰고 고칠 다음 문자 인덱스
            while (next < length && name.charAt(next) == 'A') {
                next++;
            }
            leftright = Math.min(leftright , 
                                  Math.min(i + i + length - next,
                                          (length - next) * 2 + i));
            
        }
        return updown + leftright;
    }
}