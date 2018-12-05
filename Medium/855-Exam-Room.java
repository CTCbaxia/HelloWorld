/*
MEDIUM
855. Exam Room

TIME: 
RESULT: 
NOTES:

*/
/*
PriorityQueue + Interval Data Structure
Use Interval Data Structure for the PriorityQueue
1) For seat, poll largest in pq, and break that interval into two parts
2) For leave, find the leave related interval, merge these two interval 

seat: 
    Time: O(logN)
    Space: O(N)
leave
    Time: O(N)
    Space: O(N)
*/
class ExamRoom {
    //data structure design
    public class Interval{
        int x;
        int y;
        int d;
        public Interval(int _x, int _y){
            x = _x;
            y = _y;
            if(x == -1) d = y;//when interval contains the 0, 0 must be the farthest
            else if(y == N) d = N - 1 - x;
            else d = Math.abs(x - y) / 2;//want to find the closet people, not the largest interval
        }
    }
    
    PriorityQueue<Interval> pq;
    int N;
    public ExamRoom(int N) {
        pq = new PriorityQueue<Interval>(new Comparator<Interval>(){
            public int compare(Interval i1, Interval i2){
                if(i1.d == i2.d) return i1.x - i2.x;//asc x
                else return i2.d - i1.d;//desc distance
            }
        });
        this.N = N;
        pq.offer(new Interval(-1, N));//initialization
    }

    //poll top interval with largest distance to the closet one, split that interval into two
    public int seat() {
        int seat = 0;
        if(pq.size() == N + 1) return seat;//if no empty position, return 0
        
        Interval interval = pq.poll();
        if(interval.x == -1) seat = 0;//when interval contains the 0, 0 must be the farthest
        else if(interval.y == N) seat = N - 1;
        else seat = interval.x + (interval.y - interval.x)/2;
        
        pq.offer(new Interval(interval.x, seat));//break the interval into two parts
        pq.offer(new Interval(seat, interval.y));
        return seat;
    }

    //find first and second part of the interval and merge
    public void leave(int p) {
        //connect the interval
        Interval[] intervals = new Interval[pq.size()];
        Interval first = null;
        Interval second = null;
        
        pq.toArray(intervals);
        for(Interval i : intervals){
            if(i.x == p) second = i;
            else if(i.y == p) first = i;
            if(first != null && second != null) break;
        }
        pq.remove(first);
        pq.remove(second);
        pq.offer(new Interval(first.x, second.y));
        return;
    }
}


/**
 * Your ExamRoom object will be instantiated and called as such:
 * ExamRoom obj = new ExamRoom(N);
 * int param_1 = obj.seat();
 * obj.leave(p);
 */