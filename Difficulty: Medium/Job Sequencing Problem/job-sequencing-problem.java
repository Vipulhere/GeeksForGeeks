//{ Driver Code Starts
// Initial Template for Java
import java.util.*;


// } Driver Code Ends

import java.util.*;

class Solution {
    public ArrayList<Integer> jobSequencing(int[] deadline, int[] profit) {
        int n = deadline.length;

        // Pair each job with (deadline, profit)
        int[][] jobs = new int[n][2];
        for (int i = 0; i < n; i++) {
            jobs[i][0] = deadline[i];
            jobs[i][1] = profit[i];
        }

        // Sort jobs by profit in descending order
        Arrays.sort(jobs, (a, b) -> b[1] - a[1]);

        // Find max deadline to define size of slot array
        int maxDeadline = 0;
        for (int i = 0; i < n; i++) {
            maxDeadline = Math.max(maxDeadline, deadline[i]);
        }

        // To keep track of occupied time slots
        boolean[] slots = new boolean[maxDeadline + 1]; // 1-based indexing

        int countJobs = 0;
        int maxProfit = 0;

        for (int[] job : jobs) {
            int d = job[0];
            int p = job[1];

            // Find the latest available time slot ≤ deadline
            for (int t = d; t >= 1; t--) {
                if (!slots[t]) {
                    slots[t] = true; // mark time slot as taken
                    countJobs++;
                    maxProfit += p;
                    break;
                }
            }
        }

        ArrayList<Integer> result = new ArrayList<>();
        result.add(countJobs);
        result.add(maxProfit);
        return result;
    }
}



//{ Driver Code Starts.

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int t = Integer.parseInt(sc.nextLine().trim());

        while (t-- > 0) {
            String[] deadlineInput = sc.nextLine().trim().split("\\s+");
            int[] deadline =
                Arrays.stream(deadlineInput).mapToInt(Integer::parseInt).toArray();

            String[] profitInput = sc.nextLine().trim().split("\\s+");
            int[] profit =
                Arrays.stream(profitInput).mapToInt(Integer::parseInt).toArray();
            Solution obj = new Solution();
            ArrayList<Integer> result = obj.jobSequencing(deadline, profit);
            System.out.println(result.get(0) + " " + result.get(1));
            System.out.println("~");
        }

        sc.close();
    }
}
// } Driver Code Ends