package leetCode;

import java.util.Arrays;

public class _Others {
    // 274. H指数 （有更优解）[3,0,6,1,5]
    public int hIndex(int[] citations) {
        int h = 0;
        int len = citations.length - 1;
        Arrays.sort(citations); // [0, 1, 3, 5, 6]
        while (len >= 0 && citations[len] >= h + 1) {
            h++;
            len--;
        }
        return h;
    }


    // 463. 岛屿的周长
    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {1, 0, -1, 0};

    public int islandPerimeter(int[][] grid) {
        int n = grid.length, m = grid[0].length;
        int ans = 0;
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < m; ++j) {
                if (grid[i][j] == 1) {
                    ans += dfs(i, j, grid, n, m);
                }
            }
        }
        return ans;
    }

    public int dfs(int x, int y, int[][] grid, int n, int m) {
        if (x < 0 || x >= n || y < 0 || y >= m || grid[x][y] == 0) {
            return 1;
        }
        if (grid[x][y] == 2) {
            return 0;
        }
        grid[x][y] = 2;
        int res = 0;
        for (int i = 0; i < 4; ++i) {
            int tx = x + dx[i];
            int ty = y + dy[i];
            res += dfs(tx, ty, grid, n, m);
        }
        return res;
    }
}
