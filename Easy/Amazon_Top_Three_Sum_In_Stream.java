/*
给你一个iterator, iterator 动态输入 数字 比如 【1，2，0，4，5，2】； 你写个function 能够及时返回 top 3 value's sum
*/
class topThreeSum{
    PriorityQueue<Integer> pq;
    int sum;
    public topThreeSum(){
        pq = new PriorityQueue<>();
        sum = 0;
    }
    public void put(int num){
        if(pq.size() < 3){
            pq.offer(num);
            sum += num;
        }else{
            pq.offer(num);
            sum += num - pq.poll();
        }
    }
    public int get(){
        return sum;
    }
}