package l02.n42;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Stack;

class Solution42 {
    public int trap(int[] height) {
        int sum = 0;
        Stack<Integer> stack = new Stack<>();
        int current = 0;
        while (current < height.length) {
            //如果栈不空并且当前指向的高度大于栈顶高度就一直循环
            while (!stack.empty() && height[current] > height[stack.peek()]) {
                //取出要出栈的元素
                int h = height[stack.peek()];
                stack.pop(); //出栈
                // 栈空就出去
                if (stack.empty()) {
                    break;
                }
                //两堵墙之前的距离。
                int distance = current - stack.peek() - 1;
                int min = Math.min(height[stack.peek()], height[current]);
                sum = sum + distance * (min - h);
            }
            //当前指向的墙入栈
            stack.push(current);
            //指针后移
            current++;

        }
        return sum;
    }

    public int trap1(int[] height) {
        int sum = 0;
        int cur = 0;
        Deque<Integer> stack = new ArrayDeque<>();
        while (cur < height.length) {
            while (!stack.isEmpty() && height[cur] > height[stack.getLast()]) {
                // 取出要出栈的元素
                int h = height[stack.removeLast()];
                if (stack.isEmpty()) {
                    break;
                }
                // 2堵墙之间的距离
                int distance = cur - stack.getLast() - 1;
                int min = Math.min(height[cur], height[stack.getLast()]);
                sum = sum + distance * (min - h);
            }

            stack.addLast(cur);
            cur++;
        }
        return sum;

    }

}