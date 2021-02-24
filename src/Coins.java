import java.util.Arrays;

public class Coins {
    public static void main(String[] args) {
        System.out.println(coinChange(new int[]{1,2,5},10));;
    }
    public static int coinChange(int[] coins, int amount) {
        if(amount == 0) {
            return 0;
        }

        int[] dp = new int[amount+1];
        for(int i= 0; i <= amount; i++){
            dp[i] = Integer.MAX_VALUE;
        }
        //初始化
        for(int i= 0; i < coins.length; i++){
            if (dp.length > coins[i]){
                dp[coins[i]] = 1;
            }
        }
        System.out.println(Arrays.toString(dp));
        for(int i=1; i <= amount; i++){
            int min = dp[i];
            for(int j = 0; j < coins.length; j++){
                if (i > coins[j]) {
                    min = Math.min(min, dp[i-coins[j]]);
                }
            }
            if (min < dp[i]) {
                min ++;
            }
            dp[i] = min;
        }

        if (dp[amount] == Integer.MAX_VALUE) {
            return -1;
        }
        return dp[amount];
        // write your code here
    }
}
