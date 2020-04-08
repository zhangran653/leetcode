package leetcode.editor.cn;//给定两个单词（beginWord 和 endWord）和一个字典 wordList，找出所有从 beginWord 到 endWord 的最短转换序列。转换
//需遵循如下规则： 
//
// 
// 每次转换只能改变一个字母。 
// 转换过程中的中间单词必须是字典中的单词。 
// 
//
// 说明: 
//
// 
// 如果不存在这样的转换序列，返回一个空列表。 
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
//输出:
//[
//  ["hit","hot","dot","dog","cog"],
//  ["hit","hot","lot","log","cog"]
//]
// 
//
// 示例 2: 
//
// 输入:
//beginWord = "hit"
//endWord = "cog"
//wordList = ["hot","dot","dog","lot","log"]
//
//输出: []
//
//解释: endWord "cog" 不在字典中，所以不存在符合要求的转换序列。 
// Related Topics 广度优先搜索 数组 字符串 回溯算法


import java.util.*;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution126 {

    /**
     * dfs,有问题，超时
     *
     * @param beginWord
     * @param endWord
     * @param wordList
     * @return
     */

    public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
        List<List<String>> res = new LinkedList<>();
        if (!wordList.contains(endWord)) {
            return res;
        }
        Map<Integer, Set<String>> levelMap = bfs(beginWord, endWord, wordList);

        List<String> temp = new ArrayList<>();
        temp.add(beginWord);
        dfs(res, temp, new HashSet<>(), wordList, beginWord, endWord, 0, levelMap);
        return res;
    }

    private Map<Integer, Set<String>> bfs(String beginWord, String endWord, List<String> wordList) {
        Queue<String> queue = new LinkedList<>();
        Set<String> visited = new HashSet<>();
        queue.add(beginWord);
        visited.add(beginWord);
        Map<Integer, Set<String>> map = new HashMap<>();
        int level = 0;
        boolean isFind = false;
        map.put(0, new HashSet<>(visited));
        while (!queue.isEmpty()) {
            int size = queue.size();
            Set<String> v = new HashSet<>();
            while (size-- > 0 && !isFind) {
                String s = queue.poll();
                for (String w : wordList) {
                    if (visited.contains(w)) {
                        continue;
                    }
                    if (!canConvert(s, w)) {
                        continue;
                    }
                    if (endWord.equals(w)) {
                        queue.clear();
                        isFind = true;
                        v.add(w);
                        map.put(++level, v);
                        break;
                    }
                    queue.add(w);
                    visited.add(w);
                    v.add(w);
                }

            }
            if (!isFind) {
                map.put(++level, v);
            }

        }
        return map;

    }

    private void dfs(List<List<String>> res, List<String> temp, Set<String> visited, List<String> words, String cur, String endWord, int level, Map<Integer, Set<String>> levelMap) {

        if (cur.equals(endWord)) {
            res.add(new ArrayList<>(temp));
            return;
        }
        if (level >= levelMap.keySet().size() - 1) {
            return;
        }
        System.out.println(level);
        for (String word : levelMap.get(level + 1)) {
            if (visited.contains(word)) {
                continue;
            }
            if (!canConvert(cur, word)) {
                continue;
            }

            visited.add(word);
            temp.add(word);
            dfs(res, temp, visited, words, word, endWord, level + 1, levelMap);
            visited.remove(word);
            temp.remove(temp.size() - 1);
        }


    }

    private boolean canConvert(String a, String b) {
        if (a.length() != b.length()) {
            return false;
        }
        int count = 0;
        for (int i = 0; i < a.length(); i++) {
            if (a.charAt(i) != b.charAt(i)) {
                count++;
            }
            if (count > 1) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        String begin = "a";
        String end = "c";
        String[] wo = {"a", "b", "c"};
        List<List<String>> res = new Solution126().findLadders(begin, end, Arrays.asList(wo));
        System.out.println(res);
    }

}
//leetcode submit region end(Prohibit modification and deletion)
