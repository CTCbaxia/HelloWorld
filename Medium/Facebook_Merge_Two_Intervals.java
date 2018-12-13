import java.util.*;
/*
https://leetcode.com/discuss/interview-question/124616/merge-two-interval-lists?orderBy=most_votes
Merge Interval 变种

*/
class Facebook_Merge_Two_Intervals {
	public static class Interval {
		int start;
		int end;
		public Interval(int start, int end) {
			this.start = start;
			this.end = end;
		}
	}
	public static void main(String[] args) {
		
		List<Interval> l1 = new ArrayList<>();
		l1.add(new Interval(1, 5));
		l1.add(new Interval(10, 14));
		l1.add(new Interval(16, 18));
		l1.add(new Interval(20, 24));
		l1.add(new Interval(30, 38));
		List<Interval> l2 = new ArrayList<>();
		l2.add(new Interval(2, 6));
		l2.add(new Interval(8, 10));
		l2.add(new Interval(11, 20));
		System.out.println("test");
		List<Interval> result = mergeList(l1, l2);

		for (Interval i1: result) {
			System.out.println(i1.start + ", " + i1.end);
		}
		return;
	}

	public static class intervalComparator implements Comparator<Interval>{
		public int compare(Interval i1, Interval i2){
			return i1.start - i2.start;
		}
	}
	public static List<Interval> mergeList(List<Interval> l1, List<Interval> l2) {
		if (l1 == null || l1.size()  == 0) {
			return l2;
		} else if (l2 == null || l2.size() == 0) {
			return l1;
		} 
		
		Collections.sort(l1, new intervalComparator());
		Collections.sort(l2, new intervalComparator());
		
		List<Interval> result = new ArrayList<>();
		Interval pre = null;
		int i1 = 0;
		int i2 = 0;

		if(l1.get(i1).start <= l2.get(i2).start){
			pre = l1.get(i1++);

		}else{
			pre = l2.get(i2++);
		}
		while(i1 < l1.size() || i2 < l2.size()){
			if(i2 == l2.size() || i1 < l1.size() && l1.get(i1).start <= l2.get(i2).start){
				if(l1.get(i1).start <= pre.end){//merge
					pre.end = Math.max(pre.end, l1.get(i1).end);
				}else{
					result.add(pre);
					pre = l1.get(i1);
				}
				i1++;
			}else{
				if(l2.get(i2).start < pre.end){
					pre.end = Math.max(pre.end, l2.get(i2).end);
				}else{
					result.add(pre);
					pre = l2.get(i2);
				}
				i2++;
			}
		}
		result.add(pre);

		return result;
	}
	

	
}