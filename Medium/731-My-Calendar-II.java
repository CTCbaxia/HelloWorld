/*
MEDIUM
731. My Calendar II

*/
/*
Interval + List in order
tips: 判断 interval 要反向判断

Time: O(n + n^2) twice 的 大小可能是 n^2 假设每个都 overlap
Space: O(n^2) worst case, 假设每个都 overlap
*/
class MyCalendarTwo {
    List<Interval> list;
    List<Interval> twice;
    public class Interval{
        int start;
        int end;
        public Interval(int _start, int _end){
            start = _start;
            end = _end;
        }
    }
    public MyCalendarTwo() {
        list = new ArrayList<>();
        twice = new ArrayList<>();
    }
    
    public boolean book(int start, int end) {
        for(Interval interval : twice){
            if(interval.end <= start || interval.start >= end) continue;//反向判断 interval overlap 
            else return false;
        }
        //loop the current Interval to update new overlap and insert this Interval
        int index = 0;//insert index
        for(int i = 0; i < list.size(); i++){
            if(list.get(i).start >= end) break;//no overlap
            if(list.get(i).start <= start) index = i + 1;//update insert index
            if(list.get(i).end > start){//there is overlap
                int overlapStart = Math.max(start, list.get(i).start);
                int overlapEnd = Math.min(end, list.get(i).end);
                twice.add(new Interval(overlapStart, overlapEnd));
            }
        }
        list.add(index, new Interval(start, end));
        
        return true;
    }
}

/**
 * Your MyCalendarTwo object will be instantiated and called as such:
 * MyCalendarTwo obj = new MyCalendarTwo();
 * boolean param_1 = obj.book(start,end);
 */