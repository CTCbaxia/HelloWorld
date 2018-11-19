/*
MEDIUM
452. Minimum Number of Arrows to Burst Balloons

TIME: 
RESULT: 
NOTES:

*/
/*
Sort by end
put arrow at end, compare start with current end

Time: O(nlogn)
Space: O(1)
*/
class Solution {
    public int findMinArrowShots(int[][] points) {
        if(points.length == 0) return 0;
        //sort by end
        Arrays.sort(points, new Comparator<int[]>(){
            public int compare(int[] i1, int[] i2){
                return i1[1] - i2[1];
            }
        });
        int count = 1;
        int[] cur = points[0];//initial the first
        for(int i = 1; i < points.length; i++){
            if(points[i][0] <= cur[1]) continue;
            else{
                count++;
                cur = points[i];
            }
        }
        return count;
    }
}






/*same as above
Sort by finishing first
see before the first finishin time, how many ballon we can pass
if start > finish, result++

Time: O(nlogn)
Space: O(1)
*/
class Solution {
    public int findMinArrowShots(int[][] points) {
        if(points.length == 0) return 0;
        
        int result = 1;
        //sort the array by finishing time asceding
        Arrays.sort(points, new Comparator<int[]>(){
            public int compare(int[] i1, int[] i2){
                return i1[1] - i2[1];
            }
        });
        
        int finish = points[0][1];
        for(int[] point : points){
            if(point[0] > finish){//if new start time later than finish, we need more
                result++;
                finish = point[1];
            }
        }
        return result;
    }
}


/*
Sort by start:
update the latest finished position
if start > latest finished position, result++

Time: O(nlogn)
Space: O(1)
*/
class Solution {
    public int findMinArrowShots(int[][] points) {
        if(points.length == 0) return 0;
        
        int result = 1;

        //sort the array by starting time asceding
        Arrays.sort(points, new Comparator<int[]>(){
            public int compare(int[] i1, int[] i2){
                return i1[0] - i2[0];
            }
        });
        
        int finish = points[0][1];
        for(int[] point : points){
            if(point[0] > finish){
                result++;
                finish = point[1];
            }else{
                finish = Math.min(finish, point[1]);
            }
        }
        return result;
    }
}