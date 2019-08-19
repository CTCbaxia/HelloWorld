/*
H
218. The Skyline Problem
*/
/*
Active Set - Line Sweep
separate the input as [index, height, isStart]  -- can use "-height" to represent isStart
then go through the vertical line one by one, 
when it isStart is true, put the height into the pq,
when it isStart is false, remove from the pq
when the preMax changed, put [curIndex, curHeight] into the result

3 edge cases that same index has different height:
 ___
|-- |
|   |
higher should come earlier
 ___
| --|
|   |
higher should come later
    ___
 __|   |
|      |
heigher should come earlier

So, when the index is the same, the index for start should be earlier, for end should be later


https://www.youtube.com/watch?time_continue=709&v=GSBLe8cKu0s

Time: O(nlogn ~ n^2)
Space: O(n)
*/
class Solution {
    public List<List<Integer>> getSkyline(int[][] buildings) {
        // separat the start and end point
        List<int[]> list = new ArrayList<>();
        for(int[] b : buildings){
            // Negative to tell whether it is start, 
            // also can cover edge cases that for the start point, multiple height should be descending
            list.add(new int[]{b[0], -b[2]});
            list.add(new int[]{b[1], b[2]});
        }
        Collections.sort(list, new Comparator<int[]>(){
           public int compare(int[] i1, int[] i2){
               if(i1[0] != i2[0]) return i1[0] - i2[0];//sort by index
               else return i1[1] - i2[1];// sort by height
           } 
        });
        
        PriorityQueue<Integer> pq = new PriorityQueue<>(new Comparator<Integer>(){//descending
            public int compare(Integer i1, Integer i2){//should use Integer instead of int
                return i2 - i1;
            }
        });
        int preMax = 0;
        pq.offer(0);//ground
        List<List<Integer>> result = new ArrayList<>();
        for(int[] height: list){
            if(height[1] < 0) pq.offer(-height[1]);//update the height
            else pq.remove(height[1]);
            
            int curMax = pq.peek();
            if(preMax != curMax){//there is a update for the max height
                result.add(Arrays.asList(height[0], curMax));
                preMax = curMax;//update max
            }
        }
        return result;
    }
}
