//{ Driver Code Starts
import java.io.*;
import java.util.*;

class IntArray {
    public static int[] input(BufferedReader br) throws IOException {
        String[] s = br.readLine().trim().split(" ");
        int n = s.length;
        int[] a = new int[n];
        for (int i = 0; i < n; i++) a[i] = Integer.parseInt(s[i]);

        return a;
    }

    public static void print(int[] a) {
        for (int e : a) System.out.print(e + " ");
        System.out.println();
    }

    public static void print(ArrayList<Integer> a) {
        for (int e : a) System.out.print(e + " ");
        System.out.println();
    }
}

class GFG {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t;
        t = Integer.parseInt(br.readLine());
        while (t-- > 0) {

            int[] arr = IntArray.input(br);

            Solution obj = new Solution();
            int res = obj.maxValue(arr);

            System.out.println(res);
            System.out.println("~");
        }
    }
}

// } Driver Code Ends


class Solution {
    int maxValue(int[] arr) {
        int n = arr.length;
        //Github -- vipulhere
        // Edge case: if there's only one house, rob it
        if (n == 1) return arr[0];

        // Helper function to calculate max value from linear houses
        return Math.max(rob(arr, 0, n - 2), rob(arr, 1, n - 1));
    }

    // Rob houses from index start to end (inclusive), like linear House Robber problem
    private int rob(int[] arr, int start, int end) {
        int prev1 = 0;  // dp[i - 1]
        int prev2 = 0;  // dp[i - 2]

        for (int i = start; i <= end; i++) {
            int pick = arr[i] + prev2;
            int skip = prev1;
            int curr = Math.max(pick, skip);
            prev2 = prev1;
            prev1 = curr;
        }

        return prev1;
    }
}
