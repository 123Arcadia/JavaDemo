package AlgorithmNotes.hot100.ListNodeTest;


class ListNode {
    int val;
    ListNode next;

    ListNode() {
    }

    ListNode(int val) {
        this.val = val;
    }

    ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }
}

public class LitsNodeCode {

    /**
     * 反转链表
     *
     * @param head
     * @return
     */
    public ListNode reverseList(ListNode head) {
//        // 双指针
//        ListNode pre = null;
//        ListNode cur = head;
//        while (cur!=null) {
//            ListNode t = cur.next;
//            cur.next = pre;
//            pre = cur;
//            cur = t;
//        }
//        return pre;

        // 递归
        return backTrack(head, null);
    }

    private ListNode backTrack(ListNode cur, ListNode pre) {
        if (cur == null) {
            return pre;
        }
        ;
        ListNode node = backTrack(cur.next, cur);
        cur.next = pre;
        return node;
    }


    /**
     * 21. 合并两个有序链表
     */
    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
//        if (list1 == null) return list2;
//        if (list2 == null) return list1;
//        ListNode dum = new ListNode(-1);
//        ListNode cur = dum;
//        while (list1 != null && list2 != null) {
////            ListNode node = new ListNode(Math.min(list1.val ,list2.val));
//            if (list1.val < list2.val) {
//                cur.next = list1;
//                list1 = list1.next;
//            } else {
//                cur.next = list2;
//                list2 = list2.next;
//            }
//            cur = cur.next;
//        }
//        if (list1 == null) {
//            cur.next = list2;
//        }
//        if (list2 == null) {
//            cur.next = list1;
//        }
//        return dum.next;

        if (list1 == null) {
            return list2;
        }
        if (list2 == null) {
            return list1;
        }
        if (list1.val < list2.val) {
            list1.next = mergeTwoLists(list1.next, list2);
            return list1;
        } else {
            list2.next = mergeTwoLists(list1, list2.next);
            return list2;
        }
    }

}
