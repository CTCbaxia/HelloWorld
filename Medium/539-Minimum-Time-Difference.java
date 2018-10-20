/*
MEDIUM
539. Minimum Time Difference
Microsoft On Campus !!!

TIME: 1012 - 15min + 2h
RESULT: 64% - 16ms

*/
//Bucket Sort
//O(n) + O(1)
class Solution {
    public int findMinDifference(List<String> timePoints) {
        boolean[] minutes = new boolean[24*60]; 
        
        //"sort" the min using bucket sort
        for(int i = 0; i < timePoints.size(); i++){
            String[] t = timePoints.get(i).split(":");
            int minute = Integer.parseInt(t[0]) * 60 + Integer.parseInt(t[1]);
            if(minutes[minute]) return 0;
            minutes[minute] = true;
        }
        int pre = -1;
        int first = -1;
        int last = 24 * 60 + 1;
        int res = 24 * 60 + 1;
        for(int i = 0; i < minutes.length; i++){
            if(minutes[i]){
                System.out.println(i);
                if(pre == -1){
                    pre = i;
                    first = i;
                    continue;
                }
                last = i;
                res = Math.min(res, i - pre);
                pre = i;
            }
        }
        int corner = 24*60 + first - last;
        return Math.min(corner, res);
    }
}
//Sort 正解
//O(nlogn) + O(1)
class Solution {
    public int findMinDifference(List<String> timePoints) {
        int res = 24*60 + 1;
        //get the min
        int[] minutes = new int[timePoints.size()];
        for(int i = 0; i < timePoints.size(); i++){
            String[] t = timePoints.get(i).split(":");
            minutes[i] = Integer.parseInt(t[0]) * 60 + Integer.parseInt(t[1]);
        }
        Arrays.sort(minutes);
        for(int i = 1; i < minutes.length; i++){
            res = Math.min(res, minutes[i] - minutes[i - 1]);
        }
        int corner = minutes[0] + 24*60 - minutes[minutes.length - 1];
        return Math.min(corner, res);
    }
}

//Brute Force 正解
//O(n^2) + O(1)
class Solution {
    public int findMinDifference(List<String> timePoints) {
        int res = 24*60 + 1;
        for(int i = 0; i < timePoints.size(); i++){
            for(int j = i+1; j < timePoints.size(); j++){
                String[] t1 = timePoints.get(i).split(":");
                String[] t2 = timePoints.get(j).split(":");
                int h1 = Integer.parseInt(t1[0]);
                int h2 = Integer.parseInt(t2[0]);
                int m1 = Integer.parseInt(t1[1]);
                int m2 = Integer.parseInt(t2[1]);
                res = Math.min(res, Math.abs((h1 - h2) * 60 + m1 - m2));
                res = Math.min(res, Math.abs((h1 + 24 - h2) * 60 + m1 - m2));
                res = Math.min(res, Math.abs((h1 - (h2 + 24)) * 60 + m1 - m2));
            }
        }
        return res;
    }
}


//2018.10.12 15:00 - 15: 30 Microsoft On Campus 面试
//我的解答
class Solution {
    public int findMinDifference(List<String> timePoints) {
        int res = 24*60 + 1;
        for(int i = 0; i < timePoints.size(); i++){
            for(int j = i+1; j < timePoints.size(); j++){
                String[] t1 = timePoints.get(i).split(":");
                String[] t2 = timePoints.get(j).split(":");
                if(t1[0].equals("00") && t1[1].equals("00")) t1[0] = "24";
                if(t2[0].equals("00") && t2[1].equals("00")) t2[0] = "24";
                int h = Integer.parseInt(t1[0]) - Integer.parseInt(t2[0]);
                int m = Integer.parseInt(t1[1]) - Integer.parseInt(t2[1]);
                res = Math.min(res, Math.abs(h * 60 + m));

            }
        }
        return res;
    }
}