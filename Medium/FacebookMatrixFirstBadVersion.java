/*
FB - Matrix First Bad Version

题目给一个matrix 每一行左边都是0， 右边都是1 ，返回最左边1的位置。
0 0 0 0 1
0 0 1 1 1
0 0 0 1 1
0 0 0 0 1
return 2


矩阵都是0和1，每行都排序了，找出⾄少含一个1的最左列 等概率返回一个1的位置
*/
public class Main {
    
    /*
    FB - only return the leftmost column num
    Use first bad version for each row
    for each row, set l = 0, set r = the current res (min col)
    just need to see if the current row has smaller col
    
    Time: O(mlogn)
    Space: O(1)
    */
    public static int matrixFirstBadVersion1(int[][] matrix){
        if(matrix.length == 0 || matrix[0].length == 0) return -1;
        int M = matrix.length, N = matrix[0].length;
        int res = N;//no 1 at all
        
        int l = 0, r = res;
        for(int i = 0; i < M; i++){
            while(l < r){
                int m = l + (r - l)/2;
                if(matrix[i][m] == 1) r = m;
                else l = m + 1;
            }
            res = r;//r always hold 1 to the left of the last res, (can be n + 1)
            l = 0;
            //r = res;//not need actually, but the idea is use the current res as r
        }
        return res;
    }
    
    
    /*
    FB - only return the leftmost column num
    Go from topright to downleft, 
    when left is 1, go left
    else go down
    
    Time: O(m + n)
    Space: O(1)
    */
    public static int matrixFirstBadVersion2(int[][] matrix){
        if(matrix.length == 0 || matrix[0].length == 0) return -1;
        int M = matrix.length, N = matrix[0].length;
        int i = 0, j = N;//j holds the res
        
        while(i < M && j > 0){//be careful, j should be > 0 for row with all 1
            if(matrix[i][j - 1] == 1) j--;
            else i++;
        }
        
        return j;
    }
    /*
    FB - only return the leftmost column num
    Go from topright to downleft, 
    when left is 1, go left
    else go down
    
    Time: O(m + logn), but the worst case is still O(m + n) when first row has no 1
    Space: O(1)
    */
    public static int matrixFirstBadVersion3(int[][] matrix){
        if(matrix.length == 0 || matrix[0].length == 0) return -1;
        int M = matrix.length, N = matrix[0].length;
        //binary search for the first row
        int l = 0, r = N;
        while(l < r){
            int m = l + (r - l)/2;
            if(matrix[0][m] == 1) r = m;
            else l = m + 1;
        }
        
        int i = 1, j = r;//r holds 1 for first row, j will hold res
        while(i < M && j > 0){
            if(matrix[i][j - 1] == 1) j--;
            else i++;
        }
        
        return j;
    }
    /*
    FB - return a point of 1 in the leftmost column with equal probability
    Go from topright to downleft, 
    when left is 1, go left
    else go down
    
    Time: O(m + n)
    Space: O(1)
    */
    //
    public static int[] matrixFirstBadVersionReturnRandomPoint(int[][] matrix){
        if(matrix.length == 0 || matrix[0].length == 0) return null;
        int M = matrix.length, N = matrix[0].length;
        int i = 0, j = N;
        List<Integer> rowIndex = new ArrayList<>();
        
        while(i < M && j >= 0){
            if(j > 0 && matrix[i][j - 1] == 1){
                rowIndex = new ArrayList<>();
                rowIndex.add(i);
                while(j > 0 && matrix[i][j - 1] == 1) j--;//在这一行左移到头
            }else{
                if(j < N && matrix[i][j] == 1) rowIndex.add(i);//mind the bound
                i++;
            }
        }
        if(j == N) return null;//no 1 at all
        
        Random rdn = new Random();
        int index = rdn.nextInt(rowIndex.size());
        return new int[]{rowIndex.get(index), j};
    }

    //test
    public static void main(String[] args) {
        int[][] matrix = new int[4][5];
        System.out.println(matrixFirstBadVersion1(matrix));//5
        System.out.println(matrixFirstBadVersion2(matrix));//5
        System.out.println(matrixFirstBadVersion3(matrix));//5
        System.out.println(matrixFirstBadVersionReturnRandomPoint(matrix));//null
        
        matrix[0][4] = 1;
        matrix[1][4] = 1; matrix[1][3] = 1; matrix[1][2] = 1;
        matrix[2][4] = 1; 
        matrix[3][4] = 1; matrix[3][3] = 1;
        System.out.println(matrixFirstBadVersion1(matrix));//2
        System.out.println(matrixFirstBadVersion2(matrix));//2
        System.out.println(matrixFirstBadVersion3(matrix));//2
        int[] p2 = matrixFirstBadVersionReturnRandomPoint(matrix);
        System.out.println(p2[0] + " , "+ p2[1]);//[1,2]
        
        matrix[1][1] = 1; matrix[1][0] = 1; 
        System.out.println(matrixFirstBadVersion1(matrix));//0
        System.out.println(matrixFirstBadVersion2(matrix));//0
        System.out.println(matrixFirstBadVersion3(matrix));//0
        int[] p3 = matrixFirstBadVersionReturnRandomPoint(matrix);
        System.out.println(p3[0] + " , "+ p3[1]);//[1,0]
        
        matrix[3][2] = 1; matrix[3][1] = 1; matrix[3][0] = 1;
        System.out.println(matrixFirstBadVersion1(matrix));//0
        System.out.println(matrixFirstBadVersion2(matrix));//0
        System.out.println(matrixFirstBadVersion3(matrix));//0
        int[] p4 = matrixFirstBadVersionReturnRandomPoint(matrix);
        System.out.println(p4[0] + " , "+ p4[1]);//[1,0] or [3,0]
    }
}