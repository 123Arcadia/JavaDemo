package AlgorithmNotes.hot100.mergeAscListNode;

import java.util.PriorityQueue;

public class mergeKLists {

    class
    Status implements Comparable<ListNode> {
        private int val;
        private ListNode ptr;

        public Status(int val, ListNode ptr) {
            this.val = val;
            this.ptr = ptr;
        }



        @Override
        public int compareTo(ListNode o) {
            return this.val - o.val;
        }
    }

    public ListNode mergeKLists(ListNode[] lists) {
//        // 1. 合并每两个链表
//        ListNode newRes = null; // 新合并的链表
//        for (ListNode list : lists) {
//            newRes = mergeTwoLists(newRes, list);
//        }
//        return newRes;

        // 2. 分治合并
//        return merge(lists, 0, lists.length-1);

        // 3. 使用优先队列
        PriorityQueue<Status> queue = new PriorityQueue<>();
        for (ListNode list : lists) {
            if (list != null) {
                queue.offer(new Status(list.val, list));
            }
        }
        ListNode head = new ListNode(-1);
        ListNode cur = head;
        while (!queue.isEmpty()) {
            Status status = queue.poll();
            cur.next = status.ptr;
            cur = cur.next;
            // 每次从queue去除一个，就把他的next放进去
            if (status.ptr.next!=null) {
                queue.offer(new Status(status.ptr.next.val ,status.ptr.next));
            }
        }
        return head.next;
    }

    private ListNode merge(ListNode[] lists, int l, int r) {
        if (l == r) { // 分到l == r 时, 只能返回当前list[l], 参与别处的合并
            return lists[l];
        }
        if (l > r) { //错误
            return null;
        }
        int mid = (l + r) >> 1;
        return mergeTwoLists(merge(lists, l, mid), merge(lists, mid + 1, r));
    }

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
        while (list1 != null && list2 != null) {
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
