package leetcode.editor.cn;//给你一份『词汇表』（字符串数组） words 和一张『字母表』（字符串） chars。
//
// 假如你可以用 chars 中的『字母』（字符）拼写出 words 中的某个『单词』（字符串），那么我们就认为你掌握了这个单词。 
//
// 注意：每次拼写时，chars 中的每个字母都只能用一次。 
//
// 返回词汇表 words 中你掌握的所有单词的 长度之和。 
//
// 
//
// 示例 1： 
//
// 输入：words = ["cat","bt","hat","tree"], chars = "atach"
//输出：6
//解释： 
//可以形成字符串 "cat" 和 "hat"，所以答案是 3 + 3 = 6。
// 
//
// 示例 2： 
//
// 输入：words = ["hello","world","leetcode"], chars = "welldonehoneyr"
//输出：10
//解释：
//可以形成字符串 "hello" 和 "world"，所以答案是 5 + 5 = 10。
// 
//
// 
//
// 提示： 
//
// 
// 1 <= words.length <= 1000 
// 1 <= words[i].length, chars.length <= 100 
// 所有字符串中都仅包含小写英文字母 
// 
// Related Topics 数组 哈希表


import java.util.HashMap;
import java.util.Map;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution1160 {
    public int countCharacters(String[] words, String chars) {
        Map<Character, Integer> charMap = new HashMap<>();
        for (char c : chars.toCharArray()) {
            charMap.put(c, charMap.getOrDefault(c, 0) + 1);
        }
        int total = 0;
        for (String word : words) {
            char[] characters = word.toCharArray();
            Map<Character, Integer> used = new HashMap<>();
            int flag = 0;
            for (char c : characters) {
                if (charMap.containsKey(c)) {
                    int max = charMap.get(c);
                    int count = used.getOrDefault(c, 0);
                    if (count < max) {
                        used.put(c, count + 1);
                        flag++;
                    }
                }
            }
            total += characters.length == flag ? characters.length : 0;
        }
        return total;
    }

}

/**
 * 使用int数组作为容器
 */
class Solution1160_1 {
    public int countCharacters(String[] words, String chars) {
        int[] chararr = new int[26];
        for (char c : chars.toCharArray()) {
            chararr[c - 'a'] = chararr[c - 'a'] + 1;
        }
        int total = 0;
        for (String word : words) {
            boolean flag = true;
            int[] used = new int[26];
            for (char c : word.toCharArray()) {
                // chars中有该字母
                if (chararr[c - 'a'] == 0) {
                    flag = false;
                    break;
                }
                if (used[c - 'a'] >= chararr[c - 'a']) {
                    flag = false;
                    break;
                }
                used[c - 'a'] = used[c - 'a'] + 1;
            }
            if (flag) {
                total += word.length();
            }
        }
        return total;
    }


}


//leetcode submit region end(Prohibit modification and deletion)
