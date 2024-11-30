package ru.job4j.algo.stack;

import java.util.Stack;

public class Brackets {
    /**
     * Memory complexity:
     * Object type: class java.util.Stack, size: 32 bytes
     * Object type: class java.lang.Boolean, size: 16 bytes
     * In total: 48 bytes
     *
     * Time complexity:
     * O(n), where n is length of input string.
     *
     * @param s
     * @return true if input string is correct
     * or false if not.
     */
    public boolean isValid(String s) {
        boolean result = s.isEmpty();
        if (s.length() > 1) {
            Stack<Character> stack = new Stack<>();
            for (int i = 0; i < s.length(); i++) {
                if (s.charAt(i) == '('
                        || s.charAt(i) == '{'
                        || s.charAt(i) == '[') {
                    stack.push(s.charAt(i));
                } else {
                    if (s.charAt(i) == ']' && stack.peek() == '[') {
                        stack.pop();
                    }
                    if (s.charAt(i) == '}' && stack.peek() == '{') {
                        stack.pop();
                    }
                    if (s.charAt(i) == ')' && stack.peek() == '(') {
                        stack.pop();
                    }
                }
            }
            if (stack.isEmpty()) {
                result = true;
            }
        }
        return result;
    }
}