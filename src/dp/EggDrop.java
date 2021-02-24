package dp;

public class EggDrop {
    public int superEggDrop(int K, int N) {
        //bad case
        if(K == 1){
            return N;
        }
        if(N == 0){
            return 0;
        }
        //dp[i][j]表示有j层楼，i个鸡蛋的最小移动步数。
        int[][] dp = new int[K+1][N+1];
        //只有一个鸡蛋，顺序遍历。
        for(int j = 1; j <= N; j++){
            dp[1][j] = j;
        }

        //每一步，都遍历下所有层数，看这次从哪层下最优。
        for(int i = 2; i <= K; i++){
            for(int j = 1; j <= N; j++){
                //尝试每一层楼。
                dp[i][j] = j;
                for(int k = 1; k <= j; k++){
                    //任意层数，所以这里里面是max.尝试所有层扔，找到最小的，外面是min。
                    dp[i][j] = Math.min(Math.max(dp[i][j-k], dp[i-1][k-1]) + 1, dp[i][j]);
                }
            }
        }

        return dp[K][N];
    }

    //加入二分法
    public int superEggDrop2(int K, int N) {
        //bad case
        if(K == 1){
            return N;
        }
        if(N == 0){
            return 0;
        }
        //dp[i][j]表示有j层楼，i个鸡蛋的最小移动步数。
        int[][] dp = new int[K+1][N+1];
        //只有一个鸡蛋，顺序遍历。
        for(int j = 1; j <= N; j++){
            dp[1][j] = j;
        }

        for(int i = 2; i <= K; i++){
            for(int j = 1; j <= N; j++){
                //尝试每一层楼。
                dp[i][j] = j;
//                for(int k = 1; k <= j; k++){
//                    //任意层数，所以这里里面是max.尝试所有层扔，找到最小的，外面是min。
//                    dp[i][j] = Math.min(Math.max(dp[i][j-k], dp[i-1][k-1]) + 1, dp[i][j]);
//                }
                //因为dp[i][j-k], dp[i-1][k-1] 两个函数，当i,j固定时，一个随k单调递减，一个递增，有交点。
                // Math.max(dp[i][j-k], dp[i-1][k-1]) + 1的最小值，就是两个相等的点。
                int left = 1;
                int right = j;
                while(left <= right) {
                    int mid = (left + right) >> 1;
                    if(dp[i-1][mid-1] < dp[i][j-mid] ){
                        left = mid + 1;
                    }else {
                        right = mid - 1;
                    }
                }
                dp[i][j] = Math.max(dp[i][j-left], dp[i-1][left-1]) + 1;
            }
        }

        return dp[K][N];
    }

    //重新定义dp dp[i][j]表示有i个鸡蛋，j次移动的机会，最多可以测到的楼层数。
    public int superEggDrop3(int K, int N) {
        //bad case
        if(K == 1){
            return N;
        }
        if(N == 0){
            return 0;
        }
        //dp[i][j]表示有i个鸡蛋，j次移动的机会，最多可以测到的楼层数。
        int[][] dp = new int[K+1][N+1];

        int m = 0;
        //感觉这里复杂度接近了 KlogN
        while(dp[K][m] < N){
            m++;
            for(int i = 1; i <= K; i++){
                //总的楼层数 = 楼上的楼层数 + 楼下的楼层数 + 1（当前这层楼）
                dp[i][m] = dp[i][m-1] + dp[i-1][m-1] + 1;
            }
        }

        return m;
    }

    public static void main(String[] args) {
        EggDrop client = new EggDrop();
        System.out.println(client.superEggDrop3(8,10000));
    }
}
