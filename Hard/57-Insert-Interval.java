/*
HARD
57. Insert Interval

TIME: 
RESULT: 
*/
/*
Update interval when overlap, 
update insert index when add smaller interval into result(when adding bigger, stop updating insert index)

Time: O(n)
Space: O(1) //result doesn't count
*/

/**
 * Definition for an interval.
 * public class Interval {
 *     int start;
 *     int end;
 *     Interval() { start = 0; end = 0; }
 *     Interval(int s, int e) { start = s; end = e; }
 * }
 */
class Solution {
    public List<Interval> insert(List<Interval> intervals, Interval newInterval){
        List<Interval> result = new ArrayList<>();
        int insert = 0;
        for(int i = 0; i < intervals.size(); i++){
            Interval cur = intervals.get(i);
            if(cur.end < newInterval.start){//all smaller
                result.add(cur);
                insert = result.size();//***update insert index,只更新比 newInterval 小的位置
            }else if(cur.start > newInterval.end){//all larger
                result.add(cur);
            }else{//must be overlapping, merge
                newInterval.start = Math.min(newInterval.start, cur.start);
                newInterval.end = Math.max(newInterval.end, cur.end);
            }
        }
        //insert newInterval at insert index
        result.add(insert, newInterval);

        return result;
    }
}