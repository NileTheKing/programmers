class Solution {
    public String solution(String video_len, String pos, String op_start, String op_end, String[] commands) {
        
        //편의를 위해 초로 환산
        int video_len_sec = toSeconds(video_len);
        int op_start_sec = toSeconds(op_start);
        int op_end_sec = toSeconds(op_end);
        int pos_sec = toSeconds(pos);

        for (String cmd : commands) {
            //오프닝 스킵 체크
            //System.out.printf("current pos: %d\n", pos_sec);
            if (pos_sec >= op_start_sec && pos_sec <= op_end_sec) { //현재위치가 스킵시간이라면 스킵
                //System.out.printf("skipping at: %d \n", pos_sec);
                pos_sec = op_end_sec;
            }
            //-1초나 도영ㅇ상길이 넘어가는 거는 prev,next에서 처리.
            if (cmd.equals("prev")) { //이전으로 이동
                if (pos_sec - 10 <= 0) {
                    pos_sec = 0;
                }else {
                    pos_sec -= 10; //10초 뒤로 이동
                }
            }else{ //이동으로 이동 처리
                if (pos_sec + 10 >= video_len_sec) {
                    pos_sec = video_len_sec; //초과하므로 끝
                }else {
                    pos_sec += 10; //10초 뒤로
                }
            }
        }
        if (pos_sec >= op_start_sec && pos_sec <= op_end_sec) { //현재위치가 스킵시간이라면 스킵
                //System.out.printf("skipping at: %d \n", pos_sec);
                pos_sec = op_end_sec;
            }
        //현재 시간을 "mm:ss" 형식으로 변환
        //13:00이라면 현재 13*60인 780일거임.
        //780 / 60인 13을 mm에넣고
        //나머지 0을 00으로 변환해서 ss에 넣음.
        StringBuilder ans = new StringBuilder();
        int minute = pos_sec / 60;
        int sec = pos_sec % 60;
        //정수를 string으로..... 배열에 담아서 읽기? 흠
        if(minute < 10) {
            ans.append("0");
            ans.append(String.valueOf(minute));
        }else {
            ans.append(String.valueOf(minute));
        }
        ans.append(":");
        if(sec < 10) {
            ans.append("0");
            ans.append(String.valueOf(sec));
        }else {
            ans.append(String.valueOf(sec));
        }
        return ans.toString();


    }
    public int toSeconds(String time) { //분을 해당 시간으로 변환
        int videoMinuteLength;
        int videoSecondLength;
        videoMinuteLength = Integer.parseInt(time.substring(0,2)); //비디오 길이 분
        videoSecondLength = Integer.parseInt(time.substring(3,time.length())); //비디오 길이 초
        return videoMinuteLength * 60 + videoSecondLength;
    }
}
/**
시뮬레이션
일단 그냥 다 초로 바꾼다음에 분, 초로 다시 환산하는게 더 나을듯
*/