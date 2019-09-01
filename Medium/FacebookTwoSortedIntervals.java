/*
Facebook - Two Sorted Intervals
1. merge
2. intersaction
*/
public class FacebookTwoSortedIntervals {
    public static void main(String[] args) {
        FacebookTwoSortedIntervals s = new FacebookTwoSortedIntervals();
        List<int[]> a = new ArrayList<>();
        a.add(new int[]{1, 5});
        a.add(new int[]{8, 10});
        a.add(new int[]{15, 25});
        List<int[]> b = new ArrayList<>();
        b.add(new int[]{2, 6});
        b.add(new int[]{25, 100});
        List<int[]> res1 = s.intersact(a, b);
        for (int[] i : res1) {
            System.out.println("[" + i[0] + ", " + i[1] + "]");
        }
        /*
        [2, 5]
        [25, 25]
        */
        List<int[]> res2 = s.merge(a, b);
        for (int[] i : res2) {
            System.out.println("[" + i[0] + ", " + i[1] + "]");
        }
        /*
        [1, 6]
        [8, 10]
        [15, 100]
        */
    }
    public List<int[]> merge(List<int[]> a, List<int[]> b){
        List<int[]> res = new ArrayList<int[]>();
        int[] last = null;
        int i = 0, j = 0;
        while(i < a.size() || j < b.size()){
            int[] cur = null;
            if(i < a.size() && j < b.size()){
                cur = a.get(i)[0] < b.get(j)[0]
                    ? a.get(i++)
                    : b.get(j++);
            }
            else if(i < a.size()) cur = a.get(i++);
            else cur = b.get(j++);
            
            if(last == null) last = cur;
            else{
                if(last[1] < cur[0]){
                    res.add(last);
                    last = cur;
                } 
                else last[1] = Math.max(last[1], cur[1]);
            }
        }
        if(last != null) res.add(last);
        return res;
            
    }
    public List<int[]> intersact(List<int[]> a, List<int[]> b){
        List<int[]> res = new ArrayList<>();
        int i = 0, j = 0;
        while(i < a.size() && j < b.size()){
            int[] curA = a.get(i);
            int[] curB = b.get(j);
            
            if(curA[1] < curB[0]) i++;
            else if(curB[1] < curA[0]) j++;
            else{
                res.add(new int[]{Math.max(curA[0], curB[0]), Math.min(curA[1], curB[1])});
                i++;
                j++;
            }
        }
        return res;
    }

}


