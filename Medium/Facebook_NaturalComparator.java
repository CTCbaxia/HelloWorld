/*
自然string comparator。不知道的搜下。就是string 比较的时候考虑里面数字的大小，比如 abc9 < abc123， abc > ab9  因为char比digit重要。
['10a','abc','a2','a10','bac']
同一位如果是数字和字母，字母大
同一位都是数字的话，比较完整数字
同一位都是字母的话，比较字母.
*/
public class Main {
    public static void main(String[] args) {
        String[] input1 = new String[]{"10a","abc","a2","a10","bac","aa234", "bac1", "ab12","ab123","ab123a","ab123a1","bac0001","bac09"};
        String[] res1 = sorting(input1);
		for(String s : res1){
			System.out.println(s);
		}
        String[] input2 = new String[]{"ab12ab","a121b12","ab12a2b","a12"};
        String[] res2 = sorting(input2);
        for(String s : res2){
			System.out.println(s);
		}

		return;
    }
    public static String[] sorting(String[] input){
        Comparator<String> naturalComparator = new Comparator<String>(){
            public int compare(String s1, String s2){
                int i1 = 0, i2 = 0;
                while(i1 < s1.length() && i2 < s2.length()){
                    char c1 = s1.charAt(i1);
                    char c2 = s2.charAt(i2);
                    if(Character.isLetter(c1) && Character.isLetter(c2)){
                        if(c1 != c2) return c1 - c2;
                        i1++;
                        i2++;
                    }else if(Character.isLetter(c1)){
                        return 1;
                    }else if(Character.isLetter(c2)){
                        return -1;
                    }else{//compare whole numbers
                        int n1 = 0, n2 = 0;
                        while(i1 < s1.length() && Character.isDigit(s1.charAt(i1))){
                           n1 = n1 * 10 + (s1.charAt(i1++) - '0');
                        }
                        while(i2 < s2.length() && Character.isDigit(s2.charAt(i2))){
                           n2 = n2 * 10 + (s2.charAt(i2++) - '0');
                        }
                        if(n1 != n2) return n1 - n2;
                    }
                }
                //here at least one of string ends, and they are equal by far
                if(i1 < s1.length()) return 1;
                else if(i2 < s2.length()) return -1;
                else return 0;
            }
        };
        Arrays.sort(input, naturalComparator);
        return input;
    }
}









import java.util.*;
import java.math.*;
class Facebook_NaturalComparator {
	public static void main(String[] args) {
		String[] test = new String[]{"10a","abc","a2","a10","bac","aa234", "bac1", "ab12","ab123","ab123a","ab123a1","bac0001","bac09"};
		Arrays.sort(test, new NaturalComparator());
		for(String n : test){
			System.out.println(n);
		}
		return;
	}
	static class NaturalComparator implements Comparator<String> {
	    /**
	     * return negative num if l < r
	     0  if l == r
	     positive if l > r. check 1point3acres for more.
	     */
	    public int compare(String l, String r) {
	        int i = 0, j = 0;
	        while(i < l.length() && j < r.length()){
	            char lc = l.charAt(i);
	            char rc = r.charAt(j);
	            if(Character.isLetter(lc) && Character.isLetter(rc)){//if all charactor, compare one by one
	                if(lc != rc) return lc - rc;
	                i++;
	                j++;
	            }else if(Character.isLetter(lc)){
	                return 1;
	            }else if(Character.isLetter(rc)){
	                return -1;
	            }else{
	                //find the whole num
	                int numl = 0;
	                int numr = 0;
	                while(i < l.length() && Character.isDigit(l.charAt(i))){
	                    numl = numl * 10 + l.charAt(i++) - '0';
	                }
	                while(j < r.length() && Character.isDigit(r.charAt(j))){
	                    numr = numr * 10 + r.charAt(j++) - '0';
	                }
	                if(numl != numr) return numl - numr;                
	            }

	        }
	        if(i < l.length()) return 1;
	        else if(j < r.length()) return -1;
	        else return 0;
	    }
	}
}