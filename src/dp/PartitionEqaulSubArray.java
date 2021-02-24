package dp;


public class PartitionEqaulSubArray {

    public static Boolean canPartition(int[] nums)  {
        //先求sum
        int sum = 0;
        for(int i: nums){
            sum += i;
        }

        if(sum == 0 || sum%2 !=0){
            return false;
        }

        //背包问题：求重量为sum/2, 物品重量为nums的能否刚好装下。
        int len = sum/2;

        boolean dp[][] = new boolean[nums.length+1][len+1];
        for(int i=0; i <= nums.length; i++){
            dp[i][0] = true;
        }
        for(int i=1; i<= nums.length; i++){
            for(int j=1; j<= len; j++){
                //大于，可以是选或者不选。任意刚好装下那就都可以
                if(j >= nums[i-1]){
                    dp[i][j] = dp[i-1][j] || dp[i-1][j-nums[i-1]];
                }else {
                    //小于，只能不选。
                    dp[i][j] = dp[i-1][j];
                }
            }
        }

        return dp[nums.length][len];
    }

    //状态压缩
    //每次dp[i][j]的计算都只依赖与dp[i-1]数组。所以只需要一个一纬数据就够了。
    public static Boolean canPartition2(int[] nums)  {
        //先求sum
        int sum = 0;
        for(int i: nums){
            sum += i;
        }

        if(sum == 0 || sum%2 !=0){
            return false;
        }

        //背包问题：求重量为sum/2, 物品重量为nums的能否刚好装下。
        int len = sum/2;

        boolean dp[] = new boolean[len+1];
        dp[0] = true;
        for(int i=1; i<= nums.length; i++){
            //注意此处需要从后往前遍历，
            for(int j=len; j>=0; j--){
                //大于，可以是选或者不选。任意刚好装下那就都可以
                if(j >= nums[i-1]){
                    dp[j] = dp[j] || dp[j-nums[i-1]];
                }
            }
        }

        return dp[len];
    }

    public static void main(String[] args) {
        System.out.println(canPartition2(new int[]{1, 5,11,5}));
    }

}

