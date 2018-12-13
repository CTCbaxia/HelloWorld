import java.util.*;
/*
Interval AND

interval lists 内部没有overlap

*/
class Facebook_Intersection_of_Two_Intervals {
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
		List<Interval> result = intersection(l1, l2);

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
	public static List<Interval> intersection(List<Interval> l1, List<Interval> l2) {
		if (l1 == null || l1.size()  == 0) {
			return l2;
		} else if (l2 == null || l2.size() == 0) {
			return l1;
		} 
		
		Collections.sort(l1, new intervalComparator());
		Collections.sort(l2, new intervalComparator());
		
		List<Interval> result = new ArrayList<>();
		int i1 = 0, i2 = 0;
		while(i1 < l1.size() && i2 < l2.size()){
			Interval interval1 = l1.get(i1);
			Interval interval2 = l2.get(i2);
			if(interval1.end <= interval2.start) i1++;
			else if(interval2.end <= interval1.start) i2++;
			else{
				/*
				there is an intersection between interval1 and interval2
				And since no interval inside the list, we just need to put it into result without further intersection compare
				But be careful before moving index:  need to check the ending point, one may contains multiple intersection
				---------
				---  ---
				*/
				Interval intersection = new Interval(Math.max(interval1.start, interval2.start), Math.min(interval1.end, interval2.end));
				result.add(intersection);
				//move index
				if(interval1.end < interval2.end) i1++;
				else if(interval2.end < interval1.end) i2++;
				else{i1++; i2++;}

			}
		}

		return result;
	}
	
}