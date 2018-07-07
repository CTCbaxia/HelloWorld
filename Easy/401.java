/*
401. Binary Watch
https://leetcode.com/problems/binary-watch/description/

TIME: 0707
RESULT: 92%, 3ms
Notes: 注意传参
*/

class Solution {
    public List<String> readBinaryWatch(int num) {
        int[] hour_all = {8,4,2,1};
        int[] minute_all = {32,16,8,4,2,1};
        List<String> watchTime = new ArrayList<String>();
        
        if(num > 10 ){
            return watchTime;
        }else if(num == 0){
            watchTime.add("0:00");
            return watchTime;
        }
        
        for(int i = 0; i <= num; i++){
            int hour_num = i;
            int minute_num = num - i;
            List<String> hour = new ArrayList<String>();
            List<String> minute = new ArrayList<String>();

            if(hour_num <= 3 && minute_num <= 5){
                int pre_hour = 0;
                int pre_minute = 0;

                chooseLED(0, hour_all, hour_num, pre_hour, hour);
                chooseLED(0, minute_all, minute_num, pre_minute, minute);

            }else{
                continue;
            }
            
            System.out.println("i:"+i);
            System.out.println("hour: "+hour);
            System.out.println("minute "+minute);
            for(int j = 0; j < hour.size(); j++){
                for(int k = 0; k < minute.size(); k++){
                    String s = hour.get(j) + ":" + minute.get(k);
                    watchTime.add(s);
                }
            }
        }
        return watchTime;
    }
    
    
    private void chooseLED(int start, int[] all, int leds, int pre, List<String> time){
        if(leds > 0){
            for(int i = start; i < all.length; i++){
                chooseLED(i + 1, all, leds - 1, pre + all[i], time);
            }
        
        }else{
            
            if(all.length == 6){
                if(pre < 10) {
                    time.add("0" + Integer.toString(pre));//对于分钟的 0 要换成 00
                }else if(pre < 60){
                    time.add(Integer.toString(pre));
                }else{
                    return;
                }
            }else if(all.length == 4){
                if(pre <= 11){
                    time.add(Integer.toString(pre));
                }else{
                    return;
                }
                
            }
        }

        
        return;
    }
}