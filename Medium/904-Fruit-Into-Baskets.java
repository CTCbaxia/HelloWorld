/*
MEDIUM
904. Fruit Into Baskets
问题转化为求一个最长连续subarray，使得里面的元素最多只有两种

*/
/*
2. Sliding Window

Time: O(n)
Space: O（2)
*/
class Solution {
    public int totalFruit(int[] tree) {
        int result = 0;
        int start = 0, end = 0;
        Map<Integer, Integer> freqMap = new HashMap<>();

        //find longest subarray
        for(; end < tree.length; end++){
            freqMap.put(tree[end], freqMap.getOrDefault(tree[end], 0) + 1);
            
            while(freqMap.size() > 2){
                freqMap.put(tree[start], freqMap.get(tree[start]) - 1);
                if(freqMap.get(tree[start]) == 0) freqMap.remove(tree[start]);
                start++;
            }
            result = Math.max(result, end - start + 1);
        }
        return result;
    }
}
/*
2. Sliding Window

Time: O(n)
Space: O（key)
*/
class Solution {
    public int totalFruit(int[] tree) {
        int result = 0;
        int start = 0, end = 0, count = 0;
        Map<Integer, Integer> freqMap = new HashMap<>();
        
        //build freqMap
        for(int i = 0; i < tree.length; i++){
            if(!freqMap.containsKey(tree[i])) freqMap.put(tree[i], 0);
        }
        
        //find longest subarray
        for(; end < tree.length; end++){
            freqMap.put(tree[end], freqMap.get(tree[end]) + 1);
            if(freqMap.get(tree[end]) == 1) count++;
            
            while(count > 2){
                freqMap.put(tree[start], freqMap.get(tree[start]) - 1);
                if(freqMap.get(tree[start]) == 0) count--;
                start++;
            }
            result = Math.max(result, end - start + 1);
        }
        return result;
    }
}
/*
1. 伪 Dynamic Programming 

Time: Worst Case: O(n^2), Best Case: O(n) - DP可以使用的情况
Space: O(n)

*/
class Solution {
    public int totalFruit(int[] tree) {
        int len = tree.length;
        Set<Integer>[] set = new HashSet[len];//一开始创建的都是 null
        int[] max = new int[len];
        int result = 0;
        
        for(int i = len - 1; i >= 0; i--){
            if(i == len - 1 || (set[i + 1].size() >= 2 && !set[i + 1].contains(tree[i]))){
                //need to count
                countFruit(max, set, tree, i);
            }else{
                max[i] = max[i + 1] + 1;
                set[i] = new HashSet<Integer>(set[i + 1]);
                if(!set[i + 1].contains(tree[i])) set[i].add(tree[i]);
            }
            result = Math.max(result, max[i]);
        }
        
        return result;
    }
    private void countFruit(int[] max, Set<Integer>[] set, int[] tree, int index){
        set[index] = new HashSet<Integer>();
        for(int i = index; i < tree.length; i++){
            if(!set[index].contains(tree[i])){
                if(set[index].size() >= 2) return;
                else{
                    set[index].add(tree[i]);
                    max[index] = i - index + 1;
                } 
            }else{
                max[index] = i - index + 1;
            }
        }
        return;
    }
}