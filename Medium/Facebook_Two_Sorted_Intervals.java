/*
Facebook - Interval
1. merge two sorted intervals
2. intersact two sorted intervals

*/
public class FacebookTwoSortedIntervals {
    public static void main(String[] args) {
        Facebook_Two_Sorted_Intervals s = new SolutionPriceInterval();
        List<int[]> l = new ArrayList<>();
        l.add(new int[]{6,8});
        l.add(new int[]{8,10});
        l.add(new int[]{12,14});
        System.out.println(s.meetingRoom(l));//16
    }
}
