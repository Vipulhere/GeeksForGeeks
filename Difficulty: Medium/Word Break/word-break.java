//{ Driver Code Starts
import java.io.*;
import java.util.*;

public class GfG {
    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        sc.nextLine();
        while (t-- > 0) {
            String s = sc.nextLine();
            String line = sc.nextLine();
            String[] dictionary = line.split(" ");

            Solution obj = new Solution();
            if (obj.wordBreak(s, dictionary)) {
                System.out.println("true");
            } else {
                System.out.println("false");
            }
            System.out.println("~");
        }
    }
}
// } Driver Code Ends

class Solution {
    public boolean wordBreak(String s, String[] dictionary) {
        Set<String> wordSet = new HashSet<>(Arrays.asList(dictionary));

        int n = s.length();
        boolean[] dp = new boolean[n + 1];
        dp[0] = true;

        int minLen = Integer.MAX_VALUE, maxLen = Integer.MIN_VALUE;

        for (String word : dictionary) {
            int len = word.length();
            minLen = Math.min(minLen, len);
            maxLen = Math.max(maxLen, len);
        }

        for (int i = 1; i <= n; i++) {
            // Limit j to only check words between minLen and maxLen
            for (int j = Math.max(0, i - maxLen); j <= i - minLen; j++) {
                if (dp[j] && wordSet.contains(s.substring(j, i))) {
                    dp[i] = true;
                    break;
                }
            }
        }

        return dp[n];
    }
}
