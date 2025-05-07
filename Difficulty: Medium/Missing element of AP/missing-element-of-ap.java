//{ Driver Code Starts
import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(reader.readLine().trim());

        Solution solution = new Solution();
        while (t-- > 0) {
            String input = reader.readLine().trim();
            String[] parts = input.split("\\s+");
            int[] arr = Arrays.stream(parts).mapToInt(Integer::parseInt).toArray();

            System.out.println(solution.findMissing(arr));

            System.out.println("~");
        }
    }
}

// } Driver Code Ends


// User function Template for Java

class Solution {
    public int findMissing(int[] arr) {
        int n = arr.length;
        if (n < 2) return -1;

        // Step 1: Find the minimum common difference between adjacent elements
        int diff = Math.min(arr[1] - arr[0], arr[n - 1] - arr[n - 2]);
        
        // Step 2: Traverse the array to find where the AP breaks
        for (int i = 1; i < n; i++) {
            if (arr[i] - arr[i - 1] != diff) {
                // Missing element is in between
                return arr[i - 1] + diff;
            }
        }

        // Step 3: If no element is missing, return the next element
        return arr[n - 1] + diff;
    }
}
