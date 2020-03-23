package l01.n242;//给定两个字符串 s 和 t ，编写一个函数来判断 t 是否是 s 的字母异位词。
//
// 示例 1: 
//
// 输入: s = "anagram", t = "nagaram"
//输出: true
// 
//
// 示例 2: 
//
// 输入: s = "rat", t = "car"
//输出: false 
//
// 说明: 
//你可以假设字符串只包含小写字母。 
//
// 进阶: 
//如果输入字符串包含 unicode 字符怎么办？你能否调整你的解法来应对这种情况？ 
// Related Topics 排序 哈希表


import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

//leetcode submit region begin(Prohibit modification and deletion)

/**
 * 哈希表
 */
class Solution242 {
    public boolean isAnagram(String s, String t) {
        Map<Character, Integer> map = new HashMap<>();
        for (char c : s.toCharArray()) {
            map.put(c, map.getOrDefault(c, 0) + 1);
        }
        for (char c : t.toCharArray()) {
            if (!map.containsKey(c)) {
                return false;
            }
            if (map.get(c) == 1) {
                map.remove(c);
            } else {
                map.put(c, map.get(c) - 1);
            }
        }
        return map.isEmpty();
    }
}

/**
 * 排序
 */
class Solution242_1 {
    public boolean isAnagram(String s, String t) {
        char[] cs = s.toCharArray();
        Arrays.sort(cs);

        char[] ct = t.toCharArray();
        Arrays.sort(ct);
        return String.valueOf(cs).equals(String.valueOf(ct));
    }
}
//leetcode submit region end(Prohibit modification and deletion)
