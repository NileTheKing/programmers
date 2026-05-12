import java.util.*;
class Solution {
    public String solution(String m, String[] musicinfos) {
        int maxLength = Integer.MIN_VALUE;
        String rep_m = replaceSharp(m);
        // System.out.printf("%s\n", rep_m);
        String found = "(None)";
        for (String mu : musicinfos) {
            //순회하면서 만들기
            String[] parts = mu.split(",");
            String[] startParts = parts[0].split(":");
            String[] endParts = parts[1].split(":");
            String name = parts[2];
            String melody = replaceSharp(parts[3]);
            
            int startInt = 60 * Integer.parseInt(startParts[0]) +  Integer.parseInt(startParts[1]);
            int endInt = 60 * Integer.parseInt(endParts[0]) + Integer.parseInt(endParts[1]);
            int diffMin = endInt - startInt;//재생시간
            //3분이면 3번순회
            StringBuilder sb = new StringBuilder();
            int melodyLen = melody.length();
            for (int i = 0; i < diffMin; i++) {
                sb.append(melody.charAt(i % melodyLen));
            }
            // System.out.println("===debug===");
            // System.out.printf("start end melodysb : (%d %d %s)\n", startInt, endInt, sb.toString());
            Outter : for (int i = 0; i <= sb.length() - rep_m.length(); i++) {
                for (int j = 0; j < rep_m.length(); j++) {
                    if (sb.charAt(i + j) != rep_m.charAt(j)) continue Outter;
                }
                if (diffMin > maxLength) {
                    found = name;
                    maxLength = diffMin;
                }
            }
            
            
        }
        return found;
    }
    public String replaceSharp(String s) {
        return s.replaceAll("A#", "a")
            .replaceAll("B#", "b")
            .replaceAll("C#","c")
            .replaceAll("D#","d")
            .replaceAll("E#","e")
            .replaceAll("F#","f")
            .replaceAll("G#","g");
    }
}