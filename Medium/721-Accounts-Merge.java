/*
MEDIUM
721. Accounts Merge
https://leetcode.com/problems/accounts-merge/description/

TIME: 1025 - 2h
RESULT: 23% - 94ms
NOTES: 
1. sort sublist: Collections.sort(list.subList(0,3)); sublist 的排序会影响原 list 的结果
2. 这种题肯定不能来一个 merge 一个，如果两个list一开始看着没联系，但是后来有一个list能够把他们两连起来，这情况就会漏
3. 所以要建立总联系 / 找到顺序，再来 merge
*/

/*
Union Find
we need to merge something using some link -> union find

n is the total input string
Time: O(n^2) we may need to track the parent using n time for every input for worst case
Space: O(n)
RESULT: 42% - 83ms

*/
class Solution {
    public List<List<String>> accountsMerge(List<List<String>> accounts) {
        Map<String, String> parent = new HashMap<String, String>();//parent to help we find parent
        Map<String, String> owner = new HashMap<String, String>();//for account to find owner
        Map<String, Set<String>> union = new HashMap<String, Set<String>>();//<parent, all children>
        
        for(List<String> a : accounts){
            owner.put(a.get(1), a.get(0));//remember the owner of each first email (potential parent)
            for(int i = 1; i < a.size(); i++){
                parent.put(a.get(i), a.get(i)); //这样如果之后有重复 key(email)，对应的parent就会是后一个 email 的 string(地址)
                //所以key只和值内容有关？
            }
        }
        //这是一个不断向下收纳合并的过程，如果有后面的list里面的email的parent指向了前面，那么前面的parent会在最后合并到后面list的parent里
        for(List<String> a: accounts){
            String p = find(parent, a.get(1));
            for(int i = 2; i < a.size(); i++){
                parent.put(find(parent, a.get(i)), p);
            }
        }
        //union all with same parent
        for(List<String> a : accounts){
            String p = find(parent, a.get(1));
            if(!union.containsKey(p)) union.put(p, new HashSet<String>());
            for(int i = 1; i < a.size(); i++){
                union.get(p).add(a.get(i));
            }
        }
        //output the result with link to the name
        List<List<String>> result = new ArrayList<List<String>>();
        for(String e : union.keySet()){
            List<String> emails = new ArrayList<String>(union.get(e));
            Collections.sort(emails);
            emails.add(0, owner.get(e));
            result.add(emails);
        }
        return result;
        
    }
    private String find(Map<String, String> p, String s){
        //if the parent is itself, return itself, otherwise, find its final parent
        return p.get(s) == s ? s : find(p, p.get(s));
    }

}

/*
SOLUTION: REFERENCE
TIME: 0819 - 3h
RESULT: 23% - 94ms
METHOD:
Graph + DFS

THOUGHTS:
把每个 email 当成一个 node，先建立所有 email 的联系（为每个 email 都建立它自己的图谱），记录所有和他在同一个用户账户里的 emails
然后 DFS 来找联系
*/
class Solution {
    public List<List<String>> accountsMerge(List<List<String>> accounts) {
        Map<String, List<String>> emaillink = new HashMap<String, List<String>>();//<email, neighbor email>
        Map<String, String> name = new HashMap<String, String>();//<email, name>
        for(List<String> account: accounts){
            for(int i = 1; i < account.size(); i++){
                String email = account.get(i);
                if(!name.containsKey(email)) name.put(email, account.get(0));
                if(!emaillink.containsKey(email)) emaillink.put(email, new ArrayList<String>());
                if(i == 1) continue;
                
                //build the email link
                emaillink.get(email).add(account.get(i - 1));
                emaillink.get(account.get(i - 1)).add(email);
            }
        }
        
        List<List<String>> result = new ArrayList<List<String>>();
        Set<String> visited = new HashSet<String>();
        for(String email: name.keySet()){
            if(visited.add(email)){
                List<String> list = new ArrayList<String>();
                mergeHelper(emaillink, list, email, visited);
                Collections.sort(list);//sort
                list.add(0, name.get(email));//add to result
                result.add(list);                
            }
        }
        
        return result;
    }
    
    private void mergeHelper(Map<String, List<String>> emaillink, List<String> list, String email, Set<String> visited){
        list.add(email);
        for(String linkedemail : emaillink.get(email)){
            if(visited.add(linkedemail)){//if the linkedemail has not been visited before, find its connected email
                mergeHelper(emaillink, list, linkedemail, visited);
            }
        }
    }
}





基础测试：
[["John","johnsmith@mail.com","john_newyork@mail.com"],
["John","johnsmith@mail.com","john00@mail.com"],
["Mary","mary@mail.com"],
["John","johnnybravo@mail.com"]]


如果某 list 内部有重复：
[["Alex","Alex5@m.co","Alex4@m.co","Alex0@m.co"],
["Ethan","Ethan3@m.co","Ethan3@m.co","Ethan0@m.co"],
["Kevin","Kevin4@m.co","Kevin2@m.co","Kevin2@m.co"],
["Gabe","Gabe0@m.co","Gabe3@m.co","Gabe2@m.co"],
["Gabe","Gabe3@m.co","Gabe4@m.co","Gabe2@m.co"]]


如果前后 list 不重合，但是最终结果会重合：
[["David","David0@m.co","David1@m.co"],
["David","David3@m.co","David4@m.co"],
["David","David4@m.co","David5@m.co"],
["David","David2@m.co","David3@m.co"],
["David","David1@m.co","David2@m.co"]]
/*
accounts 里面的信息不超过 1000 条
不超过 10 个人
每个人不超过 30 个邮箱
*/class Solution {
    public List<List<String>> accountsMerge(List<List<String>> accounts) {
        Map<String, Integer> map = new HashMap<String, Integer>();
        List<List<String>> result = new ArrayList<List<String>>();
        for(int i = 0; i < accounts.size(); i++){
            String name = accounts.get(i).get(0);
            int index = result.size();
            for(int j = 1; j < accounts.get(i).size(); j ++){
                String email = accounts.get(i).get(j);
                if(map.containsKey(email)){
                    index = map.get(email);
                }
            }
            if(index == result.size()){
                result.add(new ArrayList<String>());
                result.get(index).add(name);
            } 
                            
            for(int j = 1; j < accounts.get(i).size(); j ++){
                String email = accounts.get(i).get(j);
                if(result.get(index).contains(email)) continue;
                else{
                    result.get(index).add(email);
                    map.put(email,index);
                }
            }
        }
        for(List l : result) Collections.sort(l.subList(1,l.size()));//注意 name 也在该 list 里面，排序的时候要除去 name
        return result;
        
    }
}
