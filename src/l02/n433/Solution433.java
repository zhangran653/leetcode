package l02.n433;//一条基因序列由一个带有8个字符的字符串表示，其中每个字符都属于 "A", "C", "G", "T"中的任意一个。
//
// 假设我们要调查一个基因序列的变化。一次基因变化意味着这个基因序列中的一个字符发生了变化。 
//
// 例如，基因序列由"AACCGGTT" 变化至 "AACCGGTA" 即发生了一次基因变化。 
//
// 与此同时，每一次基因变化的结果，都需要是一个合法的基因串，即该结果属于一个基因库。 
//
// 现在给定3个参数 — start, end, bank，分别代表起始基因序列，目标基因序列及基因库，请找出能够使起始基因序列变化为目标基因序列所需的最少变
//化次数。如果无法实现目标变化，请返回 -1。 
//
// 注意: 
//
// 
// 起始基因序列默认是合法的，但是它并不一定会出现在基因库中。 
// 所有的目标基因序列必须是合法的。 
// 假定起始基因序列与目标基因序列是不一样的。 
// 
//
// 示例 1: 
//
// 
//start: "AACCGGTT"
//end:   "AACCGGTA"
//bank: ["AACCGGTA"]
//
//返回值: 1
// 
//
// 示例 2: 
//
// 
//start: "AACCGGTT"
//end:   "AAACGGTA"
//bank: ["AACCGGTA", "AACCGCTA", "AAACGGTA"]
//
//返回值: 2
// 
//
// 示例 3: 
//
// 
//start: "AAAAACCC"
//end:   "AACCCCCC"
//bank: ["AAAACCCC", "AAACCCCC", "AACCCCCC"]
//
//返回值: 3
// 
//


import java.util.*;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution433 {
    int res = -1;


    /**
     * 双向bfs
     *
     * @param start
     * @param end
     * @param bank
     * @return
     */
    public int minMutation(String start, String end, String[] bank) {
        Set<String> set = new HashSet<>(Arrays.asList(bank));
        if (!set.contains(end)) {
            return -1;
        }
        Deque<String> q1 = new ArrayDeque<>();
        Set<String> v1 = new HashSet<>();
        q1.addLast(start);
        v1.add(start);

        Deque<String> q2 = new ArrayDeque<>();
        Set<String> v2 = new HashSet<>();
        q2.addLast(end);
        v2.add(end);

        int count = 0;
        while (!q1.isEmpty() && !q2.isEmpty()) {
            count++;
            if (q1.size() > q2.size()) {
                Deque<String> temp = q1;
                q1 = q2;
                q2 = temp;

                Set<String> v = v1;
                v1 = v2;
                v2 = v;

            }

            int size = q1.size();
            while (size-- > 0) {
                String s = q1.pollFirst();
                for (String word : set) {
                    if (v1.contains(word)) {
                        continue;
                    }
                    if (!canConvert(s, word)) {
                        continue;
                    }
                    if (v2.contains(word)) {
                        return count + 1;
                    }
                    q1.addLast(word);
                    v1.add(word);
                }
            }
        }
        return -1;
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
        return count == 1;
    }


    public static void main(String[] args) {
        String[] bank = {"AACCGGTA", "AACCGCTA", "AAACGGTA"};
        int res = new Solution433().minMutation("AACCGGTT", "AAACGGTA", bank);

        System.out.println(res);
    }

}
//leetcode submit region end(Prohibit modification and deletion)
