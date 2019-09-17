/*
H
295. Find Median from Data Stream
*/
/*
Two PriorityQueue
A = max heap
B = min heap

在 add 的时候一定要将 num 和两个 heap 都进行比较，之后再来对付数量不符合的移动问题
不然类似 -1，-2，-3 的输入就返回错误
不然类似 1，2，3 的输入就返回错误
Time: 
    addNum = O(logn)
    findMedian = O(1)
Space: O(n)
*/
class MedianFinder {
    PriorityQueue<Integer> maxHeap;//small
    PriorityQueue<Integer> minHeap;//large
    /** initialize your data structure here. */
    public MedianFinder() {
        maxHeap = new PriorityQueue<>(Collections.reverseOrder());
        minHeap = new PriorityQueue<>();
    }
    
    public void addNum(int num) {//无论怎样都要有一个跟之前 median 比较的环节
        maxHeap.offer(num);
        minHeap.offer(maxHeap.poll());
        if(minHeap.size() > maxHeap.size()) maxHeap.offer(minHeap.poll());
    }
    
    public double findMedian() {
        if(maxHeap.size() > minHeap.size()) return maxHeap.peek();
        else return (double) (maxHeap.peek() + minHeap.peek())/2;
    }
}

/**
 * Your MedianFinder object will be instantiated and called as such:
 * MedianFinder obj = new MedianFinder();
 * obj.addNum(num);
 * double param_2 = obj.findMedian();
 */