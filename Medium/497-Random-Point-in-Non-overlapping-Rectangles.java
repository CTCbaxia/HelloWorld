/*
MEDIUM
497. Random Point in Non-overlapping Rectangles

*/
/*
how many points included in an rect

每个 rect 内部有不同数量的可取点数，把所有 rect 内部的点 presum
在左右可取点中 random
得到对应的 rect
再在 rect 中 rand 长宽

Time: 
1. initialize: O(n)
2. Pick: O(1)
Space: O(n)
*/
class Solution {
    Random rand;
    int[][] rects;
    TreeMap<Integer, Integer> map;
    int sum;
    public Solution(int[][] rects) {
        this.rects = rects;
        map = new TreeMap<>();
        rand = new Random();
        sum = 0;
        for(int i = 0; i < rects.length; i++){
            int[] rec = rects[i];
            int len = rec[2] - rec[0] + 1;
            int h = rec[3] - rec[1] + 1;
            sum += len * h;//presum
            //sum += (rect[2] - rect[0] + 1) * (rect[3] - rect[1] + 1);
            map.put(sum, i);
        }
    }
    
    public int[] pick() {
        int r = rand.nextInt(sum + 1);
        int index = map.get(map.ceilingKey(r));//向上对齐
        
        //index is the rec index
        int x1 = rects[index][0];
        int x2 = rects[index][2];
        int x = x1 + rand.nextInt(x2 - x1 + 1);
        int y1 = rects[index][1];
        int y2 = rects[index][3];
        int y = y1 + rand.nextInt(y2 - y1 + 1);
        return new int[]{x, y};
    }
}

/**
 * Your Solution object will be instantiated and called as such:
 * Solution obj = new Solution(rects);
 * int[] param_1 = obj.pick();
 */




/*
how many points included in an rect

Time: 
1. initialize: O(n)
2. Pick: O(logn)
Space: O(n)
*/
class Solution {
    Random rand;
    int[][] rects;
    int[] area;
    public Solution(int[][] rects) {
        this.rects = rects;
        area = new int[rects.length];
        rand = new Random();
        for(int i = 0; i < rects.length; i++){
            int[] rec = rects[i];
            int len = rec[2] - rec[1] + 1;
            int h = rec[3] - rec[0] + 1;
            area[i] += len * h;//presum
        }
    }
    
    public int[] pick() {
        int r = rand.nextInt(area[area.length - 1] + 1);
        
        //find position smallerOrEqual than rand
        int lo = 0, hi = area.length - 1;
        while(lo < hi){
            int mid = lo + (hi - lo)/2;
            if(area[mid] > r) hi = mid - 1;
            else lo = mid;
        }
        //lo is the rec index
        int x1 = rects[lo][0];
        int x2 = rects[lo][2];
        int x = x1 + rand.nextInt(x2 - x1 + 1);
        int y1 = rects[lo][1];
        int y2 = rects[lo][3];
        int y = y1 + rand.nextInt(y2 - y1 + 1);
        return new int[]{x, y};
    }
}

/**
 * Your Solution object will be instantiated and called as such:
 * Solution obj = new Solution(rects);
 * int[] param_1 = obj.pick();
 */