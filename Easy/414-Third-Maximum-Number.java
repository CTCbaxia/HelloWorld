/*
414. Third Maximum Number
*/
/*
method is not modifiable
Three marker + check duplicate
在无法预测极值是否等于会出现的时候，不要用 Integer.MIN_VALUE 这种东西

tips: using Integer and initialization = null

Time: O(n)
Space: O(1)

fail:[1,-2147483648,2]
*/
class Solution {
    public int thirdMax(int[] nums) {
        Integer first = null, second = null, third = null;
        for(Integer num : nums){//should be Integer
            if(num.equals(first) || num.equals(second) || num.equals(third)) continue;//should compare this way
            
            if(first == null || num > first){
                third = second;
                second = first;
                first = num;
            }else if(second == null || (num > second && num < first)){
                third = second;
                second = num;
            }else if(third == null || (num > third && num < second)){
                third = num;
            } 
        }
        return third == null ? first:third;
    }
}
/*
PriorityQueue + Set


Time: O(n)
Space: O(1)

*/
class Solution {
    public int thirdMax(int[] nums) {
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        Set<Integer> set = new HashSet<>();
        
        for(int num : nums){
            if(set.contains(num)) continue;
            pq.offer(num);
            set.add(num);
            
            if(pq.size() > 3) set.remove(pq.poll());
        }
        if(pq.size() == 3){
            return pq.poll();
        }else{
            while(pq.size() > 1) pq.poll();
            return pq.poll();
        }
    }
}





/*
list + insert


Time: O(n)
Space: O(1)

*/
class Solution {
    public int thirdMax(int[] nums) {
        List<Integer> list = new ArrayList<>();
        for(int num : nums){//should be Integer
            if(list.size() > 0 && list.get(0) == num) continue;
            if(list.size() > 1 && list.get(1) == num) continue; 
            if(list.size() > 2 && list.get(2) == num) continue;
            
            if(list.size() == 0 || num > list.get(0)){
                list.add(0, num);
            }else if(list.size() == 1 || (num > list.get(1) && num < list.get(0))){
                list.add(1, num);
            }else if(list.size() == 2 || (num > list.get(2) && num < list.get(1))){
                list.add(2, num);
            } 
            if(list.size() == 4) list.remove(3);
        }
        return list.size() == 3 ? list.get(2) :list.get(0);
    }
}


/*
❌initialization is problematic
Three marker + check duplicate
Time: O(n)
Space: O(1)

fail:[1,-2147483648,2]
*/

class Solution {
    public int thirdMax(int[] nums) {
        int first = Integer.MIN_VALUE, second = Integer.MIN_VALUE, third = Integer.MIN_VALUE;//对于可能有 Integer.MIN_VALUE 值的情况，不要用这个来初始化
        int  count = 0;
        for(int num : nums){
            
            if(num > first){
                third = second;
                second = first;
                first = num;
                count++;
            }else if(num > second && num != first){
                third = second;
                second = num;
                count++;
            }else if(num >= third && num != first && num != second){//for [1,-2147483648]
                third = num;
                count++;
            } 
        }
        return count >= 3 ? third : first;
    }
}
