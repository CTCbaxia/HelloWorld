/*
MEDIUM
253. Meeting Rooms II
https://leetcode.com/problems/meeting-rooms-ii/description/

TIME: 10.06 - 3h
RESULT: 100% - 2ms

*/
/*
Line sweep: start and end separation, count room
只需要给每一个时间点标记开始和结束，然后按照时间排序，每次start都增加一个房间，end都结束一个房间。
一直更新最大房间占有量

** need to be careful for SAME start and end time

Time: O(nlogn)
Space: O(n)
*/
class Solution {
    public int minMeetingRooms(int[][] intervals) {
        int res = 0;
        int room = 0;
        
        PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>(){
            public int compare(int[] i1, int[] i2){
                if(i1[0] != i2[0]) return i1[0] - i2[0];//asc time
                else return i2[1] - i1[1];//desc end first, start next
                
            }
        });
        //separate the timeline
        //[time, start/end], start = 0, end = 1
        for(int[] i : intervals){
            pq.offer(new int[]{i[0], 0});
            pq.offer(new int[]{i[1], 1});
        }
        
        while(!pq.isEmpty()){
            int[] t = pq.poll();
            if(t[1] == 0) room++;// start
            else if(t[1] == 1) room--;// end
            
            res = Math.max(res, room);
        }
        return res;
    }
}




/*
Array sort + PriorityQueue
我们只会在有新 meeting 的时候 poll form pq，并且一定会放入至少一个meeting进去
所以 pq 的 size 只会越来越多

sort the array by starting time
use pq to save all active meeting, sorted using the finishing time(pq.poll() get the smallest end time out)
    1. if end before start, no need for more room and we update the finishing time for that room
    2. else, add more room (put this meeting into pq)
return pq.size()
*/
class Solution {
    public int minMeetingRooms(Interval[] intervals){
        if(intervals.length == 0) return 0;
        //sort using start time
        Arrays.sort(intervals, new Comparator<Interval>(){
            public int compare(Interval i1, Interval i2){
                return i1.start - i2.start;
            }
        });
        //end using pq, min rooms == size of pq
        PriorityQueue<Interval> pq = new PriorityQueue<>(new Comparator<Interval>(){
            public int compare(Interval i1, Interval i2){
                return i1.end - i2.end;
            }
        });

        for(Interval i: intervals){
            if(pq.size() == 0) pq.offer(i);//put the first one into pq
            else{
                Interval finished = pq.poll();
                if(i.start < finished.end){
                    pq.offer(i);
                }else{
                    finished.end = i.end;
                }
                pq.offer(finished);//always keep the room occupied in pq
            }

        }
        return pq.size();

    }                       

}




/**
 * Definition for an interval.
 * public class Interval {
 *     int start;
 *     int end;
 *     Interval() { start = 0; end = 0; }
 *     Interval(int s, int e) { start = s; end = e; }
 * }
 */
/*
Array sort + PriorityQueue
我们只会在有新 meeting 的时候 poll form pq，并且一定会放入至少一个meeting进去
所以 pq 的 size 只会越来越多

sort the array by starting time
use pq to save all active meeting, sorted using the finishing time(pq.poll() get the smallest end time out)
    1. if end before start, no need for more room and we update the finishing time for that room
    2. else, add more room (put this meeting into pq)
return pq.size()
*/
class Solution {
    public int minMeetingRooms(Interval[] intervals) {
        if(intervals.length == 0) return 0;
        Arrays.sort(intervals, new Comparator<Interval>(){
            public int compare(Interval i1, Interval i2){
                return i1.start - i2.start;
            }
        });
        PriorityQueue<Interval> pq = new PriorityQueue<>(new Comparator<Interval>(){
            public int compare(Interval i1, Interval i2){
                return i1.end - i2.end;
            }
        });
        int res = 1;
        pq.offer(intervals[0]);
        for(int i = 1; i < intervals.length; i++){
            Interval meeting = intervals[i];
            Interval finish = pq.poll();
            if(finish.end <= meeting.start){
                pq.offer(meeting);
            }else{
                pq.offer(finish);
                pq.offer(meeting);
            }
            res = Math.max(res, pq.size());
        }
        return res;
    }
}
/*
Array sort + PriorityQueue
我们只会在有新 meeting 的时候 poll form pq，并且一定会放入至少一个meeting进去
所以 pq 的 size 只会越来越多

sort the array by starting time
use pq to save all active meeting, sorted using the finishing time(pq.poll() get the smallest end time out)
    1. if end before start, no need for more room and we update the finishing time for that room
    2. else, add more room (put this meeting into pq)
return pq.size()
*/
class Solution {
    public int minMeetingRooms(Interval[] intervals) {
        if(intervals.length == 0) return 0;
        //sort the arrays using the start time
        Arrays.sort(intervals, new Comparator<Interval>(){
            public int compare(Interval i1, Interval i2){
                return i1.start - i2.start;
            }
        });

        //use a min heap to store all rooms needed and the end time of each meetings
        PriorityQueue<Interval> pq = new PriorityQueue<Interval>(intervals.length, new Comparator<Interval>(){
            public int compare(Interval i1, Interval i2){
                return i1.end - i2.end;
            }
        });
        pq.offer(intervals[0]);
        for(int i = 1; i < intervals.length; i++){
            Interval tmp = pq.poll();//get the one finishing earlist
            
            if(intervals[i].start >= tmp.end){//no need more room, update finishing time
                tmp.end = intervals[i].end;
            }else{//need more room
                pq.offer(intervals[i]);
            }
            pq.offer(tmp);//continuingly using that room (either old meeting or new meeting)
        }
        
        return pq.size();
    }
}
/*
Sort + Two Pointers:

直接看所有的start time and end time。
有 meeting end，那在之前肯定有meeting start。

every time we want to start a meeting, we need to check if there is enough rooms
How do we check that?
we check if there is any meeting finished before we start a new meeting.
If no, we need more room
If so, we just use that room

Time: O(nlogn)
Space: O(n)
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
