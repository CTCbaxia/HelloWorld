/*
MEDIUM
341. Flatten Nested List Iterator
https://leetcode.com/problems/flatten-nested-list-iterator/description/

TIME: 0724 - 3h
RESULT: 59% - 5ms
METHOD:

NOTES:
1. 这题思路陷在 iterator 里面出不来了。明显是要用一个不需要 iterator 的方式来实现。也即利用 list 特性。

思路：
1. 如果是数字，就直接输出
2. 如果是list，就用 List<Integer> 赋值，然后用 iterator 直至结束
*/
/**
 * // This is the interface that allows for creating nested lists.
 * // You should not implement it, or speculate about its implementation
 * public interface NestedInteger {
 *
 *     // @return true if this NestedInteger holds a single integer, rather than a nested list.
 *     public boolean isInteger();
 *
 *     // @return the single integer that this NestedInteger holds, if it holds a single integer
 *     // Return null if this NestedInteger holds a nested list
 *     public Integer getInteger();
 *
 *     // @return the nested list that this NestedInteger holds, if it holds a nested list
 *     // Return null if this NestedInteger holds a single integer
 *     public List<NestedInteger> getList();
 * }
 */
/*
Stack: 保证最上面的是int

Time: O(n)
Space: O(1)
*/
public class NestedIterator implements Iterator<Integer> {
    private Stack<NestedInteger> stack = new Stack<NestedInteger>();
    
    public NestedIterator(List<NestedInteger> nestedList) {
        for(int i = nestedList.size() - 1; i >= 0; i--){
            stack.push(nestedList.get(i));
        }
    }

    @Override
    public Integer next() {
        return stack.pop().getInteger();
    }

    @Override
    public boolean hasNext() {
        if(stack.size() == 0) return false;
        if(stack.peek().isInteger()) return true;
        else{
        //while the top is not int, while until it is
            while(stack.size() != 0 && !stack.peek().isInteger()){
                List<NestedInteger> tmp = stack.pop().getList();
                if(tmp.size() == 0) continue;
                for(int i = tmp.size() - 1; i >= 0; i--){//把这个list 撸平
                    stack.push(tmp.get(i));
                }
            }
        }
        return stack.size() == 0 ? false:true;
    }

}

/**
 * Your NestedIterator object will be instantiated and called as such:
 * NestedIterator i = new NestedIterator(nestedList);
 * while (i.hasNext()) v[f()] = i.next();
 */