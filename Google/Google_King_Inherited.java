/*
MEDIUM
Google_King_Inherited
给一个国王家的family tree （n-ary tree），王位继承是先传国王最年长的儿子，
假如最年长儿子死了，就传给死儿子最年长的儿子。。。如果这些人都不存在，再考虑国王次年长的儿子，以此类推。
要求设计这样一棵树，死掉的人不要求删除，实现birth（）和输出王位继承顺序的method（死掉的人不在继承顺序结果里）
*/
/*
Build Family Tree + DFS get Order
Map<name, node>
node king

Time: 
	birth: O(1)
	death: O(1)
	getOrder: O(n)
Space: 
	O(n)
*/

//tree
// "static void main" must be defined in a public class.
class Google_King_Inherited2 {
    public class FamilyNode{
        String name;
        List<FamilyNode> children;
        public FamilyNode(String _name){
            name = _name;
        }
    }
    FamilyNode king;
    Map<String, FamilyNode> map;
    public Google_King_Inherited2(){
        king = new FamilyNode("king");
        map = new HashMap<>();
        map.put("king", king);
    }

    public void birth(String parent, String child){
        if(!map.containsKey(parent)){
            System.out.println("invalid input:");
            return;
        }
        FamilyNode p = map.get(parent);
        FamilyNode c = new FamilyNode(child);
        if(p.children == null) p.children = new ArrayList<FamilyNode>();
        p.children.add(c);//update tree
        map.put(child, c);//update map
    } 

    public void death(String name){
        if(!map.containsKey(name)){
            System.out.println("invalid input");
            return;
        }
        map.remove(name);
    }
    public List<String> getOrder(){
        List<String> res = new ArrayList<>();
        dfs(king, res);
        return res;
    }
    public void dfs(FamilyNode node, List<String> res){
        if(map.containsKey(node.name)) res.add(node.name);//alive
        if(node.children == null) return;

        List<FamilyNode> children = node.children;
        for(FamilyNode child : children){
            dfs(child, res);
        }
    }
   public static void main(String[] args) {
        System.out.println("Hello World!");
        Google_King_Inherited2 test = new Google_King_Inherited2();
        test.birth("king", "big");
        test.birth("big", "bb");
        test.birth("king", "medium");
        test.birth("big", "bbb");
        test.birth("big", "bbbb");
        test.birth("medium", "mm");
        test.birth("king", "small");
        test.birth("small", "ss");
        test.birth("small", "sss");
                
        System.out.println(test.getOrder());
        test.death("big");
        test.birth("bbb","ccc");
        System.out.println(test.getOrder());
        test.death("bbb");
        System.out.println(test.getOrder());
        /*
        [king, big, bb, bbb, bbbb, medium, mm, small, ss, sss]
		[king, bb, bbb, ccc, bbbb, medium, mm, small, ss, sss]
		[king, bb, ccc, bbbb, medium, mm, small, ss, sss]
		*/
    }



}






class Google_King_Inherited{
	Map<String, List<String>> map;
	Set<String> dead;
	String king = “king”;
	public Google_King_Inherited(){
		map = new HashMap<>();
		dead = new HashSet<>();
		map.put(king, new ArrayList<>());
	}

	public void birth(String parent, String name){
		if(!map.containsKey(parent)){
			System.out.println(“invalid input”);
		}
		List<String> children = map.get(parent);
		children.add(name);

		map.put(name, ArrayList<String>());
	} 

	public void death(String name){
		if(!map.containsKey(name)){
			System.out.println(“invalid input”);
		}
		dead.add(name);
	}
	public List<String> getOrder(){
		List<String> res = new ArrayList<>();
		dfs(king, res);
		return res;
	}
	private void dfs(String name, List<String> res){
		if(!dead.contains(name)) res.add(name);

		List<String> children = map.get(name);
		for(String child : children){
			dfs(child, res);
		}
		return;
	}
}
