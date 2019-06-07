/*
MEDIUM
536. Construct Binary Tree from String
*/
/*
Recursive
getNum = root
( :parnum ++
) :parnum --
parnum == 0
root.left = str2tree(substring)

Time: O(n^2)
Space: O(n)
*/
class Solution {
    public TreeNode str2tree(String s) {
        if(s.equals("")) return null;
        //generate the root
        int i = 0;
        int rootnum = 0, flag = 1;
        if(s.charAt(0) == '-'){
            i++;
            flag = -1;
        } 
        while(i < s.length() && s.charAt(i) != '('){
            rootnum = rootnum * 10 + (s.charAt(i) - '0');
            i++;
        }
        TreeNode root = new TreeNode(rootnum * flag);
        
        //for left
        int start = i + 1;
        int end = i + getSubTree(s.substring(i));
        if(start >= end) return root;//check if it is valid string
        String left = s.substring(start, end);
        root.left = str2tree(left);
        
        //for right
        start = end + 2;
        end = end + 1 + getSubTree(s.substring(start - 1));
        if(start >= end) return root;
        String right = s.substring(start, end);
        root.right = str2tree(right);
        return root;
    }

    private int getSubTree(String s){
        int left = 0, end = 0;
        for(int i = 0; i < s.length(); i++){
            if(s.charAt(i) == '(') left++;
            else if(s.charAt(i) == ')') left--;
        
            if(left == 0) return i;
        }
        return s.length();
    }
}







//Wrong Code
/*
Recursive
getNum = root
( :parnum ++
) :parnum --
parnum == 0
root.left = str2tree(substring)

Time: O(n^2)
Space: O(n)
*/
class Solution {
    int index = 0;
    public TreeNode str2tree(String s) {
        int rootnum = getNum(s);
        TreeNode root = new TreeNode(rootnum);
        
        root.left = str2tree(getSubTree(s));
        root.right = str2tree(getSubTree(s));
        return root;
    }
    private int getNum(String s){
        int num = 0;
        while(index < s.length() && s.charAt(index) != '('){
            num = num * 10 + (s.charAt(index) - '0');
            index++;
        }
        return num;
    }
    private String getSubTree(String s){
        System.out.println("s:" + s);
        int left = 0;
        int start = index, end = index;
        while(index < s.length()){
            if(s.charAt(index) == '('){
                left++;
            }else if(s.charAt(index) == ')'){
                left--;
            }
            index++;
            end = index;
            if(left == 0) break;
        }
        System.out.println("s.length():" + s.length() + "  end:"+end);
        return s.substring(start, end);
    }
}