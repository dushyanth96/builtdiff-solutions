import java.util.ArrayDeque;
import java.util.Queue;

class Solution {
    // Return minutes until all fresh crates rot, or -1.
    public int solve(int[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0) {
            return 0;
        }

        int m = grid.length;
        int n = grid[0].length;
        int freshCount = 0;
        Queue<int[]> queue = new ArrayDeque<>();

        // 1. Enqueue all spoiled crates and count fresh crates
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 2) {
                    queue.offer(new int[]{i, j});
                } else if (grid[i][j] == 1) {
                    freshCount++;
                }
            }
        }

        // If no fresh crates exist initially, 0 minutes are needed
        if (freshCount == 0) {
            return 0;
        }

        int minutes = 0;
        int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

        // 2. Multi-source BFS layer by layer
        while (!queue.isEmpty() && freshCount > 0) {
            int size = queue.size();

            for (int i = 0; i < size; i++) {
                int[] curr = queue.poll();
                int r = curr[0];
                int c = curr[1];

                for (int[] dir : directions) {
                    int nr = r + dir[0];
                    int nc = c + dir[1];

                    // Check bounds and whether adjacent crate is fresh
                    if (nr >= 0 && nr < m && nc >= 0 && nc < n && grid[nr][nc] == 1) {
                        grid[nr][nc] = 2; // Spoil crate
                        freshCount--;
                        queue.offer(new int[]{nr, nc});
                    }
                }
            }
            minutes++;
        }

        // 3. If unreachable fresh crates remain, return -1
        return freshCount == 0 ? minutes : -1;
    }
}