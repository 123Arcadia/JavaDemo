package AlgorithmNotes.hot100.mergeAscListNode;


public class mergeTwoLists {
    /**
     * 21. 合并两个有序链表
     */
    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        //-100 <= Node.val <= 100
//        if (list1 == null) {
//            return list2;
//         } else if (list2 == null) {
//            return list1;
//        } else if (list1.val < list2.val){
//            list1.next = mergeTwoLists(list1.next, list1);
//            return list1;
//        } else {
//            list2.next = mergeTwoLists(list2.next, list1);
//            return list2;

        // ListNode dum = new ListNode(-1);
        // ListNode pre =  dum;
        // while (list1!=null && list2 !=null) {
        //     if (list1.val < list2.val) {
        //         pre.next = list1;
        //         list1 = list1.next;
        //     } else {
        //         pre.next = list2;
        //         list2 = list2.next;
        //     }
        //     pre = pre.next;
        // }
        // pre.next = (list1 == null) ? list2 : list1;
        // return dum.next;
        ListNode dummy = new ListNode(-1);
        ListNode newHead = dummy;
        while (list1 != null && list2!=null) {
            if (list1.val < list2.val) {
                newHead.next = list1;
                list1 = list1.next;
            } else {
                newHead.next = list2;
                list2 = list2.next;
            }
            newHead = newHead.next;

        }
        if (list1 == null) {
            newHead.next = list2;
        }
        if (list2 == null) {
            newHead.next = list1;
        }
        return dummy.next;
    }
}
