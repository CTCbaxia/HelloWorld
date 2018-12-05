/*
EASY
198. House Robber

TIME: 
RESULT: 
NOTES:
*/
/*Quora
刚电面的。 一道题：一个array 在1-n之间，找到max sum subset, 数字n 和 n+1不能同时在subset 里面。和这个里面的一样：

http://www.1point3acres.com/bbs/thread-424099-1-1.html

就是dp，和paint house的想法一样。 但后面有三个followup:
1.假如有negative的怎么改
2.有negative并且要PRINT出其中一个结果
3.有negative并且要PRINT出所有的结果
*/
/*
Dynamic Programming
Time:O(n)
Space: O(n)
*/
class Solution {
    public int rob(int[] nums) {
        if(nums.length == 0) return 0;
        int[] dp = new int[nums.length];//dp[i] till element i, the optimal sum
        List<Set<Integer>> subsets = new ArrayList<>();
        for(int i = 0; i < nums.length; i++){
            subsets.add(new HashSet<Integer>());
        }

        for(int i = 0; i < nums.length; i++){
            int choose = nums[i] + (i - 2 >= 0 ? dp[i - 2] : 0);
            int notChoose = (i - 1 >= 0 ? dp[i - 1] : 0);
            if(choose >= notChoose){
                dp[i] = choose;
                if(i - 2 >= 0) subsets.get(i).addAll(subsets.get(i - 2));
                subsets.get(i).add(i);
            }else{
                dp[i] = notChoose;
                if(i - 1 >= 0) subsets.get(i).addAll(subsets.get(i - 1));
            }
        }
        //System.out.println(subsets.get(nums.length - 1));
        return dp[nums.length - 1];
    }
}
//coderPad: 在 coderPad 上面 Set<Integer>[] subsets = new HashSet[nums.length]; 无法成功
import java.io.*;
import java.util.*;


class Solution {
    public static void main(String[] args) {
        //test
        int[] n1 = new int[]{1, 2, 3, 4};
        int[] n2 = new int[]{1, -3, 1, 4, 7, 0};
        int[] n3 = new int[]{-1,-2,-4};
        System.out.println(rob(n1));
        System.out.println(rob(n2));
        System.out.println(rob(n3));
        
    }
    public static Set<Integer> rob(int[] nums) {
        if(nums.length == 0) new HashSet<Integer>();
        int[] dp = new int[nums.length];//dp[i] till element i, the optimal sum
        List<Set<Integer>> subsets = new ArrayList<>();
        for(int i = 0; i < nums.length; i++){
            subsets.add(new HashSet<Integer>());
        }

        for(int i = 0; i < nums.length; i++){
            int choose = nums[i] + (i - 2 >= 0 ? dp[i - 2] : 0);
            int notChoose = (i - 1 >= 0 ? dp[i - 1] : 0);
            if(choose >= notChoose){
                dp[i] = choose;
                if(i - 2 >= 0) subsets.get(i).addAll(subsets.get(i - 2));
                subsets.get(i).add(i);
            }else{
                dp[i] = notChoose;
                if(i - 1 >= 0) subsets.get(i).addAll(subsets.get(i - 1));
            }
        }
        //System.out.println(subsets[nums.length - 1]);
        return subsets.get(nums.length - 1);
    }
}


