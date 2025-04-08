//{ Driver Code Starts
import java.util.*;

public class Main {
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
            ArrayList<Integer> ans = obj.articulationPoints(V, edges);
            Collections.sort(ans);
            for (int val : ans) {
                System.out.print(val + " ");
            }
            System.out.println();
            System.out.println("~");
        }
    }
}
// } Driver Code Ends

class Solution {
    static int time = 0;

    public static ArrayList<Integer> articulationPoints(int V, int[][] edges) {
        List<List<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < V; i++) adj.add(new ArrayList<>());
        for (int[] edge : edges) {
            adj.get(edge[0]).add(edge[1]);
            adj.get(edge[1]).add(edge[0]);
        }

        int[] disc = new int[V];
        int[] low = new int[V];
        int[] parent = new int[V];
        boolean[] visited = new boolean[V];
        boolean[] ap = new boolean[V]; // articulation point markers

        Arrays.fill(disc, -1);
        Arrays.fill(low, -1);
        Arrays.fill(parent, -1);

        for (int i = 0; i < V; i++) {
            if (!visited[i]) {
                dfs(i, visited, disc, low, parent, ap, adj);
            }
        }

        ArrayList<Integer> res = new ArrayList<>();
        for (int i = 0; i < V; i++) {
            if (ap[i]) res.add(i);
        }

        return res.isEmpty() ? new ArrayList<>(List.of(-1)) : res;
    }

    private static void dfs(int u, boolean[] visited, int[] disc, int[] low,
                            int[] parent, boolean[] ap, List<List<Integer>> adj) {
        visited[u] = true;
        disc[u] = low[u] = ++time;
        int children = 0;

        for (int v : adj.get(u)) {
            if (!visited[v]) {
                children++;
                parent[v] = u;
                dfs(v, visited, disc, low, parent, ap, adj);

                low[u] = Math.min(low[u], low[v]);

                // Articulation point condition
                if (parent[u] == -1 && children > 1) ap[u] = true;
                if (parent[u] != -1 && low[v] >= disc[u]) ap[u] = true;

            } else if (v != parent[u]) {
                low[u] = Math.min(low[u], disc[v]);
            }
        }
    }
}
