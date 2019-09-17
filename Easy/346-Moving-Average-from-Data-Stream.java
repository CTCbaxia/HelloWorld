/*
EASY
346. Moving Average from Data Stream

RESULT: 
NOTES: 
*/
/*
Queue<Integer>

Time: O(n)
Space: O(size)

can also do with circular array
*/
class MovingAverage {
    Queue<Integer> q;
    int sum;
    int windowSize;
    /** Initialize your data structure here. */
    public MovingAverage(int size) {
        sum = 0;
        q = new LinkedList<>();
        windowSize = size;
    }
    
    public double next(int val) {
        sum += val;
        q.offer(val);
        
        if(q.size() > windowSize){
            sum -= q.poll();
        }
        return (double) sum / q.size();
    }
}

/**
 * Your MovingAverage object will be instantiated and called as such:
 * MovingAverage obj = new MovingAverage(size);
 * double param_1 = obj.next(val);
 */


 
/*
Window + pointer
data: the window of sum
index: inserted index
sum: 
n: divisor (1, 2, ..., size)
*/
class MovingAverage {
    int[] data;
    int index, n;
    int sum;
    /** Initialize your data structure here. */
    public MovingAverage(int size) {
        this.data = new int[size];
        index = 0;
        n = 0;
        sum = 0;
    }
    
    public double next(int val) {
        if(n < data.length) n++;
        sum += val;
        sum -= data[index];
        data[index] = val;
        index = (index + 1) % data.length;
        return (double) sum / n;
    }
}

class MovingAverage {
    Queue<Integer> q;
    int size;
    /** Initialize your data structure here. */
    public MovingAverage(int size) {
        this.q = new LinkedList<Integer>();
        this.size = size;
    }
    
    public double next(int val) {
        q.offer(val);
        if(q.size() > size) q.poll();
        Iterator<Integer> it = q.iterator();
        
        double sum = 0;
        while(it.hasNext()){
            sum += it.next();
        }
        return sum / q.size();
    }
}

/**
 * Your MovingAverage object will be instantiated and called as such:
 * MovingAverage obj = new MovingAverage(size);
 * double param_1 = obj.next(val);
 */