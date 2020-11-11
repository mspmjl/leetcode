package leetcode;


import java.util.*;

/**
 * Created by Miaojiale on 2020/2/18.
 */
public class LeetCode {
    //  给定一个字符串 s，找到 s 中最长的回文子串。你可以假设 s 的最大长度为 1000。
    public String longestPalindrome(String s) {
        if (s == null || s.length() == 0) {
            return "";
        }
        int len = 0;
        int start = 0, end = 0;
        for (int i = 0; i < s.length(); i++) {
            int[] len1 = expandAroundCenter(s, i, i); // 用来找出奇数回文 like abdbc
            int[] len2 = expandAroundCenter(s, i, i + 1);// 用来找出偶数回文 like abbc
            if (len1[0] > len2[0] && len1[0] > len) {
                len = len1[0];
                start = len1[1];
                end = len1[2];
            } else if (len2[0] > len1[0] && len2[0] > len) {
                len = len2[0];
                start = len2[1];
                end = len2[2];
            }
        }
        return s.substring(start + 1, end);
    }

    private int[] expandAroundCenter(String s, int left, int right) {
        while (left >= 0 && right <= s.length() - 1 && s.charAt(left) == s.charAt(right)) {
            left--;
            right++;
        }
        int[] ints = {right - left - 1, left, right};
        return ints;
    }

    // 两数之和
    // 给定一个整数数组 nums 和一个目标值 target，请你在该数组中找出和为目标值的那 两个 整数，并返回他们的数组下标。
    //你可以假设每种输入只会对应一个答案。但是，你不能重复利用这个数组中同样的元素。
    public int[] twoSum(int[] nums, int target) {
        HashMap<Integer, Integer> map = new HashMap();
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(target - nums[i])) {
                return new int[]{map.get(target - nums[i]), i};
            }
            map.put(nums[i], i);
        }
        return null;
    }

    // 给定一个字符串，请你找出其中不含有重复字符的 最长子串 的长度。
    public static int lengthOfLongestSubstring(String s) {
        // 我的
        int length = 0;
        int start = 0;
        int end = 0;
        String maxS = "";
        while (end < s.length()) {
            char c = s.charAt(end);
            if (maxS.contains(String.valueOf(c))) {
                start += maxS.indexOf(c) + 1;
            }
            end++;
            maxS = s.substring(start, end);
            length = Math.max(length, maxS.length());
        }
        // 别人的
//        int n = s.length(), ans = 0;
//        Map<Character, Integer> map = new HashMap<>();//key出现的字符，value对应的最新的位置
//        // try to extend the range [i, j]
//        for (int end = 0, start = 0; end < n; end++) {
//            if (map.containsKey(s.charAt(end))) {
//                start = Math.max(map.get(s.charAt(end)) + 1, start);//由于重复的坐标不知道在start的前方还是后方，所以要取个最大值
//            }
//            ans = Math.max(ans, end - start + 1);
//            map.put(s.charAt(end), end);
//        }
//        return ans;
        return length;
    }

    // Z 字形变换
/*    输入: s = "LEETCODEISHIRING", numRows = 4
    输出: "LDREOEIIECIHNTSG"
    解释:

    0L     D     R
    1E   5O E   I I
    2E 4C   I H   N
    3T     S     G */
    public String convert(String s, int numRows) {
        String out = "";
        if (numRows == 1) {
            return s;
        } else if (s != null) {
            Map<Integer, List<String>> map = new HashMap<>();
            int cycle = numRows * 2 - 3; // leetco numRows * 2 - 3 个一个循环
            Integer count = 0;
            for (int i = 0; i < s.length(); i++) {
                int index = count < numRows ? count : cycle + 1 - count;
                List<String> strings = map.get(index);
                if (strings == null) {
                    strings = new ArrayList<>();
                    map.put(index, strings);
                }
                strings.add(String.valueOf(s.charAt(i)));
                count++;
                if (count > cycle) {
                    count = 0;
                }
            }
            Set<Map.Entry<Integer, List<String>>> entries = map.entrySet();

            for (Map.Entry<Integer, List<String>> entry : entries) {
                List<String> value = entry.getValue();
                if (value != null) {
                    for (String s1 : value) {
                        out += s1;
                    }
                }
            }
        }
        System.out.println(out);
        return out;
    }

    //整数反转
    //给出一个 32 位的有符号整数，你需要将这个整数中每位上的数字进行反转。
    public int reverse(int x) {
        long count = 0;
        while (x != 0) {
            count = count * 10 + x % 10;
            x = x / 10;
        }
        return count > 2147483647 || count < -2147483648 ? 0 : (int) count;
    }

    /* 给你 n 个非负整数 a1，a2，...，an，每个数代表坐标中的一个点 (i, ai) 。在坐标内画 n 条垂直线，垂直线 i 的两个端点分别为 (i, ai) 和 (i, 0)。找出其中的两条线，使得它们与 x 轴共同构成的容器可以容纳最多的水。

     说明：你不能倾斜容器，且 n 的值至少为 2。*/
    public int maxArea(int[] a) {
        int max = 0;
        for (int i = 0, j = a.length - 1; i < j; ) {
            int minHeight = a[i] < a[j] ? a[i++] : a[j--];
            max = Math.max(max, (j - i + 1) * minHeight);
        }
        return max;
    }

    // 编写一个函数来查找字符串数组中的最长公共前缀。
    // 如果不存在公共前缀，返回空字符串 ""。
    public String longestCommonPrefix(String[] strs) {
        if (strs != null && strs.length > 0) {
            String commonPrefix = strs[0];
            for (String str : strs) {
                commonPrefix = getCommonPrefix(str, commonPrefix);
                if (commonPrefix == null)
                    return null;
            }
            return commonPrefix;
        }
        return "";
    }

    public String getCommonPrefix(String s1, String s2) {
        if (s1 != null && s2 != null) {
            String commonPrefix = "";
            int j = s1.length() >= s2.length() ? s2.length() : s1.length();
            for (int i = 0; i < j; i++) {
                if (s1.charAt(i) == s2.charAt(i)) {
                    commonPrefix += s1.charAt(i);
                } else {
                    break;
                }
            }
            return commonPrefix;
        }
        return "";
    }

    /*给你一个包含 n 个整数的数组 nums，判断 nums 中是否存在三个元素 a，b，c ，使得 a + b + c = 0 ？请你找出所有满足条件且不重复的三元组。
    注意：答案中不可以包含重复的三元组。
    示例：

    给定数组 nums = [-1, 0, 1, 2, -1, -4]，

    满足要求的三元组集合为：
            [
            [-1, 0, 1],
            [-1, -1, 2]
            ]*/
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        if (nums != null && nums.length > 2) {
            Arrays.sort(nums);
            for (int i = 0; i <= nums.length - 3; i++) {
                // 和前一个i一样的话 就跳过 这样有可能含重复的
                if (i > 0 && nums[i] == nums[i - 1]) {
                    continue;
                }
                int j = i + 1;
                int k = nums.length - 1;
                while (j < k) {
                    int sum = nums[i] + nums[j] + nums[k];
                    if (sum == 0) {
                        result.add(Arrays.asList(nums[i], nums[j], nums[k]));
                        while (j < k && nums[j] == nums[j + 1]) {
                            // 和 nums[i] 一样 去重
                            j++;
                        }
                        while (j < k && nums[k] == nums[k - 1]) {
                            // 和 nums[i] 一样 去重
                            k--;
                        }
                        j++;
                        k--;
                    } else if (sum < 0) {
                        j++;
                    } else if (sum > 0) {
                        k--;
                    }
                }
            }
        }
        return result;
    }

    /*给定一个包括 n 个整数的数组 nums 和 一个目标值 target。
    找出 nums 中的三个整数，使得它们的和与 target 最接近。
    返回这三个数的和。假定每组输入只存在唯一答案。*/
    public int threeSumClosest(int[] nums, int target) {
        if (nums == null || nums.length < 3)
            return 0;
        Arrays.sort(nums);
        int result = nums[0] + nums[1] + nums[2];
        int length = nums.length;
        for (int i = 0; i < length - 2; i++) {
            int start = i + 1, end = length - 1;
            while (start < end) {
                int sum = nums[i] + nums[start] + nums[end];
                if (Math.abs(target - sum) <= Math.abs(target - result)) {
                    result = sum;
                }
                if (sum < target) {
                    start++;
                } else if (sum > target) {
                    end--;
                } else {
                    return sum;
                }
            }
        }
        return result;
    }



    public static void main(String[] args) {
        System.out.println('1'-'0');
    }
}
