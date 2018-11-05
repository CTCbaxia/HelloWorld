/*
EASY
339. Nested List Weight Sum

RESULT: 
NOTES: 
*/
/*
DFS
get the result with level known for each nestedlist

Time: O(n)
Space: O(1)
*/
/**没什么用，就告诉你 API
 * // This is the interface that allows for creating nested lists.
 * // You should not implement it, or speculate about its implementation
 * public interface NestedInteger {
 *     // Constructor initializes an empty nested list.
 *     public NestedInteger();
 *
 *     // Constructor initializes a single integer.
 *     public NestedInteger(int value);
 *
 *     // @return true if this NestedInteger holds a single integer, rather than a nested list.
 *     public boolean isInteger();
 *
 *     // @return the single integer that this NestedInteger holds, if it holds a single integer
 *     // Return null if this NestedInteger holds a nested list
 *     public Integer getInteger();
 *
 *     // Set this NestedInteger to hold a single integer.
 *     public void setInteger(int value);
 *
 *     // Set this NestedInteger to hold a nested list and adds a nested integer to it.
 *     public void add(NestedInteger ni);
 *
 *     // @return the nested list that this NestedInteger holds, if it holds a nested list
 *     // Return null if this NestedInteger holds a single integer
 *     public List<NestedInteger> getList();
 * }
 */
class Solution {
    public int depthSum(List<NestedInteger> nestedList) {
        return levelSum(nestedList, 1);
    }
    private int levelSum(List<NestedInteger> list, int level){
        int res = 0;
        for(NestedInteger n : list){
            if(n.isInteger()) res += level * n.getInteger();
            else{
                res += levelSum(n.getList(), level + 1);
            }
        }
        return res;
    }
}