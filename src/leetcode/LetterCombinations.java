package leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Miaojiale on 11/11/2020.
 */
public class LetterCombinations {

    private List<String> strings = new ArrayList<>();
    private String[] numbers = new String[]{"", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};
    private StringBuilder stringBuilder = new StringBuilder();

    /*给定一个仅包含数字 2-9 的字符串，返回所有它能表示的字母组合。
    给出数字到字母的映射如下（与电话按键相同）。注意 1 不对应任何字母。*/
    public List<String> letterCombinations(String digits) {
        letterCombinations(digits, 0);
        System.out.println(strings);
        return strings;
    }

    public void letterCombinations(String digits, int index) {
        if (index == digits.length()) {
            strings.add(stringBuilder.toString());
            return;
        }
        char c = digits.charAt(index);
        int thisNum = c - '0';
        String letter = numbers[thisNum];
        for (int i = 0; i < letter.length(); i++) {
            stringBuilder.append(letter.charAt(i));
            letterCombinations(digits, index + 1);
            stringBuilder.deleteCharAt(stringBuilder.length()-1);
        }
    }

    public static void main(String[] args) {
        LetterCombinations letterCombinations = new LetterCombinations();
        letterCombinations.letterCombinations("23");
    }
}
