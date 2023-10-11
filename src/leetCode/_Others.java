package leetCode;

import dataStructure.basic.ListNode;
import dataStructure.basic.TreeNode;

import java.util.*;

public class _Others {
    // 205. 同构字符串
    public boolean isIsomorphic(String s, String t) {
        HashMap<Character, Character> s2t = new HashMap<>();
        HashMap<Character, Character> t2s = new HashMap<>();

        for (int i = 0; i < s.length(); i++) {
            if (s2t.containsKey(s.charAt(i)) && s2t.get(s.charAt(i)) != t.charAt(i) ||
                    t2s.containsKey(t.charAt(i)) && t2s.get(t.charAt(i)) != s.charAt(i)) {
                return false;
            }
            s2t.put(s.charAt(i), t.charAt(i));
            t2s.put(t.charAt(i), s.charAt(i));
        }
        return true;
    }

    // 206. 反转链表
    public ListNode reverseList(ListNode head) {
        ListNode prev = null;
        ListNode cur = head;
        while (cur != null) {
            ListNode next = cur.next;
            cur.next = prev;
            prev = cur;
            cur = next;
        }
        return prev;
    }

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

    // 222. 完全二叉树的节点个数
    public int countNodes(TreeNode root) {
        return root == null ? 0 : countNodes(root.left) + countNodes(root.right) + 1;
    }

    // 238. 除自身以外数组的乘积
    public int[] productExceptSelf(int[] nums) {
        int n = nums.length;
        int[] left = new int[n];
        int[] right = new int[n];
        int[] result = new int[n];

        left[0] = 1;
        for (int i = 1; i < n; i++) {
            left[i] = left[i - 1] * nums[i - 1];
        }

        right[n - 1] = 1;
        for (int i = n - 2; i >= 0; i--) {
            right[i] = right[i + 1] * nums[i + 1];
        }

        for (int i = 0; i < n; i++) {
            result[i] = left[i] * right[i];
        }

        return result;
    }

    // 240.搜索二维矩阵
    // 从左下角遍历，每次能排除一行或者一列
    public boolean searchMatrix(int[][] matrix, int target) {
        if (matrix.length * matrix[0].length <= 1) return matrix[0][0] == target;
        int i = matrix.length - 1;
        int j = 0;
        while (i > 0 && j < matrix[0].length) {
            if (target > matrix[i][j]) {
                j++;
            } else if (target < matrix[i][j]) {
                i--;
            } else return true;
        }
        return false;
    }

    // 242. 有效的字母异位
    public static boolean isAnagram(String s, String t) {
        HashMap<Character, Integer> count = new HashMap<>();
        int i = 0, j = 0;
        while (i < s.length()) {
            if (!count.containsKey(s.charAt(i))) {
                count.put(s.charAt(i), 1);
            } else {
                count.put(s.charAt(i), count.get(s.charAt(i)) + 1);
            }
            i++;
        }
        while (j < t.length()) {
            if (count.containsKey(t.charAt(j))) {
                if (count.get(t.charAt(j)) - 1 < 0) return false;
                else if (count.get(t.charAt(j)) - 1 == 0) count.remove(t.charAt(j));
                else count.put(t.charAt(j), count.get(t.charAt(j)) - 1);
            } else return false;
            j++;
        }
        return count.isEmpty();
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

    // 290. 单词规律
    // 输入: pattern = "abba", s = "dog cat cat dog"
    // 输出: true
    public boolean wordPattern(String pattern, String s) {
        HashMap<Character, String> char2Str = new HashMap<>();
        HashMap<String, Character> str2Char = new HashMap<>();
        int start = 0, end;
        for (int i = 0; i < pattern.length(); i++) {
            end = start;
            if (end > s.length()) return false;
            while (end < s.length() && s.charAt(end) != ' ') {
                end++;
            }
            String word = s.substring(start, end);
            if (char2Str.containsKey(pattern.charAt(i)) && !char2Str.get(pattern.charAt(i)).equals(word)
                    || str2Char.containsKey(word) && !str2Char.get(word).equals(pattern.charAt(i))) {
                return false;
            }
            char2Str.put(pattern.charAt(i), word);
            str2Char.put(word, pattern.charAt(i));
            start = end + 1;
        }
        return start == s.length();
    }

    // 380. O(1) 时间插入、删除和获取随机元素
    // 本来可以用一个hashMap来实现，但由于需要随机get，hashMap实现不了，只能用HashMap + ArrayList
    // 随机数： Random random = new Random(); random.nextInt();
    class RandomizedSet {
        private Map<Integer, Integer> dict;
        private List<Integer> list;
        private Random rand = new Random();

        /**
         * Initialize your data structure here.
         */
        public RandomizedSet() {
            dict = new HashMap<>();
            list = new ArrayList<>();
        }

        /**
         * Inserts a value to the set. Returns true if the set did not already contain the specified element.
         */
        public boolean insert(int val) {
            if (dict.containsKey(val)) return false;

            dict.put(val, list.size());
            list.add(list.size(), val);
            return true;
        }

        /**
         * Removes a value from the set. Returns true if the set contained the specified element.
         */
        public boolean remove(int val) {
            if (!dict.containsKey(val)) return false;

            int lastElement = list.get(list.size() - 1);
            int idx = dict.get(val);

            list.set(idx, lastElement);
            dict.put(lastElement, idx);

            list.remove(list.size() - 1);
            dict.remove(val);
            return true;
        }

        /**
         * Get a random element from the set.
         */
        public int getRandom() {
            return list.get(rand.nextInt(list.size()));
        }
    }


    // 383. 赎金信
    public boolean canConstruct(String ransomNote, String magazine) {
        if (ransomNote.length() > magazine.length()) {
            return false;
        }
        int[] cnt = new int[26];
        for (char c : magazine.toCharArray()) {
            cnt[c - 'a']++;
        }
        for (char c : ransomNote.toCharArray()) {
            cnt[c - 'a']--;
            if (cnt[c - 'a'] < 0) {
                return false;
            }
        }
        return true;
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

    // 452. 用最少数量的箭引爆气球
    public int findMinArrowShots(int[][] points) {
        if (points == null || points.length == 0) return 0;

        // 按照结束点排序
        Arrays.sort(points, (a, b) -> Integer.compare(a[1], b[1]));

        int arrows = 1;
        int end = points[0][1];
        for (int i = 1; i < points.length; i++) {
            // 如果箭能击中当前气球
            if (points[i][0] <= end) {
                continue;
            }
            // 否则，我们需要一支新的箭，并更新箭的位置为当前气球的结束点
            arrows++;
            end = points[i][1];
        }

        return arrows;
    }
}
