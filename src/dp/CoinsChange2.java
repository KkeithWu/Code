package dp;

import javax.xml.crypto.Data;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CoinsChange2 {
    public int change1(int amount, int[] coins){
        //考虑边界：amount=1输出为1，否则，coins=0时输出为0。能正确处理。
//        if (amount <= 0){
//            return 1;
//        }
//        if(coins.length == 0){
//            return 0;
//        }

        //初始化：dp[i][j]表示有1...i件物品，j个额度的可组织的个数。
        int[][] dp = new int[coins.length+1][amount+1];
        //初始化n个金币刚好摆放ok是1。
        for(int i = 0; i <= coins.length; i++){
            dp[i][0] = 1;
        }
        //初始化第一个金币.如果i定义+1的话，就可以不操作。
//        for(int j = 0; j <= amount; j++){
//            dp[0][j] = j%coins[0] == 0?1:0;
//        }


        for(int i = 1; i <= coins.length; i++){
            //只有第一件物品的时候，有几种排列方式
            for(int j = 1; j <= amount; j++){
                for(int k = j/coins[i-1]; k >= 0; k--){
                    dp[i][j] += dp[i-1][j-k*coins[i-1]];
                }
            }
        }
        return dp[coins.length][amount];
    }

    //时间复杂度优化
    public int change2(int amount, int[] coins){
        //考虑边界：amount=1输出为1，否则，coins=0时输出为0。能正确处理。
        //初始化：dp[i][j]表示有1...i件物品，j个额度的可组织的个数。
        int[][] dp = new int[coins.length+1][amount+1];
        //初始化0个金币刚好摆放ok是1。
        for(int i = 0; i <= coins.length; i++){
            dp[i][0] = 1;
        }
        //初始化第一个金币.如果i定义+1的话，就可以不操作。
//        for(int j = 0; j <= amount; j++){
//            dp[0][j] = j%coins[0] == 0?1:0;
//        }


        for(int i = 1; i <= coins.length; i++){
            for(int j = 1; j <= amount; j++){
                //此处可以依赖之前的数据.
                if(j >= coins[i-1]) {
                    dp[i][j] = dp[i-1][j] + dp[i][j-coins[i-1]];
                }else {
                    dp[i][j] = dp[i-1][j];
                }
            }
        }
        return dp[coins.length][amount];
    }

    //空间复杂度优化 只依赖dp[i-1][j]以及dp[i][0...j-1]
    public int change(int amount, int[] coins){
        //考虑边界：amount=1输出为1，否则，coins=0时输出为0。能正确处理。
        //初始化：dp[j]表示j个额度的可组合的个数。
        int[] dp = new int[amount+1];

        //初始化0个金币刚好摆放ok是1。
        dp[0]=1;

        for(int i = 1; i <= coins.length; i++){
            for(int j = 1; j <= amount; j++){
                //此处可以依赖之前的数据.
                if(j >= coins[i-1]) {
                    dp[j] = dp[j] + dp[j-coins[i-1]];
                }
            }
        }
        return dp[amount];
    }

    public static void main(String[] args) {
        CoinsChange2 client = new CoinsChange2();
        Date start = new Date();
        System.out.println( client.change1(3999, new int[]{200, 217, 234, 251, 268, 285, 302, 319, 336, 353, 370, 387, 404, 421, 438, 455, 472, 489, 506, 523, 540, 557, 574, 591, 608, 625, 642, 659, 676, 693, 710, 727, 744, 761, 778, 795, 812, 829, 846, 863, 880, 897, 914, 931, 948, 965, 982, 999, 1016, 1033, 1050, 1067, 1084, 1101, 1118, 1135, 1152, 1169, 1186, 1203, 1220, 1237, 1254, 1271, 1288, 1305, 1322, 1339, 1356, 1373, 1390, 1407, 1424, 1441, 1458, 1475, 1492, 1509, 1526, 1543, 1560, 1577, 1594, 1611, 1628, 1645, 1662, 1679, 1696, 1713, 1730, 1747, 1764, 1781, 1798, 1815, 1832, 1849, 1866, 1883, 1900, 1917, 1934, 1951, 1968, 1985, 2002, 2019, 2036, 2053, 2070, 2087, 2104, 2121, 2138, 2155, 2172, 2189, 2206, 2223, 2240, 2257, 2274, 2291, 2308, 2325, 2342, 2359, 2376, 2393, 2410, 2427, 2444, 2461, 2478, 2495, 2512, 2529, 2546, 2563, 2580, 2597, 2614, 2631, 2648, 2665, 2682, 2699, 2716, 2733, 2750, 2767, 2784, 2801, 2818, 2835, 2852, 2869, 2886, 2903, 2920, 2937, 2954, 2971, 2988, 3005, 3022, 3039, 3056, 3073, 3090, 3107, 3124, 3141, 3158, 3175, 3192, 3209, 3226, 3243, 3260, 3277, 3294, 3311, 3328, 3345, 3362, 3379, 3396, 3413, 3430, 3447, 3464, 3481, 3498, 3515, 3532, 3549, 3566, 3583, 3600, 3617, 3634, 3651, 3668, 3685, 3702, 3719, 3736, 3753, 3770, 3787, 3804, 3821, 3838, 3855, 3872, 3889, 3906, 3923, 3940, 3957, 3974, 3991, 4008, 4025, 4042, 4059, 4076, 4093, 4110, 4127, 4144, 4161, 4178, 4195, 4212, 4229, 4246, 4263, 4280, 4297, 4314, 4331, 4348, 4365, 4382, 4399, 4416, 4433, 4450, 4467, 4484, 4501, 4518, 4535, 4552, 4569, 4586, 4603, 4620, 4637, 4654, 4671, 4688, 4705, 4722, 4739, 4756, 4773, 4790, 4807, 4824, 4841, 4858, 4875, 4892, 4909, 4926, 4943, 4960, 4977, 4994}));
        Date end1 = new Date();
        System.out.println( client.change(3999,new int[]{200, 217, 234, 251, 268, 285, 302, 319, 336, 353, 370, 387, 404, 421, 438, 455, 472, 489, 506, 523, 540, 557, 574, 591, 608, 625, 642, 659, 676, 693, 710, 727, 744, 761, 778, 795, 812, 829, 846, 863, 880, 897, 914, 931, 948, 965, 982, 999, 1016, 1033, 1050, 1067, 1084, 1101, 1118, 1135, 1152, 1169, 1186, 1203, 1220, 1237, 1254, 1271, 1288, 1305, 1322, 1339, 1356, 1373, 1390, 1407, 1424, 1441, 1458, 1475, 1492, 1509, 1526, 1543, 1560, 1577, 1594, 1611, 1628, 1645, 1662, 1679, 1696, 1713, 1730, 1747, 1764, 1781, 1798, 1815, 1832, 1849, 1866, 1883, 1900, 1917, 1934, 1951, 1968, 1985, 2002, 2019, 2036, 2053, 2070, 2087, 2104, 2121, 2138, 2155, 2172, 2189, 2206, 2223, 2240, 2257, 2274, 2291, 2308, 2325, 2342, 2359, 2376, 2393, 2410, 2427, 2444, 2461, 2478, 2495, 2512, 2529, 2546, 2563, 2580, 2597, 2614, 2631, 2648, 2665, 2682, 2699, 2716, 2733, 2750, 2767, 2784, 2801, 2818, 2835, 2852, 2869, 2886, 2903, 2920, 2937, 2954, 2971, 2988, 3005, 3022, 3039, 3056, 3073, 3090, 3107, 3124, 3141, 3158, 3175, 3192, 3209, 3226, 3243, 3260, 3277, 3294, 3311, 3328, 3345, 3362, 3379, 3396, 3413, 3430, 3447, 3464, 3481, 3498, 3515, 3532, 3549, 3566, 3583, 3600, 3617, 3634, 3651, 3668, 3685, 3702, 3719, 3736, 3753, 3770, 3787, 3804, 3821, 3838, 3855, 3872, 3889, 3906, 3923, 3940, 3957, 3974, 3991, 4008, 4025, 4042, 4059, 4076, 4093, 4110, 4127, 4144, 4161, 4178, 4195, 4212, 4229, 4246, 4263, 4280, 4297, 4314, 4331, 4348, 4365, 4382, 4399, 4416, 4433, 4450, 4467, 4484, 4501, 4518, 4535, 4552, 4569, 4586, 4603, 4620, 4637, 4654, 4671, 4688, 4705, 4722, 4739, 4756, 4773, 4790, 4807, 4824, 4841, 4858, 4875, 4892, 4909, 4926, 4943, 4960, 4977, 4994}));
        Date end2 = new Date();
        System.out.printf("time1: %d, time2:%d\n", end1.getTime()-start.getTime(), end2.getTime()-end1.getTime());
    }
}
