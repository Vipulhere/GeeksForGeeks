//{ Driver Code Starts
import java.io.*;
import java.lang.*;
import java.util.*;
import java.util.stream.*;

class GFG {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());
        while (t-- > 0) {
            String s = br.readLine();
            Solution obj = new Solution();
            System.out.println(obj.countWays(s));
            System.out.println("~");
        }
    }
}
// } Driver Code Ends


// User function Template for Java
class Solution {
    // Memoization map to store results of subproblems
    static Map<String, Integer> memo = new HashMap<>();

    // Main function to be called
    static int countWays(String s) {
        memo.clear(); // Clear cache before each run (important if reused)
        return count(s, 0, s.length() - 1, true); // We want the full string to evaluate to true
    }

    // Recursive function to count ways between indices i and j to evaluate to isTrue
    static int count(String s, int i, int j, boolean isTrue) {
        // Base case: invalid range
        if (i > j) return 0;

        // Base case: single character
        if (i == j) {
            if (isTrue) return s.charAt(i) == 'T' ? 1 : 0;
            else return s.charAt(i) == 'F' ? 1 : 0;
        }

        // Memoization key
        String key = i + "_" + j + "_" + isTrue;

        // Return cached result if available
        if (memo.containsKey(key)) return memo.get(key);

        int ways = 0;

        // Loop through the string and evaluate all possible operator splits
        for (int k = i + 1; k <= j - 1; k += 2) {
            char operator = s.charAt(k);

            // Recursively count number of ways for left and right parts
            int lt = count(s, i, k - 1, true);   // Left true
            int lf = count(s, i, k - 1, false);  // Left false
            int rt = count(s, k + 1, j, true);   // Right true
            int rf = count(s, k + 1, j, false);  // Right false

            // Combine results based on operator and desired result
            if (operator == '&') {
                if (isTrue) ways += lt * rt;
                else ways += lt * rf + lf * rt + lf * rf;
            } else if (operator == '|') {
                if (isTrue) ways += lt * rt + lt * rf + lf * rt;
                else ways += lf * rf;
            } else if (operator == '^') {
                if (isTrue) ways += lt * rf + lf * rt;
                else ways += lt * rt + lf * rf;
            }
        }

        // Save the result to memo and return
        memo.put(key, ways);
        return ways;
    }
}
