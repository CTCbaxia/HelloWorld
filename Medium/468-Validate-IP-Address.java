/*
MEDIUM
468. Validate IP Address

TIME: 
RESULT: 
NOTES:

*/
/*
Check
分别 check 是不是 IPV4 或者 IPV6
check 长度和leading zero

1. String.split()的时候不会 separate ending sign，所以要check
2. split("\\.") 要escape
3. 16bit 只能有 0-9, a-z, A-Z

Time: O(n)
Space: O(1)
*/
class Solution {
    public String validIPAddress(String IP) {
        if(isIPv4(IP)) return "IPv4";
        else if(isIPv6(IP)) return "IPv6";
        else return "Neither";
    }
    private boolean isIPv4(String IP){
        String[] parts = IP.split("\\.");
        if(parts.length != 4) return false;
        if(IP.charAt(0) == '.' || IP.charAt(IP.length() - 1) == '.') return false;//"." will not separate ending
        for(String p : parts){
            if(!isValidIPv4(p)) return false;
        }
        return true;
    }
    private boolean isValidIPv4(String p){
        char[] c = p.toCharArray();
        if(c.length > 3 || c.length == 0) return false;
        if(c.length > 1 && c[0] == '0') return false;//leading 0
        for(char ch : c){
            if(!Character.isDigit(ch)) return false;
        }
        int num = Integer.parseInt(p);
        if(num < 0 || num > 255) return false;
        else return true;
        
    }
    private boolean isIPv6(String IP){
        String[] parts = IP.split(":");
        if(parts.length != 8) return false;
        if(IP.charAt(0) == ':' || IP.charAt(IP.length() - 1) == ':') return false;//":" will not separate ending
        for(String p : parts){
            if(!isValidIPv6(p)) return false;
        }
        return true;
    }
    private boolean isValidIPv6(String p){
        char[] c = p.toCharArray();
        if(c.length > 4 || c.length == 0) return false;
        for(char ch:c) {
            boolean isDigit = ch - '0' >= 0 && ch - '0'<= 9;
            boolean isUppercaseAF = ch >='A' && ch<= 'F';
            boolean isLowerCaseAF = ch>= 'a' && ch<= 'f';
            if(!(isDigit || isUppercaseAF || isLowerCaseAF)) {
                return false;
        }
	}
        return true;
    }
}