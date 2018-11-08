/*
MEDIUM
281. Zigzag Iterator

TIME: 
RESULT: 
NOTES:

*/
/*
Iterator + kind of BFS:
if the iterator hasNext, we put it into queue
everytime we poll first, and offer back if it continuously have next (then it will join the queue)

用 queue 来存放 iterator，每次 poll 出来的都是 next 输出的元素，
如果没有 next 了就不放在 queue 里了

Time: O(n)
Space: O(1)
*/
public class ZigzagIterator {
    public Queue<Iterator> q;
    public ZigzagIterator(List<Integer> v1, List<Integer> v2) {
        q = new LinkedList<Iterator>();
        if(v1.size() > 0) q.offer(v1.iterator());
        if(v2.size() > 0) q.offer(v2.iterator());
    }

    public int next() {
        Iterator<Integer> it = q.poll();
        int res = it.next();
        if(it.hasNext()) q.offer(it);
        return res;
    }

    public boolean hasNext() {
        return !q.isEmpty();
    }
}

/**
 * Your ZigzagIterator object will be instantiated and called as such:
 * ZigzagIterator i = new ZigzagIterator(v1, v2);
 * while (i.hasNext()) v[f()] = i.next();
 */