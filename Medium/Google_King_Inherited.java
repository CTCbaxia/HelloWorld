/*
MEDIUM
Google_King_Inherited


DFS

Time: O(n)
Space: O(n)

*/
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




