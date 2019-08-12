/*
M
1024. Video Stitching
*/
/*
Sort + Greedy
Always find the next connected interval that can arrive at a farthest position

Time: O(nlogn)
Space: O(1)
*/
class Solution {
    public int videoStitching(int[][] clips, int T) {
        Arrays.sort(clips, new Comparator<int[]>(){
            public int compare(int[] n1, int[] n2){
                return n1[0] - n2[0];
            }
        });
        int i = 0, curEnd = 0, res = 0;
        while(i < clips.length && curEnd < T){
            int nextEnd = curEnd;
            while(i < clips.length && clips[i][0] <= curEnd){
                nextEnd = Math.max(nextEnd, clips[i][1]);
                i++;
            }
            if(curEnd == nextEnd) return -1;//if it is not continuous (there is a gap)
            curEnd = nextEnd;
            res++;
        }
        return curEnd >= T ? res : -1;//if the longest interval cannot arrive at T
    }
}
