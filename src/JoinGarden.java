
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Map;
import java.util.List;


public class JoinGarden {
    public static void main(String[] args) {
        System.out.println( isBuild(10));;
    }
    public static  String isBuild(int x) {
        if (x == 0) {
            return "NO";
        }
        boolean[] dp = new boolean[x+1];
        dp[3] = true;
        dp[8] = true;
        for(int i = 8; i <= x; i++) {
            dp[i] = dp[i-3] || dp[i-7];
        }
        if (dp[x]) {
            return "YES";
        }else {
            return "NO";
        }
        // write you code here
    }
}
