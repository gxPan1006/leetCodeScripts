package leetCode;

import java.util.Arrays;

public class _Others {
    // 209. 长度最小的子数组
    public int minSubArrayLen(int target, int[] nums) {
        int i = 0, j = 0, sum = 0;
        int len = nums.length;
        int minRange = Integer.MAX_VALUE;
        while (j < len) {
            sum += nums[j];
            while (sum >= target) {
                minRange = Math.min(minRange, j - i + 1);
                sum -= nums[i];
                i++;
            }
            j++;
        }
        return minRange != Integer.MAX_VALUE ? minRange : 0;
    }

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

    // 392. 判断子序列 [双指针]
    public boolean isSubsequence(String s, String t) {
        int n = s.length(), m = t.length();
        int i = 0, j = 0;
        while (i < n && j < m) {
            if (s.charAt(i) == t.charAt(j)) {
                i++;
            }
            j++;
        }
        return i == n;
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
