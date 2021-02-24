package dp;

//https://leetcode-cn.com/problems/longest-common-subsequence/
public class LongestCommonSubsequence {
    public int longestCommonSubsequence(String text1, String text2) {
        if(text1.length() == 0 || text2.length() == 0){
            return 0;
        }
        //dp[i][j]表示text1 0..i 子串和text2 0..j 子串的最长公共子序列
        int[][] dp = new int[text1.length()+1][text2.length()+1];
        for(int i = 1; i <= text1.length(); i++){
            for(int j =1; j <= text2.length(); j++){
                //注意减一。
                if(text1.charAt(i-1) == text2.charAt(j-1)){
                    dp[i][j] = dp[i-1][j-1] + 1;
                }else{
                    //对比哪一个值最大。
                    dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);
                }
            }
        }
        return dp[text1.length()][text2.length()];
    }

    public static void main(String[] args) {
        LongestCommonSubsequence client = new LongestCommonSubsequence();
        System.out.println(client.longestCommonSubsequence("abcde", "ace"));
    }
}
