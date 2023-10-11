package leetCode;

import dataStructure.basic.ListNode;

import java.util.*;

public class _1To100 {
    // 1. 两数之和 [hashMap]
    public int[] twoSum(int[] nums, int target) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(target - nums[i])) {
                return new int[]{
                        map.get(target - nums[i]), i
                };
            }
            map.put(nums[i], i);
        }
        throw new IllegalArgumentException();
    }

    // 2. 两数相加
    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode ans = new ListNode(0);
        var cur = ans;
        int carry = 0;
        while (l1 != null || l2 != null) {
            int x = l1 != null ? l1.val : 0;
            int y = l2 != null ? l2.val : 0;
            cur.next = new ListNode((x + y + carry) % 10);
            cur = cur.next;
            carry = (x + y + carry) / 10;
            if (l1 != null) l1 = l1.next;
            if (l2 != null) l2 = l2.next;
        }
        if (carry != 0) cur.next = new ListNode(carry);
        return ans.next;
    }

    // 3. 无重复字符的最长字串
    public int lengthOfLongestSubstring(String s) {
        if (s.isEmpty()) return 0;
        int i = 0;
        int j = 1;
        int maxLength = 1;
        while (i < s.length() && j < s.length()) {
            if (!s.substring(i, j).contains(s.substring(j, j + 1))) {
                j++;
                if (j - i > maxLength) {
                    maxLength = j - i;
                }
            } else {
                i = s.substring(i, j).indexOf(s.substring(j, j + 1)) + i + 1;
                j++;
            }
        }
        return maxLength;
    }

    // 5. 最长回文字符串
    public static String longestPalindrome_Stupid(String s) {
        int globalMaxLength = 1;
        String globalMaxStr = String.valueOf(s.charAt(0));

        for (int i = 0; i < s.length(); i++) {
            int localMaxLength = 1;
            String localMaxStr = String.valueOf(s.charAt(i));
            StringBuilder reverseStr = new StringBuilder("");

            for (int j = i + 1; j < s.length() + 1; j++) {
                String subStr = s.substring(i, j);
                if (subStr.contentEquals(reverseStr.replace(0, reverseStr.length(), subStr).reverse())
                        && j - i > localMaxLength) {
                    localMaxLength = j - i;
                    localMaxStr = subStr;
                }
            }
            if (localMaxLength > globalMaxLength) {
                globalMaxLength = localMaxLength;
                globalMaxStr = localMaxStr;
            }
        }
        return globalMaxStr;
    }

    public static String longestPalindrome(String s) {
        boolean[][] dp = new boolean[1000][1000];
        int maxLength = 1;
        String maxStr = s.substring(0, 1);
        for (int i = 0; i < s.length(); i++) {
            dp[i][i] = true;
        }
        for (int i = 0; i < s.length() - 1; i++) {
            if (s.charAt(i + 1) == s.charAt(i)) {
                dp[i][i + 1] = true;
                maxLength = 2;
                maxStr = s.substring(i, i + maxLength);
            }
        }
        for (int len = 3; len <= s.length(); len++) {
            for (int i = 0; i <= s.length() - len; i++) {
                int j = i + len - 1;
                if (dp[i + 1][j - 1] && s.charAt(i) == s.charAt(j)) {
                    dp[i][j] = true;
                    if (j - i + 1 > maxLength) {
                        maxLength = j - i + 1;
                        maxStr = s.substring(i, j + 1);
                    }
                }
            }
        }
        return maxStr;
    }

    // 6. N字型变换
    public String convert(String s, int numRows) {
        StringBuffer[] sb = new StringBuffer[Math.min(numRows, s.length())];
        for (int i = 0; i < sb.length; i++) {
            sb[i] = new StringBuffer();
        }
        int index = 0;
        boolean goingDown = false;
        for (char c : s.toCharArray()) {
            sb[index].append(c);
            if (index == 0 || index == numRows - 1) {
                goingDown = !goingDown;
            }
            index = index + (goingDown ? 1 : -1);
        }

        StringBuffer ans = new StringBuffer();
        for (StringBuffer row : sb) {
            ans.append(row);
        }
        return ans.toString();
    }

    // 9. 回文数 [回文数，数学]
    public boolean isPalindrome(int x) {
        if (x < 0) return false;
        int prev = x;
        int sum = 0;
        while (x != 0) {
            sum = sum * 10 + x % 10;
            x = x / 10;
        }
        return sum == prev;
    }

    // 11. 盛水最多的容器
    public int maxArea(int[] height) {
        int i = 0;
        int j = height.length - 1;
        int maxArea = Math.min(height[0], height[1]);
        while (i < j) {
            if (Math.min(height[i], height[j]) * Math.abs(j - i) > maxArea) {
                maxArea = Math.min(height[i], height[j]) * Math.abs(j - i);
            }
            if (height[i] <= height[j]) {
                i = i + 1;
            } else if (height[j] < height[i]) j = j - 1;
        }
        return maxArea;
    }

    // 13. 罗马数字转整数 [hashMap]
    public int romanToInt(String s) {
        HashMap<String, Integer> romanMap = new HashMap<>();
        int sum = 0;
        int i = 0;
        romanMap.put("I", 1);
        romanMap.put("IV", 4);
        romanMap.put("V", 5);
        romanMap.put("IX", 9);
        romanMap.put("X", 10);
        romanMap.put("XL", 40);
        romanMap.put("L", 50);
        romanMap.put("XC", 90);
        romanMap.put("C", 100);
        romanMap.put("CD", 400);
        romanMap.put("D", 500);
        romanMap.put("CM", 900);
        romanMap.put("M", 1000);

        while (i < s.length() - 1) {
            if (romanMap.containsKey(s.substring(i, i + 2))) {
                sum += romanMap.get(s.substring(i, i + 2));
                i += 2;
            } else {
                sum += romanMap.get(s.substring(i, i + 1));
                i++;
            }
        }
        if (i != s.length()) sum += romanMap.get(s.substring(i, i + 1));
        return sum;
    }

    // 14.最长公共前缀 [双指针]
    public String longestCommonPrefix(String[] strs) {
        String ans = "";
        if (strs.length == 0) return ans;

        for (int i = 0; i < strs[0].length(); i++) {
            for (int j = 1; j < strs.length; j++) {
                if (i == strs[j].length() || !strs[0].substring(i, i + 1).equals(strs[j].substring(i, i + 1)))
                    return ans;
            }
            ans = ans.concat(strs[0].substring(i, i + 1));
        }
        return ans;
    }

    // 15. 三数之和
    public List<List<Integer>> threeSum(int[] nums) {
        // 三层遍历
        // 优化1: 三个数都往前遍历，不往后遍历，i, j=i, k=j;
        // 优化2: 不重复，排序，进入的前提是和前一个不一样;
        // 优化3: 用一个右指针一次性遍历，因为第二层前一个如果满足的话，后一个的第三层匹配值一定更小。
        return new ArrayList<>();
    }

    // 17. 电话号码的字母组合
    public List<String> letterCombinations(String digits) {
        if (digits.isEmpty()) return new ArrayList<>();
        HashMap<Character, Character[]> numsMap = new HashMap<>() {{
            put('2', new Character[]{'a', 'b', 'c'});
            put('3', new Character[]{'d', 'e', 'f'});
            put('4', new Character[]{'g', 'h', 'i'});
            put('5', new Character[]{'j', 'k', 'l'});
            put('6', new Character[]{'m', 'o', 'n'});
            put('7', new Character[]{'p', 'q', 'r', 's'});
            put('8', new Character[]{'t', 'u', 'v'});
            put('9', new Character[]{'w', 'x', 'y', 'z'});
        }};

        List<String> ans = new ArrayList<>();
        int index = 0;
        getAns(ans, index, digits, numsMap, new StringBuilder());
        return ans;
    }

    private void getAns(List<String> ans, int index, String digits, HashMap<Character, Character[]> numsMap, StringBuilder roundAns) {
        if (index == digits.length()) {
            ans.add(roundAns.toString());
        }
        Character[] chars = numsMap.get(digits.charAt(index));

        for (Character i : chars) {
            roundAns.append(i);
            getAns(ans, index + 1, digits, numsMap, roundAns);
            roundAns.delete(roundAns.length() - 1, roundAns.length());
        }
    }

    // 20. 有效的括号 [栈、hashMap]
    public boolean isValid(String s) {
//        Stack<Character> stack = new Stack<>();
        Deque<Character> stack = new LinkedList<>();

        HashMap<Character, Character> markPairs = new HashMap<>();
        markPairs.put(')', '(');
        markPairs.put(']', '[');
        markPairs.put('}', '{');

        for (int i = 0; i < s.length(); i++) {
            if (markPairs.containsKey(s.charAt(i)) && markPairs.get(s.charAt(i)) == stack.peek()) {
                stack.pop();
            } else stack.push(s.charAt(i));
        }
        return stack.isEmpty();
    }

    // 21. 合并两个有序链表 [递归]
    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        if (list1 == null) {
            return list2;
        }
        if (list2 == null) {
            return list1;
        }

        if (list1.val <= list2.val) {
            list1.next = mergeTwoLists(list1.next, list2);
            return list1;
        } else {
            list2.next = mergeTwoLists(list2.next, list1);
            return list2;
        }
    }

    // 22. 括号生成
    public List<String> generateParenthesis(int n) {
        ArrayList<String> ans = new ArrayList<>();
        String roundStr = "";
        dfs(ans, n, n, roundStr);
        return ans;
    }

    private void dfs(ArrayList<String> ans, int lb, int rb, String roundStr) {
        if (rb == 0 && lb == 0) {
            ans.add(roundStr);
            return;
        }
        if (lb > 0) {
            dfs(ans, lb - 1, rb, roundStr + "(");
        }
        if (rb > 0) {
            dfs(ans, lb, rb - 1, roundStr + ")");
        }
    }

    // 26. 删除有序数组中的重复项 [双指针]
    // [0,1,2,3,1.,2,2,3',3,4]  [0,1.,1,1',1,2,2,3,3,4]
    public int removeDuplicates1(int[] nums) {
        int i = 1;
        int j = 1;
        while (i < nums.length) {
            while (nums[j] == nums[i - 1]) {
                if (j == nums.length - 1) {
                    return i;
                }
                j++;
            }
            nums[i] = nums[j];
            i++;
        }
        return i;
    }


    // 27. 移除元素
    public int removeElement(int[] nums, int val) {
        int n = nums.length;
        int left = 0;
        for (int right = 0; right < n; right++) {
            if (nums[right] != val) {
                nums[left] = nums[right];
                left++;
            }
        }
        return left;
    }

    public int DeprecatedRemoveElement(int[] nums, int val) {
        int temp;
        int right = nums.length - 1;
        int j = 0;
        for (int i = 0; i < nums.length - j; i++) {
            if (nums[i] == val) {
                if (i + j + 1 == nums.length) return nums.length - ++j;
                while (nums[right - j] == val) {
                    j++;
                }
                temp = nums[i];
                nums[i] = nums[right - j];
                nums[right - j] = temp;
                j++;
            }
        }
        return nums.length - j;
    }

    // TODO: KMP算法(先放弃了)
    // 28. 找出字符串中第一个匹配项的下标 [KMP算法]
    public int strStr(String haystack, String needle) {
        return 1;
    }

    public static void main(String[] args) {
        int[] a = new int[]{1, 2};
        int[] b = new int[]{1, 2};

    }

    // 31. 下一个排列 123546214
    public void nextPermutation(int[] nums) {
        if (nums.length < 2) return;
        int i = nums.length - 1;
        int j = nums.length - 1;
        while (i > 0) {
            if (nums[i] > nums[i - 1]) {
                i--;
                break;
            }
            i--;
            if (i == 0) {
                Arrays.sort(nums);
                return;
            }
        }
        int rminLoc = i + 1;
        while (j > i) {
            if (nums[j] > nums[i] && nums[j] < nums[rminLoc]) {
                rminLoc = j;
            }
            j--;
        }
        swap(nums, i, rminLoc);
        for (int k = i + 1, l = 0; k < nums.length; k++, l++) {
            boolean roundSorted = false;
            for (int m = i + 1; m < nums.length - 1 - l; m++) {
                if (nums[m] > nums[m + 1]) {
                    swap(nums, m, m + 1);
                    roundSorted = true;
                }
            }
            if (!roundSorted) break;
        }
    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    // 35. 搜索插入位置
    public int searchInsert(int[] nums, int target) {
        int left = 0, right = nums.length - 1;
        int mid;
        while (left <= right) {
            mid = (left + right) / 2;
            if (nums[mid] == target) {
                return mid;
            } else if (target > nums[mid]) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return left;
    }

    // 36. 有效的数组
    public boolean isValidSudoku(char[][] board) {
        int[][] rows = new int[9][9];
        int[][] column = new int[9][9];
        int[][][] subclass = new int[3][3][9];

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                char c = board[i][j];
                int index = c - '0' - 1;
                if (c != '.') {
                    rows[i][index]++;
                    column[j][index]++;
                    subclass[i / 3][j / 3][index]++;
                    if (rows[i][index] > 1 || column[j][index] > 1 || subclass[i / 3][j / 3][index] > 1) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    // 45. 跳跃游戏 【贪心算法】[2,3,1,1,4] [3,2,1,0,4]
    // 实现思路:在具体的实现中，我们维护当前能够到达的最大下标位置，记为边界。
    // 我们从左到右遍历数组，到达边界时，更新边界并将跳跃次数增加 1。
    public int jump(int[] nums) {
        int maxPos = 0;
        int border = 0;
        int steps = 0;
        for (int i = 0; i < nums.length - 1; i++) {
            maxPos = Math.max(i + nums[i], maxPos);
            if (i == border) {
                border = maxPos;
                steps++;
            }
        }
        return steps;
    }

    // 48. 旋转图像
    public void rotate(int[][] matrix) {
        int n = matrix.length;
        // 水平翻转
        for (int i = 0; i < n / 2; ++i) {
            for (int j = 0; j < n; ++j) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[n - i - 1][j];
                matrix[n - i - 1][j] = temp;
            }
        }
        // 主对角线翻转
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < i; ++j) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = temp;
            }
        }
    }

    // 49. 字母异位器分组
    public List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> map = new HashMap<>();
        for (String str : strs) {
            char[] array = str.toCharArray();
            Arrays.sort(array);
            String key = new String(array);
            List<String> list = map.getOrDefault(key, new ArrayList<>());
            list.add(str);
            map.put(key, list);
        }
        return new ArrayList<>(map.values());
    }

    // 50. 跳跃游戏 【贪心】[2,3,1,1,4] [3,2,1,0,4]
    public boolean canJump(int[] nums) {
        int reach = 0;
        for (int i = 0; i < nums.length; i++) {
            if (i > reach) return false;
            if (i + nums[i] > reach) {
                reach = i + nums[i];
            }
        }
        return true;
    }

    // 54. 螺旋矩阵
    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> ans = new ArrayList<>();

        int rows = matrix.length;
        int columns = matrix[0].length;
        int row = 0, column = 0;
        boolean[][] visited = new boolean[rows][columns];
        int totalSteps = rows * columns;

        int[][] directions = new int[][]{{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
        int index = 0;
        for (int i = 0; i < totalSteps; i++) {
            ans.add(matrix[row][column]);
            visited[row][column] = true;

            int nextRow = row + directions[index][0], nextColumn = column + directions[index][1];
            if (nextRow < 0 || nextRow >= rows || nextColumn < 0 || nextColumn >= columns || visited[nextRow][nextColumn]) {
                index = (index + 1) % 4;
            }
            row = row + directions[index][0];
            column = column + directions[index][1];
        }
        return ans;
    }

    // 56. 合并区间
    // Array.sort(intervals, (i1, i2) -> Integer.compare(i1[0], i2[0]))
    public int[][] merge(int[][] intervals) {
        if (intervals.length <= 1) {
            return intervals;
        }

        // 按照起始位置进行排序
        Arrays.sort(intervals, (i1, i2) -> Integer.compare(i1[0], i2[0]));

        List<int[]> result = new LinkedList<>();
        int[] currentInterval = intervals[0];
        result.add(currentInterval);

        for (int[] interval : intervals) {
            int currentEnd = currentInterval[1];
            int nextBegin = interval[0];
            int nextEnd = interval[1];

            if (currentEnd >= nextBegin) {  // 判断是否重叠
                currentInterval[1] = Math.max(currentEnd, nextEnd);  // 合并
            } else {  // 如果不重叠, 直接加入结果集
                currentInterval = interval;
                result.add(currentInterval);
            }
        }
        return result.toArray(new int[result.size()][]);
    }

    // 57. 插入区间
    // 两边搭，看能不能勾住
    public int[][] insert(int[][] intervals, int[] newInterval) {
        List<int[]> result = new ArrayList<>();
        int i = 0;
        int n = intervals.length;

        // 将所有在新区间左边的区间添加到结果中
        while (i < n && intervals[i][1] < newInterval[0]) {
            result.add(intervals[i]);
            i++;
        }

        // 合并所有与新区间重叠的区间
        while (i < n && intervals[i][0] <= newInterval[1]) {
            newInterval[0] = Math.min(newInterval[0], intervals[i][0]);
            newInterval[1] = Math.max(newInterval[1], intervals[i][1]);
            i++;
        }
        result.add(newInterval);

        // 将所有在新区间右边的区间添加到结果中
        while (i < n) {
            result.add(intervals[i]);
            i++;
        }

        return result.toArray(new int[result.size()][]);
    }

    // 61. 旋转链表 【链表】[适当部位截断，即是旋转]
    public ListNode rotateRight(ListNode head, int k) {
        if (head == null || head.next == null || k == 0) {
            return head;
        }

        // 1. 获取链表长度
        int n = 1;
        ListNode curr = head;
        while (curr.next != null) {
            curr = curr.next;
            n++;
        }

        // 2. 取模处理
        k = k % n;
        if (k == 0) {
            return head;
        }

        // 3. 双指针法
        ListNode first = head;
        for (int i = 0; i < k; i++) {
            first = first.next;
        }

        ListNode second = head;
        while (first.next != null) {
            first = first.next;
            second = second.next;
        }

        // 4. 旋转处理
        first.next = head;
        head = second.next;
        second.next = null;

        // 5. 返回结果
        return head;
    }


    // 70. 爬楼梯
    public int climbStairs(int n) {
        int[] dp = new int[46];
        dp[1] = 1;
        dp[2] = 2;
        for (int i = 3; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        return dp[n];
    }

    // 71. 简化路径
    public String simplifyPath(String path) {
        String[] names = path.split("/");
        Deque<String> deque = new ArrayDeque<>();

        for (String name : names) {
            if ("..".equals(name)) {
                if (!deque.isEmpty()) {
                    deque.pollLast();
                }
            } else if (name.length() > 0 && !".".equals(name)) {
                deque.offerLast(name);
            }
        }
        StringBuffer ans = new StringBuffer();
        if (deque.isEmpty()) {
            ans.append('/');
        } else {
            while (!deque.isEmpty()) {
                ans.append('/');
                ans.append(deque.pollFirst());
            }
        }
        return ans.toString();
    }

    // 73. 矩阵置0
    public void setZeroes(int[][] matrix) {
        int m = matrix.length, n = matrix[0].length;
        boolean[] row = new boolean[m];
        boolean[] col = new boolean[n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == 0) {
                    row[i] = col[j] = true;
                }
            }
        }
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (row[i] || col[j]) {
                    matrix[i][j] = 0;
                }
            }
        }
    }

    // 74. 搜索二维矩阵
    public boolean searchMatrix(int[][] matrix, int target) {
        int rows = matrix.length;
        int columns = matrix[0].length;

        int top = 0;
        int bottom = rows - 1;
        while (top <= bottom) { // 注意这里是<=
            int mid = top + (bottom - top) / 2;
            if (target >= matrix[mid][0] && (mid == rows - 1 || target < matrix[mid + 1][0]))  {
                int left = 0;
                int right = columns - 1;
                while (left <= right) { // 注意这里是<=
                    int mid2 = left + (right - left) / 2;
                    if (target == matrix[mid][mid2]) return true;
                    else if (target > matrix[mid][mid2]) {
                        left = mid2 + 1;
                    } else {
                        right = mid2 - 1;
                    }
                }
                return false; // 在当前行中没有找到目标值
            }
            else if (mid < rows - 1 && target >= matrix[mid + 1][0]) {
                top = mid + 1;
            } else {
                bottom = mid - 1;
            }
        }
        return false;
    }
    // 拉平处理，二维数组和一维数组的转化
    public boolean searchMatrix2(int[][] matrix, int target) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return false;
        }
        int m = matrix.length, n = matrix[0].length;
        int left = 0, right = m * n - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            int midValue = matrix[mid / n][mid % n];
            if (midValue == target) {
                return true;
            } else if (midValue < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return false;
    }


    // 80. 删除有序数组中的重复项 [0,0,1,1,1,2,2,3,3,4]
    // 我们可以让指针 i 遍历整个数组。
    // 初始时，我们将 j 设置为2（因为前两个元素总是有效的）。
    // 当 i 遍历数组时，我们将 nums[i] 与新数组的最后第二个元素 nums[j-2] 进行比较。
    // 如果 nums[i] 不等于 nums[j-2]，则说明 nums[i] 可以加入新数组，因为它还没有重复两次。
    // 如果 nums[i] 等于 nums[j-2]，则我们简单地跳过 nums[i]，因为新数组中已经有两个重复的 nums[i]。
    public int removeDuplicates(int[] nums) {
        int n = nums.length;
        if (n <= 2) {
            return n;
        }
        int slow = 2, fast = 2;
        while (fast < n) {
            if (nums[slow - 2] != nums[fast]) {
                nums[slow] = nums[fast];
                ++slow;
            }
            ++fast;
        }
        return slow;
    }

    // 88. 合并两个有序数组  1 2 3    2 5 6
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int[] newNums = new int[m + n];
        int k = 0, i = 0, j = 0;
        while (k < m + n) {
            if (i < m && (j >= n || nums1[i] <= nums2[j])) {
                newNums[k++] = nums1[i++];
            } else {
                newNums[k++] = nums2[j++];
            }
        }

        if (m + n >= 0) System.arraycopy(newNums, 0, nums1, 0, m + n);
    }

    // wrong way
    public void wrongMerge(int[] nums1, int m, int[] nums2, int n) {
        int[] newNums = new int[m + n];
        int k = 0, i = 0, j = 0;
        while (k < m + n) {
            if (j >= n || nums1[i] <= nums2[j]) {
                newNums[k] = nums1[i++];
            } else if (i >= m || nums1[i] > nums2[j]) {
                newNums[k] = nums2[j++];
            }
            k++;
        }
        nums1 = newNums;
    }

    // 92. 反转链表2
    public ListNode reverseBetween(ListNode head, int left, int right) {
        ListNode ans = new ListNode(-1); // 虚拟节点
        ans.next = head;
        ListNode prev = ans; // Start from the dummy node
        for (int i = 0; i < left - 1; i++) {
            prev = prev.next;
        }

        ListNode outerLeft = prev;
        ListNode leftEdge = prev.next;

        for (int i = 0; i < right - left + 1; i++) {
            prev = prev.next;
        }
        ListNode rightEdge = prev;
        ListNode outerRight = prev.next;

        rightEdge.next = null;

        ListNode a = reverseList(leftEdge);

        outerLeft.next = a;
        leftEdge.next = outerRight;
        return ans.next;
    }

    private ListNode reverseList(ListNode head) {
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
}








































































