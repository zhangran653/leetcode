package leetcode.editor.cn;//给定一个字符串 s 和一个整数 k，你需要对从字符串开头算起的每隔 2k 个字符的前 k 个字符进行反转。
//
// 
// 如果剩余字符少于 k 个，则将剩余字符全部反转。 
// 如果剩余字符小于 2k 但大于或等于 k 个，则反转前 k 个字符，其余字符保持原样。 
// 
//
// 
//
// 示例: 
//
// 输入: s = "abcdefg", k = 2
//输出: "bacdfeg"
// 
//
// 
//
// 提示： 
//
// 
// 该字符串只包含小写英文字母。 
// 给定字符串的长度和 k 在 [1, 10000] 范围内。 
// 
// Related Topics 字符串


//leetcode submit region begin(Prohibit modification and deletion)
class Solution541 {
    public String reverseStr(String s, int k) {
        char[] cs = s.toCharArray();
        int n = cs.length / (2 * k);
        for (int i = 0; i < n; i++) {
            reverseString(cs, i * 2 * k, i * 2 * k + k - 1);
        }
        int mod = cs.length % (2 * k);
        if (mod < k) {
            reverseString(cs, n * 2 * k, n * 2 * k + mod - 1);
        } else {
            reverseString(cs, n * 2 * k, n * 2 * k + k - 1);
        }
        return String.valueOf(cs);
    }

    public void reverseString(char[] s, int start, int end) {
        for (int i = start, j = end; i < j; i++, j--) {
            char temp = s[i];
            s[i] = s[j];
            s[j] = temp;
        }
    }
}
//leetcode submit region end(Prohibit modification and deletion)
