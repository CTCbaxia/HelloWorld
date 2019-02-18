/*
EASY
628. Maximum Product of Three Numbers

*/
/*
PriorityQueue -- 其实就是找 Kth largest number

Time: O(n)
Space: O(1)
*/
class Solution {
    public int maximumProduct(int[] nums) {
        PriorityQueue<Integer> large = new PriorityQueue<>();
        
        PriorityQueue<Integer> small = new PriorityQueue<>(new Comparator<Integer>(){
            public int compare(Integer i1, Integer i2){
                return i2 - i1;
            }
        });//要把大的弹出
        
        for(int n : nums){
            large.offer(n);
            if(large.size() > 3) large.poll();
            
            small.offer(n);
            if(small.size() > 2) small.poll();
        }
        
        int p1 = 1, p2 = 1;
        while(large.size() > 1) p1 *= large.poll();
        while(!small.isEmpty()) p2 *= small.poll();
        int largest = large.poll();//最大的最后一个弹出来
        
        return Math.max(p1 * largest, p2 * largest);
    }
}



/*
找到五个对应值 = 对应排序后最右边三个值，最左边两个值

Time: O(n)
Space: O(1)
*/
class Solution {
    public int maximumProduct(int[] nums) {
        int n1 = Integer.MAX_VALUE, n2 = Integer.MAX_VALUE;
        int n3 = Integer.MIN_VALUE, n4 = Integer.MIN_VALUE, n5 = Integer.MIN_VALUE;
        for(int n : nums){
            if(n > n5){//find rightmost 3 large
                n3 = n4;
                n4 = n5;
                n5 = n;
            }else if(n > n4){
                n3 = n4;
                n4 = n;
            }else if(n > n3){
                n3 = n;
            }
            
            if(n < n1){//find leftmost 2 small
                n2 = n1;
                n1 = n;
            }else if(n < n2){
                n2 = n;
            }
        }
        
        return Math.max(n3 * n4 * n5, n1 * n2 * n5);
    }
}




/*
找到五个对应值 = 对应排序后最右边三个值，最左边两个值

Time: O(n)
Space: O(1)
*/
class Solution {
    public int maximumProduct(int[] nums) {
        int n1 = Integer.MAX_VALUE, n2 = Integer.MAX_VALUE;
        int n3 = Integer.MIN_VALUE, n4 = Integer.MIN_VALUE, n5 = Integer.MIN_VALUE;
        for(int n : nums){
            if(n > n5){
                n3 = n4;
                n4 = n5;
                n5 = n;
            }else if(n > n4){
                n3 = n4;
                n4 = n;
            }else if(n > n3){
                n3 = n;
            }
        }
        for(int n : nums){
            if(n < n1){
                n2 = n1;
                n1 = n;
            }else if(n < n2){
                n2 = n;
            }
        }
        int p1 = n3 * n4 * n5;
        int p2 = n1 * n2 * n5;
        return Math.max(p1, p2);
    }
}

/*
简化，其实就两种情况
Time: O(nlogn)
Space: O(1)
*/
class Solution {
    public int maximumProduct(int[] nums) {
        Arrays.sort(nums);
        int n = nums.length;
        int p1 = nums[n - 1] * nums[n - 2] * nums[n - 3];
        int p2 = nums[0] * nums[1] * nums[n - 1];
        return Math.max(p1, p2);
    }
}
/*
分情况讨论

Time: O(nlogn)
Space: O(1)
*/
class Solution {
    public int maximumProduct(int[] nums) {
        Arrays.sort(nums);
        int n = nums.length;
        int product = 0;
        if(nums[0] >= 0 || nums[n - 1] <= 0){//取最右边三个
            product = nums[n - 1] * nums[n - 2] * nums[n - 3];
        }else{
            
            //3 right
            int p1 = nums[n - 1] * nums[n - 2] * nums[n - 3];
            //2*left, 1 right
            int p2 = nums[0] * nums[1] * nums[n - 1];
            product = Math.max(p1, p2);
            
        }
        return product;
    }
}