/*
PriorityQueue:
left - maxHeap
right - minHeap

! Tricky 1: 
always make the left pq size == leftSize == k/2
if k is odd, then the median is the first num in right

! Tricky 2: 
need half the number before add them up
((double)left.peek() + (double)right.peek())/2
((double)left.peek()/2 + (double)right.peek()/2)

! Tricky 3: 
PQ maxHeap should be implemented by i2.compareTo(i1)

Time: O(n(logn + n))
Space: O(n)
*/
class Solution {
    public double[] medianSlidingWindow(int[] nums, int k) {
        if(nums == null || nums.length == 0) return null;
        
        PriorityQueue<Integer> right = new PriorityQueue<>();
        PriorityQueue<Integer> left = new PriorityQueue<>(new Comparator<Integer>(){
            public int compare(Integer i1, Integer i2){
                return i2.compareTo(i1);//不能 i2 - i1 因为 Integer.MAX_VALUE, Integer.MIN_VALUE 相关的操作会溢出
            }
        });
        
        int leftSize = k / 2; //if k is odd, the first num in right is the median
        double[] res = new double[nums.length - k + 1];
        //initialization
        for(int i = 0; i < k; i++){
            left.offer(nums[i]);
        } 
        balancePQ(left, right, leftSize);
        res[0] = k == leftSize * 2 ? ((double)left.peek() + (double)right.peek())/2 : right.peek();
        
        //move the sliding window
        for(int i = k; i < nums.length; i++){
            addNum(left, right, res[i - k], nums[i]);
            removeNum(left, right, res[i - k], nums[i - k]);
            balancePQ(left, right, leftSize);
            res[i - k + 1] = k == leftSize * 2 ? ((double)left.peek() + (double)right.peek())/2 : right.peek();
        }
        return res;
    }
    private void addNum(PriorityQueue<Integer> left, PriorityQueue<Integer> right, double median, int num){
        if(num < median) left.offer(num);
        else right.offer(num);
    }
    
    private void removeNum(PriorityQueue<Integer> left, PriorityQueue<Integer> right, double median, int num){
        if(num < median) left.remove(num);
        else right.remove(num);
    }
    
    private void balancePQ(PriorityQueue<Integer> left, PriorityQueue<Integer> right, int leftSize){
        while(left.size() > leftSize){
            right.offer(left.poll());
        }
        if(left.size() < leftSize){
            left.offer(right.poll());
        }
    }
    
}



/*
TreeMap -- 写不出来

*/
class Solution {
    public double[] medianSlidingWindow(int[] nums, int k) {
        TreeMap<Integer, Integer> map = new TreeMap<>();
        for(int i = 0; i < k; i++){
            map.put(nums[i], map.getOrDefault(nums[i], 0) + 1);
        }
        //get the median
        double[] res = new double[nums.length - k + 1];
        if(k % 2 != 0){
            int median = getMedianOdd(map, k);
            res[0] = median;
            
            int right = k, left = 1;
            while(right < nums.length){
                if(nums[right] > median && nums[left] <= median){
                    median = moveOneRight(map, median);
                }else if(nums[right] < median && nums[left] >= median){
                    median = moveOneLeft(map, median);
                }
                res[left] = median;
                    
                if(map.get(nums[left]) > 1) map.put(nums[left], map.get(nums[left]) - 1);
                else map.remove(nums[left]);
                
                map.put(nums[right], map.getOrDefault(nums[right], 0) + 1);
                
                right++;
                left++;
            }
        }else{
            int[] median = getMedianEven(map, k);
            int medianL = median[0], medianR = median[1];
            res[0] = (double)(medianL + medianR)/2;
            
            int right = k, left = 1;
            while(right < nums.length){
                if(nums[right] > median && nums[left] <= median){
                    medianL = medianR;
                    medianR = moveOneRight(map, medianR);
                }else if(nums[right] < median && nums[left] >= median){
                    medianR = medianL;
                    medianL = moveOneLeft(map, medianL);
                }
                res[left] = (double)(medianL + medianR)/2;
                
                if(map.get(nums[left]) > 1) map.put(nums[left], map.get(nums[left]) - 1);
                else map.remove(nums[left]);
                
                map.put(nums[right], map.getOrDefault(nums[right], 0) + 1);
                
                right++;
                left++;
            }
        }
        return res;
    }
    private int moveOneRight(TreeMap<Integer, Integer> map, int key){
        
    }
    private int moveOneLeft(TreeMap<Integer, Integer> map, int key){
        
    }
    private int getMedianOdd(TreeMap<Integer, Integer> map, int k){
        int num = (k - 1) / 2;
        int preKey = Integer.MIN_VALUE;
        while(num > 0){
            int curKey = map.ceilingKey(preKey);
            num -= map.getOrDefualt(preKey, 0);
            preKey = curKey;
        }
        if(num == 0) return map.higherKey(preKey);//find the next one
        else return preKey;//find the last one
    }
    private int[] getMedianEven(TreeMap<Integer, Integer> map, int k){
        int num = k / 2;
        int preKey = Integer.MIN_VALUE;
        while(num > 0){
            int curKey = map.ceilingKey(preKey);
            num -= map.getOrDefualt(preKey, 0);
            preKey = curKey;
        }
        if(num == 0) return new int[]{preKey, map.higherKey(preKey)};
        else return new int[]{preKey, preKey};
    }
}
