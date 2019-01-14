/*
MEDDIUM
621. Task Scheduler

TIME: 1111
RESULT: 
NOTE: 
*/
/*
找到每个 task 的数量，排序。
找到多少个 task 数量均为最高值 -> sameMostFreq
最终结果
1. A B_ _ _ A B _ _ _ A B ---Frame Size 里面可能没放满，但是AB要满足这样的位置  --> 返回 (freq[25] - 1) * (n + 1) + sameMostFreq
2. A B C D F A B C D F A B E R T Y  AB满足关系，但是还有很多散 task，或者频率低一些的，这时候只需要插入就行 --> 返回 tasks.length

Time: O(n) ---sort 只有 26 的数组
Space: O(26) 
*/
class Solution {
    public int leastInterval(char[] tasks, int n) {
        int[] freq = new int[26];
        for(char c : tasks){
            freq[c - 'A']++;
        }
        Arrays.sort(freq);
        int sameMostFreq = 0;
        for(int i : freq){
            if(i == freq[25]) sameMostFreq++;
        }
        return Math.max((freq[25] - 1) * (n + 1) + sameMostFreq, tasks.length);
    }
}

/*
Frequency + PriorityQueue

1. 先得到每个task需要执行的次数
2. 放进 pq
3. 从 pq 弹出还剩执行次数最多的 task，放入 q 中（不超过 n+1) 

Time: O(num) + O(klogk) k = kinds of task
Space: O(26)
*/
class Solution {
    public int leastInterval(char[] tasks, int n) {
        int result = 0;
        int[] count = new int[26];
        PriorityQueue<Integer> pq = new PriorityQueue<Integer>(new Comparator<Integer>(){
            public int compare(Integer i1, Integer i2){
                return i2 - i1;
            }
        });
        //get frequency
        for(char c : tasks){
            count[c - 'A']++;
        }
        //push frequence to pq
        for(char c = 'A'; c <= 'Z'; c++){
            if(count[c - 'A'] > 0){// !!! don't add non-existing task to pq
                pq.offer(count[c - 'A']);
            } 
        }
        //build scheduler
        while(!pq.isEmpty()){
            Queue<Integer> q = new LinkedList<Integer>();
            int time = 0;
            //push into processing queue
            for(int i = 0; i < n + 1; i++){
                if(pq.isEmpty()) break;
                q.offer(pq.poll());
                time++;
            }
            //push back into pq
            while(!q.isEmpty()){
                int remain = q.poll() - 1;
                if(remain > 0) pq.offer(remain);
            }
            //if no other task later, add time to result, else add n + 1 time slot
            result += pq.isEmpty() ? time : n + 1;
        }
        return result;
    }
}


// if task order should be kept and cool down time exists only between same task
// map solution
// space overhead is big if number of task type is huge
class Solution{
    public int leastInterval(char[] tasks, int n){
        int res = 0;//time
        Map<Character, Integer> map = new HashMap<>();
        for(int i = 0; i < tasks.length; i++){
            if(!map.containsKey(tasks[i]) || map.get(tasks[i]) <= res){
                res++;
            }else{
                res = map.get(tasks[i]);
            }
            map.put(tasks[i], res + n + 1);
            System.out.println(res);
        }
        return res;
    }    
}













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
