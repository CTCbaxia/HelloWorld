/*
HARD
123. Best Time to Buy and Sell Stock III

TIME: 
RESULT: 
*/
//没做出来
class Solution {
    public class point{
        int end;
        int start;
        int profit;
        public point(int _end, int _start, int _profit){
            end = _end;
            start = _start;
            profit = _profit;
        }
    }
    public int maxProfit(int[] prices) {
        PriorityQueue<point> pq = new PriorityQueue<point>(new Comparator<point>(){
            public int compare(point p1, point p2){
                return p2.profit - p1.profit;
            }
        });
        
        int min = Integer.MAX_VALUE;
        int index = 0;
        for(int i = 0; i < prices.length; i++){
            if(min >= prices[i]){// = to keep the index the latest one, 减少限制
                min = prices[i];
                index = i;
            }else{
                int profit = prices[i] - min;
                pq.offer(new point(i, index, profit));
            }
        }
        
        
        int res = 0;
        if(pq.isEmpty()) return res;
        
        point p = pq.poll();
        res += p.profit;
        int n = 1;

        while(n > 0 && !pq.isEmpty()){
            point p2 = pq.poll();
            if(p.start >= p2.end || p.end <= p2.start){
                res += p2.profit;
                n--;
            }
        }
        return res;
    }
}