/*
1. Facebook - Meeting Room 变种
输入时间为 string
*/
class SolutionStringTime{
    public int meetingRoom(List<String> l){
        PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>(){
            public int compare(int[] i1, int[] i2){
                if(i1[0] != i2[0]) return i1[0] - i2[0];
                else return i2[1] - i1[1];
            }
        });
        //preprocess + sort
        for(String s : l){
            String[] str = s.split("-");
            int[] start = new int[]{getTime(str[0]), 0};
            int[] end = new int[]{getTime(str[1]), 1};
            pq.offer(start);
            pq.offer(end);
        }
        //count
        int res = 0;
        int count = 0;
        while(!pq.isEmpty()){
            if(pq.poll()[1] == 0) count++;
            else count--;
            res = Math.max(res, count);
        }
        return res;
    }

    private int getTime(String s){
        s = s.trim();
        int n = s.length();
        int baseIndex = s.charAt(n - 1) == 'M' ? n - 2 : n - 1;
        String[] time = s.substring(0,baseIndex).split(":");
        int h = Integer.parseInt(time[0]);
        int m = time.length > 1 ? Integer.parseInt(time[1]) : 0;
        
        int base = s.charAt(baseIndex) == 'A' 
            ? 0 
            : h == 12 //12PM
                ? 0
                : 12 * 60;
        System.out.println(s + " "+(base + h * 60 + m));
        return base + h * 60 + m;
    }
}
/*
2.统计每个房间使用的时间段
List<List<roomIndex>>
PriorityQueue<[roomIndex, endTime]>

sort collections by start time
sort pq by end time

when having a new meeting, peek the room that ends earliest,
if endTime <= startTime, pop, and add a new [(same) roomIndex, endTime] ** cannot just change endtime
else, create a new room

*/

/*
3. 给出所有人能接受的价格区间
[6,8]
[8,10]
[12,14]
找出一个价格，使得总收益最大，比如这里是 8，因为两个人会买，收益16
*/
class SolutionPriceInterval{
    public int meetingRoom(List<int[]> l){
        PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>(){
            public int compare(int[] i1, int[] i2){
                if(i1[0] != i2[0]) return i1[0] - i2[0];
                else return i1[1] - i2[1];//for same price, should poll start before end
            }
        });
        //preprocess + sort
        for(int[] s : l){
            pq.offer(new int[]{s[0], 0});
            pq.offer(new int[]{s[1], 1});
        }
        //count
        int res = 0;
        int count = 0;
        while(!pq.isEmpty()){
            int[] price = pq.poll();
            if(price[1] == 0) count++;//lower bound
            else{//upper bound
                res = Math.max(res, count * price[0]);//current number of people who can afford * price
                count--;
            } 
        }
        return res;
    }

}
public class Facebook_Meeting_Room_Modified {
    public static void main(String[] args) {
        SolutionStringTime s = new SolutionStringTime();
        List<String> l = new ArrayList<>();
        l.add("10:30AM-11AM");
        l.add("10:20AM-1PM");
        l.add("11:30AM-12PM");
        l.add("10:20AM-12:30PM");
        l.add("11AM-2PM");
        System.out.println(s.meetingRoom(l));//4


        SolutionPriceInterval s = new SolutionPriceInterval();
        List<int[]> l = new ArrayList<>();
        l.add(new int[]{6,8});
        l.add(new int[]{8,10});
        l.add(new int[]{12,14});
        System.out.println(s.meetingRoom(l));//16
    }
}
