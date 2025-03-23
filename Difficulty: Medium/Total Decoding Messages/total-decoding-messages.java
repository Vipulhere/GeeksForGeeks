//{ Driver Code Starts
// Initial Template for Java
import java.io.*;
import java.lang.*;
import java.util.*;

class GFG {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        int T = Integer.parseInt(br.readLine().trim());
        while (T-- > 0) {
            String digits = br.readLine().trim();
            Solution ob = new Solution();
            int ans = ob.countWays(digits);
            out.println(ans);

            out.println("~");
        }
        out.close();
    }
}

// } Driver Code Ends


// User function Template for Java
class Solution {
    //Github -- vipulhere
    public int countWays(String digits) {
        int n = digits.length();
        if (n == 0 || digits.charAt(0) == '0') return 0;

        int[] dp = new int[n + 1];
        dp[0] = 1; // empty string
        dp[1] = 1; // first character is valid (we checked above)

        for (int i = 2; i <= n; i++) {
            char oneDigit = digits.charAt(i - 1);
            String twoDigits = digits.substring(i - 2, i);

            // Check if single digit is valid (1-9)
            if (oneDigit != '0') {
                dp[i] += dp[i - 1];
            }

            // Check if two-digit number is valid (10-26)
            int val = Integer.parseInt(twoDigits);
            if (val >= 10 && val <= 26) {
                dp[i] += dp[i - 2];
            }
        }

        return dp[n];
    }
}
