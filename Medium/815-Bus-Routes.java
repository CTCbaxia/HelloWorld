/*
MEDIUM
815. Bus Routes

*/

/*
BFS
we only try every bus once
visit 只需要存 bus
Time: O(mn)
Space: O(mn)
*/
class Solution {
    public int numBusesToDestination(int[][] routes, int S, int T) {
        Map<Integer, List<Integer>> map = new HashMap<>();

		//build the map
		for(int i = 0; i < routes.length; i++){
			for(int j = 0; j < routes[i].length; j++){
				if(!map.containsKey(routes[i][j])) map.put(routes[i][j], new ArrayList<>());
				map.get(routes[i][j]).add(i);
			}
		}

		//do bfs
		Queue<Integer> q = new LinkedList<>();//put busStop can arrived in this level
		Set<Integer> visited = new HashSet<>();
        
        if(S == T) return 0;//if we are at the destination at the begining

        q.offer(S);
        int count = 1;
		while(!q.isEmpty()){
			Queue<Integer> newQ = new LinkedList<>();
			while(!q.isEmpty()){
				int busStop = q.poll();
				List<Integer> buses = map.get(busStop);
				for(int bus : buses){
					if(visited.contains(bus)) continue;
					visited.add(bus);
					for(int i = 0; i < routes[bus].length; i++){
						int nextStop = routes[bus][i];
						if(nextStop == T) return count;
						newQ.offer(nextStop);
					}
				}

			}
			q = newQ;
			count++;
		}
		return -1;
    }
}



/*
BFS
!! 用 Queue<bus index> 的问题是公交车不会重复，但是地点会重复，只用 visited 去重公交车的话，每次地点可能都还是要loop很大
这题的重点是公交车少于地点吧

Time: O(mn) only loop every bus for once
Space: O(mn)
*/
class Solution {
    public int numBusesToDestination(int[][] routes, int S, int T) {
        Map<Integer, List<Integer>> map = new HashMap<>();

		//build the map
		for(int i = 0; i < routes.length; i++){
			for(int j = 0; j < routes[i].length; j++){
				if(!map.containsKey(routes[i][j])) map.put(routes[i][j], new ArrayList<>());
				map.get(routes[i][j]).add(i);
			}
		}

		//do bfs
		Queue<Integer> q = new LinkedList<>();
		Set<Integer> visited = new HashSet<>();
        
        if(S == T) return 0;//if we are at the destination at the begining
		List<Integer> start = map.get(S);
		for(int i : start){
			q.offer(i);
		}
		int count = 1;
		while(!q.isEmpty()){
			Queue<Integer> newQ = new LinkedList<>();
			while(!q.isEmpty()){
				int bus = q.poll();
				for(int i = 0; i < routes[bus].length; i++){
					int busStop = routes[bus][i];

					if(busStop == T) return count;//termination
					if(visited.contains(busStop)) continue;//重点就在要尽早少一层 loop
					visited.add(busStop);

					List<Integer> nextBus = map.get(busStop);
					for(int nb : nextBus){
						newQ.offer(nb);			
					}
				}

			}
			q = newQ;
			count++;
		}
		return -1;
    }
}

