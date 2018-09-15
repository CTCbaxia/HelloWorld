/*
MEDIUM
368. Largest Divisible Subset
https://leetcode.com/problems/minimum-genetic-mutation/description/

TIME: 0914 - 3h
RESULT: 30% - 34ms
NOTES:
首先就要排序。
然后借助传递性，找到 dp 的算法

*/
/*
//create a list and make the first element of the list to be the largest one
//and for every element in the list, the element i can be totally divided by the elements behind it. n(0), n(1),..., n(i - 1) % n(i) == 0
//but what if you pick a wrong subset...so make more subset
*/
/*
The question is how to find the Longest subset
for the divisible subset, we know that 
	- every element can divide the last element(sort) because of the transmission of the divisible element
	- every element can be divided by the first element
so for every element A, we loop all elements B that are smaller than it and can divide it
and if the divider has more children, children + 1 > current children, we make it the children-parent link
*** The point is that we don't addup all its dividor(because you don't know whether the dividor can divide each other), instead, we find the link
*** Another point is that we make a link between all parent and children, so in the end we can make the full link
*/


class Solution {
    public List<Integer> largestDivisibleSubset(int[] nums) {
        if(nums == null || nums.length == 0) return new ArrayList<Integer>();
        Arrays.sort(nums);
        int[] dp = new int[nums.length];//the number of children in the link for the element i
        int[] parent = new int[nums.length];//the link relationship
        int maxLen = 1;
        int maxIndex = 0;
        dp[0] = 1;//itself
        for(int i = 1; i < nums.length; i++){//the parent
            dp[i] = 1;
            for(int j = 0; j <= i - 1; j++){//find its children link
                if(nums[i] % nums[j] == 0 && dp[j] + 1 > dp[i]){
                    dp[i] = dp[j] + 1;
                    parent[i] = j;
                }
            }
            if(maxLen < dp[i]){
                maxLen = dp[i];
                maxIndex = i;
            }
        }
        List<Integer> list = new ArrayList<Integer>();
        for(int i = 0; i < maxLen; i++){
            list.add(nums[maxIndex]);
            maxIndex = parent[maxIndex];
        }
        return list;
    }
}




