/*
HARD
123. Best Time to Buy and Sell Stock III

*/
/*
Dynamic Programming
buy[i]: the highest money we can get in buy state for i day 
(buy state means there is one hold in hand now)
sell[i]: the highest money we can get in sell state for i day
(sell state means there is NO hold in hand now)

buy[i] = Math.max(buy[i - 1], sell[i - 1] - prices[i])
sell[i] = Math.max(sell[i - 1], buy[i - 1] + prices[i])

buy and sell 都有两种状态，每次都更新这四个值

Time: O(n)
Space: O(1)
*/
class Solution {
    public int maxProfit(int[] prices) {
        if(prices.length == 0) return 0;
        int buy1 = Integer.MIN_VALUE, sell1 = 0; 
        int buy2 = Integer.MIN_VALUE, sell2 = 0;
        for(int i = 0; i < prices.length; i++){
            sell2 = Math.max(sell2, buy2 + prices[i]);
            buy2 = Math.max(buy2, sell1 - prices[i]);
            sell1 = Math.max(sell1, buy1 + prices[i]);
            buy1 = Math.max(buy1, -prices[i]);
            //System.out.println(sell2 + " "+buy2 + " "+sell1+ " "+buy1);
        }
        return sell2;
    }
}










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