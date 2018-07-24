/*
MEDIUM
284. Peeking Iterator
https://leetcode.com/problems/peeking-iterator/description/

TIME: 0722 - 1h
RESULT: 91% - 62ms
METHOD:

NOTES:
1. 这种改写题的解法：
    - 先看原有的结构有哪些 method
    - 再看 Method 输入端有哪些内容（那个 method 包含 input，一般都是对于 input 做一些改变）
2. 原本iterator中没有peek()方法，即无法取顶元素，现在你给加一个peek方法，next，hasNext功能不变

*/

// Java Iterator interface reference:
// https://docs.oracle.com/javase/8/docs/api/java/util/Iterator.html

class PeekingIterator implements Iterator<Integer> {
    private Iterator<Integer> it;
    private Integer cache;
    public PeekingIterator(Iterator<Integer> iterator) {
        // initialize any member here.
        this.it = iterator;
        if(it.hasNext()) cache = it.next();
        else cache = -100000; 
    }

    // Returns the next element in the iteration without advancing the iterator.
    public Integer peek() {
        return cache;
    }

    // hasNext() and next() should behave the same as in the Iterator interface.
    // Override them if needed.
    @Override
    public Integer next() {
        int tmp = cache;
        if(it.hasNext()) cache = it.next();
        else cache = -100000; 
        return tmp;
    }

    @Override
    public boolean hasNext() {
        if(cache == -100000) return false;
        return true;
    }
}