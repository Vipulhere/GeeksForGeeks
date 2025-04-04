//{ Driver Code Starts
// Initial Template for Java
import java.io.*;
import java.util.*;

class GFG {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int tc = sc.nextInt();
        while (tc-- > 0) {
            int V = sc.nextInt();
            int E = sc.nextInt();
            int[][] edges = new int[E][2];
            for (int i = 0; i < E; i++) {
                edges[i][0] = sc.nextInt();
                edges[i][1] = sc.nextInt();
            }

            Solution obj = new Solution();
            boolean ans = obj.isCycle(V, edges);
            System.out.println(ans ? "true" : "false");
            System.out.println("~");
        }
        sc.close();
    }
}

// } Driver Code Ends


class Solution {
    public boolean isCycle(int V, int[][] edges) {
        int[] parent = new int[V];
        
        // Initialize parent of each node to itself
        for (int i = 0; i < V; i++) {
            parent[i] = i;
        }

        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];

            int pu = findParent(u, parent);
            int pv = findParent(v, parent);

            if (pu == pv) {
                // Same parent => cycle
                return true;
            } else {
                // Union
                parent[pu] = pv;
            }
        }

        return false; // No cycle found
    }

    // Find with path compression
    private int findParent(int node, int[] parent) {
        if (parent[node] == node) return node;
        return parent[node] = findParent(parent[node], parent);
    }
}
