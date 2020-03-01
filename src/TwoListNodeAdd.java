/**
 * Created by Miaojiale on 2020/3/1.
 */

//        给出两个 非空 的链表用来表示两个非负的整数。其中，它们各自的位数是按照 逆序 的方式存储的，并且它们的每个节点只能存储 一位 数字。
//        如果，我们将这两个数相加起来，则会返回一个新的链表来表示它们的和。
//        您可以假设除了数字 0 之外，这两个数都不会以 0 开头。
 /*   输入：(2 -> 4 -> 3) + (5 -> 6 -> 4)
            输出：7 -> 0 -> 8
            原因：342 + 465 = 807*/

public class TwoListNodeAdd {
    public static ListNode add(ListNode l1, ListNode l2) {
        ListNode dummary = new ListNode(0);
        ListNode listNode = dummary;
        int add = 0;
        while (l1 != null || l2 != null || add != 0) {
            int val1 = l1 == null ? 0 : l1.val;
            int val2 = l2 == null ? 0 : l2.val;
            int sum = val1 + val2 + add;
            add = sum / 10;
            listNode.next = new ListNode(sum % 10);
            listNode = listNode.next;
            if (l1 != null) {
                l1 = l1.next;
            }
            if (l2 != null) {
                l2 = l2.next;
            }
        }
        return listNode;
    }

    public static void main(String[] args) {
        ListNode l1 = new ListNode(3);
        l1.next = new ListNode(5);
//        l1.next.next = new ListNode(9);
        ListNode l2 = new ListNode(2);
//        l2.next = new ListNode(6);
//        l2.next.next = new ListNode(7);
        System.out.println(add(l1,l2));
    }

}
