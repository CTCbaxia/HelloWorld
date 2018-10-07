/*
MEDIUM
636. Exclusive Time of Functions
https://leetcode.com/problems/exclusive-time-of-functions/description/

TIME: 1006 - 1h
RESULT: 41% - 26ms
思路：
类似于 valid parenthesis 的做法。用 stack 来存所有的 start 状态。每次有新的 start 或者 end 就会改变相应的值
*/
class Solution {
    public int[] exclusiveTime(int n, List<String> logs) {
        Stack<Integer> stack = new Stack<Integer>();
        int[] result = new int[n];
        int preTime = 0;
        for(String log : logs){
            String[] parts = log.split(":");

            if(parts[1].equals("start")){
                if(!stack.isEmpty()){
                    result[stack.peek()] += Integer.parseInt(parts[2]) - preTime;
                }
                stack.push(Integer.parseInt(parts[0]));
                preTime = Integer.parseInt(parts[2]);
            }else{
                if(!stack.isEmpty()){
                    result[stack.pop()] += Integer.parseInt(parts[2]) - preTime + 1;
                }
                preTime = Integer.parseInt(parts[2]) + 1;
            }
        }
        return result;
    }
}



class Solution {
    public int[] exclusiveTime(int n, List<String> logs) {
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        Stack<String[]> starts = new Stack<String[]>();
        for(int i = 0; i < logs.size(); i++){
            String[] parts = logs.get(i).split(":");
            if(parts[1].equals("start")){

                if(!starts.isEmpty()){
                    String[] pre = starts.peek();
                    int func = Integer.parseInt(pre[0]);
                    map.put(func, map.getOrDefault(func, 0) + Integer.parseInt(parts[2]) - Integer.parseInt(pre[2]));
                }
                starts.push(parts);
                System.out.println(map);
            }
            else if(parts[1].equals("end")){
                String[] pre = starts.pop();
                int func = Integer.parseInt(parts[0]);
                map.put(func, map.getOrDefault(func, 0) + Integer.parseInt(parts[2]) - Integer.parseInt(pre[2]) + 1);
                if(!starts.isEmpty()){
                    String[] tmp = starts.pop();
                    tmp[2] = String.valueOf(Integer.parseInt(parts[2]) + 1);
                    starts.push(tmp);
                }
            }
        }
        int[] result = new int[n];
        for(int i = 0; i < n; i++){
            result[i] = map.get(i);
        }
        return result;
    }
}