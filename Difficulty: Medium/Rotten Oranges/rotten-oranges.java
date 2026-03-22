import java.util.*;

class Solution {
    public int orangesRot(int[][] mat) {
        int n = mat.length;
        int m = mat[0].length;

        Queue<int[]> q = new LinkedList<>();
        int fresh = 0;

        // Count fresh oranges and add rotten oranges to queue
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (mat[i][j] == 2) {
                    q.offer(new int[]{i, j});
                } else if (mat[i][j] == 1) {
                    fresh++;
                }
            }
        }

        // No fresh oranges already
        if (fresh == 0) return 0;

        int time = 0;
        int[] dr = {-1, 1, 0, 0};
        int[] dc = {0, 0, -1, 1};

        while (!q.isEmpty()) {
            int size = q.size();
            boolean rottenThisRound = false;

            for (int k = 0; k < size; k++) {
                int[] curr = q.poll();
                int r = curr[0];
                int c = curr[1];

                for (int d = 0; d < 4; d++) {
                    int nr = r + dr[d];
                    int nc = c + dc[d];

                    if (nr >= 0 && nr < n && nc >= 0 && nc < m && mat[nr][nc] == 1) {
                        mat[nr][nc] = 2;
                        fresh--;
                        q.offer(new int[]{nr, nc});
                        rottenThisRound = true;
                    }
                }
            }

            if (rottenThisRound) time++;
        }

        return fresh == 0 ? time : -1;
    }
}