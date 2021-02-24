package dp;

/**
 首先我们尝试每戳破一个气球，以该气球为边界将气球数组分为两部分，使用这两部分的解来求解原问题。
 我们设戳破区间 i 到 j 间的气球我们得到的最大金币数为coin。及coin = def( i , j )。
 则当我们戳破气球 k 时，两边区间的最大值分别是 def( i , k-1 ) 与 def( k+1 , j )。
 此时我们发现了问题，因为戳破了气球 k ，气球数组的相邻关系发生了改变，k-1 与 k+1 原本都与 k 相邻，而 k 戳破后他们两个直接相邻了。而且先戳破 k+1 与先戳破 k-1 得到的结果将完全不同，也就是说两个子问题间发生了依赖。如果先戳破 k-1 ，则 k+1 左边的相邻气球变成了 k-2；反之 k-1 右边相邻的气球变成了 k+2 。
 子问题的处理顺序将影响到每个子问题的解，这将使我们的状态转移方程极为复杂和低效，我们应当换一种划分子问题的方式，使每个子问题都是独立的。
 那么我们换一种划分方式，既然两个子问题都依赖 k 和两个边界，那么我们划分子问题时，k 与两个边界的气球我们都不戳破，求出 i+1 到 k-1 与 k+1 到 j-1 之间的解。这样两个子问题间的依赖便被消除了，两个边界及气球 k 不被戳破，两个子问题的依赖都不会越过 k 到另一个子问题上，子问题间是相互独立的。
 并且在两个子问题解决后，气球序列还剩下 k 与两个边界的气球没有戳破，那么我们用两个子问题的解与戳破 k 与两个边界的最大值即可求出原问题的解。
 那么 def( i , j ) 函数的定义则为，不戳破 i 与 j ，仅戳破 i 与 j 之间的气球我们能得到的最大金币数。
 **/

//状态转移方程 def( i, j ) = max { def( i , k ) + def( k , j )+nums[ i ][ j ][ k ] } | i<k<j 的实现
//https://leetcode-cn.com/problems/burst-balloons/solution/chao-xiang-xi-hui-su-dao-fen-zhi-dao-dp-by-niu-you/
public class BurstBalloons {
    public int maxCoins(int[] nums) {
        int length = nums.length;
        //添加虚拟边界
        int[] nums2 = new int[length+2];
        System.arraycopy(nums, 0, nums2, 1, length);
        nums2[0] = nums2[length+1] = 1;
        length = nums2.length;
        //dp[i][j]定义开区间（i,j）之间的最大金币，最后才戳破k， dp[i][j] = dp[i][k]+ dp[k][j] + nums[i]*nums[j]*nums[k] （i<k<j）
        int[][] dp = new int[length][length];
        for(int i = length-3; i >= 0; i--){
            for(int j = i+2; j < length; j++){
               int max = 0;
               for(int k = i+1; k < j; k++){
                   max = Math.max(max, dp[i][k]+dp[k][j]+nums2[i]*nums2[j]*nums2[k]);
               }
               dp[i][j] = max;
            }
        }
        return dp[0][length-1];
    }

    public static void main(String[] args) {
        BurstBalloons client = new BurstBalloons();
        System.out.println(client.maxCoins(new int[]{3,1,5,8}));
    }
}
