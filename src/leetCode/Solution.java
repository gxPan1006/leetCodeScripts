package leetCode;

import lombok.val;

// Temp Solution
class Solution {
    public static void main(String[] args) {
        int x = foundMaxLen("AABBAAAB", 2);
        System.out.println(x);
    }

    public static int foundMaxLen(String s, int k) {
        int maxLen = 1;
        for (int i = 0; i < s.length(); i++) {
            for (int j = i + 1; j < s.length(); j++) {
                if (k < 0) break;
                if (s.charAt(j) != s.charAt(i)) {
                    k--;
                }
                if (j - i + 1 > maxLen) maxLen = j - i + 1;
            }
        }
        return maxLen;
    }
}
