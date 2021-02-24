public class Triangle {

    static int minimumTotal(int[][] triangle) {
        if(triangle.length <= 0 || triangle[0].length <= 0) {
            return 0;
        }
        int size = triangle.length;
            // write your code here
        int[][] dp = new int[size][triangle[size-1].length];
        for(int j=0; j < size;j++){
            dp[size-1][j] = triangle[size-1][j];
        }
        for(int i=triangle.length-2; i>=0; i--) {
            for(int j=0; j<=i; j++){
                dp[i][j] = Math.min(dp[i+1][j], dp[i+1][j+1]) + triangle[i][j];
            }
        }

        return dp[0][0];
    }

    public static void main(String[] args) {
        int[][] a = {{1},{2,3}};
        minimumTotal(a);
    }
}

