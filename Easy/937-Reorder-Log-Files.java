/*
EASY
937. Reorder Log Files

*/
/*
Comparator: get the start index of the info string

Time: O(n)
Space: O(n)
*/
class Solution {
    public String[] reorderLogFiles(String[] logs) {
        Comparator<String> comp = new Comparator<String>(){
            public int compare(String s1, String s2){
                int i = s1.indexOf(" ");
                int j = s2.indexOf(" ");
                boolean c1 = Character.isDigit(s1.charAt(i + 1));
                boolean c2 = Character.isDigit(s2.charAt(j + 1));
                if(c1 && c2) return 0;
                else if(c1) return 1;
                else if(c2) return -1;
                else{//all string
                    int compStr = s1.substring(i + 1).compareTo(s2.substring(j + 1));
                    if(compStr != 0) return compStr;
                    else return s1.substring(0, i).compareTo(s2.substring(0, j));
                }
            }
        };
        Arrays.sort(logs, comp);
        return logs;
    }
}



/*
Comparator: split string

Time: O(n)
Space: O(n)
*/
class Solution {
    public String[] reorderLogFiles(String[] logs) {
        Comparator<String> comp = new Comparator<String>(){
            public int compare(String s1, String s2){
                String[] s11 = s1.split(" ");
                String[] s22 = s2.split(" ");
                boolean c1 = Character.isDigit(s11[1].charAt(0));
                boolean c2 = Character.isDigit(s22[1].charAt(0));
                if(c1 && c2) return 0;
                else if(c1) return 1;
                else if(c2) return -1;
                else{//all string
                    int i = 1, j = 1;
                    while(i < s11.length && j < s22.length){
                        if(s11[i].compareTo(s22[j]) != 0){
                            return s11[i].compareTo(s22[j]);
                        }else{
                            i++;
                            j++;
                        }
                    }
                    if(i < s11.length) return 1;
                    else if(j < s22.length) return -1;
                    else return s11[0].compareTo(s22[0]);
                }
            }
        };
        Arrays.sort(logs, comp);
        return logs;
    }
}





//略复杂，其实 数字和字母可以一起比较，一个 comparator 里面只需要写好两个对象的比较结果就好
class Solution {
    public String[] reorderLogFiles(String[] logs) {
        List<String> list = new ArrayList<>();
        PriorityQueue<String> pq = new PriorityQueue<>(new Comparator<String>(){
            public int compare(String s1, String s2){
                String[] s11 = s1.split(" ");
                String[] s22 = s2.split(" ");
                int i = 1, j = 1;
                while(i < s11.length && j < s22.length){
                    if(s11[i].compareTo(s22[j]) != 0){
                        return s11[i].compareTo(s22[j]);
                    }else{
                        i++;
                        j++;
                    }
                }
                if(i < s11.length) return 1;
                else if(j < s22.length) return -1;
                else{
                    return s11[0].compareTo(s22[0]);
                }
            }
        });
        
        for(String s : logs){
            String[] str = s.split(" ");
            //if(str.length == 1) 
            char c = str[1].charAt(0);
            if(c >= '0' && c <= '9') list.add(s);
            else pq.offer(s);
        }
        String[] res = new String[logs.length];
        int i = 0, j = 0;
        while(pq.size() > 0){
            res[i++] = pq.poll();
        }
        while(i < res.length){
            res[i++] = list.get(j++);
        }
        return res;
    }
}