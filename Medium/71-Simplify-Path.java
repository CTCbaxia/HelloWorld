/*
MEDIUM
71. Simplify Path
https://leetcode.com/problems/simplify-path/description/

TIME: 0823 - 2h
RESULT: 23% - 12ms
NOTES:
1.DISCIPLINE:
	"/b/c/" - directory 'b ' - > directory 'c '
	"." - current directory
	"./" - current directory
	"../" - one directory up
	e.g
	"/" : root directory
	"b/c/../" : it will go from c to b
	"c/b/./" : it is still in directory b

2. for (String dir : path.split("/")) 
*/
//这个方法不错，只用数组的思想，没有真正用 stack
class Solution {
    public String simplifyPath(String path) {
	String[] dir = path.split("/");
	String[] stack = new String[dir.length];
	int ptr = 0;
	for(int i = 0; i < dir.length; i++){
		if(dir[i].equals(".") || dir[i].equals("")){
			continue;
		}else if(dir[i].equals("..")){
			if(ptr > 0) ptr--;
		}else{
			stack[ptr] = dir[i];
			ptr++;
		}
	}
	StringBuilder result = new StringBuilder();
	for(int i = 0; i < ptr; i++){
		result.append("/");
		result.append(stack[i]);
	}
	return result.length() == 0 ? "/" : result.toString();
}
}


/*
SOLUTION 0:
TIME: 0823 - 2h
RESULT: 23% - 12ms
*/
class Solution {
    public String simplifyPath(String path) {
        Stack<String> stack = new Stack<String>();
        int len = path.length();
        String result = "";
        for(String s : path.split("/")){
            if(s.equals("..")){
                if(!stack.empty()){
                    stack.pop();
                }
            }else if(!s.equals(".") && !s.equals("")) stack.push(s);
        }
        System.out.println(stack);
        StringBuilder sb = new StringBuilder();
        for(String s : stack){
            sb.append("/");
            sb.append(s);
        }
        return (sb.toString().length() == 0) ? "/" : sb.toString();
    }
}

/*
SOLUTION 0:
TIME: 0823 - 1h
RESULT: 12% - 15ms
*/
class Solution {
    public String simplifyPath(String path) {
        Stack<String> stack = new Stack<String>();
        int len = path.length();
        String result = "";
        for(int i = 0; i < len; i++){
            if(i == 0 && path.charAt(i) != '/'){
                i = getCommand(path, 0, stack);
            }
            if(path.charAt(i) == '/'){
                i = getCommand(path, i + 1, stack);
            }
        }
        for(String s : stack) result = result + "/" + s;
        
        return (result.length() == 0) ? "/" : result;
    }
    private int getCommand(String path, int index, Stack<String> stack){//get the word between / and /, or / till end
        String tmp = "";
        while(index < path.length() && path.charAt(index) != '/'){
            tmp = tmp + path.charAt(index++);
        }
        if(tmp.equals("..")){
            if(!stack.empty()) stack.pop(); 
        }else if(tmp.equals(".") || tmp.equals("")){
            
        }else stack.push(tmp);
        return index - 1;
    }
}