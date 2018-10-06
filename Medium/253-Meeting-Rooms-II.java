/*
MEDIUM
253. Meeting Rooms II
https://leetcode.com/problems/meeting-rooms-ii/description/

TIME: 10.06 - 3h
RESULT: 100% - 2ms

*/
/*
SOLUTION REFERENCE:
直接看所有的start time and end time。
every time we want to start a meeting, we need to check if there is enough rooms
How do we check that?
we check if there is any meeting finished before we start a new meeting.
If no, we need more room
If so, we just use that room

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
    public int minMeetingRooms(Interval[] intervals) {
        if(intervals.length == 0) return 0;
        
        int[] starts = new int[intervals.length];
        int[] ends = new int[intervals.length];
        
        for(int i = 0; i < intervals.length; i++){
            starts[i] = intervals[i].start;
            ends[i] = intervals[i].end;
        }
        Arrays.sort(starts);
        Arrays.sort(ends);
        
        int rooms = 0;
        int endIndex = 0;
        for(int i = 0; i < starts.length; i++){
            if(ends[endIndex] <= starts[i]) endIndex++;//smaller or equal, when the previous one just finished the meeting, we can fit in
            else rooms++;
        }
        return rooms;
    }
    
}
/*TLE
思路：
It is like we need to see how many overlap meetings at a time
we can just use a moving index to see at every time slot, how many meetings are there.
but the time complexity is O(meetings * total duration)

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
    public int minMeetingRooms(Interval[] intervals) {
        if(intervals.length == 0) return 0;
        
        Arrays.sort(intervals,new Comparator<Interval>() {
           public int compare(Interval i1, Interval i2){
               if(i1.start != i2.start) return i1.start - i2.start;
               else return i1.end - i2.end;
           } 
        });

        int res = 0;
        int index = intervals[0].start;
        int end = intervals[0].end;
        for(Interval interval: intervals) end = Math.max(end, interval.end);
        System.out.println(end);
        while(index <= end){
            int count = 0;
            for(int i = 0; i < intervals.length; i++){
                if(intervals[i].start <= index && intervals[i].end > index) count++;
            }
            res = Math.max(res, count);
            index++;
        }
        return res;
    }
    
}

//TLE
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
    public int minMeetingRooms(Interval[] intervals) {
        if(intervals.length == 0) return 0;
        
        Arrays.sort(intervals,new Comparator<Interval>() {
           public int compare(Interval i1, Interval i2){
               if(i1.start != i2.start) return i1.start - i2.start;
               else return i1.end - i2.end;
           } 
        });
        
        int res = 0;
        for(int i = 0; i < intervals.length; i++){
            //System.out.println(intervals[i].start + " "+intervals[i].end);
            if(i > 0 && intervals[i].end <= intervals[i - 1].end) continue;
            res = Math.max(res, conHelper(intervals, i + 1, intervals[i].start, intervals[i].end, 1));
        }
        return res;
    }
    private int conHelper(Interval[] intervals, int index, int start, int end, int pre){
        
        if(index >= intervals.length) return pre;
        int res = pre;
        for(int i = index; i < intervals.length; i++){
            //System.out.println(i + " "  + intervals[i].start + ":" + intervals[i].end + "  bound:"+ start + "  " + end + " " + pre);
            if(intervals[i].start < end){
                int tmpstart = Math.max(start, intervals[i].start);
                int tmpend = Math.min(end, intervals[i].end);
                res = Math.max(res, conHelper(intervals, i + 1, tmpstart, tmpend, pre + 1));
            }else break;
        }
        return res;
    }
    
}