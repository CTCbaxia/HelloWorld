/*
Two Sum Closet

You are taking a flight and you wanna watch two movies. You are given int[] dur which includes all the movie durations. 
You are also given the duration of the flight which is d in minutes. 
Now, you need to pick two movies and the total duration of the two movies is less than or equal to (d - 30min). 
Find the pair of movies with the most total duration. If multiple found, return the pair with the longest movie.


飞机电影2Sum Closest 给一个int[] 找到离target最近(<=)的两部电影的时间 return index

Time: O(nlogn)
Space: O(1)
 */
class Amazon_Two_Sum_Closet{
    public List<Integer> closetTwo(int[] movies, int duration){
        Arrays.sort(movies);

        int lo = 0, hi = movies.length - 1;
        int maxSum = 0;
        List<Integer> res = new ArrayList<>();
        
        while(lo < hi){
            int sum = movies[lo] + movies[hi];
            if(sum > duration) hi--;
            else{
                if(sum > maxSum){
                    maxSum = sum;
                    if(res.size() == 0){
                        res.add(movies[lo]);
                        res.add(movies[hi]);
                    }else{
                        res.set(0, movies[lo]);
                        res.set(1, movies[hi]);
                    }
                    
                }
                lo++;
            }
        }
        return res;
    }
}