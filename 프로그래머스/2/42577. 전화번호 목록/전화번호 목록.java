import java.util.*;
class Solution {
    public boolean solution(String[] phone_book) {
        
        Arrays.sort(phone_book);
        for (int i = 0; i < phone_book.length; i++) {
            String  prefix = phone_book[i];
            for (int j = i + 1; j < phone_book.length; j++) {
                if (phone_book[j].startsWith(prefix)) {
                    return false;
                }
                else {
                    break;
                }
            }
        }
        
        return true;
    }
}