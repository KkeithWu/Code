package dp;

public class MinEditDistance {
    public int minDistance(String word1, String word2) {
        //边界条件 word1或者word2为空串,就是全部删除/增加。
        if(word1.length() == 0) {
            return word2.length();
        }
        if(word2.length() == 0){
            return word1.length();
        }

        //初始化：dp[i][j]表示word1 第1..i的串和word2 第1..j的串的编辑距离。
        int[][] dp = new int[word1.length()+1][word2.length()+1];
        //bad case。 某个串先走完，另一个串直接删除即可。
        for(int i =0; i <= word1.length(); i++){
            dp[i][0] = i;
        }
        for(int j =0; j <= word2.length(); j++){
            dp[0][j] = j;
        }

        for(int i =1; i <= word1.length(); i++){
            for(int j =1; j <= word2.length(); j++){
                if(word1.charAt(i-1) == word2.charAt(j-1)){
                    //不需要改动。
                    dp[i][j] = dp[i-1][j-1];
                }else {
                    //增加，删除，修改中选择一个编辑距离最小的
                    dp[i][j] = Math.min(Math.min(dp[i-1][j], dp[i][j-1]), dp[i-1][j-1]) + 1;
                }
            }
        }
        return dp[word1.length()][word2.length()];
    }

    public static void main(String[] args) {
        MinEditDistance client = new MinEditDistance();
        System.out.println(client.minDistance("horse", "ros"));
    }
}
