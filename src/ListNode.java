/**
 * Created by Miaojiale on 2020/3/1.
 */
public class ListNode {
    int val;
    ListNode next;

    ListNode(int x) {
        val = x;
    }

    @Override
    public String toString() {
        String s = "";
        s += this.val;
        ListNode next = new ListNode(0);
        while (this.next != null){
            next = this.next;
            s += this.next.val;
            next = this.next;
        }
        return s;
    }
}
