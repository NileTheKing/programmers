class Solution {
    public int solution(String name) {
        int alphabet = 0;
        int length = name.length();
        int cursor = length;
        
        for (int i = 0; i < length; i++) {
            int current = Math.min(name.charAt(i) - 'A', 
                                  'Z' - name.charAt(i) + 1);
            alphabet += current;
            
            int next = i + 1;
            while (next < length && name.charAt(next) == 'A') {
                next++;
            }
            
            cursor = Math.min(cursor, Math.min(i + i + length - next, length - next + length - next + i ));
        }
        return alphabet + cursor;
    }
}