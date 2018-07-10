/*
621. Task Scheduler
https://leetcode.com/problems/task-scheduler/description/

TIME: 0709
RESULT: 40%, 57ms
NOTE: 
analyse the question before start coding 
if you find the magic, it can be far more easy to write a routine code, but being able to quickly write a routine code is the basic skill
*/
class Solution {
    public int leastInterval(char[] tasks, int n) {
        int[] task_num = new int[26];
        int count_type = 0;
        List<Character> task_type = new ArrayList<Character>();
        int result = 0;
        //get task_num: {0,0,0,0,2,3,4}
        for(int i = 0; i < tasks.length; i++){
            if(!task_type.contains(tasks[i])){
                task_type.add(tasks[i]);
                task_num[count_type] = 1;
                count_type++;
            }else{
                int tmp = 0;
                for(int j : task_type){
                    if(j == tasks[i]){
                        task_num[tmp]++;
                    }
                    tmp++;
                }
            }
        }
        Arrays.sort(task_num);//升序排列
        int same_task = 0;
        while(task_num[25 - same_task] == task_num[25]){
            same_task++;
        }
        result = Math.max(tasks.length, (task_num[25] - 1)*(n + 1) + same_task);
        return result;

    }
}
