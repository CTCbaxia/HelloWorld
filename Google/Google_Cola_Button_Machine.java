/*
有一系列按钮，每个按钮按下去会得到一定体积范围的可乐。先给定一个目标体积范围，问不限制按按钮次数，能否确定一定能得到目标范围内的可乐？
举例：有三个按钮，按下去得到的范围是[100, 120], [200, 240] , [400, 410],
假设目标是[100, 110], 那答案是不能。因为按下一，可能得到120体积的可乐，不在目标范围里。
假设目标是[90, 120]，那答案是可以。因为按下一，一定可以得到此范围内的可乐。
假设目标是[300, 360], 那答案是可以，因为按下一再按二，一定可以得到此范围内
假设目标是[310, 360], 那答案是不能，因为按下一再按二，有可能得到300，永远没可能确定得到这个范围内的可乐。
假设目标是[1, 9999999999]，那答案是可以。随便按一个都确定满足此范围。
*/
public class Main {
    public static void main(String[] args) {
        System.out.println("Hello World!");
        List<List<Integer>> buttons = new ArrayList<>();
        buttons.add(Arrays.asList(10, 12));
        buttons.add(Arrays.asList(20, 24));
        buttons.add(Arrays.asList(40, 41));
        List<Integer> t1 = new ArrayList<>();
        t1.add(10);
        t1.add(11);
        List<Integer> t2 = new ArrayList<>();
        t2.add(9);
        t2.add(12);
        List<Integer> t3 = new ArrayList<>();
        t3.add(30);
        t3.add(36);
        System.out.println(coke(buttons, t1));//false
        System.out.println(coke(buttons, t2));//true
        System.out.println(coke(buttons, t3));//true
    }
    public static boolean coke(List<List<Integer>> buttons, List<Integer> target) {
    int m = target.get(0);
    int n = target.get(1);
    boolean[][] dp = new boolean[m + 1][n + 1];
    
    //Init
    for (int i = 0; i <= m; ++i) {
      for (int j = 0; j <= n; ++j) {
        for (List<Integer> button : buttons) {
          if (i <= button.get(0) && j >= button.get(1)) {
            dp[i][j] = true;
            break;
          }
        }
      }
    }
  
    for (int i = 0; i <= m; ++i) {
      for (int j = 0; j <= n; ++j) {
        for (List<Integer> button : buttons) {
          int preL = i - button.get(0);
          int preR = j - button.get(1);
          if (preL >= 0 && preR >= 0 && dp[preL][preR]) {
            dp[i][j] = true;
           break;
          }
        }
      }
    }
    
    return dp[m][n];
  }
}



//DFS + MEMO
public class Main {
    public static void main(String[] args) {
        System.out.println("Hello World!");
        List<List<Integer>> buttons = new ArrayList<>();
        buttons.add(Arrays.asList(10, 12));
        buttons.add(Arrays.asList(20, 24));
        buttons.add(Arrays.asList(40, 41));
        List<Integer> t1 = new ArrayList<>();
        t1.add(10);
        t1.add(11);
        List<Integer> t2 = new ArrayList<>();
        t2.add(9);
        t2.add(12);
        List<Integer> t3 = new ArrayList<>();
        t3.add(30);
        t3.add(36);
        System.out.println(coke(buttons, t1));//false
        System.out.println(coke(buttons, t2));//true
        System.out.println(coke(buttons, t3));//true
    }
    public static boolean coke(List<List<Integer>> buttons, List<Integer> target) {
        
    }
    public static boolean dfs(List<Soda> sodas, int volumeLower, int volumeUpper,
                              int targetLower, int targetUpper, Map<String, Boolean> memo) {

        Boolean val = memo.get(volumeLower + "-" + volumeUpper);
        if (val != null) {
            return val;
        }
         
        if (volumeLower >= targetLower && volumeUpper <= targetUpper) {
            return true;
        }
        if (volumeUpper > targetUpper) {
            return false;
        }
         // if (volumeUpper - volumeLower > targetUpper - targetLower) return false;
        for (Soda soda : sodas) {
            if (dfs(sodas, volumeLower + soda.lower, volumeUpper + soda.upper, targetLower, targetUpper, memo)) {//false的子问题会重复计算
                memo.put(volumeLower + "-" + volumeUpper, true);
                return true;
            }
        }
         
        memo.put(volumeLower + "-" + volumeUpper, false);
        return false;
    }


}


