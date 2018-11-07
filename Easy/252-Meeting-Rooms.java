/*
EASY
252. Meeting Rooms

RESULT: 
NOTES: 
*/
/*
Sorting: 
sort by start time, if the meeting start before the previous end, then false

Time: O(nlogn)
Space: O(1)
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
    public boolean canAttendMeetings(Interval[] intervals) {
        Arrays.sort(intervals, new Comparator<Interval>(){
            public int compare(Interval i1, Interval i2){
                return i1.start - i2.start;
            }
        });

        for(int i = 1; i < intervals.length; i++){
            if(intervals[i].start < intervals[i - 1].end) return false;
        }
        return true;
    }
}