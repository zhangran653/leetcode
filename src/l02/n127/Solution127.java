package l02.n127;//给定两个单词（beginWord 和 endWord）和一个字典，找到从 beginWord 到 endWord 的最短转换序列的长度。转换需遵循如下规则：
// 
//
// 
// 每次转换只能改变一个字母。 
// 转换过程中的中间单词必须是字典中的单词。 
// 
//
// 说明: 
//
// 
// 如果不存在这样的转换序列，返回 0。 
// 所有单词具有相同的长度。 
// 所有单词只由小写字母组成。 
// 字典中不存在重复的单词。 
// 你可以假设 beginWord 和 endWord 是非空的，且二者不相同。 
// 
//
// 示例 1: 
//
// 输入:
//beginWord = "hit",
//endWord = "cog",
//wordList = ["hot","dot","dog","lot","log","cog"]
//
//输出: 5
//
//解释: 一个最短转换序列是 "hit" -> "hot" -> "dot" -> "dog" -> "cog",
//     返回它的长度 5。
// 
//
// 示例 2: 
//
// 输入:
//beginWord = "hit"
//endWord = "cog"
//wordList = ["hot","dot","dog","lot","log"]
//
//输出: 0
//
//解释: endWord "cog" 不在字典中，所以无法进行转换。 
// Related Topics 广度优先搜索


import java.util.*;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution127 {
    /**
     * BFS
     *
     * @param beginWord
     * @param endWord
     * @param wordList
     * @return
     */
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        if (wordList.isEmpty() || !wordList.contains(endWord)) {
            return 0;
        }
        Deque<String> queue = new ArrayDeque<>();
        Set<String> visited = new HashSet<>();
        queue.addLast(beginWord);
        int count = 0;
        while (!queue.isEmpty()) {
            count++;
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                String start = queue.pollFirst();
                for (String word : wordList) {
                    if (visited.contains(word)) {
                        continue;
                    }
                    if (!canConvert(start, word)) {
                        continue;
                    }
                    if (endWord.equals(word)) {
                        return count + 1;
                    }
                    visited.add(word);
                    queue.addLast(word);
                }
            }
        }
        return 0;
    }

    /**
     * 双端BFS
     *
     * @param beginWord
     * @param endWord
     * @param wordList
     * @return
     */
    public int ladderLength1(String beginWord, String endWord, List<String> wordList) {
        if (wordList.isEmpty() || !wordList.contains(endWord)) {
            return 0;
        }
        Deque<String> queue1 = new ArrayDeque<>();
        Set<String> v1 = new HashSet<>();
        queue1.addLast(beginWord);
        v1.add(beginWord);
        int count1 = 0;

        Deque<String> queue2 = new ArrayDeque<>();
        Set<String> v2 = new HashSet<>();
        queue2.addLast(endWord);
        v2.add(endWord);
        int count2 = 0;

        while (!queue1.isEmpty() && !queue2.isEmpty()) {
            count1++;
            for (int i = 0; i < queue1.size(); i++) {
                String s = queue1.pollFirst();
                for (String word : wordList) {
                    if (v1.contains(word)) {
                        continue;
                    }
                    if (!canConvert(s, word)) {
                        continue;
                    }
                    if (v2.contains(word)) {
                        return count1 + count2 + 1;
                    }
                    v1.add(word);
                    queue1.addLast(word);
                }
            }

            count2++;
            for (int i = 0; i < queue2.size(); i++) {
                String s = queue2.pollFirst();
                for (String word : wordList) {
                    if (v2.contains(word)) {
                        continue;
                    }
                    if (!canConvert(s, word)) {
                        continue;
                    }
                    if (v1.contains(word)) {
                        return count1 + count2 + 1;
                    }
                    v2.add(word);
                    queue2.addLast(word);
                }
            }
        }
        return 0;

    }


    /**
     * 双端队列，每次从较少的队列开始
     *
     * @param beginWord
     * @param endWord
     * @param wordList
     * @return
     */
    public int ladderLength2(String beginWord, String endWord, List<String> wordList) {
        if (!wordList.contains(endWord)) {
            return 0;
        }
        wordList.add(beginWord);
        Deque<String> queue1 = new ArrayDeque<>();
        Set<String> v1 = new HashSet<>();
        queue1.addLast(beginWord);
        v1.add(beginWord);

        Deque<String> queue2 = new ArrayDeque<>();
        Set<String> v2 = new HashSet<>();
        queue2.addLast(endWord);
        v2.add(endWord);
        int count = 0;

        while (!queue1.isEmpty() && !queue2.isEmpty()) {

            if (queue1.size() > queue2.size()) {
                Deque<String> temp = queue1;
                queue1 = queue2;
                queue2 = temp;

                Set<String> tempv = v1;
                v1 = v2;
                v2 = tempv;
            }
            count++;
            int size = queue1.size();
            while (size-- > 0) {
                String s = queue1.pollFirst();
                for (String word : wordList) {
                    if (v1.contains(word)) {
                        continue;
                    }
                    if (!canConvert(s, word)) {
                        continue;
                    }
                    if (v2.contains(word)) {
                        return count + 1;
                    }
                    v1.add(word);
                    queue1.addLast(word);

                }
            }

        }

        return 0;
    }

    private boolean canConvert(String a, String b) {
        if (a.length() != b.length()) {
            return false;
        }
        int diff = 0;
        for (int i = 0; i < a.length(); i++) {
            if (a.charAt(i) != b.charAt(i)) {
                diff++;
            }
            if (diff > 1) {
                return false;
            }
        }
        return true;
    }


}
//leetcode submit region end(Prohibit modification and deletion)
