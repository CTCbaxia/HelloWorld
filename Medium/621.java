/*
621. Task Scheduler
https://leetcode.com/problems/task-scheduler/description/
*/

class Solution {

    public int leastInterval(char[] tasks, int n) {
        int[][] todolist = new int[26][2];
        int count_type = 0;
        int tasks_left = tasks.length;
        List<Character> task_type = new ArrayList<Character>();
        List<Integer> scheduler = new ArrayList<Integer>();
        List<List<Character>> subset = new ArrayList<List<Character>>();

        //get todolist: [[A,2],[B,2]]
        for(int i = 0; i < tasks.length; i++){
            if(!task_type.contains(tasks[i])){
                task_type.add(tasks[i]);
                todolist[count_type][0] = tasks[i];
                todolist[count_type][1] = 1;
                count_type++;
            }else{
                for(int j = 0; j < count_type; j++){
                    if(todolist[j][0] == tasks[i]){
                        todolist[j][1] ++;
                    }
                }
            }
            
        }
        //get schedule
        int N = 0;
        int current = 0;// currently do

        for(int i = 0; i <= n; i++){
            subset.add(task_type);
            
        }
        while(tasks_left > 0){
            int most_num = todolist[0][1];
            int most_task = 0;
            
            for(int i = 1; i < task_type.size(); i++){// choose the task with most work to do
                if(todolist[i][1] > most_num){
                    most_num = todolist[i][1];
                    most_task = i;
                }
            }

            scheduler.add(todolist[most_task][0]);//do A
            todolist[most_task][1]--;
            tasks_left--;
            if(todolist[most_task][1] == 0){//if the all of this task type is finished
                task_type.remove(todolist[most_task][0]);
            }
            
            for(int i = 1; i <= n; i++){//from current + 1 ~ current + N, remove the most_task
                subset.get(current + i).remove(todolist[most_task][0]);
                
            }
            subset.add(task_type);
            current++;
            
        }
        return scheduler.size();
    }
}
