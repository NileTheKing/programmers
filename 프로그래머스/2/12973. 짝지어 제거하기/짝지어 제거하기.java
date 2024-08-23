import java.util.Stack;
class Solution
{
    public int solution(String s)
    {
        Stack<Character> stack = new Stack<>();
        stack.push(s.charAt(0));
        for(int i = 1; i < s.length(); i++){
            if(!stack.isEmpty() && s.charAt(i) == stack.peek()){
                stack.pop();
            }
            else{
                stack.push(s.charAt(i));
            }
        }
        int answer;
        if(stack.isEmpty())
            answer = 1;
        else answer = 0;
        
        return answer;
    
    }
}