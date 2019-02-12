/*
High Five

Description
There are two properties in the node student id and scores, 
to ensure that each student will have at least 5 points, 
find the average of 5 highest scores for each person.


Example
Given results = [[1,91],[1,92],[2,93],[2,99],[2,98],[2,97],[1,60],[1,58],[2,100],[1,61]]
 */
class Amazon_High_Five{
    public static class Record{
        int id;
        int score;
        public Record(int _id, int _score){
            id = _id;
            score = _score;
        }
    }
    public Map<Integer, Double> highFive(Record[] records){
        Map<Integer, PriorityQueue<Integer>> map = new HashMap<>();
        Map<Integer, Double> result = new HashMap<>();

        for(Record r : records){
            if(!map.containsKey(r.id)) map.put(r.id, new PriorityQueue<>());

            PriorityQueue<Integer> pq = map.get(r.id);
            pq.offer(r.score);
            if(pq.size() > 5) pq.poll();
        }

        for(Map.Entry<Integer, PriorityQueue<Integer>> entry : map.entrySet()){
            PriorityQueue<Integer> pq = entry.getValue();
            int sum = 0;
            int num = pq.size();
            while(!pq.isEmpty()){
                sum += pq.poll();
            }
            double avg = (double) sum / num;
            result.put(entry.getKey(), avg);
        }
        return result;
    }
}
