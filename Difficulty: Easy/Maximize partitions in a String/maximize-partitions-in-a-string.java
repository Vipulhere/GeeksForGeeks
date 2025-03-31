//{ Driver Code Starts
import java.util.*;

public class Main {
    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        int tc = sc.nextInt();

        while (tc-- > 0) {
            String s = sc.next();
            Solution obj = new Solution();
            int ans = obj.maxPartitions(s);
            System.out.println(ans);
            System.out.println("~");
        }
    }
}
// } Driver Code Ends


class Solution {
    public int maxPartitions(String s) {
        int[] last = new int[26];
        //Github --
        // Step 1: Record the last occurrence of each character
        for (int i = 0; i < s.length(); i++) {
            last[s.charAt(i) - 'a'] = i;
        }

        int count = 0;
        int end = 0;

        // Step 2: Traverse the string to find partitions
        for (int i = 0; i < s.length(); i++) {
            end = Math.max(end, last[s.charAt(i) - 'a']);
            if (i == end) {
                count++;
            }
        }

        return count;
    }
}
