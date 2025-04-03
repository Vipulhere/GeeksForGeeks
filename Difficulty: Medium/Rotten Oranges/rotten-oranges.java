//{ Driver Code Starts
import java.io.*;
import java.lang.*;
import java.util.*;

class GFG {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();

        while (t-- > 0) {
            int n = sc.nextInt();
            int m = sc.nextInt();

            int mat[][] = new int[n][m];
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) mat[i][j] = sc.nextInt();
            }
            Solution obj = new Solution();
            int ans = obj.orangesRotting(mat);
            System.out.println(ans);
            System.out.println("~");
        }
    }
}
// } Driver Code Ends

class Solution {
    public int orangesRotting(int[][] mat) {
        int rows = mat.length;
        int cols = mat[0].length;
        Queue<int[]> queue = new LinkedList<>();
        int freshCount = 0;

        // Step 1: Initialize queue with all rotten oranges
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (mat[i][j] == 2) {
                    queue.offer(new int[]{i, j});
                } else if (mat[i][j] == 1) {
                    freshCount++;
                }
            }
        }

        // Edge case: No fresh orange
        if (freshCount == 0) return 0;

        int[][] directions = {{-1,0},{1,0},{0,-1},{0,1}};
        int minutes = -1;

        // Step 2: Multi-source BFS
        while (!queue.isEmpty()) {
            int size = queue.size();
            minutes++;

            for (int i = 0; i < size; i++) {
                int[] curr = queue.poll();
                int x = curr[0], y = curr[1];

                for (int[] dir : directions) {
                    int newX = x + dir[0];
                    int newY = y + dir[1];

                    if (newX >= 0 && newY >= 0 && newX < rows && newY < cols && mat[newX][newY] == 1) {
                        mat[newX][newY] = 2;
                        freshCount--;
                        queue.offer(new int[]{newX, newY});
                    }
                }
            }
        }

        return freshCount == 0 ? minutes : -1;
    }
}
