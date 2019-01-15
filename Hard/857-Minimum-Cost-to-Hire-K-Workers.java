/*
HARD
857. Minimum Cost to Hire K Workers

*/
/*
两层排序：
Array for ratio
PQ for quality
最终希望 group 内部 max ratio * group sum 最小，则可以控制一个最小值 ratio，然后找到这个最小值对应的 group sum 最小

将 worker 按照 ratio 从小到大排列，
对于 K group 里的新 worker，依次计算该 worker ratio 下的最小乘积。
当 ratio 固定，则必须 group sum 最小，则必须在放入新的 worker 之后删除 group 里面 quality 最大的
所以对于 quality 应该选择 PriorityQueue

Time: O(nlogn)
Space: O(n)
*/
class Solution {
    public double mincostToHireWorkers(int[] quality, int[] wage, int K) {
        int n = quality.length;
        worker[] workers = new worker[n];
        for(int i = 0; i < n; i++){
            double r = (double) wage[i] / quality[i];//这里要转化成 double，不然结果会是整数
            workers[i] = new worker(r, quality[i]);
        }
        //sort workers by ratio
        Arrays.sort(workers, new Comparator<worker>(){
            public int compare(worker w1, worker w2){
                return Double.compare(w1.ratio, w2.ratio);
            }
        });
        //maintain the group using pq, sorting by quality
        PriorityQueue<worker> pq = new PriorityQueue<>(new Comparator<worker>(){
            public int compare(worker w1, worker w2){
                return w2.quality - w1.quality;
            }
        });
        
        double result = Double.MAX_VALUE;
        int sum = 0;
        for(worker w : workers){
            pq.offer(w);
            sum += w.quality;
            
            if(pq.size() > K) sum -= pq.poll().quality;//有可能using his ratio without him，但是结果不会被影响
            if(pq.size() == K){
                result = Math.min(result, sum * w.ratio);
            }
        }
        return result;
    }
    
    public class worker{
        double ratio;
        int quality;
        public worker(double r, int q){
            ratio = r;
            quality = q;
        }
    }

}





/*
只有单层排序，思路不对
Time: O(nlogn)
Space: O(K)
*/
class Solution {
    public double mincostToHireWorkers(int[] quality, int[] wage, int K) {
        int n = quality.length;
        PriorityQueue<worker> pq = new PriorityQueue<>(new Comparator<worker>(){
            public int compare(worker w1, worker w2){
                return Double.compare(w2.ratio,w1.ratio);
            }
        });
        
        //calculate the ratio for every worker, find the min group sum
        double preSum = Double.MAX_VALUE;
        int sum = 0;
        for(int i = 0; i < n; i++){
            double r = (double) wage[i] / (double) quality[i];
            worker curW = new worker(r, quality[i]);
            
            if(pq.size() < K){
                pq.offer(curW);
                sum += curW.quality;
                preSum = sum * pq.peek().ratio;
            }else{
                worker preW = pq.poll();
            
                double curSum = (sum + curW.quality - preW.quality) * Math.max(pq.peek().ratio, curW.ratio);

                if(curSum < preSum){
                    pq.offer(curW);
                    sum += curW.quality - preW.quality;
                    preSum = curSum;
                }else pq.offer(preW); 
                
                
            }
        }
        return preSum;
    }
    public class worker{
        double ratio;
        int quality;
        public worker(double _ratio, int _quality){
            ratio = _ratio;
            quality = _quality;
        }
    }
}