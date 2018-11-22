/*
MEDIUM
636. Exclusive Time of Functions
https://leetcode.com/problems/exclusive-time-of-functions/description/

TIME: 1006 - 1h
RESULT: 41% - 26ms
思路：
类似于 valid parenthesis 的做法。用 stack 来存所有的 start 状态。每次有新的 start 或者 end 就会改变相应的值
*/
/*
类似 parenthesis 
每次用 stack 缓存还没匹配上的部分

Time: O(n)
Space: O(n)
*/
class Solution {
    public int[] exclusiveTime(int n, List<String> logs) {
        int[] result = new int[n];
        if(logs.size() == 0) return result;
        
        Stack<Integer> stack = new Stack<>();
        int preTime = 0;
        for(int i = 0; i < logs.size(); i++){
            String[] parts = logs.get(i).split(":");
            int time = Integer.parseInt(parts[2]);
            int task = Integer.parseInt(parts[0]);
            if(parts[1].equals("start")){
                if(!stack.isEmpty()){
                    result[stack.peek()] += (time - 1) - preTime + 1;
                }
                stack.push(task);
                preTime = time;
            }else if(parts[1].equals("end")){
                if(!stack.isEmpty()){
                    result[stack.pop()] += time - preTime + 1;
                }
                preTime = time + 1;
            }
        }
        return result;
            
    }
}







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